package fullConceptInOneFile;

class CustomStack{
    protected int[] data;
    private static final int DEFAULT_SIZE = 5;
    protected int end=0;
    public CustomStack(){
        this(DEFAULT_SIZE);
    }
    public CustomStack(int size){
        this.data = new int[size];
    }
    public boolean push(int val) throws Exception {
        if(isFull()){
            throw new Exception("Stack is full");
        }
        data[end++]=val; // insert value at current index and increment index
        return true;
    }
    public int pop() throws Exception {
        if(isEmpty()){
            throw new Exception("Stack is empty");
        }
        return data[--end]; // as current index is empty, to insert new values, first decrement index by one and return val
    }
    public int peek() throws Exception {
        if(isEmpty()){
            throw new Exception("Stack is empty");
        }
        return data[end-1]; // just return value of previous index not decrement the index
    }
    public void display(){
        for (int i = 0; i < end; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    protected boolean isEmpty() {
        return end==0;
    }

    protected boolean isFull() {
        return end==data.length;
    }

}

class DynamicStack extends CustomStack{
    public DynamicStack(){
        super();
    }
    public DynamicStack(int val){
        super(val);
    }

    @Override
    public boolean push(int val) throws Exception {
        if(isFull()){
            int[] temp= new int[data.length*2];
            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }
            data=temp;
        }

        return super.push(val);
    }
}

class CustomQueue{
    protected int[] data;
    private static final int DEFAULT_SIZE = 5;
    public CustomQueue(){
        this(DEFAULT_SIZE);
    }
    public CustomQueue(int size){
        this.data = new int[size];
    }
    protected int front = 0, end = 0;
    public void insert(int val) throws Exception {
        if(isFull()){
            throw new Exception("FULL");
        }
        data[end++] = val;
    }
    public int remove() throws Exception {
        if(isEmpty()){
            throw new Exception("EMPTY");
        }
        int removed= data[0];
        for (int i = 0; i < end-1; i++) {
            data[i] = data[i+1];
        }
        end--;
        return removed;
    }

    public void display(){
        for (int i = 0; i < end; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
    protected boolean isEmpty() {
        return end==0;
    }

    protected boolean isFull() {
        return end==data.length;
    }
}

class DynamicQueue extends CustomQueue{
    public DynamicQueue(){
        super();
    }
    public DynamicQueue(int size){
        super(size);
    }

    @Override
    public void insert(int val) throws Exception {
        if(isFull()){
            int[] temp = new int[data.length*2];
            for (int i = 0; i < end; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
        super.insert(val);
    }
}

class CircularQueue{
    protected int[] data;
    private static final int DEFAULT_SIZE = 5;
    protected int front=0, end=0, size=0;
    public CircularQueue() {
        this(DEFAULT_SIZE);
    }
    public CircularQueue(int size) {
        this.data = new int[size];
    }

    public void insert(int val) throws Exception {
        if(isFull()){
            throw new Exception("Stack is Full");
        }
        data[end++] = val;
        end = end % data.length;
        size++;
    }
    public int remove() throws Exception {
        if(isEmpty()){
            throw new Exception("Stack is empty");
        }
        int removed = data[front++];
        front = front%data.length;
        size--;
        return removed;
    }
    public void display(){
        int i=front;
        do{
            System.out.print(data[i++] + " ");
            i = i%data.length;
        }
        while (i!=end);
        System.out.println();
    }
    protected boolean isFull(){
        return size == data.length;
    }
    protected boolean isEmpty(){
        return size==0;
    }
}

class DynamicCircularQueue extends CircularQueue{
    public DynamicCircularQueue(int size){
        super(size);
    }
    public DynamicCircularQueue(){
        super();
    }

    @Override
    public void insert(int val) throws Exception {
        if(isFull()){
            int[] temp = new int[data.length*2];
            for (int i = 0; i < data.length; i++) {
                temp[i] = data[(i+front)%data.length];
            }
            front = 0;
            end = data.length;
            data = temp;
        }
        super.insert(val);
    }
}

public class StackAndQueue {
    public static void main(String[] args) throws Exception {
        DynamicCircularQueue dcq = new DynamicCircularQueue();
        dcq.insert(1);
        dcq.insert(2);
        dcq.insert(3);
        dcq.insert(4);
        dcq.insert(5);
        dcq.insert(6);
        dcq.insert(7);
        dcq.display();
        dcq.remove();
        dcq.insert(11);
        dcq.display();

//        CircularQueue cq= new CircularQueue();
//        cq.insert(1);
//        cq.insert(2);
//        cq.insert(3);
//        cq.insert(4);
//        cq.insert(5);
//        cq.display();
//        cq.remove();
//        cq.insert(8);
//        cq.display();

//        DynamicQueue dq = new DynamicQueue();
//        dq.insert(1);
//        dq.insert(2);
//        dq.insert(3);
//        dq.insert(4);
//        dq.insert(5);
//        dq.insert(6);
//        dq.display();
//        dq.remove();
//        dq.insert(7);
//        dq.display();

//        CustomQueue queue = new CustomQueue();
//        queue.insert(1);
//        queue.insert(2);
//        queue.insert(3);
//        queue.insert(4);
//        queue.insert(5);
//        queue.display();
//        queue.remove();
//        queue.insert(2);
//        queue.display();

//        DynamicStack ds = new DynamicStack();
//        ds.push(1);
//        ds.push(2);
//        ds.push(3);
//        ds.push(4);
//        ds.push(5);
//        ds.push(6);
//        ds.display();
//        ds.pop();
//        ds.push(7);
//        System.out.println(ds.peek());
//        ds.display();

//        CustomStack stack = new CustomStack();
//        stack.push(9);
//        stack.push(8);
//        stack.display();
//        System.out.println(stack.pop());
//        stack.push(6);
//        stack.display();

    }
}
