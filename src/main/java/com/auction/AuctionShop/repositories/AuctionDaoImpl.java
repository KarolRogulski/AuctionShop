package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.domain.Auction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class AuctionDaoImpl extends AbstractRepository<Auction> implements AuctionDao {

    private SessionFactory sessionFactory;
    private static final Logger log = LogManager.getLogger(AuctionDaoImpl.class);

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
            log.info("Update " + getClazz() +" with id= " + auctionUpdated.getId());
            Auction auctionFromDB = this.findById(auctionUpdated.getId());
            auctionFromDB.setAuctionEnd(auctionUpdated.getAuctionEnd());
            auctionFromDB.setTitle(auctionUpdated.getTitle());
            auctionFromDB.setDescription(auctionUpdated.getDescription());
    }

    @Override
    public List<Auction> findByOwnerId(long id){
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Auction> criteria = builder.createCriteriaUpdate(getClass());

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Opinion> criteria = builder.createQuery(Opinion.class);
        Root<Opinion> root = criteria.from(Opinion.class);

        Join<Opinion, User> userJoin = root.join("user", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(userJoin.get("id"), id));

        criteria.where(
                builder.and(predicates.toArray(new Predicate[predicates.size()]))
        );
        List<Opinion> opinionsList = getSession().createQuery(criteria)
                .getResultList();
        log.info("Returned " + opinionsList.size() + "opinions where user Id: " + id);
        return opinionsList;
    }
    }
}
