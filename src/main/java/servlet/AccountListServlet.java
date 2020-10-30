package servlet;

import domain.Account;
import domain.PageBean;
import service.AccountService;
import service.impl.AaccountServiceImpl;
import util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @TODO:用户信息展示的servlet
 * @author: 陌意随影
 * @date: 2020-02-05 16:45
 */
@WebServlet("/accountListServlet")
public class AccountListServlet extends HttpServlet {
    AccountService accountService = new AaccountServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //获取每页的数量
        String _pageSize = request.getParameter("pageSize");
        //获取偏移量，即在数据库中开始查询的起始位置
        String _offset = request.getParameter("offset");
        if (_pageSize==null||_pageSize.trim().length()==0){
            return;
        }
        if (_offset==null||_offset.trim().length()==0){
            return;
        }
        int pageSize = Integer.parseInt(_pageSize);
        int offset = Integer.parseInt(_offset);
        //获取排序方式
        String tableOrder=request.getParameter("tableOrder");
        //获取排序名称
        String tableSortName=request.getParameter("tableSortName");
        String tableSearch = request.getParameter("tableSearch");
        //获取所有的请求参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        //条件参数集合
        Map<String,String> condition =null;
        PageBean<Account> bookPageBean=null;
        if (tableSearch!=null&&tableSearch.length()!=0){
            //列表本身的模糊查询，每行的每一列都进行模糊查询
            //搜索查询，对每一列都进行模糊查询
            bookPageBean= this.accountService.fuzzySearchABookByPage(offset,pageSize,tableSearch,tableSortName,tableOrder);
        }else{
            //根据条件进行模糊查询并显示分页数据
            //获取条件查询的key和value,要除去pageSize 和offset这两个键值对
            if (parameterMap!=null &&parameterMap.size()>2){
                condition =new HashMap<>();
                for (Map.Entry<String ,String[]> entry:parameterMap.entrySet()){
                    String key = entry.getKey();
                    if ("pageSize".equalsIgnoreCase(key)||"offset".equalsIgnoreCase(key)||"tableSearch".equalsIgnoreCase(key)||"tableOrder".equalsIgnoreCase(key)||"tableSortName".equalsIgnoreCase(key)){
                        continue;
                    }else {
                        condition.put(key,entry.getValue()[0]);
                    }
                }
            }
            //条件查询
            bookPageBean= this.accountService.findAccountByPage(offset,pageSize,condition,tableSortName,tableOrder);
        }
        String pageBeanToJsonStr = pageBeanToJsonStr(bookPageBean);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(pageBeanToJsonStr);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    /**
     * @Description :将pageBean手动转化为json字符串
     * @Date 20:08 2020/10/30 0030
     * @Param * @param pageBean ：需要转换的pageBean
     * @return java.lang.String
     **/
    private static String  pageBeanToJsonStr(PageBean<Account> pageBean){
        StringBuilder sb=new StringBuilder("{");
        sb.append("\"total\":").append(pageBean.getTotal()+",");
        sb.append("\"rows\":").append("[");
        List<Account> bookList=pageBean.getList();
        if (bookList==null||bookList.size()==0){
            //数据为空
            sb.append("]}");
            return sb.toString();
        }else {
            //数据不为空
            Account account = null;
            int len = bookList.size();
            //在table中的id
            int tableId=pageBean.getOffset()+1;
            for (int i =0;i < len;i++){
                account = bookList.get(i);
                sb.append("{");
                sb.append("\"tableid\":").append(tableId++).append(",");
                sb.append("\"id\":").append(account.getId()).append(",");
                sb.append("\"name\":").append("\""+account.getName()+"\"").append(",");
                sb.append("\"password\":").append("\""+account.getPassword()+"\"").append(",");
                String dateToStr = DateUtil.DateToStr(account.getCreateTime());
//                System.out.println(dateToStr);
                sb.append("\"createTime\":").append("\""+dateToStr+"\"").append(",");
                sb.append("\"status\":").append("\""+account.getStatus()+"\"").append(",");
                sb.append("\"type\":").append(account.getType()).append(",");
                sb.append("\"sex\":").append("\""+account.getSex()+"\"").append(",");
                sb.append("\"hobby\":").append("\""+account.getHobby()+"\"").append(",");
                sb.append("\"age\":").append(account.getAge()).append(",");
                sb.append("\"signature\":").append("\""+account.getSignature()+"\"");
                if (i==len-1){
                    sb.append("}");
                }else {
                    sb.append("},");
                }
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
