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
          pro.load( Thread.currentThread().getContextClassLoader().getResourceAsStream("properties/druid.properties"));
      } catch (IOException e) {
          e.printStackTrace();
      }

  }
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
public  static int upDate(String sql,Object... params){
      if (params==null||params.length==0) return 0;
    Connection connection=JdbcUtil.getConnection();
    try {
        connection.setAutoCommit(false);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    PreparedStatement preparedStatement=null;
    int result = 0;
    try {
        preparedStatement= connection.prepareStatement(sql);
        for (int i= 0;i < params.length;i++){
               preparedStatement.setObject(i+1,params[i]);
        }
        result = preparedStatement.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            connection.rollback();
            connection.setAutoCommit(true);
            JdbcUtil.close(connection,preparedStatement,null);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
      JdbcUtil.close(connection,preparedStatement,null);
      return result;
}
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
        preparedStatement= connection.prepareStatement(sql);
        for (int i= 0;i < params.length;i++){
            preparedStatement.setObject(i+1,params[i]);
        }
       resultSet = preparedStatement.executeQuery();
        connection.commit();
        connection.setAutoCommit(true);
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            connection.rollback();
            connection.setAutoCommit(true);
            JdbcUtil.close(connection,preparedStatement,resultSet);
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    T t = resultSetHandler.resultSetHandler(resultSet);
    JdbcUtil.close(connection,preparedStatement,resultSet);
    return t;
}
/**
 * @Description :通过所给的数据库表名来获取对应的表中所有的列名
 * @Date 17:10 2020/2/5 0005
 * @Param * @param tablename ：
 * @return java.lang.String[]
 **/
 public static String[] getTableColumnsName(String tablename){
     String sql="select* from  " + tablename;
     Connection connection = getConnection();
     Statement statement = null;
     ResultSet resultSet = null;
     try {
        statement = connection.createStatement();
         resultSet =statement.executeQuery(sql);
         ResultSetMetaData metaData = resultSet.getMetaData();
         if (metaData==null)  return null;
         int columnCount = metaData.getColumnCount(); //获取列的数量
         if (columnCount==0) return null;
         String[] columnNames = new String[columnCount];
         for (int i = 0; i < columnCount; i++) { //循环列
             columnNames[i]=metaData.getColumnName(i+1); //通过序号获取列名,起始值为1
         }
         close(connection,statement,resultSet);
         return columnNames;
     } catch (SQLException e) {
         e.printStackTrace();
         close(connection,statement,resultSet);
     }

      return null;
 }

}
