package test;


class AccountSin{

    private int id;
    private String name;

    static boolean objCreated = false;

    private AccountSin(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AccountSin getObject(int id, String name){

        if(objCreated == false){
            objCreated = true;
            return new AccountSin(id, name);
        }else{
            return null;
        }
    }

}

class Node {

    int data;
    Node left;
    Node right;

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
public class Main {

    public static boolean isSymmetric(Node root){

        if(root == null) return true;
        if(root.left == null && root.right !=null) return false;
        if(root.right == null && root.left !=null) return false;

        return isSymmetric(root.left) && isSymmetric(root.right);


    }

    public static void main(String[] args) {


    }
}
