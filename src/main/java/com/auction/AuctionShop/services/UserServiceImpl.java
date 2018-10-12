package com.auction.AuctionShop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auction.AuctionShop.domain.User;
import com.auction.AuctionShop.exceptions.UserNotFoundException;
import com.auction.AuctionShop.repositories.UserDao;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public User findById(long id) throws UserNotFoundException {
		User user = userDao.findById(id);
		if (user != null) {
			return user;
		} else {
			throw new UserNotFoundException("User with ID:" + id + " not found");
		}
	}
}
