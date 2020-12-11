package BST_A2;

public class BST implements BST_Interface {
	public BST_Node root;
	int size;

	public BST() {
		size = 0;
		root = null;
	}

	public boolean insert(String s) {
		if (size() == 0) {
			root = new BST_Node(s);
			size++;
			return true;
		}
		if (root.insertNode(s)) {
			size++;
			root.insertNode(s);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean remove(String s) {
		if (!root.removeNode(s) || size() == 0) {
			return false;
		} else {
			root.removeNode(s);
			size--;
			return true;
		}
	}

	@Override
	public String findMin() {
		if (size == 0) {
			return null;
		} else {
			return root.findNodeMin().getData();
		}
	}

	@Override
	public String findMax() {
		if (size == 0) {
			return null;
		} else {
			return root.findNodeMax().getData();
		}
	}

	@Override
	public boolean empty() {
		if (size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(String s) {
		return root.containsNode(s);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		return root.getHeight();
	}

	@Override
	public BST_Node getRoot() {
		return root;
	}

}
