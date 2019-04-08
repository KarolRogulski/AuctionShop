package com.auction.AuctionShop.dao;

import com.auction.AuctionShop.configuration.DataBaseConfiguration;
import com.auction.AuctionShop.entities.User;
import com.auction.AuctionShop.repositories.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfiguration.class)
@Sql("classpath:user-test-data.sql")
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void findUserById() {
        long idFromTestUser1 = 1L;

        long idFromUserDao = userDao.findById(1L).getId();

        assertEquals(idFromUserDao, idFromTestUser1);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void findNonExistingUserById() {
        long nonExistingId = 531235L;

        userDao.findById(nonExistingId);
    }

    @Test
    public void findByLogin() {
        String testLoginBefore = "someLogin2";

        User userFromDao = userDao.findByLogin(testLoginBefore);
        String loginFromUserDao = userFromDao.getLogin();

        assertEquals(loginFromUserDao, testLoginBefore);
    }


    @Test(expected = EmptyResultDataAccessException.class)
    public void voidFindNonExistingUserByLogin() {
        String nonExistingLogin = "nonExistingLogin";

        userDao.findByLogin(nonExistingLogin);
    }

    @Test
    public void returnAllUsers() {
        List<User> usersList = userDao.getAll();

        assertEquals(3, usersList.size());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void updateWithNotMatchingId() {
        User updatedUser = new User("updatedEmail1", "updatedLogin1", "updatedPassword1", LocalDate.now());
        updatedUser.setId(143242L);

        userDao.update(updatedUser);
    }

    @Test
    public void update() {
        String loginBeforeUpdate = "someLogin2";
        User updatedUser = new User("updatedEmail1", "updatedLogin1", "updatedPassword1", LocalDate.now());
        updatedUser.setId(1L);

        userDao.update(updatedUser);

        assertNotEquals(loginBeforeUpdate, userDao.findByLogin(updatedUser.getLogin()).getLogin());
    }

    @Test
    public void delete() {
        User user = userDao.findById(1L);

        int result = userDao.delete(user);

        assertEquals(result, 1);
    }

    @Test
    public void deleteWithNonExistingUser(){
        User user = new User("email", "login", "password", LocalDate.now());
        user.setId(1533L);

        int result = userDao.delete(user);

        assertEquals(0, result);
    }
}
