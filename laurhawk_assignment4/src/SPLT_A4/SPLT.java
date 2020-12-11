package SPLT_A4;

public class SPLT implements SPLT_Interface {
	private BST_Node root;
	private int size;

	public SPLT() {
		root = null;
		this.size = 0;
	}

	public BST_Node getRoot() { // please keep this in here! I need your root node to test your tree!
		return root;
	}

	@Override
	public void insert(String s) {
		// resetRoot();
		if (size() == 0) {
			root = new BST_Node(s);
			size++;
		}
		if (root.data == s) {
			return;
		}
		if (root.insertNode(s)) {
			resetRoot();
			size++;

		} else {
			resetRoot();
			return;
		}
	}

	private void resetRoot() {
		while (root.par != null) {
			root = root.par;
		}
	}

	@Override
	public void remove(String s) {
		BST_Node lefty = null; // root.containsNode(s);
		contains(s);

//if s is not in tree, return
		if (root.data != s) {
			return;
		}

//empty tree 
		if (root.data.equals(s) && size == 1) {
			root = null;
			size--;
			return;
		}
//if value to be taken out is already at root
		

//if data is equal to s...
		if (root.getData().equals(s)) {
			BST_Node lTree = root.left;
			BST_Node rTree = root.right;
			// unhook left and right subtrees
			
			
			if (lTree != null) {
				lTree.par = null;
				
				if (rTree == null) {
					lTree.right = null;
				}

				root = null;
				lefty = lTree.findtheMax();
				while (lefty.par != null) {
					lefty = lefty.par;
				}
				root = lTree;
				lTree.right = rTree;
				//if right tree is null, left subtree.right == null
				if (rTree != null) {
					rTree.par = lTree;
				}
				

			} else if (lTree == null) {
				root = rTree;
			}
			if (rTree != null) {
				rTree.par = lefty;
				if (lefty != null) {
					lefty.right = rTree;
				}
			}
			size--;
		}
	}

	@Override
	public String findMin() {
		if (size == 0) {
			return null;
		} else {
			// return minimum value in tree
			// return null;
			// is this true?
			// return root.left.data;
			root = root.findtheMin();
			return root.findtheMin().getData();
		}
	}

	@Override
	public String findMax() {
		// TODO Auto-generated method stub
		if (size == 0) {
			return null;
		} else {
			// return maximum value in tree
			// return null;
			// is this true?
			// return root.right.data;
			root = root.findtheMax();
			return root.findtheMax().getData();
		}

	}

	@Override
	public boolean empty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(String s) {
		if (empty()) {
			return false;
		}
		if (root.data != null) {
			resetRoot();
			root = root.containsNode(s);

			if (root.data == s) {
				// root = root.containsNode(s);
				return root.containsNode(s) != null;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public int size() {
		// size incremented or de-incremented in individual methods
		return size;
	}

	@Override
	public int height() {
		if (size == 0) {
			return -1;
		} else {
			// return length of longest path in tree from root to leaf
			return root.getHeight();
		}

	}
}
