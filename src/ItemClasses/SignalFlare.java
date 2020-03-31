package ItemClasses;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import PlayerClasses.PlayerContainer;

import java.util.ArrayList;

public class SignalFlare {

    public ArrayList<SignalFlarePart> signalFlareParts;

    public void putTogether(RoundController rc){
        System.out.println("ItemClasses.SignalFlare.putTogether()");

        TileClasses.Tile t1=PositionLUT.pLUT.getPosition(signalFlareParts.get(0));

        PlayerContainer players=rc.getPlayerContainer();

        for (int i=0;i<players.players.size();i++) {//lehetne átláthatóbban csinalni
            if(PositionLUT.pLUT.getPosition(players.players.get(i))!=t1)
                return;
        }

        for (int i=0;i<3;i++) {//lehetne átláthatóbban csinalni
            if(PositionLUT.pLUT.getPosition(signalFlareParts.get(i))!=t1)
                return;
        }

        RoundController.rc.win();
    }
}
