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

@WebServlet("/newBookServlet")
public class newBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String s1 = request.getParameter("bkID");
        String bkCode = request.getParameter("bkCode");
        String bkName = request.getParameter("bkName");
        String bkAuthor = request.getParameter("bkAuthor");
        String bkPress = request.getParameter("bkPress");
        String bkDatePress = request.getParameter("bkDatePress");
        String bkISBN = request.getParameter("bkISBN");
        String bkCatalog = request.getParameter("bkCatalog");
        if (s1 == "" || s1 == null || bkCode == "" || bkCode == null || bkName == "" || bkName == null
                || bkCode == "" || bkCode == null || bkCatalog == "" || bkCatalog == null) {
            JOptionPane.showMessageDialog(null, "图书信息不完整，无法提交");
            response.sendRedirect("/newBook.jsp");
        } else {
            int bkID = Integer.valueOf(s1);
            String sql1 = "select * from TB_Book where bkID=? ";
            Object[] params1 = {bkID};
            ResultSet rs = SQLHelper.getResultSet(sql1, params1);
            try {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "添加失败，书库中存在相同序号的书");
                    response.sendRedirect("/newBook.jsp");
                } else {
                    String bkCover1 = request.getParameter("myfile");
                    int index=bkCover1.indexOf("img");
                    String result=bkCover1.substring(index);
                    int index2=result.indexOf("\\");
                    String res=result.substring(index2+1);
                    String bkCover="img/"+res;
                    String bkPrice1 = request.getParameter("bkPrice");
                    String bkDateIn = request.getParameter("dateNow");
                    String bkBrief = request.getParameter("bkBrief");
                    String sql = "set IDENTITY_INSERT  TB_Book on  " +
                            "insert into TB_Book(bkID,bkCode,bkName,bkAuthor,bkPress,bkDatePress," +
                            "bkISBN,bkCatalog,bkPrice,bkDateIn,bkBrief,bkCover,bkStatus) " +
                            "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    Object[] params = new Object[13];
                    params[0] = bkID;
                    params[1] = bkCode;
                    params[2] = bkName;
                    params[3] = bkAuthor;
                    params[4] = bkPress;
                    params[5] = bkDatePress;
                    params[6] = bkISBN;
                    params[7] = bkCatalog;
                    params[8] = (bkPrice1==""||bkPrice1==null)?0:Float.valueOf(bkPrice1);
                    params[9] = bkDateIn;
                    params[10] = bkBrief;
                    params[11] = bkCover;
                    params[12] = "在馆";
                    for (int i = 0; i < params.length; i++) {
                        System.out.println("插入图书 参数" + (i + 1) + params[i]);
                    }
                    if (SQLHelper.ExecSql(sql, params)) {
                        JOptionPane.showMessageDialog(null, "新书添加成功");
                        response.sendRedirect("/newBook.jsp");
                    } else {
                        JOptionPane.showMessageDialog(null, "新书添加失败");
                        response.sendRedirect("/newBook.jsp");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
