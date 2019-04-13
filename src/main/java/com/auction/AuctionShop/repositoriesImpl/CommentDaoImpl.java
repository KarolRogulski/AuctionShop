package com.auction.AuctionShop.repositoriesImpl;

import com.auction.AuctionShop.entities.Auction;
import com.auction.AuctionShop.entities.Comment;
import com.auction.AuctionShop.repositories.CommentDao;
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
    public static final Logger log = LogManager.getLogger(CommentDaoImpl.class);

    @Autowired
    public CommentDaoImpl(SessionFactory sessionFactory){
        super.setClazz(Comment.class);
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(){ return sessionFactory.getCurrentSession();}

    //Returning comments list based on auction id
    @Override
    public List<Comment> findByAuctionId(long id) {
        log.debug("Get list of comments by id of auction: " + id);
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
        log.debug("Returned " + list.size() + " comments where auction's id " + id);
        return list;
    }

    @Override
    public void update(Comment updated) {
        log.debug("Updating " + getClazz() + "with id " + updated.getId());
        Comment commentFromDB = this.findById(updated.getId());
        commentFromDB.setTitle(updated.getTitle());
        commentFromDB.setContent(updated.getContent());
        commentFromDB.setAdded(updated.getAdded());
    }
}
