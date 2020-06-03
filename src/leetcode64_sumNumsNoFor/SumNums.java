package leetcode64_sumNumsNoFor;

public class SumNums {
    /**
     * 用递归求和
     * @param n
     * @return
     */
    public int sumNum1(int n){
        return  n*1+n*(n-1)/2;
    }

    /**
     * 用递归
     * @param n
     * @return
     */
    public  int sumNum2(int n){
        int sum=n;
        boolean flag=n>0&&(sum+=sumNum2(n-1))>0;
        return  sum;
    }

}
