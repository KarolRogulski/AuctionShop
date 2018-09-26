package com.auction.AuctionShop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auction.AuctionShop.domain.User;
import com.auction.AuctionShop.repositories.UserDao;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	public void save(User user) {
		System.out.println("User saved");
		userDao.save(user);
	}
}
