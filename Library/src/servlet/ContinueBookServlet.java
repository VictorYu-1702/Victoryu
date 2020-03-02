package servlet;

import dal.SQLHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ContinueBookServlet")
public class ContinueBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     request.setCharacterEncoding("utf-8");
        String s=request.getParameter("BorrowID");
        int BorrowID=Integer.valueOf(s);
        String s1=request.getParameter("bkID");
        int bkID=Integer.valueOf(s1);
        String s2=request.getParameter("rdID");
        int rdID=Integer.valueOf(s2);
        String s3=request.getParameter("canCont");
        int canCont=Integer.valueOf(s3);
        String s4=request.getParameter("hasCont");
        int hasCont=Integer.valueOf(s4);
        String s5=request.getParameter("OverDay");
        int OverDay=Integer.valueOf(s5);
        String s6=request.getParameter("hasBorrDay");
        int hasBorrDay=Integer.valueOf(s6);
        String DateRetPlan=request.getParameter("RetPlan");
        String sss=request.getParameter("continueDay");
        int continueDay=0;
        if(sss.equals("ten")){
            continueDay=10;
        }else if(sss.equals("fifteen")){
            continueDay=15;
        }else{
            continueDay=20;
        }
        System.out.println("获得续借天数"+continueDay);
       if(hasCont>canCont){
           JOptionPane.showMessageDialog(null, "超过规定续借次数，无法进行续借");
           response.sendRedirect("/MyBook.jsp");
       }else if(OverDay>0){
           JOptionPane.showMessageDialog(null, "借阅已超期，无法进行续借");
           response.sendRedirect("/MyBook.jsp");
       }else if(hasBorrDay<25){
           JOptionPane.showMessageDialog(null, "借阅未满25天，无法进行续借");
           response.sendRedirect("/MyBook.jsp");
       }else{
           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
           Date date = null; // 指定日期
           try {
               date = dateFormat.parse(DateRetPlan);
               long time = date.getTime(); // 得到指定日期的毫秒数
               long day=continueDay;
               day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
               time+=day; // 相加得到新的毫秒数
               Date newDate = new Date(time); // 指定日期加上20天
               String DateRetChange=dateFormat.format(newDate);
               //System.out.println(dateFormat.format(date));// 输出格式化后的日期
               System.out.println("修改后应还日期"+DateRetChange);
               hasCont=hasCont+1;

               String sql="  update TB_Borrow SET IdContinueTimes=?,IdDateRetPlan=? where BorrowID=? ";
               Object []params=new Object[3];
               params[0]=hasCont;
               params[1]=DateRetChange;  params[2]=BorrowID;
               if(SQLHelper.ExecSql(sql, params)){
                   String msg="图书续借成功，请在 "+DateRetChange+" 前归还图书";
                   JOptionPane.showMessageDialog(null, msg);
                   response.sendRedirect("/MyBook.jsp");
               }else{
                   JOptionPane.showMessageDialog(null, "图书续借失败");
                   response.sendRedirect("/continueBook.jsp");
               }

           } catch (ParseException e) {
               e.printStackTrace();
           }


       }
    }
}
