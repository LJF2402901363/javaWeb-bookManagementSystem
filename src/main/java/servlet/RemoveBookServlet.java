package servlet;

import com.alibaba.fastjson.JSON;
import service.BookService;
import service.impl.BookServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @TODO:删除图书的servlet
 * @author: 陌意随影
 * @date: 2020-02-05 21:41
 */
@WebServlet("/removeBookServlet")
public class RemoveBookServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取要删除数据的id
        String[] _ids = request.getParameterValues("id");
        Map<String ,String > map= new HashMap<>();
        if (_ids==null||_ids.length==0) {
            map.put("msg","尚未选择删除书籍！");
            String jsonString = JSON.toJSONString(map);
            response.getWriter().write(jsonString);
            return;
        }
        boolean fla=false;
        for (String _id:_ids){
            if (_id!=null&&_id.trim().length()!=0){
                int id = Integer.parseInt(_id);
               fla = this.bookService.removeBookById(id);
               if (fla==false){
                   map.put("msg","删除出错！");
                   String jsonString = JSON.toJSONString(map);
                   response.getWriter().write(jsonString);
                   return;
               }
            }
        }
        if (fla){
            map.put("msg","删除成功！");
            String jsonString = JSON.toJSONString(map);
            response.getWriter().write(jsonString);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
