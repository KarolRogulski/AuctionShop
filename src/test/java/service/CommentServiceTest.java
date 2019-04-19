package service;

import com.auction.AuctionShop.entities.Comment;
import com.auction.AuctionShop.exceptions.NotFoundException;
import com.auction.AuctionShop.repositories.CommentDao;
import com.auction.AuctionShop.servicesImpl.CommentServiceImpl;
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

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfigurationTest.class)
public class CommentServiceTest {

    @InjectMocks
    private CommentServiceImpl commentService;
    private long nonExistingId = 52342L;

    @Mock
    private CommentDao commentDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findCommentById() {
        Comment comment = getComments().get(1);
        when(commentDao.findById(1L)).thenReturn(comment);

        Comment commentFoundByService = commentService.findById(1L);

        assertEquals(comment.getTitle(), commentFoundByService.getTitle());
    }

    @Test(expected = NotFoundException.class)
    public void findNonExistingCommentById() {
        when(commentDao.findById(nonExistingId)).thenThrow(new EmptyResultDataAccessException(0));

        commentService.findById(nonExistingId);
    }

    @Test
    public void returnAllComments() {
        when(commentDao.getAll()).thenReturn(getComments());

        List<Comment> list = commentService.getAll();

        assertEquals(2, list.size());
    }


    @Test
    public void findByAuctionId() {
        when(commentDao.findByAuctionId(1L)).thenReturn(getComments());

        List<Comment> list = commentService.findByAuctionId(1L);

        assertEquals(2, list.size());
    }

    @Test
    public void findByNonExistingAuctionId() {
        when(commentDao.findByAuctionId(nonExistingId)).thenReturn(new ArrayList<>());

        List<Comment> list = commentService.findByAuctionId(nonExistingId);

        assertTrue(list.isEmpty());
    }

    @Test
    public void delete() {
        Comment comment = getComments().get(1);
        when(commentDao.delete(comment)).thenReturn(1);

        int result = commentService.delete(comment);

        assertEquals(result, 1);
    }

    @Test
    public void deleteWithNonExistingUser() {
        Comment comment = getComments().get(1);
        comment.setId(nonExistingId);
        when(commentDao.delete(comment)).thenReturn(0);

        int result = commentService.delete(comment);

        assertEquals(0, result);
    }

    private List<Comment> getComments() {
        List<Comment> list = new ArrayList<>();
        Comment comment1 = new Comment("someTitle1", "someContent1", LocalDateTime.now());
        Comment comment2 = new Comment("someTitle2", "someContent2", LocalDateTime.now());
        comment1.setId(1L);
        comment2.setId(2L);

        list.add(comment1);
        list.add(comment2);
        return list;
    }
}
