package CLI;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Interprets the input commands and if the game is started then initializes a game instance
 * THE GAME SHOULD NEVER USE OTHER INPUT OR OUTPUT AS THE STORED ONES
 * (see its members: log, input)
 */
public class CommandInterpreter {
    /**
     * Currently active, started game
     */
    private Game gameInstance;
    /**
     * The output CLI language will be written to this stream
     * Watch out: it is static, but will be set in the c'tor
     * ( Because every other class should see the log and at any given moment there is only one actively running log )
     */
    private PrintStream log;
    /**
     * The input CLI language will be read from this stream
     */
    private InputStream input;
    /**
     * The inputs will be scanned through this scanner
     */
    private Scanner commandScanner = new Scanner(input);
    /**
     * Ends the game at the beginning of the next iteration
     */
    private boolean endGame = false;

    /**
     * @param in used as an input for commands and arguments
     * @param out used as output of game
     */
    public CommandInterpreter(InputStream in, PrintStream out) {
        input = in;
        log = out;
    }

    /**
     * Basically the game loop in the prototype,
     * waits for a command, processes it (and asks for argument values, if needed),
     * gives an output and then waits for the next one
     * Processing is done in the corresponding method of the command in the gameInstance
     * The loop ends, when the game ends or when EndGame command is given.
     * The commands and arguments should be separated with new lines
     */
    public void waitingCommands() {
        while(!endGame) {
            // amíg nincs
            while(commandScanner.nextLine()!="StartGame") {
                log.println("You can't do anything until you start the game (StartGame)");
            }
            log.println("Deterministic or Random?");
            switch (commandScanner.nextLine()) {
                case "Deterministic":
                    gameInstance = Game.startGame(log, true, 6);
                break;
                case "Random":
                    log.println("How many players?");
                    gameInstance = Game.startGame(log, false, commandScanner.nextInt());
                    break;
            }

            // és itt jön a szép nagy switch case :D
            // TODO corresponding methods (like startGame) should be implemented
            // TODO Note: A Game osztály lényege, hogy amikor a command interpreter helyett a CLI küldözget hasonló parancsokat, akkor azok szét legyenek választva a CLI-től
            // Tehát: a corresponding methodok a Game methodjai kell legyenek (startGame static, mert még nincs példány, a többi nem static)
            switch (commandScanner.nextLine()) {
                case "PrintCharacterMap":
                    break;
                case "PrintItemMap":
                    break;
                case "PrintHeimMap": // TODO lehet azt a Heim-t angolul kéne, bocsi, most vettem csak észre :D
                    break;
                case "PrintSnowTileMap":
                    break;
                case "PrintTile":
                    break;
                case "PrintItem":
                    break;
                case "PrintPlayer":
                    break;
                case "Step":
                    break;
                case "PickUp":
                    break;
                case "DigItemUp":
                    break;
                case "UseSkill":
                    break;
                case "ClearSnow":
                    break;
                case "SavePlayers":
                    break;
                case "BuildTent":
                    break;
                case "PutSignalTogether":
                    break;
                case "PassRound":
                    break;
                case "EndGame": // NOTE: this one is new, but its useful for automated CLI tests
                    endGame = true;
                    break;
                default:
                    log.println("There is no such command");
                    break;
            }
        }
    }


}
