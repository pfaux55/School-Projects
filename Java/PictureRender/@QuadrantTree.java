/**
 * CS1027 Assignment 4 
 * Implements a quadrant tree for efficient image representation at varying resolutions. 
 * Nodes represent quadrants of the image, with operations to build the tree, search by color, 
 * and navigate the structure. Useful for applications in image processing and spatial partitioning.
 *
 * @author pfaux
 * @date April 2nd, 2024
 */
public class QuadrantTree {

	QTreeNode root;
	
	/**
	 * Constructs a QuadrantTree from a 2D array of pixels.
	 * @param thePixels The square 2D array of pixels representing an image, where the size is a power of 2.
	 */
	public QuadrantTree (int[][] thePixels) {
	    // Assuming thePixels is square and size is a power of 2
	    root = buildTreeRecur(0, 0, null, thePixels.length, thePixels);
	}

	/**
	 * Recursively builds the quadrant tree from a section of the image.
	 * @param x The x-coordinate of the upper-left corner of the current quadrant.
	 * @param y The y-coordinate of the upper-left corner of the current quadrant.
	 * @param parent The parent node of the current node being created.
	 * @param size The size of the current quadrant.
	 * @param matrix The 2D array of pixels representing the image.
	 * @return The root node of the constructed quadrant subtree.
	 */
	private QTreeNode buildTreeRecur(int x, int y, QTreeNode parent, int size, int [][] matrix) {
	    if (size == 1) {
	        // Directly return a leaf node with the pixel's color
	        int leafColor = matrix[y][x]; // y for row, x for column
	        return new QTreeNode(null, x, y, size, leafColor);
	    } else {
	        int halfSize = size / 2;
	        int quadrantAverageColor = Gui.averageColor(matrix, x, y, size);
	        QTreeNode quadrantNode = new QTreeNode(new QTreeNode[4], x, y, size, quadrantAverageColor);
	        
	        // Upper left child
	        quadrantNode.setChild(buildTreeRecur(x, y, quadrantNode, halfSize, matrix), 0);
	        // Upper right child
	        quadrantNode.setChild(buildTreeRecur(x + halfSize, y, quadrantNode, halfSize, matrix), 1);
	        // Lower left child
	        quadrantNode.setChild(buildTreeRecur(x, y + halfSize, quadrantNode, halfSize, matrix), 2);
	        // Lower right child
	        quadrantNode.setChild(buildTreeRecur(x + halfSize, y + halfSize, quadrantNode, halfSize, matrix), 3);

	        return quadrantNode;
	    }
	}

	/**
	 * Gets the root of the Quadrant Tree.
	 * @return The root node of the Quadrant Tree.
	 */
	public QTreeNode getRoot() {
		return this.root;
	}
	
	/**
	 * Retrieves all nodes at a specified level of the tree as a linked list.
	 * @param r The root node of the subtree from which to retrieve nodes.
	 * @param theLevel The level of the tree from which to retrieve nodes.
	 * @return A ListNode containing all nodes at the specified level.
	 */
	public ListNode<QTreeNode> getPixels(QTreeNode r, int theLevel){
		if (r.isLeaf() || theLevel == 0) {
			return new ListNode<QTreeNode>(r);
		}
		
		ListNode<QTreeNode> pixList = null;
		ListNode<QTreeNode> lastNode = null;
		
		
		// append children of parents to list for specific parent where
//		children are at the level being appended
		// recurse if we are not at the last parent
		// combine the lists of each recursion together
		for(int i = 0; i < 4; i ++) {
			ListNode<QTreeNode> childList = getPixels(r.getChild(i), theLevel - 1);
			
			if (pixList == null) {
				pixList = childList;
				if(lastNode == null) {
					lastNode = childList;
				}
				
			}
			else {
				lastNode.setNext(childList);
			}
			
			while (lastNode != null && lastNode.getNext() != null) {
                lastNode = lastNode.getNext();
            }
			
		}
		return pixList;
	}
	
	/**
	 * Finds nodes matching a specific color at a specified level of the tree.
	 * @param r The root node of the subtree to search.
	 * @param theColor The color to match.
	 * @param theLevel The level of the tree to search.
	 * @return A Duple object containing a list of matching nodes and the count of such nodes.
	 */
	public Duple findMatching(QTreeNode r, int theColor, int theLevel) {
	    if (r == null) {
	        return new Duple();
	    }

	    Duple matchesFound = new Duple();

	    if ((theLevel == 0 || r.isLeaf()) && Gui.similarColor(r.getColor(), theColor)) {
	        matchesFound.setFront(new ListNode<QTreeNode>(r)); 
	        matchesFound.setCount(1); 
	        return matchesFound;
	    }

	    if (theLevel > 0 && !r.isLeaf()) {
	        ListNode<QTreeNode> currentTail = null; 

	        for (int i = 0; i < 4; i++) {
	            QTreeNode child = r.getChild(i);

	            if (child != null) {
	                Duple childDuple = findMatching(child, theColor, theLevel - 1);

	                if (childDuple.getCount() > 0) { 
	                    if (matchesFound.getFront() == null) {
	                    	
	                        matchesFound.setFront(childDuple.getFront());
	                        currentTail = matchesFound.getFront();
	                        
	                        while (currentTail.getNext() != null) {
	                            currentTail = currentTail.getNext(); 
	                        }
	                    } else {
	                        currentTail.setNext(childDuple.getFront());
	                        
	                        while (currentTail.getNext() != null) {
	                            currentTail = currentTail.getNext(); 
	                        }
	                    }
	                    matchesFound.setCount(matchesFound.getCount() + childDuple.getCount()); // Update the count.
	                }
	            }
	        }
	    }

	    return matchesFound; 
	}

	/**
	 * Finds a node that contains a specific point at a specified level of the tree.
	 * @param r The root node of the subtree to search.
	 * @param theLevel The level of the tree to search.
	 * @param x The x-coordinate of the point.
	 * @param y The y-coordinate of the point.
	 * @return The node containing the point at the specified level, or null if no such node exists.
	 */
	public QTreeNode findNode(QTreeNode r, int theLevel, int x, int y) {
	    // Base case: if node is null or level is negative
	    if (r == null || theLevel < 0) {
	        return null; 
	    }

	    // If the target level is reached and the point is within this node
	    if (theLevel == 0 && r.contains(x, y)) {
	        return r;
	    }
	    
	    // If we're above the target level but within the bounds of this node,
	    // recursively search the children
	    if (r.contains(x, y)) {
	        QTreeNode resultNode = null;
	        for(int quadrantIndex = 0; quadrantIndex < 4; quadrantIndex++) {
	            QTreeNode childNode = r.getChild(quadrantIndex);
	            resultNode = findNode(childNode, theLevel - 1, x, y);
	            // If a matching node is found in the children, break the loop
	            if (resultNode != null) {
	                break;
	            }
	        }
	        return resultNode;
	    }

	    // If the node doesn't contain the point or no child contains the point
	    return null;
	}
}
