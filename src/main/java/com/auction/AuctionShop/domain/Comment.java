package com.auction.AuctionShop.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private long id;

	@Column(name = "comment_title")
	private String title;

	@Column(name = "comment_content")
	private String content;

	@Column(name = "commment_add_date")
	private LocalDateTime added;

	@ManyToOne
	private User owner;

	@ManyToOne
	private Auction auction;

	// Constructor for JPA
	protected Comment() {
	}

	public Comment(String title, String content, LocalDateTime added, User owner, Auction auction) {
		this.title = title;
		this.content = content;
		this.added = added;
		this.owner = owner;
		this.auction = auction;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getAdded() {
		return added;
	}

	public void setAdded(LocalDateTime added) {
		this.added = added;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

}
