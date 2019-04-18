package com.auction.AuctionShop.services;

import com.auction.AuctionShop.entities.Comment;

import java.util.List;

public interface CommentService extends Service<Comment>{

    List<Comment> findByAuctionId(long id);
}
