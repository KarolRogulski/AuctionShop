package com.auction.AuctionShop.repositories;

import java.util.List;
import java.util.Set;

import com.auction.AuctionShop.domain.Opinion;

public interface OpinionDao extends Repository<Opinion> {

	List<Opinion> findByAuthorId(long id);
	
	List<Opinion> findByUserId(long id);

}
