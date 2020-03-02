<%@ page import="dal.ReaderDAL" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="dal.ReaderTypeDAL" %>
<%@ page import="model.Reader" %>
<%@ page import="model.ReaderType" %>
<%@ page import="java.sql.SQLException" %>
<%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/23
  Time: 14:50
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
                    $("#img").append("<img src=\""+src+"\" width=\"120\" height=\"150\" border=\"1\" >");
                }
            });
        })
    </script>
</head>
<header>
    <h1>图书馆管理信息系统</h1>
</header>

<body style="background-color:#faebd7">

<br/>
<br/>
<br/>
<div id="dv1">
    <h3 style="font-size: 20px;color: #333333">读者信息——借书证信息变更&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;读者基本信息查询</h3>
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
                <input type="hidden" name="select" value="changeReader"/>
                <%--<input type="hidden" name="rdID" value="changerole">--%>
            </td>
        </tr>
    </table>
</form>


<div id="dv3">
    <%
        String s=request.getParameter("rdID");
        System.out.println("测试能否输出"+s);
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
    <form action="./changeReaderServlet" method="post">
        <table bgcolor="white" cellpadding="2" width="60%">
            <tr>
                <td style="width: 80px" align="center">借书证号</td>
                <td style="width: 200px"><%=rdID%></td>
                <td rowspan="5" align="center">
                    <div id="img" >
                        <%--<img src="<%=reader.getRdPhoto()%>" width="180px" height="250px">--%>
                    </div>
                </td>
            </tr>
            <tr>
                <td align="center">姓名</td>
                <td><input type="text" name="inputName" style="width: 150px" value="<%=reader.getRdName()%>"/></td>
            </tr>

            <tr>
                <td align="center">性别</td>
                <td >
                    <input type="radio" name="inputSex" value="male" checked="checked"/> 男 &nbsp;&nbsp;
                    <input type="radio" name="inputSex" value="female"/>女
                </td>
            </tr>

            <tr>
                <td align="center">单位</td>
                <td colspan="2">
                    <select name="inputDept" style="width: 200px">
                        <%
                            //ReaderDAL readerDAL = new ReaderDAL();
                            ResultSet rs2 = readerDAL.getReaderDept();
                            try {
                                while (rs2.next()) {
                                    String dept = rs2.getString(1);
                        %>
                        <option value="<%=dept%>"><%=dept%>
                        </option>
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
                <td align="center">电话号码</td>
                <td colspan="2"><input type="text" name="inputPhone" style="width: 200px" value="<%=reader.getRdPhone()%>"/></td>
            </tr>
            <tr>
                <td align="center">电子邮件</td>
                <td colspan="2"><input type="text" name="inputEmail" style="width: 200px" value="<%=reader.getRdEmail()%>"/></td>
            </tr>
            <tr>
                <td align="center">办证日期</td>
                <td colspan="2"><input type="text" name="inputTime" style="width: 200px" value="<%=reader.getRdDateReg()%>"/></td>
                <td>
                    <input type="file" name="myfile" id="myfile" value="<%=reader.getRdPhoto()%>">
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="banzheng" value="确认变更" />&nbsp;&nbsp;&nbsp;
                    <input type="reset" name="myreset" value="取消"/></td>
            </tr>
            <tr>
                <input type="hidden" name="inputId" value="<%=rdID%>">
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
