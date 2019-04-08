package com.auction.AuctionShop.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUCTION")
public class Auction implements AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUCTION_ID")
	private long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PRICE")
	private float startingPrice;

	@Column(name = "AUCTION_BEGINNING")
	private LocalDateTime auctionBeginning;

	@Column(name = "AUCTION_END")
	private LocalDateTime auctionEnd;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "auction", orphanRemoval = true)
	private List<Comment> comments;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "auction", orphanRemoval = true)
	private List<Offer> offers;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	// Constructor for JPA
	protected Auction() {
	}

	public Auction(String title, String description, float startingPrice, LocalDateTime auctionBeginning,
			LocalDateTime auctionEnd) {
		this.title = title;
		this.description = description;
		this.startingPrice = startingPrice;
		this.auctionBeginning = auctionBeginning;
		this.auctionEnd = auctionEnd;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(float startingPrice) {
		this.startingPrice = startingPrice;
	}

	public LocalDateTime getAuctionBeginning() {
		return auctionBeginning;
	}

	public void setAuctionBeginning(LocalDateTime auctionBeginning) {
		this.auctionBeginning = auctionBeginning;
	}

	public LocalDateTime getAuctionEnd() {
		return auctionEnd;
	}

	public void setAuctionEnd(LocalDateTime auctionEnd) {
		this.auctionEnd = auctionEnd;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
