/*
 * Tejas Guha
 * Caesar Cipher
 */
public class Caesar implements Codeable{
	private int key;
	private String text;
	
	public Caesar(int k, String t){ //constructor for cipher/plain text and key
		key = Math.floorMod(k, 26); //in case key is over 26 or negative
		text = t;
	}
	
	public Caesar(){ //default
		key = 13;
		text = "Caesar";
	}

	public String encode() {
		StringBuffer cipher = new StringBuffer(text);
		for (int i = 0; i < cipher.length(); i++){
			if (Character.isLetter(cipher.charAt(i))){
				cipher.setCharAt(i, shift(cipher.charAt(i), key)); //goes letter by letter in word
			}
		}
		return cipher.toString();
	}


	public String decode() { //to decode
		key = 26 - key;
		return encode();
	}
	
	private char shift(char pos, int k){
		if (Character.isLowerCase(pos)){ //for lower case
			if ((pos+k) % 123 < 97){ //if the adding of key not a lower case letter
				return (char) ((97 + (pos+k)) % 123);
			}
			return (char) ((pos+k) % 123);
		} else{
			if ((pos+k) % 91 < 65){ //for upper case
				return (char) ((65 + (pos+k)) % 91); //if the adding of the key not upper case
			}
			return (char) ((pos+k) % 91);
		}
	}

	public String toString() { //to print the cipher
		return "Caesar Cipher[key=" + key + ", text=" + text + "]";
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Caesar other = (Caesar) obj;
		if (key != other.key)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	


}
