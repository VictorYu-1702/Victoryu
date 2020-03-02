<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="dal.BookDAL" %>
<%@ page import="dal.BorrowDAL" %>
<%@ page import="model.Book" %>
<%@ page import="model.Borrow" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/25
  Time: 17:25
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
    <%--<h2 style="text-align: center;margin: 0;"><%=request.getParameter("name")%>,欢迎您来到长江大学图书馆管理信息系统</h2>--%>
    <h1>图书馆管理信息系统</h1>
</header>
<body style="background-color:#faebd7  ">
<h3></h3>
<br/>
<br/>
<br/>
<br/>
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
        <a>ISBN</a>
        <a>索书号</a>
        <a>借书时间</a>
        <a>书籍状态</a>
        <a>详情</a>
        <a>还书|续借</a>
    </dt>
</dl>
<%
    String string=(String)request.getSession().getAttribute("rdID");
    //String string=request.getParameter("rdID");
    int rdID = Integer.valueOf(string);
    System.out.println("用来保存2:::" + rdID);
    try {
        BorrowDAL borrowDAL = new BorrowDAL(rdID);
        for (int i = 0; i < borrowDAL.borrows.size(); i++) {
            int bkID = borrowDAL.borrows.get(i).getBkID();
            BookDAL bookDAL2 = new BookDAL();
            Book book = bookDAL2.getObjectByID(bkID);
            int overDay = borrowDAL.borrows.get(i).getIdOverDay();
            Boolean hasReturn = borrowDAL.borrows.get(i).isHasReturn();

            String overMsg = "";
            if (!hasReturn) {
                if (overDay == 0) {
                    overMsg = "<font color=\"black\">借阅中，未超期</font>";
                } else {
                    String Msg = "借阅中，已超期" + overDay + "天";
                    overMsg = "<font color=\"red\">" + Msg + "</font>";
                }

%>
    <table border="0" class="font">
        <tr class="cart_info">
            <hr/>
            <td width="180” height = " 30” valign="middle" style="text-align: center">
                <%=book.getBkName() %>
            </td>
            <td height="30" width="155" style="text-align: center">
                <%=book.getBkAuthor() %>
            </td>
            <td height="30" width="145" style="text-align: center">
                <%=book.getBkISBN() %>
            </td>
            <td height="30" width="170" style="text-align: center">
                <%=book.getBkCatalog() %>
            </td>
            <td height="30" width="170" style="text-align: center">
                <%=borrowDAL.borrows.get(i).getIdDateOut() %>
            </td>
            <td height="30" width="170" style="text-align: center">
                <%=overMsg %>
            </td>
            <td height="30" width="160" style="text-align: center">
                <a href="./returnBook.jsp?BorrowID=<%=borrowDAL.borrows.get(i).getBorrowID()%>">借阅详情</a>
            </td>
            <td height="30" width="65" style="align: center">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <a href="./returnBook.jsp?BorrowID=<%=borrowDAL.borrows.get(i).getBorrowID()%>">还书</a>
            </td>
            <td height="30" width="65" style="align: center">
                <%--<input type="button" name="continue" value="续借"/>--%>
                <a href="./continueBook.jsp?BorrowID=<%=borrowDAL.borrows.get(i).getBorrowID()%>">续借</a>
            </td>

        </tr>
    </table>
<%
        }else{
        overMsg="已归还";
        %>
<table border="0" class="font">
    <tr class="cart_info">
        <hr/>
        <td width="180” height = " 30” valign="middle" style="text-align: center">
            <%=book.getBkName() %>
        </td>
        <td height="30" width="155" style="text-align: center">
            <%=book.getBkAuthor() %>
        </td>
        <td height="30" width="145" style="text-align: center">
            <%=book.getBkISBN() %>
        </td>
        <td height="30" width="170" style="text-align: center">
            <%=book.getBkCatalog() %>
        </td>
        <td height="30" width="170" style="text-align: center">
            <%=borrowDAL.borrows.get(i).getIdDateOut() %>
        </td>
        <td height="30" width="170" style="text-align: center">
            <%=overMsg %>
        </td>
        <td height="30" width="160" style="text-align: center">
            <a href="./returnBook.jsp?BorrowID=<%=borrowDAL.borrows.get(i).getBorrowID()%>">借阅详情</a>
        </td>


    </tr>
</table>
<%
    }
    }
%>
<br/>
<br/>
<br/>
<br/>
<nav>
    <%
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String name = (String) request.getSession().getAttribute("name");
        //System.out.println(name);
    %>
    <nav>
        <a href="main.jsp?name= <%=name%>" class="homeIcon">返回图书页</a>
        <a href="index.jsp" class="userIcon">退出登录</a>
    </nav>
</nav>
</body>
</html>
