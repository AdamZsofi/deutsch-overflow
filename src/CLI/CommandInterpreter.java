package CLI;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import ItemClasses.Item;
import ItemClasses.ItemState;
import PlayerClasses.Eskimo;
import PlayerClasses.Player;
import PlayerClasses.PlayerContainer;
import TileClasses.Direction;
import TileClasses.Tile;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interprets the input commands and if the game is started then initializes a game instance
 * THE GAME SHOULD NEVER USE OTHER INPUT OR OUTPUT AS THE STORED ONES
 * (see its members: log, input)
 * @author Zsófi
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
    private Scanner commandScanner;
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
        commandScanner = new Scanner(input);
    }

    /**
     * Basically the game loop in the prototype,
     * waits for a command, processes it (and asks for argument values, if needed),
     * gives an output and then waits for the next one
     * Processing is done in the corresponding method of the command in the gameInstance
     * The loop ends, when the game ends or when EndGame command is given.
     * The commands and arguments should be separated with new lines
     * @author Zsófi
     */
    public void waitingCommands() {
        // amíg nincs
        while(!commandScanner.nextLine().toLowerCase().equals("startgame")) {
            log.println("You can't do anything until you start the game (StartGame)");
        }
        log.println("Deterministic or Random?");
        switch (commandScanner.nextLine().toLowerCase()) {
            case "deterministic":
                log.println("Choose map (0 or 1)");
                int map = commandScanner.nextInt();
                log.println("Should the game have a storm after the last player's round? (y/n)");
                commandScanner.nextLine(); // dummy
                boolean stormy = commandScanner.nextLine().equals("y");
                gameInstance = Game.startDeterministicGame(log, 5, map, stormy);
                break;
            case "random":
                log.println("How many players?");
                gameInstance = Game.startRandomGame(log, commandScanner.nextInt());
                break;
        }
        boolean ended = false;
        while(!ended) {

            // és itt jön a szép nagy switch case :D
            // TODO corresponding methods for every command (like startGame) should be implemented
            // TODO Note: A Game osztály lényege, hogy amikor a command interpreter helyett a CLI küldözget hasonló parancsokat, akkor azok szét legyenek választva a CLI-től
            // TODO Note2: a corresponding methodok a Game methodjai kell legyenek (startGame static, mert még nincs példány, a többi nem static)
            // TODO Note3: Fontos, hogy az argumentumokat (irány, stb.) már itt bekérje a CommandInterpreter és csak utána hívja meg a gameInstanceon amit kell, az arg-t paraméterként átadva
            // TODO Note4: a printCharacterMap-t és a step-t megírtam, mint példa, az lehet, hogy segít
            // TODO Note5: illetve a 7-s doksiban van a ki és bemeneti nyelv kiírása az argumentumokkal, azt légyszi kövessétek
            // TODO Note6: néhány helyen lekezeltem, ha hülyeséget kapunk bemenetként, de alapvetően sztem NEM kell kezelni - ez egy teszt nyelv, nem hülye user van, hanem mi használjuk tesztelésre, tőlünk elvárható az értelmes input
            switch (commandScanner.nextLine().toLowerCase()) {
                case "printcharactermap":
                    gameInstance.printCharacterMap();
                    break;
                case "printitemmap":
                    gameInstance.printItemMap();
                    break;
                case "printheimmap":
                    gameInstance.printShelterMap();
                    break;
                case "printsnowtilemap":
                    gameInstance.printSnowTileMap();
                    break;
                case "printtile":
                    gameInstance.printTile();
                    break;
                case "printitem":
                    gameInstance.printItem();
                    break;
                case "printplayer":
                    gameInstance.printPlayer();
                    break;
                case "step":
                    log.println("Which direction? (w,a,s,d)"); // Note: ahol van értelme egy helyben csinálni vmit, ott kell 5.-ik irány arg
                    switch (commandScanner.nextLine()) {
                        case "w":
                            gameInstance.step(Direction.UP);
                            break;
                        case "a":
                            gameInstance.step(Direction.LEFT);
                            break;
                        case "s":
                            gameInstance.step(Direction.DOWN);
                            break;
                        case "d":
                            gameInstance.step(Direction.RIGHT);
                            break;
                    }
                    break;
                case "pickup":
                    Player p= PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());
                    Tile currentTile= PositionLUT.getInstance().getPosition(p);
                    ArrayList<Item> items = PositionLUT.getInstance().getItemOnTile(currentTile);
                    for(Item i : items) {//ha elfogy minden hibás
                        if(i.getState().equals(ItemState.frozen))
                            items.remove(i);
                        if(items.size() == 0)
                            break;
                    }
                    int maxPick=items.size();
                    int posPick=0;
                    for(Item i: items){
                        log.println(posPick+": "+i.getShortName());
                        posPick++;
                    }
                    log.println("Which item? 0-"+(maxPick-1));
                    int inputPick=0;
                    do {
                        inputPick = Integer.parseInt(commandScanner.nextLine());
                    }while(inputPick<0 || inputPick>maxPick-1);
                    gameInstance.pickUp(items.get(inputPick));
                    break;
                case "digitemup":
                    Player p1= PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());
                    Tile currentTile1= PositionLUT.getInstance().getPosition(p1);
                    ArrayList<Item> items1 = PositionLUT.getInstance().getItemOnTile(currentTile1);
                    for(Item ii : items1) {
                        if(!ii.getState().equals(ItemState.frozen))
                            items1.remove(ii);
                        if(items1.size()== 0)
                            break;
                    }
                    int maxDig=items1.size();
                    int posDig=0;
                    for(Item i: items1){
                        log.println(posDig+": "+i.getShortName());
                        posDig++;
                    }
                    log.println("Which item? 0-"+(maxDig-1));
                    int inputDig=0;
                    do {
                        inputDig = Integer.parseInt(commandScanner.nextLine());
                    }while(inputDig<0 || inputDig>maxDig-1);
                    gameInstance.digItemUp(items1.get(inputDig));
                    break;
                case "useskill":
                    Player p2= PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());
                    if(p2 instanceof Eskimo){ // :(
                        gameInstance.buildIgloo();
                    }
                    else{
                        log.println("Which direction? (w,a,s,d)");
                        switch (commandScanner.nextLine()) {
                            case "w":
                                gameInstance.detectCapacity(Direction.UP);
                                break;
                            case "a":
                                gameInstance.detectCapacity(Direction.LEFT);
                                break;
                            case "s":
                                gameInstance.detectCapacity(Direction.DOWN);
                                break;
                            case "d":
                                gameInstance.detectCapacity(Direction.RIGHT);
                                break;
                        }
                    }
                    break;
                case "clearsnow":
                    gameInstance.clearSnow();
                    break;
                case "saveplayers":
                    log.println("Which direction? (w,a,s,d)");
                    Player player= PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());
                    //Tile ct= PositionLUT.getInstance().getPosition(p1);
                    /*do {
                        String dir = commandScanner.nextLine();
                        Tile nextTile=

                    }while()*/
                        switch (commandScanner.nextLine()) {
                            case "w":
                                gameInstance.savePlayers(Direction.UP);
                                break;
                            case "a":
                                gameInstance.savePlayers(Direction.LEFT);
                                break;
                            case "s":
                                gameInstance.savePlayers(Direction.DOWN);
                                break;
                            case "d":
                                gameInstance.savePlayers(Direction.RIGHT);
                                break;
                        }
                    break;
                case "buildtent":
                    gameInstance.buildTent();
                    break;
                case "putsignaltogether":
                    gameInstance.putSignalTogether();
                    break;
                case "passround":
                    gameInstance.passRound();
                    break;
                case "endgame": // NOTE: this one is new, but its useful for automated CLI tests
                    ended = true;
                    break;
                default:
                    log.println("There is no such command");
                    break;
            }
        }
    }
}
