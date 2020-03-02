package model;

import java.util.Date;

public class Book extends AbstractModel {     //图书实体类
   int bkID;//	Int	图书序号【标识列，主键】
   String bkCode;  //	Nvarchar (20)	图书编号或条码号（前文中的书号）
    String bkName; //	Nvarchar(50)	书名
    String bkAuthor;  //	Nvarchar(30)	作者
    String bkPress;   //	Nvarchar(50)	出版社
    Date bkDatePress;  //	datetime	出版日期
    String bkISBN; //	Nvarchar (15)	ISBN书号
    String bkCatalog; //	Nvarchar(30)	分类号（如：TP316-21/123）
    int bkLanguage;   //SmallInt	语言，0-中文，1-英文，2-日文，3-俄文，4-德文，5-法文
    int bkPages;   //	Int	页数
    float bkPrice;  //	Money	价格
    Date bkDateIn;  //	DateTime	入馆日期
    String bkBrief; //	Text	内容简介
    String bkCover; //	image	图书封面照片
    String bkStatus; //	NChar(2)	图书状态，在馆、借出、遗失、变卖、销毁

    public Book(){}
    public int getBkID() {
        return bkID;
    }

    public void setBkID(int bkID) {
        this.bkID = bkID;
    }

    public String getBkCode() {
        return bkCode;
    }

    public void setBkCode(String bkCode) {
        this.bkCode = bkCode;
    }

    public String getBkName() {
        return bkName;
    }

    public void setBkName(String bkName) {
        this.bkName = bkName;
    }

    public String getBkAuthor() {
        return bkAuthor;
    }

    public void setBkAuthor(String bkAuthor) {
        this.bkAuthor = bkAuthor;
    }

    public String getBkPress() {
        return bkPress;
    }

    public void setBkPress(String bkPress) {
        this.bkPress = bkPress;
    }

    public Date getBkDatePress() {
        return bkDatePress;
    }

    public void setBkDatePress(Date bkDatePress) {
        this.bkDatePress = bkDatePress;
    }

    public String getBkISBN() {
        return bkISBN;
    }

    public void setBkISBN(String bkISBN) {
        this.bkISBN = bkISBN;
    }

    public String getBkCatalog() {
        return bkCatalog;
    }

    public void setBkCatalog(String bkCatalog) {
        this.bkCatalog = bkCatalog;
    }

    public int getBkLanguage() {
        return bkLanguage;
    }

    public void setBkLanguage(int bkLanguage) {
        this.bkLanguage = bkLanguage;
    }

    public int getBkPages() {
        return bkPages;
    }

    public void setBkPages(int bkPages) {
        this.bkPages = bkPages;
    }

    public float getBkPrice() {
        return bkPrice;
    }

    public void setBkPrice(float bkPrice) {
        this.bkPrice = bkPrice;
    }

    public Date getBkDateIn() {
        return bkDateIn;
    }

    public void setBkDateIn(Date bkDateIn) {
        this.bkDateIn = bkDateIn;
    }

    public String getBkBrief() {
        return bkBrief;
    }

    public void setBkBrief(String bkBrief) {
        this.bkBrief = bkBrief;
    }

    public String getBkCover() {
        return bkCover;
    }

    public void setBkCover(String bkCover) {
        this.bkCover = bkCover;
    }

    public String getBkStatus() {
        return bkStatus;
    }

    public void setBkStatus(String bkStatus) {
        this.bkStatus = bkStatus;
    }

    public Book(Book book){
        setBkID(book.getBkID());
        setBkCode(book.getBkCode());
        setBkName(book.getBkName());
        setBkAuthor(book.getBkAuthor());
        setBkPress(book.getBkPress());
        setBkDatePress(book.getBkDatePress());
        setBkISBN(book.getBkISBN());
        setBkCatalog(book.getBkCatalog());
        setBkLanguage(book.getBkLanguage());

        setBkPages(book.getBkPages());
        setBkPrice(book.getBkPrice());
        setBkDateIn(book.getBkDateIn());
        setBkBrief(book.getBkBrief());
        setBkCover(book.getBkCover());
        setBkStatus(book.getBkStatus());
    }
}
