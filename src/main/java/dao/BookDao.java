package dao;
/**
 * @TODO:
 * @author: 陌意随影
 * @date: 2020-01-31 17:34
 */


import domain.Book;
import domain.PageBean;

import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-01-31 17:34
 * @desc 书籍的dao
 **/
public interface BookDao extends DAO<Book> {
    /**
     * @Description :  通过数据的起始位置，页面大小，查询条件，排序的名称以及排序方式来进行获得排序的对象
     * @Date 12:58 2020/2/2 0002
     * @Param * @param offset  数据的起始位置
     * @param pageSize  页面的大小
     * @param condition ：  查询的条件
     * @param tableSortName 排序的列名
     * @param tableOrder 排序的方式 默认为 asc排序
     * @return domain.PageBean<domain.Book>
     **/
    PageBean<Book> findBookByPage(int offset, int pageSize, Map<String, String> condition, String tableSortName, String tableOrder);
/**
 * @Description : 获取指定查询语句的总记录数
 * @Date 13:02 2020/2/2 0002
 * @Param * @param  ：
 * @return int
 **/
    int getCount(String sql);
    /**
     * @Description : 通过数据的起始位置，页面大小，搜索的条件，排序的名称以及排序方式来进行获得排序的对象
     * @Date 18:59 2020/2/2 0002
     * @Param * @param offset
     * @param pageSize   页面的大小
     * @param fuzzySearchContent  模糊搜索的内容
     * @param tableSortName  排序的列名
     * @param tableOrder ：排序的方式
     * @return domain.PageBean<domain.Book>
     **/
    PageBean<Book> fuzzySearchABookByPage(int offset, int pageSize, String fuzzySearchContent, String tableSortName, String tableOrder);
}

