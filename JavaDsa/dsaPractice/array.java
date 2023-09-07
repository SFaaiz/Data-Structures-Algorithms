package dsaPractice;
import java.util.Arrays;
public class array {
    static void revArr(int[] arr){ // time complexity is O(n)
        int start = 0;
        int end = arr.length-1;
        while (start<end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    // find min val in array
    static int minValue(int[] arr){ // O(n)
        if(arr.length==0){
            return -1;
        }
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }

    static int[] searchIn2dArray(int[][]arr, int target){
        for (int i = 0; i < arr.length; i++) {
            for(int j=0; j < arr[i].length; j++){
                if(arr[i][j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    //find even no. of DIGITS of numbers an array contains
    static int evenNoOfDigits(int[]arr){
        if(arr.length==0){
            return -1;
        }
        int countEven = 0;
        for (int i = 0; i < arr.length; i++) {
            if(hasEvenDigits(arr[i])){
                countEven++;
            }
        }
        return countEven;
    }

    private static boolean hasEvenDigits(int n) {
        int count=0;
        while (n>0){
            n=n/10;
            count++;
        }
        return count%2==0;
    }

    // return sum of an array whose sum is max in 2d array
    static int maxSumIn2dArray(int[][]arr){
        int max=Integer.MIN_VALUE;
        for (int[] i: arr) {
            int sum=0;
            for(int j: i){
                sum+=j;
            }
            if(max<sum){
                max=sum;
            }
        }
        return max;
    }

    static int binarySearch(int[] nums, int target){
        if(nums.length==0){
            return -1;
        }
        int start = 0;
        int end = nums.length-1;
        while (start<=end){
            int mid = start + (end-start)/2;
            if(nums[mid] == target){
                return mid;
            }
            if(target < nums[mid]){
                end=mid-1;
            }else {
                start=mid+1;
            }
        }
        return -1;
    }

    //orderAgnosticBS , searching in array whose order is not determined
    static int orderAgnosticBS(int[] nums, int target){
        if(nums.length==0){
            return -1;
        }
        boolean isAsc = nums[0] < nums[nums.length-1];
        int start = 0;
        int end = nums.length-1;
        while (start<=end){
            int mid = start + (end-start)/2;
            if(nums[mid] == target){
                return mid;
            }
            if(!isAsc){
                if(target > nums[mid]){
                    end=mid-1;
                }else {
                    start=mid+1;
                }
                continue;
            }
            if(target < nums[mid]){
                end=mid-1;
            }else {
                start=mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,57};
        int[] arr1 = {66,54,46,32,11,3};
        System.out.println(orderAgnosticBS(arr,4));
        System.out.println(orderAgnosticBS(arr,4));

//        System.out.println(binarySearch(arr,57));

//        int[][] arr= { {1,2,3},
//                {4,5,68},
//                {7,8,9}};
//        System.out.println(maxSumIn2dArray(arr));

//        int[] arr = {111,1,233,576,567};
//        System.out.println(evenNoOfDigits(arr));

//        int[][] arr= { {1,2,3},
//                {4,5,6},
//                {7,8,9}};
//        System.out.println(Arrays.toString(searchIn2dArray(arr,8)));

//        int[] arr = {4,3,-11,2,1};
//        System.out.println(minValue(arr));

//        System.out.println(Arrays.toString(arr));
//        revArr(arr);
//        System.out.println(Arrays.toString(arr));
    }

}
