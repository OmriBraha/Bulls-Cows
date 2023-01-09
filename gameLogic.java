/*
 * Bull Pgiaa game
 * 
 * The program will generate a 4 (different) digits number
 * 
 * For every guess made by the user, the program will present:
 *  	- The guessed number 
 *  	- Number of correct hits ( bull )
 *  	- Number of correct digits that located differently in the generated number ( pgiaa )
 * 
 * Using java swing, the input-output will be performed by a dialog box which will include that history of guesses made by the user ( including bulls & pgiot )
 */




public class gameLogic {
	/*
	 * Game logics class which includes:
	 * Constructors:
	 * - Game
	 * - Number generator
	 * - Restart game -> to regenerate a new number & game status and guesses
	 * Getters:
	 * - getters for generated number & total guesses
	 * Methods:
	 * - Guess checker
	 * - Input checker method for illegal inputs
	 */
	
	
	
	// length of the generated hidden number
	private final int LENGTH = 4; 
	private String hiddenNum = "";
	private int totalGuesses;

	// empty constructor - generating a new number using the method numGenerator
	public gameLogic() {
		this.hiddenNum = numGenerator();
		this.totalGuesses = 0;
	}
	
	// restart game constructor
	public void restartGame() {
		
		this.hiddenNum = numGenerator();
		this.totalGuesses = 0;
	}
	
	// getter of hidden number
	public String getNum() {
		return hiddenNum;
	}

	// getter of total guesses number
	public int getGuesses() {
		return totalGuesses;
	}
	
	// Number generator method
	private String numGenerator() {
		String numToGuess = "";
		int randDigit;
		
		// till we are reaching to a 4 digit number:
		while (numToGuess.length() < LENGTH) {
			
			// using Math.random, getting a random digit
			randDigit = (int) (Math.random() * 10);
			
			// if the number to guess does not contains the current randomized digit, add it to the number as a string
			if (numToGuess.contains("" + randDigit) != true) {
				numToGuess += randDigit;
			}
		}
		// return the 4 digits number ( with all different digits )
		return numToGuess;
	}

	// guess checker method
	public String guessChecker(String guess) {
		// initialize the bull and pgia counter
		int bullCounter = 0;
		int pgiaCounter = 0;
		// message for the user
		String msg;

		// if the inputChecker method return a not empty message, return it to the user ( this case is when the user types an illegal input )
		if (inputChecker(guess).length() != 0) {
			return inputChecker(guess);
		}
		
		// otherwise, the input is legal, add it to total guesses
		this.totalGuesses += 1;

		// going thru every digit in user input ( guess )  comparing to hidden number ( generated number )
		for (int i = 0; i < LENGTH; i++) {
			
			// User guessed correct location & digits of * digits as in the hidden number
			if (this.getNum().charAt(i) == guess.charAt(i)) {
				
				// increase number of bulls
				bullCounter += 1; 
				
			// use guessed the correct * digits ( but not the location ) as in the hidden number
			} else if (this.getNum().contains("" + guess.charAt(i)) == true) {
				// increase number of pgia
				pgiaCounter += 1;
			}
		}
		
		// possible messages for the users when the input is valid:
		
		// The bullCounter is 4, thus the user guessed the number!
		if (bullCounter == LENGTH) {
			msg = "YAY you WON! after a total of " + this.getGuesses() + " guesess to reveal the hidden number: "
					+ this.getNum();
		
		// The user did not won yet, and he still needs to try another time
		} else {
			msg = "Your guess:" + guess + " has " + bullCounter + " bulls and " + pgiaCounter + " pgiot";
		}

		return msg;
	}

	// input checker method
	private String inputChecker(String guess) {
		String msg = "";
		
		// the guess is not from a numeric type
		if (guess.matches("\\d+") != true) {
			return msg = "EROR! Your guess must be a numeric type";
		}
		
		// the guess is not 4 digit long
		if (guess.length() != LENGTH) {
			return msg = "EROR! Your guess is NOT 4 digit long";
		}
		
		// the guess is a number that contains one ore more digits more than one time ( ex. '4412', '1111' )
		for (int i = 0; i < guess.length(); i++) {
			for (int j = i + 1; j < guess.length(); j++) {
				if (guess.charAt(i) == guess.charAt(j)) {
					return msg = "EROR! Your guess have one of more of the same digits";
				}
			}
		}
		return msg;
	}

}