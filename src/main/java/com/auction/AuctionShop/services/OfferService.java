package com.auction.AuctionShop.services;

import com.auction.AuctionShop.entities.Offer;

import java.util.List;

public interface OfferService extends Service<Offer> {

    List<Offer> findByAuctionId(long id);

    List<Offer> findByOfferOwnerId(long id);
}
