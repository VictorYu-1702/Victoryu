import java.sql.*;

public class MySQLdEMO {
    public static String driver = "com.mysql.jdbc.Driver";
    /**
     * 连接字符串
     */
    public static String url = "jdbc:mysql://localhost:3306/Test";

    /**
     * 用户名
     */
    public static String user = "root";
    /**
     * 密码
     */
    public static String password = "990724longyu";
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connection=null;
        Statement statement=null;
        Class.forName(driver);
        connection= DriverManager.getConnection(url,user,password);
        statement=connection.createStatement();
        String sql="select sname,psw from Student";
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()){
            String name=rs.getString("sname");
            String psw=rs.getString("psw");
            System.out.println(name+"   "+psw);}

    }
}
