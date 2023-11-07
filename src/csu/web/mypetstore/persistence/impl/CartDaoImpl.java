package csu.web.mypetstore.persistence.impl;
import csu.web.mypetstore.domain.ItemBasic;
import csu.web.mypetstore.persistence.CartDao;
import csu.web.mypetstore.persistence.DBUtil;
import csu.web.mypetstore.domain.CartItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {

    @Override
    public void addToCart(String username, String itemId, int quantity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false); // 开始事务

            String sql = "INSERT INTO cart (username, itemId, number) VALUES (?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE number = number + ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, itemId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setInt(4, quantity);
            preparedStatement.executeUpdate();

            connection.commit(); // 提交事务
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback(); // 发生异常时回滚事务
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            // 处理数据库异常
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true); // 恢复自动提交模式
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void removeFromCart(String username, String itemId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false); // 开始事务

            String sql = "DELETE FROM cart WHERE username = ? AND itemId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, itemId);
            preparedStatement.executeUpdate();

            connection.commit(); // 提交事务
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback(); // 发生异常时回滚事务
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            // 处理数据库异常
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true); // 恢复自动提交模式
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void updateCartItemQuantity(String username, String itemId, int quantity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false); // 开始事务

            String sql = "UPDATE cart SET number = ? WHERE username = ? AND itemId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, itemId);
            preparedStatement.executeUpdate();

            connection.commit(); // 提交事务
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback(); // 发生异常时回滚事务
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            // 处理数据库异常
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true); // 恢复自动提交模式
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public List<ItemBasic> getCartItemsByUsername(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ItemBasic> items = new ArrayList<>();

        try {
            connection = DBUtil.getConnection();
            String sql = "SELECT * FROM cart WHERE username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String itemId = resultSet.getString(2);
                int quantity = resultSet.getInt(3);
                ItemBasic itemBasic = new ItemBasic();
                itemBasic.setItemId(itemId);
                itemBasic.setNumber(quantity);
                items.add(itemBasic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 处理数据库异常
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }

        return items;
    }

//    public static void main(String[] args) {
//        // 测试上述函数
//        CartDao cartDao = new CartDaoImpl();
//        String username = "test";
//        String itemId = "EST-1";
//        int quantity = 2;
//        cartDao.addToCart(username, itemId, quantity);
//        String secondId = "EST-2";
//        cartDao.addToCart(username, secondId, quantity);
//        int number = 3;
//        List<ItemBasic> items = cartDao.getCartItemsByUsername(username);
//        for (ItemBasic item : items) {
//            System.out.println(item.getItemId() + " " + item.getNumber());
//        }
//    }
//public static void main(String[] args) {
//    //removeFromCart
//    CartDao cartDao = new CartDaoImpl();
//    String username = "test";
//    String itemId = "EST-1";
//    cartDao.removeFromCart(username, itemId);
//
//}
}
