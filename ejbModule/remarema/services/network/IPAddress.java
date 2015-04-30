package remarema.services.network;

import java.util.regex.*;

/**
 * Diese Klasse stellt sicher, dass es sich bei einer IP-Adresse auch wirklich
 * um eine gültige IPv4-Adresse handelt.
 * 
 * @author Rebecca vanLangelaan
 *
 */

public class IPAddress {

	private int byte0;
	private int byte1;
	private int byte2;
	private int byte3;

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

	public IPAddress(int sourceValue) {
		this.byte0 = (sourceValue >> 24) & 0xFF;
		this.byte1 = (sourceValue >> 16) & 0xFF;
		this.byte2 = (sourceValue >> 8) & 0xFF;
		this.byte3 = sourceValue & 0xFF;
	}

	public int toInt() {
		return ((byte0) << 24) | ((byte1) << 16) | ((byte2) << 8) | byte3;
	}

	public static IPAddress parse(String ipString) throws IPNotValidException {
		Matcher matcher = Pattern
				.compile(
						"(?<Byte0>[0-9]{1,3})\\.(?<Byte1>[0-9]{1,3})\\.(?<Byte2>[0-9]{1,3})\\.(?<Byte3>[0-9]{1,3})")
				.matcher(ipString);
		if (!matcher.matches()) {
			throw new IPNotValidException(
					"Der angegebene String entspricht nicht einer IP-Adresse");
		}
		return new IPAddress(parseAndValidateByte(matcher.group("Byte0")),
				parseAndValidateByte(matcher.group("Byte1")),
				parseAndValidateByte(matcher.group("Byte2")),
				parseAndValidateByte(matcher.group("Byte3")));
	}

	private static int parseAndValidateByte(String source)
			throws IPNotValidException {
		int temp = Integer.parseInt(source);
		validateByteRange(temp);
		return temp;
	}

	private static void validateByteRange(int value) throws IPNotValidException {
		if (value < 0 || value > 255) {
			throw new IPNotValidException(
					"Der angebene Bereich ist nicht gültig: " + value);
		}
	}

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
