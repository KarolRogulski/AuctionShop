package com.auction.AuctionShop.dao;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.auction.AuctionShop.configuration.DataBaseConfiguration;
import com.auction.AuctionShop.domain.Opinion;
import com.auction.AuctionShop.domain.User;
import com.auction.AuctionShop.repositories.OpinionDao;
import com.auction.AuctionShop.repositories.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = DataBaseConfiguration.class)
public class OpinionDaoTest {

	@Autowired
	private OpinionDao opinionDao;
	
	@Autowired
	private UserDao userDao;
	private Opinion opinion1;
	private User user1;
	
	@Before
	public void setup() {
		user1 = new User("someEmail1", "someLogin1", "somePassword1", LocalDate.now());
		opinion1 = new Opinion(1, "someTitle1", "someDescription1", user1);
		userDao.save(user1);
		opinionDao.save(opinion1);
	}
	
}
