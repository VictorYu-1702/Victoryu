package servlet;


import dal.ReaderDAL;
import model.Reader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/BorrowServlet")
public class BorrowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int bkID = Integer.valueOf(request.getParameter("bkID"));
        String bkStatus = request.getParameter("bkStatus");
        String s = (String) request.getSession().getAttribute("rdID");
        String name = (String) request.getSession().getAttribute("name");
        int rdID = Integer.valueOf(s);
        System.out.println(bkID + ";" + bkStatus + ";" + bkID);
        ReaderDAL readerDAL = new ReaderDAL();
        try {
            Reader reader = readerDAL.getObjectByID(rdID);
            String rdStatus = reader.getRdStatus();
            // System.out.println("证件状态："+rdStatus);
            if (!bkStatus.equals("在馆")) {
                JOptionPane.showMessageDialog(null, "此图书不在馆，无法借阅");
                response.sendRedirect("/main.jsp?name= " +java.net.URLEncoder.encode(name,"UTF-8")
                );
            } else if (rdStatus.equals("挂失")) {
                JOptionPane.showMessageDialog(null, "证件处于挂失状态，暂不能借书！请先联系管理员修改证件状态");
                response.sendRedirect("/main.jsp");
            } else if (rdStatus.equals("注销")) {
                JOptionPane.showMessageDialog(null, "读者证件已注销，无法借书");
                response.sendRedirect("/main.jsp");
            } else {
                response.sendRedirect("/borrowBook.jsp?rdID=" + rdID + "&bkID=" + bkID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
