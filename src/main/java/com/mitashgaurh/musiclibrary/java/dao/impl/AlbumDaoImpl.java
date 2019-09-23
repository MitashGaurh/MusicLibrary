package com.mitashgaurh.musiclibrary.java.dao.impl;

import com.mitashgaurh.musiclibrary.java.dao.AlbumDao;
import com.mitashgaurh.musiclibrary.java.dao.base.BaseDaoImpl;
import com.mitashgaurh.musiclibrary.java.entities.Album;
import com.mitashgaurh.musiclibrary.java.exception.DataAccessLayerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlbumDaoImpl extends BaseDaoImpl<Album> implements AlbumDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlbumDaoImpl.class);

    @Override
    public Album createAlbum(Album album) {
        LOGGER.trace("createAlbum");
        try {
            return createEntity(album);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred in createAlbum ", ex);
            throw new DataAccessLayerException("createAlbum failed ", ex);
        }
    }

    @Override
    public Album updateAlbum(Album album) {
        LOGGER.trace("updateAlbum");
        try {
            return updateEntity(album);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred in updateAlbum ", ex);
            throw new DataAccessLayerException("updateAlbum failed ", ex);
        }
    }

    @Override
    public void deleteAlbum(Album album) {
        LOGGER.trace("deleteAlbum");
        try {
            deleteEntity(album);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred in deleteAlbum ", ex);
            throw new DataAccessLayerException("deleteAlbum failed ", ex);
        }
    }

    @Override
    public Album findAlbumById(Integer albumId) {
        LOGGER.trace("findAlbumById");
        try {
            return findEntityById(Album.class, albumId);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred while retrieving album with id, {} ", albumId, ex);
            throw new DataAccessLayerException("findAlbumById failed ", ex);
        }
    }

    @Override
    public List<Album> getAlbums() {
        LOGGER.trace("getAlbums");
        try {
            return fetchEntities(Album.class);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred while fetching getting album list ", ex);
            throw new DataAccessLayerException("getAlbums failed", ex);
        }
    }
}
