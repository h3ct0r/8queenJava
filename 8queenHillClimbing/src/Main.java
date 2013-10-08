
public class Main {

	static final int WORKING_TIMES = 100; 
	
	public static void main(String[] args) {
		iHeuristic heuristic = new HeuristicNumberOfConflicts();
		
		double timeSum1 = 0.0;
		int costSum1 = 0;
		int solvedPuzzles1 = 0;
		
		double timeSum2 = 0.0;
		int costSum2 = 0;
		int solvedPuzzles2 = 0;
		
		double timeSum3 = 0.0;
		int costSum3 = 0;
		int solvedPuzzles3 = 0;
		
		double timeSum4 = 0.0;
		int costSum4 = 0;
		int solvedPuzzles4 = 0;
		
		for(int h = 0; h < WORKING_TIMES; h++){
			
			System.out.println("\n--->"+h);
			
			int[] initialBoard = new int[8];
			for(int i = 0; i < initialBoard.length; i++) {
				initialBoard[i] = (int)(Math.random()*8);
			}
			
			HillClimbingRandomRestart rr = new HillClimbingRandomRestart(heuristic, initialBoard);
			timeSum1 += rr.getTime();
			costSum1 += rr.getCost();
			if(rr.isSuccess()) solvedPuzzles1++;
			
			HillClimbingSteepestAscentSearch as = new HillClimbingSteepestAscentSearch(heuristic, initialBoard);
			timeSum2 += as.getTime();
			costSum2 += as.getCost();
			if(as.isSuccess()) solvedPuzzles2++;
			
			HillClimbingFirstChoiceSearch fc = new HillClimbingFirstChoiceSearch(heuristic, initialBoard);
			timeSum3 += fc.getTime();
			costSum3 += fc.getCost();
			if(fc.isSuccess()) solvedPuzzles3++;
			
			SimulatedAnnealing sa = new SimulatedAnnealing(heuristic, initialBoard, 35.0, 0.05);
			timeSum4 += sa.getTime();
			costSum4 += sa.getCost();
			if(sa.isSuccess()) solvedPuzzles4++;
		}
		
		System.out.println("1 averageTime ["+(timeSum1/WORKING_TIMES)+"] wins ["+solvedPuzzles1+"] average cost ["+costSum1/WORKING_TIMES+"]");
		System.out.println("2 averageTime ["+(timeSum2/WORKING_TIMES)+"] wins ["+solvedPuzzles2+"] average cost ["+costSum2/WORKING_TIMES+"]");
		System.out.println("3 averageTime ["+(timeSum3/WORKING_TIMES)+"] wins ["+solvedPuzzles3+"] average cost ["+costSum3/WORKING_TIMES+"]");
		System.out.println("4 averageTime ["+(timeSum4/WORKING_TIMES)+"] wins ["+solvedPuzzles4+"] average cost ["+costSum4/WORKING_TIMES+"]");
	}

}
