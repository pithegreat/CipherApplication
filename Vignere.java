package Cipher;

public class Vignere implements Codeable{
	private String key;
	private String text;
	
	public Vignere(String k, String t){
		key = k.toLowerCase();
		text = t;
	}

	public String encode() {
		StringBuffer cipher = new StringBuffer(text);
		int no_spaces = 0;
		for (int i = 0; i < cipher.length(); i++){
			if (Character.isLetter(cipher.charAt(i))){
				int k = Character.valueOf(key.charAt(no_spaces%key.length())) - 97;
				no_spaces++;
				cipher.setCharAt(i, shift(cipher.charAt(i), k)); //goes letter by letter in word
			}
		}
		return cipher.toString();
	}


	public String decode() { //to decode
		StringBuffer cipher = new StringBuffer(text);
		int no_spaces = 0;
		for (int i = 0; i < cipher.length(); i++){
			if (Character.isLetter(cipher.charAt(i))){
				int k = Character.valueOf(key.charAt(no_spaces%key.length())) - 97;
				no_spaces++;
				cipher.setCharAt(i, shift(cipher.charAt(i), 26-k)); //goes letter by letter in word
			}
		}
		return cipher.toString();
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
