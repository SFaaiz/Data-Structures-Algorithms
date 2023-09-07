package dsaPractice;

import java.util.Arrays;

public class Backtracking {
    static void mazePath(int startRow, int startCol, int endRow, int endCol){
        if(startRow < endRow){
            System.out.print("D");
            mazePath(startRow+1,startCol,endRow,endCol);
            return;
        }
        if(endRow < startRow) {
            System.out.print("U");
            mazePath(startRow-1,startCol,endRow,endCol);
            return;
        }
        if(startCol < endCol){
            System.out.print("R");
            mazePath(startRow,startCol+1,endRow,endCol);
            return;
        }
        if(endCol < startCol){
            System.out.print("L");
            mazePath(startRow,startCol-1,endRow,endCol);
            return;
        }
    }
    static void mazeAllPaths(String path, int startRow, int startCol, int endRow, int endCol){ // all shortest paths
        if(startRow==endRow && startCol==endCol){
            System.out.println(path);
            return;
        }

        if(startRow < endRow){
            mazeAllPaths(path+"D", startRow+1,startCol,endRow,endCol);
        }
        if(endRow < startRow) {
            mazeAllPaths(path+"U", startRow-1,startCol,endRow,endCol);
        }
        if(startCol < endCol){
            mazeAllPaths(path+"R", startRow,startCol+1,endRow,endCol);
        }
        if(endCol < startCol){
            mazeAllPaths(path+"L", startRow,startCol-1,endRow,endCol);
        }
    }

    static void mazeAllPathsCombinations(String path, boolean[][] maze, int startRow, int startCol, int endRow, int endCol){
        if(startRow==endRow && startCol==endCol){
            System.out.println(path);
            return;
        }
        if(!maze[startRow][startCol]){ // if current index is false then it means we've come through this path
            return;                          // so can't go back to it again
        }

        maze[startRow][startCol] = false; // mark your current path so as to not go back to it again
        if(startRow < maze.length-1){
            mazeAllPathsCombinations(path+"D", maze, startRow+1,startCol,endRow,endCol);
        }
        if(startRow > 0) {
            mazeAllPathsCombinations(path+"U",maze, startRow-1,startCol,endRow,endCol);
        }
        if(startCol < maze.length-1){
            mazeAllPathsCombinations(path+"R",maze, startRow,startCol+1,endRow,endCol);
        }
        if(startCol > 0){
            mazeAllPathsCombinations(path+"L",maze, startRow,startCol-1,endRow,endCol);
        }
        maze[startRow][startCol] = true;
    }

    static void mazePathPrint(int[][]path, boolean[][] maze, int startRow, int startCol, int endRow, int endCol, int steps){
        if(startRow==endRow && startCol==endCol){
            path[startRow][startCol] = steps;
            for(int[] i : path){
                System.out.println(Arrays.toString(i));
            }
            System.out.println();
            return;
        }
        if(!maze[startRow][startCol]){ // if current index is false then it means we've come through this path
            return;                          // so can't go back to it again
        }

        maze[startRow][startCol] = false; // mark your current path so as to not go back to it again
        path[startRow][startCol] = steps;
        if(startRow < maze.length-1){
            mazePathPrint(path, maze, startRow+1,startCol,endRow,endCol,steps+1);
        }
        if(startRow > 0) {
            mazePathPrint(path,maze, startRow-1,startCol,endRow,endCol,steps+1);
        }
        if(startCol < maze.length-1){
            mazePathPrint(path,maze, startRow,startCol+1,endRow,endCol,steps+1);
        }
        if(startCol > 0){
            mazePathPrint(path,maze, startRow,startCol-1,endRow,endCol,steps+1);
        }
        maze[startRow][startCol] = true;
        path[startRow][startCol] = 0;
    }

    static int nQueens(boolean[][] board, int row){

        if(row == board.length){
            display(board);
            System.out.println();
            return 1;
        }
        int count = 0;
        for (int col = 0; col < board.length; col++) {
            if(isSafe(board, row, col)){
                board[row][col] = true;
                count += nQueens(board,row+1);
                board[row][col] = false;
            }
        }
        return count;
    }

    private static boolean isSafe(boolean[][] board, int row, int col) {
        // to check if a box is safe to place a queen or not, we need to check in three directions, up, rightDiagonal
        // left diagonal as we are placing queens row-wise from top so we don't need to check below

        // to check vertically upwards
        for (int i = 0; i < row; i++) {
            if(board[i][col]){
                return false;
            }
        }

        // to check left diagonal
        int leftMax = Math.min(row, col); // min of row, col is the no. of boxes remaining in left diagonal
        for (int i = 1; i <= leftMax; i++) {
            if(board[row-i][col-i]){
                return false;
            }
        }

        // to check right diagonal
        int rightMax = Math.min(row, board.length-col-1);
        for (int i = 1; i <= rightMax; i++) {
            if(board[row-i][col+i]){
                return false;
            }
        }

        return true;
    }

