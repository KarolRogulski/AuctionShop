package com.auction.AuctionShop.dao;

import com.auction.AuctionShop.configuration.DataBaseConfiguration;
import com.auction.AuctionShop.domain.Auction;
import com.auction.AuctionShop.repositories.AuctionDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfiguration.class)
@Sql({"classpath:user-test-data.sql", "classpath:auction-test-data.sql"})
public class AuctionDaoTest {

    @Autowired
    private AuctionDao auctionDao;
    long nonExistingId = 5312431L;

    @Test
    public void findById() {
        long idBeforeTest = 1L;
        long idAfterTest = auctionDao.findById(1L).getId();

        assertEquals(idBeforeTest, idAfterTest);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findByNonExistingId() {
        auctionDao.findById(nonExistingId);
    }

    @Test
    public void findAll() {
        List<Auction> auctionList = auctionDao.getAll();

        assertEquals(2, auctionList.size());
    }

    @Test
    public void update() {
        Auction auction = auctionDao.findById(2L);
        Auction updatedAuction = new Auction("updatedTitle2", "someDescription2", 400.0F,
                LocalDateTime.now(), LocalDateTime.now().plusDays(4));
        updatedAuction.setId(auction.getId());
        String titleBeforeUpdate = auction.getTitle();

        auctionDao.update(updatedAuction);
        String titleAfterUpdate = auctionDao.findById(auction.getId()).getTitle();

        assertNotEquals(titleAfterUpdate, titleBeforeUpdate);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void updateWithNonExistingAuction() {
        Auction auction = new Auction("updatedTitle2", "someDescription2", 400.0F,
                LocalDateTime.now(), LocalDateTime.now().plusDays(4));
        auction.setId(nonExistingId);
        
        auctionDao.update(auction);
    }

    @Test
    public void findByOwnerId() {
        long userId = 1L;

        List<Auction> list = auctionDao.findByOwnerId(userId);

        assertEquals(1, list.size());
    }

    @Test
    public void findByNonExistingOwnerId() {
        List<Auction> list = auctionDao.findByOwnerId(nonExistingId);

        assertTrue(list.isEmpty());
    }

    @Test
    public void delete() {
        Auction auction = auctionDao.findById(1L);

        int result = auctionDao.delete(auction);
        assertEquals(1, result);
    }

    @Test
    public void deleteNonExistingAuction(){
        Auction auction = new Auction("updatedTitle2", "someDescription2", 400.0F,
                LocalDateTime.now(), LocalDateTime.now().plusDays(4));
        auction.setId(nonExistingId);

        int result = auctionDao.delete(auction);

        assertEquals(0, result);
    }
}
