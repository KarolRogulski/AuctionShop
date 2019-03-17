package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.domain.Opinion;
import com.auction.AuctionShop.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class OpinionDaoImpl extends AbstractRepository<Opinion> implements OpinionDao {

    private SessionFactory sessionFactory;
    private static final Logger log = LogManager.getLogger(OpinionDaoImpl.class);

    @Autowired
    public OpinionDaoImpl(SessionFactory sessionFactory) {
        super.setClazz(Opinion.class);
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    //Return all opinions written by user with given id
    @Override
    public List<Opinion> findByAuthorId(long id) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Opinion> criteria = builder.createQuery(Opinion.class);
        Root<Opinion> root = criteria.from(Opinion.class);

        Join<Opinion, User> userJoin = root.join("opinionAuthor", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(userJoin.get("id"), id));

        criteria.where(
                builder.and(predicates.toArray(new Predicate[predicates.size()]))
        );
        List<Opinion> opinionsList = getSession().createQuery(criteria)
                .getResultList();
        log.info("Returned " + opinionsList.size() + "opinions where authorId: " + id);
        return opinionsList;
    }

    //Return all opinions for user with given id
    @Override
    public List<Opinion> findByUserId(long id) {
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

    @Override
    public void update(Opinion updatedOpinion) {
        log.info("Updating opinion with id: " + updatedOpinion.getId());
        Opinion opinionFromDB = this.findById(updatedOpinion.getId());
        opinionFromDB.setRate(updatedOpinion.getRate());
        opinionFromDB.setTitle(updatedOpinion.getTitle());
        opinionFromDB.setDescription(updatedOpinion.getDescription());
    }
}
