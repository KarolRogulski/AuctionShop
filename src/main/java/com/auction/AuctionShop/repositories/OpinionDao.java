package com.auction.AuctionShop.repositories;

import java.util.List;
import java.util.Set;

import com.auction.AuctionShop.domain.Opinion;

public interface OpinionDao {

	Opinion findById(long id);
	
	List<Opinion> findByAuthorId(long id);
	
	List<Opinion> findByUserId(long id);

	List<Opinion> getAll();

	void update(Opinion opinion);

	void save(Opinion opinion);
	
	void delete(Opinion opinion);
}
