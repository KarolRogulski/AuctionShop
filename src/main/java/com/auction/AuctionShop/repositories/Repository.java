package com.auction.AuctionShop.repositories;

import com.auction.AuctionShop.domain.AbstractEntity;

import java.util.List;

public interface Repository<T extends AbstractEntity> {

    void save(T entity);

    List<T> getAll();

    T findById(long id);

    void update(T entity);

    int delete(T entity);

}