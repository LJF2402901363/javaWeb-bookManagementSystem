package servlet;

import com.alibaba.fastjson.JSON;
import domain.Account;
import domain.Book;
import domain.BorrowBook;
import service.AccountService;
import service.BookService;
import service.BorrowBookService;
import service.impl.AaccountServiceImpl;
import service.impl.BookServiceImpl;
import service.impl.BorrowBookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @TODO:图书借阅的servlet
 * @author: 陌意随影
 * @date: 2020-02-09 19:02
 */
@WebServlet("/borrowBookServlet")
public class BorrowBookServlet extends HttpServlet {
  BookService bookService = new BookServiceImpl();
  AccountService accountService= new AaccountServiceImpl();
  BorrowBookService borrowBookService = new BorrowBookServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        //获取借阅的书籍id
        String[] bookids = request.getParameterValues("bookid");
        //获取已经登录的用户
        Account loginUser = (Account) request.getSession().getAttribute("loginUser");
        Map<String,String > responseMap=new HashMap<>();
        if (loginUser==null){
            String jsonString = JSON.toJSONString(responseMap);
            response.getWriter().write(jsonString);
            return;
        }
        if (bookids==null||bookids.length==0){
            responseMap.put("bookborrow_msg","借阅出错");
            String jsonString = JSON.toJSONString(responseMap);
            response.getWriter().write(jsonString);
            return;
        }
        for (String _bookid:bookids){
            int bookid = Integer.parseInt(_bookid);
            Book book =bookService.findBookById(bookid);
            if (book.getStatus()!=Book.STATUS_BORROWED){
                //图书还未被借阅
                //创建借书记录
                BorrowBook borrowBook = new BorrowBook();
                //获取借书用户的id
                int loginUserId = loginUser.getId();
                borrowBook.setAccountId(loginUserId);
                borrowBook.setBookId(bookid);
                borrowBook.setBorrowTime(new Date());
                //将该记录存储到数据库
               boolean fla = borrowBookService.saveBorrowBook(borrowBook);
               if (fla){
                   responseMap.put("msg", "图书借阅成功");
                   //设置该图书已被借阅
                   book.setStatus(Book.STATUS_BORROWED);
                   //更新该书数据库的状态
                   this.bookService.updateBook(book);
                   String jsonString = JSON.toJSONString(responseMap);
                   response.getWriter().write(jsonString);
               }else{
                   responseMap.put("msg", "图书借阅失败");
                   String jsonString = JSON.toJSONString(responseMap);
                   response.getWriter().write(jsonString);
               }
            }else{
                //图书还已经被借阅
                responseMap.put("msg","图书已被借阅");
                String jsonString = JSON.toJSONString(responseMap);
                response.getWriter().write(jsonString);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
