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
import java.util.InputMismatchException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

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
		
		try {
			String output;
			String wordFilePath = "5LetterWords.txt"; // The dictionary of words to use
			
			// Checking the number of lines/words in the file
			BufferedReader reader = new BufferedReader(new FileReader(wordFilePath));
			int lineCount = 0;
			while ((reader.readLine()) != null) {
				lineCount++;
			}
			reader.close();
			System.out.println(lineCount);
			
			// A random int is chosen from 0 to lineCount (exclusive), which is the chosen word
			int randomLine = new Random().nextInt(lineCount);
			//System.out.println(randomLine); // DEBUG: Shows what line from list was chosen
			
			// Starts again from the top of the dictionary, and moves through until the chosen word is found
			reader = new BufferedReader(new FileReader(wordFilePath));
			for (int i = 0; i < randomLine; i++) {
				reader.readLine();
			}
			
			output = reader.readLine();
			reader.close();
			
			return output;
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException();
		}
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
		//System.out.println(g); // DEBUG: prints out selected word and rearranged order on game start
		
		Scanner scanner = new Scanner(System.in);
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
				try {
					int scanInt = scanner.nextInt();
					if (scanInt < 1 || scanInt > g.selectedWord.length()) {
					System.out.println("Input error, please enter an int between 1 and " + g.selectedWord.length());
					}
					else if (lines.charAt(scanInt-1) != '_') {
						System.out.println("You've already guessed the character at " + scanInt + ", please enter a different number!");
					}
					else {
						intRecieved = true;
						lines.setCharAt(scanInt - 1, g.mixedWord[j]);
					}
				}
				/*
				 * Program fails setting due to scanner input not being an int,
				 * Scanner.next() to "clear" the current scanner data, ending exception
				 * 
				 */
				catch (InputMismatchException e) {
					System.out.println("\"" + scanner.next() + "\" is not an integer! Enter again");
					continue; // restarts the while loop, which attempts try again
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