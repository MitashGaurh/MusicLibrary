package com.mitashgaurh.musiclibrary.java.service.impl;

import com.mitashgaurh.musiclibrary.java.config.mapper.BeanMapper;
import com.mitashgaurh.musiclibrary.java.config.mapper.BeanMapperFactory;
import com.mitashgaurh.musiclibrary.java.dao.AlbumDao;
import com.mitashgaurh.musiclibrary.java.dao.SongDao;
import com.mitashgaurh.musiclibrary.java.dao.TrackDao;
import com.mitashgaurh.musiclibrary.java.dto.AlbumDto;
import com.mitashgaurh.musiclibrary.java.dto.SongDto;
import com.mitashgaurh.musiclibrary.java.entities.Album;
import com.mitashgaurh.musiclibrary.java.entities.Song;
import com.mitashgaurh.musiclibrary.java.entities.Track;
import com.mitashgaurh.musiclibrary.java.service.AlbumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Transactional
@Service
public class AlbumServiceImpl implements AlbumService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumServiceImpl.class);

    @Autowired
    private SongDao songDao;

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private TrackDao trackDao;

    @Autowired
    private
    BeanMapperFactory beanMapperFactory;

    @Override
    public List<AlbumDto> getAlbums() {
        LOGGER.trace("getAlbums called");
        List<AlbumDto> albumDtoList;
        try {
            BeanMapper orikaMapper = beanMapperFactory.getMapper(BeanMapperFactory.ORIKA_MAPPER);
            List<Album> albums = albumDao.getAlbums();
            albumDtoList = new ArrayList<>();
            orikaMapper.mapCollections(albums, albumDtoList, AlbumDto.class);
            for (AlbumDto albumDto : albumDtoList) {
                List<Track> tracks = trackDao.getTracksByAlbum(Collections.singletonList(albumDto.getId()));
                List<Integer> songIds = tracks.parallelStream().map(Track::getSongId).collect(Collectors.toList());
                List<Song> songs = songDao.getSongsByCriteria(songIds);
                List<SongDto> songDtoList = new ArrayList<>();
                orikaMapper.mapCollections(songs, songDtoList, SongDto.class);
                albumDto.setSongList(songDtoList);
            }
        } catch (Exception ex) {
            LOGGER.error("Exception: getAlbums ", ex);
            throw ex;
        }
        LOGGER.trace("getAlbums ended");
        return albumDtoList;
    }

    @Override
    public AlbumDto createAlbum(@NotNull @Valid AlbumDto albumDto) {
        LOGGER.trace("createAlbum called");
        AlbumDto createdAlbumDto;
        try {
            BeanMapper directMapper = beanMapperFactory.getMapper(BeanMapperFactory.ORIKA_MAPPER);
            BeanMapper orikaMapper = beanMapperFactory.getMapper(BeanMapperFactory.ORIKA_MAPPER);
            Album album = new Album();
            directMapper.map(albumDto, album);
            album = albumDao.createAlbum(album);
            createdAlbumDto = (AlbumDto) orikaMapper.map(album, AlbumDto.class);

            ArrayList<SongDto> songDtoList = new ArrayList<>();
            for (SongDto songDto : albumDto.getSongList()) {
                Song song = new Song();
                directMapper.map(songDto, song);
                song = songDao.addSong(song);
                songDtoList.add((SongDto) orikaMapper.map(song, SongDto.class));
                trackDao.createTrack(new Track(album.getId(), song.getId()));
            }
            createdAlbumDto.setSongList(songDtoList);
        } catch (Exception ex) {
            LOGGER.error("Exception: createAlbum ", ex);
            throw ex;
        }
        LOGGER.trace("createAlbum ended");
        return createdAlbumDto;
    }

    @Override
    public AlbumDto updateAlbum(@NotNull @Valid AlbumDto albumDto) {
        return null;
    }

    @Override
    public void deleteAlbum(@NotNull Integer songId) {

    }
}
