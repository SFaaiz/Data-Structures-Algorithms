package dsaPractice;
// whenever a range of numbers is given use cyclic sort
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sortingAlgos {

    static void insertionSort(int[] nums){
        for (int i = 0; i < nums.length-1; i++) {
            for(int j=i+1; j>0; j--){
                if(nums[j] < nums[j-1]){
                    swap(nums,j,j-1);
                }else {
                    break;
                }
            }
        }
    }

    static void selectionSort(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            int last = nums.length-1-i;
            int max= findMaxIndex(nums,0,last);
            int temp = nums[max];
            nums[max] = nums[last];
            nums[last] = temp;
        }
    }

    private static int findMaxIndex(int[] nums, int first, int last) {
        int max = first;
        for (int j = 0; j <= last; j++) {
            if(nums[max] < nums[j]){
                max = j;
            }
        }
        return max;
    }

    static void bubbleSort(int[] nums){
        boolean swaps = false;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j < nums.length-i; j++) {
                if(nums[j-1] > nums[j]){
                    swap(nums,j-1,j);
                    swaps = true;
                }
            }
            if(!swaps){
                break;
            }
        }
    }

    private static void swap(int[] nums, int j, int i) {
        int temp = nums[j];
        nums[j]=nums[i];
        nums[i] = temp;
    }

    static void cyclicSort(int[] nums){
        int i=0;
        while (i<nums.length){
            if(i != nums[i]-1){
                swap(nums,i,nums[i]-1);
            }else {
                i++;
            }
        }
    }

//    find a duplicate no. from 1 to N
    static int duplicate(int[] nums){
        int i=0;
        while (i < nums.length){
            if(i != nums[i]-1) { // if value at current index is not correct i.e. index 0 = 1
                if (nums[i] != nums[nums[i] - 1]) { // if the val at correct index != to the val at current index
                    swap(nums, i, nums[i] - 1);    // then only swap or else it stuck in an infinite loop of swapping
                } else {  // if the val at current index == val at correct index then it's duplicate so just return it
                    return nums[i];
                }
            }else { // if the val at current index is correct then check for next index
                i++;
            }
        }
        return -1;
    }

    // find all duplicates from an array of range 1 to N
    static List<Integer> allDuplicates(int[] nums){
        int i=0;
        while (i<nums.length){
            if(nums[i] != i+1){
                if(nums[i] != nums[nums[i]-1]){
                    swap(nums, i,nums[i]-1);
                }else {
                    i++;
                }
            }else {
                i++;
            }
        }
        // find all the duplicates and store them in an array
        ArrayList list = new ArrayList<>();
        for (int j = 0; j < nums.length; j++) {
            if(nums[j] != j+1){
                list.add(nums[j]);
            }
        }
        return list;
    }

    // in an array of range from 1 to N find a duplicate and a missing number
    static int[] duplicateMissing(int[] nums){
        int i=0;
        while (i<nums.length){
            if(nums[i] != i+1){
                if(nums[i] != nums[nums[i]-1]){
                    swap(nums, i, nums[i]-1);
                }else {
                    i++;
                }
            }else {
                i++;
            }
        }
        // check for duplicateMissing , if the no. is not correct index then that incorrect number is the duplicate
        // and the i+1 i.e. no. that should be there is missing
        for (int j = 0; j < nums.length; j++) {
            if(nums[j] != j+1){
                return new int[]{nums[j], j+1}; // (duplicate, missing)
            }
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        System.out.println(Arrays.toString(duplicateMissing(arr)));

//        int[] arr1 ={3,3,5,4,2,2,2,1,1};
//        int[] arr = {5,4,3,6,2,1};
//        System.out.println(allDuplicates(arr1));

//        int[] nums = {4,3,8,2,1,7};
//        insertionSort(nums);
//        selectionSort(nums);
//        bubbleSort(nums);

//        cyclicSort(arr);
//        System.out.println(duplicate(arr));
//        System.out.println("hello");
//        System.out.println(Arrays.toString(arr));
    }
}
