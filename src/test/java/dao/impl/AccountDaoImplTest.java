package dao.impl;

import org.junit.Test;

/**
 * @author 陌意随影
 * @create 2020-01-31 21:51
 * @desc 测试类
 **/
public class AccountDaoImplTest {
    @Test
    public void testGetAll(){
        AccountDaoImpl accountDao=new AccountDaoImpl();
        System.out.println(accountDao.getAll());
    }

}
