package com.auction.AuctionShop.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.auction.AuctionShop.domain.User;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
}
