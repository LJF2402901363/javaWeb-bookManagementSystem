package service.impl;

import dao.BorrowBookDao;
import dao.impl.BorrowBookDaoImpl;
import domain.BorrowBook;
import service.BorrowBookService;

/**
 * @author 陌意随影
 * @create 2020-02-09 20:37
 * @desc 借书业务逻辑的实现
 **/
public class BorrowBookServiceImpl implements BorrowBookService {
    BorrowBookDao borrowBookDao =  new BorrowBookDaoImpl();
    @Override
    public boolean saveBorrowBook(BorrowBook borrowBook) {
        int result = borrowBookDao.save(borrowBook);
        return result==1;
    }
}
