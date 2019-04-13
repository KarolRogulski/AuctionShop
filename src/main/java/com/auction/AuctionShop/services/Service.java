package com.auction.AuctionShop.services;

import com.auction.AuctionShop.entities.AbstractEntity;

import java.util.List;

public interface Service<T extends AbstractEntity> {

    void save(T entity);

    List<T> getAll();

    T findById(long id);

    void update(T entity);

    int delete(T entity);
}
