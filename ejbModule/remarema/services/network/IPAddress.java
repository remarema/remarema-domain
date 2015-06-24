package remarema.services.network;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Diese Klasse stellt sicher, dass es sich bei einer IP-Adresse auch wirklich
 * um eine gültige IPv4-Adresse handelt. Hierfür wurde ein Regex-Pattern
 * geschrieben.
 * 
 * Um Benutzer auf eine falsche IPv4-Adressen-Eingabe hinweisen zu können- wurde
 * eine eigene Exception <code>IPNotValidException</code> geschrieben. Der
 * Benutzer wird darauf hingewiesen, welcher Teil der IP-Adresse falsch verfasst
 * wurde.
 * 
 * @author Rebecca van Langelaan
 *
 */

public class IPAddress {

	private int byte0;
	private int byte1;
	private int byte2;
	private int byte3;

	/**
	 * Dieser Konstruktor wird benötigt, um anhand von 4 Bytes eine IP-Adresse
	 * zu erstellen.
	 * Es wird weiters die Methode {@link #validateByteRange(int)} aufgerufen. 
	 * 
	 * @param byte0
	 * @param byte1
	 * @param byte2
	 * @param byte3
	 * @throws IPNotValidException
	 */
	public IPAddress(int byte0, int byte1, int byte2, int byte3)
			throws IPNotValidException {
		validateByteRange(byte0);
		validateByteRange(byte1);
		validateByteRange(byte2);
		validateByteRange(byte3);
		this.byte0 = byte0;
		this.byte1 = byte1;
		this.byte2 = byte2;
		this.byte3 = byte3;
	}

	/**
	 * Dieser Konstruktor wird benötigt, um einen Integer-Wert, der
	 * aus der Datenbank ausgelesen wurde, in eine IP-Adresse umzuwandeln. 
	 * @param sourceValue
	 * 			IP-Adresse als Integer-Wert
	 */
	public IPAddress(int sourceValue) {
		this.byte0 = (sourceValue >> 24) & 0xFF;
		this.byte1 = (sourceValue >> 16) & 0xFF;
		this.byte2 = (sourceValue >> 8) & 0xFF;
		this.byte3 = sourceValue & 0xFF;
	}

	/**
	 * Diese Methode verwendet Bitshifting, um aus den 4 Bytes zu je 8 Bits, aus
	 * denen eine IP-Adresse besteht, einen Integer, bestehend aus 32 Bits, zu
	 * machen.
	 * 
	 * @return IPAdresse als Integer-Wert
	 */
	public int toInt() {
		return ((byte0) << 24) | ((byte1) << 16) | ((byte2) << 8) | byte3;
	}

	/**
	 * Die Methode enthält das Regex-Pattern. Die Funktion ist vom Typ IPAddress
	 * und benötigt als Parameter eine IP-Adresse in Form eines Strings. Zu
	 * Begin wird ein Matcher-Objekt erstellt und der Ausdruck
	 * <code>Pattern.compile(regex).matcher(ipString)</code> aufgerufen. Statt
	 * dem Begriff <code>regex</code>, wird das Pattern eingesetzt. Stimmt die
	 * übergebene IP-Adresse mit dem Pattern überein, wird dem Benutzer die
	 * IP-Adresse zurückgegben. Stimmt sie nicht überein, wird eine
	 * <code>IPNotValidException</code> geworfen.
	 * 
	 * @param ipString
	 * @return IPAddress
	 * @throws IPNotValidException
	 */
	public static IPAddress parse(String ipString) throws IPNotValidException {
		Matcher matcher = Pattern
				.compile(
						"(?<Byte0>[0-9]{1,3})\\.(?<Byte1>[0-9]{1,3})\\.(?<Byte2>[0-9]{1,3})\\.(?<Byte3>[0-9]{1,3})")
				.matcher(ipString);
		if (!matcher.matches()) {
			throw new IPNotValidException(
					"Der angegebene String entspricht nicht einer IP-Adresse.");
		}
		return new IPAddress(parseAndValidateByte(matcher.group("Byte0")),
				parseAndValidateByte(matcher.group("Byte1")),
				parseAndValidateByte(matcher.group("Byte2")),
				parseAndValidateByte(matcher.group("Byte3")));
	}

	/**
	 * Die Methode <code>parseAndValidateByte()</code> benötigt einen Parameter
	 * vom Typ String. Dieser Parameter wird zuerst von einem String-Wert in
	 * einen int-Wert geparsed. Danach wird für diesen int-Wert die Methode
	 * {@link #validateByteRange(int)} aufgerufen, um den Wertebereich zu
	 * prüfen.
	 * 
	 * @param source
	 * @return temp - Stellt ein Byte einer IP-Adresse dar.
	 * @throws IPNotValidException
	 */
	private static int parseAndValidateByte(String source)
			throws IPNotValidException {
		int temp = Integer.parseInt(source);
		validateByteRange(temp);
		return temp;
	}

	/**
	 * Diese Methode prüft, ob sich die Bytes im richtigen Wertebereich
	 * befinden. Ist der Wert kleiner als 0 bzw. größer als 255, wird eine
	 * <code>IPNotValidException</code> geworfen. Diese gibt dem Benutzer eine
	 * Fehlermeldung zurück, in der auch der ungültige Bereich angegeben wird.
	 * 
	 * @param value
	 * @throws IPNotValidException
	 */
	private static void validateByteRange(int value) throws IPNotValidException {
		if (value < 0 || value > 255) {
			throw new IPNotValidException(
					"Der angebene Bereich ist nicht gültig: " + value);
		}
	}

	// get-Methoden
	public int getByte0() {
		return byte0;
	}

	public int getByte1() {
		return byte1;
	}

	public int getByte2() {
		return byte2;
	}

	public int getByte3() {
		return byte3;
	}

	@Override
	public String toString() {
		return byte0 + "." + byte1 + "." + byte2 + "." + byte3;

	}

}
