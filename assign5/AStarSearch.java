import java.util.ArrayList;
import java.util.LinkedList;

public class AStarSearch implements SolutionFinder {

	private ArrayList<PuzzleNode> statesVisited = new ArrayList<PuzzleNode>();
	
	private LinkedList<PuzzleNode> openStates = new LinkedList<PuzzleNode>();
	
	private ArrayList<PuzzleGrid> solution = new ArrayList<PuzzleGrid>();
	

	private long runTime = 0; 
	private long startTime, endTime;
	
	public AStarSearch() { }
	
	public ArrayList<PuzzleGrid> findSolution(PuzzleGrid startState, PuzzleGrid goalState)
	{

		ArrayList<PuzzleNode> children = new ArrayList<PuzzleNode>();
		
		solution = new ArrayList<PuzzleGrid>();
		PuzzleNode currentState = null;
		
		boolean solutionFound = false, stateVisited = false;
		int nodeCounter = 0;
		
		openStates.offer( new PuzzleNode( startState, new AStarHeuristic() ) );
		
		startTime = System.nanoTime();
		
		while(!openStates.isEmpty() && solutionFound == false){

			currentState = openStates.poll();
			nodeCounter++;
			
			currentState.getGrid().printGrid();

			if(currentState.getGrid().equalTo(goalState)){
				solutionFound = true;
				break;
			}
			
			children = currentState.createChildren();
			for ( int i=0; i < children.size(); i++ )
			{
				
				PuzzleNode child = children.get(i);
				stateVisited = false;
				
				for ( PuzzleNode newNode : statesVisited )
				{
					if ( child.equalTo( newNode ) ) 
					{ 
						System.out.println( "\t Closed \n" );
						stateVisited = true;
						break;
					}
				}
				
				for ( PuzzleNode newNode : openStates )
				{

					if ( child.equalTo( newNode ) ) 
					{ 
						System.out.println( "\t Opened \n" );
						stateVisited = true;
						break;
					}
				}
				
				if ( stateVisited != true )
				{
					boolean inserted = false;
					
					child.determineHeuristic( goalState );
					
					for ( int j = 0; j < openStates.size(); j++ )
					{
						if ( child.getHeuristicValue() < openStates.get(j).getHeuristicValue() ) 
						{
							openStates.add( j, child);
							inserted = true;
							break;
						}
					}
					
					if ( inserted == false ) openStates.offer( child );
					
					child.getGrid().printGridAsChild();
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
			return null;
		}
		
		return solution;
	}
	
	public void printSearchData()
	{
		System.out.println(" ");
	}

}
