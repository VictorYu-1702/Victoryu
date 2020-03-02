<%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/23
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书馆管理信息系统</title>

    <link rel="stylesheet" type="text/css" href="css/style2.css"/>
    <style>
        #dv1 {
            padding-left: 300px;
        }

        #dv2 {
            padding-left: 350px;
        }

        #dv3 {
            padding-left: 400px;
        }
    </style>
</head>
<header>
    <h1>图书馆管理信息系统</h1>
</header>

<body style="background-color:#faebd7">

<br/>
<br/>
<br/>
<div id="dv1">
    <h3 style="font-size: 20px;color: #333333">用户管理——密码修改</h3>
</div>
<br/>
<br/>
<p/>

<form action="./changePswServlet" method="post">
    <table bgcolor="#f0ffff" ALIGN="CENTER" cellpadding="2" width="30%">
        <tr>
            <td style="width: 80px" align="center">借书证号</td>
            <td><input type="text" name="inputId" style="width: 150px"/></td>
        </tr>
        <tr>
            <td align="center">原始密码</td>
            <td><input type="password" name="inputPwd" id="inputPwd" style="width: 150px"/></td>

        </tr>
        <tr>
            <td align="center">新密码</td>
            <td><input type="password" name="inputnewPwd" id="inputnewPwd" style="width: 150px"/></td>
        </tr>
        <tr>
            <td align="center">确认新密码</td>
            <td><input type="password" name="inputrenewPwd" style="width: 150px" /></td>

        </tr>
        <tr>
            <td></td>
            <td><input type="submit" name="banzheng" value="确认办证"/>&nbsp;&nbsp;&nbsp;
                <input type="reset" name="myreset" value="取消"/></td>
        </tr>
    </table>
</form>
<%
    String name = (String) request.getSession().getAttribute("name");
    //System.out.println(name);
%>
<nav>
    <a href="main.jsp?name= <%=name%>" class="homeIcon">返回上一页</a>
    <a href="index.jsp" class="userIcon">退出登录</a>
</nav>
</body>
</html>
