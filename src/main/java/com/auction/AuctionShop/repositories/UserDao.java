package com.auction.AuctionShop.repositories;

import java.util.List;

import com.auction.AuctionShop.domain.User;

public interface UserDao {

	public User findById(long id);

	public List<User> getAll();
	
	public List<User> findByLogin(String login);
	
	public void save(User user);
}
