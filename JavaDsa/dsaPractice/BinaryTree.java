package dsaPractice;
// inorderIteration
// postorder iteration 2 stack / 1 stack
// zig zag traversal
// boundary traversal
//vertical order
//topview / leftside
// width of BT
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;

class BinaryTree1{
    public class Node{
        int val;
        Node left, right;
        public Node(int val){
            this.val=val;
        }
    }
    int index;
    Node root;
    BinaryTree1(){
        index=-1;
    }
    public Node buildTree(int[]nodes){ // using preorder traversal to build tree
        index++;
            if(nodes[index]==-1) return null;
            Node node = new Node(nodes[index]);
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            root = node;
            return node;
    }

    public void preorder(Node root){
        if(root==null)return;
        System.out.print(root.val + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public void postorder(Node root){
        if(root==null)return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.val + " ");
    }

    public void inorder(Node root){
        if(root==null)return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public void levelOrder(Node root){ // BFS
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while (!q.isEmpty()){
            Node cur = q.poll();
            if(cur==null){
                System.out.println();
                if(!q.isEmpty()) q.add(null);
            }
            else {
                System.out.print(cur.val + " ");
                if(cur.left!=null)q.add(cur.left);
                if(cur.right!=null)q.add(cur.right);
            }
        }
    }

    public int nodeCount(Node root){
        if(root==null)return 0;
        return 1 + nodeCount(root.left) + nodeCount(root.right);
    }

    public int sumOfNodes(Node root){
        if(root==null)return 0;
        return root.val + sumOfNodes(root.left) + sumOfNodes(root.right);
    }

    public int heightOfTree(Node root){
        if(root==null)return 0;
        return 1 + Math.max(heightOfTree(root.left),heightOfTree(root.right));
    }

    public int diameter(){// longest continuous distance from one node to another
        // we should know height and diameter of each node
        return diameter(root).diam;
    }
    private class TreeInfo{
        int ht;
        int diam;
        TreeInfo(int ht, int diam){
            this.ht=ht;
            this.diam=diam;
        }
    }
    private TreeInfo diameter(Node root){
        if(root==null){
            return new TreeInfo(0,0);
        }
        TreeInfo left = diameter(root.left);
        TreeInfo right = diameter(root.right);
        int height = 1+Math.max(left.ht,right.ht);
        return new TreeInfo(height, Math.max(1+ left.ht+right.ht, Math.max(left.diam, right.diam)));
    }

    public boolean isSubTree(Node root, Node subRoot){
        if(subRoot==null)return true;
        if(root==null)return false;
        if(root.val == subRoot.val){
            return isIdentical(root.left,subRoot.left) && isIdentical(root.right,subRoot.right);
        }
        return isSubTree(root.left,subRoot) || isSubTree(root.right,subRoot);

    }
    private boolean isIdentical(Node root, Node subRoot){
        if(root==null && subRoot==null)return true;
        if(root==null || subRoot==null)return false;
        if(root.val != subRoot.val)return false;
        return isIdentical(root.left,subRoot.left) && isIdentical(root.right,subRoot.right);
    }

    public int sumAtKthLevel(Node root, int k){ //k starts from 0
        if(root == null)return 0;
        if(k==0) return root.val;
        return sumAtKthLevel(root.left,k-1) + sumAtKthLevel(root.right,k-1);
    }

    public List<ArrayList<Integer>> levelOrderReturns(Node root){ // BFS
        Queue<Node> q = new LinkedList<>();
        List<ArrayList<Integer>> ans = new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()){
            int size = q.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                if(cur.left!=null)q.add(cur.left);
                if(cur.right!=null)q.add(cur.right);
                list.add(cur.val);
            }
            ans.add(list);
        }
        return ans;
    }

    public List<Integer> preorderIteration(){
        Node node = root;
        List<Integer> ans = new ArrayList<>();
        if(root==null)return ans;
        Stack<Node> st = new Stack<>();
        st.add(root);
        while (!st.isEmpty()){
            Node cur = st.pop();
            // first we will push right then left because we first want to access left then right, so left will be on top
            if(cur.right!=null)st.push(cur.right);
            if(cur.left!=null)st.push(cur.left);
            ans.add(cur.val);
        }
        return ans;
    }
    
