<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/27
  Time: 16:32
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
                    $("#img").append("<img src=\""+src+"\" width=\"150\" height=\"180\" border=\"1\" padding-left: 100px;>");
                }
            });
        })
    </script>
</head>
<header>
    <h1 >图书馆管理信息系统</h1>

</header>

<body style="background-color:#faebd7">

<br/>
<br/>
<br/>
<div id="dv1">
    <h3 style="font-size: 20px;color: #333333">新书入库</h3>
</div>
<h4 style="text-align: center;color: #999999;font-size: 18px;font-family: 'Adobe 楷体 Std R'">注意：管理员在添加新书时，应将图书序号、条码号、书名、索书号等基本信息填写完整，否则会提交失败</h4>

<p/>
<div id="dv3">
    <form action="./newBookServlet" method="post">
        <table bgcolor="#f0ffff" cellpadding="2" width="60%">
            <tr>
                <td style="width: 120px;text-align:center" >图书序号</td>
                <td><input type="text" name="bkID" style="width: 150px"/></td>
                <td rowspan="7" >
                    <div id="img" ></div>
                </td>
            </tr>
            <tr>
                <td style="width: 120px;text-align:center">条码号</td>
                <td><input type="text" name="bkCode" style="width: 150px"/></td>
            </tr>
            <tr>
                <td style="width: 120px;text-align:center">书名</td>
                <td><input type="text" name="bkName" style="width: 150px"/></td>
            </tr>
            <tr>
                <td style="width:120px;text-align:center">作者</td>
                <td><input type="text" name="bkAuthor" style="width: 150px"/></td>
            </tr>
            <tr>
                <td style="width:120px;text-align:center">出版社</td>
                <td><input type="text" name="bkPress" style="width: 150px"/></td>
            </tr>
            <tr>
                <td style="width:120px;text-align:center">出版日期</td>
                <td><input type="text" name="bkDatePress" style="width: 150px"/></td>
            </tr>
            <tr>
                <td style="width:120px;text-align:center">ISBN编号</td>
                <td><input type="text" name="bkISBN" style="width: 150px"/></td>
            </tr>
            <tr>
                <td style="width:120px;text-align:center">索书号</td>
                <td><input type="text" name="bkCatalog" style="width: 150px"/></td>
                <td>
                    <input type="file" name="myfile" id="myfile">
                </td>
            </tr>
            <tr>
                <td style="width:120px;text-align:center">价格</td>
                <td><input type="text" name="bkPrice" style="width: 150px"/></td>
            </tr>
            <tr>
                <%
                    Date dNow = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    //out.println("现在时间" + formatter.format(dNow));
                %>
                <td style="width:120px;text-align:center">入馆(当前)日期</td>
                <td><%=formatter.format(dNow)%></td>
            </tr>
            <tr>
                <td style="width:120px;text-align:center">图书简介</td>
                <td><textarea name="bkBrief" id="bkBrief" cols="30" rows="6" >
                    </textarea>
                </td>
            </tr>

            <tr>
                <td></td>
                <td><input type="submit" name="banzheng" value="添加图书" />&nbsp;&nbsp;&nbsp;
                    <input type="reset" name="myreset" value="取消"/></td>
            </tr>
            <tr>
                <input type="hidden" name="dateNow" value="<%=formatter.format(dNow)%>">

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
