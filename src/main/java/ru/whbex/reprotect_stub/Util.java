package ru.whbex.reprotect_stub;
// TODO: imports
// (written in neovim from an android device)
public class Util {
	private static final char[] ALPHABET = "0123456789abcdefABCDEF".toCharArray();
	private static final String SERIAL_PREFIX = "[RW-SERIAL]";
	public static String generateString(int len){
		StringBuilder ret = new StringBuilder();
		Random rnd = new Random();
		int i = 0
		while(i < len){
			int randInt = rnd.nextInt(alphabet.length - 1);
			ret.append(alphabet[randInt]);
			i++;
		}
		return ret.toString();
	}
	public static String generateFakeSerial(){
		StringBuilder ret = new StringBuilder(SERIAL_PREFIX);
		String serial = generateString(32);
		ret.append(serial);
		String base64 = ret.toString();
		int i = 0;
		while(i < 3){
			base64 = Base64.getEncoder().encodeToString(base64.getBytes());
			i++;
		}
		// TODO: figure out wtf is VUMDk= string
		// dont use this yet, it won't work 98%
		return base64;
	}
}

		
		

