package Graph;

import java.util.*;

class DisjointSet{
    // intially everyone will be their own parent
    ArrayList<Integer> parent = new ArrayList<>();
    // initially everyone's rank will be zero
    ArrayList<Integer> rank = new ArrayList<>();
    // initially everyone's size will be 1 i.e. their own
    ArrayList<Integer> size = new ArrayList<>();
    public DisjointSet(int n){
        for(int i=0; i<=n; i++){
            parent.add(i);
            rank.add(0);
            size.add(1);
        }
    }

    // of a given node, find it's ultimate parent(root)
    public int findUlPar(int node){
        if(node == parent.get(node)) return node; // of root node, it is the parent of itself
        int ulp = findUlPar(parent.get(node));
        parent.set(node,ulp);   // mark ultimate_parent as everyone's parent
        return ulp;
    }

    // join 2 nodes from different components as per rank
    public boolean UnionByRank(int u, int v){
        int ulp_u = findUlPar(u);
        int ulp_v = findUlPar(v);
        if(ulp_u == ulp_v) return false;
        // when the one having smaller rank is attached to the one with greater rank, then the rank of greater one will not increase
        if(rank.get(ulp_u) < rank.get(ulp_v)){
            parent.set(ulp_u, ulp_v);
        }
        else if(rank.get(ulp_v) > rank.get(ulp_u)) parent.set(ulp_v,ulp_u);
        else {
            parent.set(ulp_v, ulp_u);
            rank.set(ulp_u,rank.get(ulp_u)+1); // if the rank is same then increase the rank
        }
        return true;
    }

    // join 2 nodes from different components as per size of the tree
    public void UnionBySize(int u, int v){
        int ulp_u = findUlPar(u);
        int ulp_v = findUlPar(v);
        if(ulp_u == ulp_v) return;
        if(size.get(ulp_u) < size.get(ulp_v)){
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_u)+size.get(ulp_v));
        }else{
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u)+size.get(ulp_v));
        }
    }
}

public class Graph2 {

    static class Tuple{
        int wt, u, v;
        public Tuple(int wt, int u, int v){
            this.wt=wt;
            this.u=u;
            this.v=v;
        }
    }
    // finding MST using kruskal's algorithm
    static int spanningTree(int V, int E, int edges[][]){
        PriorityQueue<Tuple> pq = new PriorityQueue<>((x, y)->x.wt-y.wt);
        DisjointSet ds = new DisjointSet(V);
        for(int i=0; i<edges.length; i++){
            pq.add(new Tuple(edges[i][2],edges[i][0],edges[i][1]));
        }

        int sum=0;
        while(!pq.isEmpty()){
            Tuple t = pq.poll();
            if(ds.UnionByRank(t.u,t.v)) sum += t.wt;
        }
        return sum;
    }

    // GFG - minimum edges req to connect the graph
    static int connectGraph(int n, int[][] edge) {
        DisjointSet ds = new DisjointSet(n);
        int extra = 0;
        for(int i=0; i<edge.length; i++){
            // if these 2 nodes r already in the same component, count it as extra
            if(!ds.UnionByRank(edge[i][0],edge[i][1])) extra++;
        }
        // find no. of connected compponents
        int cnt=0;
        for(int i=0; i<n; i++){
            if(i == ds.findUlPar(i)) cnt++;
        }
        // if there are 3 different connected components then atleast n-1 or 2 extra edges are required
        return (extra>=cnt-1) ? cnt-1 : -1;
    }

