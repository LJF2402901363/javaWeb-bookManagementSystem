package servlet;

import com.alibaba.fastjson.JSON;
import domain.Book;
import service.BookService;
import service.impl.BookServiceImpl;
import util.EncapsulateJavaBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @TODO:
 * @author: 陌意随影
 * @date: 2020-02-06 22:41
 */
@WebServlet("/updateBookServlet")
public class UpdateBookServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=UTF-8");
        //获取所有的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> map=new HashMap<>();
        //响应的参数集合
        Map<String, String>   responseMap=new HashMap<String,String>();
        if (parameterMap==null||parameterMap.size()==0){
            responseMap.put("msg","更新成功！");
            responseMap.put("erro","更新失败！");
            String jsonString = JSON.toJSONString(responseMap);
            response.getWriter().write(jsonString);
            return;
        }
        for (Map.Entry<String, String[]> entry:parameterMap.entrySet()){
            String key = entry.getKey();
            String[] values = entry.getValue();
            if (values!=null||values.length!=0){
                String value = values[0];
                map.put(key,value);
            }
        }
        //获取封装的对象
        Book book = EncapsulateJavaBean.encapsulateJavaBean(Book.class, map);
        //更新书籍
        boolean fla = this.bookService.updateBook(book);
       if (fla){
           //更新成功
           responseMap.put("msg","更新成功！");
           responseMap.put("erro","更新失败！");
           String jsonString = JSON.toJSONString(responseMap);
           response.getWriter().write(jsonString);
       }else{
           //更新失败
           responseMap.put("msg","更新失败！");
           responseMap.put("erro","更新失败！");
           String jsonString = JSON.toJSONString(responseMap);
           response.getWriter().write(jsonString);
       }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
