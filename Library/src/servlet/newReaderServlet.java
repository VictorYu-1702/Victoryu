package servlet;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
import java.util.Date;

@WebServlet("/newReaderServlet")
public class newReaderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ResultSet rs;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String ids = request.getParameter("inputId");
        int id=Integer.valueOf(ids);
        String name = request.getParameter("inputName");
        String psw = request.getParameter("inputPwd");
        String s = request.getParameter("inputSex");
        String sex=transformSex(s);
        String hasbro = request.getParameter("inputHasbro");
        String status = request.getParameter("inputStatus");
        String[] roles = request.getParameterValues("inputRole");
        int role = 0;
        for (int i = 0; i < roles.length; i++) {
            //System.out.println(roles[i]);
            int r = transformRole(roles[i]);
            //System.out.println(roles[i]+r);
            role = role + r;
        }
        //System.out.println(role);
        String t = request.getParameter("inputType");
        int type=transformType(t);
        //System.out.println(type);
        String url = request.getParameter("myfile");
        String dept = request.getParameter("inputDept");
        String phone = request.getParameter("inputPhone");
        String email = request.getParameter("inputEmail");
        String time = request.getParameter("inputTime");

        System.out.println("修改后的参数时间"+time);
        String sql1 = "select rdID from TB_Reader where rdID=?";
        Object[] param1 = {id};
       // System.out.println("Step 1");
        rs = SQLHelper.getResultSet(sql1, param1);
        //System.out.println("Step 2");
        try {
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "用户名已存在");
                response.sendRedirect("/newReader.jsp");
            } else {
                System.out.println("Step 3");
                String sql = "insert into TB_Reader(rdID,rdName,rdSex,rdType,rdDept,rdPhone," +
                        "rdEmail,rdDateReg,rdPhoto,rdStatus," +
                        "rdBorrowQty,rdPwd,rdAdminRoles)" +
                        " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                Object[] params = new Object[13];

                params[0] = id;
                params[1] = name;
                params[2] = sex;
                params[3] = type;
                params[4] = dept;
                params[5] = phone;
                params[6] = email;
                params[7] = time;
                params[8] = url;
                params[9] = transformStatus(status);
                params[10] = 0;
                params[11] = psw;
                params[12] = role;
                if (SQLHelper.ExecSql(sql, params)) {
                    JOptionPane.showMessageDialog(null, "借书证办理成功");
                    response.sendRedirect("/newReader.jsp");
                } else {
                    JOptionPane.showMessageDialog(null, "借书证办理失败");
                    response.sendRedirect("/newReader.jsp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int transformRole(String s) {
        int n = 0;
        if (s.equals("zero")) {
            n = 0;
        } else if (s.equals("one")) {
            n = 1;
        }/* else if (s.equals("two")) {
            n = 2;
        } else if (s.equals("four")) {
            n = 4;
        } else if (s.equals("eight")) {
            n = 8;
        }*/
        return n;
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
    public String transformSex(String s){
        String sex="";
        if(s.equals("male")){
            sex="男";
        }else if(s.equals("female")){
            sex="女";
        }
        return sex;
    }
    public String transformStatus(String s){
        String s1="";
        if(s.equals("valid")){
            s1="有效";
        }else if(s.equals("loss")){
            s1="挂失";
        }else if(s.equals("logout")){
            s1="注销";
        }
        return s1;
    }
}
