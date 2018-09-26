package hangman;

import javax.swing.*;

public class HangMan {

    public static void main(String[] args) {
        String sports[] = {"SPORTS", "BOBBY ORR", "BASKETBALL", "RACQUETBALL", "SIDNEY CROSBY"};
        String movies[] = {"MOVIES", "AVATAR", "STAR WARS", "THE GODFATHER", "TERMINATOR"};
        String tvshows[] = {"TV SHOWS", "SPONGEBOB SQUAREPANTS","FAMILY GUY", "LOST", "HOUSE", "SURVIVOR"};
        //2D array of all words and categories
        String hangmanWords[][] = {sports, movies, tvshows};
        // array for the hangman graphics
        String graphic[] = {"  O \n/ | \\\n  |\n/   \\", "  O \n/ | \\\n  |\n/", "  O \n/ | \\\n  |", "  O \n/ | \\", "  O \n/ |", "  O \n/", "  O", " "};

        int numGuesses, numWords;
        String category, word, blankWord, lettersUsed;

        int randomNumber1, randomNumber2; //Random category and word
        randomNumber1 = (int) (Math.random() * hangmanWords.length);  //random category
        randomNumber2 = (int) (Math.random() * (hangmanWords[randomNumber1].length - 1) + 1); //Random word
        category = hangmanWords[randomNumber1][0];
        word = hangmanWords[randomNumber1][randomNumber2];

        //Build the word with hyphens
        blankWord = "";
        numWords = 1;

        for (int pos = 0; pos < word.length(); pos++) {
            //Convert the char at position pos to an int
            //If it is between 'A' and 'Z', add a dash
            if (((int) word.charAt(pos) >= 65) && ((int) word.charAt(pos) <= 90)) {
                blankWord += "-";
            } else {
                blankWord += word.charAt(pos);
                if (word.charAt(pos) == ' ') {
                    numWords++;
                }
            }
        }

        lettersUsed = "";

        for (numGuesses = 7; numGuesses > 0; numGuesses--) {
            System.out.println("Category: " + category + "\nNumber of Words: " + numWords + "\n" + blankWord);
            System.out.println(graphic[numGuesses]);
            //Loop for guesses
            String myGuess = JOptionPane.showInputDialog("Number of Guesses Remaining: " + numGuesses
                    + "\nGuess a letter:");
            myGuess = myGuess.toUpperCase();

            boolean found = false;

            //Check if letter was already used
            if (lettersUsed.indexOf(myGuess) != -1) {
                JOptionPane.showMessageDialog(null, "The letter " + myGuess + " was already guess.\n"
                        + "Lose one guess");
            } else {
                lettersUsed += myGuess;
                //Determine if the guessed letter is in the word
                for (int pos = 0; pos < word.length(); pos++) {
                    if (("" + word.charAt(pos)).equals(myGuess)) {
                        //Replace hyphen with the letter
                        blankWord = blankWord.substring(0, pos) + myGuess + blankWord.substring(pos + 1, blankWord.length());
                        found = true;
                    }
                }
            }

            //if the letter is is found, add one to the guess
            if (found) {
                numGuesses++;
            }

            if (blankWord.indexOf("-") == -1) {
                //No dashes left, you guessed it!
                System.out.println("Category: " + category + "\nNumber of Words: " + numWords + "\n" + blankWord);
                System.out.println("Congratulations!  You guessed correctly!");
                System.exit(1);
            }
        }

        System.out.println("Sorry you have no guesses left.");
        System.out.println(graphic[numGuesses]);
        System.out.println("The correct word was: " + word);

    }
}
