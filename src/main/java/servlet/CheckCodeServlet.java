package servlet;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @TODO:验证码的servlet
 * @author: 陌意随影
 * @date: 2020-02-01 12:14
 */
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
     //设置编码
        request.setCharacterEncoding("utf-8");
       final  int weight=80;
       final  int hight=30;
        //加载一个图片缓冲流进内存
        BufferedImage img=new BufferedImage(weight,hight,BufferedImage.TYPE_INT_RGB);
        //获取图片的画笔
        Graphics graphics = img.getGraphics();
        Random random = new Random();
        //给图片填充颜色
        graphics.setColor(Color.pink);
        graphics.fillRect(0,0,weight,hight);
        //给图片填充颜色
        graphics.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
        graphics.setColor(Color.CYAN);
        //绘画边框
        graphics.drawRect(0,0,weight-1,hight-1);
        //获取验证码
        String checkCode = this.getCheckCode();
        //提交验证码
        request.getSession().setAttribute("checkcode",checkCode);
        graphics.setFont(new Font("微软雅黑",Font.BOLD,24));
        //绘画验证码
        for (int i =1;i <= 4;i++){
            graphics.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
            graphics.drawString(checkCode.charAt(i-1)+"",i*weight/5-7,hight/2+8);
        }
        graphics.setFont(new Font("微软雅黑",Font.BOLD,12));
      //绘画干扰元素
        for (int i =1;i <= 8;i++){
            graphics.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
            graphics.drawLine(random.nextInt(weight),random.nextInt(hight),random.nextInt(weight),random.nextInt(hight));
        }
        //写入到网页中
        ImageIO.write(img,"png",response.getOutputStream());


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doPost(request, response);
    }
    private String getCheckCode(){
        //验证码源码
        String str="ABCDEFGHIJKLMNOPQRSTUVWSYZabcdefghijklmnopqrstuvwsyz0123456789";
        //验证码数组
        char[] chars = str.toCharArray();
        StringBuilder sb=new StringBuilder();
        //随机数类
        Random random= new Random();
        int index=0;
        for (int i =0 ;i < 4;i++){
            //生成一个随机数
            index=random.nextInt(chars.length);
            sb.append(chars[index]);
        }
        return sb.toString();
    }
}
