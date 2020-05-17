package Control;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import ItemClasses.Item;
import ItemClasses.ItemState;
import PlayerClasses.Eskimo;
import PlayerClasses.Player;
import PlayerClasses.PlayerContainer;
import TileClasses.Direction;
import TileClasses.Tile;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class KeyboardInterpreter implements KeyListener {
    /**
     * Currently active, started game
     * @author Zsófi
     */
    private Game gameInstance;

    /**
     * The output CLI language will be written to this stream
     * Watch out: it is static, but will be set in the c'tor
     * ( Because every other class should see the log and at any given moment there is only one actively running log )
     * @author Zsófi
     */
    private PrintStream log;

    /**
     * @param out used as output of game
     * @param numOfPlayers Number of players in game
     * @author Zsófi
     */
    public void startGame(PrintStream out, int numOfPlayers) {
        log = out;
        gameInstance = Game.startRandomGame(log, numOfPlayers);
    }

    /**
     * Symbolizes the state of input acceptance:
     * We can wait for a new command, wait for arguments of a command
     * or disable waiting, while carrying out a task
     * @author Zsófi
     */
    public InputAcceptingState state;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    /**
     * If a key is released, the event is processed here,
     * with the help of the state machine of the interpreter (state)
     * @param e KeyEvent of released key
     * @author Zsófi
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (state) {
            case waiting_command:
                commandArrived(e);
                break;
            case waiting_saving_argument:
                break;
            case waiting_skill_argument:
                break;
        } // default would be disabled -> return
    }

    /**
     * @param e KeyEvent of command
     * @author Zsófi
     */
    private void commandArrived(KeyEvent e) {
        state = InputAcceptingState.disabled; // TODO ha a skill meg savePlayers másik key felfogása furán működne (lassan), akkor ezt kell átpakolni (Zsófi)
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                gameInstance.step(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                gameInstance.step(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                gameInstance.step(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                gameInstance.step(Direction.RIGHT);
                break;
            case KeyEvent.VK_I:
                Player p = PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());
                Tile currentTile = PositionLUT.getInstance().getPosition(p);
                ArrayList<Item> items = PositionLUT.getInstance().getItemOnTile(currentTile);
                for(Item i : items) {
                    if(i.getState().equals(ItemState.frozen))
                        items.remove(i);
                    if(items.size() == 0)
                        break;
                }
                // elvileg már csak egy maradhatott a listában TODO ellenőrizni
                gameInstance.pickUp(items.get(0));
                break;
            case KeyEvent.VK_D:
                Player p1 = PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());
                Tile currentTile1= PositionLUT.getInstance().getPosition(p1);
                ArrayList<Item> items1 = PositionLUT.getInstance().getItemOnTile(currentTile1);
                for(Item ii : items1) {
                    if(!ii.getState().equals(ItemState.frozen))
                        items1.remove(ii);
                    if(items1.size()== 0)
                        break;
                }
                gameInstance.digItemUp(items1.get(0)); // elvileg csak egy lehet ott befagyva
                break;
            case KeyEvent.VK_U:
                Player p2 = PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());
                if(p2 instanceof Eskimo) {
                    gameInstance.buildIgloo();
                }
                else state = InputAcceptingState.waiting_skill_argument;
                break;
            case KeyEvent.VK_C:
                gameInstance.clearSnow();
                break;
            case KeyEvent.VK_H:
                state = InputAcceptingState.waiting_saving_argument;
                break;
            case KeyEvent.VK_T:
                gameInstance.buildTent();
                break;
            case KeyEvent.VK_W:
                gameInstance.putSignalTogether();
                break;
            case KeyEvent.VK_ENTER:
                gameInstance.passRound();
                break;
        }
        // ha nem másik billentyűre várunk (-> lásd useSkill, savePlayers), akkor kell blokkolóról waitingre állítani
        if(state.equals(InputAcceptingState.disabled)) state = InputAcceptingState.waiting_command;
    }

    private void savingReady(KeyEvent e) {
        state = InputAcceptingState.disabled;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                gameInstance.savePlayers(Direction.UP);
                break;
            case KeyEvent.VK_LEFT:
                gameInstance.savePlayers(Direction.LEFT);
                break;
            case KeyEvent.VK_DOWN:
                gameInstance.savePlayers(Direction.DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                gameInstance.savePlayers(Direction.RIGHT);
                break;
        }
        state = InputAcceptingState.waiting_command;
    }

    private void researcherSkillReady(KeyEvent e) {
        state = InputAcceptingState.disabled;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                gameInstance.detectCapacity(Direction.UP);
                break;
            case KeyEvent.VK_LEFT:
                gameInstance.detectCapacity(Direction.LEFT);
                break;
            case KeyEvent.VK_DOWN:
                gameInstance.detectCapacity(Direction.DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                gameInstance.detectCapacity(Direction.RIGHT);
                break;
        }
        state = InputAcceptingState.waiting_command;
    }
}
