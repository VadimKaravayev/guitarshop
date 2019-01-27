package com.epam.preprod.karavayev.db;

import com.epam.preprod.karavayev.exception.DbException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import javax.sql.DataSource;

public class ConnectionManager {

    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);
    private static final ThreadLocal<Connection> CONNECTIONS = new ThreadLocal<>();
    private DataSource dataSource;

    public ConnectionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        Connection connection = CONNECTIONS.get();
        try {
            if (Objects.isNull(connection) || connection.isClosed()) {
                connection = dataSource.getConnection();
                CONNECTIONS.set(connection);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DbException("Cannot connect to database");
        }
        return connection;
    }

    public void removeConnection() {
        CONNECTIONS.remove();
    }
}
