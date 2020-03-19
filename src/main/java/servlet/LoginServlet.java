package servlet;

import domain.Account;
import service.AccountService;
import service.impl.AaccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @TODO:登录验证的servlet
 * @author: 陌意随影
 * @date: 2020-02-01 13:38
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    AccountService accountService = new AaccountServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   //设置请求编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;utf-8");
        //获取用户输入的验证码
       String login_checkcode = (String) request.getParameter("checkcode");
       request.setAttribute("checkcode",login_checkcode);
        //获取用户名和密码
        String name= (String) request.getParameter("name");
        String password= (String) request.getParameter("password");
        //设置回显的数据
        request.setAttribute("name",name);
        request.setAttribute("password",password);
        if (login_checkcode==null||login_checkcode.length()==0){
            request.getSession().removeAttribute("checkcode");
            //验证为空
            request.setAttribute("checkcode_err","验证码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else{
            //验证码不为空
            request.setAttribute("checkcode",login_checkcode);
            //获取网页自动生成的验证码
            String checkcode = (String) request.getSession().getAttribute("checkcode");
            request.getSession().removeAttribute("checkcode");
            //比较用户输入的验证码和网页生成的验证码
            if (checkcode.equalsIgnoreCase(login_checkcode)){
                //用户输入的验证码匹配
                request.setAttribute("checkcode_err","验证码正确");
                //封装只有用户名和密码的账号
                Account user=new Account();
                user.setName(name);
                user.setPassword(password);
                Account loginUser = accountService.login(user);
                if (loginUser==null){
                    //该用户不存在
                    request.setAttribute("login_err","用户名或密码错误");
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                }else {
                    //用户存在
                  request.getSession().setAttribute("loginUser",loginUser);
                  if (loginUser.getType()==Account.TYPE_USER){
                      response.sendRedirect(request.getContextPath()+"/booksystem-logined.jsp");
                  }else if(loginUser.getType()==Account.TYPE_ADMINISTRATOR){
                      response.sendRedirect(request.getContextPath()+"/booksystem-back-stage-home.jsp");
                  }

                }
            }else {
                //用户输入的验证码不匹配
                request.setAttribute("checkcode_err","验证码错误");
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
