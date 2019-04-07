package com.auction.AuctionShop.dao;

import com.auction.AuctionShop.domain.Offer;
import com.auction.AuctionShop.repositories.OfferDao;
import config.DataBaseConfigurationTest;
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

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfigurationTest.class)
@Sql({"classpath:user-test-data.sql", "classpath:auction-test-data.sql", "classpath:offer-test-data.sql"})
public class OfferDaoTest {

    @Autowired
    private OfferDao offerDao;
    private long nonExistingId = 53243L;


    @Test
    public void findById() {
        long idBeforeTest = 1L;
        long idAfterTest = offerDao.findById(1L).getId();

        assertEquals(idBeforeTest, idAfterTest);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findByNonExistingId() {
        offerDao.findById(nonExistingId);
    }

    @Test
    public void findAll() {
        List<Offer> list = offerDao.getAll();

        assertEquals(2, list.size());
    }

    @Test
    public void update() {
        Offer offer = offerDao.findById(2L);
        Offer updatedOffer = new Offer(532.0F, LocalDateTime.now());
        updatedOffer.setId(offer.getId());
        double amountBeforeUpdate = offer.getAmountOfMoney();

        offerDao.update(updatedOffer);
        double amountAfterUpdate = offerDao.findById(offer.getId()).getAmountOfMoney();

        assertNotEquals(amountBeforeUpdate, amountAfterUpdate);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void updateWithNonExistingAuction() {
        Offer offer = new Offer(5134F, LocalDateTime.now());
        offer.setId(nonExistingId);

        offerDao.update(offer);
    }

    @Test
    public void findByOfferOwnerId() {
        long auctionId = 1L;

        List<Offer> list = offerDao.findByOfferOwnerId(auctionId);

        assertEquals(1, list.size());
    }

    @Test
    public void findByNonExistingOfferOwnerId() {
        List<Offer> list = offerDao.findByOfferOwnerId(nonExistingId);

        assertTrue(list.isEmpty());
    }

    @Test
    public void findByAuctionId() {
        long auctionId = 1L;

        List<Offer> list = offerDao.findByAuctionId(auctionId);

        assertEquals(1, list.size());
    }

    @Test
    public void findByNonExistingAuctionId() {
        List<Offer> list = offerDao.findByAuctionId(nonExistingId);

        assertTrue(list.isEmpty());
    }

    @Test
    public void delete() {
        Offer offer = offerDao.findById(1L);

        int result =offerDao.delete(offer);
        assertEquals(1, result);
    }

    @Test
    public void deleteNonExistingAuction() {
        Offer offer= new Offer(5314F,
                LocalDateTime.now());
        offer.setId(nonExistingId);

        int result = offerDao.delete(offer);

        assertEquals(0, result);
    }
}
