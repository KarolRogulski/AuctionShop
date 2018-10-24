package com.auction.AuctionShop.repositories;

import org.hibernate.query.Query;

import java.util.Set;

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
	public Opinion findById(long id) {
		Query query = getSession().createQuery(
					"from Opinion " +
					"where id= :id");
		query.setParameter("id", id);
		Opinion opinion = (Opinion) query.uniqueResult();
		return opinion;
	}

	@Override
	public Opinion findByOwnerId(long id) {
		return null;
	}

	@Override
	public Set<Opinion> findByUserId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Opinion opinion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Opinion opinion) {
		// TODO Auto-generated method stub
		
	}
}
