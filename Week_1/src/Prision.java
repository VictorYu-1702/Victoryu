public class Prision {
    public void Result(int n) {
        //1表示锁着，0表示开锁
        int room[] = new int[n];
        int i;
        for (i=0;i<n;i++) {
            room[i]=1;
        }
        int k,j;
        for(i=1;i<=n;i++) {
            //i表示通过牢房次数
            System.out.print("第"+i+"次通过牢房");
            for(k=i;k<=n;k+=i) {
                if(room[k-1]==1) {
                    room[k-1]=0;
                }else {
                    room[k-1]=1;
                }
            }
            for(j=0;j<n;j++) {
                if(room[j]==0) {
                    System.out.print(j+1+"、");
                }
            }
            System.out.println("是开锁的");
        }
    }
    public static void main(String[] args) {
        int n=10;//牢房间数
        Prision prision=new Prision();
        prision.Result(n);
    }
}
