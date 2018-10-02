package com.auction.AuctionShop;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

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
import com.auction.AuctionShop.exceptions.UserNotFoundException;
import com.auction.AuctionShop.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfiguration.class)
public class UserServiceTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Autowired
	private UserService userService;

	private User user1;
	private User user2;

	@Before
	public void setup() {
		user1 = new User("someEmail1", "someLogin1", "somePassword1", LocalDateTime.now());
		user2 = new User("someEmail2", "someLogin2", "somePassword2", LocalDateTime.now());
		userService.save(user1);
		userService.save(user2);
	}

	@Test
	public void findUserByIdTest() {
		assertEquals(userService.findById(user1.getId()).getId(), user1.getId());
	}
	
	@Test(expected=UserNotFoundException.class)
	public void notExistingIdTest() {
		long notExistingId = user1.getId() + user2.getId();
		userService.findById(notExistingId);
	}
	
}
