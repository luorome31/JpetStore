package csu.web.mypetstore.persistence.impl;

import java.sql.*;

import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.persistence.OrderDao;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    @Override
    public List<Order> getOrdersByUsername(String username) {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT " +
                    "BILLADDR1 AS billAddress1, " +
                    "BILLADDR2 AS billAddress2, " +
                    "BILLCITY, " +
                    "BILLCOUNTRY, " +
                    "BILLSTATE, " +
                    "BILLTOFIRSTNAME, " +
                    "BILLTOLASTNAME, " +
                    "BILLZIP, " +
                    "SHIPADDR1 AS shipAddress1, " +
                    "SHIPADDR2 AS shipAddress2, " +
                    "SHIPCITY, " +
                    "SHIPCOUNTRY, " +
                    "SHIPSTATE, " +
                    "SHIPTOFIRSTNAME, " +
                    "SHIPTOLASTNAME, " +
                    "SHIPZIP, " +
                    "CARDTYPE, " +
                    "COURIER, " +
                    "CREDITCARD, " +
                    "EXPRDATE AS expiryDate, " +
                    "LOCALE, " +
                    "ORDERDATE, " +
                    "ORDERS.ORDERID, " +
                    "TOTALPRICE, " +
                    "USERID AS username, " +
                    "STATUS " +
                    "FROM ORDERS, ORDERSTATUS " +
                    "WHERE ORDERS.USERID = ? " +
                    "AND ORDERS.ORDERID = ORDERSTATUS.ORDERID " +
                    "ORDER BY ORDERDATE";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Order order = resultSetToOrder(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }

        return orders;
    }


    @Override
    public Order getOrder(int orderId) {
        Order order = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT " +
                    "BILLADDR1 AS billAddress1, " +
                    "BILLADDR2 AS billAddress2, " +
                    "BILLCITY, " +
                    "BILLCOUNTRY, " +
                    "BILLSTATE, " +
                    "BILLTOFIRSTNAME, " +
                    "BILLTOLASTNAME, " +
                    "BILLZIP, " +
                    "SHIPADDR1 AS shipAddress1, " +
                    "SHIPADDR2 AS shipAddress2, " +
                    "SHIPCITY, " +
                    "SHIPCOUNTRY, " +
                    "SHIPSTATE, " +
                    "SHIPTOFIRSTNAME, " +
                    "SHIPTOLASTNAME, " +
                    "SHIPZIP, " +
                    "CARDTYPE, " +
                    "COURIER, " +
                    "CREDITCARD, " +
                    "EXPRDATE AS expiryDate, " +
                    "LOCALE, " +
                    "ORDERDATE, " +
                    "ORDERS.ORDERID, " +
                    "TOTALPRICE, " +
                    "USERID AS username, " +
                    "STATUS " +
                    "FROM ORDERS, ORDERSTATUS " +
                    "WHERE ORDERS.ORDERID = ? " +
                    "AND ORDERS.ORDERID = ORDERSTATUS.ORDERID";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                order = resultSetToOrder(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }

        return order;
    }


    @Override
    public void insertOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean transactionSuccessful = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);

            String sql = "INSERT INTO orders (orderid, userid, orderdate, shipaddr1, shipaddr2, " +
                    "shipcity, shipstate, shipzip, shipcountry, billaddr1, billaddr2, billcity, billstate, " +
                    "billzip, billcountry, courier, totalprice, billtofirstname, billtolastname, shiptofirstname, " +
                    "shiptolastname, creditcard, exprdate, cardtype, locale) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setString(2, order.getUsername());
            preparedStatement.setTimestamp(3, new Timestamp(order.getOrderDate().getTime()));
            preparedStatement.setString(4, order.getShipAddress1());
            preparedStatement.setString(5, order.getShipAddress2());
            preparedStatement.setString(6, order.getShipCity());
            preparedStatement.setString(7, order.getShipState());
            preparedStatement.setString(8, order.getShipZip());
            preparedStatement.setString(9, order.getShipCountry());
            preparedStatement.setString(10, order.getBillAddress1());
            preparedStatement.setString(11, order.getBillAddress2());
            preparedStatement.setString(12, order.getBillCity());
            preparedStatement.setString(13, order.getBillState());
            preparedStatement.setString(14, order.getBillZip());
            preparedStatement.setString(15, order.getBillCountry());
            preparedStatement.setString(16, order.getCourier());
            preparedStatement.setBigDecimal(17, order.getTotalPrice());
            preparedStatement.setString(18, order.getBillToFirstName());
            preparedStatement.setString(19, order.getBillToLastName());
            preparedStatement.setString(20, order.getShipToFirstName());
            preparedStatement.setString(21, order.getShipToLastName());
            preparedStatement.setString(22, order.getCreditCard());
            preparedStatement.setString(23, order.getExpiryDate());
            preparedStatement.setString(24, order.getCardType());
            preparedStatement.setString(25, order.getLocale());

            preparedStatement.executeUpdate();

