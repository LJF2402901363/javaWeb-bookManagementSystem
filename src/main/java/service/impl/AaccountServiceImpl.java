package service.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import domain.Account;
import domain.PageBean;
import service.AccountService;

import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-02-01 13:52
 * @desc  用户业务逻辑类的实现类
 **/
public class AaccountServiceImpl implements AccountService {
    AccountDao dao = new AccountDaoImpl();
    @Override
    public Account login(Account account) {
        return dao.login(account);
    }

    @Override
    public PageBean<Account> findAccountByPage(int offset, int pageSize, Map<String, String> condition, String tableSortName, String tableOrder) {
        return dao.findAccountByPage(offset,pageSize,condition, tableSortName, tableOrder);
    }

    @Override
    public boolean saveAccount(Account user) {
        int result = dao.save(user);

        return result==1;
    }

    @Override
    public Account findAccountByName(String name) {
        return dao.findAccountByName(name);
    }

    @Override
    public PageBean<Account> fuzzySearchABookByPage(int offset, int pageSize, String fuzzySearchContent, String tableSortName, String tableOrder) {
        return dao.fuzzySearchABookByPage(offset,pageSize,fuzzySearchContent,tableSortName,tableOrder);
    }

    @Override
    public boolean removeBookById(int id) {
        int result = this.dao.remove(id);
        return result==1;
    }

    @Override
    public boolean updateAccount(Account account) {
        int result = this.dao.upDate(account);
        return result==1;
    }

    @Override
    public boolean addNewAccount(Account account) {
        int result = this.dao.save(account);
        return result==1;
}
}
