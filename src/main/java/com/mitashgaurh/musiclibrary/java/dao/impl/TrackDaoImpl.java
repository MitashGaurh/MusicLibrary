package com.mitashgaurh.musiclibrary.java.dao.impl;

import com.mitashgaurh.musiclibrary.java.dao.TrackDao;
import com.mitashgaurh.musiclibrary.java.dao.base.BaseDaoImpl;
import com.mitashgaurh.musiclibrary.java.entities.Track;
import com.mitashgaurh.musiclibrary.java.exception.DataAccessLayerException;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrackDaoImpl extends BaseDaoImpl<Track> implements TrackDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackDaoImpl.class);

    @Override
    public void createTrack(Track track) {
        LOGGER.trace("createTrack");
        try {
            createEntity(track);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred in createTrack ", ex);
            throw new DataAccessLayerException("createTrack failed ", ex);
        }
    }

    @Override
    public void deleteTrack(Track track) {
        LOGGER.trace("deleteTrack");
        try {
            deleteEntity(track);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred in deleteTrack ", ex);
            throw new DataAccessLayerException("deleteTrack failed ", ex);
        }
    }

    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    @Override
    public List<Track> getTracksByAlbum(List<Integer> albumIds) {
        LOGGER.trace("getTracksByAlbum");
        try {
            Query query = getSessionFactory().getCurrentSession().createQuery("FROM Track t WHERE t.albumId IN :ids");
            query.setParameterList("ids", albumIds);
            return query.getResultList();
        } catch (Exception ex) {
            LOGGER.error("Exception occurred in getTracksByAlbum ", ex);
            throw new DataAccessLayerException("getTracksByAlbum failed ", ex);
        }
    }
}
