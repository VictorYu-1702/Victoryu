<%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/20
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>登录界面</title>
  <%--<link rel="stylesheet" type="text/css" href="css/style.css"/>--%>
  <style>
    #div1 {
      /*align: center;*/
      text-align: center;
      /*margin-left: 400px;*/
    }
    #div2 {
      text-align: center;
      color: red;
    }
    header {
      height: 62px;
      line-height: 1rem;
      background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #21d9a2), color-stop(1, #31bd80));
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      z-index: 99;
    }
  </style>
  <script type="text/javascript">
      function subClick() {
          document.getElementById("identity").src = './IdentityServlet?ts=' + new Date().getTime();
      }
  </script>
</head>
<header>

</header>
<body style="background-color:#faebd7  ">
<br/>
<br/>
<br/>
<h2 align="center"style="font-size: 25px;">图书馆管理信息系统</h2>
<h2 align="center"style="font-size: 20px;font-family: 'Adobe 楷体 Std R';color: blue">欢迎您   请登录</h2>

<%--<img src="img/login.png" height="280px" width="auto"/>--%>

<br/>
<br/>
<form action="./LoginServlet" method="post">
  <table align="center" bgcolor="#f0ffff" cellpadding="2" width="30%">
    <tr>
      <td>用户编号</td>
      <td><input type="text" name="id"/></td>
    </tr>
    <tr>
      <td>用户密码</td>
      <td><input type="password" name="pwd"/></td>
    </tr>
    <tr>
      <td>验&nbsp;&nbsp;证&nbsp;&nbsp;码&nbsp;</td>
      <td><input type="text" name="yzm"/></td>
      <td><img src="./IdentityServlet" id="identity" onclick="subClick()"/></td>
    </tr>
    <tr>
      <td></td>
      <td><input type="submit" name="tijiao" value="登录"/>&nbsp;&nbsp;&nbsp;
        <input type="reset" name="myreset" value="取消"/></td>
    </tr>
  </table>
</form>
<div id="div2">
  <%
    //message 是登录界面返回的消息
    //info是注册页面返回的消息
    String message = request.getParameter("message");
    String info = request.getParameter("info");
    if (message != null) {
      if (Integer.valueOf(message) == 1) {
        out.println("<p>验证码错误</p>");
      } else if (Integer.valueOf(message) == 2) {
        out.println("<p>账号密码错误</p>");
      } else if (Integer.valueOf(message) == -1) {
        out.println("<p>用户名不存在</p>");
      }
    }
    if (info != null) {
      out.println("<p>注册成功，请重新输入密码</p>");
    }
  %>
</div>
</body>
</html>
