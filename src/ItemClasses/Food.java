package ItemClasses;

import CLI.Game;
import GlobalControllers.PositionLUT;
import PlayerClasses.Player;

import java.util.Random;

public class Food extends Item{

    /**
     * If picked up, gets automatically eaten and respawns on a randomly selected tile,
     * in frozen state.
     * In skeleton random values are omitted, so it respawns on it's initial tile again.
     * @param p Player
     */
    @Override
    public void pickedUp(Player p) {
        PositionLUT.getInstance().setPosition(this, PositionLUT.getInstance().getTile(0, 2));
        state = ItemState.frozen;
        p.ateFood();
        Game.log.println("$ Food>pickedUp : Transaction 'pickUp' and 'ateFood' was successful");
    }
    public String getShortName(){ return "F("+state.getShortName(getState())+")";}
    public String toString() {return "Food";}
}
