package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.entities.Opinion;

import java.util.List;

public interface OpinionDao extends Repository<Opinion> {

	List<Opinion> findByAuthorId(long id);
	
	List<Opinion> findByUserId(long id);

}
