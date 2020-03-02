<%@ page import="dal.ReaderDAL" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="dal.ReaderTypeDAL" %>
<%@ page import="model.Reader" %>
<%@ page import="model.ReaderType" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/27
  Time: 0:05
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
    <h3 style="font-size: 20px;color: #333333">读者信息——读者类别管理</h3>
</div>
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
                <input type="hidden" name="select" value="changeRdType"/>
                <%--<input type="hidden" name="rdID" value="changerole">--%>
            </td>
        </tr>
    </table>
</form>


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
    %>
    <form action="./changeRdTypeServlet" method="post">
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
            <td style="width: 200px"><%=reader.getRdDateReg()%></td>
        </tr>
        <tr>
            <td style="width: 100px;font-family: 'Adobe 黑体 Std R'" align="center">当前证件状态</td>
            <td style="width: 200px"><%=reader.getRdStatus()%></td>
        </tr>
        <tr>
            <td style="width: 100px;font-family: 'Adobe 黑体 Std R'" align="center">当前读者类型</td>
            <td style="width: 200px"><%=rdTypeName%></td></td>
        </tr>
        <tr>
            <td align="center">修改读者类别</td>
            <td>
                    <%
                        //ReaderTypeDAL readerTypeDAL = new ReaderTypeDAL();
                        ResultSet rs = readerTypeDAL.getReaderType();
                        try {
                            while (rs.next()) {
                                String type = rs.getString(1);
                                //System.out.println(dept);
                    %>
                <input type="radio" name="changedRdType" value="<%=type%>"/><%=type%> &nbsp;&nbsp;
                    <%
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } finally {
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" name="banzheng" value="确认变更" />&nbsp;&nbsp;&nbsp;
                <input type="reset" name="myreset" value="取消"/></td>
        </tr>
        <tr>
            <input type="hidden" name="rdID" value="<%=rdID%>">
            <input type="hidden" name="rdName" value="<%=reader.getRdName()%>">
            <input type="hidden" name="rdTypeNow" value="<%=rdTypeName%>">
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

