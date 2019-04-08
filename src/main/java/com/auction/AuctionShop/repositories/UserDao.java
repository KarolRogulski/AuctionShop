package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.entities.User;

public interface UserDao extends Repository<User> {

	User findByLogin(String login);
}
