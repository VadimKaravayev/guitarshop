package com.epam.preprod.karavayev.db.dao.mysql;

import com.epam.preprod.karavayev.converter.ResultSetToUser;
import com.epam.preprod.karavayev.db.ConnectionManager;
import com.epam.preprod.karavayev.db.dao.UserDao;
import com.epam.preprod.karavayev.entity.User;
import com.epam.preprod.karavayev.exception.DbException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlUserDaoImpl implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlUserDaoImpl.class);
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String CREATE_USER = "INSERT INTO users (first_name, last_name, email, password, subscription) VALUES(?, ?, ?, ?, ?)";

    private ResultSetToUser toUserConverter;
    private ConnectionManager connectionManager;

    public MysqlUserDaoImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        toUserConverter = new ResultSetToUser();
    }

    @Override
    public boolean create(User user) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER, PreparedStatement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.isSubscription() ? 1 : 0);
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            } else {
                throw new DbException("Cannot create user");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DbException("Cannot create user", e);
        }
        return true;
    }

    @Override
    public User getUserByEmail(String email) {
        Connection connection = connectionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                return toUserConverter.convert(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DbException("Cannot get user by email");
        }
        return null;
    }
}
