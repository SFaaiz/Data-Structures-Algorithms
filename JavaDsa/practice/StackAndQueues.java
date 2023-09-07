package practice;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

// insertion efficient
class QueueUsingStacks{
    private Stack<Integer> first;
    private Stack<Integer> second;
    public QueueUsingStacks(){
        first = new Stack<>();
        second = new Stack<>();
    }
    public void insert(int item){
        first.push(item);
    }

    public int remove() throws Exception {
        if(isEmpty()){
            throw new Exception("Queue is Empty");
        }
        while (!first.isEmpty()){
            second.push(first.pop());
        }
        int removed = second.pop();
        while (!second.isEmpty()){
            first.push(second.pop());
        }
        return removed;
    }

    public int peek() throws Exception {
        if(isEmpty()){
            throw new Exception("Queue is Empty");
        }
        while (!first.isEmpty()){
            second.push(first.pop());
        }
        int peeked = second.peek();
        while (!second.isEmpty()){
            first.push(second.pop());
        }
        return peeked;
    }
    private boolean isEmpty(){
        return first.isEmpty();
    }
}

// removal efficient
class QueueUsingStacks2{
    private Stack<Integer> first;
    private Stack<Integer> second;
    public QueueUsingStacks2(){
        first = new Stack<>();
        second = new Stack<>();
    }

    public int remove() throws Exception {
        if(isEmpty()){
            throw new Exception("Queue is empty");
        }
        return first.pop();
    }

    public void insert(int item){
        while (!first.isEmpty()){
            second.push(first.pop());
        }
        second.push(item);
        while (!second.isEmpty()){
            first.push(second.pop());
        }
    }

    public int peek() throws Exception {
        if (isEmpty()) {
            throw new Exception("Queue is Empty");
        }
        return first.peek();
    }

    private boolean isEmpty(){
        return first.isEmpty();
    }
}



public class StackAndQueues {
    // u r given two stacks , u can pop one item(no.) from it each time , so what is the max. no of item u can remove
    // from it so that the sum of all removed items do not exceed the allowed sum
    static int twoStacksGame(int maxSum, int[] st1, int[]st2){
        return twoStacksGame(maxSum,st1,st2,0,0)-1;
    }
    static int twoStacksGame(int maxSum, int[] st1, int[]st2, int curSum, int count){
        if(curSum>maxSum || st1.length==0 || st2.length==0){
            return count;
        }
        int leftCount = twoStacksGame(maxSum,Arrays.copyOfRange(st1,1,st1.length),st2,curSum+st1[0],count+1);
        int rightCount = twoStacksGame(maxSum,st1,Arrays.copyOfRange(st2,1,st2.length),curSum+st2[0],count+1);
        return Math.max(leftCount,rightCount);
    }

    static boolean isValidParenthesis(String s){
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='(' || s.charAt(i)=='{' || s.charAt(i)=='['){
                st.push(s.charAt(i));
            }
            else {
                char c = s.charAt(i);
                if(st.isEmpty())return false;
                if(c==')'){
                    if(st.peek()!='(')return false;
                }
                else if(c=='}'){
                    if(st.peek()!='{')return false;
                }
                else {
                    if(st.peek()!='[')return false;
                }
                st.pop();
            }
        }
        return st.isEmpty();
    }

    // only () this type of parenthesis is considered not {} [] of these types
    static int minAddToMakeValidParenthesis(String s){
        Stack<Character> st = new Stack<>();
        for(char ch : s.toCharArray()){
            if(ch==')'){
                if(st.isEmpty() || st.peek()!='('){
                    st.push(ch);
                }
                else {
                    st.pop();
                }
            }else {
                st.push(ch);
            }
        }
        return st.size(); // in the end only those without opening or closing will remain in the stack
    }

//    static int largestAreaHistogram(int[]histogram){
//        int ans=0;
//        Stack<Integer> st = new Stack<>();
//        int[] ps = new int[histogram.length]; // previous smaller valued index
//        int[] ns = new int[histogram.length]; // next smaller valued index
//
//    }

//    static int[] previousSmaller(int[]arr, Stack<Integer> st){
//        for (int i = 0; i < arr.length; i++) {
//            while (!st.isEmpty() && arr[i] <= arr[st.peek()]){
//                st.pop();
//            }
//            if(st.isEmpty()){
//                arr[i]=-1;
//            }else {
//
//            }
//        }
//    }

    public static void main(String[] args) throws Exception{
        System.out.println(isValidParenthesis("({[]})"));
        System.out.println(isValidParenthesis("({})"));
        System.out.println(isValidParenthesis("()"));
        System.out.println(isValidParenthesis("({[]}})"));
        System.out.println(isValidParenthesis("({[(]})"));
        System.out.println(isValidParenthesis(")()"));

//        int[]st1 = {4,2,4,6,1};
//        int[]st2 = {2,1,8,5};
//        System.out.println(twoStacksGame(10,st1,st2));

//        QueueUsingStacks2 q = new QueueUsingStacks2();
//        System.out.println("removal efficient");
//        q.insert(1);
//        q.insert(2);
//        q.insert(3);
//        System.out.println(q.peek());
//        System.out.println(q.remove());
//        System.out.println(q.peek());

//        QueueUsingStacks q = new QueueUsingStacks();
//        q.insert(1);
//        q.insert(2);
//        q.insert(3);
//        System.out.println(q.peek());
//        System.out.println(q.remove());
//        System.out.println(q.peek());
    }
}
