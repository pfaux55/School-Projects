# Frog Path

## Author
Peter Faux

## Description
Frog Path is a Java-based project designed to simulate a frog navigating a hexagonal grid pond. The goal is for the frog to optimize its path to consume flies while avoiding alligators and mud, and seeking out lily pads, reeds, or food. The project demonstrates advanced algorithmic techniques, custom data structures, and exception handling in a complex, grid-based environment.

## Requirements

- Java Development Kit (JDK) installed on your system
## Project Structure

- **FrogPath.java**: Manages the frog's navigation across the pond, using a priority queue for path selection and a stack for backtracking. Adapts to varied terrains and obstacles in the pond.
- **Pond.java**: Represents the pond environment, including loading the pond layout, managing the hexagonal grid, and providing methods to interact with the pond.
- **Hexagon.java** and **FoodHexagon.java**: Represent individual hexagonal cells in the pond, including special types like those containing food (flies). Handle interactions with neighboring cells and manage the cell's state.
- **ArrayUniquePriorityQueue.java**: Implements a unique priority queue, ensuring elements are sorted by priority and preventing duplicates. Supports dynamic expansion and priority updates.
- **ArrayStack.java**: Implements a dynamic stack with operations for pushing, popping, and peeking elements, crucial for pathfinding and state management.
- **CellComponent.java**: Likely represents the visual or logical components of a cell, assisting with the interaction and rendering of hexagonal cells.
- **Exception Handling**: Custom exception classes manage errors related to collections, invalid map formats, and command-line arguments:
  - **IllegalArgumentException.java**
  - **InvalidMapException.java**
  - **InvalidNeighbourIndexException.java**
  - **CollectionException.java**
- **HexLayout.java**: Supports the organization and positioning of cells in the hexagonal grid, ensuring the correct layout of the pond.
- **Test Classes**: Validates core functionalities and ensures consistent behavior:
  - **TestPath.java**
  - **TestUPQ.java**
  - **TestSetup.java**

## How to Use

### **Compile the Program:**
Open a terminal and navigate to the directory containing the `TestPath.java` file. 
Compile the program using the following command:

  	
  	javac TestPath.java

## How It Works

1. **Initialization**: The program begins by reading a pond layout from a specified file. This layout is used to create a grid of hexagonal cells, each representing a different terrain type (e.g., lily pads, mud, reeds, etc.).

2. **Pathfinding Algorithm**: The frog starts at a designated starting cell and uses a priority queue to determine the optimal next move. The algorithm prioritizes safety, proximity to flies, and avoidance of hazards like alligators.

3. **Dynamic Terrain Handling**: As the frog moves across the pond, it encounters various types of terrain. The program adjusts the frog's path based on the characteristics of these terrains, such as seeking out flies on food cells or avoiding dangerous alligator cells.

4. **Backtracking**: If the frog reaches a dead-end or no beneficial move is available, the program uses a stack to backtrack to the previous position and re-evaluate the path.

5. **Output**: The program outputs the sequence of cells visited by the frog and the total number of flies consumed, providing a detailed summary of the path taken.

