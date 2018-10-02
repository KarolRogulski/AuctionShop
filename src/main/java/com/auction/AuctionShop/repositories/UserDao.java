package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.domain.User;

public interface UserDao {

	public User get(long id);

	public void save(User user);
}
