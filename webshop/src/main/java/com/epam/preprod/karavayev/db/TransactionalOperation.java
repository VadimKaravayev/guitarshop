package com.epam.preprod.karavayev.db;

@FunctionalInterface
public interface TransactionalOperation<T> {
    T execute();
}
