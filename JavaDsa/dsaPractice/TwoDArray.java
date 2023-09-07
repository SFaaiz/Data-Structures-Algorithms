package dsaPractice;

import java.util.Arrays;

public class TwoDArray {

    //linear searching in a 2d array
    static int[] linear(int[][] arr, int target){
        for (int i = 0; i < arr.length; i++) {
            for(int j=0; j<arr[i].length; j++){
                if(arr[i][j] == target){
                    return new int[] {i,j};
                }
            }
        }
        return new int[]{-1,-1};
    }

    // searching in a 2d array which is sorted row-wise and column wise (NOT linear search)
    static int[] search(int[][] arr, int target){
        int row=0;
        int col=arr[0].length-1;
        while (row<arr.length && col>=0){
            if(arr[row][col] == target){
                return new int[]{row,col};
            }
            if(arr[row][col] < target){
                row++;
            }else {
                col--;
            }
        }
        return new int[]{-1,-1};
    }

    // binary search in a 2d array (sorted row wise, last element of the row is less than first element of next row)
    static int[] binarySearchIn2dArray(int[][] arr, int target){
        int row = arr.length;
        int col = arr[0].length;
        if(row==1){
            return BSIn1dArray(arr,0,0,col-1,target);
        }
        int rStart=0;
        int rEnd=row-1;
        int cMid = col/2;

        while (rStart < rEnd-1){
            int mid= (rStart+rEnd)/2;
            if(arr[mid][cMid] == target){
                return new int[]{mid,cMid};
            }
            if(arr[mid][cMid] < target){
                rStart = mid;
            }else {
                rEnd=mid;
            }
        }

        // when only 2 rows r remaining
        if(arr[rStart][cMid] == target){ // 0,2
            return new int[]{rStart,cMid};
        }
        if (arr[rStart+1][cMid] == target){ // 1,2
            return new int[]{rStart+1,cMid};
        }
        if (arr[rStart][cMid-1] >= target){
            return BSIn1dArray(arr,rStart,0,cMid-1,target);
        }
        if (arr[rStart][cMid+1] <= target){
            return BSIn1dArray(arr,rStart,cMid+1,col-1,target);
        }
        if (arr[rStart+1][cMid-1] >= target){
            return BSIn1dArray(arr,rStart+1,0,cMid-1,target);
        }

        else { //(arr[rStart+1][cMid+1] <= target){
            return BSIn1dArray(arr,rStart+1,cMid+1,col-1,target);
        }
    }
    static int[] BSIn1dArray(int[][] arr, int row, int cStart, int cEnd, int target){
        while (cStart<=cEnd){
            int cMid = cStart + (cEnd-cStart)/2;
            if(arr[row][cMid] == target){
                return new int[]{row,cMid};
            }
            if(arr[row][cMid] < target){
                cStart = cMid+1;
            }else {
                cEnd=cMid-1;
            }
        }
        return new int[]{-1,-1};
    }

    public static void main(String[] args) {
        int[][] arr = {{2,4,6},
                {10,12,14},
                {18,20,22}};
        int target =18;
        System.out.println(Arrays.toString(binarySearchIn2dArray(arr,target)));

//        int[][] arr = {{2,4,6,8},
//                {3,5,7,9},
//                {6,8,11,13},
//                {7,10,12,14}};
//        System.out.println(Arrays.toString(search(arr,2)));

//        System.out.println(Arrays.toString(linear(arr,31)));
    }
}
