package dsaPractice;

// rotated array binary search

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class recursion {

    static void oneToN(int n){
        if(n<=0){
            return;
        }
        oneToN(n-1);
        System.out.println(n);
    }

    static int fibonacci(int n){ // 0,1,1,2,3,5,8,13,21
        if(n<2){
            return n;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    static int binarySearch(int[] nums, int target, int start, int end){
        if(start>end){
            return -1;
        }
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                return binarySearch(nums, target, start, mid - 1);
            } else {
                return binarySearch(nums, target, mid + 1, end);
            }
    }

    static void primeOrNot(int n){
        if(n<2){
            System.out.println(n + " Is Not prime");
            return;
        }
        primeHelper(n,2);
    }
    private static void primeHelper(int n, int divisor){
        if(n==divisor){
            System.out.println(n + " Is prime");
            return;
        }
        if(n%divisor==0){
            System.out.println(n + " Is Not prime");
            return;
        }
        primeHelper(n,divisor+1);
    }

    static void factors(int n){ // this is taking O(n) time checking for every number
        for (int i = 1; i <= n ; i++) {
            if(n%i == 0){
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    static void factors2(int n){ // this will take O(n^1/2) time , checking till n root numbers
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if(n%i == 0){
                System.out.print(i + " ");
                if(n/i != i){  // in order to not add duplicates in the array
                    list.add(n/i); // 4 * 5 = 20 then 4 & 5 both are it's divisors
                }
            }
        }
        for (int i = list.size()-1; i >=0 ; i--) {
            System.out.print(list.get(i) + " ");
        }
    }

    static int factorial(int n){
        if(n<2){
            return 1;
        }
        return n * factorial(n-1);
    }

    static int sumOfDigits(int n){
        if(n%10 == n){
            return n;
        }
        return n%10 + sumOfDigits(n/10);
    }

    static int productOfDigits(int n){
        if(n%10 == n){
            return n;
        }
        return n%10 * productOfDigits(n/10);
    }

    static int reverseNum(int n){
        if(n%10 == n){
            return n;
        }
        int digits =(int) Math.log10(n);
        return n%10 * (int)(Math.pow(10,digits)) + reverseNum(n/10);
    }

    static int noOfZeros(int n){
        if(n%10 == n){
            if(n==0){
                return 1;
            }
            return 0;
        }
        int count = (n%10==0) ? 1 : 0;
        return count + noOfZeros(n/10);
    }

    // no. of steps it will take to reduce the no. to zero
    // if even divide by 2, else -1 (reduce by 1)
    static int noOfSteps(int n){
        if(n==0){
            return 0;
        }
        int num = (n%2==0) ? n/2 : n-1;
        return 1 + noOfSteps(num);
    }

    static boolean isSorted(int[] nums, int index){
        if(index == nums.length-1){
            return true;
        }
        return nums[index] < nums[index+1] && isSorted(nums,index+1);
    }

    static int linearSearch(int [] nums, int target, int index){
        if(index == nums.length){ // either the array is empty or we have searched the whole array
            return -1;
        }
        if(nums[index] == target){
            return index;
        }
        return linearSearch(nums,target,index+1);
    }

    // search for the target which could be repeated on many indices
    static ArrayList<Integer> linearSearchAll(int[] nums, int target, int index){
        if(index == nums.length){
            return new ArrayList<>(); // return the empty arrayList because either the array is empty or we have
        }                                // searched the whole array
        ArrayList<Integer> list = new ArrayList<>();
        if(nums[index] == target){
            list.add(index);
        }
        list.addAll(linearSearchAll(nums,target,index+1)); // addAll method copies all the elements of an arraylist
        return list;     // into another one, it will copy the elements of returned arrayList into the current one
    }

    static int rotatedArrayBS(int[] nums, int target, int s, int e){
        if(s>e){
            return -1;
        }
            int m = (s+e)/2;
            if(nums[m] == target){
                return m;
            } // [6,7,1,2,3,4,5] -> 6
            if(nums[s] <= nums[m]){
                if(target >= nums[s] && target <= nums[m]){
                    return rotatedArrayBS(nums,target,s,m-1);
                }
                else {
                    return rotatedArrayBS(nums,target,m+1,e);
                }
            }
            if(target >= nums[m] && target <= nums[e]){
                return rotatedArrayBS(nums,target,m+1,e);
            }
        return rotatedArrayBS(nums,target,s,m-1);
    }

    static void triangle90degree(int r, int c){
        if(r==0){
            return;
        }
        if(c<r){
            System.out.print("*");
            triangle90degree(r,c+1);
        }else {
            System.out.println();
            triangle90degree(r - 1, 0);
        }
    }

    static void bubbleSort(int[] nums, int startIndex, int e){ // e = lastIndex
        if(e<=0){ // either one element is left or none
            return;
        }
        if(startIndex<e){
            if(nums[startIndex] > nums[startIndex+1]){
                swap(nums, startIndex, startIndex+1);
            }
            bubbleSort(nums,startIndex+1,e);
        }
        else {
            bubbleSort(nums,0,e-1);
        }
    }

    private static void swap(int[] nums, int startIndex, int i) {
        int temp = nums[startIndex];
        nums[startIndex] = nums[i];
        nums[i] = temp;
    }

    static void selectionSort(int[] nums, int s, int e, int maxIndex){
        if(e<0){
            return;
        }
        if(s<=e){
            if(nums[s] > nums[maxIndex]){
                selectionSort(nums,s+1,e,s);
            }else{
                selectionSort(nums, s+1,e,maxIndex);
            }
        }else {
            int temp = nums[maxIndex];
            nums[maxIndex] = nums[e];
            nums[e] = temp;
            selectionSort(nums,0,e-1,0);
        }
    }

    static void merge(int[] arr, int l, int h){ // high is inclusive
        // when l==h means now only one element is left
        if(l<h){ // keep dividing until there is only 1 element left in the array
            int mid = (l+h)/2;
            merge(arr,l,mid);
            merge(arr,mid+1,h);
            mergeHelper(arr,l,mid,h); // initially merging will start from 2 elements
        }
    }
    private static void mergeHelper(int[]arr, int s, int m, int e){
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

    // after each pass the pivot is placed at it's correct index
    static void quickSort(int[] nums, int s, int e){
        if(s>=e){ // either it is sorted or only 1 element is left
            return;
        }
        int low = s;
        int high = e;
        int m = (s+e)/2;
        int pivot = nums[m]; // mid element is pivot
        while (s<=e){               //{4,3,6,2,5,1};
            while (nums[s] < pivot){ // until we don't find a greater element on left
                s++;
            }
            while (nums[e] > pivot){ // until we don't find a smaller element on right
                e--;
            }
            if(s<=e){
                int temp = nums[s];
                nums[s] = nums[e];
                nums[e] = temp;
                s++;
                e--;
            }
            quickSort(nums, low, e);
            quickSort(nums, s, high);
        }
    }

    // skip a word from a string
    static String skipWord(String toSkip, String skipFrom){
        if(skipFrom.isEmpty()){
            return "";
        }
        if(skipFrom.startsWith(toSkip)){ // if it starts from toSkip then don't return that part just ignore it
            return skipWord(toSkip, skipFrom.substring(toSkip.length()));
        }
        else {
            return skipFrom.charAt(0) + skipWord(toSkip, skipFrom.substring(1)); // if it doesn't start from
        }                             // toskip part the just return it
    }

    // skip given character/s from a string
//    static String skipsChar(String toSkip, String skipFrom){
//        if(skipFrom.isEmpty()){
//            return "";
//        }
//        if(skipFrom.startsWith(toSkip)){
//            return skipsChar(toSkip, skipFrom.substring(toSkip.length()));
//        }else {
//            return skipFrom.charAt(0) + skipsChar(toSkip, skipFrom.substring(1));
//        }
//    }

    // return subsequences of a string
    // subsequence is a subset of a string, it can't be in random order like permutation
    // ac is a subsequence of abc because the order didn't change
    static ArrayList<String> subsequence(String p, String up){
        ArrayList<String> list = new ArrayList<>();
        if(up.isEmpty()){
            if(!p.isEmpty()){ // if p is not empty then only add into te list
                list.add(p);
            }
            return list;
        }
        list.addAll(subsequence(p+up.charAt(0), up.substring(1))); // taking first char
        list.addAll(subsequence(p, up.substring(1))); // ignoring first char

        return list;
    }

    static List<List<Integer>> subset(int[] arr){ // subset of an array consisting of integers
        List<List<Integer>> list = new ArrayList<>(); // 2d arrayList consists of subsets
        list.add(new ArrayList<>()); // initially add an empty arrayList
        for (int i : arr) {// add current array element to all the list elements by cloning them
            int n = list.size();
            for (int j = 0; j < n; j++) { // loop will clone and add the no. of times of size to the 2d list size
                List<Integer> list2 = new ArrayList<>(list.get(j));// clone the list from 2d list
                list2.add(i); // add current element into that list
                list.add(list2); // add this new subset to the original one
            }
        }
        return list;
    }

    // u have to form a given no. with dice by rolling it any no. of times and form all of its combination
    // for eg. 3 -> 111, 12, 21, 3
    static ArrayList<String> diceCombination(String s, int target){
        if(target==0){
            ArrayList<String> list = new ArrayList<>();
            list.add(s);
            return list;
        }
        ArrayList<String> list = new ArrayList<>();
        for (int i = target; i > 0; i--) {
            list.addAll(diceCombination(s+i, target-i));//add the target to string and substract it from target
        }                   // when target becomes 0 i.e. if target is 3 then 3 is added to string and we got a combination
        return list;
    }

    // possible combination by pressing 2 given numbers
//     1      2       3
//    abc   def      ghi
//     4      5       6
//    jkl   mno      pqr
//     7      8       9
//    stu   vwx      yz
    static void dialPad(String p, String up){
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }
        int digit = up.charAt(0) - '0';
        for (int i = (digit-1)*3; i < digit*3 && i<26; i++) {
            char c = (char) ('a' + i);
            dialPad(p + c, up.substring(1));
        }
    }

    static String skipWord2(String toSkip, String skipFrom){
        if(toSkip.isEmpty() || skipFrom.isEmpty()) return "";
        if(skipFrom.startsWith(toSkip)) return skipFrom.substring(skipFrom.length());
        else return skipFrom.charAt(0) + skipWord2(toSkip, skipFrom.substring(1));
    }

    public static void main(String[] args) {
//        System.out.println(skipWord2("ab","cabde"));


//        dialPad("","92");

//        System.out.println(diceCombination("",3));

//        List<List<Integer>> ans = subset(new int[]{1,2,3});
//        for(List<Integer> i : ans){
//            System.out.println(i);
//        }
//
//        System.out.println(subsequence("","abc"));

//        subsequence("", "abc");

//        System.out.println(skipsChar("a", "faaiz"));

//        System.out.println(skipWord("apple", "myappleitis"));

        int[] arr = {4,3,6,2,5,1};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

//        int[] arr= {4,3,2,1};
//        mergeSort(arr,0,arr.length);
//        System.out.println(Arrays.toString(arr));

//        int[] arr = {4,3,2,1};
//        selectionSort(arr,0,arr.length-1,0);
//        System.out.println(Arrays.toString(arr));

//        int[] arr = {4,3,2,1};
//        bubbleSort(arr, 0, arr.length-1);
//        System.out.println(Arrays.toString(arr));

//        triangle90degree(5,0);

//        System.out.println(rotatedArrayBS(new int[]{4,5,6,1,2,3}, 1,0,5));

//        System.out.println(linearSearchAll(new int[]{22,1,21,3,4,1}, 2, 0));

//        System.out.println(linearSearch(new int[]{6,87,43,243,65,2,56}, 64,0));

//        System.out.println(isSorted(new int[]{1},0));

//        System.out.println(noOfSteps(14));

//        System.out.println(noOfZeros(100));

//        System.out.println(reverseNum(1030));

//        System.out.println(productOfDigits(12340));

//        System.out.println(sumOfDigits(5));

//        System.out.println(factorial(1));

//        factors2(25);

//        factors(20);

//        for (int i = 0; i < 20; i++) {
//            primeOrNot(i);
//        }

//        int[] arr = {1,3,4,6,8};
//        System.out.println(binarySearch(arr,8,0,arr.length-1));

//        oneToN(5);
//        System.out.println(fibonacci(6));
    }
}
