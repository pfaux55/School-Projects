/** Linked Number Exception Class
 * Feb 22, 2024
 * 
 * The LinkedNumber exception class is a subclass of RuntimeException
 * that throws an exception with a particular message when it is invoked
 * 
 * @author pfaux
 * 
 */
public class LinkedNumberException extends RuntimeException {

	/**
	 * This class calls the super class RuntimeException to throw an exception
	 * with a certain message provided by the instance of the class.
	 * @param msg - string value of the message to be printed out when exception is thrown
	 */
	public LinkedNumberException(String msg) {
        super(msg);
    }

}
