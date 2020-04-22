package ItemClasses;

import CLI.Game;
import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import PlayerClasses.Player;
import PlayerClasses.PlayerContainer;

import java.util.ArrayList;

/**
 *
 * DESIGN PERSPECTIVE: It makes no sense to differentiate the SignalParts (no different behaviour, no more classes)
 * -> each part has just an (unique)id
 */
public class SignalFlare {
    /**
     * @param singalFlareParts parts of SignalFlare
     */
    public ArrayList<SignalFlarePart> signalFlareParts;
    //3 db alkatrész

    public SignalFlare(){
        signalFlareParts = new ArrayList<>();
        signalFlareParts.add(new SignalFlarePart(0));
        signalFlareParts.add(new SignalFlarePart(1));
        signalFlareParts.add(new SignalFlarePart(2));
    }

    /**
     * Checks all player && all item on a Tile -> calls RoundController win() method
     * @param rc RoundController
     */
    public void putTogether(RoundController rc){
        TileClasses.Tile t1 = PositionLUT.getInstance().getPosition(signalFlareParts.get(0));
        int playerNum = PlayerContainer.getInstance().getPlayerNum();

        ArrayList<Player> players = PositionLUT.getInstance().getPlayersOnTile(t1);

        if(players.size()<playerNum) {
            Game.log.println("! SignalFlare>putTogether : Not all players are on Tile");
            return;
        }
        for (int i=0;i<3;i++) {
            if(!(PositionLUT.getInstance().getPosition(signalFlareParts.get(i)).equals(t1)))
            {
                Game.log.println("! SignalFlare>putTogether : Not all signalFlareParts are on Tile");
                return;
            }
        }
        Game.log.println("# SignalFlare>putTogether : Ready to win");
        rc.win();
        Game.log.println("$ SignalFlare>putTogether : Transaction 'putTogether' was successful");
    }
}
