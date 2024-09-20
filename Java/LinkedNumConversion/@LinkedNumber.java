/** Linked Number Class
 * Feb 22, 2024
 * 
 * The LinkedNumber class models numbers as doubly linked lists to facilitate 
 * operations like base conversion, digit manipulation, and validation within 
 * a specified numerical base. It supports initializing numbers from strings or 
 * integers, adding or removing digits at specific positions, and checking the 
 * validity of the number for its base. 
 * 
 * @author pfaux
 * 
 * 
 * @param base - represents the base of the number system of which the 
 * instance number is
 * @param front - represents the front of the Linked Number
 * @param rear - represents the rear of the Linked Number
 
 */
public class LinkedNumber {
	private int base;
	private DLNode<Digit> front;
	private DLNode<Digit> rear;
	
	/*
	 * LinkedNumber is a constructor that takes a string number and a base number as parameters
	 * and constructs a doubly linked list of digits.
	 * @param  num - number as a string to be converted into a linked list
	 * @param baseNum - represents the base of the number system of which the 
	 * instance number is
	 * @throws exception if no numbers are given
	 */
	public LinkedNumber(String num, int baseNum) {
		this.base = baseNum;
		
		if (num == null || num.isEmpty()) { // Check if num is an empty string
            throw new LinkedNumberException("no digits given");
        }

        front = null;
        rear = null;
        
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            Digit digit = new Digit(c);
            DLNode<Digit> newNode = new DLNode<>(digit);
            
            if (front == null) { // This means the list is empty
                front = newNode;
                rear = newNode;
            } else {
                rear.setNext(newNode); // Link the new node as the next of the current rear
                newNode.setPrev(rear); // Set the current rear as the previous of the new node
                rear = newNode; // Move rear to the new node
            }
        }
    }
	
	
	/*
	 * LinkedNumber is an overloading constructor that takes an integer number as a parameter
	 * and constructs a doubly linked list of digits.
	 * @param  num - number as an int to be converted into a linked list
	 * @throws exception if no numbers are given
	 */
	public LinkedNumber(int num) {
		String numStr = String.valueOf(num);
		this.base = 10;
		if (numStr.isEmpty()) {
            throw new LinkedNumberException("no digits given");
        }

        front = null;
        rear = null;

        // Iterate through each character of the string representation of num
        for (int i = 0; i < numStr.length(); i++) {
            char c = numStr.charAt(i);
            Digit digit = new Digit(c); // Assuming Digit can be initialized with a char
            DLNode<Digit> newNode = new DLNode<>(digit);

            if (front == null) {
                front = newNode; // First node becomes both front and rear
                rear = newNode;
            } else {
                rear.setNext(newNode); // Link new node as next of current rear
                newNode.setPrev(rear); // Set current rear as prev of new node
                rear = newNode; // Update rear to new node
            }
        }	
	}
	
	/*
	 * isValidNumber scans through the constructed doubly linked list and determines if each
	 * element is a valid number compared to the base number system it is in.
	 * @return whether it is a valid number or not
	 */
	public boolean isValidNumber() {
		DLNode<Digit> currentNode = front; // Start from the beginning of the list
	    while (currentNode != null) {
	        int digitValue = currentNode.getElement().getValue();
	        // Check if the digit value is within the valid range for the base
	        if (digitValue < 0 || digitValue >= base) {
	            return false; // Digit is not valid for the base
	        }
	        currentNode = currentNode.getNext(); // Move to the next node
	    }
	    return true; // All digits were valid
	}
	
	/*
	 * getBase retrieves the value of the base number for the instance
	 * @return the base number
	 */
	public int getBase() {
		return this.base;
	}
	/*
	 * getFront retrieves the value of the front number for the list instance
	 * @return the front value
	 */
	public DLNode<Digit> getFront(){
		return front;
	}
	/*
	 * getRear retrieves the value of the rear number for the list instance
	 * @return the rear value
	 */
	public DLNode<Digit> getRear(){
		return rear;
	}
	/*
	 * getNumDigits gets the length of the list by counting the nodes
	 * @return the list length
	 */
	public int getNumDigits() {
		int count = 0; // Initialize counter
	    DLNode<Digit> current = front; // Start with the first node

	    while (current != null) { // Traverse the list until you reach the end
	        count++; // Increment counter for each node
	        current = current.getNext(); // Move to the next node
	    }

	    return count; // Return the total count of nodes
	}
	
	/*
	 * toString creates a visual representation of the list instance by
	 * adding the nodes to a string
	 * @return the string version of the list
	 */
	public String toString() {
		String listRep = "";
		DLNode<Digit> current = front; 

	    while (current != null) { 
	    	listRep += current.getElement();
	        current = current.getNext(); 
	    }
		return listRep;
	}
	
	/*
	 * equals determines if two objects or lists of LinkedNumber are equal
	 * by comparing each node
	 * @param other - different list being compared to the current list instance
	 * @return whether or not two lists are the same
	 */
	public boolean equals (LinkedNumber other) {
		if (this.base == other.base && this.getNumDigits() == other.getNumDigits()) {
			DLNode<Digit> thisCurrent = this.front; 
			DLNode<Digit> otherCurrent = other.front; 
		    while (thisCurrent != null && otherCurrent != null) { 
		    	if (!thisCurrent.getElement().equals(otherCurrent.getElement())) {
		    		return false;
		    	}
		        thisCurrent = thisCurrent.getNext(); 
		        otherCurrent = otherCurrent.getNext();
		    }
		}
		else {
			return false;
		}
		return true;
	}
	
	/*
	 * baseNumToRawNum takes a number in its base form and converts it to  its value
	 * @param original - linkedNumber instance
	 * @return the raw value of the base number
	 */
	private int baseNumToRawNum(LinkedNumber original) {
		int rawNum = 0;
		int counter = 0;
		int currentChar;
		
		DLNode<Digit> current = original.rear; 

	    while (current != null) { 
	    	currentChar = current.getElement().getValue();
	    	currentChar = currentChar * (int)Math.pow(original.getBase(), counter);
	    	
	    	rawNum += currentChar;
	        current = current.getPrev(); 
	        counter++;
	    }
	    return rawNum;
	}
	
	/*
	 * rawNumToBaseNum takes a number in its raw value and converts it to its base value
	 * given the base number system for it to be converted into
	 * @param rawNum - int of a number as a raw value
	 * @param newBase - int of  base number system for it to be converted into
	 * @return the string base number of the converted raw value given
	 */
	private String rawNumToBaseNum(int rawNum, int newBase) {
		if (rawNum == 0) {
	        return "0"; // Directly handle the special case of 0
	    }
		StringBuilder baseNum = new StringBuilder();
	    while (rawNum > 0) {
	        int newDig = rawNum % newBase;
	        if (newDig >= 10) {
	            char newChar = (char) ('A' + (newDig - 10)); // Handle hexadecimal A-F
	            baseNum.insert(0, newChar);
	        } else {
	            baseNum.insert(0, newDig); // Handle numeric digits
	        }
	        rawNum /= newBase;
	    }
	    return baseNum.toString();
	}
	
	/*
	 * convert takes a number in its base form and converts it to its new base value
	 * given the new base number system for it to be converted into
	 * @param newBase - int of  base number system for it to be converted into
	 * @return the list object base number of the converted int value given
	 * @throws exception if invalid number is given
	 */
	public LinkedNumber convert(int newBase) {
		if(this.isValidNumber()){
			int val = this.baseNumToRawNum(this);
			 LinkedNumber newVal = new LinkedNumber(this.rawNumToBaseNum(val, newBase), newBase);
			 return newVal;
		}
		else {
			throw new LinkedNumberException("cannot convert invalid number");
		}
	}

	/*
	 * addDigit adds a digit to a certain position in the instance list given the 
	 * position of it and the value of the digit being added
	 * 
	 * @param position - int of  base number system for it to be converted into
	 * @param digit - the digit being added as an object of the digit class
	 * @throws exception if invalid position is given
	 */
	public void addDigit(Digit digit, int position) {
		DLNode<Digit> newNode = new DLNode<>(digit);
		int n = this.getNumDigits();
		if (position >= 0 && position <= n) {
			if (position == 0) {
		        if (rear != null) { // List is not empty
		            newNode.setPrev(rear);
		            rear.setNext(newNode);
		            rear = newNode;
		        } else { // List is empty
		            front = newNode;
		            rear = newNode;
		        }
		    }
		    // Adding at the beginning (before the current front)
		    else if (position == n) {
		        if (front != null) { // List is not empty
		            newNode.setNext(front);
		            front.setPrev(newNode);
		            front = newNode;
		        } else { // List is empty
		            front = newNode;
		            rear = newNode;
		        }
		    }
		    // Adding in the middle or at the front (logical position within the list)
		    else {
		        DLNode<Digit> current = rear;
		        for (int i = 0; i <= (position - 1); i++) { // Traverse to the position before insertion
		            current = current.getPrev();
		        }

		        // Insert before the current node for positions 0 to n
		        if (current == null) { // If inserting at position n and list was empty
		            front = newNode;
		            rear = newNode;
		        } else {
		            newNode.setNext(current.getNext());
		            newNode.setPrev(current);
		            if (current.getNext() != null) { // Middle of the list
		                current.getNext().setPrev(newNode);
		            } else { // End of the list
		                front = newNode;
		            }
		            current.setNext(newNode);
		        }
		    }
		}
		else {
			throw new LinkedNumberException("invalid position");
		}
	}
	
	/*
	 * removeDigit removes a particular digit of the instance list given
	 * the position of the digit within the list
	 * 
	 * @param position - int of  base number system for it to be converted into
	 * @throws exception if invalid position is given
	 */
	public int removeDigit(int position) {
	    DLNode<Digit> current = rear;
	    int n = this.getNumDigits();
	    if (position < 0 || position >= n) { // Ensure position is within bounds
	        throw new LinkedNumberException("invalid position");
	    }

	    int removedValue = 0; // Placeholder for the removed node's value

	    // Traverse to the node at the specified position
	    for (int i = 0; i < position; i++) {
	        current = current.getPrev();
	    }

	    // Calculate the value of the node to be removed
	    removedValue = current.getElement().getValue() * (int)Math.pow(10, position);

	    // Remove the node from the list
	    if (current.getPrev() != null) {
	        current.getPrev().setNext(current.getNext());
	    } else {
	        // This means current is the front node, adjust front.
	        front = current.getNext();
	    }
	    
	    if (current.getNext() != null) {
	        current.getNext().setPrev(current.getPrev());
	    } else {
	        // This means current is the rear node, adjust rear.
	        rear = current.getPrev();
	    }

	    // If removing the only element, update both front and rear to null.
	    if (n == 1) {
	        front = rear = null;
	    }

	    LinkedNumber removedValObj = new LinkedNumber(String.valueOf(removedValue), this.getBase());
	    int convertedRemVal = baseNumToRawNum(removedValObj);
	    // Return the value of the removed node
	    return convertedRemVal;
	}
}
