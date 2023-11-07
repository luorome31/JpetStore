package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.LineItem;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.LineItemDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineItemDaoImpl implements LineItemDao {
    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> lineItems = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT " +
                    "ORDERID, " +
                    "LINENUM AS lineNumber, " +
                    "ITEMID, " +
                    "QUANTITY, " +
                    "UNITPRICE " +
                    "FROM LINEITEM " +
                    "WHERE ORDERID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(resultSet.getInt("ORDERID"));
                lineItem.setLineNumber(resultSet.getInt("lineNumber"));
                lineItem.setItemId(resultSet.getString("ITEMID"));
                lineItem.setQuantity(resultSet.getInt("QUANTITY"));
                lineItem.setUnitPrice(resultSet.getBigDecimal("UNITPRICE"));
                lineItems.add(lineItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }

        return lineItems;
    }


    @Override
    public void insertLineItem(LineItem lineItem) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false); // 关闭自动提交

            String sql = "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) " +
                    "VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, lineItem.getOrderId());
            preparedStatement.setInt(2, lineItem.getLineNumber());
            preparedStatement.setString(3, lineItem.getItemId());
            preparedStatement.setInt(4, lineItem.getQuantity());
            preparedStatement.setBigDecimal(5, lineItem.getUnitPrice());

            preparedStatement.executeUpdate();

            // 如果没有异常，手动提交事务
            connection.commit();
        } catch (SQLException e) {
            // 发生异常时回滚事务
            try {
                if (connection != null) {
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

//    public static void main(String[] args) {
//        //测试这两个方法
//        LineItemDao lineItemDao = new LineItemDaoImpl();
//        LineItem lineItem = new LineItem();
//        lineItem.setOrderId(1);
//        lineItem.setLineNumber(1);
//        lineItem.setItemId("EST-1");
//        lineItem.setQuantity(1);
//        lineItem.setUnitPrice(new java.math.BigDecimal(1));
//        lineItemDao.insertLineItem(lineItem);
//        List<LineItem> lineItems = lineItemDao.getLineItemsByOrderId(1);
//        System.out.println(lineItems);
//
//
//
//    }
}
