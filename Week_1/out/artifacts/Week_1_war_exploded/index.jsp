<%--
  Created by IntelliJ IDEA.
  User: 27347
  Date: 2020/2/15
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
      <style type="text/css">
      .position{
      position: absolute;
      top: 20%;
      left: 50%;
          transform: translate(-50%, 0);
      }
      </style>
  </head>
  <body >
  <div class="position">
  <form action="./loginServlet" method="post">
    <table>
      <tr>
        <td>用户名：</td>
        <td>
          <input type="text"  width="200px" name="userName" >
        </td>
      </tr>
      <tr>
        <td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
        <td>
          <input type="password " width="200px" name="psw" >
        </td>
      </tr>
        <tr>
            <td>

            </td>
            <td><input type="submit" value="登录">
                <input type="reset" value="取消">
            </td>
        </tr>
    </table>

  </form>
  </div>
  </body>
</html>
