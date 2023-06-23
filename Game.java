/**
 *
 * TO DO: - bring up to JavaDoc standards
 *		  - find list of words online, shorten down using regex to 4-7 character words
 *
 * Bugs: - can type in the same spot, replacing the letters
 */
 
import java.text.MessageFormat;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	String selectedWord;
	char[] mixedWord; // letters of selectedWord in a random order
	int wordLength;
	
	public Game() {
		selectedWord = randomWordSelect();
		wordLength = selectedWord.length();
		mixedWord = mixLetters(selectedWord);
	}
	
	public String randomWordSelect() {
		return "test";  // Have this pull from a list of possible words
	}
	
	public char[] mixLetters(String s) {
		char[] letterArray = s.toCharArray();
		int charCount = letterArray.length;
		Random randGenerator = new Random();
		
		for (int i = 0; i < charCount; i++) {
			int x = randGenerator.nextInt(charCount);
			
			char temp = letterArray[x];
			letterArray[x] = letterArray[i];
			letterArray[i] = temp;
		}
		
		return letterArray;
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		Scanner scanner = new Scanner(System.in);
		System.out.println(g);
		StringBuilder lines = new StringBuilder(); // More efficient implementation of StringBuffer? Read up on it;  Should this be a class variable?
		
		for (int i = 0; i < g.wordLength; i++){
			lines.append('_');
		}
		lines.append("  ");
		
		for (int j = 0; j < g.wordLength; j++){
			lines.deleteCharAt(g.wordLength + 1);
			lines.append(g.mixedWord[j]);
			System.out.println(lines);
			
			
			boolean intRecieved = false;
			System.out.println("which position is this character in? (type 1 to "
								+ g.selectedWord.length() + ")");
			while (!intRecieved) {
				int scanInt = scanner.nextInt();
				
				if (scanInt < 1 || scanInt > g.selectedWord.length()) {
					System.out.println("Input error, please enter an int between 1 and " + g.selectedWord.length());
				}
				else {
					intRecieved = true;
					lines.setCharAt(scanInt - 1, g.mixedWord[j]);
				}
			}
		}
		
		lines.delete(lines.length() - 2, lines.length());
		
		if (lines.toString().equals(g.selectedWord)) {
			System.out.println("Game Over! you typed: \"" + lines + "\" which was the word!");
		} else {
			System.out.println("Game Over! you typed: \"" + lines + "\" but the word was: " + g.selectedWord);
		}
		
		
	
	}
	
	public String toString() {
		String s = "The word for this game is \"{0}\" which has a length of {1}; it has been rearranged as: {2}";
		String output = MessageFormat.format(s, selectedWord, wordLength, new String(mixedWord));
		return output;
	}
	
}