    public List<Integer> inorderIteration(){
        List<Integer> ans = new ArrayList<>();
        Stack<Node> st = new Stack<>();
        Node node = root;
        while (true){
            if(node!=null){
                st.push(node);
                node=node.left;
            }
            else {
                if(st.isEmpty())break;
                node=st.pop();
                ans.add(node.val);
                node=node.right;
            }
        }
        return ans;
    }

    public List<Integer> postorderIterationTwoStack(){
        List<Integer> ans = new ArrayList<>();
        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        Node node = root;
        st1.add(node);
        while (!st1.isEmpty()){
            node = st1.pop();
            st2.push(node);
            if(node.left!=null)st1.push(node.left);
            if(node.right!=null)st1.push(node.right);
        }
        while (!st2.isEmpty()){
            ans.add(st2.pop().val);
        }
        return ans;
    }

    public List<Integer> postorderIterationOneStack(){
        Stack<Node> st = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        Node node = root;
        Node temp;
        while (node!=null || !st.isEmpty()){
            if(node!=null){
                st.push(node);
                node=node.left; // push the node into the stack and keep going left, because we first want to print left
            }
            else { // after keep going left now go to right because after left we have to go to right
                temp = st.peek().right;
                if(temp==null){ // if we don't have node at right, go to the root now which is on top of the stack
                    temp=st.pop();
                    ans.add(temp.val);// after right we have to go to root (left right root)
                    while (!st.isEmpty() && temp == st.peek().right){// if node on top of stack is the root of cur node
                        temp=st.pop();                // then go to it
                        ans.add(temp.val);
                    }
                }
                else { // if we have node at root's right assign it to node
                    node = temp;
                }
            }
        }
        return ans;
    }

    // Balanced BT - difference between the height of left and right child of all the node is less than 2
    public boolean isBalanced(){
        return checkHeight(root) != -1;
    }
    private int checkHeight(Node root){ //O(n)
        if(root==null)return 0;
        int left = checkHeight(root.left);
        int right = checkHeight(root.right);
        if(left==-1 || right==-1)return -1;
        int heightDiff = Math.abs(left-right);
        if(heightDiff>1)return -1;
        return Math.max(left,right)+1;
    }

    // return the highest sum from all the paths, here path from one leaf node to another is considered as path
    public int maxPathSum(){
        return maxPathSumHelper(root).maxRootSum;
    }
    private NodeInfo maxPathSumHelper(Node root){
        if(root==null){
            return new NodeInfo(0,0);
        }
        NodeInfo left = maxPathSumHelper(root.left);
        NodeInfo right = maxPathSumHelper(root.right);
        int maxHtSum = Math.max(left.maxHtSum,right.maxHtSum)+ root.val;
        int maxRootSum = Math.max(left.maxHtSum+ right.maxHtSum+ root.val,Math.max(left.maxRootSum,right.maxRootSum));
        return new NodeInfo(maxHtSum,maxRootSum);
    }
    private class NodeInfo{
        int maxHtSum;
        int maxRootSum; // max of left ht val + right ht val + 1
        NodeInfo(int ht, int root){
            this.maxHtSum=ht;
            this.maxRootSum=root;
        }
    }

    // zig-zag traversal (same as level-order but first traverse left-to-right then right-to-left and so on in a level)
    public List<List<Integer>> zigZagTraversal(Node root){
        List<List<Integer>> ans = new ArrayList<>();
        if(root==null)return ans;
        Queue<Node> q = new LinkedList<>();
        Node node = root;
        q.add(node);
        boolean leftToRight = true;
        while (!q.isEmpty()){
            int size = q.size();
            ArrayList<Integer> list = new ArrayList<>(size); // a fixed size is given to it
            for (int i = 0; i < size; i++) {
                node = q.remove();
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
                if(leftToRight)list.add(node.val);
                else list.add(0,node.val); // always add at first index
            }
            leftToRight = !leftToRight;
            ans.add(list);
        }
        return ans;
    }

