package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.entities.Auction;

import java.util.List;

public interface AuctionDao extends Repository<Auction>{

	List<Auction> findByOwnerId(long id);
}

