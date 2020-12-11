package BST_A2;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;

	BST_Node(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public BST_Node getLeft() {
		return left;
	}

	public BST_Node getRight() {
		return right;
	}

	public boolean containsNode(String s) {
		return recursiveContains(this, s);
	}

	public boolean recursiveContains(BST_Node node, String s) {
		if (node == null) {
			return false;
		}
		if (s.compareTo(node.data) > 0) {
			return recursiveContains(node.right, s);
		}
		if (s.compareTo(node.data) < 0) {
			return recursiveContains(node.left, s);
		}
		return true;
	}

	public BST_Node recursiveInsert(BST_Node node, String s) {
		if (node == null) {
			return new BST_Node(s);
		}
		int compare = s.compareTo(node.data);
		if (compare < 0) {
			node.left = recursiveInsert(node.left, s);
		}
		if (compare > 0) {
			node.right = recursiveInsert(node.right, s);
		} else
			;
		return node;
	}

	public boolean insertNode(String s) {
		if (this.containsNode(s)) {
			return false;
		} else {
			recursiveInsert(this, s);
			return true;
		}
	}

	public BST_Node recursiveRemove(BST_Node node, String s) {
		if (node == null) {
			return null;
		}
		
		if (s.compareTo(node.data) < 0) {
			node.left = recursiveRemove(node.left, s);
		}
		if (s.compareTo(node.data) > 0) {
			node.right = recursiveRemove(node.right, s);
		} else {
			if (node.right != null && node.left == null) {
				node = node.right;
			} else if (node.right == null && node.left != null) {
				node = node.left;
			} else if (node.right != null && node.left != null) {
				BST_Node max = Max(node.left);
				node.data = max.data;
				node.left = recursiveRemove(node.left, max.data);
			} else if (node.right == null && node.left == null) {
				node = null;
			} 
			else {
				node = (node.left == null) ? node.left : node.right;
			}
		} 
		return node;
	}

	public boolean removeNode(String s) {
		if (this.containsNode(s)) {
			recursiveRemove(this, s);
			return true;
		} else {
			return false;
		}
	}

	public BST_Node findNodeMin() {
		return Min(this);
	}

	public BST_Node Min(BST_Node node) {
		BST_Node curr = node;
		if (curr != null) {
			while (curr.left != null) {
				curr = curr.left;
			}
		}
		return curr;
	}

	public BST_Node findNodeMax() {
		return Max(this);
	}

	public BST_Node Max(BST_Node node) {
		BST_Node curr = node;
		if (curr != null) {
			while (curr.right != null) {
				curr = curr.right;
			}
		}
		return curr;
	}

	public int getHeight() {
		return height(this);
	}

	public int height(BST_Node node) {
		if (node == null) {
			return -1;
		} else {
			return Math.max(height(node.left), height(node.right)) + 1;
		}

	}

	public String toString() {
		return "Data: " + this.data + " , Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}

}
