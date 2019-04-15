package com.auction.AuctionShop.servicesImpl;

import com.auction.AuctionShop.entities.User;
import com.auction.AuctionShop.exceptions.NotFoundException;
import com.auction.AuctionShop.repositories.UserDao;
import com.auction.AuctionShop.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {

	@Autowired
	private UserDao userDao;
	private static final Logger log = LogManager.getLogger(UserService.class);

	@Override
	public User findByLogin(String login) throws NotFoundException {
		log.info("Get user with login: " + login);
		try{
			return userDao.findByLogin(login);
		}
		catch(EmptyResultDataAccessException e){
			throw new NotFoundException("User with login" + login + " not found");
		}
	}
}
