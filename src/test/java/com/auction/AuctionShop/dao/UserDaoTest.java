package com.auction.AuctionShop.dao;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.auction.AuctionShop.configuration.DataBaseConfiguration;
import com.auction.AuctionShop.domain.User;
import com.auction.AuctionShop.repositories.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfiguration.class)
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Test
	@Transactional
	@Rollback(true)
	public void findUserByIdTest() {
		User user = new User("someEmail", "someLogin", "somePassword", LocalDate.now());
		userDao.save(user);
		
		long idFromUserDao = userDao.findById(user.getId()).getId();
		long idFromTestUser1 = user.getId();
		
		assertEquals(idFromUserDao, idFromTestUser1);
	}

	@Test
	@Transactional
	@Rollback(true)
	public void findByLogin() {
		User user = new User("someEmail", "someLogin", "somePassword", LocalDate.now());
		userDao.save(user);
		
		User userFromDao = userDao.findByLogin(user.getLogin());
		String loginFromUserDao = userFromDao.getLogin();
		String loginFromTestUser1 = user.getLogin();
		
		assertEquals(loginFromUserDao, loginFromTestUser1);
	}
}
