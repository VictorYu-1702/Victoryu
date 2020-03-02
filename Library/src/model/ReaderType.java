package model;

public class ReaderType extends AbstractModel {   //读者类型实体类
    int rdType;             //读者类别【主键】
    String rdTypeName;      // 读者类别名称【唯一、非空】
    int CanLendQty;          //可借书数量
    int CanLendDay;          // 可借书天数
    int CanContinueTimes;    //可续借的次数
    float punishRate;         // 罚款率（元/天）
    int dateValid;              //证书有效期（年）【非空，0表示永久有效】
   public ReaderType(){}

    public int getRdType() {
        return rdType;
    }

    public void setRdType(int rdType) {
        this.rdType = rdType;
    }

    public String getRdTypeName() {
        return rdTypeName;
    }

    public void setRdTypeName(String rdTypeName) {
        this.rdTypeName = rdTypeName;
    }

    public int getCanLendQty() {
        return CanLendQty;
    }

    public void setCanLendQty(int canLendQty) {
        CanLendQty = canLendQty;
    }

    public int getCanLendDay() {
        return CanLendDay;
    }

    public void setCanLendDay(int canLendDay) {
        CanLendDay = canLendDay;
    }

    public int getCanContinueTimes() {
        return CanContinueTimes;
    }

    public void setCanContinueTimes(int canContinueTimes) {
        CanContinueTimes = canContinueTimes;
    }

    public float getPunishRate() {
        return punishRate;
    }

    public void setPunishRate(float punishRate) {
        this.punishRate = punishRate;
    }

    public int getDateValid() {
        return dateValid;
    }

    public void setDateValid(int dateValid) {
        this.dateValid = dateValid;
    }


    public ReaderType(ReaderType rt){
       setRdType(rt.getRdType());
       setRdTypeName(rt.getRdTypeName());
       setCanLendQty(rt.getCanLendQty());
       setCanLendDay(rt.getCanLendDay());
       setCanContinueTimes(rt.getCanContinueTimes());
       setPunishRate(rt.getPunishRate());
       setDateValid(rt.getDateValid());
    }
}
