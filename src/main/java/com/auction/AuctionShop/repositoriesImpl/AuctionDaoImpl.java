package com.auction.AuctionShop.repositoriesImpl;

import com.auction.AuctionShop.entities.Auction;
import com.auction.AuctionShop.entities.User;
import com.auction.AuctionShop.repositories.AuctionDao;
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
public class AuctionDaoImpl extends AbstractRepository<Auction> implements AuctionDao {

    private SessionFactory sessionFactory;
    public static final Logger log = LogManager.getLogger(AuctionDaoImpl.class);

    @Autowired
    public AuctionDaoImpl(SessionFactory sessionFactory){
        super.setClazz(Auction.class);
        this.sessionFactory = sessionFactory;
    }

    public Session getSession(){
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void update(Auction auctionUpdated){
            log.debug("Update " + getClazz() +" with id " + auctionUpdated.getId());
            Auction auctionFromDB = this.findById(auctionUpdated.getId());
            auctionFromDB.setAuctionEnd(auctionUpdated.getAuctionEnd());
            auctionFromDB.setTitle(auctionUpdated.getTitle());
            auctionFromDB.setDescription(auctionUpdated.getDescription());
    }

    @Override
    public List<Auction> findByOwnerId(long id){
        log.debug("Get list of " + getClazz() + " with owner id: " + id);
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Auction> criteria = builder.createQuery(getClazz());
        Root<Auction> root = criteria.from(getClazz());

        Join<Auction, User> auctionJoin = root.join("user", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(auctionJoin.get("id"), id));
        criteria.where(
                builder.and(predicates.toArray(new Predicate[predicates.size()]))
        );

        List<Auction> auctionsList = getSession().createQuery(criteria).getResultList();
        log.debug("Returned " + auctionsList.size() + "auctions where owner Id: " + id);
        return auctionsList;
    }
}
