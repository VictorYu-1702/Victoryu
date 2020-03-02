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

@WebServlet("/changeBookInfoServlet")
public class changeBookInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String bkName = request.getParameter("bkName");
        String s1 = request.getParameter("bkID");
        int bkID = Integer.valueOf(s1);
        String bkCode = request.getParameter("bkCode");
        String bkAuthor = request.getParameter("bkAuthor");
        String bkPress = request.getParameter("bkPress");
        String bkISBN = request.getParameter("bkISBN");
        String bkCatalog = request.getParameter("bkCatalog");
        String bkPrice1 = request.getParameter("bkPrice");
        float bkPrice = Float.valueOf(bkPrice1);
        String bkBrief = request.getParameter("bkBrief");
        String bkCover = request.getParameter("myfile");
        String bkCoverNow = request.getParameter("bkCoverNow");
        if (bkCover == "" || bkCover == null) {
            bkCover = bkCoverNow;

        } else {
            int index = bkCover.indexOf("web\\img");
            String result = bkCover.substring(index);
            //System.out.println(result);
            int i = result.indexOf("\\");
            String res = result.substring(i + 1);
            //System.out.println(res);
            bkCover = res;
        }
        String sql = "update TB_Book set bkCode=?,bkName=?,bkAuthor=?,bkPress=?,bkISBN=?,bkCatalog=?," +
                "bkBrief=?,bkCover=?,bkPrice=?, bkStatus=? where bkID=? ";
        Object[] params = new Object[11];
        params[0] = bkCode;
        params[1] = bkName;
        params[2] = bkAuthor;
        params[3] = bkPress;
        params[4] = bkISBN;
        params[5] = bkCatalog;
        params[6] = bkBrief.trim();
        params[7] = bkCover;
        params[8] = bkPrice;
        params[10] = bkID;
        String stausOpe = request.getParameter("changedStatus");
        String changeStatus = transformStatus(stausOpe);
        String statusNow = request.getParameter("bkStatus");
        boolean b = false;
        if (statusNow.equals("借出")) {
            JOptionPane.showMessageDialog(null, "图书当前外借中，无法修改信息");
            response.sendRedirect("changeBookInfo.jsp?bkID=" + bkID);
            b = false;
        } else if (changeStatus.equals(statusNow)) {
            /*JOptionPane.showMessageDialog(null, "所选择的修改状态与当前状态相同，无法修改");
            response.sendRedirect("/changeBookInfo.jsp?bkID=" + bkID);*/
            b = true;
            params[9] = "在馆";
        } else if (changeStatus.equals("遗失")) {
            int option = JOptionPane.showConfirmDialog(null, "将图书状态修改为遗失后，所有读者将无法借阅，是否确认更改？", "提示", JOptionPane.INFORMATION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                b = true;
                params[9] = "遗失";
            } else if (option == JOptionPane.CANCEL_OPTION) {
                b = false;
            } else if (option == JOptionPane.CLOSED_OPTION) {
                b = false;
            }
        } else if (changeStatus.equals("变卖")) {
            if (statusNow.equals("借出")) {
                JOptionPane.showMessageDialog(null, "图书当前外借中，无法变卖");
                response.sendRedirect("changeBookInfo.jsp?bkID=" + bkID);
                b = false;
            } else {
                int option = JOptionPane.showConfirmDialog(null, "将图书状态修改为变卖后，所有读者将无法借阅，是否确认更改？", "提示", JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    b = true;
                    params[9] = "变卖";
                } else if (option == JOptionPane.CANCEL_OPTION) {
                    b = false;
                } else if (option == JOptionPane.CLOSED_OPTION) {
                    b = false;
                }
            }
        } else if (changeStatus.equals("销毁")) {
            if (statusNow.equals("借出")) {
                b = false;
                JOptionPane.showMessageDialog(null, "图书当前外借中，无法销毁");
                response.sendRedirect("changeBookInfo.jsp?bkID=" + bkID);
            } else { //图书在馆 处理能否销毁
                int option = JOptionPane.showConfirmDialog(null, "将图书状态修改为销毁后，图书将从系统中删除，所有关于此书的记录都将消失。是否确认更改？", "提示", JOptionPane.INFORMATION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    String sql1 = " select * from TB_Borrow where bkID= ?";
                    Object[] params1 = {bkID};
                    ResultSet rs = SQLHelper.getResultSet(sql1, params1);
                    try {
                        if (rs.next()) {
                            String ss = " delete TB_Borrow WHERE bkID=? ";
                            Object[] pp = {bkID};
                            if (SQLHelper.ExecSql(ss, pp)) {
                                String ss1 = " delete TB_Book WHERE bkID=? ";
                                Object[] pp1 = {bkID};
                                if (SQLHelper.ExecSql(ss1, pp1)) {//成功删除图书 状态不需再修改
                                    b = false;
                                    JOptionPane.showMessageDialog(null, "图书销毁成功");
                                    String name222 = (String) request.getSession().getAttribute("name");
                                    response.sendRedirect("/main.jsp?name= " + java.net.URLEncoder.encode(name222, "UTF-8"));
                                } else {
                                    b = false;
                                    JOptionPane.showMessageDialog(null, "图书销毁失败");
                                    response.sendRedirect("/changeBookInfo.jsp?bkID=" + bkID);
                                }
                            }
                        } else {
                            String sql3 = "delete TB_Book WHERE bkID=? ";
                            Object[] params3 = {bkID};
                            if (SQLHelper.ExecSql(sql3, params3)) {
                                b = false;
                                JOptionPane.showMessageDialog(null, "图书销毁成功");
                                String name222 = (String) request.getSession().getAttribute("name");
                                response.sendRedirect("/main.jsp?name= " + java.net.URLEncoder.encode(name222, "UTF-8"));
                            } else {
                                JOptionPane.showMessageDialog(null, "图书销毁失败");
                                response.sendRedirect("/changeBookInfo.jsp?bkID=" + bkID);
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if (option == JOptionPane.CANCEL_OPTION) {
                    b = false;
                    response.sendRedirect("/changeBookInfo.jsp?bkID=" + bkID);
                } else if (option == JOptionPane.CLOSED_OPTION) {
                    b = false;
                    response.sendRedirect("/changeBookInfo.jsp?bkID=" + bkID);
                }
            }
        }
        for (int j = 0; j < params.length; j++) {
            System.out.println("式中第" + j + "各参数" + params[j]);
        }
        if (b) {
            if (SQLHelper.ExecSql(sql, params)) {
                JOptionPane.showMessageDialog(null, "图书信息修改成功");
                response.sendRedirect("/bookInfo.jsp?bkID=" + bkID);
            } else {
                JOptionPane.showMessageDialog(null, "图书信息修改失败");
                response.sendRedirect("/changeBookInfo.jsp?bkID=" + bkID);
            }
        }
    }

    public String transformStatus(String s) {
        String rt = "";
        if (s.equals("inLib")) {
            rt = "在馆";
        } else if (s.equals("lost")) {
            rt = "遗失";
        } else if (s.equals("sold")) {
            rt = "变卖";
        } else if (s.equals("delete")) {
            rt = "销毁";
        }
        return rt;
    }
}
