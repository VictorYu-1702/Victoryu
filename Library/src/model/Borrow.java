package model;

import java.util.Date;

public class Borrow extends AbstractModel {

    long BorrowID;    //	借书顺序号【主键】
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
    String OperatorRet; //	Nvarchar(20)	还书操作员

    public Borrow(){}
    public long getBorrowID() {
        return BorrowID;
    }

    public void setBorrowID(long borrowID) {
        BorrowID = borrowID;
    }

    public int getRdID() {
        return rdID;
    }

    public void setRdID(int rdID) {
        this.rdID = rdID;
    }

    public int getBkID() {
        return bkID;
    }

    public void setBkID(int bkID) {
        this.bkID = bkID;
    }

    public int getIdContinueTimes() {
        return IdContinueTimes;
    }

    public void setIdContinueTimes(int idContinueTimes) {
        IdContinueTimes = idContinueTimes;
    }

    public Date getIdDateOut() {
        return IdDateOut;
    }

    public void setIdDateOut(Date idDateOut) {
        IdDateOut = idDateOut;
    }

    public Date getIdDateRetPlan() {
        return IdDateRetPlan;
    }

    public void setIdDateRetPlan(Date idDateRetPlan) {
        IdDateRetPlan = idDateRetPlan;
    }

    public Date getIdDateRetAct() {
        return IdDateRetAct;
    }

    public void setIdDateRetAct(Date idDateRetAct) {
        IdDateRetAct = idDateRetAct;
    }

    public int getIdOverDay() {
        return IdOverDay;
    }

    public void setIdOverDay(int idOverDay) {
        IdOverDay = idOverDay;
    }

    public float getIdOverMoney() {
        return IdOverMoney;
    }

    public void setIdOverMoney(float idOverMoney) {
        IdOverMoney = idOverMoney;
    }

    public float getIdPunishMoney() {
        return IdPunishMoney;
    }

    public void setIdPunishMoney(float idPunishMoney) {
        IdPunishMoney = idPunishMoney;
    }

    public boolean isHasReturn() {
        return IsHasReturn;
    }

    public void setHasReturn(boolean hasReturn) {
        IsHasReturn = hasReturn;
    }

    public String getOperatorLend() {
        return OperatorLend;
    }

    public void setOperatorLend(String operatorLend) {
        OperatorLend = operatorLend;
    }

    public String getOperatorRet() {
        return OperatorRet;
    }

    public void setOperatorRet(String operatorRet) {
        OperatorRet = operatorRet;
    }

    public Borrow(Borrow borrow){
        setBorrowID(borrow.getBorrowID());
        setRdID(borrow.getRdID());
        setBkID(borrow.getBkID());
        setIdContinueTimes(borrow.getIdContinueTimes());
        setIdDateOut(borrow.getIdDateOut());
        setIdDateRetPlan(borrow.getIdDateRetPlan());
        setIdDateRetAct(borrow.getIdDateRetAct());
        setIdOverDay(borrow.getIdOverDay());
        setIdOverMoney(borrow.getIdOverMoney());
        setIdPunishMoney(borrow.getIdPunishMoney());
        setHasReturn(borrow.isHasReturn());
        setOperatorLend(borrow.getOperatorLend());
        setOperatorRet(borrow.getOperatorRet());
    }
}
