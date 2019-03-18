package com.auction.AuctionShop.dao;

import com.auction.AuctionShop.configuration.DataBaseConfiguration;
import com.auction.AuctionShop.domain.Auction;
import com.auction.AuctionShop.domain.User;
import com.auction.AuctionShop.repositories.AuctionDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfiguration.class)
public class AuctionDaoTest {

    @Autowired
    private AuctionDao auctionDao;


    private User user = new User("someEmail1", "someLogin1", "somePassword1", LocalDate.now());
    private Auction auction1 = new Auction("someTitle1", "someDescription1", 200.0F,
            LocalDateTime.now(), LocalDateTime.now().plusDays(2), user);
    private Auction auction2 = new Auction("someTitle2", "someDescription2", 400.0F,
            LocalDateTime.now(), LocalDateTime.now().plusDays(4), user);


    @Test
    @Rollback
    public void findById(){
        auctionDao.save(auction1);
        long idBefore = auction1.getId();
        long idAfter = auctionDao.findById(auction1.getId()).getId();

        assertEquals(idBefore, idAfter);
    }

    @Test
    @Rollback
    public void findAll(){
        auctionDao.save(auction1);
        auctionDao.save(auction2);

        List<Auction> auctionList = auctionDao.getAll();

        assertEquals(2, auctionList.size());
    }

    @Test
    @Rollback
    public void update(){
        String titleBeforeUpdate = auction1.getTitle();
        auctionDao.save(auction1);
        Auction updatedAuction = new Auction("someTitle2", "someDescription2", 400.0F,
                LocalDateTime.now(), LocalDateTime.now().plusDays(4), user);
        updatedAuction.setId(auction1.getId());

        auctionDao.update(updatedAuction);
        String titleAfterUpdate = auctionDao.findById(updatedAuction.getId()).getTitle();

        assertNotEquals(titleAfterUpdate, titleBeforeUpdate);
    }

    @Test
    @Rollback
    public void delete(){
        auctionDao.save(auction1);
        auctionDao.save(auction2);

        auctionDao.delete(auction1);

        int sizeAfterDelete = auctionDao.getAll().size();

        assertEquals(1, sizeAfterDelete);
    }


}
