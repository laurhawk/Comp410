package SPLT_A4;

// everything happens in splay tree except splay method, delegating regular case to bst node method
public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;
	BST_Node par;
	boolean justMade;

	BST_Node(String data) {
		this.data = data;
		this.justMade = true;
	}

	BST_Node(String data, BST_Node left, BST_Node right, BST_Node par) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.par = par;
		this.justMade = true;
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

	public BST_Node containsNode(String s) { // it was me
		if (data.equals(s)) {
			splay(this);
			return this;
		}
		if (data.compareTo(s) > 0) {// s lexiconically less than data
			if (left == null) {
				splay(this);
				return this;
			}
			return left.containsNode(s);
		}
		if (data.compareTo(s) < 0) {
			if (right == null) {
				splay(this);
				return this;
			}
			return right.containsNode(s);
		}
		return this; // shouldn't hit
	}

	public boolean insertNode(String s) {
		if (data.compareTo(s) > 0) {
			if (left == null) {
				left = new BST_Node(s);
				left.par = this;
				splay(left);
				return true;
			}
			return left.insertNode(s);
		}
		if (data.compareTo(s) < 0) {
			if (right == null) {
				right = new BST_Node(s);
				right.par = this;
				splay(right);
				return true;
			}
			return right.insertNode(s);
		}
		else {
			containsNode(s);
			return false;// ie we have a duplicate
		}

	}

	public BST_Node findtheMin() {
		if (left != null) {
			return left.findtheMin();
		}
		splay(this);
		return this;
	}

	public BST_Node findtheMax() {
		if (right != null) {
			return right.findtheMax();
		}
		splay(this);
		return this;
	}

	public int getHeight() {
		int l = 0;
		int r = 0;
		if (left != null)
			l += left.getHeight() + 1;
		if (right != null)
			r += right.getHeight() + 1;
		return Integer.max(l, r);
	}

	public String toString() {
		return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}

	private void splay(BST_Node toSplay) {
		while (toSplay.par != null) {

			if (toSplay.par.par == null) {
				if (toSplay == toSplay.par.left) {
					rightRotate(toSplay.par);
				} else {
					leftRotate(toSplay.par);
				}
				// says that toSplay is equal to the left child of the parent
			} else if (toSplay == toSplay.par.left && toSplay.par == toSplay.par.par.left) {
				// zig zig
				rightRotate(toSplay.par.par);
				rightRotate(toSplay.par);
				// says that toSplay is equal to right child of parent
			} else if (toSplay == toSplay.par.right && toSplay.par == toSplay.par.par.right) {
				leftRotate(toSplay.par.par);
				leftRotate(toSplay.par);
			} else if (toSplay == toSplay.par.right && toSplay.par == toSplay.par.par.left) {
				leftRotate(toSplay.par);
				rightRotate(toSplay.par);
			} else {
				rightRotate(toSplay.par);
				leftRotate(toSplay.par);
			}
		}
	}

	private void leftRotate(BST_Node a) {
		BST_Node grandparent = a.par;
		BST_Node b = a.right;
		BST_Node d = b.left;
		
		if (grandparent != null) {
			if (a == grandparent.left) {
				grandparent.left = b;
			} else {
				grandparent.right = b;
			}
		}
		b.par = grandparent;
		a.par = b;
		b.left = a;
		
		
		if (d != null) {
			d.par = a;
		}
		a.right = d;
	}

	private void rightRotate(BST_Node b) {
		BST_Node grandparent = b.par;
		BST_Node a = b.left;
		BST_Node d = a.right;
		
		if (grandparent != null) {
			if (b == grandparent.left) {
				grandparent.left = a;
			} else {
				grandparent.right = a;
			}
		}
		a.par = grandparent;
		b.par = a;
		a.right = b;
		
		if (d != null) {
			d.par = b;
		}

		b.left = d;
	}
	// you could have this return or take in whatever you want..so long as it will
	// do the job internally. As a caller of SPLT functions, I should really have no
	// idea if you are "splaying or not"
	// I of course, will be checking with tests and by eye to make sure you are
	// indeed splaying
	// Pro tip: Making individual methods for rotateLeft and rotateRight might be a
	// good idea!

}
