package com.auction.AuctionShop.repositories;

import java.util.List;

import com.auction.AuctionShop.domain.Comment;

public interface CommentDao {

	public Comment findById(long id);
	
	public List<Comment> findByAuctionId(long id);
	
	public void save(Comment comment);
	
	public void update(Comment comment);
	
	public void delete(Comment comment);
}