    public void boundaryTraversal(){
        if(root==null)return;
        ArrayList ans = new ArrayList<>();
        addLeft(root,ans);
        addLeaves(root,ans);
        addRight(root,ans);
        System.out.println(ans);
    }
    private void addLeft(Node root,ArrayList<Integer> ans){
        Node node = root;
        while (node!=null){
            if(!isLeaf(node))ans.add(node.val);
            if(node.left!=null)node=node.left;
            else node= node.right;
        }
    }
    private void addLeaves(Node root,ArrayList<Integer> ans){
        if(root==null){
            return;
        }
        addLeaves(root.left,ans);
        if(isLeaf(root))ans.add(root.val);
        addLeaves(root.right,ans);
    }
    private void addRight(Node root,ArrayList<Integer> ans){
        Stack<Node> st = new Stack<>();
        Node node = root.right;
        while (node!=null){
            if(!isLeaf(node)) st.push(node);
            if(node.right!=null)node=node.right;
            else node=node.left;
        }
        while (!st.isEmpty()){
            ans.add(st.pop().val);
        }
    }
    private boolean isLeaf(Node root){
        return root.left==null && root.right==null;
    }

    // below doesn't give potential answer
    public void verticalOrderTraversal(){
        Map<Integer, ArrayList> map = new TreeMap<>();
        verticalOrderTraversalHelper(root,map,0);
        for(Map.Entry<Integer,ArrayList> map2 : map.entrySet()){
            System.out.println(map2.getValue());
        }
    }
    private void verticalOrderTraversalHelper(Node root,Map<Integer, ArrayList> map,int index){
        if(root==null)return;
        if(map.containsKey(index))map.get(index).add(root.val);
        else {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(root.val);
            map.put(index,temp);
        }
        verticalOrderTraversalHelper(root.left,map,index-1);
        verticalOrderTraversalHelper(root.right,map,index+1);
    }

    public void verticalOrder(){
        Node node = root;
        if(root==null)return;
        Queue<Pair> q = new LinkedList<>();
        Map<Integer,List<Integer>> map = new TreeMap<>();
        q.add(new Pair(0,node));
        while (!q.isEmpty()){
            Pair cur = q.poll();
            if(cur.node.left!=null)q.add(new Pair(cur.col-1,cur.node.left));
            if(cur.node.right!=null)q.add(new Pair(cur.col+1,cur.node.right));

            if(map.containsKey(cur.col)) map.get(cur.col).add(cur.node.val);
            else {
                List<Integer> list = new ArrayList<>();
                list.add(cur.node.val);
                map.put(cur.col,list);
            }
        }
        for(Map.Entry<Integer,List<Integer>> el : map.entrySet()){
            System.out.println(el.getValue());
        }
    }

    private class Pair{
        int col;
        Node node;
        Pair(int col, Node node){
            this.col=col;
            this.node=node;
        }
    }

