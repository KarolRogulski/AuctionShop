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

	@Override
	public void save(User user) {
		log.info("Saving user with id: " + user.getId() + " and login: " + user.getLogin());
		getSession().save(user);
	}

	
	//Return user by given login
	@Override
	public User findByLogin(String login) {
		log.info("Get user with login: " + login);
		Query query = getSession().createQuery("from User " + "where login = :login");
		query.setParameter("login", login);
		User user = (User) query.uniqueResult();
		log.info("Return user with login: " + user.getLogin() + "and id: " + user.getId());
		return user;
	}
	
	//Return all users
	@Override
	public List<User> getAll() {
		log.info("Get all users");
		Query query = getSession().createQuery("from User ");
		List<User> userList = query.list();
		return userList;
	}

	//Return user by given id
	@Override
	public User findById(long id) {
		log.info("Get user with id=" + id);
		Query query = getSession().createQuery("from User " + "where id = :id");
		query.setParameter("id", id);
		User user = (User) query.uniqueResult();
		log.info("Return user with login: " + user.getLogin() + "and id: " + user.getId());
		return user;
	}
	
	@Override
	public void update(User user) {
		log.info("Update user with id= " + user.getId() + ", set email= " + user.getEmail() 
			+ ", login = " + user.getLogin() + ", dateOfBirth= " + user.getDateOfBirth());
		Query query = getSession().createQuery("update User "
				+ " set email= :email,"
				+ " set login= :login, "
				+ " set password= :password, "
				+ " set date_of_birth= : dateOfBirth where id= :id");
		query.setParameter("email", user.getEmail());
		query.setParameter("login", user.getLogin());
		query.setParameter("password", user.getPassword());
		query.setParameter("dateOfBirth", user.getDateOfBirth());
		query.setParameter("id", user.getId());
		int result = query.executeUpdate();
		log.info("Update result: " + result);
	}

	@Override
	public void delete(User user) {
		log.info("Delete user with id= " + user.getId());
		Query query = getSession().createQuery("delete from User where id= :id");
		query.setParameter("id", user.getId());
		int result = query.executeUpdate();
		log.info("Delete result: " + result);
	}
}
