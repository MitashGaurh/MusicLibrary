package com.mitashgaurh.musiclibrary.java.dao.base;

import com.mitashgaurh.musiclibrary.java.exception.DataAccessLayerException;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<E extends Serializable> {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    protected E createEntity(E entity) throws DataAccessLayerException {
        try {
            return (E) sessionFactory.getCurrentSession().merge(entity);
        } catch (Exception ex) {
            throw new DataAccessLayerException("Error in creating " + entity.getClass().getSimpleName(), ex);
        }
    }

    protected E updateEntity(E entity) throws DataAccessLayerException {
        try {
            return (E) sessionFactory.getCurrentSession().merge(entity);
        } catch (Exception ex) {
            throw new DataAccessLayerException("Error in updating " + entity.getClass().getSimpleName(), ex);
        }
    }

    protected void deleteEntity(E entity) throws DataAccessLayerException {
        try {
            sessionFactory.getCurrentSession().delete(entity);
        } catch (Exception ex) {
            throw new DataAccessLayerException("Error in deleting " + entity.getClass().getSimpleName(), ex);
        }
    }

    protected List<E> fetchEntities(Class clazz) throws DataAccessLayerException {
        try {
            return sessionFactory.getCurrentSession().createQuery("from " + clazz.getName()).list();
        } catch (Exception ex) {
            throw new DataAccessLayerException("Error in finding entities for " + clazz.getSimpleName(), ex);
        }
    }

    protected List<E> fetchEntitiesByCriteria(Class<E> clazz, String criteria) throws DataAccessLayerException {
        try {
            return sessionFactory.getCurrentSession().createQuery("from " + clazz.getName() + " where " + criteria).list();
        } catch (Exception ex) {
            throw new DataAccessLayerException("Error in finding entities for " + clazz.getSimpleName(), ex);
        }
    }

    protected E findEntityById(Class<E> clazz, int id) throws DataAccessLayerException {
        try {
            return sessionFactory.getCurrentSession().find(clazz, id);
        } catch (Exception e) {
            throw new DataAccessLayerException("Failed to find by Id for " + clazz.getSimpleName(), e);
        }
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
