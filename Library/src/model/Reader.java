package model;

import java.util.Date;

public class Reader extends AbstractModel {   //读者实体类
    int rdID;               //	读者编号/借书证号【主键】
    String rdName;    //	读者姓名
    String rdSex;    //	性别，男/女
    int rdType;    //读者类别【外键TB_ReaderType】【非空】
    String rdDept;   //	nvarchar (20)	单位代码/单位名称
    String rdPhone; //	nvarchar(25)	电话号码
    String rdEmail; //	nvarchar(25)	电子邮箱
    Date rdDateReg;    //	读者登记日期/办证日期
    String rdPhoto;    //	读者照片
    String rdStatus; //		证件状态，3个：有效、挂失、注销
    int rdBorrowQty;  //		已借书数量(缺省值0)
    String rdPwd;   //		读者密码(初值123)，可加密存储
    int rdAdminRoles; //		管理角色，0-读者、1-借书证管理、2-图书管理、4-借阅管理、8-系统管理，可组合

    public Reader() { }

    public int getRdID() {
        return rdID;
    }

    public void setRdID(int rdID) {
        this.rdID = rdID;
    }

    public String getRdName() {
        return rdName;
    }

    public void setRdName(String rdName) {
        this.rdName = rdName;
    }

    public String getRdSex() {
        return rdSex;
    }

    public void setRdSex(String rdSex) {
        this.rdSex = rdSex;
    }

    public int getRdType() {
        return rdType;
    }

    public void setRdType(int rdType) {
        this.rdType = rdType;
    }

    public String getRdDept() {
        return rdDept;
    }

    public void setRdDept(String rdDept) {
        this.rdDept = rdDept;
    }

    public String getRdPhone() {
        return rdPhone;
    }

    public void setRdPhone(String rdPhone) {
        this.rdPhone = rdPhone;
    }

    public String getRdEmail() {
        return rdEmail;
    }

    public void setRdEmail(String rdEmail) {
        this.rdEmail = rdEmail;
    }

    public Date getRdDateReg() {
        return rdDateReg;
    }

    public void setRdDateReg(Date rdDateReg) {
        this.rdDateReg = rdDateReg;
    }

    public String  getRdPhoto() {
        return rdPhoto;
    }

    public void setRdPhoto(String rdPhoto) {
        this.rdPhoto = rdPhoto;
    }

    public String getRdStatus() {
        return rdStatus;
    }

    public void setRdStatus(String rdStatus) {
        this.rdStatus = rdStatus;
    }

    public int getRdBorrowQty() {
        return rdBorrowQty;
    }

    public void setRdBorrowQty(int rdBorrowQty) {
        this.rdBorrowQty = rdBorrowQty;
    }

    public String getRdPwd() {
        return rdPwd;
    }

    public void setRdPwd(String rdPwd) {
        this.rdPwd = rdPwd;
    }

    public int getRdAdminRoles() {
        return rdAdminRoles;
    }

    public void setRdAdminRoles(int rdAdminRoles) {
        this.rdAdminRoles = rdAdminRoles;
    }

    //管理角色，0-读者、1-借书证管理、2-图书管理、4-借阅管理、8-系统管理，可组合
    //按位“与”的计算是把两个数字分别写成二进制形式，然后按照每一位判断，&计算中，只要有一个是0就算成0
    public boolean isReaderAdmin(){ //借书证管理
        return (this.rdAdminRoles&1)>0; //1&1=1,1>0返回true
    }
    public boolean isBookAdmin(){
        return (this.rdAdminRoles&2)>0;
    }
    public boolean isBorrowAdmin(){
        return (this.rdAdminRoles&4)>0;
    }
    public boolean isSysAdmin(){
        return (this.rdAdminRoles&8)>0;
    }
    public Reader(Reader r){
        setRdID(r.getRdID());
        setRdName(r.getRdName());
        setRdSex(r.getRdSex());
        setRdType(r.getRdType());
        setRdDept(r.getRdDept());
        setRdPhone(r.getRdPhone());
        setRdEmail(r.getRdEmail());
        setRdDateReg(r.getRdDateReg());
        setRdPhoto(r.getRdPhoto());
        setRdStatus(r.getRdStatus());
        setRdBorrowQty(r.getRdBorrowQty());
        setRdPwd(r.getRdPwd());
        setRdAdminRoles(r.getRdAdminRoles());
    }
}
