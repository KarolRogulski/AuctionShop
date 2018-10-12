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
@Table(name = "offer")
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "offer_id")
	private long id;

	@Column(name = "ammount_of_money")
	private float ammountOfMoney;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auction_id")
	private Auction auction;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User offerOwner;

	// Constructor for JPA
	protected Offer() {
	}

	public Offer(float ammountOfMoney, Auction auction, User offerOwner) {
		this.ammountOfMoney = ammountOfMoney;
		this.auction = auction;
		this.offerOwner = offerOwner;
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
