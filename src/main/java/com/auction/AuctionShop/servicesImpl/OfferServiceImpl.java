package com.auction.AuctionShop.servicesImpl;

import com.auction.AuctionShop.entities.Offer;
import com.auction.AuctionShop.repositories.OfferDao;
import com.auction.AuctionShop.services.OfferService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OfferServiceImpl extends AbstractService<Offer> implements OfferService {

    @Autowired
    private OfferDao offerDao;
    private static final Logger log = LogManager.getLogger(OfferService.class);

    @Override
    public List<Offer> findByAuctionId(long id) {
        log.info("Get list of offers by auction's id = " + id);
        return offerDao.findByAuctionId(id);
    }

    @Override
    public List<Offer> findByOfferOwnerId(long id) {
        log.info("Get list of offers by user's id = " + id);
        return offerDao.findByOfferOwnerId(id);
    }
}
