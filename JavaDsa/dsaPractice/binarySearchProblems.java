package dsaPractice;

import java.util.Arrays;

public class binarySearchProblems {

    // find ceiling of a number in a given array, if the no. is present just return it
    // //arr = {1,3,4,6,9,56,333}; --> ceiling of 4 is 6, 7 is 9, 333 is -1 or null
    static int ceiling(int[]arr, int target){
        int s = 0;
        int e = arr.length-1;
        if(target > arr[e]){
            return -1;
        }
        while (s<=e){
            int m = (s+e)/2;
            if(arr[m] == target){
                return m;
            }
            if(arr[m] < target){
                s=m+1;
            }
            else {//(arr[m]>target){
                e=m-1;
            }
        }
        return s;
    }

    static int floor(int[] arr, int target){
        int s=0, e=arr.length-1;
        if(target<arr[s]){
            return -1;
        }
        while (s<=e){
            int m = (s+e)/2;
            if(arr[m] == target){
                return m;
            }
            if(arr[m] < target){
                s=m+1;
            }else {
                e=m-1;
            }
        }
        return e;
    }

    // BS in a char array, always return next char of target, if target is last element then return first element
    static char ceilingChar(char[] arr, char target){
        int s=0, e=arr.length-1;
        while (s<=e){
            int m=(s+e)/2;
            if(target < arr[m]){
                e=m-1;
            }else {
                s=m+1;
            }
        }
        return (arr[s%arr.length]);
    }

    static char floorChar(char[] arr, char target){
        int s=0, e=arr.length-1;
        while (s<=e){
            int m=(s+e)/2;
            if(target < arr[m]){
                e=m-1;
            }else {
                s=m+1;
            }
        }
        if(e==0){
            return arr[arr.length-1];
        }

        return (arr[e-1]);
    }

    // find the first and the last occurrence of a number in an array
    static int[] firstLastIndex(int[] nums, int target){
//        int[] ans = {-1, -1};
//        int first = findIndex(nums,target,true);
//        int last = findIndex(nums,target,false);
//        ans[0] = first;
//        ans[1] = last;
//        return ans;

        return new int[]{findIndex(nums,target,true), findIndex(nums,target,false)};
    }
    static int findIndex(int[] nums, int target, boolean isFirstOccurrence){
        int ans = -1;
        int s = 0;
        int e = nums.length-1;

        while (s<=e){
            int m = (s+e)/2;
            if(nums[m] > target){
                e=m-1;
            } else if (nums[m] < target) {
                s=m+1;
            }else {
                ans = m;
                if(isFirstOccurrence){
                    e=m-1;
                }else {
                    s=m+1;
                }
            }
        }
        return ans;
    }

    // BS in an infinite sized array
    static int infiniteArraySearch(int[] arr, int target){
        int s = 0, e=1;
        while (true){
            if(target>=arr[s] && target<=arr[e]){
                while (s<=e){
                    int m = (s+e)/2;
                    if(arr[m] == target){
                        return m;
                    }
                    if(target>arr[m] ){
                        s=m+1;
                    }else {
                        e=m-1;
                    }
                }
                return -1;
            }
            s = e+1;
            e = s*2;
        }
    }

//    find peak index in a mountain array
//    {1,3,6,9,7,4} --> this is a mountain array first part is in ascending order and second part is in descending array
    // here we will bring both start and end to a common point i.e. at peak
    static int peakIndex(int[] nums){
        int s =0;
        int e=nums.length-1;
        while (s!=e){
            int m =(s+e)/2;
            if(nums[m] < nums[m+1]){ // if next element is greater then current one, it means peak is ahead
                s=m+1;
            }else {     // if next element is smaller than current one, it means either current element is peak or
                e=m;            // previous elements have a peak
            }
        }
        return s;
    }

//    instead of peak find a target, binary searching in a MountainArray
    // this question can be solved 2 steps
    // 1. find peak index, 2. using orderAgnosticBS first find in first half from 0,peakIndex , if not found then
    // find in second half , peakIndex+1 to end
    static int BSMountainArray(int[] nums, int target){
        int peakIndex = peakIndex(nums);
        int s=0, e=peakIndex;
        while (s<=e){
            int m = (s+e)/2;
            if(nums[m] == target){
                return m;
            }
            if(nums[m] < target){
                s=m+1;
            }else {
                e=m-1;
            }
        }

        // search descending part
        s=peakIndex+1;
        e=nums.length-1;
        while (s<=e){
            int m = (s+e)/2;
            if(nums[m] == target){
                return m;
            }
            if(nums[m] > target){
                s=m+1;
            }else {
                e=m-1;
            }
        }
        return -1;
    }

    // Binary search in a rotated array
    static int BSInRotatedArray(int[] nums, int target){
        int pivot= findPivot(nums);
        if(pivot==-1){
            return binarySearch(nums,target,0,nums.length-1);
        }
        if(target<nums[0]){ // if target is less than first element then all the elements after it must be greater
            return binarySearch(nums,target,pivot+1,nums.length-1);// till pivot so just search from pivot+1 to end
        }
        return binarySearch(nums,target,0,pivot);
    }
    private static int binarySearch(int[] nums, int target, int s, int e){
        while (s<=e){
            int m=(s+e)/2;
            if(nums[m] == target){
                return m;
            }
            if(nums[m] < target){
                s=m+1;
            }else {
                e=m-1;
            }
        }
        return -1;
    }

    private static int findPivot(int[] nums){
        int s=0, e=nums.length-1;
        while (s<=e){
            int m=(s+e)/2;
            // we can fall in 4 different cases
            if(m<e && nums[m] > nums[m+1]) {// if current element is greater than next one than current index is pivot
                return m;  // if mid is at last index than mid+1 will produce error
            }
            if(m>s && nums[m] < nums[m-1]){ // if current element is smaller than previous element than previous
                return m-1;       // index is pivot
            }
            if(nums[m] >= nums[s]){
                s=m+1;
            }
            else { // nums[m] < nums[s]
                e=m-1;
            }
        }
        return -1;
    }

    // find no. of rotations in a rotated array
    static int countRotations(int[] nums){
        return (findPivot(nums)+1);
    }

    public static void main(String[] args) {
        int[] arr = {5,6,1,2,3};
        System.out.println(countRotations(arr));

//        System.out.println(BSInRotatedArray(arr,5));

//        int[] arr = {1,3,5,7,6,4,2};
//        System.out.println(BSMountainArray(arr,11));

//        System.out.println(peakIndex(arr));

//        int[] arr = {1,2,3,4,5,6,7,8,9,12,34,56};
//        System.out.println(infiniteArraySearch(arr, 10));

//        int[] nums = {1,2,44,55,55,55,77,89};
//        System.out.println(Arrays.toString(firstLastIndex(nums,55)));

//        char[] arr = {'b','f','n','t','x'};
//        System.out.println(floorChar(arr,'n'));
//        System.out.println(ceilingChar(arr,'g'))

//        int[] arr = {1,3,4,6,9,56,333};
//        System.out.println(ceiling(arr,6));
//        System.out.println(floor(arr,332));
    }
}




















