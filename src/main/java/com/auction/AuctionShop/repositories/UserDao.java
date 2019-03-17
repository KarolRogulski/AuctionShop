package com.auction.AuctionShop.repositories;

import java.util.List;

import com.auction.AuctionShop.domain.User;

public interface UserDao extends Repository<User> {

	User findByLogin(String login);
}
