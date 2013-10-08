import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;


public class HillClimbingSteepestAscentSearch {

	final private Integer MAX_SEARCH_NUMBER = 10000;
	private Node startNode = null;
	private Double elapsedTime = 0.0;
	private Integer fitness = 99;
	
	final private boolean DEBUG_ENABLED = false;
	
	public HillClimbingSteepestAscentSearch(iHeuristic heuristic, int[] board){
		this.startNode = new Node(board, null, heuristic);
		
		Stopwatch sw = new Stopwatch();
		Node endNode = startSearch();
		this.elapsedTime = sw.elapsedTime();
		
		if(endNode == null){
			System.out.println("Cant get an answer");
			System.out.println("More than "+MAX_SEARCH_NUMBER+" tries and no answer!");
		} 
		else{
			this.fitness = endNode.calculateFitness();
			//System.out.println(endNode.calculateFitness());
			System.out.println("Success with "+this.fitness);
			//printNode(endNode);
		}
	}
	
	public double getTime(){
		return this.elapsedTime;
	}
	
	public boolean isSuccess(){
		return (this.fitness > 0 ? false : true);
	}
	
	public Integer getCost(){
		return this.cost;
	}
	
	private Integer cost = 0;
	
	public Node startSearch(){
		Node currentNode = this.startNode;
		PriorityQueue<Node> successors = null;
		
		for(int i = 0; i < MAX_SEARCH_NUMBER; i++){
			if(currentNode.calculateFitness() == 0) return currentNode;
			cost++;
			
			successors = currentNode.calculateSuccessors();
			if(successors.peek().calculateFitness() >= currentNode.calculateFitness()) return currentNode;
			currentNode = successors.peek();
		}
		
		return null;
	}
	
	public void printNode(Node n) {
		System.out.println("");
		System.out.println(n.toString());
	}
	
	public void printNode(Node n, String s) {
		System.out.println("");
		System.out.println(s);
		printNode(n);
	}
}