    private static void display(boolean[][] board) {
        for(boolean[] row : board){
            for(boolean col : row){
                if(col){
                    System.out.print("Q ");
                }else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    static int nKnights(boolean[][] board, int row, int col, int knights){
        if(knights == 0){
            displayKnight(board);
            System.out.println();
            return 1;
        }
        int count = 0;
        if(row == board.length-1 && col == board.length){ // we have checked entire board
            return 0;
        }
        if(col == board.length){ // we have checked entire row, so go to next row
            return  count + nKnights(board, row+1,0,knights);
        }
        if(isSafeForKnight(board,row,col)){
            board[row][col] = true;
            count += nKnights(board,row,col+1, knights-1);
            board[row][col] = false;
        }
        return count + nKnights(board, row,col+1,knights);
    }

    private static boolean isSafeForKnight(boolean[][] board, int row, int col) {
        // check for above rows only because we have been placing knight row-wise from top

        if(isInBounds(board,row-1,col-2)){
            if(board[row-1][col-2]){
                return false;
            }
        }

        if(isInBounds(board,row-1,col+2)){
            if(board[row-1][col+2]){
                return false;
            }
        }

        if(isInBounds(board,row-2,col+1)){
            if(board[row-2][col+1]){
                return false;
            }
        }

        if(isInBounds(board,row-2,col-1)){
            if(board[row-2][col-1]){
                return false;
            }
        }

        return true;
    }

    private static boolean isInBounds(boolean[][] board, int row, int col) {
        if(row >= 0 && row < board.length && col >= 0 && col < board.length){
            return true;
        }
        return false;
    }

    private static void displayKnight(boolean[][] board) {
        for(boolean[] row : board){
            for(boolean col : row){
                if(col){
                    System.out.print("K ");
                }else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    // sudoku solver
    static void sudokuSolver(char[][] board){
        helper(board, 0, 0);
    }

    private static boolean helper(char[][] board, int row, int col) {
        if(row == board.length){
            return true; // it means we have placed no.s on entire board
        }
        int newRow = 0;
        int newCol = 0;
        if(col == board.length-1){
            newRow = row +1;
            newCol = 0;
        }else {
            newRow = row;
            newCol = col+1;
        }
        if(board[row][col] != '0'){
            if(helper(board, newRow, newCol))
                return true;
        }
        else {
            for (int i = 1; i <= 9; i++) {
                if(isSafeNum(board, row, col, i)){
                    board[row][col] = (char) (i+'0');
                    if(helper(board, newRow, newCol)){
                        return true;
                    }
                    else {
                        board[row][col] = '0';
                    }
                }
            }
        }
        return false;
    }

    private static boolean isSafeNum(char[][] board, int row, int col, int num) {
        // to check entire row and col if that number exists or not
            for (int j = 0; j < board.length; j++) {
                if(board[row][j] == (char) num+'0' || board[j][col] == (char) num+'0'){
                    return false;
                }
            }

//            start of 3x3 grid
            int startRow = (row/3) * 3;
            int startCol = (col/3) * 3;
        for (int i = startRow; i < startRow+3; i++) {
            for (int j = startCol; j < startCol+3; j++) {
                if(board[i][j] == (char) num+'0'){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        mazePath(2,2,0,0);
//        mazeAllPaths("",0,0,2,2);
        boolean [][] maze =
                {
                        {true,true,true},
                        {true,true,true},
                        {true,true,true}
                };
//        mazeAllPathsCombinations("",maze,0,0,1,2);
//        mazePathPrint(new int[3][3], maze,0,0,2,0,1);
        int n = 4;
        boolean[][] board = new boolean[n][n];
//        System.out.println(nQueens(board, 0));

//        System.out.println(nKnights(board,0,0,8));

        char[][] board1 =
                      { {'3', '0', '6', '5', '0', '8', '4', '0', '0'},
                        {'5', '2', '0', '0', '0', '0', '0', '0', '0'},
                        {'0', '8', '7', '0', '0', '0', '0', '3', '1'},
                        {'0', '0', '3', '0', '1', '0', '0', '8', '0'},
                        {'9', '0', '0', '8', '6', '3', '0', '0', '5'},
                        {'0', '5', '0', '0', '9', '0', '6', '0', '0'},
                        {'1', '3', '0', '0', '0', '0', '2', '5', '0'},
                        {'0', '0', '0', '0', '0', '0', '0', '7', '4'},
                        {'0', '0', '5', '2', '0', '6', '3', '0', '0'} };

        sudokuSolver(board1);
        for (int i = 0; i < board1.length; i++) {
            for (int j = 0; j < board1.length; j++) {
                System.out.print(board1[i][j] + " ");
            }
            System.out.println();
        }
//        for (int i = 0; i < board1.length*board1.length; i++) {
//            if(i%board1.length == 0 && i!=0){
//                System.out.println();
//            }
//            System.out.print(board1[i/board1.length][i%board1.length] + " ");
        }
}
