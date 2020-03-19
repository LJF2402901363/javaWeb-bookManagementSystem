package service;/**
 * @TODO:
 * @author: 陌意随影
 * @date: 2020-02-09 20:36
 */

import domain.BorrowBook;

/**
 * @author 陌意随影
 * @create 2020-02-09 20:36
 * @desc 借书的业务逻辑接口
 **/
public interface BorrowBookService {
/**
 * @Description :将借书记录存储到数据库中去
 * @Date 20:38 2020/2/9 0009
 * @Param * @param borrowBook ：
 * @return boolean
 **/
    boolean saveBorrowBook(BorrowBook borrowBook);
}
