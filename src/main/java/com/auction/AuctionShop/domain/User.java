package com.auction.AuctionShop.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;

	@Column(name = "email")
	private String email;

	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String password;

	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;

	@Column(name = "user_rating")
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
