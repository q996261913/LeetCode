package leetcode238_productExceptSelf;

public class ProductExceptSelf {
    /**
     * 两个dp数组,front[x]和rear[x]分别存储x的前缀和后缀之积
     * 同时 ans存储前缀和后缀的相应乘积
     * @param nums
     * @return
     */
    public  int[] productExceptSelf1(int[] nums){
        //前缀之乘积dp数组
        int[] front=new int[nums.length];
        //后缀之乘积dp数组
        int[] rear=new int[nums.length];
        //构造前缀dp
        front[0]=1;
        for(int i=1;i<front.length;i++)
            front[i]=front[i-1]*nums[i-1];
        //构造后缀dp
        rear[nums.length-1]=1;
        for(int i=nums.length-2;i>=0;i--)
            rear[i]=rear[i+1]*nums[i+1];
        int[] ans=new int[nums.length];
        for(int i=0;i<ans.length;i++)
            ans[i]=front[i]*rear[i];
        return  ans;
    }
    /**
     * 一个dp数组，rear[x]分别存储x的后缀之积
     * 白嫖ans作为前缀
     * @param nums
     * @return
     */
    public  int[] productExceptSelf2(int[] nums){
        int[] ans=new int[nums.length];
        //后缀之乘积dp数组
        int[] rear=new int[nums.length];
        //构造前缀dp
        ans[0]=1;
        for(int i=1;i<ans.length;i++)
            ans[i]=ans[i-1]*nums[i-1];
        //构造后缀dp
        rear[nums.length-1]=1;
        for(int i=nums.length-2;i>=0;i--)
            rear[i]=rear[i+1]*nums[i+1];

        for(int i=0;i<ans.length;i++)
            ans[i]=ans[i]*rear[i];
        return  ans;
    }

    /**
     * 常数空间复杂度,最优解
     * @param nums
     * @return
     */
    public int[] productExceptSelf3(int[] nums){
        int front =1,rear=1,ans[]=new int[nums.length];
        //迭代更新front,
        for(int i=0;i< nums.length;i++){
            ans[i]=front;
            front=front*nums[i];
        }
        for(int i=nums.length-1;i>=0;i--){
            ans[i]=ans[i]*rear;
            rear=rear*nums[i];
        }
        return  ans;
    }
}
