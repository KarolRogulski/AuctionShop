package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.domain.AbstractEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.*;
import java.util.List;

public abstract class AbstractRepository<T extends AbstractEntity> implements Repository<T>{

    @Autowired
    private SessionFactory sessionFactory;
    private static final Logger log = LogManager.getLogger();

    private Class<T> clazz;
    protected void setClazz( Class< T > clazzToSet ) {
        this.clazz = clazzToSet;
    }

    public Class<T> getClazz(){ return clazz;}

    public void save(T entity){
        getSession().persist(entity);
        log.info("Saving " + clazz + " with id: " + entity.getId());
    }

    //Return all
    public List<T> getAll(){
        log.info("Get all " + clazz );
        CriteriaBuilder builder = getSession().getCriteriaBuilder();

        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> root = criteria.from(clazz);
        criteria.select(root);

        Query<T> query = getSession().createQuery(criteria);
        return query.getResultList();
    }

    //Return by given id
    @Override
    public T findById(long id) {
        log.info("Get " + clazz + " with id=" + id);
        CriteriaBuilder builder = getSession().getCriteriaBuilder();

        CriteriaQuery<T> criteria = builder.createQuery(clazz);
        Root<T> root = criteria.from(clazz);

        ParameterExpression<Long> idParameter = builder.parameter(long.class);
        criteria.where(builder.equal(root.get("id"), idParameter));

        Query<T> query = getSession().createQuery(criteria);
        query.setParameter(idParameter, id);
        return query.getSingleResult();
    }

    public abstract void update(T entity);

    @Override
    public void delete(T entity) {
        log.info("Delete " + clazz + " with id= " + entity.getId());
        CriteriaBuilder builder = getSession().getCriteriaBuilder();

        CriteriaDelete<T> criteria = builder.createCriteriaDelete(clazz);
        Root<T> root = criteria.from(clazz);

        criteria.where(builder.equal(root.get("id"), entity.getId()));

        Query<T> query = getSession().createQuery(criteria);
        query.executeUpdate();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
