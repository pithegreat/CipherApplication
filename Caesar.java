package Cipher;

public class Caesar implements Codeable{
	private int key;
	private String text;
	
	public Caesar(int k, String t){
		key = k % 26;
		text = t;
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

}
