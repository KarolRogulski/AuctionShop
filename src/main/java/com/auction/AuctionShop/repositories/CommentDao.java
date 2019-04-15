package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.entities.Comment;

import java.util.List;

public interface CommentDao extends Repository<Comment>{

	List<Comment> findByAuctionId(long id);

}
