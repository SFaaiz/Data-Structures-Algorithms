package LeetCode;

class Solution {
    static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int n = s.length();
        if(n == 0 || n == 1) {
            return n;
        }
        if(s.isBlank()) {
            return 1;
        }
//        if(n == 2) {
//            String a = "", b = "";
//            for(int i = 0; i < n-1; i++) {
//                a = String.valueOf(s.charAt(i));
//            }
//            for(int i = 1; i < n; i++) {
//                b = String.valueOf(s.charAt(i));
//            }
//            if(a.equals(b)) {
//                return 1;
//            }
//            else if(a.equals(b)==false) {
//                return 2;
//            }
//        }
        for(int i = 0; i < n-1; i++) {
            int count = 1;
            for(int j = i+1; j < n; j++) {
                if(s.charAt(i) != s.charAt(j)) {
                    count++;
                }
                else {
                    break;
                }
            }
            if(maxLength < count) {
                maxLength = count;
            }
        }
        return maxLength;
    }

    static int subarraysDivByK(int[] nums, int k) {
        int count =0;
        for (int i = 0; i < nums.length; i++) {
            int sum =0;
            for (int j = i; j < nums.length; j++) {
                sum+= nums[j];
                if(sum%k ==0){
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        int[][] arr = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        for (int i = 0; i < arr.length*arr.length; i++) {
            if(i!=0 && i%arr.length == 0){
                System.out.println();
            }
            System.out.print(arr[i/arr.length][i%arr.length]);
        }
    }
}
