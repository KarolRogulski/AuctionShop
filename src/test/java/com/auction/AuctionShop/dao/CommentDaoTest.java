package com.auction.AuctionShop.dao;

import com.auction.AuctionShop.domain.Comment;
import com.auction.AuctionShop.repositories.CommentDao;
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
@Sql({"classpath:user-test-data.sql", "classpath:auction-test-data.sql", "classpath:comment-test-data.sql"})
public class CommentDaoTest {

    @Autowired
    private CommentDao commentDao;
    private long nonExistingId = 5132L;

    @Test
    public void findById() {
        long idBeforeTest = 1L;
        long idAfterTest = commentDao.findById(1L).getId();

        assertEquals(idBeforeTest, idAfterTest);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findByNonExistingId() {
        commentDao.findById(nonExistingId);
    }

    @Test
    public void findAll() {
        List<Comment> auctionList = commentDao.getAll();

        assertEquals(2, auctionList.size());
    }

    @Test
    public void update() {
        Comment comment = commentDao.findById(2L);
        Comment updatedComment = new Comment("updatedTitle2", "someContent2",
                LocalDateTime.now());
        updatedComment.setId(comment.getId());
        String titleBeforeUpdate = comment.getTitle();

        commentDao.update(updatedComment);
        String titleAfterUpdate = commentDao.findById(comment.getId()).getTitle();

        assertNotEquals(titleAfterUpdate, titleBeforeUpdate);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void updateWithNonExistingAuction() {
        Comment comment = new Comment("updatedTitle2", "someDescription2",
                LocalDateTime.now());
        comment.setId(nonExistingId);

        commentDao.update(comment);
    }

    @Test
    public void findByAuctionId() {
        long auctionId = 1L;

        List<Comment> list = commentDao.findByAuctionId(auctionId);

        assertEquals(1, list.size());
    }

    @Test
    public void findByNonExistingAuctionId() {
        List<Comment> list = commentDao.findByAuctionId(nonExistingId);

        assertTrue(list.isEmpty());
    }

    @Test
    public void delete() {
        Comment comment = commentDao.findById(1L);

        int result = commentDao.delete(comment);
        assertEquals(1, result);
    }

    @Test
    public void deleteNonExistingAuction(){
        Comment comment= new Comment("updatedTitle2", "someDescription2",
                LocalDateTime.now());
        comment.setId(nonExistingId);

        int result = commentDao.delete(comment);

        assertEquals(0, result);
    }
}
