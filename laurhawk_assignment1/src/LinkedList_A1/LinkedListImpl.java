package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {

	Node sentinel;
	int numElts;

	public LinkedListImpl() {
		sentinel = new Node(0);
		numElts = 0;
	}

	@Override
	public boolean insert(double elt, int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size()) {
			return false;
		}
		if (numElts == 0 && index == 0) {
			addStart(elt);
		} else {
			addIndex(elt, index);
		}

		numElts++;
		return true;
	}

	private void addStart(double elt) {
		Node temporaryNode = new Node(elt);
		sentinel.next = temporaryNode;
		sentinel.prev = temporaryNode;
		temporaryNode.prev = sentinel;
		temporaryNode.next = sentinel;

	}

	public void addIndex(double elt, int index) {
		Node prevNode = sentinel;
		Node nextNode = sentinel.next;
		// s,1,2,3,4
		// p t
		for (int i = 0; i < index; i++) {
			prevNode = prevNode.next;
			nextNode = nextNode.next;
		}

		Node node = new Node(elt);
		prevNode.next = node;
		node.next = nextNode;
		node.prev = prevNode;
		nextNode.prev = node;
	}

	@Override
	public boolean remove(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size()) {
			return false;
		} else
		if (index == 0) {
			removeStart();
		}else
		if (index > 0) {
			removeIndex(index);
		}
		numElts--;
		return true;
	}

	public void removeStart() {
		Node temporaryNode = sentinel.next;
		temporaryNode.prev.next = temporaryNode.next;
		temporaryNode.next.prev = sentinel;
		
	}

	public void removeIndex(int index) {
		Node temporaryNode = sentinel;
		if (temporaryNode != null) {
			for (int i = 0; i <= index; i++) {
				temporaryNode = temporaryNode.next;
			}
			temporaryNode.prev.next = temporaryNode.next;
			temporaryNode.next.prev = temporaryNode.prev;
		}

	}

	@Override
	public double get(int index) {
		Node currNode = sentinel.next;
		if (numElts == 0) {
			return Double.NaN;
		}
		for (int i = 0; i < index; i++) {
			currNode = currNode.getNext();
		}
		return currNode.getData();

	}

	@Override
	public int size() {
		return numElts;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		doClear();

	}

	private void doClear() {
		// TODO Auto-generated method stub
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		numElts = 0;
	}

	public Node getRoot() {
		return sentinel;
	}

}
