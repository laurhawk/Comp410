package MinBinHeap_A3;

//import java.util.Arrays;

public class MinBinHeap implements Heap_Interface {

	private EntryPair[] array; // load this array
	private int size = 0;
	private static final int arraySize = 10000; // are we supposed to do anything else or leave this method final?
												// Everything in the array will initially
												// be null. This is ok! Just build out
												// from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); // 0th will be unused for simplicity
													// of child/parent computations...
													// the book/animation page both do this.
	}

	// Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() {
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		if (size < arraySize) {
			array[size + 1] = entry;
			size++;
			int index = (int) Math.floor(size / 2);
			for (int i = index; i >= 1; i--) {
				up();
			}
		}
	}

	@Override
	public void delMin() {
		if (size < 1) {
			return;
		}
		if (size == 1) {
			size--;
			array[1] = null;
		} else {
			array[1] = array[size];
			array[size] = null;
			size--;
			down(1);
		}
	}

	@Override
	public EntryPair getMin() {
		return array[1];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {

		for (int i = 0; i < entries.length; i++) {
			array[i + 1] = entries[i];
		}
		size = entries.length;
		int index = (int) Math.floor(size / 2);
		for (int i = index; i >= 1; i--) {
			down(i);
		}
	}

	private int getLeftChildIndex(int parentIndex) {
		return 2 * parentIndex;
	}

	private int getRightChildIndex(int parentIndex) {
		return 2 * parentIndex + 1;
	}

	private int getParentIndex(int childIndex) {
		return (childIndex) / 2;
	}

	private boolean hasParent(int index) {
		return getParentIndex(index) >= 1;
	}

	private EntryPair leftChild(int index) {
		return array[getLeftChildIndex(index)];
	}

	private EntryPair rightChild(int index) {
		return array[getRightChildIndex(index)];
	}

	private EntryPair parent(int index) {
		return array[getParentIndex(index)];
	}

	private void swap(int indexOne, int indexTwo) {
		EntryPair temp = array[indexOne];
		array[indexOne] = array[indexTwo];
		array[indexTwo] = temp;
	}

	public void up() {
		int index = size;
		while (hasParent(index)) {
			if (parent(index).getPriority() > array[index].getPriority()) {
				swap(getParentIndex(index), index);
				index = getParentIndex(index);
			} else {
				break;
			}
		}
	}

	public void down(int i) {
		int curr = i;
		if (leftChild(curr) != null && rightChild(curr) != null) {
			int currPriority = array[curr].getPriority();
			int leftChildPriority = leftChild(curr).getPriority();
			int rightChildPriority = rightChild(curr).getPriority();
			if (currPriority < leftChildPriority && currPriority < rightChildPriority) {
				return;
			} else if (currPriority < leftChildPriority || currPriority < rightChildPriority) {
				if (leftChildPriority < rightChildPriority) {
					EntryPair swap = leftChild(curr);
					array[getLeftChildIndex(curr)] = array[curr];
					array[curr] = swap;
					down(getLeftChildIndex(curr));
				} else {
					EntryPair swap = rightChild(curr);
					array[getRightChildIndex(curr)] = array[curr];
					array[curr] = swap;
					down(getRightChildIndex(curr));
				}
			} else {
				// current is larger than both children
				if (leftChildPriority < rightChildPriority) {
					EntryPair swap = leftChild(curr);
					array[getLeftChildIndex(curr)] = array[curr];
					array[curr] = swap;
					down(getLeftChildIndex(curr));
				} else {
					EntryPair swap = rightChild(curr);
					array[getRightChildIndex(curr)] = array[curr];
					array[curr] = swap;
					down(getRightChildIndex(curr));
				}
			}

		} else if (leftChild(curr) == null && rightChild(curr) == null) {
			return;
		} else {
			int currPriority = array[curr].getPriority();
			int leftChildPriority = leftChild(curr).getPriority();
			if (leftChildPriority < currPriority) {
				EntryPair swap = leftChild(curr);
				array[getLeftChildIndex(curr)] = array[curr];
				array[curr] = swap;
				down(getLeftChildIndex(curr));
			}
		}

	}
}
