import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Node implements Comparable<Object> {
	private Node parent;
	private int[] board;
	private Integer fitness = Integer.MAX_VALUE;
	private iHeuristic heuristic;

	public Node(int[] board, Node parent, iHeuristic heuristic) {
		this.board = board;
		this.parent = parent;
		this.heuristic = heuristic;

		calculateFitness();
	}

	public int calculateFitness() {
		return this.fitness = this.heuristic.calculateFitness(board);
	}

	public Node setParent(Node parentNode) {
		return this.parent = parentNode;
	}

	public Node getParent() {
		return this.parent;
	}
	
	public iHeuristic getHeuristic() {
		return this.heuristic;
	}

	public int[] getBoard() {
		return this.board;
	}

	public int getFitness() {
		return this.fitness;
	}

	public PriorityQueue<Node> calculateSuccessors() {
		PriorityQueue<Node> successors = new PriorityQueue<Node>();
		int neighbourCounter = 0;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {

				if (board[i] != j) {
					neighbourCounter++;
					int[] temp = board.clone();

					temp[i] = j;
					Node negihbor = new Node(temp, this, this.heuristic);
					successors.add(negihbor);
				}
			}
		}
		
		//System.out.println("Neighbour counter ["+neighbourCounter+"]");
		return successors;
	}
	
	public List<Node> calculateSuccessorsList() {
		List<Node> successors = new LinkedList<Node>();
		int neighbourCounter = 0;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {

				if (board[i] != j) {
					neighbourCounter++;
					int[] temp = board.clone();

					temp[i] = j;
					Node negihbor = new Node(temp, this, this.heuristic);
					successors.add(negihbor);
				}
			}
		}
		
		//System.out.println("Neighbour counter ["+neighbourCounter+"]");
		return successors;
	}

	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 8; i++){
			for(int h = 0; h < 8; h++){
				if(this.board[h] == i) sb.append("|Q");
				else sb.append("|_");
			}
			sb.append("|\n");
		}
		return sb.toString();
	}
	
	@Override
	public int compareTo(Object input) {
		if (this.getFitness() < ((Node) input).getFitness()) return -1;
		else if (this.getFitness() > ((Node) input).getFitness()) return 1;
		return 0;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Node) {
			Node n = (Node) o;
			if (n.getFitness() != this.getFitness())
				return false;
			return Arrays.equals(((Node) o).getBoard(), this.board);
		}
		return false;
	}
}
