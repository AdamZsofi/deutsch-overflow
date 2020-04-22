package PlayerClasses;
import CLI.Game;
import GlobalControllers.PositionLUT;
import GlobalControllers.RoundController;
import ItemClasses.*;
import TileClasses.*;
import TileClasses.Direction;

import java.io.IOException;
import java.util.Scanner;

/**
 * Class of Player
 * Functinalities:
 * -
 */
public abstract class Player extends Character{
    protected int BodyHeat;
    protected int ID;
    public int workPoints;
    public boolean inWater; // public lett, kesobb ezt átgondolhatjuk még
    protected Item inHand;
    protected Item wearing;

    /**
     * Initialisation for starting a round for Player
     * Sets workingPoint to 4
     */
    public Player(){
        inHand=null;
        inWater=false;
        wearing=null;
        workPoints=4;
    }

    public void startRound() {
        workPoints = 4;
        System.out.print("PlayerClasses.Player, ID"+ID+":");
        System.out.println("startRound()");
        System.out.println("Waiting for player input...");

        // TODO Ez a rész kb átkerült a Game és CommandInterpreterbe, Game methodjainak implementálásánál még jól jöhet, ezért majd akkor töröljük, ha azok készen vannak
        /*
        Tile position;
        while(workPoints>0 && !inWater) {
            System.out.println("Enter the activity:");
            Scanner scanner = new Scanner(System.in);
            int activity = scanner.nextInt();
           // scanner.close();
            switch (activity) {
                case 0:
                    step(Direction.valueOf(2));//left
                    break;
                case 1:
                    position = PositionLUT.getInstance().getPosition(this);
                    Item item = PositionLUT.getInstance().getItemOnTile(position).get(0);
                    pickUp(item);
                    break;
                case 2:
                    clearSnow();
                    break;
                case 3:
                    position = PositionLUT.getInstance().getPosition(this);
                    item = PositionLUT.getInstance().getItemOnTile(position).get(0);
                    digItemUp(item);
                    break;
                case 4:
                    savePlayers(Direction.valueOf(0));//up
                    break;
                case 5:
                    putSignalTogether(RoundController.getInstance().sg);
                case 6:
                    passRound();
                    break;
                default:
                    System.out.println("Invalid Activity number!");
                    break;
            }
        }
        passRound(); // ha elfogy a workPoint/vízbe esik, akkor automatikus pass
        */
    }

    /**
     * Called by the SnowyHole or UnstableTile
     * Sets inWater parameter of Player to TRUE
     */
    public void fallInWater() {
        inWater = true;
        Game.log.format("# Player>fallInWater : Player (PlayerId:%d) fall in Water \n", ID);
    }
    /**
     * Called by Food, if its picked up. Food is automatically eaten if its picked up
     * Removes the Food from Player's hand and increments the bodyHeat of Player
     */
    public void ateFood() {
        inHand = null;
        changeBodyHeat(1);
        Game.log.format("$ Player>ateFood : Player (PlayerId:%d) ate the Food \n", ID);
    }

    /**
     * Changes the Player bodyHeat with thisMuch
     * -ateFood(1)
     * -snowStorm(-1)
     * @param thisMuch thisMUCH (+/-)
     */
    public void changeBodyHeat(int thisMuch) {
        BodyHeat += thisMuch;
        Game.log.format("# Player>changeBodyHeat : Player (PlayerId:%d) bodyHeat is changed to %d (by %d much)\n", ID, BodyHeat, thisMuch);
    }
    /**
     * Called by DivingSuit, if its pickedUp (its automatically worn if its picked up)
     * Sets the diving suit to a specific variable, and takes out from the Player's hand
     */
    public void wear(DivingSuit suit) {
        wearing = suit;
        inHand = null;
        Game.log.format("$ Player>wear : Player (PlayerId:%d) now wears DivingSuit\n", ID);
    }

    // IControllable implementations:

    // getNeighbour throws IndexOutOfBounds, catch it here. (See details at Tile.getNeighbours())
    /*public void step(Direction dir) {
        System.out.print("(IControllable) Player:");
        System.out.println("step("+dir+")");
        if(dir == Direction.here) {
            System.out.println("You stay where you were");
            return;
        }

        Tile position= PositionLUT.getInstance().getPosition(this);
        try {
            Tile next_tile = position.getNeighbour(dir);
            position.steppedOff(dir);
            PositionLUT.getInstance().setPosition(this, next_tile);
            Item player_item = this.inHand;
            if(inHand!=null){
               PositionLUT.getInstance().setPosition(player_item,next_tile);
            }
            next_tile.steppedOn(this);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You can't go that way");
            return;
        }
        workPoints--;
    }*/

    /**
     * Player picks up an item
     * It Player's hand already has an item it takes back to actual Tile
     * It also decrements of working points of the player
     * @param i item
     */
    public void pickUp(Item i) {

        Tile position = PositionLUT.getInstance().getPosition(i);
        int snow = position.getSnow();
        if(snow==0) {
            if (inHand != null) {
                if (inHand.getState() == ItemState.thrownDown) {
                    inHand.diggedUp();
                } else {
                    inHand.thrownDown();
                    inHand = null;
                }
                Game.log.format("# Player>pickUp : Player (PlayerId:%d) old item from players hand has been removed and thrown down\n", ID);
            }
            inHand = i;
            i.pickedUp(this);
            Game.log.format("$ Player>pickUp : Player (PlayerId:%d) picked up item\n", ID);
        } else {
            Game.log.format("! Player>pickUp : Player (PlayerId:%d) item cannot picked up, Tile has snow\n", ID);
        }

        //TODO ha van snow a tileon akkor nem tudta felvenni, igy nem kell munkaponot levonni
        workPoints--;
        if(workPoints==0) {
            Game.log.format("# Player>pickUp : Player (PlayerId:%d) has no more workingPoints\n", ID);
            passRound();
        }
    }

