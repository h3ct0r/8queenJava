
public class HeuristicNumberOfConflicts implements iHeuristic {

	int[] board = null;
	int fitnessValue = 0;
	
	public HeuristicNumberOfConflicts(int[] board){
		this.board = board;
		calculateFitness();
	}
	
	public HeuristicNumberOfConflicts(){
	}
	
	@Override
	public int calculateFitness() {
		return fitnessValue = calculateFitness(this.board);
	}
	
	@Override
	public int calculateFitness(int[] board) {
		/*
		 * Calculate if the delta values are equal for validate diagonals
		 * if x1 - x2 == y1 - y2 then the queens are facing each other
		 */
		int fitness = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(j == i) continue;
				// same row validation
				if (board[i] == board[j]){
					fitness++;
				}
				// diagonal validation
				if (Math.abs(i - j) == Math.abs(board[i] - board[j])){
					fitness++;
				}
			}
		}
		return fitness;
	}
	
	public int getFitness(){
		return this.fitnessValue;
	}

}
