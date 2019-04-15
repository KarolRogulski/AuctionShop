package com.auction.AuctionShop.services;

import com.auction.AuctionShop.entities.User;

public interface UserService extends Service<User>{

	User findByLogin(String login);
}
