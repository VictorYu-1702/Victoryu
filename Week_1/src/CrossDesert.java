public class CrossDesert {
    public double totaldistance;
    public double speed;
    public double oilPer;
    void Travel_Answer(double dDis, double speed, double oilPer)
    {
        this.oilPer=oilPer;
        this.speed=speed;
        this.totaldistance=dDis;
        // 满负载可行距离
        double disPer = oilPer/speed;
        if(dDis <= disPer)
        {
           System.out.println("总距离小于单次满负载可行距离，直接通过即可/n");
            return;
        }
        // 否则需要设置临时补给站，每个临时补给站的储油量应该是单次最大负载的整数倍
        double d = disPer;
        int i = 1;
        while(d < dDis)
        {
            // 计算下一个补给站
            ++i;
            d += 1.0/(2*i-1)*disPer;
        }
        System.out.println("起点需设储油量："+ oilPer*i);
        double totalOil = -1;
        while(i>1)
        {
            d -= disPer/(2*i-1);
            --i;
            if(totalOil < 0)
            {
                totalOil = oilPer*i + (dDis-d)*(2*i+1)/speed;
            }

            System.out.println("下一个补给点距离起点："+(dDis-d)+",储油量："+(oilPer*i));
        }

        System.out.println("总耗油量："+totalOil);
    }

    public static void main(String[] args) {
        double totaldistance=1000.0;
        double speed=1.0;
        double oilPer=500.0;
        CrossDesert desert=new CrossDesert();
        desert.Travel_Answer(totaldistance,speed,oilPer);
    }
}
