/*
 * Tejas Guha
 * Substitution Cipher
 */

public class Substitution implements Codeable{
	private String key;
	private String text;
	
	public Substitution(String k, String t){ //constructor for cipher/plain text and key
		key = k.toLowerCase();
		text = t;
	}
	
	public Substitution(){ //default
		key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		text = "Substitution";
	}

	public String encode() {
		StringBuffer cipher = new StringBuffer(text);
		for (int i = 0; i < cipher.length(); i++){
			if (Character.isLetter(cipher.charAt(i))){
				cipher.setCharAt(i, en_shift(cipher.charAt(i), key)); //replaces each letter with its new alphabet counterpart
			}
		}
		return cipher.toString();
	}

	public String decode() {
		StringBuffer cipher = new StringBuffer(text);
		for (int i = 0; i < cipher.length(); i++){
			if (Character.isLetter(cipher.charAt(i))){
				cipher.setCharAt(i, de_shift(cipher.charAt(i), key)); //replaces each letter with the alphabet counterpart
			}
		}
		return cipher.toString();
	}
	
	private char en_shift(char pos, String k){ //for encoding
		if (Character.isLowerCase(pos)){ //for lower case
			return k.charAt(pos - 97);
		} else{ //for upper case
			return Character.toUpperCase(k.charAt(pos - 65));
		}
	}
	
	private char de_shift(char pos, String k){ //for decoding
		if (Character.isLowerCase(pos)){ //for lower case
			return (char) (97 + k.indexOf(Character.toString(pos)));
		} else{ //for upper case
			return (char) (65 + k.indexOf(Character.toString(Character.toLowerCase(pos))));
		}
	}

	public String toString() {
		return "Substitution Cipher[key=" + key + ", text=" + text + "]";
	}

	public boolean equals(Object obj) { //compares Ciphers
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Substitution other = (Substitution) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	
	
}
