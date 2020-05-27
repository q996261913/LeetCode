package leetcode42_trap_the_water;


import java.util.Stack;

public class TrapTheWater {
    //暴力解法,由于要遍历没一个柱子,而且要确定左右两边最小值
    //故O(n^2)
    public int trapTheWater1(int[] height) {
        int res = 0;
        //首尾不能存雨水,故从 [1,length-2]
        for (int i = 1; i < height.length - 1; i++) {
            //每个柱子能存的水等于  左右两侧最大高度的差值-当前柱子高度
            //leftMax和rightMax记录着左边最高的值和右边最高的值
            int leftMax = 0, rightMax = 0;
            //找到左边的最大值
            for (int j = i; j >= 0; j--)
                if (leftMax < height[j])
                    leftMax = height[j];
            //找到右边的最大值
            for (int j = i; j < height.length - 1; j++)
                if (rightMax < height[j])
                    rightMax = height[j];
            //该柱子所存的水为,min-heigh[i];
            int min = Math.min(leftMax, rightMax);
            res += (min - height[i]);
        }
        return res;
    }

    public int trapTheWater2(int[] height) {
        /**
         * 定义二维数组 dp[n][2]
         * 其中dp[i][1]表示i左边高的最大值
         * 其中dp[i][2]表示i右边高的最大值
         * 分别从两头遍历height数组,填写dp[i][j]
         */
        int n = height.length;
        if (n == 0)
            return 0;
        int[][] dp = new int[n][2];
        //dp[i][0]表示i左边的最大值
        //dp[i][1]表示i右边的最大值
        dp[0][0] = height[0];
        dp[n - 1][1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            //dp[i-1][0]--->i-1处左边的最大高度
            dp[i][0] = Math.max(dp[i - 1][0], height[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            dp[i][1] = Math.max(height[i], dp[i + 1][1]);
        }
        //最终dp绘制完成,累加res
        int res = 0;
        for (int i = 1; i < n - 1; i++) {
            res += Math.min(dp[i][0], dp[i][1]) - height[i];
        }
        return res;
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
        int left = 0, right = height.length - 1;
        //对于0号柱子,左边最大为0,对于len-1号柱最右边的最大值为0
        int leftMax = 0, rightMax = 0;
        int res = 0;
        while (left <= right) {
            if (leftMax <= rightMax) {
                //如果left左边的最大值小于等于right右边的最大值
                //相对与left来说,最终这个柱子一定能盛水 leftMax-height[left]
                //而不需要管rightMax
                leftMax = Math.max(leftMax, height[left]);
                res += leftMax - height[left++];
            } else {
                //如果右边小
                rightMax = Math.max(rightMax, height[right]);
                res += rightMax - height[right--];
            }
        }
        return res;

    }

    public int trapTheWater4(int height[]) {
        /**
         * 单调栈,栈中存每个元素的元素下标
         * 而且,栈中应该是单调递减的
         * 一旦发现了有凹槽,就出栈,直到形成单调为止
         * 那么这时候的凹槽盛水量为
         *     出栈后的栈顶下标索引的代表的值(比较高的元素)-要入栈的索引的元素-以前的栈顶元素
         */
        Stack<Integer> decStack = new Stack<>();
        int res = 0;
        if (height == null || height.length == 0)
            return 0;
        for (int i = 0; i < height.length; i++) {
            if (decStack.isEmpty())
                decStack.push(i);
            else {//栈不空,就判断出栈,否则入栈
                if (height[decStack.peek()] < height[i]) {
                    int peek = decStack.peek();
                    //栈顶元素小于入栈元素,出栈,直到栈顶元素大于等于要入栈的位置
                    while (!decStack.isEmpty() && height[decStack.peek()] < height[i]) {
                        decStack.pop();
                    }
                    //留下来的元素和要入栈的元素形成凹槽
                    res += (i - decStack.peek()) * (Math.min(height[decStack.peek()], height[i]) - height[peek]);
                    decStack.push(i);
                }
                decStack.push(i);
            }

        }
        return res;
    }
}
