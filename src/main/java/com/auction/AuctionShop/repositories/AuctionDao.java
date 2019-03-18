package com.auction.AuctionShop.repositories;

import java.util.List;
import java.util.Set;

import com.auction.AuctionShop.domain.Auction;
import com.auction.AuctionShop.domain.User;

public interface AuctionDao extends Repository<Auction>{

	List<Auction> findByOwnerId(long id);


}

