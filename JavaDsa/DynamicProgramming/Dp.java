package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

public class Dp {

    // CodeStudio
    // a frog wants to reach from 0 to n-1, it can either take 1 or 2 steps at a time
    // abs(i1-i2) is the cost it wil take to reach from i1 to i2, return the min cost to reach n-1
    public static int frogJump(int n, int heights[]) {
        // memoization
        // int[] min = new int[n];
        // Arrays.fill(min,-1);
        // int jumps = dfs(n-1,heights,min);
        // return jumps;

        // tabulation :- to save recursion stack space
        // int[] dp = new int[n];
        // dp[0]=0;
        // if(n>1) dp[1] = Math.abs(heights[1]-heights[0]);

        // space optimization
        int fs = 0;
        if(n==1) return fs;
        int ss=0;
        if(n>1) ss = Math.abs(heights[1]-heights[0]);
        for(int i=2; i<n; i++){
            // tabulation
            // int fs = dp[i-1] + Math.abs(heights[i]-heights[i-1]);
            // int ss = dp[i-2] + Math.abs(heights[i]-heights[i-2]);
            // dp[i] = Math.min(fs,ss);

            // space optimization
            int f = fs + Math.abs(heights[i]-heights[i-2]);
            int s = ss + Math.abs(heights[i]-heights[i-1]);
            fs = ss;
            ss = Math.min(f,s);
        }
        return ss;

        // tabulation
        // return dp[n-1];
    }

    // Memoization
    // private static int dfs(int n, int[] heights, int[] min){
    //     if(n<0) return Integer.MAX_VALUE;
    //     if(n==0) return 0;
    //     if(n==1) return Math.abs(heights[1]-heights[0]);
    //     if(min[n] != -1) return min[n];
    //     int left = dfs(n-1, heights, min) + Math.abs(heights[n-1]-heights[n]);
    //     int right = dfs(n-2, heights, min) + Math.abs(heights[n-2]-heights[n]);
    //     min[n] = Math.min(left,right);
    //     return min[n];
    // }


    /*
    ------------------------------------------------------------------------------------------------------------------
     */

