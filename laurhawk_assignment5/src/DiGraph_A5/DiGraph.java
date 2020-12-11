package DiGraph_A5;

import java.util.*;

public class DiGraph implements DiGraphInterface {

	final static int largestInteger = 2147483647;
	public int numNodes;
	public int numEdges;
	public long id;

	HashMap<String, NodeClass> nodes = new HashMap<String, DiGraph.NodeClass>();
	HashSet<Long> nodeid = new HashSet<Long>();
	HashSet<Long> edgeid = new HashSet<Long>();

	public DiGraph() {

	}

	public static class NodeClass {
		String identifier;
		long id;
		HashMap<String, EdgeClass> inEdge;
		HashMap<String, EdgeClass> outEdge;
		long distance;
		boolean visited;

		public NodeClass(long idNum, String name) {
			id = idNum;
			identifier = name;
			// each node can have multiple edges; implement hashset
			inEdge = new HashMap<String, DiGraph.EdgeClass>();
			outEdge = new HashMap<String, DiGraph.EdgeClass>();
			distance = largestInteger;

		}

		public NodeClass(NodeClass node) {
			this.id = node.id;
			this.identifier = node.identifier;
			this.inEdge = node.inEdge;
			this.outEdge = node.outEdge;
		}
	}

	public static class EdgeClass {
		// weight (can be any integer neg, 0, or pos; 1 should be default weight)
		// optional label (user can pass in null -- its ok for label to be null)
		// each edge needs a unique id

		// may only need ids
		String sNode;
		String dNode;
		long weight;
		long id;
		String label;

		public EdgeClass(long id, String sNode, String dNode, long weight, String label) {
			// maybe default means create different constructor that just sets weight to one
			this.weight = weight;
			this.id = id;
			this.label = label;
			this.sNode = sNode;
			this.dNode = dNode;
		}

	}

	@Override
	public boolean addNode(long idNum, String label) {
		if (idNum < 0 || nodeid.contains(idNum) || label == null) {
			// checks if id number is positive or the nodes hashmap contains the value
			return false;
		}
		// checks if nodes hashmap contains label
		if (nodes.containsKey(label)) {
			return false;
		}

		NodeClass node = new NodeClass(idNum, label);
		nodes.put(label, node);
		nodes.get(label).id = idNum;
		nodeid.add(idNum);
		numNodes++;
		return true;
	}

	public boolean addEdge(long idNum, String sLabel, String dLabel, String eLabel) {
		return addEdge(idNum, sLabel, dLabel, 1, eLabel);
	}

	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight) {
		return addEdge(idNum, sLabel, dLabel, weight, null);
	}

	public boolean addEdge(long idNum, String sLabel, String dLabel) {
		return addEdge(idNum, sLabel, dLabel, 1, null);
	}

	@Override

	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		// if idNum < 0 or idNum already present returns false
		if (idNum < 0 || edgeid.contains(idNum)) {
			return false;
		}
		// boolean iterates and if you find sLabel or dLabel already within hashmap then
		// it returns false
		if (!nodes.containsKey(sLabel) || !nodes.containsKey(dLabel)) {
			return false;
		}

		// checks if edge already exists
		if (nodes.get(sLabel).outEdge.containsKey(dLabel) || nodes.get(dLabel).inEdge.containsKey(sLabel)) {
			return false;
		}

		EdgeClass edge = new EdgeClass(idNum, sLabel, dLabel, weight, eLabel);
		edgeid.add(idNum);

		edge.id = idNum;
		nodes.get(sLabel).outEdge.put(dLabel, edge);
		nodes.get(dLabel).inEdge.put(sLabel, edge);
		numEdges++;
		return true;
	}

	@Override
	public boolean delNode(String label) {
		if (nodes.containsKey(label)) {
			for (String key : nodes.get(label).inEdge.keySet()) {
				delEdge(key, label);
			}
			for (String key : nodes.get(label).outEdge.keySet()) {
				delEdge(label, key);
			}
			nodeid.remove(nodes.get(label).id);
			nodes.remove(label);

			numNodes--;
			return true;
		}
		return false;
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		if (!nodes.containsKey(sLabel) || !nodes.containsKey(dLabel)) {
			return false;
		}

		if (!nodes.get(dLabel).inEdge.containsKey(sLabel)) {
			return false;
		}

		edgeid.remove(nodes.get(sLabel).outEdge.get(dLabel).id);
		edgeid.remove(nodes.get(dLabel).inEdge.get(sLabel).id);
		nodes.get(sLabel).outEdge.remove(dLabel);
		nodes.get(dLabel).inEdge.remove(sLabel);
		numEdges--;
		return true;

	}
//	      in: string label for source node
//          string label for destination node
//      out: boolean
//             return false if the edge does not exist
//             return true if the edge is found and successfully removed

	@Override
	public long numNodes() {
		return numNodes;
	}

	@Override
	public long numEdges() {
		return numEdges;
	}

	public ShortestPathInfo[] shortestPath(String label) {
		//resets node in case the shortest path algorithm is ran twice
		
		Iterator iterate1 = nodes.entrySet().iterator();
		while (iterate1.hasNext()) {
			Map.Entry pair1 = (Map.Entry)iterate1.next();
			NodeClass node1 = (NodeClass) pair1.getValue();
			node1.visited = false;
			node1.distance = largestInteger;
		}
		
		nodes.get(label).distance = 0;
		ShortestPathInfo[] shorty = new ShortestPathInfo[numNodes];
		HashSet<String> consideration = new HashSet<String>();
		NodeComparator comparator = new NodeComparator();
		PriorityQueue<NodeClass> shortest = new PriorityQueue<NodeClass>(1000, comparator);

		for (String key : nodes.get(label).outEdge.keySet()) {
			nodes.get(label).visited = true;
			nodes.get(key).distance = nodes.get(label).outEdge.get(key).weight;
			shortest.add(nodes.get(key));
			consideration.add(key);
		}

		while (!shortest.isEmpty()) {
			NodeClass minNode = shortest.remove();
			nodes.get(minNode.identifier).visited = true;
			for (String key : nodes.get(minNode.identifier).outEdge.keySet()) {
				if (nodes.get(minNode.identifier).distance
						+ nodes.get(minNode.identifier).outEdge.get(key).weight < nodes.get(key).distance) {
					nodes.get(key).distance = nodes.get(minNode.identifier).outEdge.get(key).weight
							+ nodes.get(minNode.identifier).distance;
					shortest.add(nodes.get(key));
					consideration.add(key);
				}
			}
		}
		
		int i = 0;
		Iterator iterate = nodes.entrySet().iterator();
		while (iterate.hasNext()) {
			Map.Entry pair = (Map.Entry)iterate.next();
			NodeClass node = (NodeClass) pair.getValue();
			if (node.visited == true) {
				shorty[i] = new ShortestPathInfo(node.identifier, node.distance);
			} else {
				shorty[i] = new ShortestPathInfo(node.identifier, -1);
			}
			i++;
		}
		return shorty;
	}
}
