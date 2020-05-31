package leetcode42_trap_the_water;


import java.util.Stack;

public class TrapTheWater {
    //暴力解法,由于要遍历没一个柱子,而且要确定左右两边最小值
    //故O(n^2)
    public int trapTheWater1(int[] height) {
       //暴力解法,遍历每一个柱子,得到其左右柱子的最小值,然后减去遍历的柱子的高度
        //注意首,尾不存雨水
        int res=0;
        for(int i=1;i<height.length-1;i++){
            int leftMax=0,rightMax=0;
            for(int j=i;j>=0;j--){
                if(height[j]>leftMax)
                    leftMax=height[i];
            }
            for(int j=i;j<height.length;j++)
                if(height[j]>rightMax)
                    rightMax=height[j];
                //取左右最大的最小值
            int min = Math.min(leftMax, rightMax);
            res+=min-height[i];
        }
        return  res;
    }

    public int trapTheWater2(int[] height) {
        /**
         * 定义二维数组 dp[n][2]
         * 其中dp[i][1]表示i左边高的最大值
         * 其中dp[i][2]表示i右边高的最大值
         * 分别从两头遍历height数组,填写dp[i][j]
         */
        int n=height.length;
        int[][] dp=new int[n][2];
        dp[0][0]=height[0];
        dp[n-1][1]=height[n-1];
        int res=0;
        for(int i=1;i<n;i++){
            //填写dp数组
            dp[i][0]=Math.max(dp[i-1][0],height[i]);
        }
        for(int j=n-2;j>=0;j--){
            //填写dp数组
            dp[j][1]=Math.max(dp[j+1][1],height[j]);
        }
        //dp填写完毕,对于每一个柱子i,取 dp[i][1]与dp[i][2]的最小值
        for(int i=0;i<height.length;i++){
            res+=Math.min(dp[i][0],dp[i][1])-height[i];
        }
        return  res;

    }

    public int trapTheWater3(int[] height) {
        /***
         * 左边指针left,右边指针right,left和right向中间逼近
         * 对于每一个柱子 如果leftMax小于等于rightMax
         * 则该柱子能存水
         *          leftMax-height[i];
         * 反之
         *          rightMax-height[i];
         * 上变边的解法,leftMax和rightMax是填出来的,而我们在遍历的过程中就能填了
         */
       int leftMax=0,rightMax=0,left=0,right=height.length-1;
       int res=0;
       while (left<=right){
           if(leftMax<=rightMax){
               leftMax = Math.max(leftMax, height[left]);
               res+=leftMax-height[left++];
           }
           else{
               rightMax = Math.max(rightMax, height[right]);
               res+=rightMax-height[right--];
           }
       }
       return  res;
    }

    public int trapTheWater4(int height[]) {
        /**
         * 单调栈,栈中存每个元素的元素下标
         * 而且,栈中应该是单调递减的
         * 一旦发现了有凹槽,就出栈,直到形成单调为止
         * 那么这时候的凹槽盛水量为
         *     出栈后的栈顶下标索引的代表的值(比较高的元素)-要入栈的索引的元素-以前的栈顶元素
         *     栈中可以有相等元素
         */
        Stack<Integer> decStack = new Stack<>();
        int res=0;
        for(int i =0;i<height.length;i++){
            while (!decStack.isEmpty()&&height[decStack.peek()]<height[i]){
                //先把比较小的栈顶元素pop出来
                int bottomIndex=decStack.pop();
                //出栈的元素是否有相等的,也只保留最后一个即可
                while (!decStack.isEmpty()&&height[decStack.peek()]==height[bottomIndex])
                    decStack.pop();
                if(!decStack.isEmpty()){
                    //如果这时候还不空,此时栈顶的值一定大于i的值,这是水槽的左边界
                    res+=
                            Math.min(height[decStack.peek()],height[i])-height[bottomIndex]//这是雨水的高度
                            *(i-decStack.peek()-1);
                }
            }

        }
        return  res;
    }
}
