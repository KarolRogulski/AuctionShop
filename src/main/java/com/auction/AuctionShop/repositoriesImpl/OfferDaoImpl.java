package com.auction.AuctionShop.repositoriesImpl;

import com.auction.AuctionShop.entities.Auction;
import com.auction.AuctionShop.entities.Offer;
import com.auction.AuctionShop.entities.User;
import com.auction.AuctionShop.repositories.OfferDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OfferDaoImpl extends AbstractRepository<Offer> implements OfferDao {

    private SessionFactory sessionFactory;
    public static final Logger log = LogManager.getLogger(OfferDaoImpl.class);

    @Autowired
    public OfferDaoImpl(SessionFactory sessionFactory){
        super.setClazz(Offer.class);
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(){
       return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void update(Offer updated){
        log.debug("Update offer with id:" + updated.getId());
        Offer offerFromDB = this.findById(updated.getId());
        offerFromDB.setAmountOfMoney(updated.getAmountOfMoney());
    }

    //Return lis of offers by given auction id
    @Override
    public List<Offer> findByAuctionId(long id){
        log.debug("Get offers list with auction id: " + id);
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Offer> criteria = builder.createQuery(getClazz());
        Root<Offer> root = criteria.from(getClazz());

        Join<Offer, Auction> join = root.join("auction", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(join.get("id"), id));
        criteria.where(builder.and(predicates.toArray(new Predicate[0])));

        List<Offer> list = getSession().createQuery(criteria).getResultList();
        log.debug("Returned " + list.size() + " offers where auction's id " + id);
        return list;
    }

    //Return list of offers by given offer owner id
    @Override
    public List<Offer> findByOfferOwnerId(long id){
        log.debug("Get offers by user id: " + id);
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Offer> criteria = builder.createQuery(getClazz());
        Root<Offer> root = criteria.from(getClazz());

        Join<Offer, User> join = root.join("offerOwner", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(join.get("id"), id));
        criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

        List<Offer> list = getSession().createQuery(criteria).getResultList();
        log.debug("Returned " + list.size() + " offers where user's id " + id);
        return list;
    }
}
