package Q4_01_Route_Between_Nodes;

import java.util.LinkedList;
import java.util.Queue;

public class Hector {
	
	static boolean search(Graph g, Node start, Node end) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(start);
		
		while (!queue.isEmpty()) {
			Node currentNode = queue.remove();
			currentNode.state = Question.State.Visited;
			if (currentNode == end) {
				return true;
			}
			for (Node node : currentNode.getAdjacent()) {
				if (node.state != Question.State.Visited) {
					queue.add(node);
				}
			}
		}
		return false;
	}
	
	public static void main(String a[])
	{
		Graph g = createNewGraph();
		Node[] n = g.getNodes();
		Node start = n[3];
		Node end = n[5];
		System.out.println(search(g, start, end));
	}
	
	public static Graph createNewGraph()
	{
		Graph g = new Graph();        
		Node[] temp = new Node[6];

		temp[0] = new Node("a", 3);
		temp[1] = new Node("b", 0);
		temp[2] = new Node("c", 0);
		temp[3] = new Node("d", 1);
		temp[4] = new Node("e", 1);
		temp[5] = new Node("f", 0);

		temp[0].addAdjacent(temp[1]);
		temp[0].addAdjacent(temp[2]);
		temp[0].addAdjacent(temp[3]);
		temp[3].addAdjacent(temp[4]);
		temp[4].addAdjacent(temp[5]);
		for (int i = 0; i < 6; i++) {
			g.addNode(temp[i]);
		}
		return g;
	}

}
