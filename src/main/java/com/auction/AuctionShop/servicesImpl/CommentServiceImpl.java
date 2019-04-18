package com.auction.AuctionShop.servicesImpl;

import com.auction.AuctionShop.entities.Comment;
import com.auction.AuctionShop.repositories.CommentDao;
import com.auction.AuctionShop.services.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentServiceImpl extends AbstractService<Comment> implements CommentService {

    @Autowired
    private CommentDao commentDao;
    private static final Logger log = LogManager.getLogger(CommentService.class);

    @Override
    public List<Comment> findByAuctionId(long id){
        log.info("Get list of comments by auction id = " + id);
        return commentDao.findByAuctionId(id);
    }
}
