package servlet;

import com.alibaba.fastjson.JSON;
import service.AccountService;
import service.impl.AaccountServiceImpl;

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
 * @date: 2020-02-08 17:42
 */
@WebServlet("/removeAccountServlet")
public class RemoveAccountServlet extends HttpServlet {
    AccountService accountService = new AaccountServiceImpl();
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
            if (_id!=null||_id.trim().length()!=0){
                int id = Integer.parseInt(_id);
                fla = this.accountService.removeBookById(id);
                if (fla==false){
                    map.put("msg","删除用户出错！");
                    String jsonString = JSON.toJSONString(map);
                    response.getWriter().write(jsonString);
                    return;
                }
            }
        }
        if (fla){
            map.put("msg","删除用户成功！");
            String jsonString = JSON.toJSONString(map);
            response.getWriter().write(jsonString);
        }else {
            map.put("msg","删除用户出错！");
            String jsonString = JSON.toJSONString(map);
            response.getWriter().write(jsonString);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
