package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Record;
import csu.web.mypetstore.persistence.LogDao;
import csu.web.mypetstore.persistence.ProductDao;
import csu.web.mypetstore.persistence.impl.LogDaoImpl;

import java.util.Date;
import java.util.List;

public class LogService {
    private LogDao logDao = new LogDaoImpl();
    public void addRecord(String username, String operation){
        logDao.addRecord(new Record(username, operation,new Date()));
    }
    public List<Record> searchRecord(String username){
        return logDao.searchRecord(username);
    }

}
