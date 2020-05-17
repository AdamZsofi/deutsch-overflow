package ItemClasses;

import Control.Game;
import GlobalControllers.PositionLUT;
import PlayerClasses.Player;

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

    @Override
    public String getShortName(){ return "F("+state.getShortName(getState())+")";}

    @Override
    public String toString() {return "food"+state.getShortName(getState());}
}
