package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.LogDao;
import csu.web.mypetstore.persistence.impl.AccountDaoImpl;
import csu.web.mypetstore.persistence.impl.LogDaoImpl;
import csu.web.mypetstore.domain.Record;
import java.util.List;

public class AccountService {
    private AccountDao accountDao = new AccountDaoImpl();
    private LogDao logDao = new LogDaoImpl();
    public AccountService(){
        this.accountDao = new AccountDaoImpl();
    }

    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountDao.getAccountByUsernameAndPassword(account);
    }
    public Account getAccount(String username) {
        return accountDao.getAccountByUsername(username);
    }

    public void insertAccount(Account account) {
        accountDao.insertAccount(account);
        accountDao.insertProfile(account);
        accountDao.insertSignon(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
        accountDao.updateProfile(account);

        if (account.getPassword() != null && account.getPassword().length() > 0) {
            accountDao.updateSignon(account);
        }
    }

    public List<Record> searchRecord(String username){
        return logDao.searchRecord(username);
    }
}
