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

@Entity
@Table(name = "OFFER")
public class Offer implements AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OFFER_ID")
	private long id;

	@Column(name = "AMOUNT_OF_MONEY")
	private float ammountOfMoney;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUCTION_ID")
	private Auction auction;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User offerOwner;

	// Constructor for JPA
	protected Offer() {
	}

	public Offer(float ammountOfMoney) {
		this.ammountOfMoney = ammountOfMoney;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getAmmountOfMoney() {
		return ammountOfMoney;
	}

	public void setAmmountOfMoney(float ammountOfMoney) {
		this.ammountOfMoney = ammountOfMoney;
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

}
