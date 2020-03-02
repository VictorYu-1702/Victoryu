package servlet;

import dal.SQLHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/deleteReaderServlet")
public class deleteReaderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("运行实测点11");
        String s1=request.getParameter("rdID");
        String s2=(String)request.getSession().getAttribute("rdID");
        int rdID=Integer.valueOf(s1);
        String status=request.getParameter("rdStatus");
        System.out.println("rdID   "+rdID+"  status   "+status);
        if(s1.equals(s2)){
            JOptionPane.showMessageDialog(null, "无法注销自己的借书证");
            response.sendRedirect("/LogoutReader.jsp?rdID="+rdID);
        }else {
            String sql1=" select IdPunishMoney,IsHasReturn from TB_Borrow where rdID= ?";
            Object[]params1={rdID};
            ResultSet rs = SQLHelper.getResultSet(sql1, params1);
            try {
                if(rs.next()){
                    boolean IsHasReturn=rs.getBoolean(2);
                    float IdPunishMoney=rs.getFloat(1);
                    System.out.println("IsHasReturn "+IsHasReturn+"  IdPunishMoney "+IdPunishMoney);
                    if(!IsHasReturn){
                        JOptionPane.showMessageDialog(null, "注销失败，该读者存在未归还的书籍");
                        response.sendRedirect("/DeleteReader.jsp?rdID="+rdID);
                    }else if(IdPunishMoney!=0){
                        JOptionPane.showMessageDialog(null, "注销失败，该读者未缴清所有罚款");
                        response.sendRedirect("/DeleteReader.jsp?rdID="+rdID);
                    }else{
                        String sql2="delete TB_Borrow WHERE rdID=? ";
                        Object []params2={rdID};
                        if(SQLHelper.ExecSql(sql2,params2)){
                            String sql3=" delete TB_Reader WHERE rdID=? ";
                            Object []params3={rdID};
                            if(SQLHelper.ExecSql(sql3,params3)){
                                JOptionPane.showMessageDialog(null, "注销成功");
                                response.sendRedirect("/DeleteReader.jsp");
                            }else{
                                JOptionPane.showMessageDialog(null, "注销操作失败");
                                response.sendRedirect("/DeleteReader.jsp?rdID="+rdID);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "注销操作失败");
                            response.sendRedirect("/DeleteReader.jsp?rdID="+rdID);
                        }
                    }
                }else{
                    String sql3=" delete TB_Reader WHERE rdID=? ";
                    Object []params3={rdID};
                    if(SQLHelper.ExecSql(sql3,params3)){
                        JOptionPane.showMessageDialog(null, "注销成功");
                        response.sendRedirect("/DeleteReader.jsp");
                    }else{
                        JOptionPane.showMessageDialog(null, "注销操作失败");
                        response.sendRedirect("/DeleteReader.jsp?rdID="+rdID);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
