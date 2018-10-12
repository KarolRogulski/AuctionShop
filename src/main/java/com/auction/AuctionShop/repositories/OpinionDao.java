package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.domain.Opinion;

public interface OpinionDao {

	public void save(Opinion opinion);
	
	public Opinion findByTitle(String title);
}
