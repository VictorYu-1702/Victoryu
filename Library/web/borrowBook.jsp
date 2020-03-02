<%@ page import="dal.BookDAL" %>
<%@ page import="model.Book" %>
<%@ page import="dal.ReaderTypeDAL" %>
<%@ page import="model.ReaderType" %>
<%@ page import="dal.ReaderDAL" %>
<%@ page import="model.Reader" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/25
  Time: 14:13
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
            padding-left:50px;
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
    <h3 style="font-size: 20px;color: #333333">借阅图书</h3>
</div>
<br/>

<p/>
<%
    String s=request.getParameter("bkID");
    int bkID=Integer.valueOf(s);
    String s1=(String)request.getSession().getAttribute("rdID");
    int rdID=Integer.valueOf(s1);
    //System.out.println(bkID);
    BookDAL bookDAL=new BookDAL();
    Book book= null;
    try {
        book = bookDAL.getObjectByID(bkID);
        ReaderDAL readerDAL=new ReaderDAL();
        Reader reader=readerDAL.getObjectByID(rdID);
        ReaderTypeDAL readerTypeDAL=new ReaderTypeDAL();
        ReaderType readerType=readerTypeDAL.getObjectByID(reader.getRdType());

        %>
<div id="dv2" >

    <h3 style="font-size: 20px;vertical-align: center">《<%=book.getBkName()%>》</h3>
</div>

<div id="dv3">
    <br/>
    <form action="./BorrowBookServlet" method="post">
        <table>
            <tr>
                <td style="width: 80px;font-family: 'Adobe 黑体 Std R'">书名</td>
                <td style="width: 200px"><%=book.getBkName()%></td>
                <td rowspan="7">
                    <img src="<%=book.getBkCover()%>"width="180px" height="250px">
                </td>
            </tr>
            <tr>
                <td style="width: 80px;font-family: 'Adobe 黑体 Std R'">索书号</td>
                <td style="width: 100px"><%=book.getBkCatalog()%></td>
            </tr>
            <tr>
                <td style="width: 80px;font-family: 'Adobe 黑体 Std R'">书籍状态</td>
                <td style="width: 100px"><%=book.getBkStatus()%></td>
            </tr>
            <tr>
                <td style="width: 80px;font-family: 'Adobe 黑体 Std R'">读者编号</td>
                <td style="width: 100px"><%=rdID%></td>
            </tr>
            <tr>
                <td style="width: 80px;font-family: 'Adobe 黑体 Std R'">读者类别</td>
                <td style="width: 100px"><%=readerType.getRdTypeName()%></td>
            </tr>
            <tr>
                <td style="width: 80px;font-family: 'Adobe 黑体 Std R'">可借阅天数</td>
                <td style="width: 100px"><%=readerType.getCanLendDay()%>
                </td>
            </tr>
            <tr>
                <td style="width: 80px;font-family: 'Adobe 黑体 Std R'">应还日期</td>
                <td style="width: 100px">
                    <%
                        Calendar now = Calendar.getInstance();
                        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd ");
                        String DateOut=formatter3.format(now.getTime());
                        now.add(Calendar.DAY_OF_YEAR,readerType.getCanLendDay());
                        SimpleDateFormat formatter4 = new SimpleDateFormat("yyyy-MM-dd  ");
                        String DateRetPlan=formatter4.format(now.getTime());
                    %>
                    <%=DateRetPlan%>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="hidden" name="rdID" value="<%=rdID %>"/>
                </td>
                <td>
                    <input type="hidden" name="bkID" value="<%=bkID %>"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="hidden" name="DateOut" value="<%=DateOut %>"/>
                </td>
                <td>
                    <input type="hidden" name="DateRetPlan" value="<%=DateRetPlan %>"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" name="submit" value="确认借阅"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<%
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>
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