    // Kosaraju's algorithm only works in directed graph
    static void dfs(int node, int[] vis, ArrayList<ArrayList<Integer>> adj, Stack<Integer> st){
        vis[node] = 1;
        for(int i: adj.get(node)){
            if(vis[i]==0){
                dfs(i, vis, adj, st);
            }
        }
        st.push(node);
    }
    //Function to find number of strongly connected components in the graph.
    public int kosaraju(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // sort the vertices according to decreasing outdegree
        // the vertices which can't go anywhere will be put first into stack
        int vis[] = new int[V];
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<V; i++){
            if(vis[i]==0) dfs(i,vis,adj,st);
        }
        ArrayList<ArrayList<Integer>> revList = new ArrayList<>();
        for(int i=0; i<V; i++) revList.add(new ArrayList<>());
        // reverse the directions of graph
        for(int i=0; i<V; i++){
            vis[i]=0;
            for(int j: adj.get(i)){
                revList.get(j).add(i);
            }
        }
        int scc=0;
        Stack<Integer> dummy = new Stack<>(); // inorder to use same dfs method
        while(!st.isEmpty()){
            int node = st.pop(); // do dfs as per the nodes entered in stack
            // because those nodes that cannot come to itself but can go to other nodes are individual
            // components, like 3 can goto 4 but 4 can't go to three, thus both r different components
            if(vis[node]==0){
                scc++; // if this node is unvisited then it forms SCC
                dfs(node, vis, revList, dummy);
            }
        }
        return scc;
    }

    // tarjan's algo
    // bridges in graph
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // creating adjacency list
        ArrayList<ArrayList<Integer>> adj =
                new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (List<Integer> it : connections) {
            int u = it.get(0); int v = it.get(1);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        int[] vis = new int[n];
        int[] tin = new int[n]; // time or step of insertion into dfs
        int[] low = new int[n]; // lowest a node can reach in terms of time of insertion of nodes
        // if for a node tin=4 & low=1 then this node is at 4th step in dfs and this node can reach till the
        // node whose tin=1 via some other nodes
        List<List<Integer>> bridges = new ArrayList<>();
        dfs(0, -1, vis, adj, tin, low, bridges);
        return bridges;
    }
    private int time = 0;
    private void dfs(int node, int parent, int[] vis,
                     ArrayList<ArrayList<Integer>> adj, int tin[], int low[],
                     List<List<Integer>> bridges) {
        vis[node] = 1;
        tin[node] = low[node] = time;
        time++;
        for (Integer it : adj.get(node)) {
            if (it == parent) continue; // infinite loop
            if (vis[it] == 0) {
                dfs(it, node, vis, adj, tin, low, bridges);
                low[node] = Math.min(low[node], low[it]); // lowest we can reach through this neighbour
                // node --- it
                if (low[it] > tin[node]) { // if this neighbour can't reach me or before me then upon edge removal
                    bridges.add(Arrays.asList(it, node));      // graph will be divided, so it's a bridge
                }
            } else {
                // if through this node we r yet to visit but it's already been visited then there must be another
                // path to visit this node, so this can't be a bridge
                low[node] = Math.min(low[node], low[it]);
            }
        }
    }

    // articulation points in graph
    public ArrayList<Integer> articulationPoints(int V,ArrayList<ArrayList<Integer>> adj)
    {
        int[] tin = new int[V]; // time of insertion of this node during dfs (at which step)
        int[] low = new int[V]; // lowest (before me) this node can reach in terms of time of insertion
        // if for a node tin=4 & low=1 then this node is at 4th step in dfs and this node can reach till the
        // node whose tin=1 via some other nodes
        int[] vis = new int[V];
        int[] mark = new int[V]; // to mark articulation points

        for(int i=0; i<V; i++){
            if(vis[i]==0){
                dfs(i,-1,vis,tin,low,mark,adj);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0; i<V; i++) if(mark[i]==1) ans.add(i);
        if(ans.size()==0) ans.add(-1);
        return ans;
    }

    private static int timer=1;
    // tin will always be 1 to n for respective nodes only low will change
    private static void dfs(int node, int par, int[] vis, int[] tin, int[] low, int[] mark,
                            ArrayList<ArrayList<Integer>> adj){
        vis[node]=1;
        tin[node]=low[node]=timer++;
        int child=0;
        for(int i: adj.get(node)){
            // i is the parent of curr node which called us
            if(i == par) continue; // going back to where we came from will cause infinite iterations
            if(vis[i]==0){
                dfs(i,node,vis,tin,low,mark,adj);
                low[node] = Math.min(low[node],low[i]);
                // if child node is reachable till curr node or after it only and can't go before it then upon
                // curr node removal the graph will be broken into multiple components
                if(low[i]>=tin[node] && par!=-1) mark[node]=1;
                child++;
            }
            else{
                low[node] = Math.min(low[node],tin[i]);
            }
        }
        if(par==-1 && child > 1) mark[node]=1;
        // why not this, because above will count only those childs who can be called by parent
        // and below will count all the childs, even if one child is reachable by another child
        // if(par==-1 && adj.get(node).size() > 1) mark[node]=1;
    }
    static int fibo(int n, int[] fib){
        if(fib[n] != -1) return fib[n];
        return fibo(n-1, fib) + fibo(n-2,fib);
    }

    public static void main(String[] args) {
//        DisjointSet ds = new DisjointSet(7);
//        ds.UnionBySize(1,2);
//        ds.UnionBySize(2,3);
//        ds.UnionBySize(4,5);
//        ds.UnionBySize(6,7);
//        ds.UnionBySize(5,6);
//        System.out.println(ds.findUlPar(3) == ds.findUlPar(7));
//        ds.UnionBySize(3,7);
//        System.out.println(ds.findUlPar(3) == ds.findUlPar(7));
        int[] fib = new int[1000];
        Arrays.fill(fib,-1);
        fib[0]=0;
        fib[1]=1;
//        System.out.println(fibo(5,fib));
        int f=0, s=1;
        int n=55;
        int c=0;
        for(int i=2; i<=n; i++){
            c = f+s;
            f=s;
            s=c;
        }
        System.out.println(c);
    }
}
