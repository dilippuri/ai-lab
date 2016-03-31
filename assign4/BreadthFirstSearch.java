import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;


public class BreadthFirstSearch implements SolutionFinder{

	private ArrayList<PuzzleNode> statesVisited = new ArrayList<PuzzleNode>();
	private ArrayList<PuzzleGrid> solution = new ArrayList<PuzzleGrid>();
	private LinkedList<PuzzleNode> openStates = new LinkedList<PuzzleNode>();
	

	private long runTime = 0;
	private long startTime, endTime;
	
	public BreadthFirstSearch() { }
	
	public ArrayList<PuzzleGrid> findSolution(PuzzleGrid startState, PuzzleGrid goalState){

		solution = new ArrayList<PuzzleGrid>();

		ArrayList<PuzzleNode> children = new ArrayList<PuzzleNode>();
		PuzzleNode currentState = null;
		
		boolean solutionFound = false, stateVisited = false;
		int nodeCounter = 0;
		openStates.offer( new PuzzleNode( startState, new EmptyHeuristic() ) );
		
		startTime = System.nanoTime();
		
		while ( !openStates.isEmpty() && solutionFound == false ){

			currentState = openStates.poll();
			nodeCounter++;
			
//			System.out.println( "Current = " + nodeCounter );
//			currentState.getGrid().printGrid();

			if ( currentState.getGrid().equalTo( goalState ) ){
				solutionFound = true;
				break;
			}
			
			children = currentState.createChildren();
			for ( int i=0; i < children.size(); i++ ){

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
					openStates.offer( children.get(i) );
					
//					children.get(i).getGrid().printGridAsChild(); 
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
			while ( pathFound == false ){
				currentState = currentState.getParent();
				solution.add( 0, currentState.getGrid() );
				if ( currentState.getGrid().equalTo( startState ) ) pathFound = true;
			}
		} 
		else{
			System.out.println( "solution not found." );
		}
		
		return solution;
	}
	
	public void printSearchData(){
		System.out.println( " " );
	}

}
