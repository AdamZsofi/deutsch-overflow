package ItemClasses;

import PlayerClasses.Player;

/**
 * Abstract Item class
 * Handles thrownDown, pickedUp, diggedUp actions of an Item
 * It has an used()  abstract method, which is implemented in the subclasses for specific tasks
 * It has an getSate() method to determine the state of the Item
 */
public abstract class Item {
    ItemState state; // state of the item

    Item() {
        state = ItemState.frozen;
    }

    /**
     * Sets the state of the item to 'thrownDown'
     */
    public void thrownDown(){
        System.out.println("ItemClasses.Item.thrownDown");
        state =  ItemState.thrownDown;
    }

    /**
     * Sets the state of the item to 'inHand'
     * @param Picker player, who calls the function
     */
    public void pickedUp(Player Picker){
        System.out.println("ItemClasses.Item.pickedUp");
        state = ItemState.inHand;
    }

    /**
     * Sets the state of the item to 'thrownDown'
     */
    public void diggedUp(){
        System.out.println("ItemClasses.Item.diggedUp");
        state = ItemState.thrownDown;
    }

    /**
     * Gives back the sate of the item
     * @return state of the item
     */
    public ItemState getState(){
        return state;
    }

    /**
     * This method is an abstract method, which is specifically implemented in the subclasses
     * @param p Player
     * @param a Activity
     */
    public void used(Player p, Activity a){ }
}
