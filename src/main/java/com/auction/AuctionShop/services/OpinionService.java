package com.auction.AuctionShop.services;

import com.auction.AuctionShop.entities.Opinion;

import java.util.List;

public interface OpinionService extends Service<Opinion> {

    List<Opinion> findByAuthorId(long id);

    List<Opinion> findByUserId(long id);
}
