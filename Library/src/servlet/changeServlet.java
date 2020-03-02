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

@WebServlet("/changeServlet")
public class changeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = (String) request.getSession().getAttribute("name");
        System.out.println("此页面名字"+name);
        String s1=request.getParameter("inputId");
        int rdID=Integer.valueOf(s1);
        String s2=request.getParameter("select");
        String sql="select * from TB_Reader where rdID=? ";
        Object [] params={rdID};
        ResultSet rs = SQLHelper.getResultSet(sql, params);
        try {
            if(rs.next()){
                if(s2.equals("changerole")){
                    System.out.println("“运行2”");
                    response.sendRedirect("./changeRole.jsp?rdID="+s1);
                }else if(s2.equals("changeReader")){
                    System.out.println("“运行3”");
                    response.sendRedirect("./changeReader.jsp?rdID="+s1);
                }else if(s2.equals("logoutReader")){
                    System.out.println("“运行4”");
                    response.sendRedirect("./LogoutReader.jsp?rdID="+s1);
                }else if(s2.equals("deleteReader")){
                    System.out.println("“运行5”");
                    response.sendRedirect("./DeleteReader.jsp?rdID="+s1);
                }else if(s2.equals("changeRdType")){
                    System.out.println("“运行5”");
                    response.sendRedirect("./changeRdType.jsp?rdID="+s1);
                }
            }else{
                JOptionPane.showMessageDialog(null, "用户不存在");
                response.sendRedirect("/main.jsp?name="+java.net.URLEncoder.encode(name,"UTF-8")+"");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*if(s1==""){
            JOptionPane.showMessageDialog(null, "请输入借书证号");
            response.sendRedirect("/changeRole.jsp?rdID=");
        }else */
    }
}
