package Cipher;

public class Substitution implements Codeable{
	private String key;
	private String text;
	
	public Substitution(String k, String t){
		key = k.toLowerCase();
		text = t;
	}

	public String encode() {
		StringBuffer cipher = new StringBuffer(text);
		for (int i = 0; i < cipher.length(); i++){
			if (Character.isLetter(cipher.charAt(i))){
				cipher.setCharAt(i, en_shift(cipher.charAt(i), key));
			}
		}
		return cipher.toString();
	}

	public String decode() {
		StringBuffer cipher = new StringBuffer(text);
		for (int i = 0; i < cipher.length(); i++){
			if (Character.isLetter(cipher.charAt(i))){
				cipher.setCharAt(i, de_shift(cipher.charAt(i), key));
			}
		}
		return cipher.toString();
	}
	
	private char en_shift(char pos, String k){
		if (Character.isLowerCase(pos)){ //for lower case
			return k.charAt(pos - 97);
		} else{ //for upper case
			return Character.toUpperCase(k.charAt(pos - 65));
		}
	}
	
	private char de_shift(char pos, String k){
		
		if (Character.isLowerCase(pos)){ //for lower case
			return (char) (97 + k.indexOf(Character.toString(pos)));
		} else{ //for upper case
			return (char) (65 + k.indexOf(Character.toString(Character.toLowerCase(pos))));
		}
	}

}
