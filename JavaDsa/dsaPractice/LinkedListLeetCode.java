package dsaPractice;

class SLL {
    static Node head;
    Node tail;
    int size;

    public SLL() {
        this.size = 0;
    }

    public static class Node {
        int val;
        Node next;
        public Node(){

        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public void insertAtStart(int val) {
        Node node = new Node(val);
        node.next = head;
        head = node;
        if (head.next == null) {
            tail = node;
        }
        size++;
    }

    public void insertAtEnd(int val) {
        if (head == null) {
            insertAtStart(val);
            return;
        }
        Node node = new Node(val);
        tail.next = node;
        tail = node;
        size++;
    }

    public void display() {
        Node node = head;
        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println("END");
    }
    public static void display(Node node) {
        while (node != null) {
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println("END");
    }

    public void insertRecursion(int val, int index){
        head = insertRecursionHelper(val,index,head);// if value to be inserted is at 0th index then head should be pointing it
    }
    private Node insertRecursionHelper(int val, int index, Node node){
        if(index==0){
            Node n = new Node(val,node);// created a new node whose next part will point to the current node
            size++;
            return n;// returning this new node so that previous node points to the newly inserted node
        }
        node.next = insertRecursionHelper(val,index-1,node.next);
        return node;
    }

    // my method
    public void removeDuplicate(){ // remove duplicate valued nodes from a sorted LL
        Node node = head;
        if(head == null){
            return;
        }
        Node node2 = node.next;
        while (node2 != null){
            if(node2.val != node.val){
                node.next= node2;
                node = node2;
                node2=node2.next;
            }else{
                node2=node2.next;
            }
        }
        node.next = null;
    }
    // kunal's method
    public void removeDuplicate2(){ // remove duplicate valued nodes from a sorted LL
        Node node = head;
        while (node!=null && node.next != null){ // if current node is the last node then we dont want to compare its value from null
            if(node.val == node.next.val){
                node.next = node.next.next; // if the val of next node is same as curr one then point curr to next of next
            }else {
                node = node.next; // else move the current node
            }
        }
    }

    public static Node mergeTwoSortedList(SLL l1, SLL l2){ // returning the head of merged list
        Node head1 = l1.head;
        Node head2 = l2.head;
        Node mainHead = (head1.val < head2.val) ? head1 : head2;
        Node temp = mainHead;
        Node node = mainHead.next;
        Node node2 = (mainHead == head1) ? head2 : head1;
        while (node!=null && node2!=null){
            if(node.val <= node2.val){
                temp.next = node;
                node=node.next;
                temp=temp.next;
            }else {
                temp.next = node2;
                node2=node2.next;
                temp=temp.next;
            }
        }
        while (node != null){
            temp.next = node;
            node=node.next;
            temp=temp.next;
        }
        while (node2 != null){
            temp.next = node2;
            node2=node2.next;
            temp=temp.next;
        }
        return mainHead;
    }

    public void insertCLL(int val){
        Node node = new Node(val);
        if(head == null){
            head = node;
            tail = node;
            tail.next= head;
            return;
        }
        tail.next = node;
        node.next = head;
        tail = node;
    }

    public void displayCLL(){
        Node node = head;
        if(node == null){
            return;
        }
        do{
            System.out.print(node.val + " -> ");
            node = node.next;
        }while (node != head);
        System.out.println(" END ");
    }

    public static boolean detectCycle(Node head){
        Node f = head;
        Node s = head;
        while (f!=null && f.next!=null){
            f=f.next.next;
            s=s.next;
            if(f==s){
                return true;
            }
        }
        return false;
    }

    public static int detectCycleReturnLength(Node head){
        Node f = head;
        Node s = head;
        while (f!=null && f.next!=null){
            f=f.next.next;
            s=s.next;
            if(f==s){
                int length=0;
                do{
                    s=s.next;
                    length++;
                }while (s!=f);
                return length;
            }
        }
        return 0;
    }

    // LOGIC : consider length of LL is 8, Length of cycle is 5, so the cycle is starting from 4th node we know that
    // so 8-5 is 3 then 3+1 i.e. 4th is the starting node, this formula applies for all types of combination
    public static Node startNodeOfCycle(Node head){ // return start node of a cycle in LL
        Node f = head;
        Node s = head;
        while (f!=null && f.next!=null){
            f=f.next.next;
            s=s.next;
            if(f==s){
                Node n = head;
                while (n != s){
                    n=n.next;
                    s=s.next;
                }
                return n;
            }
        }
        return null; // if cycle is not present
    }

    public Node returnMidNode(){
        Node f = head;
        Node s = head;
        while (f!=null && f.next !=null){
            f=f.next.next;
            s=s.next;
        }
        return s;
    }

    public Node getNode(int index){
        Node node = head;
        for (int i = 0; i < index; i++) {
            node=node.next;
        }
        return node;
    }

    public void bubbleSort(){
        bubble(size-1,0);
    }
    private void bubble(int row, int col){
        if(row == 0){
            return;
        }
        if(col < row){
            Node first = getNode(col);
            Node second = getNode(col+1);
            if(first.val > second.val){
                if(first == head){
                    head = second;
                    first.next = second.next;
                    second.next = first;
                }
                else if(second == tail){
                    tail = first;
                    Node prev = getNode(col-1);
                    first.next = second.next;
                    second.next = first;
                    prev.next = second;
                }
                else {
                    Node prev = getNode(col-1);
                    first.next = second.next;
                    second.next= first;
                    prev.next = second;
                }

            }
            bubble(row, col+1);
        }else {
            bubble(row-1,0);
        }
    }

    public Node reverseRecursion(Node head){
        if(head == null || head.next == null){ // if we are at last node just return it
            SLL.head = head; // make it head
            return head;
        }
        Node node = reverseRecursion(head.next);
        node.next = head; // returned node i.e. next node will point to current node
        tail = head; // make current node , tail
        tail.next = null; // current node is pointing to next node , so point it to null
        return head;
    }

    public void reverseLL(){
        if(size < 2){
            return;
        }
        Node prev = null;
        Node current = head;
        Node next = current.next;
        while (current!=null){
            current.next = prev;
            prev = current;
            current = next;
            if(next != null){
                next = next.next;
            }
        }
        head = prev;
    }

    public void reverseSubPart(int leftIndex, int rightIndex){
        Node prev = null;
        Node current = head;
        for (int i = 0; i < leftIndex; i++) {
            prev = current;
            current = current.next;
        }
        Node previous = prev;
        Node newEnd = current;
        Node next = current.next;
        while (leftIndex <= rightIndex && current != null){
            current.next = previous;
            previous = current;
            current = next;
            if(next != null){ // if current is at last node the n next will be null and next.next will produce an error
                next = next.next;
            }
            leftIndex++;
        }
        if(prev != null){
            prev.next = previous;
        }else { // if head == null then we are reversing array from the head then we need to update the head
            head = previous;
        }
        newEnd.next = current;
    }

    public boolean isPalindrome(){
        if(head == null){
            return false;
        }
        int midIndex = size/2;
        reverseSubPart(midIndex,size-1);
        Node midNode = getNode(midIndex);
        Node start = head;
        Node mid = midNode;
        while (start != midNode && mid != null){
            if(start.val != mid.val){
                break;
            }
            start = start.next;
            mid = mid.next;
        }
        reverseSubPart(midIndex, size-1);

        return start == midNode || mid == null;
    }

    public Node getMid(Node head) {
        Node f = head;
        Node s = head;
        while (f!=null && f.next!=null){
            f=f.next.next;
            s=s.next;
        }
        return s;
    }

    public void rearrange(){
        if(head == null || head.next == null){
            return;
        }
        reverseSubPart(size/2,size-1);
        Node midNode = getNode(size/2);
        Node start = head;
        Node mid = midNode;
        while (start != midNode && mid != null){
            Node next = start.next;
            start.next = mid;
            start =  next;
            next = mid.next;
            if(start != midNode){ // if the size of LL is odd then we don't want to link midNode so it will be skipped
                mid.next = start; // last node will always point to null because after reversing last will be pointing
            } // to null, as we are first adding left element then right element so last node is the right one
            mid = next;
        }

    }

}

public class LinkedListLeetCode {
    // sum the squares of each digit of given no. and keep doing this with the obtained sum
    // if u get 1 then its a happy no.
    // if it's not a happy no. u will never get 1 and it will run endlessly in a loop
    // 19 is a happy no. :- 1^2 + 9^2 =  82 -> 8^2 + 2^2 = 68 -> 6^2 + 8^2 = 100 -> 1^2 = 1 , it's a happy no.

    static boolean isHappy(int n){
        int f = n;
        int s = n;
        do{
            f = sumOfSquareOfEachDigit(sumOfSquareOfEachDigit(f));
            s = sumOfSquareOfEachDigit(s);
        }
        while (f!=s);
        if(f==1){
            return true;
        }
        return false;
    }

    static int sumOfSquareOfEachDigit(int n){
        int sumSquare =0 ;
        while (n>0){
            int rem= n%10;
            sumSquare += rem*rem;
            n=n/10;
        }
        return sumSquare;
    }


    public static void main(String[] args) {
        SLL ll = new SLL();
        ll.insertAtEnd(1);
        ll.insertAtEnd(2);
        ll.insertAtEnd(3);
        ll.insertAtEnd(3);
        ll.insertAtEnd(2);
        ll.insertAtEnd(1);
        ll.display();
//        ll.reverseSubPart(1,5);
//        System.out.println(ll.getMid(SLL.head).val);
//        System.out.println(ll.isPalindrome());
        ll.rearrange();
        ll.display();

//        System.out.println(isHappy(19));

//        SLL ll = new SLL();
//        ll.insertCLL(1);
//        ll.insertCLL(2);
//        ll.insertAtStart(1);
//        ll.insertAtStart(2);
//        ll.insertAtStart(3);
//        ll.insertAtStart(4);
//        ll.insertAtStart(5);
//        ll.insertAtStart(6);
//        ll.display();
//        ll.bubbleSort();
//        ll.reverseRecursion(SLL.head);
//        ll.reverseLL();
//        ll.display();
//        System.out.println((ll.getNode(0,SLL.head).val));
//        System.out.println((ll.returnMidNode().val));
//        System.out.println((SLL.startNodeOfCycle(ll.head)).val);
//        System.out.println(SLL.detectCycleReturnLength(ll.head));
//        System.out.println(SLL.detectCycle(ll.head));

//        SLL l1 = new SLL();
//        SLL l2 = new SLL();
//        l1.insertAtEnd(1);
//        l1.insertAtEnd(2);
//        l1.insertAtEnd(3);
//        l1.display();
//
//        l2.insertAtEnd(1);
//        l2.insertAtEnd(4);
//        l2.insertAtEnd(5);
//        l2.insertAtEnd(6);
//        l2.display();
//
//        SLL.Node head = SLL.mergeTwoSortedList(l1,l2);
//        SLL.display(head);


//        SLL ll = new SLL();
//        ll.insertRecursion(11,0);
//        ll.insertRecursion(11,1);
//        ll.insertRecursion(22,2);
//        ll.insertRecursion(22,3);
//        ll.insertRecursion(22,4);
//        ll.insertRecursion(33,5);
//        ll.insertRecursion(33,6);
//        ll.display();
////        ll.removeDuplicate();
//        ll.removeDuplicate2();
//        ll.display();
    }
}
