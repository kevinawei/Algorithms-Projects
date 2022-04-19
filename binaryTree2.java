class Node{
	int data;
	Node left, right;
	Node(int data){
		this.data = data;
		this.left = this.right = null;
	}
}
public class binaryTree2 {
	Node root;	
	int findMaxDepth(Node root) {
		if (root == null) {
			return 0;
		}else {
			int lDepth = findMaxDepth(root.left);
			int rDepth = findMaxDepth(root.right);
			
			if(lDepth > rDepth) {
				return (lDepth + 1);
			}else {
				return (rDepth + 1);
			}
		}
	}
	
	public static void main(String[] args) {
		binaryTree2 tree = new binaryTree2();
		
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(6);
		tree.root.left.left = new Node(3);
		tree.root.left.right = new Node(7);
		tree.root.left.right.left = new Node(8);
		tree.root.left.right.left.left = new Node(9);
		
		System.out.println("Max depth is: "+tree.findMaxDepth(tree.root));
	}
}
