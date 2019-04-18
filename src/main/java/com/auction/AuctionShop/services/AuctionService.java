package com.auction.AuctionShop.services;

import com.auction.AuctionShop.entities.Auction;

import java.util.List;

public interface AuctionService extends Service<Auction>{

    List<Auction> findByOwnerId(long id);
}
