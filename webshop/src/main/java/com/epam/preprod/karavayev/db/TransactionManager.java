package com.epam.preprod.karavayev.db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class TransactionManager {

    private final static Logger LOGGER = Logger.getLogger(TransactionManager.class);
    private ConnectionManager connectionManager;

    public TransactionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public <T> T manageTransaction(TransactionalOperation<T> operation, int isolationLevel) {
        Connection connection = null;
        try  {
            connection = connectionManager.getConnection();
            connection.setTransactionIsolation(isolationLevel);
            connection.setAutoCommit(false);
            T result = operation.execute();
            connection.commit();
            return result;
        } catch (Exception e) {
            LOGGER.error(e);
            rollback(connection);
        } finally {
            close(connection);
            connectionManager.removeConnection();
        }
        return null;
    }

    private void close(Connection connection) {
        if (Objects.nonNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    private void rollback(Connection connection) {
        if (Objects.nonNull(connection)) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }
}
