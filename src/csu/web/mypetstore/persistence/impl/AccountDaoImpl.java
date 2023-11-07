package csu.web.mypetstore.persistence.impl;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImpl implements AccountDao {

    private static final String GET_ACCOUNT_BY_USERNAME_AND_PASSWORD = "SELECT " +
            "SIGNON.USERNAME," +
            "ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS," +
            "ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2," +
            "ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE," +
            "PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId," +
            "PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption," +
            "BANNERDATA.BANNERNAME " +
            "FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA " +
            "WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ? " +
            "AND SIGNON.USERNAME = ACCOUNT.USERID " +
            "AND PROFILE.USERID = ACCOUNT.USERID " +
            "AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String SELECT_ACCOUNT_BY_USERNAME_SQL = "SELECT " +
            "SIGNON.USERNAME, " +
            "ACCOUNT.EMAIL, ACCOUNT.FIRSTNAME, ACCOUNT.LASTNAME, ACCOUNT.STATUS, " +
            "ACCOUNT.ADDR1 AS address1, ACCOUNT.ADDR2 AS address2, ACCOUNT.CITY, ACCOUNT.STATE, ACCOUNT.ZIP, " +
            "ACCOUNT.COUNTRY, ACCOUNT.PHONE, PROFILE.LANGPREF AS languagePreference, PROFILE.FAVCATEGORY AS favouriteCategoryId, " +
            "BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? " +
            "AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO ACCOUNT (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_PROFILE_SQL = "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID) VALUES (?, ?, ?)";

    private static final String INSERT_SIGNON_SQL = "INSERT INTO SIGNON (PASSWORD, USERNAME) VALUES (?, ?)";

    private static final String UPDATE_ACCOUNT_SQL = "UPDATE ACCOUNT SET EMAIL = ?, FIRSTNAME = ?, LASTNAME = ?, STATUS = ?, ADDR1 = ?, ADDR2 = ?, CITY = ?, STATE = ?, ZIP = ?, COUNTRY = ?, PHONE = ? WHERE USERID = ?";

    private static final String UPDATE_PROFILE_SQL = "UPDATE PROFILE SET LANGPREF = ?, FAVCATEGORY = ? WHERE USERID = ?";

    private static final String UPDATE_SIGNON_SQL = "UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";

    @Override
    public Account getAccountByUsername(String username) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Account account = null;

        try {
            String selectAccountSQL = "SELECT SIGNON.USERNAME, ACCOUNT.EMAIL, ACCOUNT.FIRSTNAME, ACCOUNT.LASTNAME, ACCOUNT.STATUS, " +
                    "ACCOUNT.ADDR1 AS address1, ACCOUNT.ADDR2 AS address2, ACCOUNT.CITY, ACCOUNT.STATE, ACCOUNT.ZIP, " +
                    "ACCOUNT.COUNTRY, ACCOUNT.PHONE, PROFILE.LANGPREF AS languagePreference, PROFILE.FAVCATEGORY AS favouriteCategoryId, " +
                    "BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? " +
                    "AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
            preparedStatement = connection.prepareStatement(selectAccountSQL);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                account = new Account();
                account.setUsername(resultSet.getString("USERNAME"));
                account.setEmail(resultSet.getString("EMAIL"));
                account.setFirstName(resultSet.getString("FIRSTNAME"));
                account.setLastName(resultSet.getString("LASTNAME"));
                account.setStatus(resultSet.getString("STATUS"));
                account.setAddress1(resultSet.getString("address1"));
                account.setAddress2(resultSet.getString("address2"));
                account.setCity(resultSet.getString("CITY"));
                account.setState(resultSet.getString("STATE"));
                account.setZip(resultSet.getString("ZIP"));
                account.setCountry(resultSet.getString("COUNTRY"));
                account.setPhone(resultSet.getString("PHONE"));
                account.setLanguagePreference(resultSet.getString("languagePreference"));
                account.setFavouriteCategoryId(resultSet.getString("favouriteCategoryId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }

        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account accountResult = null;
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(GET_ACCOUNT_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                accountResult = resultSetToAccount(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }

        return accountResult;
    }

    private Account resultSetToAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setUsername(resultSet.getString("USERNAME"));
        account.setEmail(resultSet.getString("EMAIL"));
        account.setFirstName(resultSet.getString("FIRSTNAME"));
        account.setLastName(resultSet.getString("LASTNAME"));
        account.setStatus(resultSet.getString("STATUS"));
        account.setAddress1(resultSet.getString("address1"));
        account.setAddress2(resultSet.getString("address2"));
        account.setCity(resultSet.getString("CITY"));
        account.setState(resultSet.getString("STATE"));
        account.setZip(resultSet.getString("ZIP"));
        account.setCountry(resultSet.getString("COUNTRY"));
        account.setPhone(resultSet.getString("PHONE"));
        account.setFavouriteCategoryId(resultSet.getString("favouriteCategoryId"));
        account.setLanguagePreference(resultSet.getString("languagePreference"));
        account.setListOption(resultSet.getInt("listOption") == 1);
        account.setBannerOption(resultSet.getInt("bannerOption") == 1);
        account.setBannerName(resultSet.getString("BANNERNAME"));
        return account;
    }

    @Override
    public void insertAccount(Account account) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_SQL);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void insertProfile(Account account) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_PROFILE_SQL);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void insertSignon(Account account) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(INSERT_SIGNON_SQL);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public void updateAccount(Account account) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_SQL);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
    }
    public void updateProfile(Account account) {
        Connection connection = DBUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFILE_SQL);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    public void updateSignon(Account account) {
        Connection connection = DBUtil.getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SIGNON_SQL);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

//    public static void main(String[] args) {
//        //测试insertAccount
//        Account account = new Account();
////        account.setUsername("rome");
////        account.setPassword("rome");
////        account.setFirstName("rome");
////        account.setLastName("rome");
////        account.setEmail("111“@qq.com");
////        account.setPhone("11111");
////        account.setAddress1("j2ee");
////        account.setAddress2("j2ee");
////        account.setCity("j2ee");
////        account.setState("ok");
////        account.setZip("j2ee");
////        account.setCountry("j2ee");
////        account.setLanguagePreference("j2ee");
////        account.setFavouriteCategoryId("j2ee");
////        account.setListOption(true);
////        account.setBannerOption(true);
////        account.setBannerName("j2ee");
////        account.setStatus("ok");
//        AccountDao accountDao = new AccountDaoImpl();
//        account.setUsername("j2ee");
//        account.setPassword("j2ee");
////        accountDao.insertAccount(account);
////        //测试insertProfile
////        accountDao.insertProfile(account);
////        //测试insertSignon
////        accountDao.insertSignon(account);
//
//        //测试getAccount
//        Account account1 = accountDao.getAccountByUsername("j2ee");
//        System.out.println(account1);
//        //测试getAccount
//        Account account3 = accountDao.getAccountByUsername("j2ee");
//        System.out.println(account3);
//
//
//    }

}
