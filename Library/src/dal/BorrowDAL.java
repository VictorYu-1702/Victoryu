package dal;

import model.AbstractModel;
import model.Borrow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAL extends AbstractDAL {
    public List<Borrow> borrows  = new ArrayList<Borrow>();
    @Override
    public AbstractModel[] getAllObjects() throws Exception {
        return new AbstractModel[0];
    }
    /*long BorrowID;    //	借书顺序号【主键】
    int rdID;    //	读者序号【外键TB_Reader】
    int bkID;    //	图书序号【外键TB_Book】
    int IdContinueTimes;//	Int	续借次数（第一次借时，记为0）
    Date IdDateOut;//	DateTime	借书日期
    Date IdDateRetPlan;    //DateTime	应还日期
    Date IdDateRetAct;//	DateTime	实际还书日期
    int IdOverDay;   //	Int	超期天数
    float IdOverMoney;  //	Money	超期金额（应罚款金额）
    float IdPunishMoney; //	Money	罚款金额
    boolean IsHasReturn;//Bit	是否已经还书，缺省为0-未还
    String OperatorLend; //	Nvarchar(20)	借书操作员
    String OperatorRet; //	Nvarchar(20)	还书操作员*/
    @Override
    public boolean add(AbstractModel object) throws Exception {
        if(object instanceof Borrow ==false){
            throw new Exception("Can only handle Borrow");
        }
        Borrow borrow=(Borrow)object;
        String sql="insert into TB_Borrow(BorrowID,rdID,bkID,IdContinueTimes,IdDateOut,IdDateRetPlan,"
                +"IdDateRetAct,IdOverDay,IdOverMoney,IdPunishMoney,IsHasReturn,"
                +"OperatorLend,OperatorRet) "
        +"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[]params=new Object[13];
        params[0]=borrow.getBorrowID();
        params[1]=borrow.getRdID();
        params[2]=borrow.getBkID();
        params[3]=borrow.getIdContinueTimes();
        params[4]=borrow.getIdDateOut();
        params[5]=borrow.getIdDateRetPlan();
        params[6]=borrow.getIdDateRetAct();
        params[7]=borrow.getIdOverDay();
        params[8]=borrow.getIdOverMoney();
        params[9]=borrow.getIdPunishMoney();
        params[10]=borrow.isHasReturn();
        params[11]=borrow.getOperatorLend();
        params[12]=borrow.getOperatorRet();

        return SQLHelper.ExecSql(sql,params);
    }

    @Override
    public boolean delete(AbstractModel object) throws Exception {
        if(object instanceof Borrow ==false){
            throw new Exception("Can only handle Borrow");
        }
        Borrow borrow=(Borrow)object;
        String sql="delete from TB_Borrow where BorrowID=?";
        Object[]params=new Object[]{borrow.getBorrowID()};
        //int rows=SQLHelper.ExecSql(sql,params);
        return SQLHelper.ExecSql(sql,params);
    }

    @Override
    public boolean update(AbstractModel object) throws Exception {
        if(object instanceof Borrow ==false){
            throw new Exception("Can only handle Borrow");
        }
        Borrow borrow=(Borrow)object;
        String sql="update TB_Borrow set rdID=?,bkID=?,IdContinueTimes=?,IdDateOut=?,"
                +"IdDateRetPlan=?,IdDateRetAct=?,IdOverDay=?,IdOverMoney=?,IdPunishMoney=?,IsHasReturn=?,"
                +"OperatorLend=?,OperatorRet=? where BorrowID=? ";

        Object[]params=new Object[13];

        params[0]=borrow.getRdID();
        params[1]=borrow.getBkID();
        params[2]=borrow.getIdContinueTimes();
        params[3]=borrow.getIdDateOut();
        params[4]=borrow.getIdDateRetPlan();
        params[5]=borrow.getIdDateRetAct();
        params[6]=borrow.getIdOverDay();
        params[7]=borrow.getIdOverMoney();
        params[8]=borrow.getIdPunishMoney();
        params[9]=borrow.isHasReturn();
        params[10]=borrow.getOperatorLend();
        params[11]=borrow.getOperatorRet();
        params[12]=borrow.getBorrowID();
        return SQLHelper.ExecSql(sql,params);
    }

    @Override
    public AbstractModel getObjectById(int id) throws Exception {
        return null;
    }
    public Borrow getObjectByID(int BorrowID) throws SQLException {
        //根据关键字查询ReaderType实体类对象的方法GetObjectByID()。
        Borrow borrow=null;
        ResultSet rs=SQLHelper.getResultSet(" select * from TB_Borrow where BorrowID= "+BorrowID);
        if (rs!=null){
            if (rs.next()){
                borrow=initBorrow(rs);
            }
        }
        return borrow;
    }
    public Borrow getObjectByBkID(int bkID) throws SQLException {
        //根据关键字查询ReaderType实体类对象的方法    查询借阅记录中未归还的书
        Borrow borrow=null;
        ResultSet rs=SQLHelper.getResultSet(" select * from TB_Borrow where bkID= "+bkID+" and IsHasReturn=0");
        if (rs!=null){
            if (rs.next()){
                borrow=initBorrow(rs);
            }
        }
        return borrow;
    }
    @Override
    public String[] getPrettyColumnNames() {
        return new String[0];
    }

    @Override
    public String[] getMethodNames() {
        return new String[0];
    }
    public Borrow initBorrow(ResultSet rs) throws SQLException {
        Borrow borrow=new Borrow();
        borrow.setBorrowID(rs.getInt("BorrowID"));
        borrow.setRdID(rs.getInt("rdID"));
        borrow.setBkID(rs.getInt("bkID"));
        borrow.setIdContinueTimes(rs.getInt("IdContinueTimes"));
        borrow.setIdDateOut(rs.getDate("IdDateOut"));
        borrow.setIdDateRetPlan(rs.getDate("IdDateRetPlan"));
        borrow.setIdDateRetAct(rs.getDate("IdDateRetAct"));
        borrow.setIdOverDay(rs.getInt("IdOverDay"));
        borrow.setIdOverMoney(rs.getFloat("IdOverMoney"));
        borrow.setIdPunishMoney(rs.getFloat("IdPunishMoney"));
        borrow.setHasReturn(rs.getBoolean("IsHasReturn"));
        borrow.setOperatorLend(rs.getString("OperatorLend"));
        borrow.setOperatorRet(rs.getString("OperatorRet"));
        return borrow;
    }
    public BorrowDAL(){

    }
    public BorrowDAL(int rdID){
       /* String sql = "select * from TB_Borrow where rdID= ?";
        int [] param = {rdID};
        System.out.println("BorrowDAL参数："+param);
        ResultSet rs = SQLHelper.getResultSet(sql, param);*/
        ResultSet rs=SQLHelper.getResultSet(" select * from TB_Borrow where rdID= "+rdID);
        try {

            while(rs.next())
            {
                Borrow borrow=new Borrow();
                borrow.setBorrowID(rs.getInt(1));
                borrow.setRdID(rs.getInt(2));
                borrow.setBkID(rs.getInt(3));
                borrow.setIdContinueTimes(rs.getInt(4));
                borrow.setIdDateOut(rs.getDate(5));
                borrow.setIdDateRetPlan(rs.getDate(6));
                borrow.setIdDateRetAct(rs.getDate(7));
                borrow.setIdOverDay(rs.getInt(8));
                borrow.setIdOverMoney(rs.getFloat(9));
                borrow.setIdPunishMoney(rs.getFloat(10));
                borrow.setHasReturn(rs.getBoolean(11));
                borrow.setOperatorLend(rs.getString(12));
                borrow.setOperatorRet(rs.getString(13));
                borrows.add(borrow);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
