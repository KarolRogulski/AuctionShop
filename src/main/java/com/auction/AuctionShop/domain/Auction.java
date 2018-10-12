package com.auction.AuctionShop.domain;

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
@Table(name = "auction")
public class Auction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auction_id")
	private long id;

	@Column(name = "auction_title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "starting_price")
	private float startingPrice;

	@Column(name = "auction_beginning")
	private LocalDateTime auctionBeginning;

	@Column(name = "auction_end")
	private LocalDateTime auctionEnd;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "auction", orphanRemoval = true)
	private List<Comment> comments;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "auction", orphanRemoval = true)
	private List<Offer> offers;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	// Constructor for JPA
	protected Auction() {
	}

	public Auction(String title, String description, float startingPrice, LocalDateTime auctionBeginning,
			LocalDateTime auctionEnd, User user) {
		this.title = title;
		this.description = description;
		this.startingPrice = startingPrice;
		this.auctionBeginning = auctionBeginning;
		this.auctionEnd = auctionEnd;
		this.user = user;
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
