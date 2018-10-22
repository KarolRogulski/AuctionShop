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
@Table(name = "opinion")
public class Opinion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "opinion_id")
	private long id;

	@Column(name = "rate")
	private float rate;

	@Column(name = "opinion_title")
	private String title;

	@Column(name = "opinion_description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="opinion_owner_id")
	private User opinionOwner;

	// Constructor for JPA
	protected Opinion() {
	}

	public Opinion(float rate, String title, String description, User user) {
		this.rate = rate;
		this.title = title;
		this.description = description;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
