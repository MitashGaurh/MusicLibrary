package com.mitashgaurh.musiclibrary.java.service.impl;

import com.mitashgaurh.musiclibrary.java.config.mapper.BeanMapper;
import com.mitashgaurh.musiclibrary.java.config.mapper.BeanMapperFactory;
import com.mitashgaurh.musiclibrary.java.dao.AlbumDao;
import com.mitashgaurh.musiclibrary.java.dao.SongDao;
import com.mitashgaurh.musiclibrary.java.dao.TrackDao;
import com.mitashgaurh.musiclibrary.java.dto.SongDto;
import com.mitashgaurh.musiclibrary.java.entities.Album;
import com.mitashgaurh.musiclibrary.java.entities.Song;
import com.mitashgaurh.musiclibrary.java.entities.Track;
import com.mitashgaurh.musiclibrary.java.exception.DataNotValidException;
import com.mitashgaurh.musiclibrary.java.service.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Transactional
@Service
public class SongServiceImpl implements SongService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongServiceImpl.class);

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
    public List<SongDto> getSongList() {
        LOGGER.trace("getSongList called");
        List<SongDto> songDtoList;
        try {
            BeanMapper orikaMapper = beanMapperFactory.getMapper(BeanMapperFactory.ORIKA_MAPPER);
            List<Song> songList = songDao.getSongs();
            songDtoList = new ArrayList<>();
            orikaMapper.mapCollections(songList, songDtoList, SongDto.class);
        } catch (Exception ex) {
            LOGGER.error("Exception: getSongList ", ex);
            throw ex;
        }
        LOGGER.trace("getSongList ended");
        return songDtoList;
    }

    @Override
    public SongDto addSongToAlbum(@Valid Integer albumId, @Valid SongDto songDto) {
        LOGGER.trace("addSongToAlbum called");
        SongDto createdSongDto;
        try {
            BeanMapper directMapper = beanMapperFactory.getMapper(BeanMapperFactory.ORIKA_MAPPER);
            BeanMapper orikaMapper = beanMapperFactory.getMapper(BeanMapperFactory.ORIKA_MAPPER);
            Song song = new Song();
            directMapper.map(songDto, song);
            song = songDao.addSong(song);
            if (albumId != 0) {
                Album existingAlbum = retrieveAlbum(albumId);
                trackDao.createTrack(new Track(existingAlbum.getId(), song.getId()));
            }
            createdSongDto = (SongDto) orikaMapper.map(song, SongDto.class);
        } catch (Exception ex) {
            LOGGER.error("Exception: addSongToAlbum ", ex);
            throw ex;
        }
        LOGGER.trace("addSongToAlbum ended");
        return createdSongDto;
    }

    @Override
    public SongDto updateSong(@Valid SongDto songDto) {
        LOGGER.trace("updateSong called");
        SongDto updatedSongDto;
        try {
            BeanMapper directMapper = beanMapperFactory.getMapper(BeanMapperFactory.ORIKA_MAPPER);
            BeanMapper orikaMapper = beanMapperFactory.getMapper(BeanMapperFactory.ORIKA_MAPPER);
            Song existingSong = retrieveSong(songDto.getId());
            directMapper.map(songDto, existingSong);
            existingSong = songDao.updateSong(existingSong);
            updatedSongDto = (SongDto) orikaMapper.map(existingSong, SongDto.class);
        } catch (Exception e) {
            LOGGER.error("Exception: updateSong", e);
            throw e;
        }
        LOGGER.trace("updateSong ended");
        return updatedSongDto;
    }

    @Override
    public void deleteSong(Integer songId) {
        LOGGER.trace("deleteSong called");
        try {
            Song song = retrieveSong(songId);
            songDao.deleteSong(song);
        } catch (Exception e) {
            LOGGER.error("Exception: deleteSong", e);
            throw e;
        }
        LOGGER.trace("deleteSong ended");
    }

    private Song retrieveSong(Integer songId) {
        try {
            LOGGER.debug("Fetching song with Id: " + songId);
            Song song = songDao.findSongById(songId);
            if (null == song) {
                throw new DataNotValidException("Song with Id " + songId + " not found.");
            }
            return song;
        } catch (Exception ex) {
            LOGGER.error("Exception: retrieveSong", ex);
            throw ex;
        }
    }

    private Album retrieveAlbum(Integer albumId) {
        try {
            LOGGER.debug("Fetching album with Id: " + albumId);
            Album album = albumDao.findAlbumById(albumId);
            if (null == album) {
                throw new DataNotValidException("Album with Id " + albumId + " not found.");
            }
            return album;
        } catch (Exception ex) {
            LOGGER.error("Exception: retrieveAlbum", ex);
            throw ex;
        }
    }
}