    static int kFrogJumps(int n, int k, int[] heights){
        int[] dp = new int[n];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                if(i-j-1 < 0) break;
                int cost = dp[i-j-1] + Math.abs(heights[i]-heights[i-j-1]);
                dp[i] = Math.min(dp[i],cost);
            }
        }
        return dp[n-1];
    }

    /*
    ------------------------------------------------------------------------------------------------------------------
     */

    // codestudio
    // return max sum of non adjacent elements from given array
    // [2,3,4,5,6] -> 2+4+6=12 is ans
        public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
            if(nums.size()==0) return 0;
            int fs=Integer.MIN_VALUE;
            if(nums.size()==1) return nums.get(0);
            int ss =nums.get(0);
            int ts=0;
            if(nums.size()>1) ts = nums.get(1);
            int ans=0;
            for(int i=2; i<nums.size(); i++){
                int	f = nums.get(i) + fs;
                int s = nums.get(i) + ss;
                ans = Math.max(f,s);
                fs = ss;
                ss = ts;
                ts=ans;
            }
            return (ss > ts) ? ss : ts;
    }

    /*
    ------------------------------------------------------------------------------------------------------------------
     */

    // codestudio
    public static long houseRobber(int[] valueInHouse) {
        int n = valueInHouse.length;
        if(n==1) return (long)valueInHouse[0];
        ArrayList<Integer> first = new ArrayList<>(); // skipping last element
        ArrayList<Integer> last = new ArrayList<>(); // skipping first element
        for(int i=0; i<n; i++){
            if(i!=n-1) first.add(valueInHouse[i]);
            if(i!=0) last.add(valueInHouse[i]);
        }
        return Math.max((long)maximumNonAdjacentSum(first),
                (long)maximumNonAdjacentSum(last));
    }

    /*
    ------------------------------------------------------------------------------------------------------------------
     */

    // go from (0,0) to (m-1,n-1)
    // u can either move right or down
    public static int uniquePaths(int m, int n) {
        boolean[][] grid = new boolean[m][n];
        int[] row = {0,1};
        int[] col = {1,0,};
        return dfs(0,0,grid,row,col,m,n);
    }

    private static int dfs(int r, int c, boolean[][] grid, int[] row, int[] col, int m, int n){
        if(r==m-1 && c==n-1) return 1;
        grid[r][c] = true;
        int count = 0;
        for(int dir = 0; dir<2; dir++){
            int nr = r+row[dir];
            int nc = c+col[dir];
            if(nr>=0 && nc>=0 && nr<m && nc<n && !grid[nr][nc]){
                count+= dfs(nr,nc,grid,row,col,m,n);
            }
        }
        grid[r][c] = false;
        return count;
    }

    // using DP
    public static int uniquePaths2(int m, int n) {
        // memoization + tabulation + space optimization
        int[] prev = new int[n];   // 1D array to store paths of previous row and sum simultaneously columns of curr row
        for(int row=0; row<m; row++){
            for(int col=0; col<n; col++){
                if(row==0 && col==0) prev[col]=1; // from 0,0 to 0,0 there is only 1 path
                else{
                    int up=0, left=0;
                    if(row>0) up = prev[col];   // no. of paths to arrrive to above cell will same reach curr
                    if(col>0) left = prev[col-1];   // plus no. of paths to arrrive left cell will same reach curr
                    prev[col] = up+left;
                }
            }
        }
        return prev[n-1]; // last cell will contain no. of all paths
    }

    /*
    ------------------------------------------------------------------------------------------------------------------
     */

    // leetcode
    // 0,0 to m-1,n-1 with min cost
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] prev = new int[n];
        for(int row=0; row<m; row++){
            for(int col=0; col<n; col++){
                if(row==0 && col==0) prev[col]=grid[0][0];
                else{
                    int up=Integer.MAX_VALUE, left=up;
                    if(row>0) up=prev[col];
                    if(col>0) left=prev[col-1];
                    prev[col] = Math.min(up,left) + grid[row][col];
                }
            }
        }
        return prev[n-1];
    }

     /*
    ------------------------------------------------------------------------------------------------------------------
     */

    // codestudio
    public static int ninjaTraining(int n, int points[][]) {
        int[] prev = new int[3];
        for(int i=0; i<n; i++){
            int[] temp = new int[3];
            for(int j=0; j<3; j++){
                if(i==0) temp[j]=points[i][j];
                else{
                    int max=0;
                    for(int c=0; c<3; c++) if(c!=j) max=Math.max(max,prev[c]);
                    temp[j] = points[i][j] + max;
                }
            }
            prev = temp;
        }
        return Math.max(prev[0],Math.max(prev[1],prev[2]));
    }

     /*
    ------------------------------------------------------------------------------------------------------------------
     */

    // codestudio / leetcode
    public static int minimumPathSum(int[][] triangle, int n) {
        int[][] dp = new int[n][n];
        for(int i=0; i<n; i++) dp[n-1][i] = triangle[n-1][i];
        for(int i = n-2; i>=0; i--){
            for(int j=i; j>=0; j--){
                int cur = triangle[i][j];
                dp[i][j] = Math.min(dp[i+1][j]+cur, dp[i+1][j+1]+cur);
            }
        }
        return dp[0][0];
    }

     /*
    ------------------------------------------------------------------------------------------------------------------
     */

    // leetcode
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        if(n==1) return matrix[0][0];
        int[] dp = new int[n];
        int minSum = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            int[] temp = new int[n];
            for(int j=0; j<n; j++){
                if(i==0) temp[j]=matrix[i][j];
                else{
                    int dl =Integer.MAX_VALUE, dr = dl;
                    int up = dp[j];
                    if(j-1>=0) dl = dp[j-1];
                    if(j+1<n) dr = dp[j+1];
                    int min = Math.min(up, Math.min(dl,dr));
                    temp[j] = min + matrix[i][j];
                    if(i==n-1) minSum = Math.min(minSum, temp[j]);
                }
            }
            dp = temp;
        }
        return minSum;
    }

     /*
    ------------------------------------------------------------------------------------------------------------------
     */

    static boolean kSubset(int[] nums, int k){
        boolean[][] dp = new boolean[nums.length][k+1];
        return kSubsetHelper(nums, nums.length-1, k,dp);
    }
    private static boolean kSubsetHelper(int[] nums, int n, int k, boolean[][] dp){
        if(k==0) {
            return dp[n][k]=true;
        }
        if(n==0) {
             return nums[n]==k;
        }
        if(dp[n][k]) {
            return true;
        }
        return kSubsetHelper(nums, n-1, k-nums[n], dp) || kSubsetHelper(nums, n-1, k, dp);
    }

    /*
    ------------------------------------------------------------------------------------------------------------------
     */

    // codestudio
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

        int[][] dp = new int[n][maxWeight+1];
        for(int[] i: dp) Arrays.fill(i,-1);
        return helper(weight,value,n-1,maxWeight,dp);

    }

    private static int helper(int[] w, int[] v, int i, int max,int[][] dp){
        if(i==0){
            if(w[0]<=max) return v[0];
            return 0;
        }
        if(dp[i][max]!=-1) return dp[i][max];
        int taken = Integer.MIN_VALUE;
        if(w[i]<=max){
            taken = v[i] + helper(w,v,i-1,max-w[i],dp);
        }
        int notTaken = helper(w,v,i-1,max,dp);
        return dp[i][max] = Math.max(taken, notTaken);
    }

    public static void main(String[] args) {
//        int[] h = {30,10,60,10,60,50};
//        System.out.println(kFrogJumps(6,5,h));
//        System.out.println(uniquePaths(3,3));

//        int[][] grid = {
//                {1, 2, 5},
//                {3, 1, 1},
//                {3, 3, 3}
//        };
//        System.out.println(ninjaTraining(3,grid));

//        int[][] tri = {
//                {1},
//                {2,3},
//                {4,5,6},
//                {8,7,9,10}
//        };
//        System.out.println(minimumPathSum(tri,4));

//        System.out.println(kSubset(new int[]{3,3,3},4));
    }
}
