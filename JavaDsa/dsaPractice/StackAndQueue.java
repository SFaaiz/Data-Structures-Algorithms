package dsaPractice;

//import hashmapAndHeaps.LRU;

import java.util.*;
import java.util.LinkedList;

class customStack{
    protected int[] arr;
    private static final int DEFAULT_SIZE = 5;
    int ptr = 0;
    customStack(){
        this(DEFAULT_SIZE);
    }
     customStack(int size){
         arr = new int[size];
     }
     public boolean push(int val) throws Exception {
        if(isFull()){
            throw new Exception("Stack is Full");
        }
        arr[ptr++] = val;
        return true;
     }
     public int pop() throws Exception {
        if(isEmpty()){
            throw new Exception("Cannot remove from an empty stack");
        }
        return ptr--;
     }
     public int peek() throws Exception {
         if(isEmpty()){
             throw new Exception("Stack is Empty");
         }
         return arr[ptr-1];
     }

    protected boolean isEmpty() {
        return ptr == 0;
    }

    protected boolean isFull() {
        return ptr >= arr.length;
    }
}

class DynamicStack extends customStack{
    DynamicStack(){
        super();
    }
    DynamicStack(int size){
        super(size);
    }
    @Override
    public boolean push (int val) throws Exception{
        if(isFull()){
            int[] temp = new int[arr.length*2];
            for (int i = 0; i < arr.length; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }
        return super.push(val);
    }
}

class customQueue{
    protected int[] arr;
    private static final int DEFAULT_SIZE = 5;
    int ptr = 0;
    customQueue(){
        this(DEFAULT_SIZE);
    }
    customQueue(int size){
        arr = new int[size];
    }
    public boolean insert(int val) throws Exception {
        if(isFull()){
            throw new Exception("Stack is Full");
        }
        arr[ptr++] = val;
        return true;
    }
    public int delete() throws Exception {
        if(isEmpty()){
            throw new Exception("Queue is Empty");
        }
        int deleted = arr[0];
        int i =0;
        while (i<ptr-1){
            arr[i] = arr[i+1];
            i++;
        }
        ptr--;
        return deleted;
    }
    public void display(){
        for (int i = 0; i < ptr; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    protected boolean isEmpty() {
        return ptr == 0;
    }

    protected boolean isFull() {
        return ptr == arr.length;
    }
}

class circularQueue{
    protected int[] arr;
    private static final int DEFAULT_SIZE = 5;
    int front =0, end=0, size=0;
    public circularQueue(){
        this(DEFAULT_SIZE);
    }
    public circularQueue(int size){
        arr = new int[size];
    }
    public void insert(int val) throws Exception {
        if(isFull()){
            throw new Exception("Queue is full");
        }
        arr[end%arr.length] = val;
        end++;
        size++;
    }
    public int delete() throws Exception {
        if(isEmpty()){
            throw new Exception("Cannot delete from an empty queue");
        }
        int deleted = arr[front%arr.length];
        front++;
        size--;
        return deleted;
    }

    public void display(){
        for (int i = front; i < end; i++) {
            System.out.print(arr[i%arr.length] + " ");
        }
        System.out.println();
    }

    protected boolean isFull(){
        return size == arr.length;
    }
    protected boolean isEmpty(){
        return size == 0;
    }
}

class DynamicCircularQueue extends circularQueue{
    public DynamicCircularQueue(){
        super();
    }
    public DynamicCircularQueue(int size){
        super(size);
    }
    @Override
    public void insert(int val) throws Exception {
        if(isFull()){
            int[] temp = new int[arr.length*2];
            for (int i = 0; i < arr.length; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }
        super.insert(val);
    }
}

class customDeque{
    protected int[] arr;
    private static final int DEFAULT_SIZE = 5;
    int front =0, end=0, size=0;
    public customDeque(){
        this(DEFAULT_SIZE);
    }
    public customDeque(int size){
        arr = new int[size];
    }

    public void insertFirst(int val) throws Exception {
        if(isFull()){
            throw new Exception("Queue is full");
        }
        if(front-1 >= 0){
            arr[--front] = val;
            size++;
        }else {
            for (int i = end; i > 0; i++) {
                arr[i] = arr[i-1];
            }

        }
    }

    public void insertLast(int val) throws Exception {
        if(isFull()){
            throw new Exception("Queue is full");
        }
        arr[end%arr.length] = val;
        end++;
        size++;
    }
    public int delete() throws Exception {
        if(isEmpty()){
            throw new Exception("Cannot delete from an empty queue");
        }
        int deleted = arr[front%arr.length];
        front++;
        size--;
        return deleted;
    }


    protected boolean isFull(){
        return size == arr.length;
    }
    protected boolean isEmpty(){
        return size == 0;
    }
}

class stackUsingQueue{
    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    public void push(int a){
        q2.add(a);
        while (!q1.isEmpty()){
            q2.add(q1.poll());
        }
        while (!q2.isEmpty()){
            q1.add(q2.poll());
        }
    }
    public int pop(){
        return q1.poll();
    }

}

// least recently used cache is a ds - u have to design two methods for it i.e. put (key, val) (will put a node in this ds if key
// does not exist else remove existing one then put // putting will make it used recently
// get will return the val of key provided if exist or else -1 // using get method will make it used recently
// this ds will have a fixed size and when the size gets full then remove LRU cache and insert the new one at start
class LRUCache{
    private Node head;
    private Node tail;
    private int capacity;
    public LRUCache(int capacity){
        this.capacity = capacity;
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }
    private class Node{
        Node prev, next;
        int key, val;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    HashMap<Integer,Node> record = new HashMap<>();

    public int get(int key){
        if(record.containsKey(key)){
            Node n = record.get(key);
            remove(n);
            insert(n);
            return n.val;
        }else return -1;
    }

    public void put(int key, int val){
        if(record.containsKey(key)){
            remove(record.get(key));
            record.remove(key);
        }
        if(record.size() == capacity){
            record.remove(tail.prev.key);
            remove(tail.prev);
        }
        Node n = new Node(key, val);
        insert(n);
        record.put(key,n);
    }

    private void remove(Node n){
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void insert(Node n){
        n.next = head.next;
        n.next.prev = n;
        n.prev = head;
        head.next = n;
    }

}

// least frequently used cache is a ds which has 2 methods get and put
// get method will give the val of key provided if exist and increase its frequency and it will be recently used in that frequency
// put will insert the key, val in the form of doubly node if there is space, at frequency 1 if key doesn't exist already
// if the space is full then delete the LFU cache and if there are more than one then delete the LRU Cache
class LFUCache{
    private int capacity, minFrequency;
    private HashMap<Integer,DoublyList> freqList;
    private HashMap<Integer,DoublyNode> keyNode;

    public LFUCache(int capacity){
        this.capacity = capacity;
        minFrequency= 0;
        freqList = new HashMap<>();
        keyNode = new HashMap<>();
    }

    public void put(int key, int val){
        if(capacity == 0) return;
        if(keyNode.containsKey(key)){
            DoublyNode node = keyNode.get(key);
            node.val = val;
            int frequency = node.frequency;
            DoublyList list = freqList.get(frequency);
            list.remove(node);
            if(list.size == 0) {
                freqList.remove(frequency);
                if(minFrequency == frequency){
                    while (!freqList.isEmpty() && !freqList.containsKey(minFrequency)){
                        minFrequency++;
                    }
                }
            }
            node.frequency++;
            updateNode(node);
            return;
        }
        if(keyNode.size() == capacity){
            DoublyList list = freqList.get(minFrequency);
            DoublyNode node = list.tail.prev;
            list.remove(node);
            keyNode.remove(node.key);
            if(list.size == 0) {
                freqList.remove(minFrequency);
                while (!freqList.isEmpty() && !freqList.containsKey(minFrequency)){
                    minFrequency++;
                }
            }
        }
            minFrequency = 1;
            DoublyNode node = new DoublyNode(key,val);
            keyNode.put(key, node);
            DoublyList list = freqList.getOrDefault(1,new DoublyList());
            list.insert(node);
            freqList.put(1,list);
    }

    public int get(int key){
        if(keyNode.containsKey(key)){
            DoublyNode node = keyNode.get(key);
            int frequency = node.frequency;
            DoublyList list = freqList.get(frequency);
            list.remove(node);
            if(list.size == 0 ){
                freqList.remove(frequency);
                if(minFrequency == frequency){
//                    while (!freqList.isEmpty() && !freqList.containsKey(minFrequency)){
//                        minFrequency++;
//                    }
                    minFrequency++;
                }
            }
            node.frequency++;
            updateNode(node);
            return node.val;
        }else {
            return -1;
        }
    }

    private void updateNode(DoublyNode node){
        DoublyList list = freqList.getOrDefault(node.frequency,new DoublyList());
        list.insert(node);
        freqList.put(node.frequency,list);
    }

    private class DoublyNode{
        int key, val, frequency;
        DoublyNode prev, next;
        public DoublyNode(int key, int val){
            this.key = key;
            this.val = val;
            frequency=1;
        }
    }

    private class DoublyList{
        int size;
        DoublyNode head, tail;
        public DoublyList(){
            head = new DoublyNode(0,0);
            tail = new DoublyNode(0,0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        private void insert(DoublyNode node){
            node.next = head.next;
            head.next.prev = node;
            node.prev = head;
            head.next = node;
            size++;
        }
        private void remove(DoublyNode node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
    }

}



public class StackAndQueue {

    static int rottenOranges(int[][] grid){
        if(grid.length == 0) return 0;
        Queue<int[]> q = new LinkedList<>();
        int freshOranges = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 2) q.add(new int[]{i,j});
                if(grid[i][j] == 1) freshOranges++;
            }
        }
        if(freshOranges == 0) return 0;
        int cnt=0;
        int minutes = 0;
        int[] xAxis = {0,-1,0,1};
        int[] yAxis = {-1,0,1,0};
        while (!q.isEmpty()){
            int size = q.size();
            minutes++;
            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();
                for (int j = 0; j < 4; j++) {
                    int x = cell[0] + xAxis[j];
                    int y = cell[1] + yAxis[j];
                    if(x<0 || y<0 || x>=grid.length || y>=grid.length || grid[x][y]==0 || grid[x][y]==2) continue;;
                    grid[x][y]= 2;
                    q.add(new int[]{x,y});
                    cnt++;
                }
            }
        }
        return freshOranges==cnt ? minutes-1 : -1;
    }

    // return indices of nextGreaterElement for no. at each index consider the array as circular if not found on right
    // check on left as well else put -1
    static int[] nextGreaterElement(int[] nums){
        int[] nge = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (st.isEmpty() == false && st.peek() <= nums[i % n]) {
                st.pop();
            }

            if (i < n) {
                if (st.isEmpty() == false) nge[i] = st.peek();
                else nge[i] = -1;
            }

            st.push(nums[i % n]);
        }
        return nge;
    }
    public static void main(String[] args) throws Exception{

        LRUCache c = new LRUCache(2);
        c.put(1,1);
        c.put(2,2);
        System.out.println(c.get(1));
        c.put(3,3);
        System.out.println(c.get(2));

//        int[][] grid = {{2,0,0,2},
//                        {1,0,0,1},
//                        {1,0,1,1},
//                        {0,0,0,1}};
//        System.out.println(rottenOranges(grid));

//        LFUCache cache = new LFUCache(1);
//        String[] str = {"put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get",
//                "get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get",
//                "get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put",
//                "get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put",
//                "get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"};
//
//        int[][] arr =  { {10,13},{3,17},{6,11},{10,5},{9,10},{13},{2,19},{2},{3},{5,25},{8},{9,22},{5,5},{1,30},{11},{9,12},{7},{5},{8},{9},{4,30},{9,3},
//                {9},{10},{10},{6,14},{3,1},{3},{10,11},{8},{2,14},{1},{5},{4},{11,4},{12,24},{5,18},{13},{7,23},{8},{12},{3,27}, {2,12},{5},{2,9},{13,4},
//                {8,18},{1,7},{6},{9,29},{8,21},{5},{6,30},{1,12},{10},{4,15},{7,22},{11,26},{8,17},{9,29},{5},{3,4},{11,30}, {12},{4,29},{3},{9},{6},
//                {3,4},{1},{10},{3,29},{10,28},{1,20},{11,13},{3},{3,12},{3,8},{10,9},{3,26},{8},{7},{5},{13,17},{2,27},{11,15},{12}, {9,19},{2,15},
//                {3,16},{1},{12,17},{9,1},{6,19},{4},{5},{5},{8,1},{11,7},{5,2},{9,28},{1},{2,2},{7,4},{4,22},{7,24},{9,26},{13,28},{11,26}};

//        for (int i = 0; i < str.length; i++) {
//            if(str[i]=="put"){
//                cache.put(arr[i][0],arr[i][1]);
//            }else {
//                System.out.println(cache.get(arr[i][0]));
//            }
//            System.out.println("---" + Arrays.toString(arr[i]) + " " + i);
//        }

//        int[] arr = {5,6,4,3,2,1};
//        System.out.println(Arrays.toString(nextGreaterElement(arr)));

//        stackUsingQueue st = new stackUsingQueue();
//        st.push(1);
//        st.push(2);
//        st.push(3);
//        System.out.println(st.pop());
//        System.out.println(st.pop());
//        System.out.println(st.pop());

//        DynamicCircularQueue queue = new DynamicCircularQueue(3);
//        queue.insert(1);
//        queue.insert(2);
//        queue.insert(3);
//        queue.insert(6);
//        queue.display();

//        circularQueue queue = new circularQueue(3);
//        queue.insert(1);
//        queue.insert(2);
//        queue.insert(3);
//        queue.display();
//        queue.delete();
//        queue.insert(4);
//        queue.delete();
//        queue.insert(5);
//        queue.delete();
//        queue.insert(6);
//        queue.display();

//        customQueue queue = new customQueue(3);
//        queue.insert(1);
//        queue.insert(2);
//        queue.insert(3);
//        queue.display();
//        queue.delete();
//        queue.delete();
//        queue.delete();
//        queue.display();

//        DynamicStack stack = new DynamicStack(3);
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        stack.push(4);
//        stack.pop();
//        stack.push(5);
//        System.out.println(stack.peek());

//        customStack stack = new customStack(3);
//        stack.push(11);
//        stack.push(12);
//        stack.push(13);
////        stack.push(11);
////        stack.push(11);
//        stack.pop();
//        stack.pop();
//        stack.pop();
//        System.out.println(stack.peek());
    }
}
