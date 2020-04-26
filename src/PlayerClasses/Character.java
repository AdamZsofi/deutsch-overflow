package PlayerClasses;

import TileClasses.Direction;

public abstract class Character {
    /**
     * Realises stepping of a Character, changes the position of Character in PositionLUT
     * Error handling: if caller(Player) would like to step to a TIle where is a PolarBear: "Dangerous Direction" error message
     * Error handling: if direction given is HERE: "You stay where you were" error message
     * Error handling: if there isn't Tile in given direction: "You can't go that way" error message
     * @param dir Direction where to step
     */
    public abstract  void step(Direction dir);
    public abstract String getShortName();
}
