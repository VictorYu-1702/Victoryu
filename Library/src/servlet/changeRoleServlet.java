package servlet;

import dal.SQLHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

@WebServlet("/changeRoleServlet")
public class changeRoleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String s1=request.getParameter("rdID");
        int rdID=Integer.valueOf(s1);
        String s2=request.getParameter("rdRole");
        int rdRole=Integer.valueOf(s2);
        String change=request.getParameter("changedRole");
        System.out.println("测试："+rdID+change);
        if((rdRole==1&&change.equals("reset"))||(rdRole==0&&change.equals("set"))){
            String sql=" update TB_Reader SET rdAdminRoles=? where rdID=? ";
            Object []params=new Object[2];
            params[0]=change.equals("reset")?0:1;
            params[1]=rdID;
            if(SQLHelper.ExecSql(sql, params)){
                JOptionPane.showMessageDialog(null, "权限更改成功");
                response.sendRedirect("/changeRole.jsp?rdID="+rdID);
            }else{
                JOptionPane.showMessageDialog(null, "权限更改失败");
                response.sendRedirect("/changeRole.jsp?rdID="+rdID);
            }
        }else{
            if(rdRole==0&&change.equals("reset")){
                JOptionPane.showMessageDialog(null, "此读者没有管理员权限，故无法取消其权限");
                response.sendRedirect("/changeRole.jsp?rdID="+rdID);
            }else if(rdRole==1&&change.equals("set")){
                JOptionPane.showMessageDialog(null, "此读者已具有管理员权限，故无法再添加权限");
                response.sendRedirect("/changeRole.jsp?rdID="+rdID);
            }
        }
    }
}