    public void topView(){
        if(root==null)return;
        Map<Integer,Integer> map= new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,root));
        while (!q.isEmpty()){
            Pair cur = q.poll();
            if (cur.node.left!=null) q.add(new Pair(cur.col-1,cur.node.left));
            if (cur.node.right!=null) q.add(new Pair(cur.col+1,cur.node.right));

            if(!map.containsKey(cur.col)){
                map.put(cur.col, cur.node.val);
            }
        }
        ArrayList<Integer> ans= new ArrayList<>();
        for(Map.Entry<Integer,Integer> el : map.entrySet()){
            ans.add(el.getValue());
        }
        System.out.println(ans);
    }

    public void bottomView(){
        if(root==null)return;
        Map<Integer,Integer> map= new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,root));
        while (!q.isEmpty()){
            Pair cur = q.poll();
            if (cur.node.left!=null) q.add(new Pair(cur.col-1,cur.node.left));
            if (cur.node.right!=null) q.add(new Pair(cur.col+1,cur.node.right));

            map.put(cur.col, cur.node.val);// each time value of hashmap is updated because we want to store bottom node
        }
        ArrayList<Integer> ans= new ArrayList<>();
        for(Map.Entry<Integer,Integer> el : map.entrySet()){
            ans.add(el.getValue());
        }
        System.out.println(ans);
    }

    public ArrayList<Integer> leftSideView(){
        ArrayList<Integer> ans = new ArrayList<>();
        leftSide(root,0,ans);
        return ans;
    }
    private void leftSide(Node root,int level, ArrayList<Integer> ans){
        if(root==null){
            return;
        }
        // if we are at this level for the first time then add it because we r going left to right
        if(level==ans.size()) ans.add(root.val);
        leftSide(root.left,level+1,ans);
        leftSide(root.right,level+1,ans);
    }

    public ArrayList<Integer> rightSideView(){
        ArrayList<Integer> ans = new ArrayList<>();
        rightSide(root,0,ans);
        return ans;
    }
    private void rightSide(Node root,int level, ArrayList<Integer> ans){
        if(root==null){
            return;
        }
        if(level==ans.size()) ans.add(root.val);
        rightSide(root.right,level+1,ans);
        rightSide(root.left,level+1,ans);
    }

    public boolean isSymmetric(Node root){
        return isSymmetricHelper(root.left,root.right);
    }
    private boolean isSymmetricHelper(Node left, Node right){
        if(left==null && right==null)return true;
        if(left==null || right==null)return false;
        if(left.val!=right.val)return false;

        return isSymmetricHelper(left.right,right.left) && isSymmetricHelper(left.left,right.right);
    }

    // return nodes from root to target
    public ArrayList<Integer> rootToNode(int target){
        ArrayList<Integer> ans  = new ArrayList<>();
        pathFinder(root,target,ans);
        return ans;
    }
    private boolean pathFinder(Node root, int target, ArrayList<Integer> ans){
        if(root==null)return false;
        ans.add(root.val);
        if(root.val==target)return true;
        if(pathFinder(root.left,target,ans)) return true;
        if(pathFinder(root.right,target,ans)) return true;
        ans.remove(ans.size()-1);
        return false;
    }

    public int lowestCommonAncestor(Node root, int a, int b){// lowest common ancestor - most in-depth common node/parent
        if(root==null)return -1;
        if(root.val == a || root.val == b) return root.val;
        int left = lowestCommonAncestor(root.left,a,b);
        int right = lowestCommonAncestor(root.right,a,b);
        if(left!=-1 && right!=-1){
            return root.val;
        }
        if(left!=-1)return left;
        else return right;
    }

    // max width of bt = in any level there should be atleast 2 nodes and no of nodes in that level that can be accomodated btwn it
    // + those 2 = width , find max width
    public int widthOfBT(){
        if(root == null) return 0;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,root));
        int ans=0;
        while (!q.isEmpty()){
            int size = q.size();
            int first=0,last=0;
            int minIndex = q.peek().col;
            for (int i = 0; i < size; i++) {
                int curIndex = q.peek().col - minIndex;
                Node node = q.poll().node;
                if(node.left!=null) q.add(new Pair(curIndex*2+1,node.left));
                if(node.right!=null) q.add(new Pair(curIndex*2+2,node.right));
                if(i==0)first=curIndex;
                if(i==size-1)last = curIndex;
            }
            ans = Math.max(ans,last-first+1);
        }
        return ans;
    }

    // root.val == root.left.val + root.right.val for all the nodes
    public void childrenSumProperty(Node root){
        if(root==null)return;
        int left=0,right=0;
        if(root.left!=null ) left=root.left.val;
        if(root.right!=null ) right=root.right.val;
        if(left+right<root.val){
            if(root.left!=null ) root.left.val=root.val;
            if(root.right!=null ) root.right.val= root.val;
        }
        childrenSumProperty(root.left);
        childrenSumProperty(root.right);
        if(root.left!=null ) left=root.left.val;
        if(root.right!=null ) right=root.right.val;
        if(root.left!=null || root.right!=null){
            root.val=left+right;
        }
    }

    private Node findNode(Node root, int target){
        if(root==null || root.val==target){
            return root;
        }
        Node left = findNode(root.left,target);
        Node right= findNode(root.right,target);
        return (left!=null) ? left : right;
    }

    private void markParents(Node root, Map<Node, Node> parent_track){
        if(root==null)return;
        if(root.left!=null)parent_track.put(root.left,root);
        if(root.right!=null)parent_track.put(root.right,root);
        markParents(root.left,parent_track);
        markParents(root.right,parent_track);
    }

    // return all the nodes at k distance from target node
    public List<Integer> distanceK(int target, int k){
        List<Integer> ans = new ArrayList<>();
        Node node = findNode(root,target);
        if(node==null)return ans;
        Map<Node, Node> parent_track = new HashMap<>();
        markParents(root,parent_track);
        Map<Node,Boolean> visited = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        visited.put(node,true);
        int level=0;
        while (!q.isEmpty()){
            if(level==k)break;
            level++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                node = q.poll();
                if(node.left!=null && !visited.containsKey(node.left)){
                    q.add(node.left);
                    visited.put(node.left,true);
                }
                if(node.right!=null && !visited.containsKey(node.right)){
                    q.add(node.right);
                    visited.put(node.right,true);
                }
                if(parent_track.get(node) != null && !visited.containsKey(parent_track.get(node))){
                    q.add(parent_track.get(node));
                    visited.put(parent_track.get(node),true);
                }
            }
        }
        int n = q.size();
        for (int i = 0; i < n; i++) {
            ans.add(q.poll().val);
        }
        return ans;
    }

    // my own solution for distanceK
    private void markParents2(Node root, Map<Node, Node> parent){
        if(root==null) return;
        if(root.left!=null) parent.put(root.left, root);
        if(root.right!=null) parent.put(root.right, root);
        markParents2(root.left, parent);
        markParents2(root.right, parent);
    }

    public List<Integer> distanceK2(Node root, Node target, int k) {
        List<Integer> ans = new ArrayList<>();
        Map<Node, Node> parent = new HashMap<>();
        markParents2(root, parent);
        findNode2(target, ans, k, parent, null);
        return ans;
    }

    private void findNode2(Node node, List<Integer> ans, int k, Map<Node, Node> parent, Node prev){
        if(node == null) return;
        if(k==0){
            ans.add(node.val);
            return;
        }
        if(prev!=node.left) findNode2(node.left, ans, k-1, parent, node);
        if(prev!=node.right) findNode2(node.right, ans, k-1, parent, node);
        if(parent.containsKey(node) && prev!=parent.get(node)) findNode2(parent.get(node), ans, k-1, parent, node);
    }

    private int leftHeight(Node root){
        if(root==null) return 0;
        return 1 + leftHeight(root.left);
    }
    private int rightHeight(Node root){
        if(root==null) return 0;
        return 1 + rightHeight(root.right);
    }
    // count no. of nodes in a complete BT, lowest level is unfilled from right side
    // count in less than O(n) time
    public int countNodes(Node root) {
        if(root == null) return 0;
        int left = leftHeight(root.left);
        int right = rightHeight(root.right);
        if(left == right) return (int)Math.pow(2,left+1)-1; //2^h-1 (if the ht of l & r child is equal) h is height
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public Node constructBT(int[]inorder, int[]preorder){
        return construct(inorder,0,inorder.length-1,preorder,0,preorder.length-1);
    }
    private Node construct(int[]inorder,int inStart,int inEnd, int[]preorder, int preStart, int preEnd){
        if(inStart>inEnd || preStart>preEnd)return null;
        Node node = new Node(preorder[preStart]);
        int indexInInorder= findIndex(preorder[preStart],inorder);
        int nodesToLeft = indexInInorder-inStart;
        node.left = construct(inorder,inStart,indexInInorder-inStart-1,preorder,preStart+1,preStart+nodesToLeft);
        node.right = construct(inorder,indexInInorder+1,inEnd,preorder,preStart+nodesToLeft+1,preEnd);
        return node;
    }
    private int findIndex(int num,int[]inorder){
        for (int i = 0; i < inorder.length; i++) {
            if(inorder[i]==num)return i;
        }
        return -1;
    }

    public Node constructBT2(int[]inorder, int[]postorder){
        Map<Integer,Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i],i);
        }
        return construct2(inorder,0,inorder.length-1,postorder,0,postorder.length-1,inorderMap);
    }
    private Node construct2(int[]inorder,int inStart, int inEnd, int[]postorder, int postStart, int postEnd,Map<Integer,Integer>inorderMap){
        if(inStart>inEnd || postStart>postEnd)return null;
        Node node = new Node(postorder[postEnd]);
        int indexInInorder = inorderMap.get(postorder[postEnd]);
        int nodesToLeft = indexInInorder-inStart;
        node.left = construct2(inorder,inStart,indexInInorder-1,postorder,postStart,postStart+nodesToLeft-1,inorderMap);
        node.right = construct2(inorder,indexInInorder+1,inEnd,postorder,postStart+nodesToLeft,postEnd-1,inorderMap);
        return node;
    }

    // Serialize - root will be given to you convert the binary tree into a string and return it , ( done using level order)
    public String serialize(Node root){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        StringBuilder sb = new StringBuilder();
        Node node;
        while (!q.isEmpty()){
            node = q.poll();
            if(node==null){
                sb.append("n ");
                continue;
            }
            sb.append(node.val+" ");
            q.add(node.left);
            q.add(node.right);
        }
        return sb.toString();
    }

    // convert level order string into a tree (1,2,3,4,5,n,6,n,n,n,n,n,n)
    public Node deserialize(String str){
        String[] levelOrder = str.split(" ");
        Node root = new Node(Integer.parseInt(levelOrder[0]));
        Node node;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        for (int i = 1; i < levelOrder.length; i++) {
            node = q.poll();
            if(!levelOrder[i].equals("n")){
                node.left = new Node(Integer.parseInt(levelOrder[i]));
                q.add(node.left);
            }
            if(!levelOrder[++i].equals("n")){
                node.right = new Node
                        (Integer.parseInt(levelOrder[i]));
                q.add(node.right);
            }
        }
        return root;
    }

    // morris traversal - it.s speciality is it takes O(1) space (constant space)
    // here we connect rightmost leaf node of each child to current node
    // inorderTraversal using morris traversal
    public ArrayList<Integer> morrisTraversal(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        while (root!=null){
            if(root.left!=null){
                Node left = root.left;
                while (left.right!=null && left.right!=root){
                    left=left.right;
                }
                if(left.right==null){
                    left.right=root;
                    root=root.left;
                }else {
                    left.right=null;
                    ans.add(root.val);
                    root=root.right;
                }
            }
            else {
                ans.add(root.val);
                root=root.right;
            }
        }
        return ans;
    }

    // flatten a binary tree to linkedList, don't create new node do it in itself
    // recursive approach, preorder traversal
    Node prev = null;
    public void flattenBT(Node root){
        if(root==null)return;
        flattenBT(root.right);
        flattenBT(root.left);
        root.right=prev;
        root.left=null;
        prev=root;
    }
    // iterative approach , preorder traversal
    public void flattenBT2(){
        Node node = root;
        if(node==null)return;
        Stack<Node> st = new Stack<>(); // preorder comes under DFS so we need to use stack
        st.push(node);
        while (!st.isEmpty()){
            node = st.pop();
            if(node.right!=null)st.push(node.right);
            if(node.left!=null)st.push(node.left);
            node.left=null;
            if(!st.isEmpty())
                node.right = st.peek();
        }
    }
    // morris traversal approach
    public void flattenBT3(){
        Node node = root;
        while (node!=null){
            if(node.left!=null){
                Node left = node.left;
                while (left.right!=null)left=left.right;
                left.right=node.right;
                node.right=node.left; // left becomes right
                node.left=null;
            }
            node=node.right;
        }
    }

    public void linkedList(Node root){
        if(root==null)return;
        System.out.print(root.val + " -> ");
        linkedList(root.right);
    }


    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> level = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        Node node = root;
        q.add(node);
        q.add(null);
        List<Integer> lev = new ArrayList<>();
        while(!q.isEmpty()){
            node = q.poll();
            if(node == null){
                level.add(lev);
                lev = new ArrayList<>();
                if(!q.isEmpty()) q.add(null);
            }else{
                lev.add(node.val);
                if(node.left!=null) q.add(node.left);
                if(node.right!=null) q.add(node.right);
            }
        }
        return level;
    }

    public List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        Node node = root;
        q.add(node);
        boolean toRight = false;
        while(!q.isEmpty()){
            Stack<Node> st = new Stack<>();
            List<Integer> sub = new ArrayList<>();
            while(!q.isEmpty()) {
                sub.add(q.peek().val);
                st.push(q.poll());
            }
            while(!st.isEmpty()){
                node = st.pop();
                if(toRight){
                    if(node.left!=null) q.add(node.left);
                    if(node.right!=null) q.add(node.right);
                }else{
                    if(node.right!=null) q.add(node.right);
                    if(node.left!=null) q.add(node.left);
                }
            }
            ans.add(sub);
            toRight = !toRight;
        }
        return ans;
    }

    public String serialize3(Node root) {
        StringBuilder decoded = new StringBuilder();
        Node node = root;
        Stack<Node> st = new Stack<>();
        st.push(root);
        while(!st.isEmpty()){
            node = st.pop();
            if(node == null) {
                decoded.append("n");
            }else{
                decoded.append(node.val);
                st.push(node.right);
                st.push(node.left);
            }
        }
        return decoded.toString();
    }

    private Node buildTree3(String[] tree, int[] idx){
        if(tree[idx[0]] == "n") return null;
        Node n = new Node(Integer.parseInt(tree[idx[0]]));
        idx[0] = idx[0]+1;
        n.left = buildTree3(tree, idx);
        idx[0] = idx[0]+1;
        n.right = buildTree3(tree, idx);
        return n;
    }

    // Decodes your encoded data to tree.
    public Node deserialize3(String data) {
        String[] tree = data.split("");
        if(tree.length == 0 || tree[0].equals("n")) return null;
        Node root = buildTree3(tree, new int[]{0});
        return root;
    }




}

