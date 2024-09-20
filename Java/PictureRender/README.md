# **Quadrant Tree Image Processing Project**

## **Author**: Peter Faux

## **Description**
This project implements a quadrant tree structure to efficiently process and manipulate images. The core functionality revolves around storing, querying, and manipulating image data using quadrant trees, which help in optimizing spatial operations such as image compression, rendering, and region-based queries.

## **Requirements**
- **Java Development Kit (JDK)** version 8 or above
- **IDE** (such as IntelliJ IDEA, Eclipse, or NetBeans) for running and testing the Java files
- **Image Processing Libraries** (if any external ones are used)

## **Project Structure**

- **@QTreeNode.java**: Defines the nodes of the quadrant tree, including the properties and methods to manipulate node data.
- **@QuadrantTree.java**: The main class for constructing and managing the quadrant tree, including operations like insertion, deletion, and searching within the tree.
- **Board.java**: Handles the representation of the image or board where the quadrant tree is applied, including drawing and updating methods.
- **CheckTree.java**: Contains validation methods to ensure the integrity and correctness of the quadrant tree structure.
- **DrawImage.java**: Responsible for rendering the image using the data stored within the quadrant tree.
- **Duple.java**: Manages pairs of data points or coordinates used within the quadrant tree structure.
- **Gui.java**: Provides a graphical user interface for interacting with the image processing functions and visualizing the quadrant tree.
- **ListNode.java**: Defines the linked list nodes that may be used in conjunction with the quadrant tree for additional data management.
- **QTreeException.java**: Custom exception class for handling errors specific to the quadrant tree operations.
- **TestQuadrant.java**: Contains test cases and methods to validate the functionality of the quadrant tree and its associated operations.

## **How to Use**
1. **Compile the Project**: Compile all the `.java` files using your preferred Java IDE or command line.
2. **Run the Application**: Execute the `Gui.java` to open the graphical interface, allowing you to load and manipulate images using the quadrant tree structure.
3. **Test the Functionality**: Use `TestQuadrant.java` to run predefined tests ensuring that the quadrant tree behaves as expected.

## **How it Works**
- **Quadrant Tree** 
. Initialization and Structure
@QuadrantTree.java: This file contains the core class that manages the construction and operations of the quadrant tree. The quadrant tree is initialized by dividing an image or a 2D space into four quadrants.
Root Node: The tree starts with a single root node that represents the entire image.
Child Nodes: The root node has four child nodes, each representing one of the quadrants (Top-Left, Top-Right, Bottom-Left, Bottom-Right).
2. Recursive Subdivision
Subdivision Process:
The tree recursively divides the image or space into smaller quadrants until a specific condition is met. This could be based on a threshold, such as a region having uniform color or a minimum resolution.
@QTreeNode.java manages these subdivisions by creating child nodes as needed. Each node in the tree represents a specific region of the image.
3. Image Data Storage
Data Handling:
Each node within the quadrant tree stores relevant data about the region it covers. For instance, if the region is homogeneous (all pixels the same color), the node will store that color value.
In cases where the region is not homogeneous, the node will continue to subdivide into smaller quadrants until the regions are sufficiently small or homogeneous.
4. Image Processing Operations
Efficient Queries and Manipulations:

The quadrant tree allows for efficient querying and manipulation of the image. For example, to find a specific region, the tree can be traversed quickly by examining only the relevant quadrants.
Operations like image compression or rendering can take advantage of the tree structure by focusing on non-homogeneous areas, reducing the amount of data processed.
@DrawImage.java: Uses the quadrant tree to render the image. Instead of processing the entire image, it only needs to process the visible or relevant quadrants, significantly improving performance.

5. Validation and Integrity Checks
Tree Validation:
The CheckTree.java class ensures that the tree is correctly structured and that all subdivisions meet the required conditions. This class helps to maintain the integrity of the tree, ensuring that the data remains accurate throughout operations like insertion, deletion, or updates.

- The **Gui** provides interaction with this structure, allowing operations like zooming, panning, and selective rendering.
- The **DrawImage** class uses the quadrant tree to render the image efficiently by only processing the necessary portions.
- The **CheckTree** validates the integrity of the quadrant tree, ensuring that operations like insertion and deletion maintain the correct structure.