    /**
     * Player clears snow
     * 1) Checks of the Player's hand has an item -> YES: it tries to clear snow with Shovel (1 unit of snow)
     * 2) (Another) unit of snow will be cleared from the Tile
     * It also decrements of working points of the player
     */
    //atirni protectedre
    public void clearSnow() {


        if (inHand != null) {
            if(inHand.getState()==ItemState.inHand){
                inHand.used(this,Activity.clearingSnow);
            }
        }

        Tile position= PositionLUT.getInstance().getPosition(this);
        position.changeSnow(-1);
        Game.log.format("$ Player>clearSnow : Player (PlayerId:%d) cleared snow\n", ID);
        workPoints--;

        if(workPoints==0) {
            Game.log.format("# Player>clearSnow : Player (PlayerId:%d) has no more workingPoints\n", ID);
            passRound();
        }

    }

    /**
     * Player digs to Item from Tile (calls the Tile to change the state of the item)
     * It also decrements of working points of the player
     * @param i item
     */
    //atirni protectedre
    public void digItemUp(Item i) {
        i.diggedUp();
        Game.log.format("$ Player>digItemUp : Player (PlayerId:%d) 'digItemUp' is completed\n", ID);
        workPoints--;
        if(workPoints==0) {
            Game.log.format("# Player>digItemUp : Player (PlayerId:%d) has no more workingPoints\n", ID);
            passRound();
        }
    }

    /**
     * Player saves another Players on neighbour Tile (defined by the direction)
     * It also decrements of working points of the player, if action is successful
     * Error handling: if direction is HERE, it makes no sense -> Reply message: "You can't save yourself"
     * @param dir direction
     */
    //atirni protectedre
    public void savePlayers(Direction dir) {
        Game.log.format("# Player>savePlayers : Player (PlayerId:%d) save started in direction:%s\n", ID, dir.toString());
        if(dir==Direction.valueOf(4)) {
            System.out.println("You cannot rescue yourself");
            Game.log.format("! Player>savePlayers : Player (PlayerId:%d) cannot rescue herself\n", ID);
            return;
        }
        inHand.used(this,Activity.savingPeople);
        workPoints--;
        if(workPoints==0) {
            Game.log.format("# Player>savePlayers : Player (PlayerId:%d) has no more workingPoints\n", ID);
            passRound();
        }
    }

    /**
     * Player puts the signal flare together
     * Calls the putTogether() function of the SignalFlare-> calls the win() function of RoundController
     * if all Player and SignalFlarePart are on the same tile
     * @param sg signal flare
     */
    //atirni protectedre
    public void putSignalTogether(SignalFlare sg) {
        Game.log.format("# Player>putSignalTogether : Player (PlayerId:%d) started putting together\n", ID);
        sg.putTogether(RoundController.getInstance());
    }

    /**
     * Player passes the round
     * Calls the endLastRound() function of RoundController
     */
    //atirni protectedre
    public void passRound() {
        Game.log.format("# Player>passRound : Player (PlayerId:%d) passed round\n", ID);
        RoundController.getInstance().endLastRound();
    }

    /**
     * Function to set the Player's hand to null (FragileShovel can be used just 3 times,
     * after it disappears from Player's hand)
     */
    // Done with IControllable Implementations
    public void dropFragileShovel(){
        inHand = null;
    }

    @Override
    public void step(Direction dir) {
            if(dir.getValue() == 4) {
                System.out.println("You stay where you were");
                Game.log.format("! Player>step : Player (PlayerId:%d) player has chosen HERE for step\n", ID);
                return;
            }
            // Player current_player= PlayerContainer.getInstance().getPlayer(RoundController.getInstance().getcurID());

            Tile position= PositionLUT.getInstance().getPosition(this);
            try {
                Tile next_tile = position.getNeighbour(dir);
                Tile bear_position= PositionLUT.getInstance().getPosition(RoundController.getInstance().polarbear);
                if(next_tile.equals(bear_position)){
                    System.out.println("Dangerous Direction, there is a PolarBear");
                    Game.log.format("! Player>step : Player (PlayerId:%d) cannot step in that direction(PolarBear)\n", ID);
                    return;
                }
                position.steppedOff(dir);
                PositionLUT.getInstance().setPosition(this, next_tile);
                Item player_item = this.inHand;
                if(this.inHand!=null){
                    PositionLUT.getInstance().setPosition(player_item,next_tile);
                }
                next_tile.steppedOn(this);
                Game.log.format("$ Player>step : Player (PlayerId:%d) Transaction 'stepping' is completed\n", ID);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You can't go that way");
                Game.log.format("! Player>step : Player (PlayerId:%d) cannot step in that direction(OutBound)\n", ID);
                return;
            }
            workPoints--;
            if(workPoints==0) {
                Game.log.format("# Player>step : Player (PlayerId:%d) has no more workingPoints\n", ID);
                passRound();
            }
    }
}
