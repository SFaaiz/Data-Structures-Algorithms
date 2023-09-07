package fullConceptInOneFile;

class Singly{
     Node head;
    private Node tail;
    private int size;

    public Singly(){
        this.size = 0;
    }
     class Node{
        private int val;
        private Node next;
        public Node(){

        }

        public Node(int val){
            this.val = val;
        }
        public Node(int val, Node next){
            this.val = val;
            this.next = next;
        }
    }
    public void insertFirst(int val){
        Node node = new Node(val);
        node.next = head;
        head = node;
        if(tail == null){
            tail = node;
        }
        size++;
    }
    public void insertLast(int val){
        Node node = new Node(val);
        if(tail == null){
            head= node;
            tail=node;
            return;
        }
        tail.next = node;
        tail = node;
        size++;
    }
    public void insertRecursion(int val, int index){
        head = insertRec(val, index, head); // head will point to the first node that will be returned after all function calls
    }
    private Node insertRec(int val, int index, Node node){
        if(index == 0){
            Node temp = new Node(val, node); // new node's next will point to the current's index node
            size++;
            return temp; // previous node will point to the newly inserted node
        }
        node.next = insertRec(val, index-1,node.next);
        return node;
    }
    // remove Duplicates from a sorted linked list
    public void removeDuplicates(){
        if(head == null || head.next == null){
            return;
        }
        Node node = head;
        while (node.next != null){
            if(node.val == node.next.val){
                node.next = node.next.next;
                node = node.next;
                size--;
            }else {
                node = node.next;
            }
        }
        tail = node; // maintain the tail
    }

//    if(list1 == null){
//        return list2;
//    }
//        if(list2 == null){
//        return list1;
//    }
    public Singly mergeTwoSortedList(Singly list1, Singly list2){ // taking head of 2 diff lists and returning head of merged list
        Node f = list1.head;
        Node s = list2.head;

        Singly merged = new Singly();
        while (f!=null && s!=null){
            if(f.val < s.val){
                merged.insertLast(f.val);
                f = f.next;
            }else {
                merged.insertLast(s.val);
                s=s.next;
            }
        }
        while (f!=null){
            merged.insertLast(f.val);
            f=f.next;
        }
        while (s!=null){
            merged.insertLast(s.val);
            s=s.next;
        }
        return merged;
    }
    private Node getNode(int index){
        if(index <0){
            return null;
        }
        Node node = head;
        while (index != 0){
            node = node.next;
            index--;
        }
        return node;
    }

    // return middle node of the list
    public Node getMid(Node head){
        Node s = head;
        Node f =  head;
        while (f!=null && f.next!=null ){
            s=s.next;
            f=f.next.next;
        }
        return s;
    }
//    public int getMidVal(){
//        Node mid = getMid();
//        return mid.val;
//    }

    public void bubbleSort(){
        bubbleSortHelper(size-1, 0);
    }
    private void bubbleSortHelper(int row, int col){ // row = LL.length-1 , col = starts from 0
        if(row == 0){ // if only one element is left to sort
            return;
        }
        if(col<row){
            Node first = getNode(col);
            Node second = getNode(col+1);
            if(first.val > second.val){ // if f > s , swap
                if(first == head){ // to maintain head
                    head = second;
                    first.next = second.next;
                    second.next = first;
                } else if (second == tail) { // to maintain the tail at the end
                    Node prev= getNode(col-1);
                    prev.next = second;
                    first.next = second.next;
                    second.next = first;
                    tail = first;
                }else {
                    Node prev= getNode(col-1);
                    prev.next = second;
                    first.next = second.next;
                    second.next = first;
                }
            }
            bubbleSortHelper(row,col+1); // j++
        }
        else {
            bubbleSortHelper(row-1,0); // length-i-1
        }
    }

    public Node reverse(Node head){
        if(head==null){
            return head;
        }
        Node prev = null;
        Node current = head;
        Node next = current.next;
//        tail = head;

        while (current!=null){
            current.next = prev;
            prev = current;
            current = next;
            if(next!=null){
                next=next.next;
            }
        }
//        head = prev;
        return prev;
    }
    public void recurReverse(){
        recurReverseHelper(head);
    }
    private void recurReverseHelper(Node node){
        if (node == tail){
            head=tail;
            return;
        }
        recurReverseHelper(node.next);
        tail.next=node;
        tail=node;
        tail.next=null;
    }

