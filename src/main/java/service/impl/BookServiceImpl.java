package service.impl;

import dao.BookDao;
import dao.impl.BookDaoImpl;
import domain.Book;
import domain.PageBean;
import service.BookService;

import java.util.Map;

/**
 * @author 陌意随影
 * @create 2020-02-02 12:58
 * @desc
 **/
public class BookServiceImpl implements BookService {
    BookDao  bookDao= new BookDaoImpl();
    @Override
    public PageBean findABookByPage(int offset, int pageSize, Map<String, String> condition, String tableSortName, String tableOrder) {
        return this.bookDao.findBookByPage(offset,pageSize,condition,tableSortName,tableOrder);
    }

    @Override
    public PageBean<Book> fuzzySearchABookByPage(int offset, int pageSize, String fuzzySearchContent, String tableSortName, String tableOrder) {
        return this.bookDao.fuzzySearchABookByPage(offset,pageSize,fuzzySearchContent,tableSortName,tableOrder);
    }

    @Override
    public boolean removeBookById(int id) {
        int result = this.bookDao.remove(id);
        return result == 1;
    }

    @Override
    public boolean updateBook(Book book) {
        int result = this.bookDao.upDate(book);
        return result == 1;
    }

    @Override
    public Book findBookById(int bookid) {
        return (Book) this.bookDao.get(bookid);
    }

    @Override
    public boolean addNewBook(Book book) {
        int result = this.bookDao.save(book);
        return result == 1;
    }
}
