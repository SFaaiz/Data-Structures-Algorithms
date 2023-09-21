// TC is log(n)
public int binarySearch(int[] nums, int target) {
    int n=nums.length;
    int s=0, e=n-1;
    while(s<=e){
        int m = (s+e)/2;
        if(nums[m]==target) return m;
        else if(nums[m]>target){
            e=m-1;
        }else{
            s=m+1;
        }
    }
    return -1;
}

/* In the worst case, Quick Sort has a time complexity of O(n^2). This worst case occurs when the pivot chosen is always the smallest or largest element in the array, causing unbalanced partitions.
However, on average and in the best case, Quick Sort has an average time complexity of O(n log n). It achieves this by dividing the array into roughly equal partitions and recursively sorting them.
*/
private void quickSort(int[] nums, int s, int e){
    if(s>=e) return;
    int pivot = nums[(s+e)/2];
    int l=s, h=e;
    while(l<=h){
        while(nums[l]<pivot) l++;
        while(nums[h]>pivot) h--;
        if(l<=h){
            swap(nums,l,h);
            l++;
            h--;
        }
    }
    quickSort(nums, s, h);
    quickSort(nums, l, e);
}

// TC is O(nlogn) in best, average and worst cases 
private void mergeSort(int[] nums, int s, int e){
    if(s==e) return;
    int m = s+((e-s)/2);
    mergeSort(nums, s,m);
    mergeSort(nums, m+1,e);

    merge(nums, s, m+1, e);
}

private void merge(int[] nums, int s, int m, int e){
    int i=s, j=m;
    int k=0;
    int[] temp = new int[(e-s)+1];
    while(i<m && j<=e){
        if(nums[i]>nums[j]){
            temp[k] = nums[j];
            j++;
        }else{
            temp[k] = nums[i];
            i++;
        }
        k++;
    }
    while(i<m){
        temp[k] = nums[i];
        i++;
        k++;
    }
    while(j<=e){
        temp[k] = nums[j];
        j++;
        k++;
    }
    for(int l=0; l<temp.length; l++){
        nums[s+l] = temp[l];
    }
}

// TC is best - o(n) , avg - o(n^2) , worst - o(n^2)
private void bubbleSort(int[] nums){
    int n = nums.length;
    for(int i=0; i<n; i++){
        for(int j=1; j<n-i; j++){
            if(nums[j]<nums[j-1]) swap(nums, j, j-1);
        }
    }
}    

// TC is best - o(n^2) , avg - o(n^2) , worst - o(n^2)
private void selectionSort(int[] nums){
    int n = nums.length;
    for(int i=0; i<n; i++){
        int maxIdx = 0;
        for(int j=0; j<n-i; j++){
            if(nums[j]>nums[maxIdx]) maxIdx = j;
        }
        swap(nums,maxIdx,n-i-1);
    }
}

// TC is best - o(n) , avg - o(n^2) , worst - o(n^2)
private void insertionSort(int[] nums){
    int n = nums.length;
    for(int i=1; i<n; i++){
        int j=i;
        while(j!=0 && nums[j]<nums[j-1]){
            swap(nums,j,j-1);
            j--;
        }
    }
}

private void swap(int[] arr, int i, int j){
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

