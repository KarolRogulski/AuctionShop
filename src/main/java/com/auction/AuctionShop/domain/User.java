package com.auction.AuctionShop.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Email
	private String email;
	
	@NotNull
	@Size(min = 4, max = 20)
	private String login;
	
	@NotNull
	@Size(min = 4, max = 20)
	private String password;
	
	@Past
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@OneToMany
	private Set<Auction> userAuctions;

	// Empty constructor for Hibernate
	protected User () {}
	
	public User (String email, String login, String password, Date dateOfBirth) {
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Set<Auction> getUserAuctions() {
		return userAuctions;
	}

	public void setUserAuctions(Set<Auction> userAuctions) {
		this.userAuctions = userAuctions;
	}
}
