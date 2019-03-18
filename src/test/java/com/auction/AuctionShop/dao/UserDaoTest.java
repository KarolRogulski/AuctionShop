package com.auction.AuctionShop.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.auction.AuctionShop.configuration.DataBaseConfiguration;
import com.auction.AuctionShop.domain.User;
import com.auction.AuctionShop.repositories.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfiguration.class)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    private User user = new User("someEmail1", "someLogin1", "somePassword1", LocalDate.now());
    private User secondUser = new User("someEmail2", "someLogin2", "somePassword2", LocalDate.now());

    @Test
    @Transactional
    @Rollback
    public void findUserByIdTest() {
        userDao.save(user);
        long idFromTestUser1 = user.getId();

        long idFromUserDao = userDao.findById(user.getId()).getId();

        assertEquals(idFromUserDao, idFromTestUser1);
    }

    @Test
    @Transactional
    @Rollback
    public void findByLogin() {
        String loginFromTestUser1 = user.getLogin();
        userDao.save(user);

        User userFromDao = userDao.findByLogin(user.getLogin());
        String loginFromUserDao = userFromDao.getLogin();

        assertEquals(loginFromUserDao, loginFromTestUser1);
    }

    @Test
    @Transactional
    @Rollback
    public void returnAllUsers() {
        userDao.save(user);
        userDao.save(secondUser);

        List<User> usersList = userDao.getAll();

        assertEquals(2, usersList.size());
    }

    @Test
    @Transactional
    @Rollback
    public void update() {
        String loginBeforeUpdate = user.getLogin();
        userDao.save(user);
        User updatedUser = new User("updatedEmail1", "updatedLogin1", "updatedPassword1", LocalDate.now());
        updatedUser.setId(user.getId());

        userDao.update(updatedUser);
        assertNotEquals(loginBeforeUpdate, userDao.findByLogin(updatedUser.getLogin()).getLogin());
    }

    @Test
    @Transactional
    @Rollback
    public void delete() {
        userDao.save(user);
        userDao.save(secondUser);

        userDao.delete(user);

        int usersLeftInDB = userDao.getAll().size();
        assertEquals(usersLeftInDB, 1);
    }
}
