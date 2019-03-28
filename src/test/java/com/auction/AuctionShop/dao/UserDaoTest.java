package com.auction.AuctionShop.dao;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.auction.AuctionShop.configuration.DataBaseConfiguration;
import com.auction.AuctionShop.domain.User;
import com.auction.AuctionShop.repositories.UserDao;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfiguration.class)
@Sql("classpath:user-test-data.sql")
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    @Rollback
    public void findUserByIdTest() {
        long idFromTestUser1 = 1L;

        long idFromUserDao = userDao.findById(1L).getId();

        assertEquals(idFromUserDao, idFromTestUser1);
    }

    @Test
    @Transactional
    @Rollback
    public void findByLogin() {
        String testLoginBefore = "someLogin2";

        User userFromDao = userDao.findByLogin(testLoginBefore);
        String loginFromUserDao = userFromDao.getLogin();

        assertEquals(loginFromUserDao, testLoginBefore);
    }

    @Test
    @Transactional
    @Rollback
    public void returnAllUsers() {
        List<User> usersList = userDao.getAll();

        assertEquals(3, usersList.size());
    }

    @Test
    @Transactional
    @Rollback
    public void update() {
        String loginBeforeUpdate = "someLogin2";
        User updatedUser = new User("updatedEmail1", "updatedLogin1", "updatedPassword1", LocalDate.now());
        updatedUser.setId(1L);

        userDao.update(updatedUser);

        assertNotEquals(loginBeforeUpdate, userDao.findByLogin(updatedUser.getLogin()).getLogin());
    }

    @Test
    @Transactional
    @Rollback
    public void delete() {
        User user = userDao.findById(1L);

        userDao.delete(user);

        int usersLeftInDB = userDao.getAll().size();
        assertEquals(usersLeftInDB, 2);
    }
}
