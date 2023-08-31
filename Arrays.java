// A subarray is a contiguous segment of an array.
// A subsequence is a set of elements maintaining their order, but they don't have to be adjacent.


// 344. Reverse String - leetcode
// 01 -  TC is O(N)/2 and SC is constant
class Solution {
    public void reverseString(char[] s) {
        int n = s.length;
        for(int i=0; i<n/2; i++){
            char temp = s[i];
            s[i] = s[n-i-1];
            s[n-1-i] = temp;
        }
    }
}

// Find minimum and maximum element in an array - GFG
// 02 -  TC is O(N)/2 and SC is constant
class Compute 
{
    static Pair getMinMax(long a[], long n)  
    {
        //Write your code here
        long min=Long.MAX_VALUE, max=Long.MIN_VALUE;
        for(int i=0; i<n; i++){
            if(a[i]>max) max = a[i];
            if(a[i]<min) min = a[i];
        }
        return new Pair(min, max);
    }
}

// 349. Intersection of Two Arrays - leetcode
// TC is O(n) and SC is also O(n)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        List<Integer> result = new ArrayList<>();
        int n1 = nums1.length, n2 = nums2.length;
        for(int i=0; i<n1; i++){
            set.add(nums1[i]);
        }
        for(int i=0; i<n2; i++){
            if(set.contains(nums2[i])) {
                result.add(nums2[i]);
                set.remove(nums2[i]);
            }
        }
        int[] res = new int[result.size()];
        for(int i=0; i<res.length; i++) res[i] = result.get(i);
        return res;
    }
}

// 53. Maximum Subarray - leetcode
// kadane's algorithm - max sum from contiguous subarray
class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int curSum = 0;
        for(int i=0; i<nums.length; i++){
            curSum += nums[i];
            max = Math.max(max, curSum);
            if(curSum < 0) curSum=0;    // if cursum is negative then it's gonna decrease the sum only when added to another number
        }
        return max;
    }
}

// 121. Best Time to Buy and Sell Stock - leetcode
// o(n) time and constant space
class Solution {
    public int maxProfit(int[] prices) {
      // with priority queue
        // PriorityQueue<Integer> pq = new PriorityQueue<>();
        // int max = 0;
        // for(int i=0; i<prices.length; i++){
        //     if(!pq.isEmpty()){
        //         max = Math.max(max, prices[i]-pq.peek());
        //     }
        //     pq.add(prices[i]);
        // }
        // return max;

      // without PQ
        int min = Integer.MAX_VALUE;
        int max = 0;
        for(int i=0; i<prices.length; i++){
            max = Math.max(max, prices[i]-min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }
}


// Count pairs with given sum - GFG
// TC is O(n) and SC is also O(n)
class Solution {
    int getPairsCount(int[] arr, int n, int k) {
        // code here
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++){
            int num = arr[i];
            if(map.containsKey(k-num)){
                cnt += map.get(k-num);
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return cnt;
    }
}

// Subarray with 0 sum - GFG
class Solution{
    //Function to check whether there is a subarray present with 0-sum or not.
    static boolean findsum(int arr[],int n)
    {
        //Your code here
        Set<Integer> sums = new HashSet<>();
        int sum =0;
        for(int i=0; i<n; i++){
            sum += arr[i];
            if(sums.contains(sum) || arr[i]==0 || sum==0) return true;
            sums.add(sum);
        }
        return false;
    }
}

// 1. Two Sum - leetcode
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]), i};
            }
            map.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }
}

// 42. Trapping Rain Water - leetcode
public int trap(int[] height) {
    int n = height.length;
    int[] leftMax = new int[n];
    int[] rightMax = new int[n];
    leftMax[0] = height[0];
    rightMax[n-1] = height[n-1];
    for(int i=1; i<n; i++){
        leftMax[i] = Math.max(leftMax[i-1],height[i]);
    }
    for(int i=n-2; i>=0; i--){
        rightMax[i] = Math.max(rightMax[i+1],height[i]);
    }
    int cnt = 0;
    for(int i=0; i<n; i++){
        cnt += Math.min(leftMax[i],rightMax[i]) - height[i];
    }
    return cnt;
}
