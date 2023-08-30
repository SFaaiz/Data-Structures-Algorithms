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
