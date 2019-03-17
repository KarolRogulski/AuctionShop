package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.domain.Opinion;
import com.auction.AuctionShop.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class OpinionDaoImpl implements OpinionDao {

	private SessionFactory sessionFactory;
    private static final Logger log = LogManager.getLogger(OpinionDaoImpl.class);

	@Autowired
	public OpinionDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Opinion opinion) {
	    log.info("Saving opinion with id: " + opinion.getId());
		getSession().persist(opinion);
	}

	// Return opinion by given id
	@Override
	public Opinion findById(long id) {
	    log.info("Get opinion with id: " + id);
		Query query = getSession().createQuery(
					"from Opinion " +
					"where id= :id");
		query.setParameter("id", id);
		Opinion opinion = (Opinion) query.uniqueResult();
		return opinion;
	}

	//Return all opinions written by user with given id
	@Override
	public List<Opinion> findByAuthorId(long id) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Opinion> criteria = builder.createQuery(Opinion.class);
		Root<Opinion> root = criteria.from(Opinion.class);

		Join<Opinion, User> userJoin = root.join("opinionAuthor", JoinType.INNER);
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.equal( userJoin.get( "id" ), id));

		criteria.where(
				builder.and(predicates.toArray(new Predicate[predicates.size()]))
		);
		List<Opinion> opinionsList = getSession().createQuery( criteria )
				.getResultList();
		log.info("Returned " + opinionsList.size() + "opinions where authorId: " + id);
		return opinionsList;
	}

	//Return all opinions
	@Override
    public List<Opinion> getAll(){
	    log.info("Get all opinions");
	    Query query = getSession().createQuery("from Opinion");
	    List<Opinion> opinionList = query.list();
	    return opinionList;
    }

    //Return all opinions for user with given id
	@Override
	public List<Opinion> findByUserId(long id) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<Opinion> criteria = builder.createQuery(Opinion.class);
		Root<Opinion> root = criteria.from(Opinion.class);

		Join<Opinion, User> userJoin = root.join("user", JoinType.INNER);
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(builder.equal( userJoin.get( "id" ), id));

		criteria.where(
				builder.and(predicates.toArray(new Predicate[predicates.size()]))
		);
		List<Opinion> opinionsList = getSession().createQuery( criteria )
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

	@Override
	public void delete(Opinion opinion) {
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaDelete<Opinion> criteria = builder.createCriteriaDelete(Opinion.class);
		Root<Opinion> root = criteria.from(Opinion.class);

		criteria.where(builder.equal(root.get("id"), opinion.getId()));

		Query<Opinion> query = getSession().createQuery(criteria);
		query.executeUpdate();
		log.info("Deleted opinion with id: " + opinion.getId());
	}
}
