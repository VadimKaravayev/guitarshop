package com.epam.preprod.karavayev.db.dao.inner;

import com.epam.preprod.karavayev.db.dao.UserDao;
import com.epam.preprod.karavayev.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InnerUserDaoImpl implements UserDao {

    private List<User> usersDb;

    public InnerUserDaoImpl(List<User> users) {
        this.usersDb = users;
    }

    @Override
    public User getUserByEmail(String email) {

        return usersDb.stream().filter(user -> Objects.equals(user.getEmail(), email)).findFirst().orElse(null);
    }

    @Override
    public boolean create(User entity) {
        int id = usersDb.stream().mapToInt(User::getId).max().orElse(0) + 1;
        entity.setId(id);
        return usersDb.add(entity);
    }
}


