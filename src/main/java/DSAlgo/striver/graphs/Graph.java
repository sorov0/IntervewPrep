package DSAlgo.striver.graphs;

import java.util.*;


class Pair{
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Triplet{
    int row;
    int col;
    int time;

    public Triplet(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

public class Graph {



    // Print all possible paths from top left to bottom right of a mXm matrix
    public static void getAllPathUtil(int[][] arr , int i , int j , int n , ArrayList<Integer> tmp , ArrayList<ArrayList<Integer> > res){

        if(i>=n || j>=n) return;

        if(i==n-1 && j==n-1){
            tmp.add(arr[i][j]);
            res.add(new ArrayList<Integer>(tmp));
        }else{
            tmp.add(arr[i][j]);
            getAllPathUtil(arr , i+1 , j , n , tmp , res);
            getAllPathUtil(arr , i , j+1 , n , tmp , res);
        }
        tmp.remove(tmp.size()-1);
    }


    public static ArrayList<ArrayList<Integer>> printAllPath(int[][] arr) {

        ArrayList<Integer> tmp = new ArrayList<>();

        ArrayList<ArrayList<Integer> > res = new ArrayList<>();
        getAllPathUtil(arr , 0 , 0 , arr.length , tmp , res);

        return res;
    }


    // BFS Traversal
    // https://bit.ly/3bn84K8
    public ArrayList<Integer> bfsTraversal(ArrayList<ArrayList<Integer>> adj) {

        ArrayList<Integer> bfs = new ArrayList<>();
        int v = adj.size();
        boolean vis[] = new boolean[v+1];

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        vis[0] = true;

        while (!q.isEmpty()){
            Integer node = q.poll();
            bfs.add(node);

            for(Integer i : adj.get(node)){
                if(!vis[i]){
                    vis[i] = true;
                    q.add(i);
                }
            }
        }
        return bfs;
    }

    // DFS Traversal
    // https://bit.ly/3SocWyX
    void dfsUtil(int node, boolean vis[], ArrayList<Integer> dfs, ArrayList<ArrayList<Integer>> adj){

        vis[node]  = true;
        dfs.add(node); // Note: While Calculating the count of number of Province, This step is not required.
        for(Integer i : adj.get(node)){
            if(!vis[i]){
                dfsUtil(i, vis, dfs, adj);
            }
        }
    }
    public ArrayList<Integer> dfsTraversal(ArrayList<ArrayList<Integer>> adj) {

        ArrayList<Integer> dfs = new ArrayList<>();
        int v = adj.size();
        boolean vis[] = new boolean[v+1];
        dfsUtil(0, vis, dfs, adj);
        return dfs;

    }


    // number of province (number of connected components)
    // https://leetcode.com/problems/number-of-provinces/description/
    // 0 <= V <= 500
    int numProvinces(int[][] adj, int V) {

        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                // Ignore self loops
                if (adj[i][j] == 1 && i != j) {
                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }
        ArrayList<Integer> dfs = new ArrayList<>();
        boolean[] vis = new boolean[V];
        int count = 0;

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                count++;
                dfsUtil(i, vis, dfs, adjList);
            }
        }
        return count;
    }

    // Number of connected components (Using BFS)
    public int countComponents(int V, int[][] edges) {

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[V];
        int components = 0;

        // Traverse all nodes in the graph
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                components++;

                // Start BFS from this node
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                visited[i] = true;

                while (!q.isEmpty()) {
                    int node = q.poll();
                    for (int nbr : adj.get(node)) {
                        if (!visited[nbr]) {
                            visited[nbr] = true;
                            q.offer(nbr);
                        }
                    }
                }
            }
        }
        return components;
    }

    // Number of Islands
    // https://bit.ly/3oT9Ndg

    public void countIslandsUtilDFS(int row, int col, char[][] grid, boolean[][] vis) {
        int n = grid.length;
        int m = grid[0].length;

        vis[row][col] = true;

        // Explore all 8 directions
        for (int delRow = -1; delRow <= 1; delRow++) {
            for (int delCol = -1; delCol <= 1; delCol++) {

                int nrow = row + delRow;
                int ncol = col + delCol;

                if (nrow >= 0 && nrow < n &&
                        ncol >= 0 && ncol < m &&
                        !vis[nrow][ncol] &&
                        grid[nrow][ncol] == 'L') {

                    countIslandsUtilDFS(nrow, ncol, grid, vis);
                }
            }
        }
    }

    public void countIslandsBFSUtil(int row, int col, char[][] grid, boolean vis[][]){

        Queue<Pair> q = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        q.add(new Pair(row,col));

        vis[row][col] = true;
        while(!q.isEmpty()){

            int frow = q.peek().first;
            int fcol = q.peek().second;
            q.remove();

            for(int delrow = -1 ; delrow<=1 ; delrow++){
                for(int delcol = -1 ; delcol <= 1 ; delcol++){
                    int nrow = frow + delrow;
                    int ncol = fcol + delcol;

                    if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && !vis[nrow][ncol] && grid[nrow][ncol]=='L'){
                        vis[nrow][ncol] = true;
                        q.add(new Pair(nrow , ncol));
                    }
                }
            }
        }
    }
    public int countIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        boolean vis[][] = new boolean[n][m];
        int cnt = 0;

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
                if(!vis[i][j] && grid[i][j]=='L'){
                    cnt++;
                    countIslandsBFSUtil(i, j, grid, vis);
                }
            }
        }
        return cnt;

    }


    // Flood Fill Algorithm
    // https://bit.ly/3bxY94d
    public static void floodFillUtilDFS(int row, int col, int ans[][], int[][] image,
                                        int delrow[], int delcol[], int iniColor, int newColor){

        ans[row][col] = newColor;
        int n = image.length;
        int m = image[0].length;
        for(int i = 0 ; i<4 ; i++){
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];
            if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && image[nrow][ncol]==iniColor && ans[nrow][ncol]!=newColor){
                floodFillUtilDFS(nrow , ncol , ans , image , delrow , delcol , iniColor , newColor);
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor)
    {

        int iniColor = image[sr][sc];
        int ans[][] = image;

        int delrow[] = {-1 , 0 , +1 , 0};
        int delcol[] = {0 , +1 , 0 , -1};

        floodFillUtilDFS(sr , sc , ans , image , delrow , delcol , iniColor , newColor);

        return ans;
    }


    // Rotten Oranges
    // https://bit.ly/3oekoir
    public int orangesRot(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;
        int vis[][] = new int[m][n];
        int cntFresh = 0;
        int maxTime = 0;
        int drow[] = {-1 , 0 , +1 , 0};
        int dcol[] = {0 , +1 , 0 , -1};
        int cnt = 0;
        Queue<Triplet> q = new LinkedList<>();

        for(int i = 0 ; i<n ; i++){
            for(int j = 0 ; j<m ; j++){
                if(mat[i][j] == 2){
                    vis[i][j] = 2;
                    q.add(new Triplet(i, j, 0));
                }
                if(mat[i][j] == 1){
                    vis[i][j] = 0;
                }else{
                    vis[i][j] = 1; cntFresh++;
                }
            }
        }
        while(!q.isEmpty()){
            Triplet node = q.peek();
            int row = node.row;
            int col = node.col;
            int time = node.time;

            q.poll();
            maxTime = Math.max(time, maxTime);
            for(int i = 0 ; i<4 ; i++){
                for(int j = 0 ; j<4 ; j++){
                    int nrow = row + drow[i];
                    int ncol = col + dcol[i];

                    if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && vis[nrow][ncol]==0 &&
                            mat[nrow][ncol]==1){
                        q.add(new Triplet(nrow, ncol, time + 1));
                        vis[nrow][ncol] = 2;
                        cnt++;
                    }
                }
            }
        }
        if(cnt != cntFresh) return -1;
        return maxTime;
    }

    boolean checkForCycleBFSForUGUtil(ArrayList<ArrayList<Integer>> adj, int s,
                                 boolean vis[]){

        Queue<Pair> q =  new LinkedList<>();
        q.add(new Pair(s, -1));
        vis[s] = true;

        while(!q.isEmpty())
        {
            // source node and its parent node
            int node = q.peek().first;
            int par = q.peek().second;
            q.remove();

            for(Integer it: adj.get(node))
            {
                if(vis[it]==false)
                {
                    q.add(new Pair(it, node));
                    vis[it] = true;
                }
                // if adjacent node is visited and is not its own parent node
                else if(it != par) return true;
            }
        }
        return false;
    }

    private boolean checkForCycleDFSForUGUtil(int node, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] visited) {

        visited[node] = true;
        for (int neighbor : adj.get(node)) {

            // If neighbor not visited, recurse
            if (!visited[neighbor]) {
                if (checkForCycleDFSForUGUtil(neighbor, node, adj, visited)) return true;
            }

            // If neighbor visited and not parent, cycle exists
            else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    public boolean isCycleDFSforUG(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean vis[] = new boolean[V];
        Arrays.fill(vis,false);

        for(int i=0;i<V;i++){
            if(vis[i]==false){
                if(checkForCycleBFSForUGUtil(adj, i, vis))  return true;
            }
        }

        return false;

    }

    // Distance of the nearest cell having 1 | 0/1 Matrix
    public int[][] nearest0And1(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];
        int[][] dis = new int[n][m];

        Queue<int[]> q = new LinkedList<>();
        for(int i = 0 ; i < n ; i++){
            for(int j = 0; j < m ; j++){
                if(grid[i][j] == 1){
                    q.add(new int[]{i, j, 0});
                    vis[i][j] = 1;
                }else{
                    vis[i][j] = 0;
                }
            }
        }
        // Directions: Up, Right, Down, Left
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        while(!q.isEmpty()){
            int row = q.peek()[0];
            int col = q.peek()[1];
            int steps = q.peek()[2];

            dis[row][col] = steps;
            q.poll();
            for(int i = 0 ; i<4 ; i++){
                int nrow = row + delRow[i];
                int ncol = col + delCol[i];

                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && vis[nrow][ncol] == 0){
                    q.add(new int[]{nrow, ncol, steps + 1});
                    vis[nrow][ncol] = 1;
                }
            }
        }
        return dis;
    }



    // Surrounded Regions | Replace O's with X's
    private void fillDFSUtil(int r, int c, int[][] vis, char[][] mat, int[] dr, int[] dc) {

        vis[r][c] = 1;
        int n = mat.length, m = mat[0].length;

        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k], nc = c + dc[k];
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && vis[nr][nc] == 0 && mat[nr][nc] == 'O') {
                fillDFSUtil(nr, nc, vis, mat, dr, dc);
            }
        }
    }

    public char[][] fill(int n, int m, char[][] mat) {

        if (n == 0 || m == 0) return mat;

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        int[][] vis = new int[n][m];

        for (int j = 0; j < m; j++) {
            if (vis[0][j] == 0 && mat[0][j] == 'O') fillDFSUtil(0, j, vis, mat, dr, dc);

            if (vis[n - 1][j] == 0 && mat[n - 1][j] == 'O') fillDFSUtil(n - 1, j, vis, mat, dr, dc);
        }

        for (int i = 0; i < n; i++) {
            if (vis[i][0] == 0 && mat[i][0] == 'O') fillDFSUtil(i, 0, vis, mat, dr, dc);

            if (vis[i][m - 1] == 0 && mat[i][m - 1] == 'O') fillDFSUtil(i, m - 1, vis, mat, dr, dc);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == 0 && mat[i][j] == 'O') mat[i][j] = 'X';
            }
        }

        return mat;

    }


    // Number of Enclaves | Multi-source BFS
    int numberOfEnclaves(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        ArrayDeque<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Check if current cell lies on boundary
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    if (grid[i][j] == 1 && !vis[i][j]) {
                        vis[i][j] = true;
                        q.add(new int[]{i, j});
                    }
                }
            }
        }

        int[] delrow = {-1, 0, 1, 0};
        int[] delcol = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0], col = cur[1];

            for (int k = 0; k < 4; k++) {
                int nrow = row + delrow[k];
                int ncol = col + delcol[k];

                // Check bounds, unvisited, and land
                if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m
                        && !vis[nrow][ncol] && grid[nrow][ncol] == 1) {
                    vis[nrow][ncol] = true;
                    q.add(new int[]{nrow, ncol});
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) cnt++;
            }
        }
        return cnt;

    }

    // Number of Distinct Islands | Constructive Thinking + DFS/BFS
    // It count distinct islands and only 4 direction traversal is allowed
    void cntDistinctIslandBFSUtil(int row, int col, boolean[][] vis, int[][] grid, List<String> shape){

        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});
        vis[row][col] = true;

        int baseRow = row;
        int baseCol = col;

        // 4-direction movement
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            // store relative position
            shape.add((r - baseRow) + "," + (c - baseCol));

            for (int k = 0; k < 4; k++) {
                int nrow = r + dr[k];
                int ncol = c + dc[k];

                if (nrow >= 0 && nrow < n &&
                        ncol >= 0 && ncol < m &&
                        !vis[nrow][ncol] &&
                        grid[nrow][ncol] == 1) {

                    vis[nrow][ncol] = true;
                    q.add(new int[]{nrow, ncol});
                }
            }
        }
    }
    int countDistinctIslands(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];
        Set<List<String>> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (!vis[i][j] && grid[i][j] == 1) {
                    List<String> shape = new ArrayList<>();
                    cntDistinctIslandBFSUtil(i, j, vis, grid, shape);
                    set.add(shape);
                }
            }
        }
        return set.size();
    }


    // Bipartite Graph | BFS
    boolean isBipartiteDFSUtil(int node, int nodeColor, int color[],
                               ArrayList<ArrayList<Integer>>adj){

        color[node] = nodeColor;
        for(int it : adj.get(node)) {
            if(color[it] == -1){
                boolean res = isBipartiteDFSUtil(it, 1 - nodeColor, color, adj);
                if(res == false) return false;
            } else if (nodeColor != color[it]) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>>adj) {

        int color[] = new int[V];
        for(int i = 0;i<V;i++) color[i] = -1;

        // for connected components
        for(int i = 0;i<V;i++) {
            if(color[i] == -1) {
                if(isBipartiteDFSUtil(i, 0, color, adj) == false) return false;
            }
        }
        return true;
    }


    //
    // Function to detect cycle in a directed graph.
    private boolean dfsCheck(int node, ArrayList<ArrayList<Integer>> adj, int vis[], int pathVis[]) {
        vis[node] = 1;
        pathVis[node] = 1;

        // traverse for adjacent nodes
        for(int it : adj.get(node)) {
            // when the node is not visited
            if(vis[it] == 0) {
                if(dfsCheck(it, adj, vis, pathVis) == true)
                    return true;
            }
            // if the node has been previously visited, but it has to be visited on the same path
            else if(pathVis[it] == 1) {
                return true;
            }
        }
        pathVis[node] = 0;
        return false;
    }
    public boolean isCyclicGivenDirectedGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        int vis[] = new int[V];
        int pathVis[] = new int[V];

        for(int i = 0;i<V;i++) {
            if(vis[i] == 0) {
                if(dfsCheck(i, adj, vis, pathVis) == true) return true;
            }
        }
        return false;
    }



    // Topologocal Sort in DAG (Directed Acyclic Graph)
    void dfsTopoSort(int node, boolean[] vis, Stack<Integer> st, ArrayList<ArrayList<Integer>> graph){

        vis[node] = true;
        for(int it : graph.get(node)){
            if(!vis[it]){
                dfsTopoSort(it, vis, st, graph);
            }
            st.push(node);
        }

    }
    int[] topoSort(int V, ArrayList<ArrayList<Integer>> graph){

        boolean[] vis = new boolean[V];

        Stack<Integer> st = new Stack<>();
        for(int i = 0 ; i<V ; i++){
            if(!vis[i]){
                dfsTopoSort(i, vis, st, graph);
            }
        }

        int ans[] = new int[V];
        int i = 0;
        while(!st.isEmpty()){
            ans[i++] = st.peek();
            st.pop();
        }
        return ans;

    }


    // Topo Sort using Kahn's Algorithm BFS
    public ArrayList<Integer> topoSortBFS(int V, List<List<Integer>> adj) {
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i)) inDegree[it]++;
        }

        Queue<Integer> q = new LinkedList<>();
        // Add the nodes with no in-degree to queue
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) q.add(i);
        }

        ArrayList<Integer> ans = new ArrayList<>();

        // Until the queue is empty
        while (!q.isEmpty()) {
            int node = q.poll();
            ans.add(node);
            for (int it : adj.get(node)) {
                // Decrement the in-degree
                inDegree[it]--;

                // Add the node to queue if n-degree becomes zero
                if (inDegree[it] == 0) q.add(it);
            }
        }
        return ans;
    }

    // Detect a Cycle in a Graph using TopoSort BFS(Kahn's Algo)
    public boolean isCyclic(int V, List<List<Integer>> adj) {
        ArrayList<Integer> toposort = topoSortBFS(V, adj);
        if(toposort.size()== V) return false;
        return true;
    }


    // Course Schedule I && II | Pre-requisite Tasks | Topological Sort
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adj = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            int a = pre[0];
            int b = pre[1];
            adj.get(b).add(a);
            inDegree[a]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        int count = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            count++;
            for (int nei : adj.get(node)) {
                inDegree[nei]--;
                if (inDegree[nei] == 0) {
                    q.offer(nei);
                }
            }
        }
        return count == numCourses;
    }


    //
    public List<Integer> eventualSafeNodes(int V, List<Integer>[] adj) {
        List<Integer>[] adjRev = new List[V];
        int[] indegree = new int[V];

        // Initialize reverse adjacency list
        for (int i = 0; i < V; i++) {
            adjRev[i] = new ArrayList<>();
        }

        // Build the reverse graph and calculate indegrees
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj[i]) {
                adjRev[neighbor].add(i);  // Reverse the direction of edges
                indegree[i]++;  // Increment indegree for the current node
            }
        }

        Queue<Integer> q = new LinkedList<>();  // Queue to store nodes with no outgoing edges
        List<Integer> safeNodes = new ArrayList<>();

        // Add all nodes with 0 indegree to the queue
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // Process the queue to find all safe nodes
        while (!q.isEmpty()) {
            int node = q.poll();
            safeNodes.add(node);  // This node is safe
            for (int parent : adjRev[node]) {
                indegree[parent]--;  // Decrease indegree of the parent nodes
                if (indegree[parent] == 0) {
                    q.offer(parent);  // If indegree becomes 0, it is a safe node
                }
            }
        }

        Collections.sort(safeNodes);  // Sort the safe nodes
        return safeNodes;
    }



    public static void main(String[] args) {

    }
}

