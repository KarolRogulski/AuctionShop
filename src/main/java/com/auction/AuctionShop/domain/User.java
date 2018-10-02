package com.auction.AuctionShop.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Email
	@Column(name="email")
	private String email;
	
	@NotNull
	@Size(min = 4, max = 20)
	@Column(name="login")
	private String login;
	
	@NotNull
	@Size(min = 4, max = 20)
	@Column(name="password")
	private String password;
	
	@Past
	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;
	
	protected User() {}
	
	public User (String email, String login, String password, LocalDate dateOfBirth) {
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

}
