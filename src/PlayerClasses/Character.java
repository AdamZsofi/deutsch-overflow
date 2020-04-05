package PlayerClasses;

import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import ItemClasses.Item;
import TileClasses.Direction;
import TileClasses.Tile;

import static ItemClasses.ItemState.inHand;

public abstract class Character {
    public void step(Direction dir) {
        System.out.print("(IControllable) Player:");
        System.out.println("step("+dir+")");
        if(dir == Direction.here) {
            System.out.println("You stay where you were");
            return;
        }
        Player current_player= PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());
        Tile position= PositionLUT.getInstance().getPosition(current_player);
        try {
            Tile next_tile = position.getNeighbour(dir);
            position.steppedOff(dir);
            PositionLUT.getInstance().setPosition(current_player, next_tile);
            Item player_item = current_player.inHand;
            if(current_player.inHand!=null){
                PositionLUT.getInstance().setPosition(player_item,next_tile);
            }
            next_tile.steppedOn(current_player);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You can't go that way");
            return;
        }
        current_player.workPoints--;
    }
}
