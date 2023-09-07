package practice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class mergeSort {

    static void merge(int[] nums, int s, int e){
        if(s<e-1){
            int m = (s+e)/2;
            merge(nums, s,m);
            merge(nums,m,e);

            mergeHelper(nums,s,m,e);
        }
    }
     private static void mergeHelper(int[] nums, int s, int m, int e){
        int[] mix = new int[e-s];
        int i =s;
        int j = m;
        int k=0;
        while (i<m && j<e){
            if(nums[i] < nums[j]){
                mix[k] = nums[i];
                i++;
            }else {
                mix[k] = nums[j];
                j++;
            }
            k++;
        }
        while (i<m){
            mix[k] = nums[i];
            i++;
            k++;
        }
        while (j<e){
            mix[k] = nums[j];
            j++;
            k++;
        }
        for (int l = 0; l < mix.length; l++) {
            nums[s+l] = mix[l];
        }
    }

    static void merge2(int[] arr, int l, int h){ // high is inclusive
        // when l==h means now only one element is left
        if(l<h){ // keep dividing until there is only 1 element left in the array
            int mid = (l+h)/2;
            merge2(arr,l,mid);
            merge2(arr,mid+1,h);
            mergeHelper2(arr,l,mid,h); // initially merging will start from 2 elements
        }
    }
    private static void mergeHelper2(int[]arr, int s, int m, int e){
        int[] temp = new int[e-s+1];
        int i= s;
        int j = m+1;
        int k=0;
        while (i<=m && j<=e){
            if(arr[i] < arr[j]){
                temp[k] = arr[i];
                i++;
            }else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }
        // if any one of the array gets empty then below loops will run
        while (i<=m){
            temp[k] = arr[i];
            i++;
            k++;
        }
        while (j<=e){
            temp[k] = arr[j];
            j++;
            k++;
        }
//        make changes in the original array
        for (int l = 0; l < temp.length; l++) {
            arr[l+s] = temp[l];
        }
    }

    static void quickSort(int[] nums, int low, int high){
        if(low<high){
            int s = low;
            int e = high;
            int m =  (s+e)/2;
            int pivot = nums[m];
            while (s<e){
                while (nums[s] < pivot) s++;
                while (nums[e] > pivot) e--;
                // swap
                int temp = nums[s];
                nums[s] = nums[e];
                nums[e] = temp;
                s++;
                e--;
                quickSort(nums,low,e);
                quickSort(nums,s,high);
            }
        }
    }
    // move from A to C using B as auxiliary
    static void TOH(int n, String A, String B, String C){
        if (n>0){
            TOH(n-1,A,C,B);
            System.out.printf("%s to %s \n", A,C);
            TOH(n-1,B,A,C);
        }
    }

    static int fibo(int n){ //nth
        if(n==0 || n==1) return n;
        return fibo(n-1) + fibo(n-2);
    }

    static void fibo2(int n){ //all
        int f = 0, s= 1;
        int next = f+s;
        if(n>=0){
            System.out.println(f);
            if(n>0){
                System.out.println(s);
                if(n==1)return;
            }
        }
        int i=1;
        while (i<n){
            System.out.println(next);
            f = s;
            s = next;
            next = f+s;
            i++;
        }
    }



    public static void main(String[] args) {
        fibo2(30);

//        System.out.println(fibo(10));

//        TOH(3,"A","B","C");

//        int[] arr = {5,4,7,3,2,8,1};
//        System.out.println(Arrays.toString(arr));
//        quickSort(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));

//        System.out.println(Arrays.toString(arr));
//        merge(arr, 0, arr.length);
//        System.out.println(Arrays.toString(arr));
    }
}
