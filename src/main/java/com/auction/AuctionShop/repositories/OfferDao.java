package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.domain.Offer;
import com.auction.AuctionShop.domain.User;

import java.util.List;
import java.util.Set;

public interface OfferDao extends Repository<Offer>{

	List<Offer> findByAuctionId(long id);
	
	List<Offer> findByOfferOwnerId(long id);

	void update(Offer offer);
}
