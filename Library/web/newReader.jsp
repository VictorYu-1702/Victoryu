<%@ page import="dal.ReaderDAL" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="dal.ReaderTypeDAL" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/21
  Time: 22:24
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
    <h3 style="font-size: 20px;color: #333333">读者信息——办理借书证</h3>
</div>

<p/>
<div id="dv3">
    <form action="./newReaderServlet" method="post">
        <table bgcolor="#f0ffff" cellpadding="2" width="60%">
            <tr>
                <td style="width: 100px" align="center">借书证号</td>
                <td><input type="text" name="inputId" style="width: 150px"/></td>
                <td rowspan="7" >
                    <div id="img" ></div>
                </td>
            </tr>
            <tr>
                <td align="center">姓名</td>
                <td><input type="text" name="inputName" style="width: 150px"/></td>
            </tr>
            <tr>
                <td align="center">密码</td>
                <td><input type="password" name="inputPwd" style="width: 150px"/></td>
            </tr>
            <tr>
                <td align="center">性别</td>
                <td >
                    <input type="radio" name="inputSex" value="male" checked="checked"/> 男 &nbsp;&nbsp;
                    <input type="radio" name="inputSex" value="female"/>女
                </td>
            </tr>
            <tr>
                <td align="center">已借书</td>
                <td><input type="text" name="inputHasbro" style="width: 150px"/></td>
            </tr>
            <tr>
                <td align="center">证件状态</td>
                <td>
                    <select name="inputStatus" style="width: 150px">
                        <option value="valid">有效</option>
                        <option value="loss">挂失</option>
                        <option value="logout">注销</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="center">读者角色</td>
                <td>
                    <input type="checkbox" name="inputRole" value="zero" checked="checked">0-读者
                    <input type="checkbox" name="inputRole" value="one">1-管理员
                </td>
            </tr>
            <tr>
                <td align="center">读者类别</td>
                <td>
                    <select name="inputType" style="width: 150px">
                        <%
                            ReaderTypeDAL readerTypeDAL = new ReaderTypeDAL();
                            ResultSet rs = readerTypeDAL.getReaderType();
                            try {
                                while (rs.next()) {
                                    String type = rs.getString(1);
                                    //System.out.println(dept);
                        %>
                        <option value="<%=type%>"><%=type%>
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
                <td>
                    <input type="file" name="myfile" id="myfile" value="图片文件">
                </td>
            </tr>
            <tr>
                <td align="center">单位</td>
                <td colspan="2">
                    <select name="inputDept" style="width: 200px">
                        <%
                            ReaderDAL readerDAL = new ReaderDAL();
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
                <td colspan="2"><input type="text" name="inputPhone" style="width: 200px"/></td>
            </tr>
            <tr>
                <td align="center">电子邮件</td>
                <td colspan="2"><input type="text" name="inputEmail" style="width: 200px"/></td>
            </tr>
            <tr>
                <%
                    Date dNow = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    //out.println("现在时间" + formatter.format(dNow));
                %>
                <td align="center">办证（当前）日期</td>
                <td colspan="2"><%=formatter.format(dNow)%></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="banzheng" value="确认办证" />&nbsp;&nbsp;&nbsp;
                    <input type="reset" name="myreset" value="取消"/></td>
            </tr>
            <tr>
                <input type="hidden" name="inputTime"value="<%=formatter.format(dNow)%>">
            </tr>
        </table>
    </form>
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