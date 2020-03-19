<%--
  Created by IntelliJ IDEA.
  User: 陌意随影
  Date: 2020/2/5 0005
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    <title>用户注册界面</title>
    <script>
        window.onload=function () {
            document.getElementById("imgcheckcode").onclick=reflushCheckCode;
        }
        function reflushCheckCode(){
            var imgcheckcode = document.getElementById("imgcheckcode");
            imgcheckcode.src="${pageContext.request.contextPath}/checkCodeServlet?time="+new Date().getTime();
        }
    </script>
    <style>

    </style>
</head>
<body>
<div  style="width: 40%;margin-left: 35%;margin-top: 15%;">
    <form method="post" action="${pageContext.request.contextPath}/accountRegisterServlet" >
        <div class="input-group " style="width: 200px;">
            <span class="input-group-addon">账号</span>
            <input type="text" class="form-control" placeholder="请输入账号" name="name" required="required" value="${name}" >
            <c:if test="${register_err eq '该账号已被注册'}">
                <span class="glyphicon glyphicon-remove" style="font-size: 20px;color: red;" id="register_err"></span>
            </c:if>
            <c:if test="${empty register_err}">
                <script>
                    var elementById = document.getElementById("register_err");
                    if (elementById!=null){
                        elementById.remove();
                    }
                </script>
            </c:if>
        </div>
        <br>
        <div class="input-group" style="width: 200px;">
            <span class="input-group-addon">密码</span>
            <input type="password" class="form-control" placeholder="请输入密码" name="password" required="required" value="${password}" >
        </div>
        <br>
        <div class="form-group" style="width: 220px;">
            <span>性别：</span>
            <c:if test="${sex eq '男'}">
                <label class="radio-inline">
                    <input type="radio"  value="男性" name="sex" checked="checked">男性
                </label>
                <label class="radio-inline">
                    <input type="radio"  value="女性" name="sex">女性
                </label>
                <label class="radio-inline">
                    <input type="radio"  value="中性" name="sex">中性
                </label>
            </c:if>
            <c:if test="${sex eq '中性'}">
                <label class="radio-inline">
                    <input type="radio"  value="男性" name="sex" >男性
                </label>
                <label class="radio-inline">
                    <input type="radio"  value="女性" name="sex">女性
                </label>
                <label class="radio-inline">
                    <input type="radio"  value="中性" name="sex" checked="checked">中性
                </label>
            </c:if>
            <c:if test="${sex eq '女'}">
                <label class="radio-inline">
                    <input type="radio"  value="男性" name="sex" >男性
                </label>
                <label class="radio-inline">
                    <input type="radio"  value="女性" name="sex" checked="checked">女性
                </label>
                <label class="radio-inline">
                    <input type="radio"  value="中性" name="sex">中性
                </label>
            </c:if>
            <c:if test="${empty sex}">
                <label class="radio-inline">
                    <input type="radio"  value="男性" name="sex"  checked="checked">男性
                </label>
                <label class="radio-inline">
                    <input type="radio"  value="女性" name="sex">女性
                </label>
                <label class="radio-inline">
                    <input type="radio"  value="中性" name="sex">中性
                </label>
            </c:if>
        </div>
            <div class="form-group">
                <label for="checkcode" stype="display:inline;">验证码：</label>
                <input type="text" name="checkcode" class="form-control" id="checkcode" style="display:inline;width:110px;"  placeholder="请输入验证码" value="${checkcode}"/>
                <img src="${pageContext.request.contextPath}/checkCodeServlet" id="imgcheckcode" onclick="reflushCheckCode()" title="看不清？点击刷新一下"/>
                <c:if test="${checkcode_err eq '验证码错误'}">
                    <span class="glyphicon glyphicon-remove" style="font-size: 20px;color:red;"></span>
                </c:if>
                <c:if test="${checkcode_err eq '验证码正确'}">
                    <span class="glyphicon glyphicon-ok" style="font-size: 20px;color: green;"></span>
                </c:if>
            </div>
        <div class="form-group" style="align-items: center;" >
            <button type="submit" class="btn btn-primary" style="width: 80px;margin-left: 60px; margin-top: 5px;">立即注册</button>
        </div>
    </form>
</div>

</body>
</html>

