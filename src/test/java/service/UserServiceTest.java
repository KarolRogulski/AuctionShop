package service;


import com.auction.AuctionShop.entities.User;
import com.auction.AuctionShop.exceptions.NotFoundException;
import com.auction.AuctionShop.services.UserService;
import config.DataBaseConfigurationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@ContextConfiguration(classes = DataBaseConfigurationTest.class)
@Sql("classpath:user-test-data.sql")
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private long nonExistingId = 52342L;

    @Test
    public void findUserById() {
        long idFromTestUser1 = 1L;

        long idFromUserDao = userService.findById(1L).getId();

        assertEquals(idFromUserDao, idFromTestUser1);
    }

    @Test(expected = NotFoundException.class)
    public void findNonExistingUserById() {
        userService.findById(nonExistingId);
    }

    @Test
    public void findByLogin() {
        String testLoginBefore = "someLogin2";

        User userFromService = userService.findByLogin(testLoginBefore);
        String loginFromUserDao = userFromService.getLogin();

        assertEquals(loginFromUserDao, testLoginBefore);
    }


    @Test(expected = NotFoundException.class)
    public void voidFindNonExistingUserByLogin() {
        String nonExistingLogin = "nonExistingLogin";

        userService.findByLogin(nonExistingLogin);
    }

    @Test
    public void returnAllUsers() {
        List<User> usersList = userService.getAll();

        assertEquals(3, usersList.size());
    }

    @Test(expected = NotFoundException.class)
    public void updateWithNotMatchingId() {
        User updatedUser = new User("updatedEmail1", "updatedLogin1", "updatedPassword1", LocalDate.now());
        updatedUser.setId(nonExistingId);

        userService.update(updatedUser);
    }

    @Test
    public void update() {
        String loginBeforeUpdate = "someLogin2";
        User updatedUser = new User("updatedEmail1", "updatedLogin1", "updatedPassword1", LocalDate.now());
        updatedUser.setId(1L);

        userService.update(updatedUser);
        String loginAfterUpdate = userService.findByLogin(updatedUser.getLogin()).getLogin();

        assertNotEquals(loginBeforeUpdate, loginAfterUpdate);
    }

    @Test
    public void delete() {
        User user = userService.findById(1L);

        int result = userService.delete(user);

        assertEquals(result, 1);
    }

    @Test
    public void deleteWithNonExistingUser(){
        User user = new User("email", "login", "password", LocalDate.now());
        user.setId(1533L);

        int result = userService.delete(user);

        assertEquals(0, result);
    }


}