public class BinaryTree {
    public static void main(String[] args) {
        BinaryTree1 tree = new BinaryTree1();
//        int[]nodes= {1,2,4,-1,-1,5,-1,-1,3,-1,-1};
//        int[]nodes = {1,2,4,-1,-1,5,-1,7,-1,-1,3,-1,6,-1,-1};
//        int[]nodes = {1,2,3,4,-1,-1,5,-1,-1,6,7,-1,-1,8,-1,-1,9,10,-1,-1,11,-1,-1};
//        int[]nodes={1,2,3,-1,-1,4,-1,-1,5,-1,-1};
//        int[]nodes = {1,2,3,-1,-1,-1,-1};
//        int[]nodes={1,2,3,-1,-1,4,-1,-1,2,4,-1,-1,3,-1,-1};
        int[] n = {1,2,-1,-1,3,4,-1,-1,5,-1,-1};
        tree.buildTree(n);
        tree.levelOrder(tree.root);
        System.out.println();
//        System.out.println(tree.zigzagLevelOrder(tree.root));
//        System.out.println(tree.levelOrder1(tree.root));
//        System.out.println(tree.serialize1(tree.root));
//        tree.flattenBT(tree.root);
//        tree.flattenBT2();
//        tree.flattenBT3();
//        tree.preorder(tree.root);
//        System.out.println();
//        tree.linkedList(tree.root);
//        System.out.println(tree.morrisTraversal(tree.root));
//        String str = tree.serialize(tree.root);
//        System.out.println(str);
//        tree.levelOrder(tree.deserialize(str));
//        int[]in = {4,2,5,1,3,6};
//        int[]pre={1,2,4,5,3,6};
//        int[]post = {4,5,2,6,3,1};
//        BinaryTree1.Node node1= tree.constructBT(in,pre);
//        tree.levelOrder(node1);
//        tree.preorder(node1);
//        System.out.println();
//        BinaryTree1.Node node2= tree.constructBT2(in,post);
//        tree.levelOrder(node2);
//        tree.postorder(node2);
//        System.out.println(tree.countNodes(tree.root));
//        System.out.println(tree.distanceK(2,1));
//        tree.childrenSumProperty(tree.root);
//        tree.levelOrder(tree.root);
        System.out.println(tree.widthOfBT());
//        System.out.println(tree.lowestCommonAncestor(tree.root,4,7));
//        System.out.println(tree.rootToNode(6));
//        System.out.println(tree.isSymmetric(tree.root));
//        System.out.println(tree.leftSideView());
//        System.out.println(tree.rightSideView());
//        tree.verticalOrderTraversal();
//        System.out.println();
//        tree.topView();
//        tree.bottomView();
//        tree.verticalOrder();
//        tree.boundaryTraversal();
//        System.out.println(tree.zigZagTraversal(tree.root));
//        System.out.println(tree.maxPathSum());
//        System.out.println(tree.isBalanced());
//        System.out.println(tree.postorderIterationOneStack());
//        System.out.println(tree.postorderIterationTwoStack());
//        System.out.println(tree.inorderIteration());
//        System.out.println(tree.preorderIteration());
//        System.out.println(tree.levelOrderReturns(tree.root));
//        System.out.println(tree.sumAtKthLevel(tree.root,1));
//        BinaryTree1 tree2 = new BinaryTree1();
//        int[]nodes2 = {2,4,-1,-1,5,-1,-1};
//        tree2.buildTree(nodes2);
//        tree.levelOrder(tree.root);
//        System.out.println();
//        tree2.levelOrder(tree2.root);
//        System.out.println(tree.isSubTree(tree.root,tree2.root));
//        System.out.println(tree.diameter());
//        System.out.println(tree.heightOfTree(tree.root));
//        System.out.println(tree.sumOfNodes(tree.root));
//        System.out.println(tree.nodeCount(tree.root));
//        tree.preorder(tree.root);
//        System.out.println();
//        tree.postorder(tree.root);
//        System.out.println();
//        tree.inorder(tree.root);
//        System.out.println();
    }
}
