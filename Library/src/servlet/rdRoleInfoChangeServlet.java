package servlet;

import dal.SQLHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

@WebServlet("/rdRoleInfoChangeServlet")
public class rdRoleInfoChangeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String rdStatus=request.getParameter("inputStatus");
        String s1=request.getParameter("inputId");
        int rdID=Integer.valueOf(s1);
        if(!rdStatus.equals("有效")){
            JOptionPane.showMessageDialog(null, "证件状态无效，无法修改信息。请先联系管理员修改证件状态！");
            response.sendRedirect("/rdRoleInfoChange.jsp?rdID="+rdID);
        }else{
            String name=request.getParameter("inputName");
            String s2=request.getParameter("inputSex");
            String sex=s2.equals("male")?"男":"女";
            String dept=request.getParameter("inputDept");
            String phone=request.getParameter("inputPhone");
            String email=request.getParameter("inputEmail");
            String pw1=request.getParameter("inputPsw");
            String pw2=request.getParameter("inputRePsw");
            if(!pw1.equals(pw2)){
                JOptionPane.showMessageDialog(null, "密码不一致，提交失败！请重新输入");
                response.sendRedirect("/rdRoleInfoChange.jsp?rdID="+rdID);
            }else{
                String psw=pw1;
                String url=request.getParameter("myfile");
                String sql="  update TB_Reader set rdName=?,rdSex=?,rdDept=?,rdPhone=?,rdEmail=?,rdPhoto=?,rdPwd=? where rdID=? ";
                Object[]params=new Object[8];
                params[0]=name;params[1]=sex;params[2]=dept;params[3]=phone;
                params[4]=email;params[5]=url;params[6]=psw;params[7]=rdID;
                /*for(int i=0;i<params.length;i++){
                    System.out.println("第"+i+"各参数："+params[i]);
                }*/
                if(SQLHelper.ExecSql(sql,params)) {
                    JOptionPane.showMessageDialog(null, "修改成功");
                    response.sendRedirect("/rdRoleInfoChange.jsp?rdID="+rdID);
                }else{
                    JOptionPane.showMessageDialog(null, "修改失败");
                    response.sendRedirect("/rdRoleInfoChange.jsp?rdID="+rdID);
                }
            }
        }

    }
}
