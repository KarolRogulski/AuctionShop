package com.auction.AuctionShop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "OFFER")
public class Offer implements AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OFFER_ID")
	private long id;

	@Column(name = "AMOUNT_OF_MONEY")
	private float amountOfMoney;

	@Column(name = "ADDED_DATE")
	private LocalDateTime added;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUCTION_ID")
	private Auction auction;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User offerOwner;

	// Constructor for JPA
	protected Offer() {
	}

	public Offer(float ammountOfMoney, LocalDateTime added) {
		this.added = added;
		this.amountOfMoney = ammountOfMoney;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getAmountOfMoney() {
		return amountOfMoney;
	}

	public void setAmountOfMoney(float amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public User getOfferOwner() {
		return offerOwner;
	}

	public void setOfferOwner(User offerOwner) {
		this.offerOwner = offerOwner;
	}

	public LocalDateTime getAdded() {
		return added;
	}

	public void setAdded(LocalDateTime added) {
		this.added = added;
	}
}
