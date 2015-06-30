package remarema.services.network;

/**
 * Dies ist eine selbst definierte Exception. Die Exception wird geworfen, wenn
 * versucht wird ein Parent-Network zu löschen, welches aber noch Child-Networks
 * besitzt.
 * 
 * @author Rebecca van Langelaan
 *
 */
public class ChildNotEmptyException extends Exception {
	private static final long serialVersionUID = 1L;

	ChildNotEmptyException(String s) {
		super(s);
	}

}
