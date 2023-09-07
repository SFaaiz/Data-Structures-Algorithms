import java.util.*;

public class help {
    public static void main(String[] args) {
        boolean[][] board = new boolean[4][4];
//        nKnights(board,0,0,8);
//        char c = 1;
//        int n = 1+'0';
//        System.out.println(c == n);
        System.out.println(lc("7",""));
    }

    static List<String> lc(String digits, String comb){
        List<String> ans = new ArrayList<>();
        if(digits.isEmpty()){
            ans.add(comb);
            return ans;
        }
        int digit = digits.charAt(0) - '0';
        int st=0,e=0;
        if(digit == 7 || digit==9){
            st = (digit == 7) ? 15 : 22;
            e = (digit == 7) ? 18 : 25;
        }
        else if(digit == 8) {
            st = 18;
            e=21;
        }else{
            st = (digit-2)*3;
            e = ((digit-1)*3)-1;
        }
        for(int i=st; i<=e; i++){
            char letter =(char) ('a' + i);
            ans.addAll(lc(digits.substring(1), comb+letter));
        }
        return ans;
    }
    static void nKnights(boolean[][] board, int row, int col, int knights) {
        if (knights == 0) {
            printBoard2(board);
            return;
        }
        if (row == board.length - 1 && col == board.length) return;
        if (col == board.length) {
            nKnights(board, row + 1, 0, knights);
            return;
        }
        if (safeForKnight(board, row, col)) {
            board[row][col] = true;
            nKnights(board, row, col + 1, knights - 1);
            board[row][col] = false;
        }
        nKnights(board, row, col + 1, knights);
    }

    private static void printBoard2(boolean[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j]) System.out.print("K");
                else System.out.print("-");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean safeForKnight(boolean[][] board, int row, int col) {
        if (row - 1 >= 0 && col - 2 >= 0) if (board[row - 1][col - 2]) return false;
        if (row - 2 >= 0 && col - 1 >= 0) if (board[row - 2][col - 1]) return false;
        if (row - 2 >= 0 && col + 1 < board.length) if (board[row - 2][col + 1]) return false;
        if (row - 1 >= 0 && col + 2 < board.length) if (board[row - 1][col + 2]) return false;
        return true;

    }

//    static int nKnights(boolean[][] board, int row, int col, int knights){
//        if(knights == 0){
//            displayKnight(board);
//            System.out.println();
//            return 1;
//        }
//        int count = 0;
//        if(row == board.length-1 && col == board.length){ // we have checked entire board
//            return 0;
//        }
//        if(col == board.length){ // we have checked entire row, so go to next row
//            return  count + nKnights(board, row+1,0,knights);
//        }
//        if(isSafeForKnight(board,row,col)){
//            board[row][col] = true;
//            count += nKnights(board,row,col+1, knights-1);
//            board[row][col] = false;
//        }
//        return count + nKnights(board, row,col+1,knights);
//    }
}
