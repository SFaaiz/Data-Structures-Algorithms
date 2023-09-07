package dsaPractice;

class Singly{
    private class Node{
        int val;
        Node next;

        Node(int val){
            this.val = val;
        }
    }
    Node head, tail;
    public void insertFirst(int val){
        Node node = new Node(val);
        node.next = head;
        head= node;
        if(tail==null){
            tail = node;
        }
    }
    public void insertLast(int val){
        Node node = new Node(val);
        if(tail == null){
            tail = node;
            head = node;
            return;
        }
        tail.next = node;
        tail = node;

    }
    public void insert(int val, int after){ // at particular pos
        Node nodePrev= findNode(after);
        if(nodePrev==null){
            System.out.println("Node not found");
            return;
        }
        Node node = new Node(val);
        node.next = nodePrev.next;
        if(nodePrev.next == null){ // if the value after which we are inserting is the last one then we must maintain
            tail = node;                                             // the tail
        }
        nodePrev.next = node;
    }
    private Node findNode(int val){
        Node node = head;
        while (node.val != val && node != null){
            node = node.next;
        }
        return node;
    }
    public void deleteStart(){
        if(head==null){
            System.out.println("No nodes available");
            return;
        }
        head = head.next;
    }
    public void deleteEnd(){
        if(head==null){
            System.out.println("No nodes available");
            return;
        }
        Node prev = head;
        Node node = head.next;
        while (node.next != null){
            node=node.next;
            prev=prev.next;
        }
        prev.next = null;
        tail = prev;
    }
    public void deleteParticular(int val){
        Node node = findNode(val);
        if(node==null){
            System.out.println("Node not found");
            return;
        }
        Node prev = head;
        while (prev.next!=node){
            prev=prev.next;
        }
        if(node.next == null){ // if the node to be deleted is the last one
            tail = prev;
        }
        prev.next = node.next;
    }
    public void display(){
        Node node = head;
        while (node!=null){
            System.out.print(node.val + " -> ") ;
            node=node.next;
        }
        System.out.println("END");
    }

    public void displayFrom(Node head){
        Node node = head;
        while (node!=null){
            System.out.print(node.val + " -> ") ;
            node=node.next;
        }
        System.out.println("END");
    }


    public int reverse(int n){
        int rev=0;
        while(n>0){
            int rem = n%10;
            rev = (rev*10) + rem;
            n=n/10;
        }
        return rev;
    }
    public Node insert(int ans){
        if(ans%10 == ans){
            Node node1 = new Node(ans);
            return node1;
        }
        Node node = new Node(ans%10);
        node.next = insert(ans/10);
        return node;

    }
    public Node addTwoNumbers(Node l1, Node l2) {
        int n1 = 0;
        int n2=0;
        Node node1 = l1;
        Node node2 = l2;
        while(node1!=null){
            n1= (n1*10) + node1.val;
            node1=node1.next;
        }
        while(node2!=null){
            n2= (n2*10) + node2.val;
            node2=node2.next;
        }
        int rev1 = reverse(n1);
        int rev2 = reverse(n2);
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(rev1);
        System.out.println(rev2);
        int ans = rev1 + rev2;

        Node node = insert(ans);
        return node;


    }
}

class Doubly{
    private class Node{
        int val;
        Node next;
        Node prev;
        Node(int val){
            this.val = val;
        }
    }
    Node head, tail;
    public void insertFirst(int val){
        Node node = new Node(val);
        node.next = head;
        head = node;
        if(node.next != null){
            node.next.prev = node;
        }
        if(tail == null){
            tail = node;
        }
    }

    public void insertLast(int val){
        Node node = new Node(val);
        if(tail==null){
            tail = node;
            head = node;
            return;
        }
        tail.next = node;
        node.prev = tail;
        tail = node;
    }

    public void insertParticular(int val, int after){
        Node prev = findNode(after);
        if(prev == null){
            System.out.println("Node not found");
            return;
        }
        Node node = new Node(val);
        node.next = prev.next;
        if(prev.next != null){
            prev.next.prev = node;
        }
        if(prev.next == null){
            tail = node;
        }
        prev.next = node;
        node.prev = prev;
    }

    public Node findNode(int val){
        Node node = head;
        while (node.val!=val && node!=null){
            node=node.next;
        }
        return node;
    }

    public void deleteStart(){
        if(head==null){
            System.out.println("No nodes available");
            return;
        }
        if(head.next == null){
            tail = null;
        }
        head = head.next;
        if(head!=null){
            head.prev = null;
        }
    }

