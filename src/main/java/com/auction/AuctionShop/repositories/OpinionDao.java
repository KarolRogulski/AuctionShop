package com.auction.AuctionShop.repositories;

import java.util.Set;

import com.auction.AuctionShop.domain.Opinion;

public interface OpinionDao {

	public Opinion findById(long id);
	
	public Opinion findByOwnerId(long id);
	
	public Set<Opinion> findByUserId(long id);
	
	public void update(Opinion opinion);
	
	public void save(Opinion opinion);
	
	public void delete(Opinion opinion);
}
