package dao.impl;

import dao.BookDao;
import dao.ResultSetHandler;
import domain.Book;
import domain.PageBean;
import util.ConfigContant;
import util.EncapsulateJavaBean;
import util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-01-31 20:56
 * @desc BookDao的实现类
 **/
public class BookDaoImpl implements BookDao {
    @Override
    public int save(Book book) {
        String sql = "insert into "+ ConfigContant.BOOK_TABLE+"(name,author,price,type,status,description,sbn,createTime) values(?,?,?,?,?,?,?,?)";
        int result = JdbcUtil.upDate(sql,
                book.getName(), book.getAuthor(),
                book.getPrice(), book.getType(),
                book.getStatus(), book.getDescription(),
                book.getSbn(), book.getCreateTime()
        );
        return result;
    }
// id | name         | author | price | type | status | description | sbn           | createTime |
    @Override
    public int upDate(Book book) {
        String sql="update  "+ConfigContant.BOOK_TABLE+" set name=?, author=?,price=?,type=?,status=?,description=?,sbn=?,createTime =? where id=?";
        int result = JdbcUtil.upDate(sql, book.getName(), book.getAuthor(),
                book.getPrice(), book.getType(),
                book.getStatus(), book.getDescription(),
                book.getSbn(), book.getCreateTime(),
                book.getId()
        );
        return result;
    }

    @Override
    public int remove(int id) {
        String sql="delete from  "+ConfigContant.BOOK_TABLE+"  where id=?";
        int result = JdbcUtil.upDate(sql, id);
        return result;
    }

    @Override
    public Book get(int id) {
        String  sql="select *from " +ConfigContant.BOOK_TABLE+" where id=?";
        List<Book> list= JdbcUtil.query(sql, new BookResultSetHandler(),id);
        if (list==null ||list.size()!=1){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Book> getAll() {
        String  sql="select *from book";
        List<Book> list= JdbcUtil.query(sql, new BookResultSetHandler());
        return list;
    }

    @Override
    public PageBean<Book> findBookByPage(int offset, int pageSize, Map<String, String> condition, String tableSortName, String tableOrder) {
        //获取总记录数的sql语句
        String totalSql ="select count(*) from "+ConfigContant.BOOK_TABLE;
        //获取总记录数
        int total=this.getCount( totalSql);
        //计算当前页面
        int currPage = 0;
        if (pageSize!=0){
            //计算当前页面
            currPage=offset/pageSize+1;
        }
        //sql语句
        String sql="select *from book where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        if (condition!=null&&condition.size()>=0){
            for (Map.Entry<String,String> entry:condition.entrySet()){
                String value = entry.getValue();
                if (value!=null && value.trim().length()!=0){
                    sb.append(" and ").append(entry.getKey()).append(" like \"%").append(entry.getValue()).append("%\"");
                }
            }
        }
        if (tableSortName==null||tableSortName.trim().length()==0){
            sb.append(" limit ?,?");
        }else {
            if (tableOrder==null||tableOrder.trim().length()==0){
                //默认的排序
                tableOrder="asc";
            }
            sb.append(" order by ").append(tableSortName +"  ").append(tableOrder+" limit ?,?");
        }

        List<Book> bookList = JdbcUtil.query(sb.toString(), new BookResultSetHandler(), offset, pageSize);
        PageBean<Book> pageBean=new PageBean<>();
        pageBean.setPageSize(pageSize);
        pageBean.setOffset(offset);
        pageBean.setCurrPage(currPage);
        pageBean.setTotal(total);
        pageBean.setList(bookList);
        return pageBean;
    }

    @Override
    public int getCount(String sql) {

        ResultSetHandler<Integer> resultSetHandler= new ResultSetHandler<Integer>() {
            @Override
            public Integer resultSetHandler(ResultSet resultSet) {
                try {
                    if (resultSet.next()){
                        return resultSet.getInt(1);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        };
        return JdbcUtil.query(sql, resultSetHandler);
    }

    @Override
    public PageBean<Book> fuzzySearchABookByPage(int offset, int pageSize, String fuzzySearchContent, String tableSortName, String tableOrder) {
        StringBuilder sb=new StringBuilder();
        //获取account数据库的列名
        String[] accounts = JdbcUtil.getTableColumnsName(ConfigContant.BOOK_TABLE);
        //获取模糊查询的关键语句
        if (accounts!=null) {
            int len = accounts.length;
            if (fuzzySearchContent!=null &&fuzzySearchContent.trim().length()!=0) {

                for (int i = 0; i < len; i++) {
                    //要排除id模糊查询，id不参与查询
                    if ("id".equalsIgnoreCase(accounts[i])){
                        continue;
                    }
                    if (i == len - 1) {
                        sb.append(accounts[i]).append("  like ").append("\"%" + fuzzySearchContent + "%\"");
                    } else {
                        sb.append(accounts[i]).append("  like ").append("\"%" + fuzzySearchContent + "%\"").append(" or ");
                    }
                }
            }
        }

        String totalSql="select count(*)from "+ConfigContant.BOOK_TABLE+" where "+sb.toString();
        //获取总记录数
        int total=this.getCount(totalSql);

        //计算当前页面
        if (tableSortName==null||tableSortName.trim().length()==0){
            sb.append(" limit ?,?");
        }else {
            if (tableOrder==null||tableOrder.trim().length()==0){
                //默认的排序
                tableOrder="asc";
            }
            sb.append(" order by ").append(tableSortName +"  ").append(tableOrder+" limit ?,?");
        }
        //模糊查询的sql语句
        String sql="select *from book where "+sb.toString();
        List<Book> bookList = JdbcUtil.query(sql, new BookResultSetHandler(), offset, pageSize);
        //计算当前页面
        int currPage = 0;
        if (pageSize!=0){
            //计算当前页面
            currPage=offset/pageSize+1;
        }
        PageBean<Book> pageBean=new PageBean<>();
        pageBean.setPageSize(pageSize);
        pageBean.setOffset(offset);
        pageBean.setCurrPage(currPage);
        pageBean.setTotal(total);
        pageBean.setList(bookList);
        return pageBean;
    }
}
class BookResultSetHandler implements ResultSetHandler<List<Book>> {

    @Override
    public List<Book> resultSetHandler(ResultSet resultSet) {
        List<Book> list = new ArrayList<>();
        EncapsulateJavaBean.encapsulateJavaBean(list,Book.class,resultSet);
        return list;
    }
}