package binaryTree;

import java.util.*;

class BinaryTree{
    public static class Node{
        int val;
        Node left;
        Node right;

        public Node(int val){
            this.val= val;
            this.left = null;
            this.right = null;
        }
    }
    Node root;
    int index = -1;
    public Node buildTree(int[] nodes){ // method is using preOrder traversal
        index++;
        if(nodes[index] == -1){
            return null;
        }
        Node node = new Node(nodes[index]);
        node.left = buildTree(nodes);
        node.right = buildTree(nodes);
        root = node;
        return node;
    }

    public void preOrder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder(Node root){
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public void postOrder(Node root){
        if(root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    public void levelOrder(Node root){
        if(root == null){
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while (!q.isEmpty()){
            Node node = q.remove();
            if(node == null){
                System.out.println();
                if(q.isEmpty()){
                    break;
                }else {
                    q.add(null);
                }
            }
            else {
                System.out.print(node.val);
                if(node.left != null){
                    q.add(node.left);
                }
                if(node.right != null){
                    q.add(node.right);
                }
            }
        }
    }

    public void levelOrder2(Node root){
        if(root == null){
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while (!q.isEmpty()){
            Node node = q.remove();
            if(node == null){
                System.out.println();
                if (q.isEmpty()){
                    break;
                }else {
                    q.add(null);
                }
            }
            else {
                if(node.left != null){
                    q.add(node.left);
                }
                if(node.right != null){
                    q.add(node.right);
                }
            }
        }
    }


    public int nodeCount(Node root){
        if (root == null){
            return 0;
        }
        return 1 + nodeCount(root.left) + nodeCount(root.right);
    }

    public int sumOfNodes(Node root){
        if (root == null){
            return 0;
        }
        return root.val + sumOfNodes(root.left) + sumOfNodes(root.right);
    }

    public int heightOfTree(Node root){ // distance from root to leaf node which is at max level
        if(root == null){
            return 0;
        }
        return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
    }

    class TreeInfo{
        int ht;
        int diam;
        TreeInfo(int ht, int diam){
            this.ht = ht;
            this.diam = diam;
        }
    }

    public TreeInfo diameter(Node root){ // longest continuous distance from one node to another
        if(root == null){
            return new TreeInfo(0,0);
        }
        TreeInfo left = diameter(root.left);
        TreeInfo right = diameter(root.right);

        int currHeight = Math.max(left.ht , right.ht) + 1;

        int maxDiam = Math.max(left.ht + right.ht + 1, Math.max(left.diam, right.diam));

        return new TreeInfo(currHeight, maxDiam);
    }

    private boolean isIdentical(Node root,Node subRoot){
        if(subRoot == null && root == null){
            return true;
        }
        if(root == null || subRoot == null){
            return false;
        }
        if(root.val == subRoot.val){ // if current val is equal then both child should be identical
            return isIdentical(root.left, subRoot.left) && isIdentical(root.right, subRoot.right);
        }
        return false;
    }

    public boolean isSubtree(Node root, Node subRoot) {
        if(subRoot == null){ // if subroot is null then root will also have null at leaf node
            return true;
        }
        if(root == null){ // if subroot is not null and if root is null then false
            return false;
        }
        if(isIdentical(root, subRoot)){ // if root of subtree lies through the current node then check further
            return true;
        }
        // now subtree may lie on either side of the node, so if true is returned from any sides it means subtree lies there
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public int sumAtKthLevel(Node root, int k, int start){
        if(root == null || k<1){ // first level of the tree i.e. of root node is 1
            return 0;
        }
        if(start == k){
            return root.val;
        }
        return sumAtKthLevel(root.left, k, start+1) + sumAtKthLevel(root.right, k, start+1);
    }

    public List<List<Integer>> levelOrderReturns(Node root){
        Queue<Node> q = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>() ;
        if(root == null){
            return ans;
        }
        q.offer(root); // adds the given element into q if it's null it wont throw an exception like add()
        while (!q.isEmpty()){
            int currSize = q.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < currSize; i++) {
                if(q.peek().left != null) q.offer(q.peek().left);
                if(q.peek().right != null) q.offer(q.peek().right);
                list.add(q.poll().val); // poll - removes and returns from front of queue
            }
            ans.add(list);
        }
        return ans;
    }

    public List<Integer> preOrderIteration(){
        Stack<Node> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        Node root = this.root;
        if(root == null) return ans;
        stack.add(root);
        while (!stack.isEmpty()){
            Node node = stack.pop();
            ans.add(node.val);
            if(node.right != null) stack.add(node.right);
            if(node.left != null) stack.add(node.left);
        }
        return ans;
    }

    public List<Integer> inOrderIteration(){
        List<Integer> ans = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (true){
            if(node != null){
                stack.push(node);
                node = node.left;
            }
            else {
                if(stack.isEmpty()){
                    break;
                }
                node = stack.pop();
                ans.add(node.val);
                node = node.right;
            }
        }
        return ans;
    }

    public List<Integer> inOrderIteration2(){
        List<Integer> ans = new ArrayList<>();
        Stack<Node> stack = new Stack();
        Node node = root;
        while (true){
            if(node != null){
                stack.push(node);
                node = node.left;
            }
            else {
                if(stack.isEmpty()){
                    break;
                }
                node = stack.pop();
                ans.add(node.val);
                node = node.right;
            }
        }
        return ans;
    }

    public List<Integer> postOrderIteration(){
        List<Integer> ans = new ArrayList<>();
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        Node node = root;
        stack1.add(node);
        while (!stack1.isEmpty()) {
            node = stack1.pop();
            stack2.add(node);
            if(node.left != null) stack1.add(node.left);
            if(node.right != null) stack1.add(node.right);
        }
        while (!stack2.isEmpty()){
            ans.add(stack2.pop().val);
        }
        return ans;
    }

    public List<Integer> postOrderIterationOneStack(){
        List<Integer> ans = new ArrayList<>();
        Stack<Node> stack1 = new Stack<>();
        Node node = root;
        Node temp;
        while (node != null || !stack1.isEmpty()) {
            if(node != null){
                stack1.add(node);
                node = node.left;
            }
            else {
                temp = stack1.peek().right;
                if(temp == null){
                    temp = stack1.pop();
                    ans.add(temp.val);
                    while (!stack1.isEmpty() && temp == stack1.peek().right){
                        temp = stack1.pop();
                        ans.add(temp.val);
                    }
                }
                else {
                    node = temp;
                }
            }
        }
        return ans;
    }

    // Balanced BT - difference between the height of left and right child of all the node is less than 2
    static boolean isBalanced(Node root){
        return checkHeight(root) != -1;
    }
    private static int checkHeight(Node root){
        if(root == null){
            return 0;
        }
        int left = checkHeight(root.left);
        if(left == -1) return -1;
        int right = checkHeight(root.right);
        if(right == -1) return -1;

        if(Math.abs(left-right) > 1) return -1;
        return Math.max(left, right) + 1;
    }


}

public class binaryTree01 {
    public static void main(String[] args) {
//        int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
//        int[] nodes = {1, 2, 4, -1, -1, 5, -1, 7,-1, -1, 3, -1, 6, -1, -1};
//        int[] nodes = {1,2,3,-1,-1,-1,-1};
        int[] nodes = {1,2,3,4,-1,-1,-1,-1,2,-1,3,-1,4,-1,-1};
        BinaryTree tree = new BinaryTree();
//        System.out.println(tree.buildTree(nodes).val);
        tree.buildTree(nodes);
//        tree.preOrder(tree.root);
//        System.out.println();
//        tree.inOrder(tree.root);
//        System.out.println();
//        tree.postOrder(tree.root);
//        System.out.println();
//        tree.levelOrder(tree.root);
        tree.levelOrder(tree.root);
//        System.out.println();
//        System.out.println(tree.levelOrderReturns(tree.root));
//        System.out.println(tree.preOrderIteration());
//        System.out.println(tree.inOrderIteration2());
//        System.out.println(tree.postOrderIteration());
//        System.out.println(tree.postOrderIterationOneStack());
//        System.out.println(tree.nodeCount(tree.root));
//        System.out.println(tree.sumOfNodes(tree.root));
//        System.out.println(tree.heightOfTree(tree.root));
//        System.out.println(tree.diameter(tree.root).diam);
//        int[] nodes1 = {2,4,-1,-1,5,-1,7,-1,-1};
//        BinaryTree tree2 = new BinaryTree();
//        tree2.buildTree(nodes1);
//        System.out.println();
//        tree2.levelOrder(tree2.root);
//        System.out.println();
//        System.out.println(tree.isSubtree(tree.root,tree2.root));
//        System.out.println(tree.sumAtKthLevel(tree.root, 1, 1));
        System.out.println(tree.isBalanced(tree.root));

    }
}