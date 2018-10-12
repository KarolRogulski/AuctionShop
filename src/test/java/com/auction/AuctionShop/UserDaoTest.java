package com.auction.AuctionShop;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.auction.AuctionShop.configuration.DataBaseConfiguration;
import com.auction.AuctionShop.domain.User;
import com.auction.AuctionShop.repositories.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfiguration.class)
public class UserDaoTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Autowired
	private UserDao userDao;

	private User user1;
	private User user2;
	private User user3;

	@Before
	public void setup() {
		user1 = new User("someEmail1", "someLogin1", "somePassword1", LocalDate.now());
		user2 = new User("someEmail2", "someLogin2", "somePassword2", LocalDate.now());
		user3 = new User("someEmail3", "someLogin3", "somePassword3", LocalDate.now());
		userDao.save(user1);
		userDao.save(user2);
		userDao.save(user3);
	}

	@Test
	public void findUserByIdTest() {
		long idFromUserDao = userDao.findById(user1.getId()).getId();
		long idFromTestUser1 = user1.getId();
		assertEquals(idFromUserDao, idFromTestUser1);
	}

	@Test
	public void findByLogin() {
		List<User> userFromDao = userDao.findByLogin(user1.getLogin());
		for (User u : userFromDao) {
			System.out.print(u.getLogin());
		}
		;
		// String loginFromUserDao = userFromDao.getLogin();
		// String loginFromTestUser1 = user1.getLogin();
	}
}
