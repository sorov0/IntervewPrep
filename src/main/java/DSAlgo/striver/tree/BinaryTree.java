package DSAlgo.striver.tree;


import sun.awt.image.ImageWatched;

import java.util.*;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}


class Pair{
    int h;
    int d;
    Pair(int h , int d){
        this.h = h;
        this.d = d;
    }
}

class PairSum{
    int height;
    int sum;
    PairSum(int  height, int sum){
        this.height = height;
        this.sum = sum;
    }
}

class PairForWidth{
    Node node;
    int ind;

    public PairForWidth(Node node, int ind) {
        this.node = node;
        this.ind = ind;
    }
}

class ResultPair {
    int isSum;  // 1 = true, 0 = false
    int sum;

    ResultPair(int isSum, int sum) {
        this.isSum = isSum;
        this.sum = sum;
    }
}

class VerticalTraversalUtilClass{
    Node node;
    int x;
    int y;

    public VerticalTraversalUtilClass(Node node, int x, int y) {
        this.node = node;
        this.x = x;
        this.y = y;
    }
}

public class BinaryTree {

    public static void inOrder(Node root, List<Integer> arr) {

        if (root == null) {
            return;
        }
        inOrder(root.left, arr);
        arr.add(root.data);
        inOrder(root.right, arr);
    }

    static void preOrder(Node root, List<Integer> arr) {

        if (root == null) {
            return;
        }
        arr.add(root.data);
        preOrder(root.left, arr);
        preOrder(root.right, arr);
    }

    static void postOrder(Node root, List<Integer> arr){

        if(root == null){
            return;
        }
        postOrder(root.left, arr);
        postOrder(root.right, arr);
        arr.add(root.data);
    }

