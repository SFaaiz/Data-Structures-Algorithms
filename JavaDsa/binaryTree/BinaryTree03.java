package binaryTree;

import java.util.*;

class BinaryTree2 {
    public static class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    Node root;
    int index = -1;

    public Node buildTree(int[] nodes) { // method is using preOrder traversal
        index++;
        if (nodes[index] == -1) {
            return null;
        }
        Node node = new Node(nodes[index]);
        node.left = buildTree(nodes);
        node.right = buildTree(nodes);
        root = node;
        return node;
    }

    public void levelOrder(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while (!q.isEmpty()) {
            Node node = q.remove();
            if (node == null) {
                System.out.println();
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                System.out.print(node.val + " ");
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
        }
    }

    // count no. of nodes in less than O(n) time in a complete binary tree (only last level is incomplete from right side)
    private int leftHeight(Node root){
        if (root==null){
            return 0;
        }
        return leftHeight(root.left) + 1;
    }
    private int rightHeight(Node root){
        if (root==null){
            return 0;
        }
        return rightHeight(root.right) + 1;
    }
    public int countNodes(Node root){
        if(root==null){
            return 0;
        }
        int leftHeight = countNodes(root.left);
        int rightHeight = countNodes(root.right);
        if(leftHeight==rightHeight) return (2<<leftHeight)-1;
        return countNodes(root.left) + countNodes(root.right)+1;
    }

    // construct a binary tree using inorder and preorder
    public Node constructBT(int[] inorder, int[]preorder){
        Map<Integer,Integer> inorderMap = new HashMap<>();
//        .. place all the inorder values with it's corresponding index
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return buildTree(inorder,0,inorder.length-1,preorder,0,preorder.length-1,inorderMap);
    }

    private Node buildTree(int[]inorder, int inStart, int inEnd, int[]preorder, int preStart, int preEnd, Map<Integer,Integer> inorderMap){
        if(inStart>inEnd || preStart>preEnd) return null;
        Node node = new Node(preorder[preStart]);
        int indexInInorder = inorderMap.get(node.val);
        int noOfNodesInLeft = indexInInorder-inStart;
        node.left = buildTree(inorder,inStart,indexInInorder-1,preorder,preStart+1,preStart+noOfNodesInLeft,inorderMap);
        node.right = buildTree(inorder,indexInInorder+1,inEnd,preorder,preStart+noOfNodesInLeft+1,preEnd,inorderMap);
        return node;
    }

    // construct a binary tree using inorder and postorder
    public Node constructBT2(int[] inorder, int[]postorder){
        Map<Integer,Integer> inorderMap = new HashMap<>();
//        .. place all the inorder values with it's corresponding index
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return buildTree2(inorder,0,inorder.length-1,postorder,0,postorder.length-1,inorderMap);
    }

    private Node buildTree2(int[]inorder, int inStart, int inEnd, int[]postorder, int postStart, int postEnd, Map<Integer,Integer> inorderMap){
        if(inStart>inEnd || postStart>postEnd) return null;
        Node node = new Node(postorder[postEnd]);
        int indexInInorder = inorderMap.get(node.val);
        int noOfNodesInLeft = indexInInorder-inStart;
        node.left = buildTree2(inorder,inStart,indexInInorder-1,postorder,postStart,postStart+noOfNodesInLeft-1,inorderMap);
        node.right = buildTree2(inorder,indexInInorder+1,inEnd,postorder,postStart+noOfNodesInLeft,postEnd-1,inorderMap);
        return node;
    }

    // Serialize - root will be given to you convert the binary tree into a string and return it
    public String serialize(Node root){
        if(root==null)return "";
        Queue<Node> q = new LinkedList<>();
        StringBuilder s = new StringBuilder();
        q.add(root);
        while (!q.isEmpty()){
            Node node = q.poll();
            if(node==null){
                s.append("n ");
                continue;
            }
            s.append(node.val + " ");
            q.add(node.left);
            q.add(node.right);
        }
        return s.toString();
    }

    public Node deserialize(String s){
        if(s=="")return null;
        Queue<Node> q = new LinkedList<>();
        String[] values = s.split(" " );
        Node root = new Node(Integer.parseInt(values[0]));
        q.add(root);
        for (int i = 1; i < values.length; i++) {
            Node node = q.poll();
            if(!values[i].equals("n")){
                Node left = new Node(Integer.parseInt(values[i]));
                node.left = left;
                q.add(left);
            }
            if(!values[++i].equals("n")){
                Node right = new Node(Integer.parseInt(values[i]));
                node.right = right;
                q.add(right);
            }
        }
        return root;

    }

    // morris traversal - it.s speciality is it takes O(1) space (constant space)
    // here we connect rightmost leaf node of each child to current node
    public List<Integer> inorderTraversal(Node root) {
        List<Integer> inorder = new ArrayList<Integer>();

        Node cur = root;
        while(cur != null) {
            if(cur.left == null) {
                inorder.add(cur.val);
                cur = cur.right;
            }
            else {
                Node prev = cur.left;
                while(prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }

                if(prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                }
                else {
                    prev.right = null;
                    inorder.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return inorder;
    }

    // same as above
    public List<Integer> morrisTraversal(Node root){ // inorderTraversal using morris traversal
        List<Integer > ans= new ArrayList<>();
        if(root == null) return ans;
        while (root!=null){
            if(root.left == null){
                ans.add(root.val);
                root=root.right;
            }
            else {
                Node link= root.left;
                while (link.right != null && link.right != root){
                    link=link.right;
                }
                if(link.right == null){
                    link.right = root;
                    root=root.left;
                }else {
                    link.right=null;
                    ans.add(root.val);
                    root=root.right;
                }
            }
        }
        return ans;
    }

    // flatten a binary tree to linkedList, don't create new node do it in itself
    // recursive approach, preorder traversal
    Node prev = null;
    public void BTtoLinkedList(Node root){ // prev will be null initially
        if(root==null) return;
        BTtoLinkedList(root.right);
        BTtoLinkedList(root.left);
        root.right = prev;
        root.left=null;
        prev = root;
    }

    // iterative approach + stack
    public void BTtoLinkedList2(Node root){
        Stack<Node> st = new Stack<>();
        st.push(root);
        while (!st.isEmpty()){
            Node cur = st.pop();
            if(cur.right!=null) st.push(cur.right);
            if(cur.left!=null) st.push(cur.left);
            if(!st.isEmpty()){
                cur.right=st.peek();
            }
            cur.left=null;
        }
    }

    // morris traversal approach
    public void BTtoLinkedList3(Node root){
        while (root!=null){
            if(root.left!=null){
                Node link = root.left;
                while (link.right!=null) {
                    link=link.right;
                }
                link.right=root.right;
                root.right=root.left;
                root.left=null;
            }
            root=root.right;
        }
    }
}

public class BinaryTree03 {
    public static void main(String[] args) {
//        int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
        int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1};
//        int[] nodes = {1, 2, 4, -1, -1, 5, -1, 7,-1, -1, 3, -1, 6, -1, -1};
        BinaryTree2 tree = new BinaryTree2();
        tree.buildTree(nodes);
        tree.levelOrder(tree.root);
//        tree.BTtoLinkedList3(tree.root);
//        tree.BTtoLinkedList2(tree.root);
//        tree.levelOrder(tree.root);
//        tree.BTtoLinkedList(tree.root);
//        tree.levelOrder(tree.root);

//        System.out.println(tree.inorderTraversal(tree.root));
        System.out.println(tree.morrisTraversal(tree.root));

//        String str = tree.serialize(tree.root);
//        System.out.println(str);
//        BinaryTree2.Node node = tree.deserialize(str);
//        tree.levelOrder(node);


//        System.out.println();
//        int[]in = {40,20,50,10,60,30};
//        int[]pre = {10,20,40,50,30,60};
//        tree.levelOrder(tree.constructBT(in,pre));
//        System.out.println();
//        int[]post = {40,50,20,60,30,10};
//        tree.levelOrder(tree.constructBT2(in,post));
//        System.out.println(tree.countNodes(tree.root));
    }
}
