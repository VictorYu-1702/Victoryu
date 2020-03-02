<%@ page import="dal.BorrowDAL" %>
<%@ page import="model.Borrow" %>
<%@ page import="dal.BookDAL" %>
<%@ page import="model.Book" %>
<%@ page import="dal.ReaderDAL" %>
<%@ page import="model.Reader" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="dal.ReaderTypeDAL" %>
<%@ page import="model.ReaderType" %>
<%@ page import="java.text.ParseException" %><%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/26
  Time: 11:12
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
    <h3 style="font-size: 20px;color: #333333">归还图书</h3>
</div>
<br/>

<p/>
<%
    String s = request.getParameter("BorrowID");
    System.out.println("returnBook页面"+s);
    int BorrowID = Integer.valueOf(s);
    //System.out.println(bkID);
    BorrowDAL borrowDAL = new BorrowDAL();
    Borrow borrow = borrowDAL.getObjectByID(BorrowID);
    int bkID = borrow.getBkID();
    int rdID = borrow.getRdID();
    BookDAL bookDAL = new BookDAL();
    Book book = bookDAL.getObjectByID(bkID);
    ReaderDAL readerDAL = new ReaderDAL();
    Reader reader = readerDAL.getObjectByID(rdID);
    int rdType = reader.getRdType();
    ReaderTypeDAL readerTypeDAL = new ReaderTypeDAL();
    ReaderType readerType = readerTypeDAL.getObjectByID(rdType);
    System.out.println("读者类别：" + readerType.getRdTypeName());
    System.out.println("罚款率：" + readerType.getPunishRate());
%>
<%
    /*获取当前时间  借书时间 应还时间 超期时间 */
    Date dNow = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  ");
    String now=formatter.format(dNow);
    Date d1 = borrow.getIdDateRetPlan();
    long diff = dNow.getTime() - d1.getTime();
   // System.out.println("Difference is " + (diff / (1000 * 60 * 60 * 24)) + " days.");
    long day=(diff / (1000 * 60 * 60 * 24));
    int OverDay=day>0?(int)day:0;
    //System.out.println(OverDay);
    float punishRate=readerType.getPunishRate() / 100;
    float OverMoney=punishRate*OverDay;
    float PunishMoney=OverMoney+borrow.getIdPunishMoney();
%>
<%--<h4 style="text-align: right;font-size: 15px" ><%=formatter.format(dNow)%></h4>--%>

<div id="dv2">

    <h3 style="font-size: 20px;vertical-align: center">《<%=book.getBkName()%>》</h3>
</div>

<div id="dv3">
    <br/>
    <form action="./returnBookServlet">
        <table>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">书名</td>
                <td style="width: 200px"><%=book.getBkName()%>
                </td>
                <td rowspan="7">
                    <img src="<%=book.getBkCover()%>" width="180px" height="250px">
                </td>
            </tr>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">作者</td>
                <td style="width: 100px"><%=book.getBkAuthor()%>
                </td>
            </tr>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">索书号</td>
                <td style="width: 100px"><%=book.getBkCatalog()%>
                </td>
            </tr>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">借阅读者姓名</td>
                <td style="width: 100px"><%=reader.getRdName()%>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">借阅日期</td>
                <td style="width: 100px"><%=borrow.getIdDateOut()%>
                </td>
            </tr>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">应还日期</td>
                <td style="width: 100px"><%=borrow.getIdDateRetPlan()%>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">当前日期</td>
                <td style="width: 100px"><%=now%>
                </td>
            </tr>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">超期天数</td>
                <td style="width: 100px"><%=OverDay%>
                </td>
            </tr>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">罚款率</td>
                <td style="width: 100px"><%=readerType.getPunishRate() / 100%> 元/天</td>
            </tr>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">本次罚款金额</td>
                <td style="width: 100px"><%=OverMoney%>  元</td>
            </tr>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">累计罚款金额</td>
                <td style="width: 100px"><%=PunishMoney%>  元</td>
            </tr>
            <tr>
                <td></td>
                <td style="width: 100px">
                    <input type="submit" name="return" value="确认还书">
                </td>
            </tr>
            <tr>
               <input type="hidden" name="BorrowID" VALUE="<%=BorrowID%>">
                <input type="hidden" name="bkID" value="<%=book.getBkID()%>">
                <input type="hidden" name="rdID" value="<%=borrow.getRdID()%>">
                <input type="hidden" name="DateRetAct" VALUE="<%=now%>">
                <input type="hidden" name="OverDay" VALUE="<%=OverDay%>">
                <input type="hidden" name="OverMoney" VALUE="<%=OverMoney%>">
                <input type="hidden" name="PunishMoney" VALUE="<%=PunishMoney%>">
            </tr>
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
    <a href="MyBook.jsp?name= <%=name%>" class="homeIcon">返回上一页</a>
    <a href="index.jsp" class="userIcon">退出登录</a>
</nav>
</body>
</html>