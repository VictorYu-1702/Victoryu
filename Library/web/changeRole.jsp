<%@ page import="dal.ReaderDAL" %>
<%@ page import="model.Reader" %>
<%@ page import="dal.ReaderTypeDAL" %>
<%@ page import="model.ReaderType" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/24
  Time: 16:41
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
            width: 610px;
            background-color: white;
            padding-left: 40px;
            margin-left: 400px;
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
    <h3 style="font-size: 20px;color: #333333">用户管理——权限管理</h3>
</div>
<br/>
<br/>
<p/>

<form action="./changeServlet" method="post">
    <table bgcolor="#f0ffff" ALIGN="CENTER" cellpadding="2" width="30%">
        <tr>
            <td style="width: 80px" align="center">借书证号</td>
            <td><input type="text" name="inputId" style="width: 150px"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" name="banzheng" value="查询读者"/>&nbsp;&nbsp;&nbsp;
                <input type="reset" name="myreset" value="取消"/></td>
        </tr>
        <tr>
            <td>
                <input type="hidden" name="select" value="changerole">
                <%--<input type="hidden" name="rdID" value="changerole">--%>
            </td>
        </tr>
    </table>
</form>

<br/>
<div id="dv3">
    <%
        String s=request.getParameter("rdID");
        if(s!=null){
            //System.out.println("测试能否输出"+s);
            int rdID=Integer.valueOf(s);
            ReaderDAL readerDAL=new ReaderDAL();
            Reader reader= null;
            try {
                reader = readerDAL.getObjectByID(rdID);
                ReaderTypeDAL readerTypeDAL=new ReaderTypeDAL();
                ReaderType readerType=readerTypeDAL.getObjectByID(reader.getRdType());
                String rdTypeName=readerType.getRdTypeName();
                int role=reader.getRdAdminRoles();
                String roles="";
                if(role==0){
                    roles="读者";
                }else{
                    roles="读者，管理员";
                }

    %>
    <form action="./changeRoleServlet" method="post">
        <table>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">读者编号</td>
                <td style="width: 200px;">
                    <%=reader.getRdID()%>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">读者姓名</td>
                <td style="width: 200px;">
                    <%=reader.getRdName()%>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">读者类别</td>
                <td style="width: 200px;">
                    <%=rdTypeName%>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">单位</td>
                <td style="width: 200px;">
                    <%=reader.getRdDept()%>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">当前管理角色</td>
                <td style="width: 200px;">
                    <%=roles%>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">更改角色</td>
                <td>
                    <input type="radio" name="changedRole" value="set" checked="checked"/> 设置为管理员 &nbsp;&nbsp;
                    <input type="radio" name="changedRole" value="reset"/>取消管理员
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" name="genggai" value="确认更改">&nbsp;&nbsp;&nbsp;
                    <input type="reset" name="myreset" value="取消"/></td>
                </td>
            </tr>
            <tr>
                <input type="hidden" name="rdID" VALUE="<%=reader.getRdID()%>">
                <input type="hidden" name="rdRole" VALUE="<%=reader.getRdAdminRoles()%>">
            </tr>
        </table>
    </form>
    <%
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    %>
</div>


<%
    String name = (String) request.getSession().getAttribute("name");
%>
<nav>
    <a href="main.jsp?name= <%=name%>" class="homeIcon">返回上一页</a>
    <a href="index.jsp" class="userIcon">退出登录</a>
</nav>
</body>
</html>
