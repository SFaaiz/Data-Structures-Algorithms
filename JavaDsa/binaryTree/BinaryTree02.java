package binaryTree;


import java.util.*;

class BinaryTree1 {
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
                System.out.print(node.val + " ");
                if(node.left != null){
                    q.add(node.left);
                }
                if(node.right != null){
                    q.add(node.right);
                }
            }
        }
    }

    private class TreeInfo{
        int heightSum;
        int maxPath;
        TreeInfo(int heightSum, int maxPath){
            this.heightSum = heightSum;
            this.maxPath = maxPath;
        }
    }
    public int maxPathSum(){
        return findMaxPathSum(root).maxPath;
    }
    private TreeInfo findMaxPathSum(Node root){
        if(root == null){
            return new TreeInfo(0,0);
        }
        TreeInfo left = findMaxPathSum(root.left);
        TreeInfo right = findMaxPathSum(root.right);

        int maxPath = Math.max(left.heightSum + right.heightSum + root.val, Math.max(left.maxPath, right.maxPath));
        int maxHeight = Math.max(left.heightSum, right.heightSum) + root.val;

        return new TreeInfo(maxHeight, maxPath);
    }

    // zig-zag traversal (same as level-order but first traverse left-to-right then right-to-left and so on
    public List<List<Integer>> zigZagTraversal(Node root){
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        Node node;
        boolean leftToRight = true;
        while (!q.isEmpty()){
            int size = q.size();
            ArrayList<Integer> subList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                node = q.remove();
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
                if(leftToRight)subList.add(node.val);
                else subList.add(0,node.val);
            }
            leftToRight = !leftToRight;
            ans.add(subList);
        }
        return ans;
    }


    // boundary traversal - in anticlock-wise direction
    public void boundaryTraversal(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root != null) ans.add(root.val);
        addLeftBoundary(root, ans);
        addLeaves(root, ans);
        addRightBoundary(root, ans);
        System.out.println(ans);
    }

    private void addLeaves(Node root, ArrayList<Integer> ans) {
        if(isLeaf(root)){
            ans.add(root.val);
            return;
        }
        if(root.left != null) addLeaves(root.left,ans);
        if(root.right != null) addLeaves(root.right,ans);
    }

    private void addRightBoundary(Node root, ArrayList<Integer> ans) {
        Node node = root.right;
        ArrayList<Integer> temp = new ArrayList<>();
        while (node != null){
            if(!isLeaf(node)) temp.add(node.val);
            if(node.right != null) node = node.right;
            else  node=node.left;
        }
        int n = temp.size();
        for (int i = 0; i < n; i++) {
            ans.add(temp.get(n-1-i));
        }
    }

    private void addLeftBoundary(Node root, ArrayList<Integer> ans) {
        Node node = root.left;
        while (node != null){
            if(!isLeaf(node)) ans.add(node.val);
            if(node.left != null) node=node.left;
            else node=node.right;
        }
    }

    private boolean isLeaf(Node node){
        return node.left == null && node.right == null;
    }

    // vertical order traversal- traverse column wise from left to right
    private class Pair{ // in each iteration we need 2 thing the node and the col at which it is
        int col;
        Node node;
        public Pair(int col, Node node){
            this.col = col;
            this.node = node;
        }
    }

    public List<ArrayList<Integer>> verticalOrderTraversal(Node root){
        Queue<Pair> q = new ArrayDeque<>();
        Map<Integer, ArrayList<Integer>> map = new TreeMap<>(); // treeMap stores keys in sorted order
        q.add(new Pair(0,root));
        while (!q.isEmpty()){
            Pair cur = q.poll(); // only diff is that poll doesn't throw exception if q is empty instead returns null
            if(map.containsKey(cur.col)){ // if an element of this col is present, then just add it in the list
                map.get(cur.col).add(cur.node.val);
            }else {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(cur.node.val);
                map.put(cur.col, temp);
            }

            // left child will be in previous col so -1
            if(cur.node.left != null) q.add(new Pair(cur.col-1, cur.node.left));
            if(cur.node.right != null) q.add(new Pair(cur.col+1, cur.node.right));

        }
        // add all the values from the Map into the arrayList
        List<ArrayList<Integer>> ans = new ArrayList<>();
        for(Map.Entry<Integer, ArrayList<Integer>> el : map.entrySet()){
            ans.add(el.getValue());
        }
        return ans;
    }

    // top-view of a binary tree - consider it a 2d binary tree and find out nodes visible form top
    public ArrayList<Integer> topView(Node root){
        ArrayList<Integer> ans =  new ArrayList<>();
        if(root == null){
            return ans;
        }
        Map<Integer,Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,root));
        while (!q.isEmpty()){
            Pair cur = q.poll();
            if(!map.containsKey(cur.col)){
                map.put(cur.col, cur.node.val);
            }
            if(cur.node.left != null) q.add(new Pair(cur.col-1, cur.node.left));
            if(cur.node.right != null) q.add(new Pair(cur.col+1, cur.node.right));
        }
        for(Map.Entry<Integer,Integer> e : map.entrySet()){
            ans.add(e.getValue());
        }
        return ans;
    }

 // bottom-view of a tree
    public ArrayList<Integer> bottomView(Node root){
        ArrayList<Integer> ans =  new ArrayList<>();
        if(root == null){
            return ans;
        }
        Map<Integer,ArrayList<Integer>> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,root));
        while (!q.isEmpty()){
            Pair cur = q.poll();
            ArrayList<Integer> temp = new ArrayList<>();
            if(!map.containsKey(cur.col)){
                temp.add(cur.node.val);
                map.put(cur.col, temp);
            }else {
                map.get(cur.col).add(cur.node.val);
            }
            if(cur.node.left != null) q.add(new Pair(cur.col-1, cur.node.left));
            if(cur.node.right != null) q.add(new Pair(cur.col+1, cur.node.right));
        }
        for(Map.Entry<Integer,ArrayList<Integer>> e : map.entrySet()){
            ans.add(e.getValue().get(e.getValue().size()-1)); // adds the last el from each arrayList of hashmap
        }
        return ans;
    }

    public ArrayList<Integer> bottomView2(Node root){
        ArrayList<Integer> ans =  new ArrayList<>();
        if(root == null){
            return ans;
        }
        Map<Integer,Integer> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,root));
        while (!q.isEmpty()){
            Pair cur = q.poll();
            map.put(cur.col, cur.node.val); // each time value of hashmap is updated because we want to store bottom node
            if(cur.node.left != null) q.add(new Pair(cur.col-1, cur.node.left));
            if(cur.node.right != null) q.add(new Pair(cur.col+1, cur.node.right));
        }
        for(Map.Entry<Integer,Integer> e : map.entrySet()){
            ans.add(e.getValue());
        }
        return ans;
    }

    // O(n) time , traversing each node , in worst case if it is skew binary tree
    // space is O(h+n) in arrayList we r storing h(height) nodes and in queue we r storing n nodes
    public List<ArrayList<Integer>> leftRightView(Node root){
        List<ArrayList<Integer>> ans = new ArrayList<>();
        if(root == null){
            return ans;
        }
        Queue<Node> q = new LinkedList<>();
        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        q.add(root);
        q.add(null);
        left.add(root.val);
        while (true){
            Node cur = q.poll();
            if(cur == null){
                if(!q.isEmpty()){
                    left.add(q.peek().val);
                    q.add(null);
                }else {
                    break;
                }
            }else {
                if(q.peek() == null){
                    right.add(cur.val);
                }
            }
            if(cur != null){
                if(cur.left != null) q.add(cur.left);
                if(cur.right != null) q.add(cur.right);
            }
        }
        ans.add(left);
        ans.add(right);
        return ans;
    }

    // recursive method to solve leftSide view
    // this will take O(n) time and O(H) space because we are only storing nodes of max height
    public ArrayList<Integer> leftSideView(Node root){
        ArrayList<Integer> ans= new ArrayList<>();
        leftSide(root,0,ans);
        return ans;
    }

    private void leftSide(Node root, int level, ArrayList<Integer> list){
        if(root == null) return;
        if(level == list.size()) list.add(root.val); // when we come first time at this level
        // swap below two lines for rightSideView
        leftSide(root.left, level+1,list);// as we r going below the level is increasing
        leftSide(root.right,level+1,list);
    }

    public boolean isSymmetric(Node root){
        // if root is null then it's symmetric
        return root == null || isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(Node left, Node right){
        // if both of them is null then it.s symmetric or if only one of them is null then it's assymmetric
        if(left == null || right == null){
            return left == right;
        }
        if(left.val != right.val) return false;
        return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
    }

    public ArrayList<Integer> rootToNode(Node root, int target){
        ArrayList<Integer> ans = new ArrayList<>();
        findPath(root,target,ans);
        return ans;
    }

    private boolean findPath(Node root, int target, ArrayList<Integer> ans){
        if(root == null){
            return false;
        }
        if(root.val == target){
            ans.add(root.val);
            return true;
        }
        ans.add(root.val);
        boolean path = findPath(root.left,target,ans) || findPath(root.right,target,ans);
        if(!path) ans.remove(ans.size()-1);
        return path;
    }

    // lowest common ancestor - most in-depth common node/parent
    public int LCA(int a, int b){
        if(root == null){
            return -1;
        }
        return LcaHelper(root, a, b).val;
    }

    private Node LcaHelper(Node root, int a, int b){
        if(root == null || root.val == a || root.val == b){
            return root;
        }
        Node left = LcaHelper(root.left,a,b);
        Node right = LcaHelper(root.right,a,b);
        if(left!=null && right!=null){
            return root;
        }
        if(left!=null){
            return left;
        }else {
            return right;
        }
    }

    public int widthOfBinaryTree(Node root){
        if(root == null) return 0;
        Queue<Pair> q = new LinkedList<>();
        int ans=0;
        q.add(new Pair(0,root)); // only root node will have index 0
        while (!q.isEmpty()){
            int size = q.size();
            int first=0, last=0;
            int minIndex = q.peek().col; // to ensure that indexing of each level starts from 1
            for (int i = 0; i < size; i++) {
                int curIndex = q.peek().col - minIndex;
                Node node = q.peek().node;
                q.poll();
                if(i==0) first= curIndex;
                if(i == size-1) last = curIndex;
                if(node.left != null) q.add(new Pair(curIndex*2+1,node.left));
                if(node.right != null) q.add(new Pair(curIndex*2+2,node.right));

            }
            ans = Math.max(ans,last-first+1);
        }
        return ans;
    }

    public void changeTree(Node root){ // children sum property
        if(root == null){
            return;
        }
        int left=0, right=0;
        if(root.left != null){
            left = root.left.val;
        }
        if(root.right != null){
            right = root.right.val;
        }
        if(left + right < root.val){
            if(root.left != null){
                root.left.val = root.val;
            }
            if(root.right != null){
                root.right.val = root.val;
            }
        }
        changeTree(root.left);
        changeTree(root.right);
        if(root.left != null){
            left = root.left.val;
        }
        if(root.right != null){
            right = root.right.val;
        }
        if (root.left!=null || root.right!=null)
        root.val = left+right;
    }

    private Node findNode(Node root, int target){
        if(root == null || root.val == target){
            return root;
        }
        Node left = findNode(root.left,target);
        Node right = findNode(root.right,target);
        if(left != null) return left;
        return right;
    }

    // print all the nodes at distance K
    public List<Integer> distanceK(Node root, int target, int k){
        Map<Node, Node> parent_track = new HashMap<>();
        markParents(root,parent_track);
        Map<Node, Boolean> visited = new HashMap<>();
        Node node = findNode(root, target);
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        int cur_level = 0;
        visited.put(node,true);
        while (!q.isEmpty()){
            int size=q.size();
            if(cur_level == k) break;
            cur_level++;
            for (int i = 0; i < size; i++) {
                Node cur= q.poll();
                if(cur.left!=null && visited.get(cur.left) == null){
                    q.add(cur.left);
                    visited.put(cur.left,true);
                }
                if(cur.right!=null && visited.get(cur.right) == null){
                    q.add(cur.right);
                    visited.put(cur.right,true);
                }
                if(parent_track.get(cur) != null && visited.get(parent_track.get(cur)) == null){
                    q.add(parent_track.get(cur));
                    visited.put(parent_track.get(cur),true);
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()){
            ans.add(q.poll().val);
        }
        return ans;
    }
    // we can find the childs of each nodes but we can't find the parents of each nodes by traversing backward
    private void markParents(Node root, Map<Node, Node> parent_track){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
            Node cur = q.poll();
            if(cur.left != null){
                q.add(cur.left);
                // we will put child in key because each child will have only one parent but 1 parent can have 2 child
                parent_track.put(cur.left,cur);
            }
            if(cur.right != null){
                q.add(cur.right);
                parent_track.put(cur.right,cur);
            }
        }
    }

}

public class BinaryTree02 {
    public static void main(String[] args) {
//        int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};
//        int[] nodes = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, -1};
        int[] nodes = {1, 2, 4, -1, -1, 5, -1, 7,-1, -1, 3, -1, 6, -1, -1};
//        int[] nodes = {1,2,3,-1,5,-1,-1,4,-1,-1,2,4,-1,-1,3,5,-1,-1,-1};
        BinaryTree1 tree = new BinaryTree1();
        tree.buildTree(nodes);
        tree.levelOrder(tree.root);
        System.out.println();
        System.out.println(tree.widthOfBinaryTree(tree.root));
//        System.out.println(tree.distanceK(tree.root,2,1));
//        tree.changeTree(tree.root);
//        tree.levelOrder(tree.root);
//        System.out.println(tree.LCA(4,71));
//        System.out.println(tree.rootToNode(tree.root,7));
//        System.out.println(tree.isSymmetric(tree.root));
//        System.out.println(tree.leftSideView(tree.root));
//        System.out.println(tree.leftRightView(tree.root));
//        System.out.println(tree.bottomView2(tree.root));
//        System.out.println(tree.topView(tree.root));
//        System.out.println(tree.verticalOrderTraversal(tree.root));
//        tree.boundaryTraversal(tree.root);
//        System.out.println(tree.zigZagTraversal(tree.root));
//        System.out.println(tree.maxPathSum());
    }
}
