package com.auction.AuctionShop.repositories;

import java.util.List;

import com.auction.AuctionShop.entities.Opinion;

public interface OpinionDao extends Repository<Opinion> {

	List<Opinion> findByAuthorId(long id);
	
	List<Opinion> findByUserId(long id);

}
