<%@ page import="model.Book" %>
<%@ page import="dal.BookDAL" %>
<%@ page import="dal.BorrowDAL" %>
<%@ page import="model.Borrow" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/25
  Time: 9:19
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
            width: 600px;
            height: 30px;
            background-color: #31bd80;
            padding-left: 50px;
            margin-left: 400px;
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
    <h3 style="font-size: 20px;color: #333333">图书详细信息</h3>
</div>
<br/>

<p/>
<%
    String s = request.getParameter("bkID");
    int bkID = Integer.valueOf(s);
    //System.out.println(bkID);
    BookDAL bookDAL = new BookDAL();
    Book book = bookDAL.getObjectByID(bkID);
    int rdRole = (int) request.getSession().getAttribute("rdRole");
%>

<div id="dv2">

    <h3 style="font-size: 20px;vertical-align: center">《<%=book.getBkName()%>》</h3>
</div>

<div id="dv3">
    <br/>
    <form action="./changeBookInfo.jsp" method="post">
        <table>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">书名</td>
                <td style="width: 200px"><%=book.getBkName()%>
                </td>
                <td rowspan="7">
                    <img src="<%=book.getBkCover()%>" width="180px" height="250px">
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">作者</td>
                <td style="width: 100px"><%=book.getBkAuthor()%>
                </td>
            </tr>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">出版社</td>
                <td style="width: 100px"><%=book.getBkPress()%>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">ISBN</td>
                <td style="width: 100px"><%=book.getBkISBN()%>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">索书号</td>
                <td style="width: 100px"><%=book.getBkCatalog()%>
                </td>
            </tr>

            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">书籍状态</td>
                <td style="width: 100px"><%=book.getBkStatus()%>
                </td>
            </tr>
            <%
                if (book.getBkStatus().equals("借出")) {
                    BorrowDAL borrowDAL = new BorrowDAL();
                    Borrow borrow = null;
                    try {
                        borrow = borrowDAL.getObjectByBkID(bkID);
                        if (rdRole == 1) {

            %>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">借阅读者编号</td>
                <td style="width: 50px"><%=borrow.getRdID()%>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="./changeReader.jsp?rdID=<%=borrow.getRdID()%>">查询读者</a>
                </td>
            </tr>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">借阅时间</td>
                <td style="width: 100px"><%=borrow.getIdDateOut()%>
                </td>
            </tr>
            <%
                }
            %>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">应还时间</td>
                <td style="width: 100px"><%=borrow.getIdDateRetPlan()%>
                </td>
            </tr>
            <%
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>
            <tr>
                <td style="width: 80px;font-family: 'Adobe 黑体 Std R'">价格</td>
                <td style="width: 100px">¥<%=book.getBkPrice()%>
                </td>
            </tr>
            <tr>
                <td style="width: 80px;font-family: 'Adobe 黑体 Std R'">图书简介</td>
                <td><%=book.getBkBrief()%>
                </td>
            </tr>
            <%
                if (rdRole == 1) {
            %>
            <tr>
                <td></td>
                <td>
                    <a href="./changeBookInfo.jsp?bkID=<%=bkID%>">
                       <font color="red">>>修改图书信息>></font>
                    </a>
                    <%--<input type="submit" value="修改图书信息">--%>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </form>
</div>

<%
    String name = (String) request.getSession().getAttribute("name");
    //System.out.println(name);
%>
<br/>
<br/>
<br/>
<br/>
<nav>
    <a href="main.jsp?name= <%=name%>" class="homeIcon">返回上一页</a>
    <a href="index.jsp" class="userIcon">退出登录</a>
</nav>
</body>
</html>