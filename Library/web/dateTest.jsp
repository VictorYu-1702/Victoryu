<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: Victor Long
  Date: 2019/12/25
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
按本地时区输出当前日期
<%
    Date myDate = new Date();
    out.println(myDate.toLocaleString());

%>
<hr/>
按照指定格式打印日期
<%
    Date dNow = new Date();

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss a E ");
    out.println("现在时间" + formatter.format(dNow));
%>
<hr/>
计算日期之间的间隔
<%
    String input = "2020-05-01";
    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
    Date d1 = null;
    try {
        d1 = formatter2.parse(input);
    } catch (ParseException e) {
        out.println("unparseable using " + formatter);
    }
    Date d2 = new Date();
    long diff = d2.getTime() - d1.getTime();
    out.println("Difference is " + (diff / (1000 * 60 * 60 * 24)) + " days.");
%>
<hr/>
日期的加减运算
方法：用Calendar类的add()方法
<%
    Calendar now = Calendar.getInstance();
    SimpleDateFormat formatter3 = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
    out.println("It is now " + formatter3.format(now.getTime()));
    now.add(Calendar.DAY_OF_YEAR,-(365*2));
    out.println("<br>");
    out.println("Two years ago was " + formatter3.format(now.getTime()));
    SimpleDateFormat formatter4 = new SimpleDateFormat("yyyy-MM-dd  ");
    out.println("现在时间" + formatter4.format(now.getTime()));
%>

<%
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
    Date date = dateFormat.parse("2019-05-10"); // 指定日期
    long time = date.getTime(); // 得到指定日期的毫秒数
    long day=6;
    day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
    time+=day; // 相加得到新的毫秒数

    Date newDate = new Date(time); // 指定日期加上20天
    System.out.println(dateFormat.format(date));// 输出格式化后的日期
    System.out.println(dateFormat.format(newDate));


%>
<%
    String s="E:\\2019semes\\数据库\\Library\\web\\img\\uml.jpg";
    int index=s.indexOf("web\\img");
    String result=s.substring(index);
    System.out.println(result);
    int i=result.indexOf("\\");
    String res=result.substring(i+1);
    System.out.println(res);
%>
</body>
</html>
