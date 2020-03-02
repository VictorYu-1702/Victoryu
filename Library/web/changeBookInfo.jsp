<%@ page import="model.Book" %>
<%@ page import="dal.BookDAL" %>
<%@ page import="dal.BorrowDAL" %>
<%@ page import="model.Borrow" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/27
  Time: 13:15
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

    <script type="text/javascript"   src="./js/jquery-1.12.4.js"></script>
    <script type="text/javascript">
        $(function () {
            /* 重点是这个位置 */
            $("#myfile").change(function() {
                /*在改变照片时，清空以前图片*/
                $("#img").empty();
                var oFReader = new FileReader();
                var file = document.getElementById('myfile').files[0];
                oFReader.readAsDataURL(file);
                oFReader.onloadend = function(oFRevent){
                    var src = oFRevent.target.result;
                    $("#img").append("<img src=\""+src+"\" width=\"180\" height=\"250\" border=\"1\" padding-left: 100px;>");
                }
            });
        })
    </script>
</header>

<body style="background-color:#faebd7">

<br/>
<br/>
<br/>
<div id="dv1">
    <h3 style="font-size: 20px;color: #333333">修改图书信息</h3>
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
    <form action="./changeBookInfoServlet" method="post">
        <table>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">书名</td>
                <td>
                    <input type="text" name="bkName" style="width: 200px" value="<%=book.getBkName()%>"/></td>
                </td>
                <td rowspan="7">
                    <div id="img" >
                        <img src="<%=book.getBkCover()%>" width="180px" height="250px">
                    </div>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">图书序号</td>
                <td style="width: 200px"><%=book.getBkID()%>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">条码号</td>
                <td>
                    <input type="text" name="bkCode" style="width: 200px" value="<%=book.getBkCode()%>"/>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">作者</td>
                <td>
                    <input type="text" name="bkAuthor" style="width: 200px" value="<%=book.getBkAuthor()%>"/>
                </td>
            </tr>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">出版社</td>
                <td >
                    <input type="text" name="bkPress" style="width: 200px" value="<%=book.getBkPress()%>"/>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">ISBN</td>
                <td >
                    <input type="text" name="bkISBN" style="width: 200px" value="<%=book.getBkISBN()%>"/>
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">索书号</td>
                <td>
                    <input type="text" name="bkCatalog" style="width: 200px" value="<%=book.getBkCatalog()%>"/>
                </td>
            </tr>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">书籍状态</td>
                <td style="width: 100px"><%=book.getBkStatus()%>
                </td>
            </tr>
            <tr>
                <td style="width:100px;font-family: 'Adobe 黑体 Std R'">修改状态</td>
                <td>
                    <input type="radio" name="changedStatus" value="inLib" checked="checked">在馆&nbsp;&nbsp;
                    <input type="radio" name="changedStatus" value="lost">遗失&nbsp;&nbsp;
                    <input type="radio" name="changedStatus" value="sold">变卖&nbsp;&nbsp;
                    <input type="radio" name="changedStatus" value="delete">销毁&nbsp;&nbsp;
                </td>
            </tr>
            <tr>
                <td style="width: 100px;font-family: 'Adobe 黑体 Std R'">价格</td>
                <td >
                    <input type="text" name="bkPrice" style="width: 200px" value="<%=book.getBkPrice()%>"/>
                </td>
                <td>
                    <input type="file" name="myfile" id="myfile" value="<%=book.getBkCover()%>">
                </td>
            </tr>
            <tr>
                <td style="width: 80px;font-family: 'Adobe 黑体 Std R'">图书简介</td>
                <td><textarea name="bkBrief" id="bkBrief" cols="30" rows="6" >
                    <%=book.getBkBrief()%>
                    </textarea>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="修改图书信息">
                    <input type="hidden" name="bkID" value="<%=book.getBkID()%>">
                    <input type="hidden" name="bkCoverNow" value="<%=book.getBkCover()%>">
                    <input type="hidden" name="bkStatus" value="<%=book.getBkStatus()%>">
                </td>
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
    <a href="main.jsp?name= <%=name%>" class="homeIcon">返回上一页</a>
    <a href="index.jsp" class="userIcon">退出登录</a>
</nav>
</body>
</html>
