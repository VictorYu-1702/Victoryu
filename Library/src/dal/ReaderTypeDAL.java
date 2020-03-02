package dal;

import model.AbstractModel;
import model.ReaderType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReaderTypeDAL extends AbstractDAL{
    @Override
    public AbstractModel[] getAllObjects() throws Exception {
        //获取所有读者类型的方法getAllObjects()：
        ArrayList<ReaderType> objects=new ArrayList<>();
        ResultSet rs=SQLHelper.getResultSet("select * from TB_ReaderType");
        if(rs!=null){
            while (rs.next()){
                ReaderType rt=initReaderType(rs);
                objects.add(rt);
            }
            rs.close();
        }
        ReaderType[] readerTypes=new ReaderType[objects.size()];
        objects.toArray(readerTypes);
        return readerTypes;
    }

    @Override
    public boolean add(AbstractModel object) throws Exception {
        if(object instanceof ReaderType==false){
            throw new Exception("Can only handle ReaderType");
        }
        ReaderType rt=(ReaderType)object;
        String sql="insert into TB_ReaderType(rdType,"
                +"rdTypeName,CanLendQty,CanLendDay,"
                +"CanContinueTimes,PunishRate,DateValid) "
                +" VALUES(?,?,?,?,?,?,?)";
        Object[]params=new Object[7];
        params[0]=rt.getRdType();
        params[1]=rt.getRdTypeName();
        params[2]=rt.getCanLendQty();
        params[3]=rt.getCanLendDay();
        params[4]=rt.getCanContinueTimes();
        params[5]=rt.getPunishRate();
        params[6]=rt.getDateValid();
        return SQLHelper.ExecSql(sql,params);
    }

    @Override
    public boolean delete(AbstractModel object) throws Exception {
        if(object instanceof ReaderType==false){
            throw new Exception("Can only handle ReaderType");
        }
        ReaderType rt=(ReaderType)object;
        String sql="delete from TB_ReaderType where rdType=?";
        Object[]params=new Object[]{rt.getRdType()};
        //int rows=SQLHelper.ExecSql(sql,params);
        return SQLHelper.ExecSql(sql,params);
    }

    @Override
    public boolean update(AbstractModel object) throws Exception {
        if(object instanceof ReaderType==false){
            throw new Exception("Can only handle ReaderType");
        }
        ReaderType rt=(ReaderType)object;
        String sql="update TB_ReaderType set rdTypeName=?,"
                +"CanLendQty=?,CanLendDay=?,"
                +"CanContinueTimes=?,PunishRate=?,"
                +"DateValid=? where rdType=?)";
        Object[]params=new Object[]{rt.getRdTypeName(),
        rt.getCanLendQty(),rt.getCanLendDay(),rt.getCanContinueTimes(),
        rt.getPunishRate(),rt.getDateValid(),rt.getRdType()};
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

    @Override
    public String[] getMethodNames() {
        return new String[0];
    }


    private ReaderType initReaderType(ResultSet rs) throws SQLException{
        ReaderType rt=new ReaderType();
        rt.setRdType(rs.getInt("rdType"));
        rt.setRdTypeName(rs.getString("rdTypeName"));
        rt.setCanLendQty(rs.getInt("CanLendQty"));
        rt.setCanLendDay(rs.getInt("CanLendDay"));
        rt.setCanContinueTimes(rs.getInt("CanContinueTimes"));
        rt.setPunishRate(rs.getInt("PunishRate"));
        rt.setDateValid(rs.getInt("DateValid"));
        return rt;
    }
    public ReaderType getObjectByID(int rdType) throws SQLException{
        //根据关键字查询ReaderType实体类对象的方法GetObjectByID()。
        ReaderType rt=null;
        ResultSet rs=SQLHelper.getResultSet("select rdType,rdTypeName,CanLendQty"
                                +",CanLendDay,CanContinueTimes,PunishRate"
                                +",DateValid from TB_ReaderType where rdType="+rdType);
        if(rs!=null){
            if(rs.next()){
                rt=initReaderType(rs);
            }
        }
        return rt;
    }

    public ResultSet getReaderType() {

        ResultSet rs;
        String sql="select rdTypeName from TB_ReaderType GROUP BY rdTypeName ";
        rs=SQLHelper.getResultSet(sql);
        return rs;
    }
}
