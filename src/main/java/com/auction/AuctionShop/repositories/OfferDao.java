package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.entities.Offer;

import java.util.List;

public interface OfferDao extends Repository<Offer>{

	List<Offer> findByAuctionId(long id);

	List<Offer> findByOfferOwnerId(long id);
}
