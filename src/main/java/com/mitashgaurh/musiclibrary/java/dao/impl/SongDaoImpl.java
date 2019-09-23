package com.mitashgaurh.musiclibrary.java.dao.impl;

import com.mitashgaurh.musiclibrary.java.dao.SongDao;
import com.mitashgaurh.musiclibrary.java.dao.base.BaseDaoImpl;
import com.mitashgaurh.musiclibrary.java.entities.Song;
import com.mitashgaurh.musiclibrary.java.exception.DataAccessLayerException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongDaoImpl extends BaseDaoImpl<Song> implements SongDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SongDaoImpl.class);

    @Override
    public Song addSong(Song song) throws DataAccessLayerException {
        LOGGER.trace("addSong");
        try {
            return createEntity(song);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred in addSong ", ex);
            throw new DataAccessLayerException("addSong failed ", ex);
        }
    }

    @Override
    public Song updateSong(Song song) {
        LOGGER.trace("updateSong");
        try {
            return updateEntity(song);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred in updateSong ", ex);
            throw new DataAccessLayerException("updateSong failed ", ex);
        }
    }

    @Override
    public void deleteSong(Song song) {
        LOGGER.trace("deleteSong");
        try {
            deleteEntity(song);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred in deleteSong ", ex);
            throw new DataAccessLayerException("deleteSong failed ", ex);
        }
    }

    @Override
    public Song findSongById(Integer songId) {
        LOGGER.trace("findSongById");
        try {
            return findEntityById(Song.class, songId);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred while retrieving song with id, {} ", songId, ex);
            throw new DataAccessLayerException("findSongById failed ", ex);
        }
    }

    @Override
    public List<Song> getSongs() {
        LOGGER.trace("getSongList");
        try {
            return fetchEntities(Song.class);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred while fetching getting song list ", ex);
            throw new DataAccessLayerException("getSongList failed", ex);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Song> getSongsByCriteria(List<Integer> ids) {
        LOGGER.trace("getSongsByCriteria");
        try {
            Query query = getSessionFactory().getCurrentSession().createQuery("FROM Song s WHERE s.id IN :ids");
            query.setParameterList("ids", ids);
            return query.getResultList();
        } catch (Exception ex) {
            LOGGER.error("Exception occurred in getSongsByCriteria ", ex);
            throw new DataAccessLayerException("getSongsByCriteria failed ", ex);
        }
        /*LOGGER.trace("getSongList");
        try {
            return fetchEntitiesByCriteria(Song.class, criteria);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred while fetching getting song list ", ex);
            throw new DataAccessLayerException("getSongList failed", ex);
        }*/
    }
}
