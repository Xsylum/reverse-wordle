/**
 *
 * TO DO: - bring up to JavaDoc standards
 *		  - find list of words online, shorten down using regex to 4-7 character words
 */
 
import java.text.MessageFormat;
import java.util.Random;

public class Game {
	
	String selectedWord;
	String mixedWord; // a String that contains the letters of selectedWord in a random order
	int wordLength;
	
	public Game() {
		selectedWord = randomWordSelect();
		wordLength = selectedWord.length();
		mixedWord = mixLetters(selectedWord);
	}
	
	public String randomWordSelect() {
		return "test";  // Have this pull from a list of possible words
	}
	
	public String mixLetters(String s) {
		char[] letterArray = s.toCharArray();
		int charCount = letterArray.length;
		Random randGenerator = new Random();
		
		for (int i = 0; i < charCount; i++) {
			int x = randGenerator.nextInt(charCount);
			
			char temp = letterArray[x];
			letterArray[x] = letterArray[i];
			letterArray[i] = temp;
		}
		
		String output = new String(letterArray);
		return output;
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		System.out.println(g);
	}
	
	public String toString() {
		String s = "The word for this game is \"{0}\" which has a length of {1}; it has been rearranged as: {2}";
		String output = MessageFormat.format(s, selectedWord, wordLength, mixedWord);
		return output;
	}
	
}