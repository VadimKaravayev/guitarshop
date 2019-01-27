package com.epam.preprod.karavayev.service.impl;

import com.epam.preprod.karavayev.db.TransactionManager;
import com.epam.preprod.karavayev.db.dao.UserDao;
import com.epam.preprod.karavayev.entity.User;
import com.epam.preprod.karavayev.exception.UserAlreadyExistsException;
import com.epam.preprod.karavayev.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.util.Objects;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private TransactionManager transactionManager;

    public UserServiceImpl(UserDao userDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public void register(User user) {
        transactionManager.manageTransaction(()-> {
            if (Objects.nonNull(userDao.getUserByEmail(user.getEmail()))) {
                throw new UserAlreadyExistsException("User already exists by email -> " + user.getEmail());
            }
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            return userDao.create(user);
        }, Connection.TRANSACTION_READ_COMMITTED);
    }


    @Override
    public User login(String email, String password) {
        return transactionManager.manageTransaction(()-> {
            User user = userDao.getUserByEmail(email);
            if (Objects.nonNull(user) && Objects.equals(DigestUtils.md5Hex(password), user.getPassword())) {
                return user;
            }
            return null;

        }, Connection.TRANSACTION_READ_COMMITTED);
    }


}