    public void deleteEnd(){
        if(head==null){
            System.out.println("No nodes available");
            return;
        }
        if(head.next == null){
            tail = null;
            head = head.next;
            return;
        }
        Node prev = head;
        Node node = head.next;
        while (node.next != null){
            prev=prev.next;
            node=node.next;
        }
        prev.next = null;
        tail = prev;
    }

    public void deleteParticular(int val){
        Node node = findNode(val);
        if(node == null){
            System.out.println("Node not found");
            return;
        }
        if(head.next == null || node.prev==null){ // if node to be deleted is the only one or first one
            deleteStart();
            return;
        }
        Node prev = head;
        while (prev.next != node){
            prev=prev.next;
        }
        prev.next = node.next;
        if(node.next == null){ // if the node is the last one then maintain the tail
            tail = prev;
        }else {
            node.next.prev = prev;
        }

    }



        public void display(){
            Node node = head;
            while (node!=null){
                System.out.print(node.val + " -> ") ;
                node=node.next;
            }
            System.out.println("END");
        }

    public void displayReverse(){
        Node node = tail;
        while (node!=null){
            System.out.print(node.val + " -> ") ;
            node=node.prev;
        }
        System.out.println("START");
    }

}

class Circular{
    private class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
        }
    }
    Node head, tail;

    public void insertFirst(int val){
        Node node = new Node(val);
        node.next = head;
        head = node;
        if(head.next == null){
            tail = node;
        }
        tail.next = head;
    }

    public void insertLast(int val){
        Node node = new Node(val);
        node.next = head;
        if(tail!=null){
            tail.next = node;
        }
        tail = node;
        if(head == null){
            head = node;
        }
        tail.next = head;
    }

    // there is a problem with this delete method that it does not delete if there is only one node remaining
    public void delete(){ // deletes last node
        if(head == null || head.next==null){
            head=null;
            tail=null;
            return;
        }
        Node node = head;

        while (node.next != tail){
            node=node.next;
        }
        node.next = head;
        tail = node;
    }

    public void display(){
        Node node = head;
        if(node == null){
            System.out.println("No nodes available");
            return;
        }
        do {
            System.out.print(node.val + " -> ");
            node=node.next;
        }while (node != head);
        System.out.println("END");
    }
}

public class LinkedList {
    public static void main(String[] args) {
        Singly l1 = new Singly();
        Singly l2 = new Singly();
        l1.insertFirst(9);
        l2.insertFirst(9);
        l2.insertFirst(9);
        l2.insertFirst(9);
        l2.insertFirst(9);
        l2.insertFirst(9);
        l2.insertFirst(9);
        l2.insertFirst(9);
        l2.insertFirst(9);
        l2.insertFirst(9);
        l2.insertFirst(1);
        l1.display();
        l2.display();
        l1.displayFrom(l1.addTwoNumbers(l1.head,l2.head));


//        Circular ll = new Circular();
//        ll.insertFirst(1);
//        ll.insertFirst(2);
//        ll.insertFirst(3);
//        ll.insertLast(4);
//        ll.insertFirst(1);
//        ll.display();
//        ll.delete();
//        ll.delete();
//        ll.delete();
//        ll.delete();
//        ll.delete();
//        ll.delete();
//        ll.insertFirst(1);
//        ll.display();
//        ll.delete();
//        ll.display();

//        Doubly ll = new Doubly();
//        ll.insertLast(0);
//        ll.insertFirst(1);
//        ll.insertFirst(2);
//        ll.display();
//        ll.displayReverse();
//        ll.insertLast(3);
//        ll.insertParticular(4,3);
//        ll.insertParticular(6,1);
//        ll.deleteStart();
//        ll.deleteEnd();
//        ll.deleteEnd();
//        ll.deleteParticular(1);
//        ll.display();
//        ll.displayReverse();
//        ll.deleteParticular(2);
//        ll.display();
//        ll.displayReverse();
//        ll.deleteParticular(0);
//        ll.display();
//        ll.displayReverse();

//        Singly ll = new Singly();
//        ll.insertFirst(1);
//        ll.insertFirst(12);
//        ll.insertFirst(3);
//        ll.insertLast(4);
//        ll.insert(7,12);
//        ll.insert(9,4);
//        ll.insertLast(99);
//        ll.display();
//        ll.deleteStart();
//        ll.deleteEnd();
//        ll.display();
//        ll.deleteParticular(1);
//        ll.deleteParticular(4);
//        ll.display();

    }
}
