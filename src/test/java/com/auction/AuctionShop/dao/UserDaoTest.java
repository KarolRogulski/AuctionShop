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
		User user1 = new User("someEmail1", "someLogin1", "somePassword1", LocalDate.now());
		userDao.save(user1);
		
		long idFromUserDao = userDao.findById(user1.getId()).getId();
		long idFromTestUser1 = user1.getId();
		
		assertEquals(idFromUserDao, idFromTestUser1);
	}

	@Test
	@Transactional
	public void findByLogin() {
		User user2 = new User("someEmail2", "someLogin2", "somePassword2", LocalDate.now());
		userDao.save(user2);
		
		User userFromDao = userDao.findByLogin("someLogin1");
		String loginFromUserDao = userFromDao.getLogin();
		String loginFromTestUser1 = user2.getLogin();
		
		assertEquals(loginFromUserDao, loginFromTestUser1);
	}
}
