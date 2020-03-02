package servlet;

import dal.SQLHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

@WebServlet( "/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String s1=request.getParameter("rdID");

        String s2=(String)request.getSession().getAttribute("rdID");
        int rdID=Integer.valueOf(s1);
        String ope=request.getParameter("changedStatus");
        String status=request.getParameter("rdStatus");
        if(s1.equals(s2)){
            JOptionPane.showMessageDialog(null, "无法为自己办理挂失/解挂服务");
            response.sendRedirect("/LogoutReader.jsp?rdID="+rdID);
        }else if((status.equals("有效")&&ope.equals("loss"))||(status.equals("挂失")&&ope.equals("reloss"))){
            String sql="  update TB_Reader set rdStatus=? where rdID= ? ";
            Object params[]=new Object[2];
            params[0]=status.equals("有效")?"挂失":"有效";
            params[1]=rdID;
            if(SQLHelper.ExecSql(sql, params)){
                JOptionPane.showMessageDialog(null, "操作成功");
                response.sendRedirect("/LogoutReader.jsp?rdID="+rdID);
            }else{
                JOptionPane.showMessageDialog(null, "操作失败");
                response.sendRedirect("/LogoutReader.jsp?rdID="+rdID);
            }
        }else{
            if(status.equals("有效")&&ope.equals("reloss")){
                JOptionPane.showMessageDialog(null, "证件状态为有效，无法解挂");
                response.sendRedirect("/LogoutReader.jsp?rdID="+rdID);
            }else if(status.equals("挂失")&&ope.equals("loss")){
                JOptionPane.showMessageDialog(null, "证件状态为挂失，不能再次操作");
                response.sendRedirect("/LogoutReader.jsp?rdID="+rdID);
            }
        }
        }

}
