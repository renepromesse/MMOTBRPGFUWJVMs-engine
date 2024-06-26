import java.util.Scanner;
import java.util.Objects;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class MedievalGame {

  /* Instance Variables */
  Player player;

  /* Main Method */
  public static void main(String[] args) {

    Scanner console = new Scanner(System.in);
    MedievalGame game = new MedievalGame();

    game.player = game.start(console);

    game.addDelay(500);
    System.out.println("\nLet's take a quick look at you to make sure you're ready to head out the door.");
    System.out.println(game.player);

    game.addDelay(1000);
    System.out.println("\nWell, you're off to a good start, let's get your game saved so we don't lose it.");
    game.save();

    game.addDelay(2000);
    System.out.println("We just saved your game...");
    System.out.println("Now we are going to try to load your character to make sure the save worked...");

    game.addDelay(1000);
    System.out.println("Deleting character...");
    String charName = game.player.getName();
    game.player = null;

    game.addDelay(1500);
    game.player = game.load(charName, console);
    System.out.println("Loading character...");

    game.addDelay(2000);
    System.out.println("Now let's print out your character again to make sure everything loaded:");

    game.addDelay(500);
    System.out.println(game.player);
  }

  /* Instance Methods */
  private Player start(Scanner console) {

    Player player;
    Art.homeScreen();

    System.out.println("Hey there, Welcome to the Game of Elites...");
    System.out.println("  The question is 'Are you Ready?'");
    while (true) {
      System.out.print("   If so enter 'y' to load a game, 'n' to create a new game: ");
      String answer = console.next().toLowerCase();
      if (answer.equals("y")) {
        System.out.println("Provide your previous name: ");
        String prevName = console.next();
        player = load(prevName, console);
        break;
      }
      if (answer.equals("n")) {
        System.out.println("Provide your name: ");
        String name = console.next();
        player = new Player(name);
        break;
      }
    }
    return player;
  }

  private void save() {

    String fileName = this.player.getName() + ".svr";
    try {
      FileOutputStream userSaveFile = new FileOutputStream(fileName);
      ObjectOutputStream playerSaver = new ObjectOutputStream(userSaveFile);
      playerSaver.writeObject(this.player);
    } catch (IOException e) {
      System.out.println("Something went wrong, unable to save the game properly");
    }
  }

  private Player load(String playerName, Scanner console) {

    Player loadedPlayer;
    try {
      FileInputStream userLoadFile = new FileInputStream(playerName + ".svr");
      ObjectInputStream playerLoader = new ObjectInputStream(userLoadFile);
      loadedPlayer = (Player) playerLoader.readObject();

    } catch (IOException | ClassNotFoundException e) {
      addDelay(1500);
      System.out.println("Something wrong happened... Let's try to resolve it for you ...");
      addDelay(2000);
      loadedPlayer = new Player(playerName);
    }
    return loadedPlayer;
  }

  // Human-like prompt simulation
  private void addDelay(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
