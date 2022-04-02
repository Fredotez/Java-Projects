/**
 *
 * @author Frederic Desjardins
 */
public class BinaryTree {


	BinaryTreeNode root = null;

	public void insertInTree (int newData) {
		if (root == null) 
			root = new BinaryTreeNode(newData);
		else 
			root.insert(newData);
	}

	public void displayInOrder () {
		displayInOrder (root);
	}
	

	private void displayInOrder (BinaryTreeNode subRoot){
		if (subRoot == null)   
			return;
		displayInOrder (subRoot.getLeft());
		System.out.print(" " + subRoot.getData() + " ");
		displayInOrder (subRoot.getRight());
	}
}
