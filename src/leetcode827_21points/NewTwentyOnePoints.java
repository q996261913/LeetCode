package leetcode827_21points;

import java.util.Random;

/**
 * 爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
 * <p>
 * 爱丽丝以 0 分开始，并在她的得分少于 K 分时抽取数字。 抽取时，她从 [1, W] 的范围中随机获得一个整数作为分数进行累计，其中 W 是整数。 每次抽取都是独立的，其结果具有相同的概率。
 * <p>
 * 当爱丽丝获得不少于 K 分时，她就停止抽取数字。 爱丽丝的分数不超过 N 的概率是多少？
 */
public class NewTwentyOnePoints {
    /**
     * 定义dp[x]--->代表手上分数为x时,赢游戏的概率
     *
     * @param N
     * @param K
     * @param W
     * @return
     */
    public double new21Game(int N, int K, int W) {
        //当手上的分数为K-1的时候,如果此时 K-1+W<N---一定能赢比赛
        if (K - 1 + W <= N)
            return 1.0;
        //当分数为[K,N]的时候赢得游戏概率为1.0
        double[] dp = new double[K + W];
        for (int i = K; i <= N; i++)
            dp[i] = 1.0;
        //后边数组为1.0的个数一共有 N-K+1;
        //故 初始的dp[K-1]=(N-K+1)*1.0/W;
        double sumIncidence = N - K + 1;
        for (int i = K - 1; i >= 0; i--) {
            //依次填写分数为[0,K-1]时赢的概率
            /*
             * 对于dp[i]=sum(dp[i+1]...dp[i+W])/W
             * 每一轮迭代,修改sumIncidence,每一轮减去上一轮末尾的dp,也就是dp[];
             * */
            dp[i] = sumIncidence / W;
            sumIncidence = sumIncidence + dp[i]-dp[i+W];
        }
        return  dp[0];

    }
}
