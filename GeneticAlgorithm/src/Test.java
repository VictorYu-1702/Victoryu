public class Test {
    private static int G_LENGTH = 32;		  // 基因编码的长度
    private static int GROUP_SIZE = 100;     // 种群的大小

    public static void main(String[] args) {
        // 生成原始数据
        int[][] group = new int[GROUP_SIZE][G_LENGTH];
        for(int i = 0;i<GROUP_SIZE;i++) {
            group[i] = GA.generateGenenic(G_LENGTH);
        }
        System.out.println("初始数据"+"最大适应度"+ GA.getMaxFit(group));
        int epoch_num = 10000;		// 训练次数
        for(int i= 0;i<epoch_num;i++) {
            // 选择
            group = GA.selection(group);
            // 变异
            GA.mutation(group);
            System.out.println("epoch"+(i+1)+"最大适应度"+ GA.getMaxFit(group));
        }
    }

}
