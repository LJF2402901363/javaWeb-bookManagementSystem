package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import dao.ResultSetHandler;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author 陌意随影
 * @create 2020-01-31 17:41
 * @desc msql数据库连接的工具类
 **/
public class JdbcUtil {
  private static DataSource dataSource=null;
  private static Properties pro= new Properties();
  static {
      try {
          //加载druid数据库连接池配置文件
          pro.load( Thread.currentThread().getContextClassLoader().getResourceAsStream("properties/druid.properties"));
      } catch (IOException e) {
          e.printStackTrace();
      }

  }
  /**
   * @Description : 获取druid数据库连接池
   * @Date 20:04 2020/10/30 0030
   * @Param * @param  ：
   * @return javax.sql.DataSource
   **/
  public DataSource getDataSource(){
      try {
          dataSource = DruidDataSourceFactory.createDataSource(pro);
      } catch (Exception e) {
          e.printStackTrace();
      }
   return dataSource;
  }
public static  Connection getConnection(){
    Connection connection = null;
    try {
        connection = DruidDataSourceFactory.createDataSource(pro).getConnection();
    } catch (Exception e) {
        e.printStackTrace();
    }
  return  connection;
}
/**
 * @Description :关闭资源
 * @Date 20:04 2020/10/30 0030
 * @Param * @param connection  数据库连接对象
 * @param statement  预处理对象
 * @param resultSet ：结果集
 * @return void
 **/
public  static void close(Connection connection, Statement statement, ResultSet resultSet){
      if (resultSet!=null){
              try {
                  resultSet.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
      }
    if (statement!=null){
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    if (connection!=null){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
/**
 * @Description :执行sql的更新语句。
 * @Date 20:01 2020/10/30 0030
 * @Param * @param sql 需要执行的sql语句
 * @param params ：sql预处理占位符中的实际参数数组
 * @return int 返回受影响的行数
 **/
public  static int upDate(String sql,Object... params){
      if (params==null||params.length==0) return 0;
      //获取连接对象
    Connection connection=JdbcUtil.getConnection();
    try {
        //设置禁止自动提交
        connection.setAutoCommit(false);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    PreparedStatement preparedStatement=null;
    int result = 0;
    try {
        //创建预处理对象
        preparedStatement= connection.prepareStatement(sql);
        //使用实际参数替换占位符
        for (int i= 0;i < params.length;i++){
               preparedStatement.setObject(i+1,params[i]);
        }
        //执行sql语句
        result = preparedStatement.executeUpdate();
        //提交更新
        connection.commit();
        //设置自动提交
        connection.setAutoCommit(true);
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            //事务回滚
            connection.rollback();
            //设置自动提交
            connection.setAutoCommit(true);
            //关闭资源
            JdbcUtil.close(connection,preparedStatement,null);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //关闭资源
      JdbcUtil.close(connection,preparedStatement,null);
      return result;
}
/**
 * @Description :返回sql查询后的结果
 * @Date 19:55 2020/10/30 0030
 * @Param * @param sql  需要执行的sql语句，包含有占位符
 * @param resultSetHandler  结果集的处理
 * @param params ： 占位符对应的值
 * @return T
 **/
public static <T> T query(String sql, ResultSetHandler<T> resultSetHandler, Object... params){
    if (params==null) return null;
    Connection connection=JdbcUtil.getConnection();
    try {
        connection.setAutoCommit(false);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    PreparedStatement preparedStatement=null;
    ResultSet resultSet=null;
    try {
        //创建预处理对象
        preparedStatement= connection.prepareStatement(sql);
        //使用实际参数替换占位符
        for (int i= 0;i < params.length;i++){
            preparedStatement.setObject(i+1,params[i]);
        }
        //执行查询
       resultSet = preparedStatement.executeQuery();
        //提交
        connection.commit();
        //重新设置自动提交
        connection.setAutoCommit(true);
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            //事务回滚
            connection.rollback();
            //重新设置自动提交
            connection.setAutoCommit(true);
            //关闭资源
            JdbcUtil.close(connection,preparedStatement,resultSet);
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //将结果集传入到处理接口中进行处理
    T t = resultSetHandler.resultSetHandler(resultSet);
    //关闭资源
    JdbcUtil.close(connection,preparedStatement,resultSet);
    return t;
}
/**
 * @Description :通过所给的数据库表名来获取对应的表中所有的列名
 * @Date 17:10 2020/2/5 0005
 * @Param * @param tablename ：数据库表的表名
 * @return java.lang.String[]
 **/
 public static String[] getTableColumnsName(String tablename){
     String sql="select* from  " + tablename;
     Connection connection = getConnection();
     Statement statement = null;
     ResultSet resultSet = null;
     String[] columnNames = null;
     try {
         //创建预处理对象
        statement = connection.createStatement();
        //查询sql语句并返回结果集
         resultSet =statement.executeQuery(sql);
         //获取结果集中的元数据
         ResultSetMetaData metaData = resultSet.getMetaData();
         if (metaData==null)  return null;
         //获取列的数量
         int columnCount = metaData.getColumnCount();
         if (columnCount==0) return null;
         columnNames = new String[columnCount];
         //循环列
         for (int i = 0; i < columnCount; i++) {
             //通过序号获取列名,起始值为1
             columnNames[i]=metaData.getColumnName(i+1);
         }

     } catch (SQLException e) {
         e.printStackTrace();
     }finally {
         //关闭资源
         close(connection,statement,resultSet);
         return columnNames;
     }


 }

}
