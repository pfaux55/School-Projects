/**
 * The FrogPath class navigates a frog across a hexagonal grid pond, 
 * optimizing its path to consume flies while avoiding alligators, mud, 
 * and seeking lily pads, reeds, or food. It leverages priority queues for 
 * path selection and stacks for path tracing, adapting to the varied terrain 
 * and obstacles represented by different cell types.
 * 
 * @author - pfaux
 */
public class FrogPath {

	// pond represents and instance of the pond class
	private Pond pond;
	
	/**
	 * Constructs a FrogPath object by initializing a pond layout from a specified file.
	 * The pond layout is read and constructed from the given filename, representing the game's environment.
	 * If the file cannot be read or another exception occurs, the exception is caught and handled silently.
	 *
	 * @param filename The path of the file containing the pond layout data.
	 */
	public FrogPath (String filename) {
		try {
            pond = new Pond(filename);
        } catch (Exception e) { }
	}
	
	/**
	 * Determines the optimal next move from the current cell, considering both immediate and if on a lily pad, the adjacent neighbors. 
	 * Prioritizes moves based on safety, strategic value, and proximity to the goal, using a unique priority queue. 
	 * Adjustments in priority are made for direct line moves to encourage efficient pathfinding. Returns null if no suitable move exists.
	 * 
	 * 
	 * MAX_VALUE is a double constant to assign an unwanted cell a much higher priority than the rest
	 * @param currCell The current Hexagon cell from which the frog is making a move.
	 * @return The next best Hexagon cell for the frog to move to, or null if no suitable move is found.
	 */
	public Hexagon findBest(Hexagon currCell) {
		final double MAX_VALUE = 25.0;
	    ArrayUniquePriorityQueue<Hexagon> priorityCells 
	    = new ArrayUniquePriorityQueue<>();
	    if (currCell.isLilyPadCell() || currCell.isStart()) {
		  //inner neighbour
		    for (int j = 0; j < 6; j++) {
		    	Hexagon innerNeighbour = currCell.getNeighbour(j);
		        if (innerNeighbour != null) {
		            double priority = getPriority(innerNeighbour);
		            
		            if (priority < MAX_VALUE) {
		                priorityCells.add(innerNeighbour, priority);
		            }
		        }
		    }
		    for (int i = 0;i<6;i++) {
               Hexagon newInnerNeighbour = currCell.getNeighbour(i);
            // outer neighbour (neighbours of inner neighbour)
	            for (int j = 0; j < 6; j++) {
	            	if (newInnerNeighbour != null) {	
		                Hexagon outerNeighbour = newInnerNeighbour.getNeighbour(j);
		                if (outerNeighbour != null && !outerNeighbour.equals(currCell) && 
		                		!outerNeighbour.equals(newInnerNeighbour)) {
		                    double priority = getPriority(outerNeighbour);
		                    if (priority < MAX_VALUE) {
		                    	// if cell is in a straight line to currCell
		                    	if(i == j) {
		                    		priority += 0.5;
		                    	}
		                    	else {
		                    		priority += 1.0;
		                    	}
		                        priorityCells.add(outerNeighbour, priority);
		                    }
		                }
		            }
	            }
		    } 

	    }
	    
	    else {
	    	//inner neighbour
		    for (int j = 0; j < 6; j++) {
				

		        Hexagon innerNeighbour = currCell.getNeighbour(j);
		        if (innerNeighbour != null) {
		            double priority = getPriority(innerNeighbour);
		            
		            if (priority < MAX_VALUE) {
		                priorityCells.add(innerNeighbour, priority);
		            }
		        }
		    }
	    }
	    
	    if (priorityCells.isEmpty()) {
	        return null; // No suitable neighbor found
	    }
	    return priorityCells.peek(); // Returns the Hexagon with the highest priority (lowest numerical value)
	}
	
	/**
	 * Assigns a priority to a Hexagon cell based on its type and strategic value. Higher priorities
	 * discourage movement to that cell, while lower priorities encourage it. Factors considered include
	 * the presence of marked cells, alligators, mud cells flies, and proximity to goals or dangers.
	 *
	 * MAX_VALUE is a double constant to assign an unwanted cell a much higher priority than the rest
	 * @param evalCell The Hexagon cell to evaluate.
	 * @return The calculated priority, with lower values being more desirable.
	 */
	private double getPriority(Hexagon evalCell) {
		final double MAX_VALUE = 25.0;
		double priority = MAX_VALUE;
		
		
    	if (evalCell.isMarked() || evalCell.isAlligator() 
    			|| evalCell.isMudCell()) return priority;
    	else if (evalCell.isEnd()) priority = 3.0;
    	else if (gatorNear(evalCell)) {
    		if (evalCell.isReedsCell()) {
    			priority = 10.0;
    		}
    		else {
    			return priority;
    		}	
    	}
    	else if (evalCell instanceof FoodHexagon) {
			int numFlies = ((FoodHexagon) evalCell).getNumFlies();
	    	if (numFlies == 3) priority = 0.0;
	    	else if (numFlies == 2) priority = 1.0;
	    	else if (numFlies == 1) priority = 2.0;
		}
    	else if (evalCell.isLilyPadCell()) {
    		priority = 4.0;
    	}
    	else if (evalCell.isReedsCell()) priority = 5.0;
    	else if (evalCell.isWaterCell()) priority = 6.0;
    	return priority;
	}
	
	/**
	 * Checks for the presence of an alligator in adjacent cells to the given Hexagon cell.
	 *
	 * @param checkCell The cell to evaluate for nearby alligators.
	 * @return true if an alligator is in any neighboring cell, false otherwise.
	 */
	private boolean gatorNear(Hexagon checkCell) {
		for (int i = 0; i < 6; i++) {
	            Hexagon neighbour = checkCell.getNeighbour(i);
	            if (neighbour != null) {
	            	if (neighbour.isAlligator()) return true;
	            }
	        }
	        return false;   	
	}
	
	/**
	 * Computes the optimal path through the pond from start to end, collecting flies. 
	 * It tracks visited cells and flies eaten, using a stack for backtracking when necessary. 
	 * Returns a string of cell IDs visited and total flies eaten or a no-solution message.
	 *
	 * @return String detailing the path taken and flies eaten or "No solution" if no path exists.
	 */
	public String findPath() {
		ArrayStack<Hexagon> cellsVisited = new ArrayStack<Hexagon>();
		
		cellsVisited.push(pond.getStart());
		pond.getStart().markInStack();
		int fliesEaten = 0;
		String IDsVisited = "";
		
		while(!(cellsVisited.isEmpty())) {
			Hexagon curr = cellsVisited.peek();
			IDsVisited += curr.getID() + " ";
			
			if (curr.isEnd()) break;
			else if (curr instanceof FoodHexagon) {
				fliesEaten += ((FoodHexagon) curr).getNumFlies();
				((FoodHexagon) curr).removeFlies();
			}
					
			Hexagon next = findBest(curr);
			if (next == null) {
				cellsVisited.pop();
				curr.markOutStack();
			}
			else {
				cellsVisited.push(next);
				next.markInStack();
			}
			
		}
		if (cellsVisited.isEmpty()) IDsVisited = "No solution";
		else IDsVisited += "ate " + fliesEaten + " flies";
		
		return IDsVisited;
	}
}
