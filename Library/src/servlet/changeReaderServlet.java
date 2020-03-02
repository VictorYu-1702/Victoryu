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

@WebServlet("/changeReaderServlet")
public class changeReaderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    ResultSet rs;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String ids = request.getParameter("inputId");
        int id = Integer.valueOf(ids);
        String name = request.getParameter("inputName");
        String s = request.getParameter("inputSex");
        String sex = transformSex(s);
        String url = request.getParameter("myfile");
        String dept = request.getParameter("inputDept");
        String phone = request.getParameter("inputPhone");
        String email = request.getParameter("inputEmail");
        String time = request.getParameter("inputTime");
        String sql = "update TB_Reader set rdName=?,rdSex=?,rdDept=?,rdPhone=?,rdEmail=?,rdDateReg=?,rdPhoto=? where rdID=?";
        Object[] params = new Object[8];
        params[0] = name;
        params[1] = sex;
        params[2] = dept;
        params[3] = phone;
        params[4] = email;
        params[5] = time;
        params[6] = url;
        params[7] = id;
        System.out.println("Step 1");
        if (SQLHelper.ExecSql(sql, params)) {
            JOptionPane.showMessageDialog(null, "信息变更成功");
            response.sendRedirect("/changeReader.jsp?rdID="+id);
        } else {
            JOptionPane.showMessageDialog(null, "信息变更失败");
            response.sendRedirect("/newReader.jsp");
        }

    }
    public String transformSex(String s) {
        String sex = "";
        if (s.equals("male")) {
            sex = "男";
        } else if (s.equals("female")) {
            sex = "女";
        }
        return sex;
    }
}
