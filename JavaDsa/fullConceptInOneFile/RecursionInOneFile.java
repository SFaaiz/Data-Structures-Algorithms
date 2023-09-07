package fullConceptInOneFile;
// !IMP , must practice these questions
// binarySearchInRotatedArray
// starPattern
// bubbleSort
// selectionSort
// mergeSort
// quickSort


import java.util.ArrayList;


public class RecursionInOneFile{

    static void oneToN(int n){
        if(n==0){
            return;
        }
        oneToN(n-1);
        System.out.println(n);
    }

    // print index
    static int fibonacci(int n){
        if(n<2){
            return n;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }


    static int binarySearch(int[] nums, int s, int e, int t){
        if(s>e){
            return -1;
        }
        int m = s + (e-s)/2;
        if(t == nums[m]){
            return m;
        }
        if(t < nums[m]){
            return binarySearch(nums, s, m-1, t);
        }
        if(t > nums[m]){
            return binarySearch(nums, m+1, e, t);
        }
        return -1;
    }

    static int factorial(int n){
        if(n==0){
            return 1;
        }
        return n * factorial(n-1);
    }

    static int sumOfDigits(int n){
        if(n%10==n){
            return n;
        }
        int rem = n%10;
        return rem + sumOfDigits(n/10);
    }

    static int productOfDigits(int n){
        if(n%10==n){
            return n;
        }
        int rem = n%10;
        return rem * productOfDigits(n/10);
    }

    static int reverseNum(int n){
        int digits = (int) (Math.log10(n)+1);
        return reverseNumHelper(n, digits);
    }
    static int reverseNumHelper(int n, int digits){
        if(n%10==n){
            return n;
        }
        int rem = n%10;
        return rem * (int) (Math.pow(10,digits-1)) + reverseNumHelper(n/10,digits-1);
    }

    static int noOfZeros(int n){
        if(n%10==n){
            if(n%10==0){
                return 1;
            }
            return 0;
        }

        int count = 0;
        if(n%10==0){
            count+=1;
        }
        return count + noOfZeros(n/10);
    }

    // no. of steps it will take to reduce the no. to zero
    // if even divide by 2, else -1
    static int reduceNoToZero(int n, int steps){
        if(n==0){
            return steps;
        }
        if(n%2==0){
            return reduceNoToZero(n/2,steps+1);
        }
        return reduceNoToZero(n-1,steps+1);
    }

    static boolean isArraySorted(int[] arr, int index){
        if(index == arr.length-1){
            return true;
        }
        return arr[index] <= arr[index+1] && isArraySorted(arr,index+1);
    }

    static int linearSearch(int[] arr, int start, int target){
        if(start == arr.length){
            return -1;
        }
        if(arr[start] == target){
            return start;
        }
        return linearSearch(arr,start+1,target);
    }

    static ArrayList<Integer> searchAllNos(int[] arr, int target, int start, ArrayList<Integer> list){
        if(start == arr.length){
            return list;
        }

        if(arr[start] == target){
            list.add(start);
        }
        return searchAllNos(arr,target,start+1,list);
    }

    // search without taking an arraylist in parameter
    static ArrayList<Integer> searchAllNo(int[] arr, int target, int start){
        ArrayList<Integer> list = new ArrayList<>();
        if(start == arr.length){
            return list;
        }

        if(arr[start] == target){
            list.add(start);
        }
        ArrayList<Integer> ans= searchAllNo(arr,target,start+1);
        list.addAll(ans);
        return list;
    }

    // { 5,6,7,1,2,3,4}
    static int binarySearchInRotatedArray(int[] arr, int target, int start, int end){
        if(start>end){
            return -1;
        }
        int m = start + (end-start)/2;
        if(arr[m] == target){
            return m;
        }
        if(arr[start] <= arr[m]){
            if(target>=arr[start] && target<=arr[m]){
                return binarySearchInRotatedArray(arr,target,start,m-1);
            }
            else {
                return binarySearchInRotatedArray(arr,target,m+1,end);
            }
        }
        else {
            if(target>=arr[m] && target<=arr[end]){
                return binarySearchInRotatedArray(arr,target,m+1,end);
            }
            return binarySearchInRotatedArray(arr,target,start,m-1);
        }
    }

    static void starPattern(int r, int c){
        if(r==0){
            return;
        }
        if(c<r){
            System.out.print("*");
            starPattern(r,c+1);
        }
        else {
            System.out.println();
            starPattern(r-1,0);
        }
    }

    static void starPattern2(int r, int c){
        if(r==0){
            return;
        }
        if(c<r){
            starPattern2(r,c+1);
            System.out.print("*");
        }else {
            starPattern2(r-1,0);
            System.out.println();
        }
    }

    static void bubbleSort(int[] arr, int start, int end){
        if(end==0){
            return;
        }
        if(start<end){
            if(arr[start] > arr[start+1]){
                int temp = arr[start];
                arr[start] = arr[start+1];
                arr[start+1] = temp;
            }
            bubbleSort(arr,start+1,end);
        }
        bubbleSort(arr,0,end-1);
    }

    static void selectionSort(int[] arr, int start, int end, int maxIndex){
        if(end==0){
            return;
        }
        if(start<=end){
            if(arr[start] > arr[maxIndex]){
                selectionSort(arr,start+1,end,start);
            }
            else {
                selectionSort(arr,start+1,end,maxIndex);
            }
        }
        else {
            int temp = arr[maxIndex];
            arr[maxIndex]= arr[end];
            arr[end] = temp;
            selectionSort(arr,0,end-1,0);
        }

    }

    static void mergeSort(int[] arr, int s, int e){
        if(e==s){ // if only one element is left, end==start
            return;
        }
        int m = (s+e)/2;
        mergeSort(arr,s,m); // separate into two parts until one element is left
        mergeSort(arr,m+1,e); // same here for right side

        merge(arr,s,m,e); // pass two separated arrays to sort and merge them
    }
    static void merge(int[] arr, int s, int m, int e){
        int[] mix = new int[(e-s)+1]; // create new array of passed array's sized
        int i = s; // iterate over first array
        int j= m+1;// iterate over second array
        int k = 0; // iterate over mix array
        while (i<=m && j<=e){ // if both is less than their respective array size or else arrayIndexOutOfBound exception will occur
            if(arr[i] < arr[j]){
                mix[k] = arr[i];
                i++;
            }
            else {
                mix[k] = arr[j];
                j++;
            }
            k++;
        }
        while (i<=m){ // add remaining elements into mix
            mix[k] = arr[i];
            i++;
            k++;
        }
        while (j<=e){ // add remaining elements into mix
            mix[k] = arr[j];
            j++;
            k++;
        }
        for(int l=0; l<mix.length; l++){ // make changes in original array from mixed array
            arr[s+l] = mix[l];
        }
    }

    static void quickSort(int[] arr, int low, int hi){
        if(low>=hi){
            return;
        }
        int s = low;
        int e = hi;
        int m = (s+e)/2;
        int pivot = arr[m];
        while (s<=e){
            while (arr[s] < pivot){
                s++;
            }
            while (arr[e] > pivot){
                e--;
            }
            if(s<=e){
                int temp = arr[s];
                arr[s] = arr[e];
                arr[e] = temp;
                s++;
                e--;
            }
        }
        quickSort(arr,low,e);
        quickSort(arr,s,hi);
    }

    // subsets of a string
    static void subSequence(String p, String up){
        if(up.isEmpty()){ // if right side is empty then all characters must be at left side
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);  // take first char
        subSequence(p+ch, up.substring(1)); // first take one char
        subSequence(p, up.substring(1)); // then ignore it in another one
    }

    // return the subsets instead of printing
    static ArrayList<String> subSequenceReturn(String p, String up){
        if(up.isEmpty()){ // if right side is empty then all characters must be at left side
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);  // take first char
        ArrayList<String> ans  = new ArrayList<>();

        ans.addAll(subSequenceReturn(p+ch, up.substring(1))); // first take one char
        ans.addAll(subSequenceReturn(p, up.substring(1))); // then ignore it in another one
        return ans;
    }

    static void permutationPrint(String p, String up){
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0,i);
            String s = p.substring(i,p.length());
            permutationPrint(f + ch + s, up.substring(1));
        }
    }

    static ArrayList<String> permutation(String p, String up){
        if(up.isEmpty()){
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i <=p.length() ; i++) {
            String f = p.substring(0,i);
            String s = p.substring(i,p.length());
            ans.addAll(permutation(f+ch+s, up.substring(1)));
        }
        return ans;
    }

    static int permutationCount(String p, String up){
        if(up.isEmpty()){
            return 1;
        }
        char ch = up.charAt(0);
        int count =0;
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0,i);
            String s = p.substring(i,p.length());
            count = count + permutationCount(f + ch + s, up.substring(1));
        }
        return count;
    }

    static void dialPad(String p, String up){
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }
        int digit = up.charAt(0) - '0';
        for(int i=(digit-1)*3; i<digit*3; i++){
            char c = (char) ('a' + i);
            dialPad(p+c, up.substring(1));
        }
    }

    static void dice(String p, int target){
        if(target==0){
            System.out.println(p);
            return;
        }
        for(int i=1; i<=6 && i<=target; i++){
            dice(p+i,target-i);
        }
    }

    public static void main(String[] args) {
//        oneToN(5);

//        System.out.println(fibonacci(8));

//        int[] nums= {1,3,5,8,9};
//        System.out.println(binarySearch(nums, 0, nums.length-1, 100));

//        System.out.println(factorial(0));

//        System.out.println(sumOfDigits(123));

//        System.out.println(productOfDigits(12304));

//        System.out.println(reverseNum(1201));

//        System.out.println(noOfZeros(0));

//        System.out.println(reduceNoToZero(10,0));

//        int[] arr= {2,8,4,6,8,9,9};
//        System.out.println(isArraySorted(arr,0));

//        int[] arr= {2,8,4,6,8,9,9};
//        System.out.println(linearSearch(arr,0,60));

//        int[] arr= {2,8,4,6,8,9,9};
//        System.out.println(searchAllNos(arr,9,0,new ArrayList<>()));

//        System.out.println(searchAllNo(arr,9,0));

//        int[] arr = { 5,6,7,1,2,3,4};
//        System.out.println(binarySearchInRotatedArray(arr,5,0,arr.length-1));

//        starPattern(5,0);

//        starPattern2(2,0);

//        int[] arr = {3,6,2,5,9,1};
//        bubbleSort(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));

//        int[] arr = {3,6,2,11,5,9,1};
//        selectionSort(arr,0,arr.length-1,0);
//        System.out.println(Arrays.toString(arr));

//        int[] arr = {5,4,3,2,1};
//        mergeSort(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));

//        int[] arr = {5,4,3,2,1};
//        quickSort(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));

//        subSequence("","abcd");

//        System.out.println(subSequenceReturn("","abc"));

//        permutationPrint("","efg");

//        System.out.println(permutation("","abc"));

//        System.out.println(permutationCount("","abc"));

//        dialPad("","34");

        dice("",4);
    }

}


//class customArrayList{
//    customArrayList(){
//        int[] arr = new int[10];
//    }
//    void add(int n){
//
//    }
//}
















