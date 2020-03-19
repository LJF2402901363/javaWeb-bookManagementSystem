package servletfilter;

import com.alibaba.fastjson.JSON;
import domain.Account;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static com.sun.mail.imap.SortTerm.CC;

/**
 * @TODO:登录过滤器
 * @author: 陌意随影
 * @date: 2020-02-09 18:58
 */
@WebFilter(
        filterName = "LoginFilter",
        urlPatterns ={"/borrowBookServlet","/removeBookServlet","/removeAccountServlet","/addNewAccountServlet","/booksystem-back-stage-home.jsp","/addNewBookServlet"}
        )
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest  request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        Account loginUser = (Account) request.getSession().getAttribute("loginUser");
         Map<String ,String > map= new HashMap<>();
        //判断是否为ajax请求，默认不是
        boolean isAjaxRequest = false;
        if(request.getHeader("x-requested-with") != null&& "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))){
            isAjaxRequest = true;
        }

        if (loginUser != null ) {
            //用户已经登录
            chain.doFilter(req, resp);
        }else{//用户未登录，登录过期
            if(isAjaxRequest){// 如果是ajax请求，则不是跳转页面，使用response返回结果
                response.setHeader("noAuthentication", "true");
                map.put("msg","请先登录");
                String jsonString = JSON.toJSONString(map);
                response.getWriter().write(jsonString);
                return;
            }else{
                response.sendRedirect(request.getContextPath()+"/booksystem-logined.jsp");
            }
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
