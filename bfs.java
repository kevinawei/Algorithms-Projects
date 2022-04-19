import java.util.*;

class Node
{
    int value;
    Node left, right;

    public Node(int i){
        this.value = i;
    }
}
public class bfs {
    static HashMap<Integer, Node> nodeMap;
    static int goal;

    public static ArrayList<Integer> readInput(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> nodeArray = new ArrayList<Integer>();
        System.out.println("Please input nodes (enter # to finish)");
        String input = sc.nextLine();

        while(!input.contains("#")){
            if (input.contains("*")){
                input = sc.nextLine();
                bfs.goal = Integer.parseInt(input);
                input = sc.nextLine();
                continue;
            }
            String[] s = input.split(",");
            nodeArray.add(Integer.parseInt(s[0]));
            nodeArray.add(Integer.parseInt(s[1]));
            input = sc.nextLine();
        }
   
        return nodeArray;
    }

    public static LinkedList<Node> createTree(ArrayList<Integer> nodeList){
        bfs.nodeMap = new HashMap<Integer, Node>();
        LinkedList<Node> tree = new LinkedList<Node>();

        int value = nodeList.get(0);
        int value2 = nodeList.get(1);

        Node root = new Node(value);
        bfs.nodeMap.put(value, root);
        tree.add(root);
        tree = createEdge(root, value2, nodeMap, tree);

        int count = 2;
        while (count < nodeList.size()){
            Node node;
            value = nodeList.get(count);
            value2 = nodeList.get(count+1);
            if (bfs.nodeMap.containsKey(value)){
                node = bfs.nodeMap.get(value);
            }
            else{
               // System.out.println("creating new node with value: "+value);
                node = new Node(value);
                tree.add(node);
                bfs.nodeMap.put(value, node);
            }
            
           // System.out.println("creating new edge between "+node.value+" and " + value2);
            tree = createEdge(node, value2, bfs.nodeMap, tree);
            count += 2;
        }
        return tree;
    }

    public static LinkedList<Node> createEdge(Node n, int i, HashMap<Integer, Node> nodeMap, LinkedList<Node> tree){
        Node node;
        if (n.left == null && !nodeMap.containsKey(i)){
              //System.out.println("creating new node with value: "+i);
              node = new Node(i);
              n.left = node;
              tree.add(node);
              bfs.nodeMap.put(i, node);
        }
        else if (n.right == null && !nodeMap.containsKey(i)){
            //System.out.println("creating new node with value: "+i);
            node = new Node(i);
            n.right = node;
            tree.add(node);
            bfs.nodeMap.put(i, node);
        }
        else if (nodeMap.containsKey(i)){
            if (n.left == null){
                node = nodeMap.get(i);
                n.left = node;
            }
            else if (n.right == null){
                node = nodeMap.get(i);
                n.right = node;
            }
            else{//Error msg if user tries to create an invalid tree
                System.out.println("Invalid Binary Tree (node has more than 2 children)");
                return null;
            }
        }
        else{
            System.out.println("Invalid Binary Tree (node has more than 2 children)");
                return null;
        }
        return tree;
    }
    public static void printTree(LinkedList<Node> tree){// Helper function to print tree
        System.out.print("List of nodes in tree: ");
        for (int i = 0; i<tree.size(); i++){
            System.out.print(tree.get(i).value+", ");
        }
    }
    public static boolean bfSearch(LinkedList<Node> tree){//BFS function, initializes root node into queue and enqueues each of current node's children
        Queue <Node> queue = new LinkedList<Node>();

        Node node = tree.get(0);
        queue.add(node); //Add root node
        System.out.println("\nTraversal Order:");
         while(!queue.isEmpty()){
            node = queue.remove();
            System.out.print(node.value+" ");
            if (node.value == bfs.goal){
                return true;
            }
            else{
                System.out.print("=> ");
                queue.add(node.left);
                queue.add(node.right);
            }  
        }
        return false;  
    }
    public static void main(String[] args) {
        ArrayList<Integer> nodeArray = readInput();
        System.out.println("Goal node is: "+goal);
        LinkedList<Node> tree= createTree(nodeArray);

        if (bfSearch(tree)){
            System.out.println("Success! Node with value: "+goal+" was found.");
        }
        else{
            System.out.println("Node not found");
        }
    }
}
