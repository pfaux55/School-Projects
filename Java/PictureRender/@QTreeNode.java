/**
 * 
 * Represents a node in a quadrant tree, encapsulating properties for image representation.
 * Nodes store information about a specific quadrant of an image, including its size, color, 
 * and position, as well as references to parent and child nodes for navigation.
 *
 * @author pfaux
 * @date April 2nd, 2024
 */
public class QTreeNode {
	
	private int x;
	private int y;
	private int size;
	private int color;
	private QTreeNode parent;
	private QTreeNode[] children;
	
	
	/**
     * Default constructor initializes a node with default properties.
     */
	public QTreeNode() {
		parent = null;
		children = new QTreeNode[4];
		x = 0;
		y = 0;
		size = 0;
		color = 0;
	}
	
	/**
     * Initializes a node with specified properties and children.
     * @param theChildren Array of child nodes.
     * @param xcoord The x-coordinate of the node's quadrant.
     * @param ycoord The y-coordinate of the node's quadrant.
     * @param theSize The size of the node's quadrant.
     * @param theColor The average color of the node's quadrant.
     */
	public QTreeNode(QTreeNode[] theChildren, int xcoord, 
			int ycoord, int theSize, int theColor) {
		parent = null;
		x = xcoord;
		y = ycoord;
		size = theSize; 
		color = theColor;
		children = theChildren;
	}
	
	/**
     * Determines if a given point is within the node's quadrant.
     * @param xcoord The x-coordinate of the point.
     * @param ycoord The y-coordinate of the point.
     * @return True if the point is within the quadrant, false otherwise.
     */
	public boolean contains(int xcoord, int ycoord) {
		if (xcoord >= this.x && xcoord <= (this.x+size-1) && ycoord >= this.y && ycoord <= (this.y+size-1)) {
			return true;
		}
		return false;
	}
	
	/**
     * Gets the x-coordinate of the node's quadrant.
     * @return The x-coordinate.
     */
	public int getx() {
		return x; 
	}
	
	 /**
     * Gets the y-coordinate of the node's quadrant.
     * @return The y-coordinate.
     */
	public int gety() {
		return y; 
	}
	
	/**
     * Gets the size of the node's quadrant.
     * @return The size.
     */
	public int getSize() {
		return size; 
	}
	
	/**
     * Gets the average color of the node's quadrant.
     * @return The color.
     */
	public int getColor() {
		return color; 
	}
	
	/**
     * Gets the parent of this node.
     * @return The parent node.
     */
	public QTreeNode getParent() {
		return parent; 
	}
	
	/**
     * Gets a specific child node.
     * @param index The index of the child to retrieve.
     * @return The child node at the given index.
     * @throws QTreeException If the index is out of bounds.
     */
	public QTreeNode getChild(int index) throws QTreeException {
		if (children == null) {
			throw new QTreeException("get child is null");
		}
		else if(index < 0 || index > 3) {
			throw new QTreeException("wrong index of get child");
		}
		return children[index];
	}
	
	/**
     * Sets the x-coordinate of the node's quadrant.
     * @param newx The new x-coordinate.
     */
	public void setx(int newx) {
		x = newx;
	}
	
	/**
     * Sets the y-coordinate of the node's quadrant.
     * @param newy The new y-coordinate.
     */
	public void sety(int newy) {
		y = newy;
	}
	
	/**
     * Sets the size of the node's quadrant.
     * @param newSize The new size.
     */
	public void setSize(int newSize) {
		size = newSize;
	}
	
	/**
     * Sets the color of the node's quadrant.
     * @param newColor The new color.
     */
	public void setColor(int newColor) {
		color = newColor;
	}
	
	/**
     * Sets the parent of this node.
     * @param newParent The new parent node.
     */
	public void setParent(QTreeNode newParent) {
		parent = newParent;
	}
	
	/**
     * Sets a child node at a given index.
     * @param newChild The child node to set.
     * @param index The index at which to set the child.
     * @throws QTreeException If the index is out of bounds.
     */
	public void setChild(QTreeNode newChild, int index) throws QTreeException {
		if (children == null || index < 0 || index > 3) {
			throw new QTreeException("error");
		}
		children[index] = newChild;
	}
	
	/**
     * Checks if the node is a leaf (i.e., has no children).
     * @return True if the node is a leaf, false otherwise.
     */
	public boolean isLeaf() {
		if (children == null) {
			return true;
		}
		for (int i = 0; i < children.length; i ++) {
			if (children[i] != null) {
				return false;
			}
		}
		return true;
	}
}
