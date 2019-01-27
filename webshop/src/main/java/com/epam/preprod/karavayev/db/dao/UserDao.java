package com.epam.preprod.karavayev.db.dao;

import com.epam.preprod.karavayev.entity.User;

public interface UserDao {

    boolean create(User user);

    User getUserByEmail(String email);
}
