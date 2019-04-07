package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.domain.Auction;
import com.auction.AuctionShop.domain.Comment;
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
public class CommentDaoImpl extends AbstractRepository<Comment> implements CommentDao {

    private SessionFactory sessionFactory;
    private static final Logger log = LogManager.getLogger(CommentDaoImpl.class);

    @Autowired
    public CommentDaoImpl(SessionFactory sessionFactory){
        super.setClazz(Comment.class);
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(){ return sessionFactory.getCurrentSession();}

    @Override
    public List<Comment> findByAuctionId(long id) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Comment> criteria = builder.createQuery(getClazz());
        Root<Comment> root = criteria.from(getClazz());

        Join<Comment, Auction> join = root.join("auction", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.equal(join.get("id"), id));
        criteria.where(
                builder.and(predicates.toArray(new Predicate[predicates.size()]))
        );

        List<Comment> list = getSession().createQuery(criteria).getResultList();
        log.info("Returned " + list.size() + " comments where auction's id " + id);
        return list;
    }

    @Override
    public void update(Comment updated) {
        log.info("Updating " + getClazz() + "with id " + updated.getId());
        Comment commentFromDB = this.findById(updated.getId());
        commentFromDB.setTitle(updated.getTitle());
        commentFromDB.setContent(updated.getContent());
        commentFromDB.setAdded(updated.getAdded());
    }
}
