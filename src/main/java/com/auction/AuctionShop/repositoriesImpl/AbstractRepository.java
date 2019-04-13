package com.auction.AuctionShop.repositoriesImpl;

import com.auction.AuctionShop.entities.AbstractEntity;
import com.auction.AuctionShop.repositories.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.*;
import java.util.List;

public abstract class AbstractRepository<T extends AbstractEntity> implements Repository<T> {

    @Autowired
    private SessionFactory sessionFactory;
    private static final Logger log = LogManager.getLogger();

    private Class<T> clazz;
    void setClazz( Class< T > clazzToSet ) {
        this.clazz = clazzToSet;
    }

    @Override
    public Class<T> getClazz(){ return clazz;}

    public void save(T entity){
        log.debug("Saving " + clazz + " with id: " + entity.getId());
        getSession().persist(entity);
    }

    //Return all
    public List<T> getAll(){
        log.debug("Get all " + clazz );
        CriteriaBuilder builder = getSession().getCriteriaBuilder();

        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> root = criteria.from(clazz);
        criteria.select(root);

        Query<T> query = getSession().createQuery(criteria);
        log.debug("Return all " + clazz);
        return query.getResultList();
    }

    //Return by given id
    @Override
    public T findById(long id) {
        log.debug("Get " + clazz + " with id=" + id);
        CriteriaBuilder builder = getSession().getCriteriaBuilder();

        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> root = criteria.from(clazz);

        ParameterExpression<Long> idParameter = builder.parameter(long.class);
        criteria.where(builder.equal(root.get("id"), idParameter));

        Query<T> query = getSession().createQuery(criteria);
        query.setParameter(idParameter, id);
        log.debug("Return " + clazz + " with id: " + id);
        return query.getSingleResult();
    }

    public abstract void update(T entity);

    //Deleting given entity
    //Return integer with quantity of deleted entities
    @Override
    public int delete(T entity) {
        log.debug("Delete " + clazz + " with id= " + entity.getId());
        CriteriaBuilder builder = getSession().getCriteriaBuilder();

        CriteriaDelete<T> criteria = builder.createCriteriaDelete(clazz);
        Root<T> root = criteria.from(clazz);

        criteria.where(builder.equal(root.get("id"), entity.getId()));

        Query<T> query = getSession().createQuery(criteria);
        int result = query.executeUpdate();
        log.debug("Deleted " + clazz + " with result: " + result);
        return result;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
