package fullConceptInOneFile;

// node, head, tail etc is private so that it cannot be accessed directly from outside
// node == null , means a node is not initialised
// node.next == null , means the next part of the node is pointing to a null value

class SLL{
    private class Node{
        private int val;
        private Node next;

        public Node(int value){ // default constructor, a node should not be created without a value in it
            this.val = value;
        }
    }
    private Node head;
    private Node tail;

    public void insertAtStart(int val){
        Node node = new Node(val);
        node.next = head;
        head = node;
        if(head.next == null){
            tail = node;
        }
    }
    public void insertAtEnd(int val){
        if(head == null){
            insertAtStart(val);
            return;
        }
        Node node = new Node(val);
        tail.next = node;
        tail = node;
    }
    public void insertAtParticularPos(int after, int val){
        Node prev = findNode(after);
        if(prev == null){
            System.out.println("Sorry, Node not found");
            return;
        }
        if(prev.next == null){
            insertAtEnd(val);
            return;
        }
        Node node = new Node(val);
        node.next = prev.next;
        prev.next = node;
    }
    public Node findNode(int value){
        Node node = head;
        while (node != null){
            if(node.val == value){
                return node;
            }
            node = node.next;
        }
        return null;
    }
    public int deleteAtStart(){
        if(head == null){
            return -1;
        }
        int val = head.val;
        head = head.next;
        if(head == null){
            tail=null;
        }
        return val;
    }
    public int deleteAtEnd(){
        if(tail == null){
            return -1;
        }
        if(head.next == null){
            return deleteAtStart();
        }
        int val = tail.val;
        Node node = head;
        Node node2 = head.next;
        while (node2.next != null){
            node2 = node2.next;
            node = node.next;
        }
        node.next = null;
        tail = node;
        return val;
    }
    public int deleteAtParticularPos(int val){
        Node node = findNode(val);
        if (node==null){
            return -1;
        }
        if(node.next == null){
            return deleteAtEnd();
        }
        int value = node.val;
        Node prev1 = head;
        Node prev = head.next;
        while (prev.val != node.val){
            prev = prev.next;
            prev1 = prev1.next;
        }
        prev1.next = node.next;
        return value;
    }

    public void display(){
        Node node = head;
        while (node != null){
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println("END");
    }

}

class DLL{
    private class Node{
        int val;
        Node next;
        Node prev;

        public Node(int val){
            this.val = val;
        }
    }
    private Node head;

    public void insertAtStart(int val){
        Node node = new Node(val);
        node.next = head;
        node.prev=null;
        if(head != null){
            head.prev = node;
        }
        head = node;
    }
    public void insertAtEnd(int val){
        Node node = new Node(val);
        node.next=null;
        if(head == null){
            head = node;
            node.prev=null;
        }
        Node temp = head;
        while (temp.next != null){
            temp= temp.next;
        }
        temp.next = node;
        node.prev = temp;
    }
    public void insertAtParticularPos(int after, int val){
        Node prev = findNode(after);
        if(prev == null){
            System.out.println("Node does not exist");
            return;
        }
        Node node = new Node(val);
        node.next = prev.next;
        if(prev.next != null){
            prev.next.prev = node;
        }
        prev.next = node;
        node.prev = prev;
    }
    public Node findNode(int val){
        Node node = head;
        while (node != null){
            if(node.val == val){
                return node;
            }
            node = node.next;
        }
        return null;
    }
    public void delete(int val){
        Node node = findNode(val);
        if(node == null){
            System.out.println("Node does not exist");
            return;
        }
        if(node.prev != null){
            node.prev.next = node.next;
        }
        if(node.prev == null){
            node.next.prev = null;
            head= node.next;
        }
        if(node.next != null){
            node.next.prev = node.prev;
        }
    }

    public void display(){
        Node node= head;
        while (node != null){
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println("END");
    }
}

class CLL{
    private Node head;
    private Node tail;
    private class Node{
        private int val;
        private Node next;
        public Node(int val){
            this.val = val;
        }
    }
    // Insertion at start and End is same in CLL
    public void insert(int val){
        Node node = new Node(val);
        node.next = head;
        head = node;
        if(head.next == null){
            tail = node;
        }
        tail.next = head;
    }
    public void delete(int val){
        Node node = head;
        if(node.val == val){
            head = head.next;
            tail.next = head;
            return;
        }
        do{
            Node n = node.next;
            if(n.val == val){
                node.next = n.next;
                return ;
            }
            node = node.next;
        } while (node != head);
    }

    public Node searchNode(int val){
        Node node = head;
        do{
            if(node.val == val){
                return node;
            }
            node = node.next;
        } while (node != head);
        return null;
    }
    public void display(){
        Node node = head;
        if(node == null){
            System.out.println("No nodes available");
            return;
        }
        System.out.print(node.val + " -> ");
        node = node.next;
        while (node != head){
            System.out.print(node.val + " -> ");
            node = node.next;
        }
        System.out.println("END");
    }

    // traverse from a particular position
    public void displayFrom(int start){
        Node node = searchNode(start);
        if(node == null){
            System.out.println("Node does not exist");
            return;
        }
        do{
            System.out.print(node.val + " -> ");
            node= node.next;
        }while (node.val != start);
        System.out.println("END");
    }

}

public class linkedListInOneFile {
    public static void main(String[] args) {
        CLL circular = new CLL();
//        circular.insert(1);
//        circular.insert(2);
//        circular.insert(3);
        circular.insert(4);
        circular.display();
//        circular.displayFrom(2);
        circular.delete(4);
        circular.display();


//        DLL doubly = new DLL();
//        doubly.insertAtStart(1);
//        doubly.insertAtStart(2);
//        doubly.insertAtStart(3);
//        doubly.insertAtStart(4);
//        doubly.insertAtEnd(5);
//        doubly.insertAtParticularPos(2,7);
//        doubly.display();
//        doubly.delete(4);
//        doubly.display();



//        SLL singly = new SLL();
//        singly.insertAtStart(1);
//        singly.insertAtStart(2);
//        singly.insertAtEnd(3);
//        singly.insertAtParticularPos(2,9);
//        singly.display();
//        System.out.println(singly.deleteAtStart());
//        singly.display();
//        System.out.println(singly.deleteAtParticularPos(1));
//        singly.display();
//        System.out.println(singly.deleteAtEnd());
//        singly.display();
    }
}
