package dsaPractice;

// outer for loop is for rows and inner for loop is for columns

public class patternQuestions {
//            *****
//            *****
//            *****
//            *****
//            *****
    static void pattern1(int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

//            *
//            **
//            ***
//            ****
//            *****
    static void pattern2(int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i ; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

//            *****
//            ****
//            ***
//            **
//            *
    static void pattern3(int n){
        for (int i = n; i >0; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

//            1
//            1 2
//            1 2 3
//            1 2 3 4
//            1 2 3 4 5
    static void pattern4(int n){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

//            *
//            **
//            ***
//            ****
//            *****
//            ****
//            ***
//            **
//            *
    static void pattern5(int n){
        for (int row = 1; row < 2*n; row++) {
            int totalCols = (row>n) ? 2*n - row : row;
            for (int col = 0; col < totalCols; col++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

//                      *
//                     **
//                    ***
//                   ****
//                  *****
    static void pattern6(int n){
        for (int i = 1; i <= n; i++) {
            int spaces = n-i;
            for (int j = 0; j < spaces; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

//            *****
//             ****
//              ***
//               **
//                *
static void pattern7(int n){

    for (int i = n; i >0; i--) {
        int spaces = n-i;
        for (int j = 0; j < spaces; j++) {
            System.out.print(" ");
        }
        for (int j = 0; j < i; j++) {
            System.out.print("*");
        }
        System.out.println();
    }
}

//                    *
//                   ***
//                  *****
//                 *******
//                *********
static void pattern8(int n){
    for (int i = 1; i <= n; i++) {
        int spaces = n-i;
        for (int j = 0; j < spaces; j++) {
            System.out.print(" ");
        }
        for (int j = 0; j < (2*i)-1; j++) {
            System.out.print("*");
        }
        System.out.println();
    }
}

//                  *
//                 * *
//                * * *
//               * * * *
//              * * * * *
//               * * * *
//                * * *
//                 * *
//                  *
    static void pattern9(int n){
        for (int i = 1; i < 2*n; i++) {
            int totalCols = (i>n) ? (2*n) - i : i;
            int spaces = (i>n) ? i%n : n-i;
            for (int j = 0; j < spaces; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < totalCols; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

//                          1
//                        2 1 2
//                      3 2 1 2 3
//                    4 3 2 1 2 3 4
//                  5 4 3 2 1 2 3 4 5
    static void pattern10(int n){
        for (int i = 1; i <=5; i++) {
            int spaces = n-i;
            for (int j = 0; j < spaces; j++) {
                System.out.print("  ");
            }
            for (int j = i; j > 0; j--) {
                System.out.print(j + " ");
            }
            for (int j = 2; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

//                 1
//                212
//               32123
//              4321234
//               32123
//                212
//                 1
static void pattern11(int n){
    for (int i = 1; i < 2*n; i++) {
        int spaces = (i>n) ? i%n : n-i;
        for (int j = 0; j < spaces; j++) {
            System.out.print(" ");
        }
        int bound = (i>n) ? (2*n)-i : i; // start and end values of rows > n
        for (int j = bound ; j > 0; j--) {
            System.out.print(j );
        }
        for (int j = 2; j <= bound; j++) {
            System.out.print(j );
        }
        System.out.println();
    }
}

//          4 4 4 4 4 4 4
//          4 3 3 3 3 3 4
//          4 3 2 2 2 3 4
//          4 3 2 1 2 3 4
//          4 3 2 2 2 3 4
//          4 3 3 3 3 3 4
//          4 4 4 4 4 4 4
    static void pattern12(int n){
        for (int i = 1; i < 2*n; i++) {
            for (int j = 1; j < 2*n; j++) {
                int atEveryIndex =(n+1) - Math.min(Math.min(j,i),Math.min(2*n-j, 2*n-i)); // left, top, right, bottom
                System.out.print(atEveryIndex + " "); // min distance from all directions
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        pattern12(4);
//        pattern11(4);
//        pattern10(5);
//        pattern9(4);
//        pattern8(5);
//        pattern7(5);
//        pattern6(5);
//        pattern5(5);
//        pattern4(5);
//        pattern3(5);
//        pattern2(5);
//        pattern1(5);
    }
}
