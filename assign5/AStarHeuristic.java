public class AStarHeuristic implements Heuristic {
	
	private float heuristic = 0.0f;
	public AStarHeuristic() { }
	
	public void calculateHeuristic( PuzzleNode currentState, PuzzleGrid goalState ){

		PuzzleGrid currentGrid = currentState.getGrid();
		int size = goalState.getWidth() * goalState.getHeight();
		int[] currentPosition = new int[2];
		int[] destination = new int[2];
		int distance;
		int totalDistance = 0;
		
		for ( int i = 0; i < size; i++){
			currentPosition = currentGrid.getPositionInGrid( i );
			destination = goalState.getPositionInGrid( i );
			distance = Math.abs( currentPosition[0] - destination[0]) + 
					Math.abs( currentPosition[1] - destination[1]);
			totalDistance += distance;	
		}
		
		
		int costSoFar = 0;
		PuzzleNode parent = currentState;
		
		while ( parent != null ){
			parent = parent.getParent();
			costSoFar++;
		}
		
		heuristic = totalDistance + costSoFar;
	}

	public float getHeuristicValue(){
		return heuristic;
	}

}
