package dao.impl;

import org.junit.Test;

/**
 * @author 陌意随影
 * @create 2020-01-31 22:17
 * @desc 测试类
 **/
public class BookDaoImplTest {
    @Test
    public void testGetAll(){
        BookDaoImpl accountDao=new BookDaoImpl();
        System.out.println(accountDao.getAll());
    }
}
