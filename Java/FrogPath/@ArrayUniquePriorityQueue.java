/**
 * The ArrayUniquePriorityQueue class manages a collection of unique elements 
 * sorted by priority low to high, supporting dynamic expansion, element insertion with 
 * priority, and priority updates. It's optimized for scenarios requiring ordered 
 * access based on priority, such as scheduling tasks or navigating paths.
 * 
 * @author - pfaux
 */
public class ArrayUniquePriorityQueue<T> implements UniquePriorityQueueADT<T>{
	
	// queue represents the queue array of data items
	private T[] queue;
	// priority represents the queue array of priorities
	private double[] priority;
	// count represents the number of times in queue and priority
	private int count;
	
//	Initializes an ArrayUniquePriorityQueue with default capacity. The queue 
//	array and priority array are set to hold 10 elements initially, and the 
//	element count is initialized to 0, preparing the queue for use.
	public ArrayUniquePriorityQueue () {
		queue = (T[]) new Object[10];
        priority = new double[10];
        count = 0;
	}
	
	/**
	 * Adds an element with its associated priority to the priority queue.
	 * If the element already exists in the queue, it does not add it again, ensuring uniqueness.
	 * This method automatically expands the queue's capacity if the current size is about to be exceeded.
	 * It inserts the new element in the correct position based on its priority to maintain the queue's order.
	 * 
	 * @param data The element to be added to the priority queue.
	 * @param prio The priority of the element, with lower values indicating higher priority.
	 */
	public void add(T data, double prio) {
		if(contains(data)) {
			return;
		}
		
		//expand size
		if (size() == getLength() - 1) {
			T[] newQueue = (T[]) new Object[getLength() + 5];
			double[] newPrio = new double[getLength() + 5];
			for (int i = 0; i < size(); i++) {
				newQueue[i] = queue[i];
				newPrio[i] = priority[i];
			}
			queue = newQueue;
			priority = newPrio;
		}
		
		if (isEmpty()) {
			queue[0] = data;
			priority[0] = prio;
			count++;
			return;
		}
		
		
		// Find the correct position for the new element based on its priority
		int position;
		for (position = 0; position < count && priority[position] <= prio; position++) {}

		// Shift elements to the right to make space for the new element
		for (int j = count; j > position; j--) {
		    queue[j] = queue[j - 1];
		    priority[j] = priority[j - 1];
		}

		// Insert the new element at the identified position
		queue[position] = data;
		priority[position] = prio;
		count++;

	}
	
	/**
	 * Checks if the queue contains the specified element.
	 * 
	 * @param data The element to search for within the queue.
	 * @return true if the element is found, false otherwise.
	 */
	public boolean contains (T data) {
		for (int i = 0; i < size(); i++) {
			if(data != null && data.equals(queue[i])) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retrieves, but does not remove, the lowest-priority element of this queue.
	 * 
	 * @return The lowest-priority element.
	 * @throws CollectionException if the priority queue is empty.
	 */
	public T peek () throws CollectionException{
		if (isEmpty()) {
			throw new CollectionException("PQ is empty");
		}
		return queue[0];
	}
	
	/**
	 * Removes and returns the highest-priority element of this queue.
	 * 
	 * @return The element that was removed from the queue.
	 * @throws CollectionException if the priority queue is empty.
	 */
	public T removeMin () throws CollectionException{
		if (isEmpty()) {
			throw new CollectionException("PQ is empty");
		}
		T removedVal = queue[0];
		
		for(int i = 0; i < size(); i ++) {
			queue[i] = queue[i+1];
		}
		count--;
		return removedVal;
	}
	
	/**
	 * Updates the priority of an existing element in the queue. If the element does not exist, throws an exception.
	 * This method first removes the element and then re-adds it with the new priority to ensure the queue remains correctly ordered.
	 * 
	 * @param data The element whose priority is to be updated.
	 * @param newPrio The new priority value for the element.
	 * @throws CollectionException if the element is not found in the queue.
	 */
	public void updatePriority (T data, double newPrio) throws CollectionException{
		if (!(contains(data))){
			throw new CollectionException("Item not found in PQ");
		}
		
		// Locate the element to remove
		int indexToRemove = 0;
		while (indexToRemove < count && !queue[indexToRemove].equals(data)) {
		    indexToRemove++;
		}

		if (indexToRemove < count) { 
		    // Shift elements left to remove the found element
		    for (int j = indexToRemove; j < count - 1; j++) {
		        queue[j] = queue[j + 1];
		        priority[j] = priority[j + 1];
		    }
		    count--; // Decrease count as one element is removed

		    // Re-add the element with the new priority
		    add(data, newPrio);
		}
		
	}
	
	/**
	 * Checks if the queue is empty.
	 * 
	 * @return true if the queue contains no elements, false otherwise.
	 */
	public boolean isEmpty () {
		if (size() <= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the number of elements in the queue.
	 * 
	 * @return The size of the queue.
	 */
	public int size () {
		return count;
	}

	/**
	 * Returns the current capacity of the queue.
	 * 
	 * @return The length of the underlying array used to store the elements of the queue.
	 */
	public int getLength () {
		return queue.length;
	}
	
	/**
	 * Provides a string representation of the queue showing all elements and their priorities.
	 * This method iterates through the queue and appends each element and its priority to a string, formatted for easy reading.
	 * 
	 * @return A string representation of the queue.
	 */
	public String toString () {
		if (isEmpty()) {
			return "The PQ is empty";
		}
		String queueVis = "";
		for(int i = 0; i < size() - 1; i++) {
			queueVis += queue[i] + " " + "[" + priority[i] + "], ";
		}
		queueVis += queue[size() - 1] + " " + "[" + priority[size() - 1] + "]";
		return queueVis;
	}
	
	/**
	 * Sorts the queue by priority using a bubble sort-like algorithm. 
	 * It arranges the elements so that lower priority values precede 
	 * higher ones, ensuring the queue is properly ordered. 
	 * The sorting continues until no further swaps are required.
	 */
	private void sortQueue() {
	    T tempVal;
	    double tempPri;
	    boolean swapMade = true; 
	    while (swapMade) {
	        swapMade = false; 

	        for (int i = 0; i < size() ; i++) {
	            if (priority[i] > priority[i + 1]) {
	                tempVal = queue[i];
	                queue[i] = queue[i + 1];
	                queue[i + 1] = tempVal;

	                // Swap priorities
	                tempPri = priority[i];
	                priority[i] = priority[i + 1];
	                priority[i + 1] = tempPri;
	                swapMade = true;
	            }
	        }
	    }
	}

}
