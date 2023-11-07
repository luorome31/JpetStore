package csu.web.mypetstore.persistence.impl;


import csu.web.mypetstore.domain.Record;
import csu.web.mypetstore.persistence.DBUtil;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class LogDaoImpl implements csu.web.mypetstore.persistence.LogDao {

    @Override
    public void addRecord(Record record) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false); // 手动开启事务

            String sql = "INSERT INTO LOGSHEET (userId, record,recordDate) VALUES (?, ? ,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, record.getUsername());
            preparedStatement.setString(2, record.getRecord());
            preparedStatement.setDate(3, new java.sql.Date(record.getRecordDate().getTime()));

            preparedStatement.executeUpdate();

            // 提交事务
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    // 发生异常时回滚事务
                    connection.rollback();
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true); // 恢复自动提交
                    connection.close();
                }
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }
    }


    @Override
    public List<Record> searchRecord(String username) {
        List<Record> records = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT userId AS username, record ,recordDate FROM LOGSHEET WHERE userId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Record record = new Record();
                record.setUsername(resultSet.getString("username"));
                record.setRecord(resultSet.getString("record"));
                record.setRecordDate(resultSet.getDate("recordDate"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }

        return records;
    }

//    public static void main(String[] args) {
//        LogDaoImpl logDao = new LogDaoImpl();
//        Record record = new Record();
//        record.setUsername("test");
//        record.setRecord("test");
//        logDao.addRecord(record);
//    }
public static void main(String[] args) {
    //测试addRecord方法
    LogDaoImpl logDao = new LogDaoImpl();
    Record record = new Record();
    record.setUsername("test");
    record.setRecord("test");
    record.setRecordDate(new java.util.Date());
    logDao.addRecord(record);

    //测试searchRecord方法
    List<Record> records = logDao.searchRecord("j2ee");
    for (Record record1: records) {
        System.out.println(record1.getUsername());
        System.out.println(record1.getRecord());
        System.out.println(record1.getRecordDate());
    }
}
}
