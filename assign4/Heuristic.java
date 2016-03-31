public interface Heuristic {

	public void calculateHeuristic( PuzzleNode currentState, PuzzleGrid goalState );

	public float getHeuristicValue();
	
}
