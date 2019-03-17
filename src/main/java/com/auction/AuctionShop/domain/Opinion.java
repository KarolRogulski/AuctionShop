package com.auction.AuctionShop.domain;

import javax.persistence.*;

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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name ="user_id")
	private User user;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name ="author_id")
	private User opinionAuthor;


	// Constructor for JPA
	protected Opinion() {
	}

	public Opinion(float rate, String title, String description, User user, User opinionAuthor) {
		this.rate = rate;
		this.title = title;
		this.description = description;
		this.user = user;
		this.opinionAuthor = opinionAuthor;
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

	public User getOpinionAuthor() {
		return opinionAuthor;
	}

	public void setOpinionAuthor(User opinionAuthor) {
		this.opinionAuthor = opinionAuthor;
	}
}