    public void reverseSubPart(int startIndex, int endIndex){
        if(startIndex >= endIndex){
            return;
        }
        Node prev = getNode(startIndex-1);
        Node current = getNode(startIndex);
        Node next = getNode(startIndex+1);

        Node last = prev;
        Node subLast = current;

        while (startIndex<=endIndex){
            current.next = prev;
            prev = current;
            current = next;
            if(next!=null){
                next=next.next;
            }
            startIndex++;
        }
        if(last!=null){ // last is basically the previous node of startIndex, if startIndex is 0 then last is null -1
            last.next=prev;   // if last is null then this will give error
        }else {
            head = prev; // if startIndex is 0 it means last is null, then prev is last of subPart before reversed
        }                                       // after reversing it will be the first node so it should be the head
            subLast.next=current; // after reversing startIndex will be lastIndex of subPart,
                                  // so it should point to next of lastIndex which was there before reversing
    }

    public boolean isPalindrome(){
        Node mid = getMid(head);
        Node reverseHead = reverse(mid);
        Node rereverseHead = reverseHead;
        // check isPal
        while (head!=null && reverseHead!=null){
            if(head.val != reverseHead.val){
                break;
            }
            head=head.next;
            reverseHead=reverseHead.next;
        }
        reverse(rereverseHead);
        if(head==null || reverseHead==null){
            return true;
        }
        return false;
    }

    public void reorderList(){
        Node mid = getMid(head);
        Node hs = reverse(mid);
        Node hf = head;
        while (hf!=null && hs!=null){
            Node temp= hf.next;
            hf.next = hs;
            hf = temp;
            temp = hs.next;
            hs.next=hf;
            hs = temp;
        }
        if(hf!=null){
            hf.next=null;
        }
    }

    public Node merge(Node head){
        if(head == null || head.next==null){
            return head;
        }
        Node mid = getMiddle(head);

        Node left = merge(head);
        Node right = merge(mid);

        return mergeSort(left, right);
    }

    private Node mergeSort(Node left, Node right) {
        Node dummyHead = new Node();
        Node tail= dummyHead;
        while (left!=null && right!=null){
            if(left.val < right.val){
                tail.next = left;
                left = left.next;
                tail = tail.next;
            }else {
                tail.next = right;
                right = right.next;
                tail=tail.next;
            }
        }
        tail.next= (left != null) ? left : right;
        return dummyHead.next;
    }

    private Node getMiddle(Node head){
        Node midPrev = null;
        while (head!=null && head.next !=null){
            midPrev = (midPrev == null) ? head : midPrev.next;
            head=head.next.next;
        }
        Node mid = midPrev.next;
        midPrev.next=null;
        return mid;
    }

    public Node reverseKGroup(Node head, int k){
        if(k<=1 || head==null){
            return head;
        }
        Node current = head;
        Node prev = null;
        while (true){
            Node last = prev;
            Node newEnd = current;

            Node next = current.next;
            for (int i = 0;current!=null && i < k; i++) {
                current.next=prev;
                prev=current;
                current=next;
                if(next!=null){
                    next=next.next;
                }
            }
            if(last!=null){
                last.next=prev;
            }else {
                head=prev;
            }
            newEnd.next=current;
            if(current==null){
                break;
            }
        }
        return head;
    }

    public void rotateKnodes(int k){
        if(head==null || head.next==null || k==0){
            return;
        }
        Node node = head;
        int len = 1;
        while (node.next!=null){
            node=node.next;
            len++;
        }
        node.next=head;
        int rotations = k % len;
        int skip = len - rotations;
        Node newLast = head;
        for (int i = 1; i < skip; i++) {
            newLast=newLast.next;
        }
        head = newLast.next;
        newLast.next=null;

//        return head;
    }

    public void display(){
        Node node = head;
        while (node != null){
            System.out.print(node.val + " -> ");
            node=node.next;
        }
        System.out.println("END");
    }
}

