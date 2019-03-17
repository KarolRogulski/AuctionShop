package com.auction.AuctionShop.repositories;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auction.AuctionShop.domain.User;

import javax.jws.soap.SOAPBinding;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

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

	@Override
	public void save(User user) {
		getSession().persist(user);
		log.info("Saving user with id: " + user.getId() + " and login: " + user.getLogin());
	}

	
	//Return user by given login
	@Override
	public User findByLogin(String login) {
		log.info("Get user with login: " + login);
		CriteriaBuilder builder = getSession().getCriteriaBuilder();

		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);

		ParameterExpression<String> loginParameter = builder.parameter(String.class);
		criteria.where(builder.equal(root.get("login"), loginParameter));

		Query<User> query = getSession().createQuery(criteria);
		query.setParameter(loginParameter, login);
		return query.getSingleResult();
	}

	//Return all users
	@Override
	public List<User> getAll() {
		log.info("Get all users");
		CriteriaBuilder builder = getSession().getCriteriaBuilder();

		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root);

		Query<User> query = getSession().createQuery(criteria);
		return query.getResultList();
	}

	//Return user by given id
	@Override
	public User findById(long id) {
		log.info("Get user with id=" + id);
		CriteriaBuilder builder = getSession().getCriteriaBuilder();

		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);

		ParameterExpression<Long> idParameter = builder.parameter(long.class);
		criteria.where(builder.equal(root.get("id"), idParameter));

		Query<User> query = getSession().createQuery(criteria);
		query.setParameter(idParameter, id);
		return query.getSingleResult();
	}
	
	@Override
	public void update(User userUpdated) {
		log.info("Update user with id= " + userUpdated.getId());
		User userFromDB = this.findById(userUpdated.getId());
		userFromDB.setLogin(userUpdated.getLogin());
		userFromDB.setEmail(userUpdated.getEmail());
		userFromDB.setPassword(userUpdated.getPassword());
		userFromDB.setDateOfBirth(userUpdated.getDateOfBirth());
	}

	@Override
	public void delete(User user) {
		log.info("Delete user with id= " + user.getId());
		CriteriaBuilder builder = getSession().getCriteriaBuilder();

		CriteriaDelete<User> criteria = builder.createCriteriaDelete(User.class);
		Root<User> root = criteria.from(User.class);

		criteria.where(builder.equal(root.get("id"), user.getId()));

		Query<User> query = getSession().createQuery(criteria);
		query.executeUpdate();
	}
}
