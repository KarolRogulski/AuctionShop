package com.auction.AuctionShop.repositories;

import org.hibernate.query.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auction.AuctionShop.domain.Opinion;

@Repository
@Transactional
public class OpinionDaoImpl implements OpinionDao {

	private SessionFactory sessionFactory;

	@Autowired
	public OpinionDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void save(Opinion opinion) {
		getSession().save(opinion);
	}
	
	@Override
	public Opinion findByTitle(String title) {
		Query query = getSession().createQuery(
					"from Opinion " +
					"where title = :title");
		query.setParameter("title", title);
		Opinion opinion = (Opinion) query.uniqueResult();
		return opinion;
	}
}
