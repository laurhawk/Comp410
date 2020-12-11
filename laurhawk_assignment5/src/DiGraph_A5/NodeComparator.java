package DiGraph_A5;

import java.util.Comparator;

import DiGraph_A5.DiGraph.NodeClass;

public class NodeComparator implements Comparator<NodeClass>{
	public int compare(NodeClass n1, NodeClass n2) {
		if(n1.distance < n2.distance) {
			return -1;
		} else if (n1.distance > n2.distance) {
			return 1;
		} else {
			return 0;
		}
	}
}
