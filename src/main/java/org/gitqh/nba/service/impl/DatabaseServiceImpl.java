package org.gitqh.nba.service.impl;

import org.gitqh.nba.dao.DatabaseDao;
import org.gitqh.nba.service.DatabaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by quhan on 2017/7/6.
 */
@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Resource
    private DatabaseDao databaseDao;

    @Override
    public void truncateTable(String tableName) {
        databaseDao.truncateTable(tableName);
    }

}
