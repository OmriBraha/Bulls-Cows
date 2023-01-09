
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class game {

	public static void main(String[] args) {

		// gameLogic object
		gameLogic game = new gameLogic();
		
		// boolean type to indicates the game is ON
		boolean gameIsRunning = true;
		
		// declare messages presented for the user
		String currentMessage = "";
		String previousGuess = "";
		
		// ArrayList of the collection of guesses made by user
		ArrayList<String> guesses = new ArrayList<String>();
		
		// System.out.println(game.hiddenNum); for debugging

		while (gameIsRunning) {
			// user input dialog box by JAVA SWING for getting input from user
			String guess = JOptionPane.showInputDialog("Enter your guess: ");
			
			// checks if the input is valid
			currentMessage = game.guessChecker(guess);
			if (currentMessage.startsWith("EROR")) {
				
				// if the input guess is not valid, present an Error to user using a dialog box
				JOptionPane.showMessageDialog(null, currentMessage = game.guessChecker(guess), "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				
				// if the guess is valid, save it to the array list
				guesses.add(guess);
				
				// present history to the user of previous guesses & bulls and pgiot
				JOptionPane.showMessageDialog(null, currentMessage + previousGuess + "\n guesses history: "+ guesses.toString());
				if (currentMessage.startsWith("Your")) {
					previousGuess = previousGuess +"\n"+ currentMessage;
				}
			}
			
			// checks if the latest message starts with YAY, thus the user guessed the number -> won
			if (currentMessage.startsWith("YAY")) {
				
				// ask user within a dialog box if to start a new game
				guess = JOptionPane.showInputDialog("Enter 'Y' to play another game: ");
				
				// if the answer is 'y' or 'Y':
				if (guess.toUpperCase().contains("Y")) {
					
					// restart the game using the restartGame constructor
					game.restartGame();
					
					// reset data for the new game
					guesses.clear();
					previousGuess = "";
					// System.out.println(game.hiddenNum); for debugging
				
				} else {
					// The user did not want to start another game
					JOptionPane.showMessageDialog(null, "Game eneded! Thanks for playing in Bull Pgia");
					gameIsRunning = false;
				}
			}
		}
	}
}