package com.auction.AuctionShop.repositories;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auction.AuctionShop.domain.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	private SessionFactory sessionFactory;
	private static final Logger log = LogManager.getLogger(UserDaoImpl.class);

	@Autowired
	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(User user) {
		getSession().save(user);
	}

	public List<User> findByLogin(String login) {
		Query query = getSession().createQuery("from User " + "where login = :login");
		query.setParameter("login", login);
		log.info("Get user with login: " + login);
		List<User> user = query.list();
		return user;
	}

	@Override
	public List<User> getAll() {
		Query query = getSession().createQuery("from User ");
		List<User> userList = query.list();
		log.info("Get all users");
		return userList;
	}

	@Override
	public User findById(long id) {
		Query query = getSession().createQuery("from User " + "where id = :id");
		query.setParameter("id", id);
		User user = (User) query.uniqueResult();
		return user;
	}
}
