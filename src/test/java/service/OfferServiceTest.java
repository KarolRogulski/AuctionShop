package service;

import com.auction.AuctionShop.entities.Offer;
import com.auction.AuctionShop.exceptions.NotFoundException;
import com.auction.AuctionShop.repositories.OfferDao;
import com.auction.AuctionShop.servicesImpl.OfferServiceImpl;
import config.DataBaseConfigurationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataBaseConfigurationTest.class})
public class OfferServiceTest {

    @InjectMocks
    private OfferServiceImpl offerService;
    private long nonExistingId = 52342L;

    @Mock
    private OfferDao offerDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findOfferById() {
        Offer offer = getOffers().get(1);
        when(offerDao.findById(1L)).thenReturn(offer);

        Offer auctionFoundByService = offerService.findById(1L);

        assertEquals(offer.getAdded(), auctionFoundByService.getAdded());
    }

    @Test(expected = NotFoundException.class)
    public void findNonExistingOfferById() {
        when(offerDao.findById(nonExistingId)).thenThrow(new EmptyResultDataAccessException(0));

        offerService.findById(nonExistingId);
    }

    @Test
    public void findByOwnerId(){
        when(offerDao.findByOfferOwnerId(1L)).thenReturn(getOffers());

        List<Offer> list = offerService.findByOfferOwnerId(1L);

        assertEquals(2, list.size());
    }

    @Test
    public void findByNonExistingOwnerId(){
        when(offerDao.findByOfferOwnerId(nonExistingId)).thenReturn(new ArrayList<>());

        List<Offer> list = offerService.findByOfferOwnerId(1L);

        assertTrue(list.isEmpty());
    }


    @Test
    public void findByAuctionId(){
        when(offerDao.findByAuctionId(1L)).thenReturn(getOffers());

        List<Offer> list = offerService.findByAuctionId(1L);

        assertEquals(2, list.size());
    }

    @Test
    public void findByNonExistingAuctionId(){
        when(offerDao.findByAuctionId(nonExistingId)).thenReturn(new ArrayList<>());

        List<Offer> list = offerService.findByAuctionId(1L);

        assertTrue(list.isEmpty());
    }

    @Test
    public void returnAllOffers() {
        when(offerDao.getAll()).thenReturn(getOffers());

        List<Offer> list = offerService.getAll();

        assertEquals(2, list.size());
    }

    @Test
    public void delete() {
        Offer offer = getOffers().get(1);
        when(offerDao.delete(offer)).thenReturn(1);

        int result = offerService.delete(offer);

        assertEquals(result, 1);
    }

    @Test
    public void deleteWithNonExistingUser() {
        Offer offer = getOffers().get(1);
        offer.setId(nonExistingId);
        when(offerDao.delete(offer)).thenReturn(0);

        int result = offerService.delete(offer);

        assertEquals(0, result);
    }

    private List<Offer> getOffers() {
        List<Offer> list = new ArrayList<>();
        Offer offer1 = new Offer(203.0F, LocalDateTime.now());
        Offer offer2 = new Offer(233.0F, LocalDateTime.now().minusWeeks(1));
        offer1.setId(1L);
        offer2.setId(2L);

        list.add(offer1);
        list.add(offer2);
        return list;
    }
}
