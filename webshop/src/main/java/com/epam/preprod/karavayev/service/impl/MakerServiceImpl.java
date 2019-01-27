package com.epam.preprod.karavayev.service.impl;

import com.epam.preprod.karavayev.db.TransactionManager;
import com.epam.preprod.karavayev.db.dao.MakerDao;
import com.epam.preprod.karavayev.entity.Maker;
import com.epam.preprod.karavayev.service.MakerService;

import java.sql.Connection;
import java.util.List;

public class MakerServiceImpl implements MakerService {

    private MakerDao makerDao;
    private TransactionManager transactionManager;

    public MakerServiceImpl(MakerDao makerDao, TransactionManager transactionManager) {
        this.makerDao = makerDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Maker> getAllMakers() {
        return transactionManager.manageTransaction(() -> makerDao.getAllMakers(), Connection.TRANSACTION_READ_COMMITTED);
    }
}
