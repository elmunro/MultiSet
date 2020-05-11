package pobj.tme5;

public class InvalidMultiSetFormat extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMultiSetFormat(String msg) {
		super(msg);
	}
	
	public InvalidMultiSetFormat(String msg, Throwable cause) {
		super(msg,cause);
	}
	
}
