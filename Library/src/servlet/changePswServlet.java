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

@WebServlet( "/changePswServlet")
public class changePswServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ResultSet rs;
    private ResultSet rs2;
    String name="";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("inputId");

        String p1=request.getParameter("inputnewPwd");
        String p2=request.getParameter("inputrenewPwd");
        //用户登录成功返回3
        //用户验证码正确，密码错误返回2
        //用户验证码错误返回1
        System.out.println("验证中。。。");
        /*response.setContentType("text/html,charset=utf-8");
        response.setCharacterEncoding("utf-8");*/
        int i = checkUser(request, response);
        switch(i)
        {
            case -1: //用户不存在
                JOptionPane.showMessageDialog(null, "不存在此编号的读者");
                response.sendRedirect("/changePsw.jsp");
                break;
            case 2: //输入的原始密码不正确
                JOptionPane.showMessageDialog(null, "原密码错误");
                response.sendRedirect("/changePsw.jsp");
                break;
            case 3: //新旧密码一致 修改密码
                String sql2="update TB_Reader set rdPwd=? where rdID=? ";
                Object[] params = new Object[2];
                params[0] =p1;
                params[1] = id ;
                if (SQLHelper.ExecSql(sql2, params)) {
                    JOptionPane.showMessageDialog(null, "密码修改成功");
                    response.sendRedirect("/changePsw.jsp");
                } else {
                    JOptionPane.showMessageDialog(null, "密码修改失败");
                    response.sendRedirect("/changePsw.jsp");
                }
                break;
            case 4://新旧密码不一致
                JOptionPane.showMessageDialog(null, "请保持密码一致");
                response.sendRedirect("/changePsw.jsp");
                break;
        }
    }

    //写一个专门判断用户登陆的函数
    public int checkUser(HttpServletRequest request, HttpServletResponse response)
    {
        //b来判断返回的信息类型
        String id = request.getParameter("inputId");
        int rdID=Integer.valueOf(id);
        String pwd = request.getParameter("inputPwd");
        String sql = "select * from TB_Reader where rdID =?";
        Object [] param = {rdID};

        String p1=request.getParameter("inputnewPwd");
        String p2=request.getParameter("inputrenewPwd");
        rs = SQLHelper.getResultSet(sql, param);
        try {
            if(rs.next())
            {
                if(!rs.getString(12).equals(pwd))
                {
                    //密码不正确
                    return 2;
                }else if(rs.getString(12).equals(pwd))
                {
                    //原密码都正确
                    if(p1.equals(p2)){
                        return 3;
                    }else{

                    return 4;
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {

        }
        return -1;
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
