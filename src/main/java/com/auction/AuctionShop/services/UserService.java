package com.auction.AuctionShop.services;

import com.auction.AuctionShop.entities.User;

public interface UserService {
	
	public User findById(long id);
	
	public void save(User user);
}
