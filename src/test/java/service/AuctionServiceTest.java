package service;

import com.auction.AuctionShop.entities.Auction;
import com.auction.AuctionShop.exceptions.NotFoundException;
import com.auction.AuctionShop.repositories.AuctionDao;
import com.auction.AuctionShop.servicesImpl.AuctionServiceImpl;
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

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfigurationTest.class)
public class AuctionServiceTest {


    @InjectMocks
    private AuctionServiceImpl auctionService;
    private long nonExistingId = 52342L;

    @Mock
    private AuctionDao auctionDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAuctionById() {
        Auction auction = getAuctions().get(1);
        when(auctionDao.findById(1L)).thenReturn(auction);

        Auction auctionFoundByService = auctionService.findById(1L);

        assertEquals(auction.getTitle(), auctionFoundByService.getTitle());
    }

    @Test(expected = NotFoundException.class)
    public void findNonExistingAuctionById() {
        when(auctionDao.findById(nonExistingId)).thenThrow(new EmptyResultDataAccessException(0));

        auctionService.findById(nonExistingId);
    }

    @Test
    public void returnAllAuctions() {
        when(auctionDao.getAll()).thenReturn(getAuctions());

        List<Auction> list = auctionService.getAll();

        assertEquals(2, list.size());
    }


    @Test
    public void findByOwnerId() {
        when(auctionDao.findByOwnerId(4L)).thenReturn(getAuctions());

        List<Auction> list = auctionService.findByOwnerId(4L);

        assertEquals(2, list.size());
    }

    @Test
    public void findByNonExistingOwnerId() {
        when(auctionDao.findByOwnerId(nonExistingId)).thenReturn(new ArrayList<>());

        List<Auction> list = auctionService.findByOwnerId(4L);

        assertTrue(list.isEmpty());
    }

    @Test
    public void delete() {
        Auction auction = getAuctions().get(1);
        when(auctionDao.delete(auction)).thenReturn(1);

        int result = auctionService.delete(auction);

        assertEquals(result, 1);
    }

    @Test
    public void deleteWithNonExistingUser() {
        Auction auction = getAuctions().get(1);
        auction.setId(nonExistingId);
        when(auctionDao.delete(auction)).thenReturn(0);

        int result = auctionService.delete(auction);

        assertEquals(0, result);
    }

    private List<Auction> getAuctions() {
        List<Auction> list = new ArrayList<>();
        Auction auction1 = new Auction("someTitle1", "someDescription1", 200.0F,
                LocalDateTime.now().minusDays(2), LocalDateTime.now());
        Auction auction2 = new Auction("someTitle2", "someDescription3", 420.0F,
                LocalDateTime.now().minusDays(4), LocalDateTime.now());
        auction1.setId(1L);
        auction2.setId(2L);

        list.add(auction1);
        list.add(auction2);
        return list;
    }
}
