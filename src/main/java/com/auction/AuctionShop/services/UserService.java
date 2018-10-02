package com.auction.AuctionShop.services;

import com.auction.AuctionShop.domain.User;

public interface UserService {
	
	public User findById(long id);
	
	public void save(User user);
}
