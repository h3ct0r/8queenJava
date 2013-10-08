import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;


public class SimulatedAnnealing {

	final private Integer MAX_SEARCH_NUMBER = Integer.MAX_VALUE;
	private Node startNode = null;
	private Double elapsedTime = 0.0;
	private Integer fitness = 99;
	private Integer cost = 0;

	private Double initTemp;
	private Double decayRate;
	private Double freezingTemperature = 0.0;
	
	final private boolean DEBUG_ENABLED = false;
	
	public SimulatedAnnealing(iHeuristic heuristic, int[] board, Double initTemp, Double decayRate){
		this.startNode = new Node(board, null, heuristic);
		this.initTemp = initTemp;
		this.decayRate = decayRate;
		
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
	
	public Node startSearch(){
		Double currentTemperature = this.initTemp;
		Double currentStabilizer = 35.0;
		Double stabilizingFactor = 1.005;
		Node currentNode = this.startNode;
		Node nextNode = null;
		List<Node> successors = null;
		Double systemEnergy = (double) currentNode.calculateFitness();
		Double energyDelta = .0;
		
		for(int i = 0; i < MAX_SEARCH_NUMBER; i++){
			if (currentTemperature > freezingTemperature){
				for (int h = 0; h < currentStabilizer; h++) {
					if(currentNode.getFitness() == 0) return currentNode;
					cost++;
					
					successors = currentNode.calculateSuccessorsList();
					nextNode = successors.get((int)(Math.random()*successors.size()));
					
					Double energy = (double) nextNode.calculateFitness();
	                energyDelta = energy - systemEnergy;
	                
	                if (probabilityFunction(currentTemperature, energyDelta)) {
	                    currentNode = nextNode;
	                    systemEnergy = energy;
	                }
	            }
				currentTemperature = currentTemperature - decayRate;
	            currentStabilizer = currentStabilizer * stabilizingFactor;
	        }
			else{
				currentTemperature = freezingTemperature;
				return currentNode;
			}
		}
        
		return null;
	}
	
	private int[] generateRandomBoard(){
		int[] initialBoard = new int[8];
		for(int i = 0; i < initialBoard.length; i++) {
			initialBoard[i] = (int)(Math.random()*8);
		}
		return initialBoard;
	}
	
	private Boolean probabilityFunction(Double temperature, Double delta) {
        if (delta < 0) {
            return true;
        }

        Double c = Math.exp(-delta / temperature);
        Double r = Math.random();

        if (r < c) {
            return true;
        }

        return false;
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
