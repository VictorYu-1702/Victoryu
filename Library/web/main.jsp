<%@ page import="dal.BookDAL" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/21
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书馆管理信息系统</title>
    <link rel="stylesheet" type="text/css" href="css/style2.css"/>
    <style type="text/css">
        #dv1 {
            width: 800px;
            height: 40px;
            background-color: white;
            padding-left: 200px;
        }

        #dv2 {
            width: 800px;
            height: 40px;
            background-color: white;
            padding-left: 200px;
        }

        li a {
            text-decoration: none;
        }

        li {
            list-style: none;
        }

        .li1 {
            float: left;
            overflow: hidden;
            width: 160px;
            height: 40px;
            line-height: 40px;
            font-size: 20px;
            align: center;
        }

        .li1:hover {
            overflow: visible;
            background-color: green;
        }

        ul li ul li a {
            width: 120px;
            height: 30px;
            float: left;
            background-color: purple;
            font-size: 15px;
            color: white;
        }

        ul li ul li a:hover {
            background-color: red;
            color: yellow;
        }

        .font {
            font-size: 12px
        }

    </style>
</head>

<header>
    <h2 style="text-align: center;margin: 0;"><%=request.getParameter("name")%>,欢迎您来到图书馆管理信息系统</h2>

</header>
<body style="background-color:#faebd7  ">
<h3></h3>
<br/>
<br/>
<br/>
<br/>
<%
    String rdID = (String) request.getSession().getAttribute("rdID");
    int rdRole = (int) request.getSession().getAttribute("rdRole");
    /*String rdID=request.getParameter("id");*/
    /*System.out.println("main   "+rdID);
    System.out.println("main rdRole  "+rdRole);*/
    if (rdRole == 1) {
%>
<center>
    <div id="dv1" align="center">

        <ul>
            <li class="li1"><a href="#">图书管理</a>
                <ul>
                    <li><a href="./newBook.jsp">新书入库</a></li>
                    <li><a href="">图书信息维护</a></li>
                </ul>
            </li>
            <li class="li1"><a href="">读者管理</a>
                <ul>
                    <li><a href="./newReader.jsp">办理借书证</a></li>
                    <li><a href="./changeReader.jsp">借书证信息变更</a></li>
                    <li><a href="./LogoutReader.jsp">借书证挂失与解除</a></li>
                    <li><a href="./DeleteReader.jsp">注销借书证</a></li>
                    <li><a href="./changeRdType.jsp">读者类型管理</a></li>
                </ul>
            </li>
            <li class="li1"><a href="./main.jsp">借阅图书</a>
                <ul>
                    <li><a href="./main.jsp?name=<%=request.getParameter("name")%>">借书</a></li>
                    <li><a href="./MyBook.jsp?rdID=<%=rdID%>">续借</a></li>
                    <li><a href="./MyBook.jsp?rdID=<%=rdID%>">还书</a></li>
                </ul>
            </li>
            <li class="li1"><a href="">用户管理</a>
                <ul>
                    <li><a href="./changeRole.jsp">权限管理</a></li>
                    <li><a href="./changePsw.jsp">密码修改</a></li>
                </ul>
            </li>
        </ul>
    </div>
</center>
<%--<div style="height:1rem;"></div>--%>
<%
    Date dNow = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd E ");
%>
<h4 style="text-align: right;font-size: 15px"><%=formatter.format(dNow)%>
</h4>
<hr/>
<h3 style="padding-left: 90px"> 全部图书</h3>

<hr/>
<!--Tab:productList-->
<dl class="tab_proList">
    <dt>
        <a>书名</a>
        <a>作者</a>
        <a>出版社</a>
        <a>ISBN</a>
        <a>索书号</a>
        <a>价格</a>
        <a>详情/信息维护</a>
        <a>状态</a>
        <a>借阅</a>
    </dt>
</dl>
<%
    /*String info = request.getParameter("info");*/

    //System.out.println("用来保存1:::"+string);
    // int rdID = Integer.valueOf(string);
    //System.out.println("用来保存2:::"+rdID);
    //request.getSession().setAttribute("rdID", rdID);
    /*int id = (int)request.getSession().getAttribute("id");*/
    BookDAL bookDAL = new BookDAL();
    for (int i = 0; i < bookDAL.books.size(); i++) {
        String s = bookDAL.books.get(i).getBkStatus();
        String stsMsg = "";
        if (!s.equals("在馆")) {
            stsMsg = "<font color=\"red\">" + s + "</font>";
        } else {
            stsMsg = "<font color=\"black\">" + s + "</font>";
            ;
        }
%>
<form action="./BorrowServlet" method="post">
    <table border="0" class="font">
        <tr class="cart_info">
            <hr/>
            <td width="220” height = " 30” valign="middle" style="text-align: center">
                <%=bookDAL.books.get(i).getBkName() %>
            </td>
            <td height="30" width="180" style="text-align: center">
                <%=bookDAL.books.get(i).getBkAuthor() %>
            </td>
            <td height="30" width="180" style="text-align: center">
                <%=bookDAL.books.get(i).getBkPress() %>
            </td>
            <td height="30" width="160" style="text-align: center">
                <%=bookDAL.books.get(i).getBkISBN() %>
            </td>
            <td height="30" width="170" style="text-align: center">
                <%=bookDAL.books.get(i).getBkCatalog() %>
            </td>
            <td height="30" width="170" style="text-align: center">
                ¥<%=bookDAL.books.get(i).getBkPrice() %>
            </td>
            <td height="30" width="160" style="text-align: center">
                <a href="./bookInfo.jsp?bkID=<%=bookDAL.books.get(i).getBkID()%>">图书详情</a>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="./changeBookInfo.jsp?bkID=<%=bookDAL.books.get(i).getBkID()%>">信息维护</a>
            </td>
            <td height="30" width="180" style="text-align: center">
                <%=stsMsg%>
            </td>
            <td height="30" width="150" style="align: center">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" name="submit" value="借阅"/>
            </td>
            <td height="30" width="50">
                <input type="hidden" name="bkID" value="<%=bookDAL.books.get(i).getBkID()%>"/>
            </td>
            <td>
                <input type="hidden" name="bkStatus" value="<%=bookDAL.books.get(i).getBkStatus() %>"/>
            </td>
            <%--<td>
                <input type="hidden" name="uId" value="<%=uId %>"/>
            </td>--%>

        </tr>
    </table>
</form>
<%
    }
} else {
%>
<center>
    <div id="dv2" align="center">

        <ul>
            <li class="li1"><a href="./main.jsp?name=<%=request.getParameter("name")%>">借书</a>
            </li>
            <li class="li1"><a href="./MyBook.jsp?rdID=<%=rdID%>">续借</a>

            </li>
            <li class="li1"><a href="./MyBook.jsp?rdID=<%=rdID%>">还书</a>
            </li>
            <li class="li1"><a href="./rdRoleInfoChange.jsp?rdID=<%=rdID%>">信息变更</a>
            </li>
        </ul>
    </div>
</center>
<%
    Date dNow = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd E ");
%>
<h4 style="text-align: right;font-size: 15px"><%=formatter.format(dNow)%>
</h4>
<hr/>
<h3 style="padding-left: 90px"> 全部图书</h3>

<hr/>
<!--Tab:productList-->
<dl class="tab_proList">
    <dt>
        <a>书名</a>
        <a>作者</a>
        <a>出版社</a>
        <a>ISBN</a>
        <a>索书号</a>
        <a>价格</a>
        <a>详情</a>
        <a>状态</a>
        <a>借阅</a>
    </dt>
</dl>
<%
    /*String info = request.getParameter("info");*/

    //System.out.println("用来保存1:::"+string);
    // int rdID = Integer.valueOf(string);
    //System.out.println("用来保存2:::"+rdID);
    //request.getSession().setAttribute("rdID", rdID);
    /*int id = (int)request.getSession().getAttribute("id");*/
    BookDAL bookDAL = new BookDAL();
    for (int i = 0; i < bookDAL.books.size(); i++) {
        String s = bookDAL.books.get(i).getBkStatus();
        String stsMsg = "";
        if (!s.equals("在馆")) {
            stsMsg = "<font color=\"red\">" + s + "</font>";
        } else {
            stsMsg = "<font color=\"black\">" + s + "</font>";
            ;
        }
%>
<form action="./BorrowServlet" method="post">
    <table border="0" class="font">
        <tr class="cart_info">
            <hr/>
            <td width="220” height = " 30” valign="middle" style="text-align: center">
                <%=bookDAL.books.get(i).getBkName() %>
            </td>
            <td height="30" width="180" style="text-align: center">
                <%=bookDAL.books.get(i).getBkAuthor() %>
            </td>
            <td height="30" width="180" style="text-align: center">
                <%=bookDAL.books.get(i).getBkPress() %>
            </td>
            <td height="30" width="160" style="text-align: center">
                <%=bookDAL.books.get(i).getBkISBN() %>
            </td>
            <td height="30" width="170" style="text-align: center">
                <%=bookDAL.books.get(i).getBkCatalog() %>
            </td>
            <td height="30" width="170" style="text-align: center">
                ¥<%=bookDAL.books.get(i).getBkPrice() %>
            </td>
            <td height="30" width="160" style="text-align: center">
                <a href="./bookInfo.jsp?bkID=<%=bookDAL.books.get(i).getBkID()%>">图书详情</a>
            </td>
            <td height="30" width="180" style="text-align: center">
                <%=stsMsg%>
            </td>
            <td height="30" width="150" style="align: center">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" name="submit" value="借阅"/>
            </td>
            <td height="30" width="50">
                <input type="hidden" name="bkID" value="<%=bookDAL.books.get(i).getBkID()%>"/>
            </td>
            <td>
                <input type="hidden" name="bkStatus" value="<%=bookDAL.books.get(i).getBkStatus() %>"/>
            </td>
            <%--<td>
                <input type="hidden" name="uId" value="<%=uId %>"/>
            </td>--%>

        </tr>
    </table>
</form>
<%
        }
    }
%>
<br/>
<br/>
<br/>
<nav>
    <a href="MyBook.jsp?rdID=<%=rdID%>" class="cartIcon">借阅书单</a>
    <a href="index.jsp" class="userIcon">退出登录</a>
</nav>
</body>
