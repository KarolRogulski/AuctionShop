package com.auction.AuctionShop.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "USER")
public class User implements AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private long id;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "LOGIN")
	private String login;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "DATE_OF_BIRTH")
	private LocalDate dateOfBirth;

	@Column(name = "USER_RATING")
	private float userRating;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private List<Auction> auctions;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "offerOwner", orphanRemoval = true)
	private List<Offer> userOffers;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "opinionAuthor", orphanRemoval = true)
	private List<Opinion> byUserOpinions;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private List<Opinion> aboutUserOpinions;

	// Constructor for JPA
	protected User() {
	}

	public User(String email, String login, String password, LocalDate dateOfBirth) {
		this.email = email;
		this.login = login;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public float getUserRating() {
		return userRating;
	}

	public void setUserRating(float userRating) {
		this.userRating = userRating;
	}

	public List<Auction> getAuctions() {
		return auctions;
	}

	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}

	public List<Offer> getUserOffers() {
		return userOffers;
	}

	public List<Opinion> getAboutUserOpinions() {
		return aboutUserOpinions;
	}

	public void setAboutUserOpinions(List<Opinion> aboutUserOpinions) {
		this.aboutUserOpinions = aboutUserOpinions;
	}

	public void setUserOffers(List<Offer> userOffers) {
		this.userOffers = userOffers;
	}

	public List<Opinion> getByUserOpinions() {
		return byUserOpinions;
	}

	public void setByUserOpinions(List<Opinion> byUserOpinions) {
		this.byUserOpinions = byUserOpinions;
	}
}
