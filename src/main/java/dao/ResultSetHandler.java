package dao;

import java.sql.ResultSet;

/**
 * @author 陌意随影
 * @create 2020-01-31 20:51
 * @desc 结果集的处理器接口
 **/
public interface ResultSetHandler<T> {
    T resultSetHandler(ResultSet resultSet);
}
