package com.auction.AuctionShop.repositories;

import java.util.Set;

import com.auction.AuctionShop.domain.Auction;
import com.auction.AuctionShop.domain.User;

public interface AuctionDao {

	public Auction findById(long id);
	
	public Set<Auction> findByOwnerId(long id);
	
	public void save(User user);
	
	public void update(User user);
	
	public void delete(User user);
}
