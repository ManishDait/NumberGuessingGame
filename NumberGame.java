import java.sql.SQLException;
import java.util.Scanner;

public class NumberGame {

  private boolean stopGame;
  private boolean isPlayed = false;
  private int option;
  private Scanner scan = new Scanner(System.in);
  private int score = 0;
  private ScoreBoard scoreBoard = new ScoreBoard();

  /** Method to start Number Game.
   * @throws SQLException
   * 
   */

  public void start() throws SQLException {
    scoreBoard.connect();
    while (!stopGame) {
      displayMenu();
      System.out.print("What option will you like to select: ");
      option = scan.nextInt();
      performAction(option);
    }
  }
    
  /** Method to displayMenu as shown below.
   * _______________________________
   * Welcome to Number Guessing Game
   * _______________________________
   
   * Options:
   * --------
   * 1) Play Game
   * 2) View Score
   * 3) Save Score
   * 4) Quit
   
   * Score: 0
   */

  public void displayMenu() {
    System.out.println("_______________________________");
    System.out.println("Welcome to Number Guessing Game");
    System.out.println("_______________________________");
    System.out.println("\nOptions:");
    System.out.println("--------");
    System.out.println("1) Play Game");
    System.out.println("2) View Score");
    System.out.println("3) Save Score");
    System.out.println("4) Quit\n");
    System.out.println("Score: " + score + "\n");
  }

  /** Method to perform Action based on the opttion provided.
   * @throws SQLException
   * 
   */

  public void performAction(int option) throws SQLException {
    if (option == 1) {
      isPlayed = true;
      playGame();
    }
    if (option == 2) {
      scoreBoard.displayScore();
    }
    if (option == 3) {
      if (isPlayed) {
        System.out.print("\nEnter your name to save score: ");
        String playerName = scan.next();
        scoreBoard.addScore(playerName, score);
        isPlayed = false;
        score = 0;
      } else {
        System.out.println("\n-> No score Available! Play the Game First...");
      }
    }
    if (option == 4) {
      if (isPlayed) {
        System.out.print("\n-> Do you want to save score (Y/N): ");
        String save = scan.next().toUpperCase();
        if (save.equals("Y")) {
          System.out.print("\nEnter your name to save score: ");
          String playerName = scan.next();
          scoreBoard.addScore(playerName, score);
        }
      }
      isPlayed = false;
      score = 0;
      stopGame = true;
    }
  }

  /** Method to implement game logic.
   * 
   */

  public void playGame() {
    int guess = (int)(Math.random() * 100) + 1;
    int userGuess = 0;
    System.out.println("\nThe number is in range of [1 to 100]");
    while (guess != userGuess) {
      System.out.print("\nEnter your Guess: ");
      userGuess = scan.nextInt();
      if (guess == userGuess) {
        System.out.println("-> Hurray!...");
        System.out.println("-> You guess the right number\n");
        score += 10;
        break;
      } else if (guess > userGuess) {
        System.out.println("-> Oops!... The number you guess is Lower than guess");
        System.out.println("-> Dont give up, Try Again..\n");
      } else {
        System.out.println("-> Oops!... The number you guess is Higher than guess");
        System.out.println("-> Dont give up, Try Again..\n");
      }
    }
  }
}
