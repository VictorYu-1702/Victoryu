package dal;

import model.AbstractModel;
import model.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderDAL extends AbstractDAL {
    @Override
    public AbstractModel[] getAllObjects() throws Exception {
        return new AbstractModel[0];
    }
   /* int rdID;               //	读者编号/借书证号【主键】
    String rdName;    //	读者姓名
    String rdSex;    //	性别，男/女
    int rdType;    //读者类别【外键TB_ReaderType】【非空】
    String rdDept;   //	nvarchar (20)	单位代码/单位名称
    String rdPhone; //	nvarchar(25)	电话号码
    String rdEmail; //	nvarchar(25)	电子邮箱
     rdDateReg;    //	读者登记日期/办证日期
    byte[] rdPhoto;    //	读者照片
    String rdStatus; //		证件状态，3个：有效、挂失、注销
    int rdBorrowQty;  //		已借书数量(缺省值0)
    String rdPwd;   //		读者密码(初值123)，可加密存储
    int rdAdminRoles;//		管理角色，0-读者、1-借书证管理、2-图书管理、4-借阅管理、8-系统管理，可组合*/
    @Override
    public boolean add(AbstractModel object) throws Exception {
        if(object instanceof Reader ==false){
            throw new Exception("Can only handle Reader");
        }
        Reader reader=(Reader)object;
        String sql="insert into TB_Reader(rdID,rdName,rdSex,rdType,rdDept,rdPhone,"
                +"rdEmail,rdDateReg,rdPhoto,rdStatus,"
                +"rdBorrowQty,rdPwd,rdAdminRoles)"
                +" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[]params=new Object[13];
        params[0]=reader.getRdID();
        params[1]=reader.getRdName();
        params[2]=reader.getRdSex();
        params[3]=reader.getRdType();
        params[4]=reader.getRdDept();
        params[5]=reader.getRdPhone();
        params[6]=reader.getRdEmail();
        params[7]=reader.getRdDateReg();
        params[8]=reader.getRdPhoto();
        params[9]=reader.getRdStatus();
        params[10]=reader.getRdBorrowQty();
        params[11]=reader.getRdPwd();
        params[12]=reader.getRdAdminRoles();
        return SQLHelper.ExecSql(sql,params);
    }

    @Override
    public boolean delete(AbstractModel object) throws Exception {
        if(object instanceof Reader==false){
            throw new Exception("Can only handle Reader");
        }
        Reader reader=(Reader)object;
        String sql="delete from TB_Reader where rdID=?";
        Object[]params=new Object[]{reader.getRdID()};
        //int rows=SQLHelper.ExecSql(sql,params);
        return SQLHelper.ExecSql(sql,params);
    }

    @Override
    public boolean update(AbstractModel object) throws Exception {
        if(object instanceof Reader==false){
            throw new Exception("Can only handle Reader");
        }
        Reader reader=(Reader)object;
        String sql="update TB_Reader set rdName=?,"
                +"rdSex=?,rdType=?,rdDept=?,rdPhone=?,rdEmail=?,rdDateReg=?,"
                +"rdPhoto=?,rdStatus=?,rdPwd=?,rdAdminRoles=?"
                +" where rdID=?)";
        Object[]params=new Object[13];

        params[0]=reader.getRdName();
        params[1]=reader.getRdSex();
        params[2]=reader.getRdType();
        params[3]=reader.getRdDept();
        params[4]=reader.getRdPhone();
        params[5]=reader.getRdEmail();
        params[6]=reader.getRdDateReg();
        params[7]=reader.getRdPhoto();
        params[8]=reader.getRdStatus();
        params[9]=reader.getRdBorrowQty();
        params[10]=reader.getRdPwd();
        params[11]=reader.getRdAdminRoles();params[12]=reader.getRdID();
        return SQLHelper.ExecSql(sql,params);
    }

    @Override
    public AbstractModel getObjectById(int id) throws Exception {
        return null;
    }

    @Override
    public String[] getPrettyColumnNames() {
        return new String[0];
    }
    /* TB_Reader(rdID,rdName,rdSex,rdType,rdDept,rdPhone"
               +"rdEmail,rdDateReg,rdPhoto,rdStatus,"
               +"rdBorrowQty,rdPwd,rdAdminRoles)"*/
    @Override
    public String[] getMethodNames() {
        return new String[0];
    }
    private Reader initReader(ResultSet rs) throws SQLException {
        Reader reader=new Reader();
        reader.setRdID(rs.getInt("rdID"));
        reader.setRdName(rs.getString("rdName"));
        reader.setRdSex(rs.getString("rdSex"));
        reader.setRdType(rs.getInt("rdType"));
        reader.setRdDept(rs.getString("rdDept"));
        reader.setRdPhone(rs.getString("rdPhone"));
        reader.setRdEmail(rs.getString("rdEmail"));
        reader.setRdDateReg(rs.getDate("rdDateReg"));
        reader.setRdPhoto(rs.getString("rdPhoto"));
        reader.setRdStatus(rs.getString("rdStatus"));
        reader.setRdBorrowQty(rs.getInt("rdBorrowQty"));
        reader.setRdPwd(rs.getString("rdPwd"));
        reader.setRdAdminRoles(rs.getInt("rdAdminRoles"));
        return reader;
    }
    public Reader getObjectByID(int rdID) throws SQLException{
        //根据关键字查询ReaderType实体类对象的方法GetObjectByID()。
        Reader reader=null;
        ResultSet rs=SQLHelper.getResultSet("select rdID,rdName,rdSex,rdType,rdDept,rdPhone,"
                +"rdEmail,rdDateReg,rdPhoto,rdStatus,"
                +"rdBorrowQty,rdPwd,rdAdminRoles from TB_Reader where rdID= "+rdID);
        if(rs!=null){
            if(rs.next()){
                reader=initReader(rs);
            }
        }
        return reader;
    }
    public ResultSet getReaderDept() {

        ResultSet rs;
        String sql="select rdDept from TB_Reader GROUP BY rdDept ";
        rs=SQLHelper.getResultSet(sql);
        return rs;
    }
}
