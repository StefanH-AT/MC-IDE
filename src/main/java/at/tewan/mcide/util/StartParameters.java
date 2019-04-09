package at.tewan.mcide.util;

import java.util.HashMap;

//
// Evar = Environment Variable. These are variables, you can change on game start and during runtime.
//

public class StartParameters {
	
	
	/** The Map of Evars */
	private static HashMap<String, String> params;
	
	/** Resolve the start parameters as Evars */
	public static void resolveStartArgs(String[] args) {
		params = new HashMap<>();
		
		for(int i = 0; i < args.length; i++) {
			if(args[i].substring(0, 1).equals("-")) {
				if(i + 1 < args.length) {
					if(!args[i + 1].startsWith("-")) {
						params.put(args[i].substring(1), args[i + 1]);
					} else {
						params.put(args[i].substring(1), "true");
					}
				} else {
					params.put(args[i].substring(1), "true");
				}
			}
		}
	}
	
	/** Set an Evar */
	public static void set(String key, String value) {
		params.put(key, value);
	}
	
	/** Tests if an Evar is contained in the Key Value set. 
	 * 	If the key has no value, it still is considered existing.
	 * 	
	 * 	 @return If the key exists.
	 * */
	public static boolean contains(String key) {
		return params.containsKey(key);
	}
	
	/** @return Boolean value of an Evar. If the key does not exist or if the value is not a boolean, fallback is returned. */
	public static boolean getBoolean(String key, boolean fallback) {
		
		String val = get(key, fallback + "");
		
		return val.equalsIgnoreCase("true");		
		
	}
	
	
	/** @return Integer value of an Evar. If the key does not exist or if the value is not an integer, fallback is returned. */
	public static int getInt(String key, int fallback) {
		try {
			return Integer.parseInt(get(key, fallback + ""));
		} catch(NumberFormatException e) {
			throw new StartParameterException();
		}
		
	}
	
	/** @return Float value of an Evar. If the key does not exist or if the value is not a float, fallback is returned. */
	public static float getFloat(String key, float fallback) {
		try {
			return Float.parseFloat(get(key, fallback + ""));
		} catch(NumberFormatException e) {
			throw new StartParameterException();
		}
		
	}
	
	/** @return Value of an Evar as the raw String. If value does not exist, fallback is returned.*/
	public static String get(String key, String fallback) {
		if(contains(key)) {
			return params.get(key);
		} else {
			return fallback;
		}
		
	}
	
	public static boolean getBoolean(String key) {
		return getBoolean(key, false);
	}
	
	public static int getInt(String key) {
		return getInt(key, 0);
	}
	
	public static float getFloat(String key) {
		return getFloat(key, 0f);
	}
	
	public static String get(String key) {
		return get(key, "");
	}
}
