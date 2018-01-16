/*
 * Tejas Guha
 * 1/7/2018
 * Cipher Application with Caesar, Substitution, and Vignere Ciphers and GUI
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CipherApplication implements ActionListener{
	private ButtonGroup bG; //makes the important GUI components global
	private JTextField txt;
	private JTextField key;
	private JTextField output;
	
	public static void main(String[] args) {
		CipherApplication c = new CipherApplication(); //starts application up
		c.createGui();
		
	}
	
	private void createGui(){
		JFrame frame = new JFrame("Cipher Application"); //creates the window
		frame.setSize(500,500);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS)); //makes sure panels laid out horizontally
		
		JPanel left = new JPanel(); //creates the leftmost panel with the radio buttons
		left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
		JRadioButton caesar = new JRadioButton("Caesar");
		JRadioButton sub = new JRadioButton("Substitution");
		JRadioButton vig = new JRadioButton("Vigenere");
		caesar.setActionCommand("c");
		sub.setActionCommand("s");
		vig.setActionCommand("v");
		bG = new ButtonGroup(); //adds buttons to the buttongroup
		bG.add(caesar);
		bG.add(sub);
		bG.add(vig);
		left.add(caesar);
		left.add(sub);
		left.add(vig);
		
		JPanel middle = new JPanel(); //creates the middle panel with text fields
		middle.setLayout(new BoxLayout(middle, BoxLayout.Y_AXIS));
		Label txtLbl = new Label("Plain/Cipher Text");
		txt = new JTextField(5);		
		Label keyLbl = new Label("Key");
		key = new JTextField(5);		
		Label outputLbl = new Label("Output");
		output = new JTextField(5);
		
		middle.add(txtLbl);
		middle.add(txt);		
		middle.add(keyLbl);
		middle.add(key);		
		middle.add(outputLbl);
		middle.add(output);
		
		JPanel right = new JPanel(); //creates the rightmost panel with encode/decode buttons
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		JButton encode = new JButton("Encode");
		JButton decode = new JButton("Decode");
		encode.addActionListener(this);
		decode.addActionListener(this);
		
		right.add(encode);
		right.add(decode);
		
		mainPanel.add(left);
		mainPanel.add(middle);
		mainPanel.add(right);
		
		frame.add(mainPanel);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (txt.getText().length() * key.getText().length() == 0 || bG.getSelection() == null){ //makes sure text fields have data in it
			JOptionPane.showMessageDialog(null, "Please make sure you have selected a cipher to use, the key, and the cipher/plain text to encode or decode.");
			return;
		}
		
		
		Codeable c; //makes it global in the method
		c = new Caesar(13,"Tejas Guha!");
		switch (bG.getSelection().getActionCommand()){ //sees which cipher has been picked
			case "c": //if caesar
				int k;
				try{
					k = Integer.parseInt(key.getText()); //makes sure key is numeric
				}
				catch (NumberFormatException e){
					JOptionPane.showMessageDialog(null, "Key must be numeric!");
					return;
				}
				c = new Caesar(k, txt.getText());
				break;
			
			case "s": //if substitution
				String alpha = key.getText();
				if (alpha.length() != 26){
					JOptionPane.showMessageDialog(null, "Make sure the key is a new alphabet (26 letters long, no spaces)!"); //makes sure key is alphabetic
					return;
				}
				
				for (int i = 0; i < 26; i++){
					if (!Character.isAlphabetic(alpha.charAt(i))){
						JOptionPane.showMessageDialog(null, "Make sure the key is all letters!"); //makes sure key is a word
						return;
					}
					
					if(!(i == alpha.lastIndexOf(alpha.charAt(i)))){
						JOptionPane.showMessageDialog(null, "Do not repeat letters in the new alphabet!"); //makes sures letters don't repeat in key
						return;
					}
				}
				
				c = new Substitution(alpha, txt.getText());
				break;
			
			case "v": //if vigenere
				String word = key.getText();
				for (int i = 0; i < word.length(); i++){
					if (!Character.isAlphabetic(word.charAt(i))){
						JOptionPane.showMessageDialog(null, "Make sure the key is all letters!"); //makes sure key is all letters
						return;
					}
				}			
				c = new Vignere(word, txt.getText());
		}
		
		if(arg0.getSource().toString().contains("Encode")){
			output.setText(c.encode());
		}else{
			output.setText(c.decode());
		}
	}
}
