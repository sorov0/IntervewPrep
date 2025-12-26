package DSAlgo.practice.tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

public class BinaryTreeTraversal {

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
