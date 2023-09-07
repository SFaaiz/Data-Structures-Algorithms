//package Graph;
//
//import java.util.*;
//
//public class Graph {
//    static void print(ArrayList<ArrayList<Integer>> adj){
//        for(int i=1; i<adj.size(); i++){
//            System.out.print(i + " -> ");
//            for(int j=0; j<adj.get(i).size(); j++){
//                System.out.print(adj.get(i).get(j) + " ");
//            }
//            System.out.println();
//        }
//    }
//
//    // level order or bfs
//    // 1 is like root node. nodes 1 edge away is at level 1, 2 edge away is at level 2 and so on
//    static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj){
//        Set<Integer> visited = new HashSet<>();
//        Queue<Integer> q = new LinkedList();
//        q.add(1);
//        visited.add(1);
//        ArrayList<Integer> res = new ArrayList<>();
//        while (!q.isEmpty()){
//            int node = q.poll();
//            res.add(node);
//            for(int i : adj.get(node)){
//                if(!visited.contains(i)){
//                    q.add(i);
//                    visited.add(i);
//                }
//            }
//        }
//        return res;
//    }
//
//    // TC :- dfs will be called o(n) times for each node tobe marked visited
//    // then each method call will run forloop for each of its neighbouring nodes, so 2 * edges , 1->2, 2->1
//    // TC is o(n) + 2*E
//    private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, Set<Integer> visited, ArrayList<Integer> res){
//        res.add(node);
//        visited.add(node);
//        for(int i : adj.get(node)){
//            if(!visited.contains(i)){
//                dfs(i, adj, visited, res);
//            }
//        }
//    }
//
//    // going in depth, goto anyone child of the given node, then to the child of that node until possible and then come out
//    static ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj){
//        ArrayList<Integer> res = new ArrayList<>();
//        Set<Integer> visited = new HashSet<>();
//        dfs(1,adj, visited, res);
//        return res;
//    }
//
//    // u r given a structure of multiple graphs, they r not connected to each other so figure out no. of graphs
//    // the nodes of graphs starts from 1 to n
//    static int NoOfProvinces(ArrayList<ArrayList<Integer>> adj) {
//        int n = adj.size();
//        Set<Integer> visited = new HashSet<>();
//        int cnt = 0;
//        for(int i=1; i<n; i++){
//            if(!visited.contains(i)){
//                dfs(i,adj,visited);
//                cnt++;
//            }
//        }
//        return cnt;
//    }
//
//    private static void dfs(int node, ArrayList<ArrayList<Integer>> adj, Set<Integer> visited){
//        visited.add(node);
//        for(int i: adj.get(node)){
//            if(!visited.contains(i)){
//                dfs(i, adj, visited);
//            }
//        }
//    }
//
//    static class Pair{
//        int r, c;
//        public Pair(int r, int c){
//            this.r=r;
//            this.c=c;
//        }
//    }
//    private boolean isValid(int r, int c, char[][] grid){
//        int n = grid.length;
//        int m = grid[0].length;
//        if(r>=0 && c>=0 && r<n && c<m) return true;
//        return false;
//    }
//    private void bfs(int r, int c, int[][] vis, char[][] grid){
//        vis[r][c] = 1;
//        ArrayList<Pair> dir = new ArrayList<>();
//        dir.add(new Pair(-1,0));
//        dir.add(new Pair(0,-1));
//        dir.add(new Pair(0,1));
//        dir.add(new Pair(1,0));
//
//        Queue<Pair> q = new LinkedList<>();
//        q.add(new Pair(r,c));
//
//        while(!q.isEmpty()){
//            Pair p1 = q.poll();
//            for(Pair p2: dir){
//                int nr = p1.r+p2.r;
//                int nc = p1.c+p2.c;
//                if(isValid(nr,nc,grid)){
//                    if(grid[nr][nc]=='1' && vis[nr][nc]==0){
//                        vis[nr][nc]=1;
//                        q.add(new Pair(nr,nc));
//                    }
//                }
//            }
//        }
//    }
//    // no. of islands, grid representing '1' as land and '0' as water
//    public int numIslands(char[][] grid) {
//        int n= grid.length;
//        int m= grid[0].length;
//        int[][] vis = new int[n][m];
//        int cnt=0;
//        for(int i=0; i<n; i++){
//            for(int j=0; j<m; j++){
//                if(grid[i][j]=='1' && vis[i][j]==0){
//                    bfs(i,j,vis,grid);
//                    cnt++;
//                }
//            }
//        }
//        return cnt;
//    }
//
//    static boolean isValid(int r, int c, int[][] grid){
//        int n = grid.length;
//        int m = grid[0].length;
//        if(r>=0 && c>=0 && r<n && c<m) return true;
//        return false;
//    }
//    static int[][] floodFill(int[][] image, int sr, int sc, int color) {
//        int initClr = image[sr][sc];
//        int[][] ans = image;
//        dfs(sr,sc,initClr,color,ans,image);
//        return ans;
//    }
//    private static void dfs(int r, int c, int initClr, int newClr, int[][]ans, int[][] img){
//        ans[r][c] = newClr;
//        int[] rDir = {-1,0,1,0};
//        int[] cDir = {0,1,0,-1};
//        for(int i=0; i<4; i++){
//            int nr = r+rDir[i];
//            int nc = c+cDir[i];
//            if(isValid(nr,nc,ans)){
//                if(img[nr][nc]==initClr){
//                    dfs(nr,nc,initClr,newClr,ans,img);
//                }
//            }
//        }
//    }
//
//    //time required to rot all the oranges
//
//    static int orangesRotting(int[][] grid) {
//        int fresh=0;
//        Queue<Pair> q = new LinkedList<>();
//        for(int i=0; i<grid.length; i++){
//            for(int j=0; j<grid[i].length; j++){
//                if(grid[i][j]==1) fresh++;
//                if(grid[i][j]==2) q.add(new Pair(i,j));
//            }
//        }
//        int[] rno = {-1,0,1,0};
//        int[] cno = {0,1,0,-1};
//        int rotten=0;
//        int time=0;
//        while(!q.isEmpty()){
//            int n = q.size();
//            boolean rot=false;
//            for(int i=0; i<n; i++){
//                Pair p = q.poll();
//                for(int j=0; j<4; j++){
//                    int nr = p.r + rno[j];
//                    int nc = p.c + cno[j];
//                    if(isValid(nr,nc,grid)){
//                        if(grid[nr][nc]==1){
//                            grid[nr][nc]=2;
//                            q.add(new Pair(nr,nc));
//                            rotten++;
//                            rot=true;
//                        }
//                    }
//                }
//            }
//            if(rot) time++;
//        }
//        return (fresh==rotten) ? time : -1;
//    }
//
//    private static boolean detect(ArrayList<ArrayList<Integer>> adj, int node, HashMap<Integer, Integer> parent){
//        Queue<Integer> q = new LinkedList<>();
//        q.add(node);
//        parent.put(node, -1); // starting from any random node, it's the first node we haven't come here thru any node so take -1
//        while(!q.isEmpty()){
//            int n = q.poll();
//            for(int i: adj.get(n)){
//                if(!parent.containsKey(i)){
//                    parent.put(i, n); // mark it as visited & current node that connects to it is it's parent
//                    q.add(i);
//                }
//                else if(parent.get(n) != i) return true; // if the node that we want to go to is already visited by someone else
//            }                                   // and we haven't come through that node then it means that a cycle it present
//        }
//        return false;
//    }
//    // Function to detect cycle in an undirected graph.
//    static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
//        HashMap<Integer, Integer> visited = new HashMap<>(); // node vs it's parent(from where we reached that node)
//        for(int i=0; i<V; i++){
//            if(!visited.containsKey(i)){ // if it's not visited
//                if(detect(adj, i, visited)){ //then detect if a cycle starts from it
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    //Function to find distance of nearest 1 in the grid for each cell.
//    static int[][] nearest(int[][] grid){
//        int n= grid.length;
//        int m= grid[0].length;
//        int[][] visited= new int[n][m];
//        int[][] ans = grid;
//        Queue<Pair> q = new LinkedList<>();
//        for(int i=0; i<n; i++){
//            for(int j=0; j<m; j++){
//                if(ans[i][j]==1){
//                    q.add(new Pair(i,j));
//                    visited[i][j]=1;
//                    ans[i][j] = 0;
//                }
//            }
//        }
//        int[] rno = {-1,0,1,0};
//        int[] cno = {0,1,0,-1};
//        while (!q.isEmpty()){
//            Pair p =q.poll();
//            int r=p.r;
//            int c=p.c;
//            for(int i=0; i<4; i++){
//                int nr= r+rno[i];
//                int nc= c+cno[i];
//                if(isValid(nr,nc,ans)){
//                    if(visited[nr][nc]==0){
//                        ans[nr][nc] = ans[r][c]+1;
//                        visited[nr][nc]=1;
//                        q.add(new Pair(nr,nc));
//                    }
//                }
//            }
//        }
//        return ans;
//    }
//
//    private static boolean isBoundary(int r, int c, char[][] board){
//        if(r==0 || c==0 || r==board.length-1 || c==board[0].length-1) return true;
//        return false;
//    }
//    private static void dfs(int r, int c, char[][] grid, int[][] vis){
//        vis[r][c] = 1;
//        int[] rno = {-1,0,1,0};
//        int[] cno = {0,1,0,-1};
//        for(int i=0; i<4; i++){
//            int nr = r+rno[i];
//            int nc = c+cno[i];
//            if(nr>=0 && nc>=0 && nr<grid.length && nc<grid[0].length){
//                if(vis[nr][nc]==0 && grid[nr][nc] == 'O'){
//                    vis[nr][nc] = 1;
//                    dfs(nr,nc,grid,vis);
//                }
//            }
//        }
//    }
//    // if a 'O' is surrounded 4 directionally by 'X' then it can be flipped to 'X'
//    // flip all the possible O's
//    static void surroundedRegions(char[][] board) {
//        int n= board.length;
//        int m= board[0].length;
//        int[][] vis = new int[n][m];
//        for(int i=0; i<n; i++){
//            for(int j=0; j<m; j++){
//                if(board[i][j]=='O' && isBoundary(i,j,board)){
//                    dfs(i,j,board,vis);
//                }
//            }
//        }
//        for(int i=0; i<n; i++){
//            for(int j=0; j<m; j++){
//                if(vis[i][j]==0) board[i][j]='X';
//            }
//        }
//    }
//
//    private static boolean check(int node,  ArrayList<ArrayList<Integer>>adj, int[] color){
//        color[node] = 0;
//        Queue<Integer> q = new LinkedList<>();
//        q.add(node);
//        while(!q.isEmpty()){
//            node = q.poll();
//            for(int i: adj.get(node)){
//                if(color[i]==-1){
//                    color[i] = 1-color[node];
//                    q.add(i);
//                }
//                else if(color[i]==color[node]) return false;
//            }
//        }
//        return true;
//    }
//    // solve it on GFG
//    // graph should be colored with 2 colors only
//    // no adjacent node in the graph should have same color, if it's possible return else return false
//    static boolean isBipartite(int V, ArrayList<ArrayList<Integer>>adj)
//    {
//        int[] color = new int[V];
//        Arrays.fill(color, -1);
//
//        for(int i=0; i<V; i++){
//            if(color[i]==-1){
//                if(!check(i,adj,color)) return false;
//            }
//        }
//        return true;
//    }
//
//    static boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
//        Set<Integer> visited = new HashSet<>();
//        Set<Integer> pathVisited = new HashSet<>();
//        for(int i=0; i<V; i++){
//            if(!visited.contains(i))
//                if(dfs(i,adj,visited,pathVisited)){
//                    return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean dfs(int node, ArrayList<ArrayList<Integer>> adj, Set<Integer> visited, Set<Integer> pathVisited){
//        visited.add(node);
//        pathVisited.add(node);
//        for(int i: adj.get(node)){
//            if(!visited.contains(i)){
//                if(dfs(i,adj,visited,pathVisited)) return true;
//            }
//            else if(pathVisited.contains(i)) return true;
//        }
//        pathVisited.remove(node);
//        return false;
//    }
//
//    // return all safe nodes in ascending order, safe nodes r the ones whose all paths leads to a terminal node
//    // terminal node = it does not have any outdegree
//    static List<Integer> eventualSafeNodes(int[][] graph) {
//        Set<Integer> visited = new HashSet<>();
//        Set<Integer> pathVis = new HashSet<>();
//        int n = graph.length;
//        List<Integer> ans = new ArrayList<>();
//        for(int i=0; i<n; i++){
//            if(!visited.contains(i)){
//                if(dfs(i,graph,visited,pathVis)) ans.add(i);
//            }
//            else if(!pathVis.contains(i)) ans.add(i);
//        }
//        return ans;
//    }
//
//    private static boolean dfs(int node, int[][] graph, Set<Integer> visited, Set<Integer> pathVis){
//        visited.add(node);
//        pathVis.add(node);
//        for(int i: graph[node]){
//            if(!visited.contains(i)){
//                if(!dfs(i,graph,visited,pathVis)) return false;
//            }
//            else if(pathVis.contains(i)) return false;
//        }
//        pathVis.remove(node);
//        return true;
//    }
//
//    // it always works in DAG(directed acyclic graph)
//    // topological sorting gives such an order where if u->v (u points to v) then u will always appear before v in topologcal list
////    Intuition of algo:- those nodes which can't go to any other node will be put into the stack first and those who can
////    will be put later so in such a way we will put for a->b, b first then a into the stack and get answer as (a,b) after popping
//    public int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj)
//    {
//        int[] ans = new int[V];
//        Stack<Integer> st = new Stack<>();
//        Set<Integer> vis = new HashSet<>();
//        for(int i=0; i<V; i++){
//            if(!vis.contains(i)) dfs(i,st,vis,adj);
//        }
//        for(int i=0; i<V; i++){
//            ans[i]= st.pop();
//        }
//        return ans;
//    }
//
//    private void dfs(int n, Stack<Integer> st, Set<Integer> vis, ArrayList<ArrayList<Integer>> adj){
//        vis.add(n);
//        for(int i: adj.get(n)){
//            if(!vis.contains(i)) dfs(i,st,vis,adj);
//        }
//        st.push(n);
//    }
//
//    // kahn's algorithm - it's toposort using BFS
//    // idea - those having indegree=0 will be put first then their outdegree will be reduced
//    // those whose indegree becomes 0 will be put and so on
//    static int[] topoSortBFS(int V, ArrayList<ArrayList<Integer>> adj)
//    {
//        // BFS
//        int[] indegree = new int[V];
//        for(ArrayList<Integer> i: adj){
//            for(int j: i){
//                indegree[j]++;
//            }
//        }
//        Queue<Integer> q = new LinkedList<>();
//        Set<Integer> zero = new HashSet<>();
//        for(int i=0; i<V; i++) if(indegree[i]==0) zero.add(i);
//        Iterator<Integer> it = zero.iterator();
//        while(it.hasNext()) q.add(it.next());
//        int i=0;
//        int[] ans= new int[V];
//        while(!q.isEmpty()){
//            int n = q.poll();
//            for(int j: adj.get(n)){
//                indegree[j]--;
//                if(indegree[j]==0) q.add(j);
//            }
//            ans[i] = n;
//            i++;
//        }
//        return ans;
//    }
//
//    // GFG
//    // return an array whose each index contains shortest path to that index from src index
//    public int[] shortestPath(int[][] edges,int n,int m ,int src) {
//        // creating adjcency list
//        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
//        for(int i=0; i<n; i++) adj.add(new ArrayList<>());
//        for(int i=0; i<edges.length; i++){
//            adj.get(edges[i][0]).add(edges[i][1]);
//            adj.get(edges[i][1]).add(edges[i][0]);
//        }
//
//        Queue<Integer> q = new LinkedList<>();
//        int[] dist = new int[n];
//        Arrays.fill(dist,-1); // if the path is unreachable then it'll be -1
//        q.add(src);
//        dist[src] = 0; // dist from src to src will always be 0
//        int d=0;
//        while(!q.isEmpty()){
//            int s = q.size();
//            for(int j=0; j<s; j++){
//                int k = q.poll();
//                for(int i: adj.get(k)){
//                    if(dist[i]==-1){
//                        dist[i]=d+1;
//                        q.add(i);
//                    }
//                    else if(dist[i]>d+1) dist[i]=d+1;
//                }
//            }
//            d++;
//        }
//        return dist;
//    }
//
//    // return min no. of steps needed to transform beginWord to endWord, only one char can be changed at each step
//    // word can only be changed to those words that r present in wordList
//    static int ladderLength(String beginWord, String endWord, List<String> wordList) {
//        Set<String> set = new HashSet<>(wordList); // insert all list elements int set
//        if(!set.contains(endWord)) return 0;
//        int steps=1;
//        Queue<String> q = new LinkedList<>();
//        q.add(beginWord);
//        set.remove(beginWord);
//        int l = beginWord.length();
//        while(!q.isEmpty()){
//            int n=q.size();
//            for(int i=0; i<n; i++){
//                String word = q.poll();
//                for(int j=0; j<l; j++){
//                    for(char ch='a'; ch<='z'; ch++){
//                        String st = word.substring(0,j) + ch + word.substring(j+1,l);
//                        if(st.equals(endWord)) return steps+1;
//                        if(set.contains(st)) {
//                            q.add(st);
//                            set.remove(st);
//                        }
//                    }
//                }
//            }
//            steps++;
//        }
//        return 0;
//    }
//
//    static class Pair2{
//        int dist, node;
//        public Pair2(int d, int n){
//            this.dist = d;
//            this.node = n;
//        }
//    }
//    //Function to find the shortest distance of all the vertices
//    //from the source vertex S.
//    // arraylist->arraylist of nodes-> each arraylist contains (node,weight)
//    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S)
//    {
//        int[] dist = new int[V];
//        // min heap - PQ (to access shortest distanced element first to avoid unneccessary processing)
//        PriorityQueue<Pair2> pq = new PriorityQueue<>((x,y) -> x.dist - y.dist);
//        Arrays.fill(dist,Integer.MAX_VALUE);
//        pq.add(new Pair2(0,S));
//        dist[S] = 0;
//        while(!pq.isEmpty()){
//            Pair2 p = pq.remove();
//            int node = p.node;
//            int dis = p.dist;
//            for(ArrayList<Integer> i: adj.get(node)){
//                int d = dis+i.get(1);
//                if(dist[i.get(0)] > d){
//                    dist[i.get(0)] = d;
//                    pq.add(new Pair2(d, i.get(0)));
//                }
//            }
//        }
//        for(int i=0; i<V; i++) if(dist[i]==Integer.MAX_VALUE) dist[i]=-1;
//        return dist;
//    }
//
//    // GFG
//    static class Pair3{
//        int d, n;
//        public Pair3(int d, int n){
//            this.d=d;
//            this.n=n;
//        }
//    }
//
//    public static List<Integer> shortestPath(int n, int m, int edges[][]) {
//        // creating adjacency list
//        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();
//        for(int i=0; i<=n; i++) adj.add(new ArrayList<>());
//        for(int i=0; i<edges.length; i++){
//            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(edges[i][1],edges[i][2]));
//            adj.get(edges[i][0]).add(list);
//            ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(edges[i][0],edges[i][2]));
//            adj.get(edges[i][1]).add(list2);
//        }
//        // min-heap
//        PriorityQueue<Pair3> pq = new PriorityQueue<>((x,y) -> x.d-y.d);
//        int[] dist = new int[n+1];
//        int[] parent = new int[n+1]; // stores parent of each node at corresponding index
//        parent[1]=1; // 1 is the src node so it has no parent
//        Arrays.fill(dist,Integer.MAX_VALUE);
//        dist[1]=0;
//        pq.add(new Pair3(0,1));
//        while(!pq.isEmpty()){
//            Pair3 p= pq.remove();
//            int d = p.d;
//            int node = p.n;
//            for(ArrayList<Integer> l : adj.get(node)){
//                int td = d+l.get(1);
//                if(dist[l.get(0)]>td) {
//                    dist[l.get(0)] = td;
//                    pq.add(new Pair3(td,l.get(0)));
//                    parent[l.get(0)] = node;
//                }
//            }
//        }
//        List<Integer> res = new ArrayList<>();
//        if(dist[n]==Integer.MAX_VALUE){
//            res.add(-1);
//            return res;
//        }
//        int node = n;
//        while(node!=1){
//            res.add(node);
//            node = parent[node];
//        }
//        res.add(1);
//        Collections.reverse(res);
//        return res;
//    }
//
//    static class Pairs{
//        int r,c;
//        public Pairs(int r, int c){
//            this.r=r;
//            this.c=c;
//        }
//    }
//    private static boolean isValid1(int r, int c, int[][] grid){
//        if(r>=0 && c>=0 && r<grid.length && c<grid.length && grid[r][c]==0) return true;
//        return false;
//    }
//    static int shortestPathBinaryMatrix(int[][] grid) {
//        int n = grid.length;
//        if(grid[0][0]==1 || grid[n-1][n-1]==1) return -1;
//        int[][] paths = {{-1,-1},{-1,0},{-1,1},{0,1},{1,-1},{1,0},{1,1}};
//        Queue<Pairs> q = new LinkedList<>();
//        q.add(new Pairs(0,0));
//        int steps=Integer.MAX_VALUE;
//        int stp=1;
//        while(!q.isEmpty()){
//            int s=q.size();
//            for(int i=0; i<s; i++){
//                Pairs p = q.poll();
//                int r=p.r;
//                int c=p.c;
//                if(r==n-1 && c==n-1 && stp<steps){
//                    steps=stp;
//                }
//                int pl = paths.length;
//                for(int j=0; j<pl; j++){
//                    int nr = r+paths[j][0];
//                    int nc = c+paths[j][1];
//                    if(isValid1(nr,nc,grid)){
//                        q.add(new Pairs(nr,nc));
//                    }
//                }
//            }
//            stp++;
//        }
//        return (steps==Integer.MAX_VALUE) ? -1 : steps;
//    }
//
//    private static boolean isValid9(int r, int c, int[][] grid){
//        if(r>=0 && c>=0 && r<grid.length && c<grid.length && grid[r][c]==0) return true;
//        return false;
//    }
////    public static int shortestPathBinaryMatrix(int[][] grid) {
////        int n = grid.length;
////        if(grid[0][0]==1 || grid[n-1][n-1]==1) return -1;
////        int[][] paths = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
////        int[] ans = new int[1];
////        ans[0] = Integer.MAX_VALUE;
////        dfs(0,0,paths,grid,1,ans);
////        return (ans[0]==Integer.MAX_VALUE) ? -1 : ans[0];
////    }
//
//    public static void dfs(int r, int c, int[][] paths, int[][] grid, int steps, int[] ans){
//        int n=grid.length;
//        if(r==n-1 && c==n-1){
//            if(steps<ans[0]) ans[0] = steps;
//            return;
//        }
//        grid[r][c] = 1;
//        int pl = paths.length;
//        for(int j=0; j<pl; j++){
//            int nr = r+paths[j][0];
//            int nc = c+paths[j][1];
//            if(isValid9(nr,nc,grid)){
//                dfs(nr,nc,paths,grid,steps+1,ans);
//                grid[nr][nc] = 0;
//            }
//        }
//    }
//
//    class Tuple{
//        int d, r, c;
//        public Tuple(int d, int r, int c){
//            this.d=d;
//            this.r=r;
//            this.c=c;
//        }
//    }
////    private boolean isValid(int r, int c, int[][] grid){
////        if(r>=0 && c>=0 && r<grid.length && c<grid[0].length) return true;
////        return false;
////    }
//    // leetcode
//    //minimum effort req to go from 0,0 to n-1,m-1
//    public int minimumEffortPath(int[][] heights) {
//        // we will use dijkstra's algorithm to access the nearest cell first
//        // for that we will use PQ
//        PriorityQueue<Tuple> pq = new PriorityQueue<>((x,y) -> x.d-y.d);
//        int n=heights.length;
//        int m=heights[0].length;
//        int[][] dist = new int[n][m];
//        for(int[] i: dist){
//            Arrays.fill(i,Integer.MAX_VALUE);
//        }
//        int[] dr = {-1,0,1,0};
//        int[] dc = {0,1,0,-1};
//        dist[0][0]=0;
//        pq.add(new Tuple(0,0,0));
//        while(!pq.isEmpty()){
//            Tuple t = pq.remove();
//            int d=t.d, r=t.r, c=t.c;
//            for(int i=0; i<4; i++){
//                int nr = r+dr[i], nc = c+dc[i];
//                if(isValid(nr,nc,heights)){
//                    int effort = Math.abs(heights[r][c]-heights[nr][nc]);
//                    int total = Math.max(d,effort);
//                    if(dist[nr][nc] > total){
//                        dist[nr][nc] = total;
//                        pq.add(new Tuple(total,nr,nc));
//                    }
//                }
//            }
//        }
//        return dist[n-1][m-1];
//    }
//
//    //leetcode
//    class Pair{
//        int n, c;
//        public Pair(int n, int c){
//            this.n=n;
//            this.c=c;
//        }
//    }
//    class Tuple{
//        int stops, node, cost;
//        public Tuple(int s, int n, int c){
//            stops=s;
//            node=n;
//            cost=c;
//        }
//    }
//    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
//        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
//        for(int i=0; i<n; i++) adj.add(new ArrayList<>());
//        for(int i=0; i<flights.length; i++){
//            adj.get(flights[i][0]).add(new Pair(flights[i][1], flights[i][2]));
//        }
//        Queue<Tuple> q = new LinkedList<>();
//        int[] cost = new int[n];
//        Arrays.fill(cost,Integer.MAX_VALUE);
//        cost[src]=0;
//        //stops, node, cost
//        q.add(new Tuple(0,src,0));
//        while(!q.isEmpty()){
//            Tuple t = q.poll();
//            int c=t.cost, stops=t.stops, node=t.node;
//            if(stops>k) continue;
//            for(Pair i: adj.get(node)){
//                int nc=c+i.c, nn=i.n;
//                if(nc<cost[nn]){
//                    cost[nn]=nc;
//                    q.add(new Tuple(stops+1,nn,nc));
//                }
//            }
//        }
//        return (cost[dst]==Integer.MAX_VALUE) ? -1 : cost[dst];
//    }
//
//    // same as dijkstra's algo but it also works for negative weights
//    // works in DG with positive cycles, if sum of weight of cycle comes out to be negative then it won't work
//    // if sum of cycle is -1 then in next iteration it will be -2 and so on and we will keep getting shorter distance
//    static int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
//        int[] dist = new int[V];
//        Arrays.fill(dist, (int)(1e8));
//        dist[S] = 0;
//        for(int i=0; i<V-1; i++){
//            for(ArrayList<Integer> j: edges){ // each arraylist represents and edge (u, v, wt)
//                int u = j.get(0);
//                int v = j.get(1);
//                int wt = j.get(2);
//                if(dist[u] != 1e8 && dist[u] + wt < dist[v]){ // 1e8 minus negative wt will give wrong distance
//                    dist[v] = dist[u] + wt;
//                }
//            }
//        }
//        // after doing NxM iterations, distance till all the nodes should be minimum, n-1 nodes and m edges
//        // now we will do one more iteration and if we find another minimum distance for any node
//        // then it means a cycle with negative weight(negative cycle) exists
//        for(ArrayList<Integer> j: edges){
//            int u = j.get(0);
//            int v = j.get(1);
//            int wt = j.get(2);
//            if(dist[u] != 1e8 && dist[u] + wt < dist[v]){
//                return new int[]{-1};
//            }
//        }
//        return dist;
//    }
//
//    // it's a multi-source shortest path algo, determines the shortest path from every node to every other node
//    public void floyd_warshall(int[][] matrix)
//    {
//        int n = matrix.length;
//        for(int i=0; i<n; i++){
//            for(int j=0; j<n; j++){
//                if(matrix[i][j]==-1) matrix[i][j]=(int)(1e8);
//            }
//        }
//        for(int k=0; k<n; k++){
//            for(int i=0; i<n; i++){
//                for(int j=0; j<n; j++){
//                    matrix[i][j] = Math.min(matrix[i][j],matrix[i][k]+matrix[k][j]);
//                }
//            }
//        }
//        for(int i=0; i<n; i++){
//            for(int j=0; j<n; j++){
//                if(matrix[i][j]==1e8) matrix[i][j]=-1;
//            }
//        }
//    }
//
//    // find a node which has least no. of reachable nodes within given distance
//    int findCity(int n, int m, int[][] edges,int distanceThreshold)
//    {
//        // creating adjacency matrix array
//        int[][] dist = new int[n][n];
//        for(int[] i: dist) Arrays.fill(i,(int)(1e8));
//        for(int i=0; i<edges.length; i++){
//            dist[edges[i][0]][edges[i][1]] = edges[i][2];
//            dist[edges[i][1]][edges[i][0]] = edges[i][2];
//        }
//        // applying warshall's algo to find shortest paths
//        for(int k=0; k<n; k++){
//            for(int i=0; i<n; i++){
//                for(int j=0; j<n; j++){
//                    dist[i][j] = Math.min(dist[i][j],dist[i][k]+dist[k][j]);
//                }
//            }
//        }
//        // a nodes' path to itself is 0
//        for(int i=0; i<n; i++) dist[i][i]=0;
//        int[] city = {-1,(int)(1e8)}; // node and it's reachable nodes
//        for(int i=0; i<n; i++){
//            int nbr = 0;
//            for(int j=0; j<n; j++){
//                if(dist[i][j]<=distanceThreshold) nbr++; // if distance is in bounds
//            }
//            if(nbr<=city[1]) { //if 2 nodes have same distance then greater node will be answer
//                city[1]=nbr;
//                city[0]=i;
//            }
//        }
//        return city[0];
//    }
//
//    static class Tuple{
//        int d, n, p;
//        public Tuple(int d, int n, int p){
//            this.d=d;
//            this.n=n;
//            this.p=p;
//        }
//    }
//    static class Pair{
//        int n, d;
//        public Pair(int n, int d){
//            this.d=d;
//            this.n=n;
//        }
//    }
//    // Prim's Algo
//    // minimum spanning tree:- Intuition:- we will visit n nodes by marking it as visited and will use PQ to get the nearest
//    // neighbor first, so through all the nearest neighbours we will visit all the nodes and mark them visited
//    static int spanningTree(int V, int E, int edges[][]){
//        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
//        for(int i=0; i<V; i++) adj.add(new ArrayList<>());
//        for(int[] i: edges){
//            adj.get(i[0]).add(new Pair(i[1],i[2]));
//            adj.get(i[1]).add(new Pair(i[0],i[2]));
//        }
//        PriorityQueue<Tuple> pq= new PriorityQueue<>((x,y)->x.d-y.d);
//        pq.add(new Tuple(0,0,-1));
//        int[] vis = new int[V];
//        int sum=0;
//        while(!pq.isEmpty()){
//            Tuple t = pq.remove();
//            int node=t.n;
//            if(vis[t.n]==0){
//                sum+=t.d;
//                for(Pair p: adj.get(node)){
//                    pq.add(new Tuple(p.d,p.n,node));
//                }
//            }
//            vis[node]=1;
//        }
//        return sum;
//    }
//    public static void main(String[] args) {
//        int v = 6;
//        // graph with 3 nodes/vertices
//        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(v);
//
//        // adding empty list to the graph
//        for(int i=0; i<=v; i++) adj.add(new ArrayList<>());
//
//        adj.get(5).add(3);
//        adj.get(3).add(1);
//        adj.get(1).add(2);
//        adj.get(2).add(4);
//        adj.get(4).add(0);
//
//        System.out.println(isCyclic(6,adj));
//
//        // node 1 has neighbours 2 and 3
////        adj.get(1).add(2);
////        adj.get(1).add(3);
////
////        // it's an undirected/bidirectional graph (1->2 and 2->1)
////        adj.get(2).add(1);
////        adj.get(2).add(4);
////
////        adj.get(3).add(1);
////        adj.get(3).add(4);
////        adj.get(3).add(5);
////
////        adj.get(4).add(2);
////        adj.get(4).add(3);
////        adj.get(4).add(5);
////
////        adj.get(5).add(3);
////        adj.get(5).add(4);
//
//        // printing the graph
//        print(adj);
//
//        System.out.println(bfs(adj));
//        System.out.println();
//
//        System.out.println(dfs(adj));
//        System.out.println();
//
//        int[][] grid = {{0,1,1,0},
//                {1,1,0,0},
//                {0,0,1,1}};
//        int[][] ans = nearest(grid);
//        for(int[] i: ans) System.out.println(Arrays.toString(i));
//
//
////        System.out.println(NoOfProvinces(adj));
////        System.out.println();
//
////        int[][] grid = {{2,1,1},
////                {1,1,0},
////                {0,1,1}};
////        System.out.println(orangesRotting(grid));
//
//
////        int[][] img = {{1,1,1},
////                       {1,1,0},
////                       {1,0,1}};
////        int[][] ans = floodFill(img,1,1,2);
////        for(int[] i: ans){
////            for(int j: i){
////                System.out.print(j + " ");
////            }
////            System.out.println();
////        }
//        System.out.println();
//
////        int[][] matrix = {
////                {0,0,0},
////                {1,1,0},
////                {1,1,0}
////        };
////        System.out.println(shortestPathBinaryMatrix(matrix));
//
////        int[][] g = {
////                {}
////        }
//
//    }
//}