public class LinkedListLeetCodeQuestions {
    // sum the squares of each digit of given no. and keep doing this with the obtained sum
    // if u get 1 then its a happy no.
    // if it's not a happy no. u will never get 1 and it will run endlessly in a loop
    public static boolean isHappy(int n){
        int f = n; // fast and slow pointer method
        int s = n;
        do{
            f = sumSquare(sumSquare(f));
            System.out.print(f + " ");
            s =sumSquare(s);
            System.out.println(s);
        } while (f != s); // if it's a happy no. then both will become 1 at the end else other than one
        if(f == 1){
            return true;
        }
        return false;
    }
    private static int sumSquare(int n){
        int ans=0;
        while (n>0){
            int rem = n%10;
            ans+= rem*rem;
            n=n/10;
        }
        return ans;
    }
    public static void main(String[] args) {


        Singly list1 = new Singly();
        list1.insertFirst(1);
        list1.insertLast(2);
        list1.insertLast(3);
        list1.insertLast(31);
        list1.insertLast(21);
        list1.insertLast(11);
        list1.display();
//        list1.bubbleSort();
//        list1.reverse();
//        list1.recurReverse();
//        list1.reverseSubPart(0,5);
//        System.out.println(list1.isPalindrome());
//        list1.reorderList();
        list1.merge(list1.head);
//        list1.reverseKGroup(list1.head, 2);
//        list1.rotateKnodes(7); // 6 rotations will give original list + 1 wil rotate 1 node
        list1.display();

//        System.out.println(list1.getMid());
//        System.out.println(list1.getMidVal());

//        System.out.println(isHappy(9));

//        Singly list1 = new Singly();
//        list1.insertFirst(1);
//        list1.insertLast(3);
//        list1.insertLast(4);
//        list1.insertLast(5);
//        list1.display();
//        Singly list2 = new Singly();
//        list2.insertFirst(1);
//        list2.insertLast(4);
//        list2.insertLast(7);
//        list2.display();
//
//        Singly ans = new Singly();
//        ans = ans.mergeTwoSortedList(list1,list2);
//        ans.display();

//        Singly list = new Singly();
//        list.insertFirst(1);
//        list.insertLast(1);
//        list.insertLast(3);
//        list.insertLast(3);
//        list.insertLast(5);
//        list.insertLast(6);
//        list.display();
////        list.insertRecursion(6,4 );
//        list.removeDuplicates();
//        list.display();

//        Circular lst = new Circular();
//        lst.insertLastCll(1);
//        lst.insertLastCll(2);
//        lst.insertLastCll(3);
//        lst.insertLastCll(4);
//        lst.displayCyclic(lst.insertLastNextPointsTo(5,4)); // can't point to itself before inserting
//        System.out.println(lst.hasCycle());
//        System.out.println(lst.lengthCycle());
//        System.out.println(lst.cycleStart());
//        System.out.println(lst.cycleStartValue());
//        lst.displayCll();
    }
}

class Circular{
    private Node head;
    private Node tail;
    private int size;
    public Circular(){
        this.size = 0; // whenever the class is instantiated the size resets to zero, if multiple objects are created
    }                                      // then size will be of previous LL, for new LL also, so resetting is needed
    private class Node{
        private int val;
        private Node next;
        public Node(int val){
            this.val = val;
        }
        public Node(int val, Node next){
            this.val = val;
            this.next = next;
        }
    }


    // simple formula : ( total LL length - cycle length = start index of cycle)
    public Node cycleStart(){
        int length = lengthCycle();
        if(length == 0){
            return null;
        }
        Node f = head;
        Node s = head;
        while (length>0){
            s=s.next;
            length--;
        }
        while (f!=s){
            f=f.next;
            s=s.next;
        }
        return s;
    }
    public int cycleStartValue(){
        Node node = cycleStart();
        if(node == null){
            return -1;
        }
        return node.val;
    }
    public int lengthCycle(){
        Node fast = head;
        Node slow = head;
        int length = 0;
        while (fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if (slow==fast){
                do{
                    slow = slow.next;
                    length++;
                }while (slow!=fast);
                return length;
            }
        }
        return 0;
    }

    // detect if a LL has cycle or not
    public boolean hasCycle(){
        Node fast = head;
        Node slow = head;

        while (fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if (slow==fast){
                return true;
            }
        }
        return false;
    }
    public void insertLastCll(int val){
        Node node = new Node(val);
        node.next = head;
        if(head == null){
            head = node;
        }
        if(tail != null){
            tail.next = node;
        }
        tail = node;
        tail.next = head;
        size++;
    }
    //if next node index provided is null then it will point to head
    public Node insertLastNextPointsTo(int val, int nextIndex){
        Node getIndexNode= getNode(nextIndex);
        if(getIndexNode == null){
            insertLastCll(val);
            return head;
        }
        Node node = new Node(val, getIndexNode);
        tail.next = node;
        tail = node;
        size++;
        return getIndexNode;
    }
    public Node getNode(int index){
        Node node = head;
        while (index!=0 && node!=null){
            node=node.next;
            index--;
        }
        if(node==null){
            return null;
        }
        return node;
    }
    public void displayCll(){
        Node node = head;
        do{
            System.out.print(node.val + " -> ");
            node=node.next;
        }
        while (node != head);
        System.out.println("START");
    }
    public void displayCyclic(Node node){ // this is the node from where the cycle starts
        Node start = head;
        do{
            System.out.print(start.val + " -> ");
            start=start.next;
        }
        while (start != node);
        if(head == node){
            System.out.println(node);
            return;
        }
        do{
            System.out.print(start.val + " -> ");
            start=start.next;
        }
        while (start != node);
        System.out.println(node);
    }
}