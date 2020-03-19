<%--
  Created by IntelliJ IDEA.
  User: 陌意随影
  Date: 2020/1/31 0031
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <title>登录页面</title>
    <style>
        #div1 {
            margin-left: 40%;
            margin-top: 20%;
        }

    </style>
<script>
    window.onload=function () {
        document.getElementById("img").onclick=reflushCheckCode;
    }
    function reflushCheckCode(){
        var img = document.getElementById("img");
        img.src="${pageContext.request.contextPath}/checkCodeServlet?time="+new Date().getTime();
    }
</script>

    <style>
        #name{
            color: red;
        }
    </style>
</head>
<body>

<!-- 登录表单 -->
<div id="div1">
        <form  method="post" action="${pageContext.request.contextPath}/loginServlet">
            <table>
                <tr>
                <td >
                 <div class="form-group">
                    <label for="name" stype="display:inline;">账户：</label>
                    <input type="text" name="name" class="form-control" id="name" style="display:inline;width:200px;" required="required" placeholder="请输入用户名" value="${name}"/>
                </div>
                </td>
                    <td>
                        <c:if test="${login_err eq '用户名或密码错误'}">
                            <span class="glyphicon glyphicon-remove" style="font-size: 20px;color: red;" id="login_err"></span>
                        </c:if>
                        <c:if test="${empty login_err}">
                            <script>
                                var elementById = document.getElementById("login_err");
                                if (elementById!=null){
                                    elementById.remove();
                                }
                            </script>
                        </c:if>

                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group">
                            <label for="password" style="display:inline;">密码：</label>
                            <input type="password"  name="password" class="form-control" id="password" style="display:inline;width:200px;" required="required"  placeholder="请输入密码"  value="${password}"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td >
                        <div class="form-group">
                            <label for="checkcode" stype="display:inline;">验证码：</label>
                            <input type="text"name="checkcode" class="form-control" id="checkcode" style="display:inline;width:110px;"  placeholder="请输入验证码" value="${checkcode}"/>
                            <img src="${pageContext.request.contextPath}/checkCodeServlet" id="img" onclick="reflushCheckCode()" title="看不清？点击刷新一下">
                        </div>
                    </td>
                    <td>
                        <c:if test="${checkcode_err eq '验证码错误'}">
                            <span class="glyphicon glyphicon-remove" style="font-size: 20px;color:red;"></span>
                        </c:if>
                        <c:if test="${checkcode_err eq '验证码正确'}">
                            <span class="glyphicon glyphicon-ok" style="font-size: 20px;color: green;"></span>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td >
                        <div class="form-group">
                            <input type="checkbox" name="remeberPassword"/>&nbsp;<span class="remebb">记住密码</span>
                            <a id="searchPwd" data-toggle="modal" data-target="#searchPwd-window" style="margin-left: 127px;">忘记密码</a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="form-group" >
                            <button type="submit" class="btn btn-primary" style="width: 80px;margin-left: 2px;">登录</button>
                            <%
                                request.removeAttribute("checkcode");
                                System.out.println(request.getParameter("checkcode"));
                                request.removeAttribute("name");
                                request.removeAttribute("password");
                            %>
                            <a id="toRegister" style="margin-left: 125px;" href="${pageContext.request.contextPath}/accountregister.jsp">立即注册</a>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
