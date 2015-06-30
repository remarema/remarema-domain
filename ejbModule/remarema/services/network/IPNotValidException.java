package remarema.services.network;

/**
 * Dies ist eine eigens geschriebene Exception. Diese Exception wird geworfen,
 * wenn der Benutzer beim Anlegen eines neuen Clients oder beim Bearbeiten eines
 * Clients eine ungültige IP-Adresse eingibt.
 * 
 * @author Rebecca van Langelaan
 */
public class IPNotValidException extends Exception {
	private static final long serialVersionUID = 1L;

	public IPNotValidException(String s){
		super(s);
	}

}
