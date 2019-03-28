package com.auction.AuctionShop.repositories;

import java.util.List;

import com.auction.AuctionShop.domain.Comment;

public interface CommentDao {

	List<Comment> findByAuctionId(long id);
	
	void update(Comment comment);
	
}
