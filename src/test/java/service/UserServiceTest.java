package service;


import com.auction.AuctionShop.entities.User;
import com.auction.AuctionShop.exceptions.NotFoundException;
import com.auction.AuctionShop.repositories.UserDao;
import com.auction.AuctionShop.servicesImpl.UserServiceImpl;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfigurationTest.class)
public class UserServiceTest {


    @InjectMocks
    private UserServiceImpl userService;
    private long nonExistingId = 52342L;

    @Mock
    private UserDao userDao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findUserById() {
        User user = getUsers().get(1);
        when(userDao.findById(1L)).thenReturn(user);

        User userFoundByService = userService.findById(1L);

        assertEquals(user.getLogin(), userFoundByService.getLogin());
    }

    @Test(expected = NotFoundException.class)
    public void findNonExistingUserById() {
        when(userDao.findById(nonExistingId)).thenThrow(new EmptyResultDataAccessException(0));

        userService.findById(nonExistingId);
    }


    @Test
    public void findByLogin() {
        User user = getUsers().get(1);
        when(userDao.findByLogin(user.getLogin())).thenReturn(user);

        User userFoundByLogin = userService.findByLogin(user.getLogin());

        assertEquals(user.getLogin(), userFoundByLogin.getLogin());
    }


    @Test(expected = NotFoundException.class)
    public void voidFindNonExistingUserByLogin() {
        String nonExistingLogin = "nonExistingLogin";
        when(userDao.findByLogin(nonExistingLogin)).thenThrow(new EmptyResultDataAccessException(1));

        userService.findByLogin(nonExistingLogin);
    }

    @Test
    public void returnAllUsers() {
        when(userDao.getAll()).thenReturn(getUsers());

        List<User> usersList = userService.getAll();

        assertEquals(2, usersList.size());
    }

    @Test
    public void delete() {
        User user = getUsers().get(1);
        when(userDao.delete(user)).thenReturn(1);

        int result = userService.delete(user);

        assertEquals(result, 1);
    }

    @Test
    public void deleteWithNonExistingUser() {
        User user = getUsers().get(1);
        user.setId(nonExistingId);
        when(userDao.delete(user)).thenReturn(0);

        int result = userService.delete(user);

        assertEquals(0, result);
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User("someEmail1", "someLogin1", "somePassword1", LocalDate.now());
        User user2 = new User("someEmail2", "someLogin2", "somePassword2", LocalDate.now());
        user1.setId(1L);
        user2.setId(2L);

        users.add(user1);
        users.add(user2);
        return users;
    }
}
