package com.epam.preprod.karavayev.service.impl;

import com.epam.preprod.karavayev.db.TransactionManager;
import com.epam.preprod.karavayev.db.dao.UserDao;
import com.epam.preprod.karavayev.entity.User;
import com.epam.preprod.karavayev.exception.UserAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.epam.preprod.karavayev.service.UserService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Test
    public void name() {

    }
    
    @Mock
    private UserDao userDao;

    @Mock
    TransactionManager transactionManager;

    private User user;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setId(1);
        user.setFirstName("Bob");
        user.setLastName("Bee");
        user.setEmail("bob@bee.bee");
        user.setPassword("1234");
        user.setSubscription(true);
        userService = new UserServiceImpl(userDao, transactionManager);

    }

    @Test
    public void shouldNotThrowExceptionNoUserWithSuchEmail() {
        when(userDao.getUserByEmail(user.getEmail())).thenReturn(null);
        when(userDao.create(user)).thenReturn(true);
        userService.register(user);
    }

}