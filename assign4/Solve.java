import java.util.ArrayList;

public class Solve {

	private SolutionFinder search;

	public static void main(String[] args) {		
		Solve solver = new Solve();
		
		solver.Run(args[0], args[1], args[2]);			
	}
	
	public Solve() { }

	public void Run(String startFilename, String goalFilename, String searchAlgorithm){

		ArrayList<PuzzleGrid> solution = new ArrayList<PuzzleGrid>();
		PuzzleGrid gridStart = new PuzzleGrid( startFilename ); 
		PuzzleGrid gridGoal = new PuzzleGrid( goalFilename );

		System.out.println("Our initial input.\n");
		gridStart.printGrid();
		System.out.println("Our goal.\n");
		gridGoal.printGrid();
		System.out.println("\n \n steps \n");		
		
		switch( searchAlgorithm ){
		case "bfs":
			search = new BreadthFirstSearch();
			break;
		case "dfs":
			search = new DepthFirstSearch();
			break;
		default:
			break;
		}	
		
		if (search != null){
			solution = search.findSolution(gridStart, gridGoal);
			
			if ( solution != null ){
				for ( int i = 0; i < solution.size(); i++ )
				{
				solution.get(i).printGrid();
				}
			}
			else{
				System.out.println("Solution not found.\n");
			}
			
		}
		else{
			System.out.println("put correct input.\n");
		}
	}

}
