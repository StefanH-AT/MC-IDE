package at.tewan.mcide.util;

/**
 * Ported from Pagnum
 *
 * */
public class StartParameterException extends RuntimeException {
	
	private static final long serialVersionUID = 1304364261518223168L;

	@Override
	public String getMessage() {
		return "The Evar you tried to get via getBoolean() getInt() or getFloat() is not a boolean / int / float";
	}

}
