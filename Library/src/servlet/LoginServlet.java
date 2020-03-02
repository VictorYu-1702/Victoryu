package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import dal.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ResultSet rs;
    String name="";
    int rdRole=0;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        //用户登录成功返回3
        //用户验证码正确，密码错误返回2
        //用户验证码错误返回1
        System.out.println("验证中。。。");
        /*response.setContentType("text/html,charset=utf-8");
        response.setCharacterEncoding("utf-8");*/
        int i = checkUser(request, response);
        switch(i)
        {
            case 1:
                response.sendRedirect("/index.jsp?message=1");
                break;
            case 2:
                response.sendRedirect("/index.jsp?message=2");
                break;
            case 3:
                //System.out.println("name:"+name);
                String url="/main.jsp?id="+id+"&name="+java.net.URLEncoder.encode(name,"UTF-8")+"";
                //"/main.jsp?id="+id+"&name="+java.net.URLEncoder.encode(name,"GBK")+""
               // url=new String(url.getBytes("GBK"),"ISO-8859-1");
                //request.getSession().setAttribute("rdID", id);
                response.sendRedirect(url);
                break;
            case -1:
                response.sendRedirect("/index.jsp?message=-1");
                break;
        }
    }

    //写一个专门判断用户登陆的函数
    public int checkUser(HttpServletRequest request, HttpServletResponse response)
    {
        //b来判断返回的信息类型
        //对登录进行验证
        String id = request.getParameter("id");
        int rdID=Integer.valueOf(id);
        String pwd = request.getParameter("pwd");
        String yzm = request.getParameter("yzm");
        String sessionYzm = (String)request.getSession().getAttribute("randomString");

        String sql = "select * from TB_Reader where rdID =?";
        Object [] param = {rdID};
        rs = SQLHelper.getResultSet(sql, param);
        try {
            if(rs.next())
            {
                if(!yzm.equals(sessionYzm))
                {
                    //验证码错误
                    return 1;
                }else if(!rs.getString(12).equals(pwd))
                {
                    //密码不正确
                    return 2;
                }else if(rs.getString(12).equals(pwd)&&yzm.equals(sessionYzm))
                {
                    //验证码和密码都正确
                    HttpSession Session =request.getSession(true);
                    rdRole=rs.getInt(13);
                    name=rs.getString(2);
                    Session.setAttribute("name",name);//放到session
                    Session.setAttribute("rdID",id);
                    Session.setAttribute("rdRole",rdRole);
                    return 3;
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