    static void levelOrder1(Node root, List<Integer> arr)
    {
        // Your code here
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){

            Node tmp = q.poll();
            arr.add(tmp.data);
            if(tmp.left!=null) q.add(tmp.left);
            if(tmp.right!=null) q.add(tmp.right);
        }
    }

    static void levelOrder2(Node root, List<List<Integer>> arr)
    {
        // Your code here
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){

            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for(int i = 0 ; i<size ; i++){
                Node tmp = q.poll();
                level.add(tmp.data);
                if(tmp.left!=null) q.add(tmp.left);
                if(tmp.right!=null) q.add(tmp.right);
            }
            arr.add(level);
        }
    }

    List<Integer> leftViewBinaryTree(Node root){

        List<List<Integer>> arr = new ArrayList<>();
        List<Integer> leftView = new ArrayList<>();

        levelOrder2(root, arr);

        for(List<Integer> level : arr){
            leftView.add(level.get(0));
        }
        return leftView;
    }

    List<Integer> rightViewBinaryTree(Node root){

        List<List<Integer>> arr = new ArrayList<>();
        List<Integer> rightView = new ArrayList<>();

        levelOrder2(root, arr);

        for(List<Integer> level : arr){
            rightView.add(level.get(level.size()-1));
        }
        return rightView;
    }

    public ArrayList<Integer> reverseLevelOrder(Node node)
    {

        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while(!q.isEmpty()){
            Node tmp = q.poll();
            ans.add(tmp.data);
            if(tmp.right!=null) q.add(tmp.right);
            if(tmp.left!=null) q.add(tmp.left);

        }
        Collections.reverse(ans);
        return ans;

    }

    List<List<Integer>> zigzagTraversal(Node root){

        List<List<Integer>> res = new ArrayList<>();

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        boolean leftToRight = true;
        while(!q.isEmpty()){

            int size = q.size();
            List<Integer> tmp = new ArrayList<>();
            for(int i = 0 ; i<size ; i++){
                Node node = q.poll();

                if(leftToRight){
                    tmp.add(node.data);
                }else{
                    tmp.add(0, node.data);
                }

                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
            }
            res.add(tmp);
        }
        return res;

    }

    public static List<Integer> diagonalTraversal(Node root) {

        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            // Traverse the current diagonal
            while (current != null) {
                result.add(current.data);

                // Save left child for next diagonal
                if (current.left != null) {
                    queue.add(current.left);
                }

                // Move right on same diagonal
                current = current.right;
            }
        }

        return result;
    }

    public static List<List<Integer>> diagonalTraversal2(Node root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // Each iteration of this loop processes one diagonal
        while (!queue.isEmpty()) {

            int size = queue.size();
            List<Integer> diagonal = new ArrayList<>();

            // Process all nodes that belong to the current diagonal
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();

                // Traverse along the diagonal (right pointers)
                while (current != null) {
                    diagonal.add(current.data);

                    // Left child goes to next diagonal
                    if (current.left != null) {
                        queue.add(current.left);
                    }

                    current = current.right;
                }
            }

            result.add(diagonal);
        }

        return result;
    }


    List<Integer> topViewTraversal(Node root){

        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        TreeMap<Integer, Integer> mp = new TreeMap<>();
        Queue<AbstractMap.SimpleEntry<Node, Integer>> q = new LinkedList<>();

        q.add(new AbstractMap.SimpleEntry<>(root, 0));
        while(!q.isEmpty()){

            AbstractMap.SimpleEntry<Node, Integer> entry = q.poll();
            Node node = entry.getKey();
            int x = entry.getValue();

            if(!mp.containsKey(x)){
                mp.put(x, node.data);
            }

            if(node.left != null){
                q.add(new AbstractMap.SimpleEntry<>(node.left, x-1));
            }
            if(node.right != null){
                q.add(new AbstractMap.SimpleEntry<>(node.right, x+1));
            }
        }

        for(int val : mp.values()){
            res.add(val);
        }
        return res;

    }

    List<Integer> bottomViewTraversal(Node root){

        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        TreeMap<Integer, Integer> mp = new TreeMap<>();
        Queue<AbstractMap.SimpleEntry<Node, Integer>> q = new LinkedList<>();

        q.add(new AbstractMap.SimpleEntry<>(root, 0));
        while(!q.isEmpty()){

            AbstractMap.SimpleEntry<Node, Integer> entry = q.poll();
            Node node = entry.getKey();
            int x = entry.getValue();

            mp.put(x, node.data);

            if(node.left != null){
                q.add(new AbstractMap.SimpleEntry<>(node.left, x-1));
            }
            if(node.right != null){
                q.add(new AbstractMap.SimpleEntry<>(node.right, x+1));
            }
        }

        for(int val : mp.values()){
            res.add(val);
        }
        return res;
    }


    boolean isLeafNode(Node node){
        return node.left == null && node.right == null;
    }

    void addLeftBounderTraversal(Node root, List<Integer> res){

        Node left = root.left;

        while(left != null){
            if(!isLeafNode(left)){
                res.add(left.data);
            }
            if(left.left != null){
                left = left.left;
            }else{
                left = left.right;
            }
        }
    }

    void addRightBoundaryReverseTraversal(Node root, List<Integer> res){
        List<Integer> tmp = new ArrayList<>();
        Node right = root.right;

        while(right != null){
            if(!isLeafNode(right)){
                tmp.add(right.data);
            }
            if(right.right != null){
                right = right.right;
            }else{
                right = right.left;
            }
        }
        Collections.reverse(tmp);
        res.addAll(tmp);
    }

    void addLeafNodeTraversal(Node root, List<Integer> res){
        if(isLeafNode(root)){
            res.add(root.data);
            return;
        }
        if(root.left != null){
            addLeafNodeTraversal(root.left, res);
        }
        if(root.right != null){
            addLeafNodeTraversal(root.right, res);
        }

    }
    List<Integer> boundaryTraversal(Node root){

        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        if(!isLeafNode(root)) res.add(root.data);

        addLeftBounderTraversal(root, res);
        addLeafNodeTraversal(root, res);
        addRightBoundaryReverseTraversal(root, res);

        return res;

    }



    boolean checkIfTwoTreesAreIdentical(Node p, Node q){

        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.data != q.data) return false;

        return checkIfTwoTreesAreIdentical(p.left, q.left)
                && checkIfTwoTreesAreIdentical(p.right, q.right);
    }

    boolean isSymmetricUtil(Node p, Node q){
        if(p == null || q == null){
            return p == q;
        }

        return (p.data == q.data &&
                isSymmetricUtil(p.left, q.right) && isSymmetricUtil(p.right, q.left));
    }
    boolean checkIfATreeIsSymmetric(Node root){

        if(root == null) return true;
        return isSymmetricUtil(root.left, root.right);
    }


    Node invertTree(Node root){

        if(root == null) return null;

        root.left = invertTree(root.left);
        root.right = invertTree(root.right);

        Node tmp = null;
        tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;

    }



    static int heightOfTree(Node root){
        if(root == null) return 0;
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        int h = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0 ; i<size ; i++){
                Node front = q.poll();

                if(front.left != null){
                    q.add(front.left);
                }

                if(front.right != null){
                    q.add(front.right);
                }
            }
            h++;
        }
        return h;
    }

    public static int height(Node root) {

        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }


    public static boolean isHeightBalanceinN2(Node root){

        if(root == null) return true;

        int lefth = height(root.left);
        int righth = height(root.right);

        if(Math.abs(lefth - righth) > 1) return false;
        return isHeightBalanceinN2(root.left) && isHeightBalanceinN2(root.right);
    }

    // <height, isBalanced>
    // <int, int>
    Pair isHeightBalancedinNUtil(Node root){

        if(root == null) return new Pair(0, 1);

        Pair left = isHeightBalancedinNUtil(root.left);
        Pair right = isHeightBalancedinNUtil(root.right);

        int d = (left.d == 1 && right.d == 1 && Math.abs(left.h - right.h) <= 1) == true ? 1 : 0;

        return new Pair(Math.max(left.h, right.h) + 1, d);
    }


    public boolean isHeightBalanceinN(Node root){
        return isHeightBalancedinNUtil(root).d == 1 ? true : false;
    }


    public static int diameterOfTree(Node root){
        if(root == null) return 0;

        int lh = height(root.left);
        int rh = height(root.right);

        int ld = diameterOfTree(root.left);
        int rd = diameterOfTree(root.right);

        return Math.max(Math.max(ld, rd), lh+rh+1);
    }

    public static Pair diameterUtil(Node root){

        if(root == null) return new Pair(0 , 0);
        Pair l = diameterUtil(root.left);
        Pair r = diameterUtil(root.right);

        int h = Math.max(l.h , r.h) + 1;
        int d = Math.max(Math.max(l.d , r.d) , l.h + r.h + 1);
        return new Pair(h , d);

    }
    public static int diameter(Node root) {

        return diameterUtil(root).d;
    }

    int ans;
    int maxPathSumSolve(Node root){

        if(root==null) return 0;

        int lsum = Math.max(0 , maxPathSumSolve(root.left));
        int rsum = Math.max(0 , maxPathSumSolve(root.right));

        ans = Math.max(ans , lsum + rsum + root.data);

        return root.data + Math.max(lsum, rsum);
    }
    public int maxPathSum(Node root) {
        ans = Integer.MIN_VALUE;
        maxPathSumSolve(root);
        return ans;
    }


    int widthOfBinaryTree(Node root){

        if(root == null) return 0;
        Queue<PairForWidth> q = new LinkedList<>();
        q.add(new PairForWidth(root,0));
        int ans = 0;

        while(!q.isEmpty()){
            int size = q.size();
            int min = q.peek().ind;
            int first = 0;
            int last = 0;
            for(int i = 0; i<size ; i++){
                int cur_ind = q.peek().ind - min;
                Node node = q.peek().node;
                q.poll();
                if(i == 0) first = cur_ind;
                if(i == size -1) last = cur_ind;
                if(node.left != null) q.add(new PairForWidth(node.left, cur_ind*2 +1));
                if(node.right != null) q.add(new PairForWidth(node.right, cur_ind*2 + 2));
            }
            ans = Math.max(ans, last - first +1);
        }
        return ans;
    }

    boolean getPath(Node root, int target, List<Integer> res){

        if(root == null) return false;
        res.add(root.data);

        if(root.data == target) return true;

        boolean left = getPath(root.left, target, res);
        boolean right = getPath(root.right, target, res);

        if(left || right) return true;

        //BackTrack if not found
        res.remove(res.size() - 1);
        return false;
    }

    List<Integer> pathFromGivenNodeToRoot(Node root, int target){
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        getPath(root, target, res);
        return res;
    }

    void findAllPathFromRootToEveryLeafUtil(Node root, List<Integer> temp, List<List<Integer>> ans){

        if (root.left == null && root.right == null) {
            temp.add(root.data);
            ans.add(new ArrayList<>(temp)); // copy list
            temp.remove(temp.size() - 1);
            return;
        }

        temp.add(root.data);

        if (root.left != null) {
            findAllPathFromRootToEveryLeafUtil(root.left, temp, ans);
        }
        if (root.right != null) {
            findAllPathFromRootToEveryLeafUtil(root.right, temp, ans);
        }

        temp.remove(temp.size() - 1);
    }

    public List<String> findAllPathFromRootToEveryLeafUtil(Node root){


        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        List<String> res = new ArrayList<>();

        if (root == null) return res;

        findAllPathFromRootToEveryLeafUtil(root, temp, ans);

        for (List<Integer> path : ans) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                sb.append(path.get(i)).append("->");
            }
            sb.append(path.get(path.size() - 1));
            res.add(sb.toString());
        }

        return res;


    }

    Node lowestCommonAncestor(Node root, Node p, Node q){

        if(root == null || root == p || root == q){
            return root;
        }

        Node left = lowestCommonAncestor(root.left, p, q);
        Node right = lowestCommonAncestor(root.right, p, q);

        if(left == null) return right;
        if(right == null) return left;
        return root;
    }

    ResultPair isSumPropertyOfTreeUtil(Node root) {

        if (root == null)
            return new ResultPair(1, 0);

        if (root.left == null && root.right == null)
            return new ResultPair(1, root.data);

        ResultPair left = isSumPropertyOfTreeUtil(root.left);
        ResultPair right = isSumPropertyOfTreeUtil(root.right);

        int sum = left.sum + right.sum;

        int isSum = (sum == root.data && left.isSum == 1 && right.isSum == 1) ? 1 : 0;

        return new ResultPair(isSum, sum);
    }


    int maximumSumLeafToRoot(Node root){

        if(root == null) return 0;

        int lsum = maximumSumLeafToRoot(root.left);
        int rsum = maximumSumLeafToRoot(root.right);

        return Math.max(lsum, rsum) + root.data;
    }

    int checkForIsSumPropertyOfTree(Node root) {
        return isSumPropertyOfTreeUtil(root).isSum;
    }

    void convertToChildrenSumProperty(Node root){

        if(root == null) return;

        int childSum = 0;
        if(root.left != null){
            childSum += root.left.data;
        }
        if(root.right != null){
            childSum += root.right.data;
        }

        // Compare the sum of children with
        // the current node's value and update
        if(childSum >= root.data){
            root.data = childSum;
        }else{
            // If the sum is smaller, update the
            // child with the current node's value.
            if(root.left != null){
                root.left.data = root.data;
            }
            if (root.right != null) {
                root.right.data = root.data;
            }
        }

        convertToChildrenSumProperty(root.left);
        convertToChildrenSumProperty(root.right);

        int tot = 0;
        if (root.left != null) {
            tot += root.left.data;
        }
        if (root.right != null) {
            tot += root.right.data;
        }

        // If either left or right child
        // exists, update the current node's
        // value with the total sum.
        if (root.left != null || root.right != null) {
            root.data = tot;
        }

    }

    void mapParents(Node root, Map<Node, Node> parentMap){

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            Node node = q.poll();
            if(node.left != null){
                parentMap.put(node.left, node);
                q.add(node.left);
            }
            if(node.right != null){
                parentMap.put(node.right, node);
                q.add(node.right);
            }
        }
    }

    List<Integer> allNodesAtDistanceKUtil(Node target, Map<Node, Node> parentMap, int k){

        Queue<Node> q = new LinkedList<>();
        Set<Node> vis = new HashSet<>();

        q.add(target);
        vis.add(target);

        int currLevel = 0;
        while (!q.isEmpty()){

            int size = q.size();
            if(currLevel++ == k) break;

            for(int i = 0 ; i<size ; i++){
                Node node = q.poll();

                if(node.left != null && !vis.contains(node.left)){
                    vis.add(node.left);
                    q.add(node.left);
                }

                if(node.right != null && !vis.contains(node.right)){
                    vis.add(node.right);
                    q.add(node.right);
                }

                if(parentMap.containsKey(node) && !vis.contains(parentMap.get(node))){
                    vis.add(parentMap.get(node));
                    q.add(parentMap.get(node));
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            result.add(q.poll().data);
        }

        return result;
    }

    List<Integer> allNodesAtDistanceKFromGIvenNode(Node root, Node target, int k){

        if(root == null) return new ArrayList<>();

        Map<Node, Node> parentMap = new HashMap<>();
        mapParents(root, parentMap);

        return allNodesAtDistanceKUtil(target, parentMap, k);
    }

    //If two paths are longest, return the sum with maximum sum
    PairSum maxSumOfNodesOnLongestPathUtil(Node root){

        if(root == null) return new PairSum(0, 0);

        PairSum left = maxSumOfNodesOnLongestPathUtil(root.left);
        PairSum right = maxSumOfNodesOnLongestPathUtil(root.right);

        int sum = 0;
        if(left.height == right.height){
            sum = Math.max(left.sum, right.sum);
        }else{
            if(left.height > right.height){
                sum = left.sum;
            }else{
                sum = right.sum;
            }
        }
        return new PairSum(1 + Math.max(left.height, right.height), sum + root.data);
    }

    int maxSumOfNodesOnLongestPath(Node root){

        return maxSumOfNodesOnLongestPathUtil(root).sum;
    }

    boolean checkIfAllLeavesAtSameLevel(Node root){

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int level = 0;
        Integer leafLevel = null;
        while(!q.isEmpty()){
            level++;
            int size = q.size();
            for(int i = 0 ; i<size ; i++){
                Node node = q.poll();

                if(node.left == null && node.right == null){
                    if(leafLevel == null){
                        leafLevel = level;
                    }else if(level != leafLevel){
                        return false;
                    }
                }
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }
        return true;
    }

    int getDepthForFirstLeaf(Node root){
        if(root == null) return 0;
        int depth = 0;
        while(root != null){
            depth++;
            if(root.left == null && root.right == null){
                return depth;
            }
            root = (root.left != null) ? root.left : root.right;
        }
        return depth;
    }

    boolean allLeavesSameLevel(Node root, int level, int leafDepth) {
        if(root == null) return true;

        level++;
        if(root.left == null && root.right == null){
            return leafDepth == level;
        }

        return allLeavesSameLevel(root.left, level, leafDepth) &&
                allLeavesSameLevel(root.right, level, leafDepth);
    }


    boolean checkIfAllLeavesAtSameLevelRec(Node root) {
        if (root == null) return true;
        int leafDepth = getDepthForFirstLeaf(root);
        return allLeavesSameLevel(root, 0, leafDepth);
    }


    boolean flag = true;
    int sumTreeUtil(Node root){

        if(root == null) return 0;

        if(root.left == null && root.right == null) return root.data;
        if(flag == false) return 0;
        int leftSum = checkForIsSumPropertyOfTree(root.left);
        int rightSum = checkForIsSumPropertyOfTree(root.right);

        if(root.data != leftSum + rightSum){
            flag = false;
        }
        return root.data + leftSum + rightSum;

    }

    //  a node is equal to the sum of the nodes present in its left subtree and right subtree
    boolean checkIfTreeIsSumTree(Node root)
    {
        // Your code here
        sumTreeUtil(root);
        return flag;
    }


    HashMap<String, Integer> map = new HashMap<>();
    private String dupSubTreeUtil(Node root) {
        if (root == null) {
            return "$";
        }

        // Leaf node (single node subtree not counted)
        if (root.left == null && root.right == null) {
            return Integer.toString(root.data);
        }

        String s = "";
        s += root.data;
        s += dupSubTreeUtil(root.left);
        s += dupSubTreeUtil(root.right);

        map.put(s, map.getOrDefault(s, 0) + 1);
        return s;
    }

    // Duplicate subtree in Binary Tree
    int dupSubTree(Node root) {
        map.clear();
        dupSubTreeUtil(root);

        for (int count : map.values()) {
            if (count >= 2) {
                return 1;
            }
        }
        return 0;
    }









    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        List<Integer> arr = new ArrayList<>();
        inOrder(root, arr);

        System.out.print("Inorder Traversal: ");

        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
