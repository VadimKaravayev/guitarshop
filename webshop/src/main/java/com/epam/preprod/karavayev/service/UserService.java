package com.epam.preprod.karavayev.service;

import com.epam.preprod.karavayev.entity.User;

public interface UserService {

    /**
     * Method register user
     *
     * @param user you want to add
     * @throws com.epam.preprod.karavayev.exception.UserAlreadyExistsException if user's email already exists
     */
    void register(User user);

    User login(String email, String password);
}
