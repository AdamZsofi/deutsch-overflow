
package GUI;
import Control.Game;
import Main.Main;
import javax.swing.*;


public class InGame extends JFrame {
    protected static InGame inGame;

    public static InGame getInstance() {
        if(inGame == null) {
            inGame = new InGame();
        }
        return inGame;
    }

    public static GamePanel gamePanel = null;
    static Menu menu = new Menu();

    InGame(){
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 750);
        this.getContentPane().add(menu);
        setVisible(true);
    }

    public void changeToGameScreen(int playerNum){
        this.getContentPane().removeAll();
        //frame.getContentPane().add(new JPanel());
        gamePanel = new GamePanel();

        gamePanel.addKeyListener(Main.interpreter);
        gamePanel.setFocusable(true);
        Main.interpreter.startGame(playerNum);

        add(gamePanel);
        gamePanel.refreshComponents();
        gamePanel.requestFocus();
        revalidate();
    }

    public static void createWelcomeScreen() {
        inGame = new InGame();
    }
}