package servlet;

import dal.SQLHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

@WebServlet("/changeRdTypeServlet")
public class changeRdTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String s1=request.getParameter("rdID");
        int rdID=Integer.valueOf(s1);
        String typeNow=request.getParameter("rdTypeNow");
        String typechanged=request.getParameter("changedRdType");
        String name=request.getParameter("rdName");
        if(typechanged==null){
            JOptionPane.showMessageDialog(null, "请选择需要修改的读者类别");
            response.sendRedirect("/changeRdType.jsp?rdID="+rdID);
        }else if(typeNow.equals(typechanged)){
            JOptionPane.showMessageDialog(null, "选择的类别与当前类别相同，无法修改");
            response.sendRedirect("/changeRdType.jsp?rdID="+rdID);
        }else{
            int rdType=transformType(typechanged);
            String sql="update TB_Reader set rdType=? where rdID=? ";
            Object []params=new Object[2];
            params[0]=rdType;params[1]=rdID;
            if(SQLHelper.ExecSql(sql,params)){
                String msg="已将读者 "+name+" 从 "+typeNow+" 改为 "+typechanged;
                JOptionPane.showMessageDialog(null, msg);
                response.sendRedirect("/changeRdType.jsp?rdID="+rdID);
            }else {
                JOptionPane.showMessageDialog(null, "修改失败");
                response.sendRedirect("/changeRdType.jsp?rdID="+rdID);
            }

        }
      /*  System.out.println("当前类型"+typeNow);
        System.out.println("修改后类型"+typechanged);*/
    }
    public  int transformType(String s){
        int n=0;
        if(s.equals("教师")){
            n=10;
        }else if(s.equals("本科生")){
            n=20;
        }else if(s.equals("专科生")){
            n=21;
        }else if(s.equals("硕士研究生")){
            n=30;
        }else if(s.equals("博士研究生")){
            n=31;
        }
        return n;
    }

}
