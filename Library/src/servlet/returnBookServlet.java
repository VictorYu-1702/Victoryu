package servlet;

import dal.SQLHelper;
import org.omg.CORBA.OBJ_ADAPTER;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/returnBookServlet")
public class returnBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String s=request.getParameter("BorrowID");
        int BorrowID=Integer.valueOf(s);
        String sss="select IsHasReturn from TB_Borrow WHERE BorrowID=?";
        Object []p={BorrowID};
        ResultSet rs = SQLHelper.getResultSet(sss, p);
        try {
            if(rs.next()){
                if(rs.getBoolean(1)){
                    JOptionPane.showMessageDialog(null, "操作失败，因为图书已归还");
                    response.sendRedirect("/MyBook.jsp");
                }else{
                    String s1=request.getParameter("bkID");
                    int bkID=Integer.valueOf(s1);
                    String s2=request.getParameter("rdID");
                    int rdID=Integer.valueOf(s2);
                    String DateRetAct=request.getParameter("DateRetAct");
                    String overday1=request.getParameter("OverDay");
                    int OverDay=Integer.valueOf(overday1);
                    String OverMoney1=request.getParameter("OverMoney");
                    float OverMoney=Float.valueOf(OverMoney1);
                    String PunishMoney1=request.getParameter("PunishMoney");
                    float PunishMoney=Float.valueOf(PunishMoney1);
                    //System.out.println(DateRetAct+"  "+OverDay+"  "+OverMoney+"   "+PunishMoney);
                    String sql ="update TB_Borrow set IdDateRetAct=? ,IdOverDay=?," +
                            "IdOverMoney=?,IdPunishMoney=?,IsHasReturn=? where BorrowID=? ";
                    Object[]params=new Object[6];
                    params[0]=DateRetAct; params[1]=OverDay; params[2]=OverMoney;
                    params[3]=PunishMoney; params[5]=BorrowID; params[4]=1;
                    String sql2="UPDATE TB_Book set  bkStatus='在馆' where bkID=? ";
                    Object[] params2 = {bkID};
                    String sql3="update TB_Reader SET rdBorrowQty=rdBorrowQty-1 where rdID=? ";
                    Object[] params3 = {rdID};
                    if (SQLHelper.ExecSql(sql, params)&&SQLHelper.ExecSql(sql2, params2)&&SQLHelper.ExecSql(sql3, params3)) {
                        JOptionPane.showMessageDialog(null, "图书归还成功");
                        response.sendRedirect("/MyBook.jsp");
                    } else {
                        JOptionPane.showMessageDialog(null, "图书归还失败");
                        response.sendRedirect("/returnBook.jsp?BorrowID="+BorrowID);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
