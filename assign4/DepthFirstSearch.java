import java.util.ArrayList;
import java.util.Stack;


public class DepthFirstSearch implements SolutionFinder {
	private ArrayList<PuzzleNode> statesVisited = new ArrayList<PuzzleNode>();
	private Stack<PuzzleNode> openStates = new Stack<PuzzleNode>(); // pop = get from top; push = add to top;
	private ArrayList<PuzzleGrid> solution = new ArrayList<PuzzleGrid>();
	

	private long runTime = 0;
	private long startTime, endTime;
	
	public DepthFirstSearch() { };	
	
	public ArrayList<PuzzleGrid> findSolution(PuzzleGrid startState, PuzzleGrid goalState){

		solution = new ArrayList<PuzzleGrid>();
		
		ArrayList<PuzzleNode> children = new ArrayList<PuzzleNode>();
		PuzzleNode currentState = null;
		
		boolean solutionFound = false, stateVisited = false;
		int nodeCounter = 0;
		
		openStates.push( new PuzzleNode( startState, new EmptyHeuristic() ) );
		
		startTime = System.nanoTime();
		
		while ( !openStates.isEmpty() && solutionFound == false ){
			currentState = openStates.pop();
			nodeCounter++;
			
//			currentState.getGrid().printGrid();


			if ( currentState.getGrid().equalTo( goalState )){
				solutionFound = true;
				break;
			}
			
			children = currentState.createChildren();
			for ( int i=children.size()-1; i >= 0; i-- ){
		
				stateVisited = false;
				for ( PuzzleNode newNode : statesVisited ){

					if ( children.get(i).equalTo( newNode ) ){ 
						stateVisited = true;
						break;
					}
				}
				
				for ( PuzzleNode newNode : openStates ){

					if ( children.get(i).equalTo( newNode ) ){ 
						stateVisited = true;
						break;
					}
				}
				
				
				if ( stateVisited != true ){
					openStates.push( children.get(i) );
//					children.get(i).getGrid().printGridAsChild(); // Wont print child if in statesVisited
				}
			}
			
			statesVisited.add( currentState );	
			openStates.remove( currentState );		
		}
		
		endTime = System.nanoTime();
		runTime = (endTime - startTime) / 1000000;
		
		if ( solutionFound == true )
		{
			boolean pathFound = false;
			solution.add( currentState.getGrid() );
			while ( pathFound == false )
			{
				currentState = currentState.getParent();
				solution.add( 0, currentState.getGrid() );
				if ( currentState.getGrid().equalTo( startState ) ) pathFound = true;
			}
		} 
		else{
			System.out.println( "Solution not found.");
		}
		
		return solution;
	}

	public void printSearchData() {
		System.out.println( " " );
	}

	
}
