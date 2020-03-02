package servlet;

import dal.SQLHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

@WebServlet("/BorrowBookServlet")
public class BorrowBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
       String s1= request.getParameter("rdID");
       int rdID=Integer.valueOf(s1);
       String s2=request.getParameter("bkID");
       int bkID=Integer.valueOf(s2);
       String DateOut=request.getParameter("DateOut");
       String DateRetPlan=request.getParameter("DateRetPlan");
       String sql="insert into TB_Borrow (rdID,bkID,IdContinueTimes,IdDateOut,IdDateRetPlan,IsHasReturn)  " +
               "values(?,?,?,?,?,?)";
       Object[] params = new Object[6];
       params[0]=rdID;
       params[1]=bkID;
       params[2]=0;
       params[3]=DateOut;
        //System.out.println(DateOut);
       params[4]=DateRetPlan;//System.out.println(DateRetPlan);
       params[5]=0;
        String sql2="UPDATE TB_Book set  bkStatus='借出' where bkID=? ";
        Object[] params2 = {bkID};
        String sql3="update TB_Reader SET rdBorrowQty=rdBorrowQty+1 where rdID=? ";
        Object[] params3 = {rdID};
        //SQLHelper.ExecSql(sql2, params2);
        if (SQLHelper.ExecSql(sql, params)&&SQLHelper.ExecSql(sql2, params2)&&SQLHelper.ExecSql(sql3, params3)) {
            JOptionPane.showMessageDialog(null, "图书借阅成功");
            response.sendRedirect("/main.jsp");
        } else {
            JOptionPane.showMessageDialog(null, "图书借阅失败");
            response.sendRedirect("/newReader.jsp");
        }
    }
}
