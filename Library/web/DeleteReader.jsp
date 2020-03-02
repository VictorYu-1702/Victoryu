<%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/26
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dal.ReaderDAL" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="dal.ReaderTypeDAL" %>
<%@ page import="model.Reader" %>
<%@ page import="model.ReaderType" %>
<%@ page import="java.sql.SQLException" %>
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
    <h3 style="font-size: 20px;color: #333333">读者信息——注销借书证</h3>
</div>
<h4 style="text-align: center;color: #999999;font-size: 18px;font-family: 'Adobe 楷体 Std R'">注意：注销借书证前请归还所有借阅的图书。若存在超期罚款金额，请支付还清，否则无法注销</h4>
<h4 style="text-align: center;color: #999999;font-size: 18px;font-family: 'Adobe 楷体 Std R'">借书证注销后，用户将被删除，所有记录都将删除，请谨慎操作</h4>
<br/>
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
                <input type="hidden" name="select" value="deleteReader"/>
                <%--<input type="hidden" name="rdID" value="changerole">--%>
            </td>
        </tr>
    </table>
</form>


<div id="dv3">
    <%
        String s=request.getParameter("rdID");
        if(s!=null){
            int rdID=Integer.valueOf(s);
            ReaderDAL readerDAL=new ReaderDAL();
            Reader reader= null;
            try {
                reader = readerDAL.getObjectByID(rdID);
                ReaderTypeDAL readerTypeDAL=new ReaderTypeDAL();
                ReaderType readerType=readerTypeDAL.getObjectByID(reader.getRdType());
                String rdTypeName=readerType.getRdTypeName();
    %>
    <form action="./deleteReaderServlet" method="post">
        <table bgcolor="white" cellpadding="2" width="60%">
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'" align="center">借书证号</td>
                <td style="width: 200px"><%=rdID%></td>
            </tr>
            <tr>
                <td  style="width: 100px;font-family: 'Adobe 黑体 Std R'" align="center">姓名</td>
                <td style="width: 200px"><%=reader.getRdName()%></td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'" align="center">单位</td>
                <td style="width: 200px"><%=reader.getRdDept()%>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'" align="center">办证日期</td>
                <td colspan="2"><%=reader.getRdDateReg()%></td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'" align="center">当前证件状态</td>
                <td colspan="2"><%=reader.getRdStatus()%></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="banzheng" value="确认注销" />&nbsp;&nbsp;&nbsp;
                    <input type="reset" name="myreset" value="取消"/></td>
            </tr>
            <tr>
                <input type="hidden" name="rdID" value="<%=rdID%>">
                <input type="hidden" name="rdStatus" value="<%=reader.getRdStatus()%>">
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
    //System.out.println(name);
%>
<nav>
    <a href="main.jsp?name= <%=name%>" class="homeIcon">返回上一页</a>
    <a href="index.jsp" class="userIcon">退出登录</a>
</nav>
</body>
</html>
