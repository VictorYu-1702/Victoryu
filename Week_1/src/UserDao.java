import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {


    boolean findUser(String userName,String userPassword){
        BDUtil db=new BDUtil();
        Connection connection=db.getConnection();
        try {
            Statement statement=connection.createStatement();
            String sql="select sname,psw from Student";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                String name=rs.getString("sname");
                String psw=rs.getString("psw");
                //System.out.println(name+"   "+psw);
                if(name.equals(userName)&&psw.equals(userPassword)){
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
