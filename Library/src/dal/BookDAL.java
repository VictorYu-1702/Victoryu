package dal;

import model.AbstractModel;
import model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAL extends AbstractDAL {
    @Override
    public AbstractModel[] getAllObjects() throws Exception {
        return new AbstractModel[0];
    }

    public List<Book> books = new ArrayList<Book>();

    public BookDAL() {
        String sql = "select * from TB_Book where 1=?";
        String[] param = {"1"};

        ResultSet rs = SQLHelper.getResultSet(sql, param);
        try {

            while (rs.next()) {
                Book book = new Book();
                book.setBkID(rs.getInt(1));book.setBkCode(rs.getString(2));
                book.setBkName(rs.getString(3));book.setBkAuthor(rs.getString(4));
                book.setBkPress(rs.getString(5));book.setBkDatePress(rs.getDate(6));
                book.setBkISBN(rs.getString(7));book.setBkCatalog(rs.getString(8));
                book.setBkLanguage(rs.getInt(9));book.setBkPages(rs.getInt(10));
                book.setBkPrice(rs.getFloat(11));book.setBkDateIn(rs.getDate(12));
                book.setBkBrief(rs.getString(13));book.setBkCover(rs.getString(14));
                book.setBkStatus(rs.getString(15));books.add(book);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(AbstractModel object) throws Exception {
        if (object instanceof Book == false) {
            throw new Exception("Can only handle Book");
        }
        Book book = (Book) object;
        String sql = "insert into TB_Book(bkID,bkCode,bkName,bkAuthor,bkPress,bkDatePress,"
                + "bkISBN,bkCatalog,bkLanguage,bkPages,bkPrice,bkDateIn,bkBrief,"
                + "bkCover,bkStatus) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = new Object[15];
        params[0] = book.getBkID();params[1] = book.getBkCode();
        params[2] = book.getBkName();params[3] = book.getBkAuthor();
        params[4] = book.getBkPress();params[5] = book.getBkDatePress();
        params[6] = book.getBkISBN();params[7] = book.getBkCatalog();
        params[8] = book.getBkLanguage();params[9] = book.getBkPages();
        params[10] = book.getBkPrice();params[11] = book.getBkDateIn();
        params[12] = book.getBkBrief();params[13] = book.getBkCover();
        params[14] = book.getBkStatus();
        return SQLHelper.ExecSql(sql, params);
    }

    @Override
    public boolean delete(AbstractModel object) throws Exception {
        if (object instanceof Book == false) {
            throw new Exception("Can only handle Book");
        }
        Book book = (Book) object;
        String sql = "delete from TB_Book where bkID=?";
        Object[] params = new Object[]{book.getBkID()};
        //int rows=SQLHelper.ExecSql(sql,params);
        return SQLHelper.ExecSql(sql, params);
    }

    @Override
    public boolean update(AbstractModel object) throws Exception {
        if (object instanceof Book == false) {
            throw new Exception("Can only handle Book");
        }
        Book book = (Book) object;
        String sql = "update TB_Book set bkCode=?," + "bkName=?,bkAuthor=?,bkPress=?," +
                "bkDatePress=?,bkISBN=?,bkCatalog=?,bkLanguage=?,bkPages=?,bkPrice=?," +
                "bkDateIn=?,bkBrief=?,bkCover=?,bkStatus=? where bkID=?)";
        Object[] params = new Object[15];

        params[0] = book.getBkCode();params[1] = book.getBkName();
        params[2] = book.getBkAuthor();params[3] = book.getBkPress();
        params[4] = book.getBkDatePress();params[5] = book.getBkISBN();
        params[6] = book.getBkCatalog();params[7] = book.getBkLanguage();
        params[8] = book.getBkPages();params[9] = book.getBkPrice();
        params[10] = book.getBkDateIn();params[11] = book.getBkBrief();
        params[12] = book.getBkCover();params[13] = book.getBkStatus();
        params[14] = book.getBkID();
        return SQLHelper.ExecSql(sql, params);
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

    /*    bkID,bkCode,bkName,bkAuthor,bkPress,bkDatePress,"
                +"bkISBN,bkCatalog,bkLanguage,bkPages,bkPrice,bkDateIn,bkBrief"
                +"bkCover,bkStatus) "*/
    private Book initBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setBkID(rs.getInt("bkID"));
        book.setBkCode(rs.getString("bkCode"));
        book.setBkName(rs.getString("bkName"));
        book.setBkAuthor(rs.getString("bkAuthor"));
        book.setBkPress(rs.getString("bkPress"));
        book.setBkDatePress(rs.getDate("bkDatePress"));
        book.setBkISBN(rs.getString("bkISBN"));
        book.setBkCatalog(rs.getString("bkCatalog"));
        book.setBkLanguage(rs.getInt("bkLanguage"));
        book.setBkPages(rs.getInt("bkPages"));
        book.setBkPrice(rs.getFloat("bkPrice"));
        book.setBkDateIn(rs.getDate("bkDateIn"));
        book.setBkBrief(rs.getString("bkBrief"));
        book.setBkCover(rs.getString("bkCover"));
        book.setBkStatus(rs.getString("bkStatus"));
        return book;
    }

    public Book getObjectByID(int bkID) throws SQLException {
        //根据关键字查询ReaderType实体类对象的方法GetObjectByID()。
        Book book = null;
        ResultSet rs = SQLHelper.getResultSet("select bkID,bkCode,bkName,bkAuthor,bkPress,bkDatePress,"
                + "bkISBN,bkCatalog,bkLanguage,bkPages,bkPrice,bkDateIn,bkBrief,"
                + "bkCover,bkStatus from TB_Book where bkID= " + bkID);
        if (rs != null) {
            if (rs.next()) {
                book = initBook(rs);
            }
        }
        return book;
    }
}
