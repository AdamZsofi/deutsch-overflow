public interface IControllable {

    void step(Direction dir);
    void pickUp(Item i);
    void clearSnown();
    void digItemUp();
    void savePlayers(Direction dir);
    void putSignalTogether(SignalFlare sg);
    void passRound();
}