//            // You may need to set LINENUM based on your business logic
//            int linenum = 1;
//
//            sql = "INSERT INTO orderstatus (orderid, linenum, timestamp, status) VALUES (?, ?, ?, ?)";
//            preparedStatement = connection.prepareStatement(sql);
//
//            preparedStatement.setInt(1, order.getOrderId());
//            preparedStatement.setInt(2, linenum);
//            preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
//            preparedStatement.setString(4, order.getStatus());
//
//            preparedStatement.executeUpdate();

            connection.commit();
            transactionSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null && !transactionSuccessful) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    DBUtil.closeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void insertOrderStatus(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean transactionSuccessful = false;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);

            // You may need to set LINENUM based on your business logic

            String sql = "INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, order.getOrderId());
            preparedStatement.setInt(2, order.getOrderId());
            preparedStatement.setTimestamp(3, new Timestamp(order.getOrderDate().getTime())); // Map orderDate to TIMESTAMP
            preparedStatement.setString(4, order.getStatus());

            preparedStatement.executeUpdate();

            connection.commit();
            transactionSuccessful = true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connection != null && !transactionSuccessful) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    DBUtil.closeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private Order resultSetToOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();

        order.setOrderId(resultSet.getInt("orderid"));
        order.setUsername(resultSet.getString("username"));
        order.setOrderDate(resultSet.getTimestamp("orderdate"));
        order.setShipAddress1(resultSet.getString("shipAddress1"));
        order.setShipAddress2(resultSet.getString("shipAddress2"));
        order.setShipCity(resultSet.getString("shipCity"));
        order.setShipState(resultSet.getString("shipState"));
        order.setShipZip(resultSet.getString("shipZip"));
        order.setShipCountry(resultSet.getString("shipCountry"));
        order.setBillAddress1(resultSet.getString("billAddress1"));
        order.setBillAddress2(resultSet.getString("billAddress2"));
        order.setBillCity(resultSet.getString("billCity"));
        order.setBillState(resultSet.getString("billState"));
        order.setBillZip(resultSet.getString("billZip"));
        order.setBillCountry(resultSet.getString("billCountry"));
        order.setCourier(resultSet.getString("courier"));
        order.setTotalPrice(resultSet.getBigDecimal("totalPrice"));
        order.setBillToFirstName(resultSet.getString("billToFirstName"));
        order.setBillToLastName(resultSet.getString("billToLastName"));
        order.setShipToFirstName(resultSet.getString("shipToFirstName"));
        order.setShipToLastName(resultSet.getString("shipToLastName"));
        order.setCreditCard(resultSet.getString("creditCard"));
        order.setExpiryDate(resultSet.getString("expiryDate"));
        order.setCardType(resultSet.getString("cardType"));
        order.setLocale(resultSet.getString("locale"));
        order.setStatus(resultSet.getString("status"));

        return order;
    }


    public static void main(String[] args) {
        //test insertOrder
        OrderDao orderDao = new OrderDaoImpl();
        Order order = new Order();
        order.setOrderId(1000);
        order.setUsername("j2ee");
        order.setOrderDate(new java.util.Date());
        order.setShipAddress1("address1");
        order.setShipAddress2("address2");
        order.setShipCity("city");
        order.setShipState("state");
        order.setShipZip("zip");
        order.setShipCountry("country");
        order.setBillAddress1("billaddress1");
        order.setBillAddress2("billaddress2");
        order.setBillCity("billcity");
        order.setBillState("billstate");
        order.setBillZip("billzip");
        order.setBillCountry("billcountry");
        order.setCourier("courier");
        order.setTotalPrice(new java.math.BigDecimal(100.0));
        order.setBillToFirstName("billfirstname");
        order.setBillToLastName("billlastname");
        order.setShipToFirstName("shipfirstname");
        order.setShipToLastName("shiplastname");
        order.setCreditCard("creditcard");
        order.setExpiryDate("12/12");
        order.setCardType("cardtype");
        order.setLocale("locale");
        order.setStatus("ok");
        orderDao.insertOrder(order);
        orderDao.insertOrderStatus(order);
        //创建另一个order对象
        Order order2 = new Order();
        order2.setOrderId(1001);
        order2.setUsername("j2ee");
        order2.setOrderDate(new java.util.Date());
        order2.setShipAddress1("address1");
        order2.setShipAddress2("address2");
        order2.setShipCity("city");
        order2.setShipState("state");
        order2.setShipZip("zip");
        order2.setShipCountry("country");
        order2.setBillAddress1("billaddress1");
        order2.setBillAddress2("billaddress2");
        order2.setBillCity("billcity");
        order2.setBillState("billstate");
        order2.setBillZip("billzip");
        order2.setBillCountry("billcountry");
        order2.setCourier("courier");
        order2.setTotalPrice(new java.math.BigDecimal(100.0));
        order2.setBillToFirstName("billfirstname");
        order2.setBillToLastName("billlastname");
        order2.setShipToFirstName("shipfirstname");
        order2.setShipToLastName("shiplastname");
        order2.setCreditCard("creditcard");
        order2.setExpiryDate("12/12");
        order2.setCardType("cardtype");
        order2.setLocale("locale");
        order2.setStatus("ok");
        orderDao.insertOrder(order2);
        orderDao.insertOrderStatus(order2);
//        test getOrdersByUsername
        List<Order> list = orderDao.getOrdersByUsername("j2ee");
        //测试getOrder
        Order order3 = orderDao.getOrder(1000);
        System.out.println(order3);
        for (Order order1 : list) {
            System.out.println(order1);
    }
}}
