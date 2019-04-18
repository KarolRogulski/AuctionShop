package com.auction.AuctionShop.servicesImpl;

import com.auction.AuctionShop.entities.Auction;
import com.auction.AuctionShop.repositories.AuctionDao;
import com.auction.AuctionShop.services.AuctionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class AuctionServiceImpl extends AbstractService<Auction> implements AuctionService {

    @Autowired
    private AuctionDao auctionDao;
    private static final Logger log = LogManager.getLogger(AuctionService.class);

    @Override
    public List<Auction> findByOwnerId(long id) {
        log.info("Get auctions by auction's owner id = " + id);
        return auctionDao.findByOwnerId(id);
    }
}
