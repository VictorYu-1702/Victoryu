import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDUtil {
    /**
     * 驱动 Class.forName("com.mysql.jdbc.Driver");//(1)注册数据库驱动
     */
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
    Connection getConnection(){
        try {
            Class.forName(driver);
            Connection connection= DriverManager.getConnection(url, user, password);
            return  connection;
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
