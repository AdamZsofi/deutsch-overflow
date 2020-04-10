package ItemClasses;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import PlayerClasses.Player;
import PlayerClasses.PlayerContainer;

import java.util.ArrayList;

/**
 *
 * DEGIN PERSPECTIVE: It makes no sense to differentiate the SignalParts (no different behaviour)
 * -> each part has just an (unique)id
 */
public class SignalFlare {

    public ArrayList<SignalFlarePart> signalFlareParts;
    //3 db alkatrész

    public SignalFlare(){
        signalFlareParts = new ArrayList<>();
        signalFlareParts.add(new SignalFlarePart(0));
        signalFlareParts.add(new SignalFlarePart(1));
        signalFlareParts.add(new SignalFlarePart(2));
    }

    /**
     * Checks all player  && all item on a Tile -> WIN
     * @param rc RoundController
     */
    public void putTogether(RoundController rc){
        System.out.println("ItemClasses.SignalFlare.putTogether()");

        TileClasses.Tile t1 = PositionLUT.getInstance().getPosition(signalFlareParts.get(0));
        int playerNum = PlayerContainer.getInstance().getPlayerNum();

        ArrayList<Player> players = PositionLUT.getInstance().getPlayersOnTile(t1);

        if(players.size()<playerNum)return;
        for (int i=0;i<3;i++) {
            if(!(PositionLUT.getInstance().getPosition(signalFlareParts.get(i)).equals(t1)))
                return;
        }
        rc.win();
    }
}
