package com.auction.AuctionShop.dao;

import com.auction.AuctionShop.configuration.DataBaseConfiguration;
import com.auction.AuctionShop.domain.Opinion;
import com.auction.AuctionShop.domain.User;
import com.auction.AuctionShop.repositories.OpinionDao;
import com.auction.AuctionShop.repositories.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = DataBaseConfiguration.class)
public class OpinionDaoTest {

	@Autowired
	private OpinionDao opinionDao;

	private User user1 = new User("someEmail1", "someLogin1", "somePassword1", LocalDate.now());
	private User opinionAuthor = new User("someEmail1", "someLogin1", "somePassword1", LocalDate.now());
	private User opinionAuthor2 = new User("someEmail2", "someLogin2", "somePassword2", LocalDate.now());
	private Opinion opinion2 = new Opinion(2, "someTitle2", "someDescription2", user1, opinionAuthor2);
	private Opinion opinion1 = new Opinion(1, "someTitle1", "someDescription1", user1, opinionAuthor);

	@Test
	@Transactional
	@Rollback
	public void findByIdTest(){
		opinionDao.save(opinion1);

		long idFromTestOpinion = opinion1.getId();
		long idFromOpinionDao = opinionDao.findById(opinion1.getId()).getId();

		assertEquals(idFromTestOpinion, idFromOpinionDao);
	}

	@Test
	@Transactional
	@Rollback
	public void findByAuthorId(){
		opinionDao.save(opinion1);
		opinionDao.save(opinion2);
		long opinion1AuthorId = opinion1.getOpinionAuthor().getId();

		List<Opinion> opinionList = opinionDao.findByAuthorId(opinion1AuthorId);

		assertEquals(1, opinionList.size());
	}

	@Test
	@Transactional
	@Rollback
	public void findByUserId(){
		opinionDao.save(opinion1);
		opinionDao.save(opinion2);

		List<Opinion> opinions = opinionDao.findByUserId(user1.getId());

		assertEquals(2, opinions.size());
	}

	@Test
	@Transactional
	@Rollback
	public void getAll(){
		opinionDao.save(opinion1);
		opinionDao.save(opinion2);

		List<Opinion> opinions = opinionDao.getAll();

		assertEquals(2, opinions.size());
	}

	@Test
	@Transactional
	@Rollback
	public void update(){
		String opinionDescriptionBefore = opinion1.getDescription();
		opinionDao.save(opinion1);
		Opinion updatedOpinion = opinion2;
		updatedOpinion.setId(opinion1.getId());

		opinionDao.update(updatedOpinion);
		String opinionDescriptionAfter = opinionDao.findById(opinion1.getId()).getDescription();

		assertNotEquals(opinionDescriptionBefore, opinionDescriptionAfter);
	}

	@Test
	@Transactional
	@Rollback
	public void delete(){
		opinionDao.save(opinion1);

		opinionDao.delete(opinion1);

		assertEquals(0, opinionDao.getAll().size());
	}
}
