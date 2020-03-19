package dao.impl;

import dao.BorrowBookDao;
import dao.ResultSetHandler;
import domain.Book;
import domain.BorrowBook;
import util.ConfigContant;
import util.EncapsulateJavaBean;
import util.JdbcUtil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 陌意随影
 * @create 2020-02-09 20:00
 * @desc 借书dao的实现类
 **/
public class BorrowBookDaoImpl implements BorrowBookDao {
    @Override
    public int save(BorrowBook borrowBook) {
        String   sql ="insert into "+ConfigContant.BORROWBOOK_TABLE+"  (accountid,bookid,borrowtime,returntime) values(?,?,?,?)";
        int result = JdbcUtil.upDate(sql,
                borrowBook.getAccountId(), borrowBook.getBookId(),
                borrowBook.getBorrowTime(), borrowBook.getReturnTime());
        return result;
    }

    @Override
    public int upDate(BorrowBook borrowBook) {
        return 0;
    }

    @Override
    public int remove(int id) {

        return 0;
    }

    @Override
    public BorrowBook get(int id) {
        return null;
    }

    @Override
    public List<BorrowBook> getAll() {
        String sql="select *from " + ConfigContant.BORROWBOOK_TABLE;
        List<BorrowBook> borrowBookList = JdbcUtil.query(sql, new BorrowBookResultSetHandler());
        return borrowBookList;
    }

    @Override
    public int getCount(String sql) {
        return 0;
    }
}
class BorrowBookResultSetHandler implements ResultSetHandler<List<BorrowBook>> {

    @Override
    public List<BorrowBook> resultSetHandler(ResultSet resultSet) {
        List<BorrowBook> list = new ArrayList<BorrowBook>();
        EncapsulateJavaBean.encapsulateJavaBean(list,BorrowBook.class,resultSet);
        return list;
    }
}