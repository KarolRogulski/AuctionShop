package com.auction.AuctionShop.repositories;

import java.util.List;

import com.auction.AuctionShop.entities.Auction;

public interface AuctionDao extends Repository<Auction>{

	List<Auction> findByOwnerId(long id);


}

