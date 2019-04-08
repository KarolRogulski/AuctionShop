package com.auction.AuctionShop.entities;

import javax.persistence.*;

@Entity
@Table(name = "OPINION")
public class Opinion implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPINION_ID")
    private long id;

    @Column(name = "RATE")
    private float rate;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    private User opinionAuthor;


    // Constructor for JPA
    protected Opinion() {
    }

    public Opinion(float rate, String title, String description) {
        this.rate = rate;
        this.title = title;
        this.description = description;
    }

    @Override
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
