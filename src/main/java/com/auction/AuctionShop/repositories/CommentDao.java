package com.auction.AuctionShop.repositories;

import java.util.List;

import com.auction.AuctionShop.entities.Comment;

public interface CommentDao extends Repository<Comment>{

	List<Comment> findByAuctionId(long id);
	
	void update(Comment comment);
	
}
