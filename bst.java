
//Node class 
class Node
{
    int value;
    Node left, right;

}

public class bst{
    // Root node of the BST
    Node root; 

    /*Recursive method that returns true if tree is a binary search tree
    Node n is the current node and lParent and rParent are the right and left parents
    of node n
    */
    boolean checkBST(Node n, Node lParent, Node rParent){ 
        
        //Base case
        if (n == null) {
            return true;
        }
        System.out.println("Current node: "+n.value);
        //first check, returns false if left parent is greater than or equal to n
        if (lParent != null && lParent.value >= n.value){
            return false;
        }
        //second check, returns false if right parent is less than or equal to n
        if (rParent!= null && rParent.value <= n.value){
            return false;
        }
        /*recursive part of function, recursively checks the left and right branches of the tree
        When checking left subtree, current node is the right parent and when checking
        the right subtree, current node is the left parent.
        */
        
        if (checkBST(n.left, lParent, n) && checkBST(n.right, n, rParent)){
            return true;
        }
        else{
            return false;
        }
    }

    //Helper function to create new nodes
    static Node createNode(int data)
    {
        Node node = new Node();
        node.value = data;
        node.left = node.right = null;
        return node;
    }
     
    // Main 
    public static void main(String args[])
    {
        bst BST = new bst();

        Node root = createNode(5);//Root value = 5
        BST.root = root;

        root.left = createNode(2);
        root.right = createNode(6);
        root.left.left = createNode(1);
        root.left.right = createNode(4);

        root.right.left = createNode(0); 
        root.right.right = createNode(8);
     


        //Print statements for result
        if (BST.checkBST(root,null,null)){
            System.out.print("Is BST");
        }
           
        else{
            System.out.print("Not a BST");
        }
    }
}



