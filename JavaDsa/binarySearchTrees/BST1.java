package binarySearchTrees;


import java.util.*;

class BST{
    static class Node{
        int val;
        Node left,right;
        public Node(int val){
            this.val=val;
        }
    }

    Node root;
    int index;
    public BST(){
        root=null;
        index=-1;
    }

    public static Node buildTree(int value, Node root){
        if(root==null){
            return new Node(value);
        }
        if(root.val>value){
            root.left = buildTree(value,root.left);
        }
        else {
            root.right = buildTree(value,root.right);
        }
        return root;
    }

    public static void levelOrder(Node root) {
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
    public static void inorder(Node root){
        if(root==null)return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // insertion using iteration
    public static Node insertBST(Node root, int val){
        if(root==null)return new Node(val);
        Node node = root;
        while (true){
            if(val < node.val){
                if(node.left!=null) node=node.left;
                else{
                    node.left = new Node(val);
                    break;
                }
            }
            else {
                if(node.right!=null)node=node.right;
                else {
                    node.right = new Node(val);
                    break;
                }
            }
        }
        return root;
    }

    public static boolean search(Node root, int val){ // O(H)
        if(root==null)return false;
        if(val < root.val) return search(root.left,val);
        if(val > root.val) return search(root.right,val);
        return true;
    }
    // value greater than or equal to given val
    public static int ceil(Node root, int val){
        int ceil=-1;
        while (root!=null){
            if(root.val==val){
                ceil=root.val;
                break;
            }
            if(root.val>val){
                ceil=root.val;
                root=root.left;
            }
            else {
                root=root.right;
            }
        }
        return ceil;
    }
    //smaller than or equal
    public static int floor(Node root, int val){
        int floor=-1;
        while (root!=null){
            if(root.val==val)return val;
            if(val < root.val){
                root=root.left;
            }
            else {
                floor= root.val;
                root=root.right;
            }
        }
        return floor;
    }
    // rightmost node of left child of (node to be deleted) will point to right child of node to be deleted
    public static Node delete(Node root, int val){
        if(root==null) return root;
        if(root.val == val) return deleteHelper(root);
        Node dummyHead = root;
        while (root!=null){
            if(val < root.val){
                if(root.left!=null && root.left.val==val){
                    root.left = deleteHelper(root.left);
                    break;
                }else {
                    root=root.left;
                }
            }
            else {
                if(root.right!=null && root.right.val==val){
                    root.right = deleteHelper(root.right);
                    break;
                }else {
                    root=root.right;
                }
            }
        }
        return dummyHead;
    }

    private static Node deleteHelper(Node root){
        if(root.left==null)return root.right;
        if(root.right==null)return root.left;
        Node right = root.right;
        Node rightMostOfLeft = findRightMost(root.left);
        rightMostOfLeft.right = right;
        return root.left;
    }
    private static Node findRightMost(Node root){
        if(root.right==null)return root;
        return findRightMost(root.right);
    }

    public static Node kthsmallest(Node root,int k[])
    {
        if(root==null)
            return null;

        Node left=kthsmallest(root.left,k);
        if(left!=null)
            return left;
        k[0]--;
        if(k[0]==0)
            return root;

        return kthsmallest(root.right,k);
    }

    public static Node kthSmallest(Node root, int k[]){
        if(root==null)return null;
        Node left = kthSmallest(root.left,k);
        if(left!=null)return left;
        k[0]--;
        if(k[0]==0)return root;

        return kthSmallest(root.right,k);
    }

    public static Node kthLargest(Node root, int k[]){
        if(root==null)return null;
        Node right = kthLargest(root.right,k);
        if(right!=null)return right;
        k[0]--;
        if(k[0]==0)return root;

        return kthLargest(root.left,k);
    }

    public static boolean isValidBST(Node root, int range[]){
        if(root==null) return true;
        if((root.val < range[0]) || (root.val > range[1])){
            return false;
        }
        return isValidBST(root.left,new int[]{range[0],root.val}) && isValidBST(root.right, new int[]{root.val,range[1]});
    }

    public static Node LCA(Node root, int node1, int node2){
        if(node1 < root.val && node2 < root.val){
            return LCA(root.left,node1,node2);
        }
        if(node1 > root.val && node2 > root.val){
            return LCA(root.right,node1,node2);
        }
        return root;
    }

    public static Node preorderInsertion(int[]arr){
        return preorderInsertionHelper(arr,Integer.MAX_VALUE,new int[]{0});
    }
    private static Node preorderInsertionHelper(int[]arr, int upperBound, int[]curIndex){
        if(curIndex[0]==arr.length || arr[curIndex[0]] > upperBound) return null;
        Node node = new Node(arr[curIndex[0]++]);

        node.left = preorderInsertionHelper(arr,node.val,curIndex);
        node.right = preorderInsertionHelper(arr,upperBound,curIndex);
        return node;
    }
    // next greater element of the given node's val from inorder perspective
    public static Node successorInorder(Node root, int val){
        Node successor = null;
        while (root!=null){
            if(val >= root.val) root=root.right;
            else {
                successor = root;
                root=root.left;
            }
        }
        return successor;
    }

    // two-sum in bst in O(n) time + O(H)*2 space
    public static boolean twoSum(Node root,int val){
        BstIterator ascending = new BstIterator(root,false);
        BstIterator descending = new BstIterator(root,true);
        int s = ascending.next();
        int e = descending.next();
        while (s<e){
            if(s+e == val) return true;
            if(s+e < val) s=ascending.next();
            else e = descending.next();
        }
        return false;
    }

    // one or two node will be positioned incorrectly so fix them
    private static Node first;//try on leetcode
    private static Node prev;
    private static Node middle;
    private static Node last;
    private static void inOrder(Node root) {
        if(root == null) return;

        inOrder(root.left);

        if (prev != null && (root.val < prev.val))
        {

            // If this is first violation, mark these two nodes as
            // 'first' and 'middle'
            if ( first == null )
            {
                first = prev;
                middle = root;
            }

            // If this is second violation, mark this node as last
            else
                last = root;
        }

        // Mark this node as previous
        prev = root;
        inOrder(root.right);
    }
    public static void recoverTree(Node root) {
        first = middle = last = null;
        prev = new Node(Integer.MIN_VALUE);
        inOrder(root);
        if(first!=null && last!=null) {
            int t = first.val;
            first.val = last.val;
            last.val = t;
        }
        else if(first!=null && middle!=null)  {
            int t = first.val;
            first.val = middle.val;
            middle.val = t;
        }
    }

    private static class Pair{
        int size;
        int max;
        int min;
        public Pair(int s, int mx, int mn){
            size = s;
            max = mx;
            min = mn;
        }
    }
    public static int largestBst(Node root){
        return largestBst2(root).size;
    }
    private static Pair largestBst2(Node root){
        if(root==null) return new Pair(0,Integer.MIN_VALUE,Integer.MAX_VALUE);
        Pair left = largestBst2(root.left);
        Pair right = largestBst2(root.right);
        int size=0, min=0, max=0;
        if(left.max < root.val && root.val < right.min){
            return new Pair(left.size+ right.size+1,Math.max(root.val, right.max),Math.min(root.val, left.min));
        }
        return new Pair(Math.max(left.size, right.size), Integer.MAX_VALUE,Integer.MIN_VALUE);
    }
    static int idx = -1;
    public static Node buildTree(int[]preorder){
        idx++;
        if(idx == preorder.length || preorder[idx]==-1)return null;
        Node node = new Node(preorder[idx]);
        node.left = buildTree(preorder);
        node.right = buildTree(preorder);
        return node;
    }
}
// iterate over a BST in inorder fashion on asking next return next element and on hasNext return true if there exist
// more nodes else return false
class BSTIterator{
    public BSTIterator(BST.Node root){
        pushAllLeft(root);
    }
    Stack<BST.Node> st = new Stack<>();
    private void pushAllLeft(BST.Node root){
        for(; (root!=null); st.push(root), root=root.left);
        }

    public boolean hasNext(){
        return !st.isEmpty();
    }
    public int next(){
        BST.Node temp = st.pop();
        pushAllLeft(temp.right);
        return temp.val;
    }


}

class BstIterator{
    Stack<BST.Node> st = new Stack<>();
    boolean isReverse= true;
    public BstIterator(BST.Node root, boolean isReverse){
        this.isReverse = isReverse;
        pushAll(root,isReverse);
    }
    public boolean hasNext(){
        return !st.isEmpty();
    }
    public int next(){
        BST.Node temp = st.pop();
        if(isReverse==false) pushAll(temp.right,isReverse);
        else pushAll(temp.left,isReverse);
        return temp.val;
    }
    private void pushAll(BST.Node root, boolean isReverse){
        while (root!=null){
            st.push(root);
            if(isReverse==false) root=root.left;
            else root=root.right;
        }
    }
}

public class BST1 {
    public static void main(String[] args) {
//        int[]values={5,1,2,3,4,7,9};
        int[]values={13,10,7,12,9,8,15,14,17,16};
        BST.Node root = null;
        for (int i = 0; i < values.length; i++) {
            root = BST.buildTree(values[i],root);
        }
        BST.inorder(root);
        System.out.println();
        BST.levelOrder(root);
        System.out.println();
//        int[] arr = {5,3,1,4,6};
//        BST.Node root = BST.preorderInsertion(arr);
//        BST.inorder(root);
//        System.out.println();
//        BST.levelOrder(root);
//        System.out.println();

        //largest bst
//        int[]pre = {4,2,1,-1,-1,3,-1,-1,6,5,-1,-1,0,-1,-1};
//        int[]pre = {4,7,1,-1,-1,3,-1,-1,6,5,-1,-1,2,-1,-1}; // recover bst
//        BST.Node n = BST.buildTree(pre);
//        BST.inorder(n);
//        System.out.println();
//        BST.levelOrder(n);
//        System.out.println();
////        System.out.println(BST.largestBst(n));
//        BST.recoverTree(n);
//        BST.inorder(n);
//        System.out.println();
//        BST.levelOrder(n);
//        System.out.println();


//        System.out.println(BST.twoSum(root,4));
//        System.out.println(BST.twoSum(root,9));
//        System.out.println(BST.twoSum(root,81));
//        BSTIterator it = new BSTIterator(root);
//        System.out.println(it.next());
//        System.out.println(it.next());
//        System.out.println(it.hasNext());
//        System.out.println(it.next());
//        System.out.println(it.next());
//        System.out.println(it.next());
//        System.out.println(it.hasNext());

//        System.out.println(BST.successorInorder(root,3).val);
//        System.out.println(BST.successorInorder(root,1).val);
//        System.out.println(BST.successorInorder(root,5).val);

//        System.out.println(BST.LCA(root,8,12).val);
//        System.out.println(BST.LCA(root,8,16).val);
//        System.out.println(BST.LCA(root,14,12).val);
//        System.out.println(BST.LCA(root,14,16).val);
//        System.out.println(BST.isValidBST(root,new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE}));
//        System.out.println(BST.kthsmallest(root,new int[]{3}).val);
//        System.out.println(BST.kthLargest(root,new int[]{2}).val);
//        root = BST.insertBST(root,6);
//        BST.inorder(root);
//        System.out.println();
        root = BST.delete(root, 7);
//        BST.inorder(root);
//        System.out.println(BST.floor(root,8));
//        System.out.println(BST.floor(root,2));
//        System.out.println(BST.ceil(root,8));
//        System.out.println(BST.ceil(root,9));
//        System.out.println(BST.ceil(root,10));
//        System.out.println(BST.search(root,3));
//        System.out.println(BST.search(root,9));
//        System.out.println(BST.search(root,31));
    }
}
