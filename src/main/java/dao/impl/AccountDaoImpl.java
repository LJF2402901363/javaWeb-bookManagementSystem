package dao.impl;
import dao.AccountDao;
import dao.ResultSetHandler;
import domain.Account;
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
 * @create 2020-01-31 20:58
 * @desc AccountDao的实现类
 **/
public class AccountDaoImpl implements AccountDao {
    @Override
    public int save(Account account) {
        //type | sex  | hobby | signature
        String sql="insert into "+ConfigContant.ACCOUNT_TABLE+"(name,password, createTime, status,type,sex,hobby,signature,age) values(?,?,?,?,?,?,?,?,?)";
        int result = JdbcUtil.upDate(sql, account.getName(), account.getPassword(),
                account.getCreateTime(), account.getStatus(),
                account.getType(), account.getSex(),
                account.getHobby(), account.getSignature(),account.getAge()
        );
        return result;
    }

    @Override
    public int upDate(Account account) {
        // password | createTime          | status | type | sex  | hobby | signature | age  |
        String sql="update  "+ConfigContant.ACCOUNT_TABLE+"  set name=?,password=?,createTime=?,status=?,type=?,sex=?,hobby=?,signature=?,age=? where id=?";
        int result = JdbcUtil.upDate(sql,
                account.getName(), account.getPassword(),
                account.getCreateTime(), account.getStatus(),
                account.getType(), account.getSex(), account.getHobby(),
                account.getSignature(), account.getAge(),
                account.getId()
        );
        return result;
    }

    @Override
    public int remove(int id) {

        String sql="delete from "+ConfigContant.ACCOUNT_TABLE+" where id=?";
        int result = JdbcUtil.upDate(sql, id);
        return result;
    }

    @Override
    public Account get(int id) {
        return null;
    }

    @Override
    public List<Account> getAll() {
             String  sql="select *from "+ConfigContant.ACCOUNT_TABLE;
        List<Account> list = JdbcUtil.query(sql, new AccountResultSetHandler());
        return list;
    }

    @Override
    public Account login(Account account) {
        String sql="select *from "+ConfigContant.ACCOUNT_TABLE+" where  binary name=? and  binary password=?";
        List<Account> query = JdbcUtil.query(sql, new AccountResultSetHandler(), account.getName(), account.getPassword());
         if (query.size()!=1){
             return  null;
         }
        return query.get(0);
    }

    @Override
    public PageBean<Account> findAccountByPage(int offset, int pageSize, Map<String, String> condition, String tableSortName, String tableOrder) {
        //获取总记录数的sql语句
        String totalSql ="select count(*) from "+ConfigContant.ACCOUNT_TABLE;
        //获取总记录数
        int total=this.getCount( totalSql);
        //计算当前页面
        int currPage = 0;
        if (pageSize!=0){
            //计算当前页面
            currPage=offset/pageSize+1;
        }
        //sql语句
        String sql="select *from "+ConfigContant.ACCOUNT_TABLE+" where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        if (condition.size()>=0){
            for (Map.Entry<String,String> entry:condition.entrySet()){
                sb.append(" and ").append(entry.getKey()).append(" like \"%").append(entry.getValue()).append("%\"");
            }
        }
        sb.append(" limit ?,?");
        List<Account> bookList = JdbcUtil.query(sb.toString(), new AccountResultSetHandler(), offset, pageSize);
        PageBean<Account> pageBean=new PageBean<Account>();
        pageBean.setPageSize(pageSize);
        pageBean.setOffset(offset);
        pageBean.setCurrPage(currPage);
        pageBean.setTotal(total);
        pageBean.setList(bookList);
        return pageBean;
    }

    @Override
    public Account findAccountByName(String name) {
        String sql="select *from "+ConfigContant.ACCOUNT_TABLE+" where  binary name=? ";
        List<Account> query = JdbcUtil.query(sql, new AccountResultSetHandler(),name);
        if (query.size()!=1){
            return  null;
        }
        return query.get(0);
    }

    @Override
    public PageBean<Account> fuzzySearchABookByPage(int offset, int pageSize, String fuzzySearchContent, String tableSortName, String tableOrder) {
        StringBuilder sb=new StringBuilder();
        //获取account数据库的列名
        String[] accounts = JdbcUtil.getTableColumnsName(ConfigContant.ACCOUNT_TABLE);
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
        String totalSql="select count(*)from "+ConfigContant.ACCOUNT_TABLE+" where "+sb.toString();
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
        String sql="select *from "+ConfigContant.ACCOUNT_TABLE+" where "+sb.toString();
        List<Account> accountList = JdbcUtil.query(sql, new AccountResultSetHandler(), offset, pageSize);
        //计算当前页面
        int currPage = 0;
        if (pageSize!=0){
            //计算当前页面
            currPage=offset/pageSize+1;
        }
        PageBean<Account> pageBean=new PageBean<>();
        pageBean.setPageSize(pageSize);
        pageBean.setOffset(offset);
        pageBean.setCurrPage(currPage);
        pageBean.setTotal(total);
        pageBean.setList(accountList);
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
        return JdbcUtil.query(sql,resultSetHandler);
    }
}
class AccountResultSetHandler implements ResultSetHandler<List<Account>>{

    @Override
    public List<Account> resultSetHandler(ResultSet resultSet) {
        List<Account> list = new ArrayList<>();
        EncapsulateJavaBean.encapsulateJavaBean(list,Account.class,resultSet);
        return list;
    }
}