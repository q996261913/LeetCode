package leetcode29_spiralorder;

public class SpiralOrder {
    public  int[]  spiralOrder(int[][] matrix){
        if(matrix.length==0)
            return new int[0];
        //四个方位边界
        int up=0,down=matrix.length-1,left=0,right=matrix[0].length-1;
        //已经记录个数
        int count=0;

        int[] res=new int[(right+1)*(down+1)];
        while (true){
            //从左向右,然后up++
            for(int i=left;i<=right;i++)
                res[count++]=matrix[up][i];
            if(++up>down)
                break;
            //从上到下,然后right--
            for(int i=up;i<=down;i++)
                res[count++]=matrix[i][right];
            if(--right<left)
                break;
            //从右向左,然后down--
            for(int i=right;i>=left;i--)
                res[count++]=matrix[down][i];
            if(--down<up)
                break;
            //从下到上,然后left++
            for(int i=down;i>=up;i--)
                res[count++]=matrix[i][left];
            if(++left>right)
                break;
        }
        return res;

    }
}
