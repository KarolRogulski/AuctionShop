package com.auction.AuctionShop.repositories;

import java.util.List;
import java.util.Set;

import com.auction.AuctionShop.domain.Offer;
import com.auction.AuctionShop.domain.User;

public interface OfferDao {

	public Offer findById(long id);
	
	public List<Offer> findByAuctionId(long id);
	
	public Set<Offer> findByOfferUserId(User user);
	
	public void save(User user);
	
	public void update(User user);
	
	public void delete(User user);
}
