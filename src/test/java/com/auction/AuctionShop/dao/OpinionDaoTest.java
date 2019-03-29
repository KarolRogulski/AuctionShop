package com.auction.AuctionShop.dao;

import com.auction.AuctionShop.domain.Opinion;
import com.auction.AuctionShop.domain.User;
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

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfigurationTest.class)
@Sql({"classpath:user-test-data.sql", "classpath:opinion-test-data.sql"})
public class OpinionDaoTest {

    @Autowired
    private OpinionDao opinionDao;

    @Test
    public void findByIdTest() {
        long idFromTestOpinion = 1L;
        long idFromOpinionDao = opinionDao.findById(idFromTestOpinion).getId();

        assertEquals(idFromTestOpinion, idFromOpinionDao);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findWithNonExistingId(){
        
    }

    @Test
    public void findByAuthorId() {
        long opinion1AuthorId = 2L;

        List<Opinion> opinionList = opinionDao.findByAuthorId(opinion1AuthorId);

        assertEquals(1, opinionList.size());
    }

    @Test
    public void findByUserId() {
        List<Opinion> opinions = opinionDao.findByUserId(2L);

        assertEquals(1, opinions.size());
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

    @Test
    public void delete() {
        Opinion opinion = opinionDao.findById(1L);

        opinionDao.delete(opinion);

        assertEquals(1, opinionDao.getAll().size());
    }
}
