package LeetCode;

import java.util.Arrays;

//class Solution {
//    public boolean isPalindrome(String s) {
//
//    }
//}
public class LeetCode{
    static boolean isPal(String s){
        String str="";
        for (int i = 0; i < s.length(); i++) {
            if(Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i))){
                str=str+s.charAt(i);
            }
        }
        str = str.toLowerCase();
//        System.out.println(str);
        for (int i = 0; i < str.length()/2; i++) {
            if(str.charAt(i) != str.charAt(str.length()-i-1)){
                return false;
            }
        }
        System.out.println(str);
        return true;
    }
    static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        for(int i=0; i<nums.length; i++){
            int sum=0;
            for(int j=i; j<nums.length; j++){
                sum+= nums[j];
                if(sum > max){
                    max = sum;
                }
            }
        }
        return max;
    }

    static int romanToInt(String s) {
        char[] arr = s.toCharArray();
        int length = s.length();
        int result = 0;
        for(int i=0; i<length; i++){
            if(i != length-1 && convert(arr[i]) < convert(arr[i+1])){
                result-= convert(arr[i]);
            }
            else{
                result += convert(arr[i]);
            }
        }
        return result;
    }
    private static int convert(char c){
        switch(c){
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return 0;
    }
    public static void main(String[] args) {
        



        /*
        System.out.println(isPal("A man, a plan, a canal: Panama"));


//        System.out.println(maxSubArray(arr));
        System.out.println(arr.length);

         */
    }
}
