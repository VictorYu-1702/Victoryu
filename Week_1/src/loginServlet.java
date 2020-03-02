import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name= request.getParameter("userName");
        String psw=request.getParameter("psw");
        System.out.println(name+"    "+psw);
        UserDao dao=new UserDao();
        boolean b=dao.findUser(name,psw);
        if(b){
            JOptionPane.showMessageDialog(null, "登录成功！");
        }else {
            JOptionPane.showMessageDialog(null, "登录失败");
            response.sendRedirect("/index.jsp");
        }
    }
}
