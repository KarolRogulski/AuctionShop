package com.auction.AuctionShop.dao;

import com.auction.AuctionShop.entities.Opinion;
import com.auction.AuctionShop.repositories.OpinionDao;
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

import java.util.List;

import static org.junit.Assert.*;

@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfigurationTest.class)
@Sql({"classpath:user-test-data.sql", "classpath:opinion-test-data.sql"})
public class OpinionDaoTest {

    @Autowired
    private OpinionDao opinionDao;
    private long nonExistingId = 85920L;

    @Test
    public void findById() {
        long idFromTestOpinion = 1L;
        long idFromOpinionDao = opinionDao.findById(idFromTestOpinion).getId();

        assertEquals(idFromTestOpinion, idFromOpinionDao);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findWithNonExistingId() {
        opinionDao.findById(nonExistingId);
    }

    @Test
    public void findByAuthorId() {
        long opinion1AuthorId = 2L;

        List<Opinion> opinionList = opinionDao.findByAuthorId(opinion1AuthorId);

        assertEquals(1, opinionList.size());
    }

    @Test
    public void findWithNonExistingAuthorId() {
        List<Opinion> list = opinionDao.findByAuthorId(nonExistingId);

        assertTrue(list.isEmpty());
    }

    @Test
    public void findByUserId() {
        List<Opinion> opinions = opinionDao.findByUserId(2L);

        assertEquals(1, opinions.size());
    }

    @Test
    public void findWithNonExistingUserId() {
        List<Opinion> list = opinionDao.findByUserId(nonExistingId);

        assertTrue(list.isEmpty());
    }

    @Test
    public void getAll() {
        List<Opinion> opinions = opinionDao.getAll();

        assertEquals(2, opinions.size());
    }

    @Test
    public void update() {
        Opinion beforeUpdateOpinion = opinionDao.findById(1L);
        Opinion updatedOpinion = new Opinion(2.5F, "someTitle1", "updatedDescription");
        String beforeUpdateDescription = beforeUpdateOpinion.getDescription();
        updatedOpinion.setId(1L);

        opinionDao.update(updatedOpinion);
        String opinionDescriptionAfter = opinionDao.findById(1L).getDescription();

        assertNotEquals(beforeUpdateDescription, opinionDescriptionAfter);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void updateWithNonExistingOpinion() {
        Opinion updatedOpinion = new Opinion(2.5F, "someTitle1", "updatedDescription");
        updatedOpinion.setId(nonExistingId);

        opinionDao.update(updatedOpinion);
    }

    @Test
    public void delete() {
        Opinion opinion = opinionDao.findById(1L);

        int result = opinionDao.delete(opinion);

        assertEquals(1, result);
    }

    @Test
    public void deleteWithNonExistingOpinion() {
        Opinion opinion = new Opinion(2.5F, "someTitle1", "updatedDescription");
        opinion.setId(nonExistingId);

        int result = opinionDao.delete(opinion);

        assertEquals(0, result);
    }
}
