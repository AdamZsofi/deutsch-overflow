package GUI;

import GlobalControllers.PositionLUT;
import ItemClasses.Item;
import PlayerClasses.PlayerContainer;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InGame {
    //internal variables
    private Map<String, DrawingGUI> icons = new HashMap<String, DrawingGUI>();

    private ArrayList<DrawingGUI> [][] elements = new ArrayList[6][6]; // on each Tile there's a ArrayList of Drawables
    private DrawingGUI [] [] tiles = new DrawingGUI[6][6];
    private ArrayList<DrawingGUI> [] hand  = new ArrayList[6];  //Collection of Drawables for each player hand
                                                                // (Note: DivingSuit counts here=>ArrayList)
    private int [] bodyHeats = new int[6];


    //amikor rajzolz hasznald relativan, ezekre a valtozokra hivatkozva a ikonok kirajzolasat
    //for more information OneDrive/GUI/sizes.png
    private int margin;
    private int tilePadding;
    private int tileSize;
    private int playerBoxX;
    private int playerBoxY;
    private int messageBoxX;
    private int messageBoxY;
    private int barPadding;
    private int textPadding;
    private int statusAndActionBoxY;
    private int statusBoxX;
    private int actionBoxX;



    // DRAWING GUI CLASSBAN a draw fuggveny(int size: parametere) => inkabb ott int lett,
    // mert szivecsket meg ilyenek is ki kell rajzolni nagyon sok lehetoseg lenne
    //viszont InGame-n belul meghivathjuk enummal ->enum intte castolodik, es mindegyik enumnak van egy konkret erteke
    //enumot pl hasznalhatsz Tilon levo elemek, playerek draw(enum size==>>int) fgvnel


    //sajnos nem latunk bele a positonLUTba, nem tudjuk indexelni a Tilokat x, y szerint
    //TODO kitalani valami ugyeset, hogy fel legyen toltve a elements tomb,
    //a setElemtsFugvt mondjuk hivhatod kivulrol, amikor beallitjuk es bepushuoljuk a dolgokat a postionLUTba
    //elementName = item.toString() / player.ToString()
    // x, y "Tileok indexei"
    public void setElements(int x, int y, String elementName) {
        elements[x][y].add(icons.get(elementName));
    }


    private void initComponents() {
        initIcons();
        //TODO sajnos a elements Array 2D tombot kivulrol lehet feltolteni Gegenstandokkal, Playererkkel hasznald kivulrol a setElements fugv-t

        //loading Tile Icons
        for (int i = 0 ; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                tiles[i][j] = icons.get("tileSnow");
            }
        }

        //loading bodyHeats for all players => for PlayerBar (right-upper corner)
        for (int i = 0; i < 6; i++) {
            bodyHeats[i] = PlayerContainer.getInstance().getPlayer(i).getBodyHeat();
        }
        //loading the handItem Icons for all players => for PlayerBar (right-upper corner)
        for (int i = 0; i < 6; i++) {
            ArrayList<Item> items = PlayerContainer.getInstance().getPlayer(i).getItemsOnHand();
            for(Item it : items) {
                hand[i].add(icons.get(it.toString()));
            }
        }


    }

    private void initIcons() {
        //loading players
        //in runtime revealed if a player is eskimo or researcher
        for (int i = 0; i < 6; i++) {
            String p = PlayerContainer.getInstance().getPlayer(i).toString();
            icons.put(p, new DrawingGUI(p +".svg"));
            icons.put(p +"-a", new DrawingGUI(p + "-a.svg"));
        }

        //loading polarbear
        icons.put("P", new DrawingGUI("P.svg"));
        icons.put("P-a", new DrawingGUI("P-a.svg"));

        //loading items
        icons.put("divingSuitT", new DrawingGUI("divingSuitT.svg"));
        icons.put("divingSuitF", new DrawingGUI("divingSuitF.svg"));
        icons.put("foodT", new DrawingGUI("foodT.svg"));
        icons.put("foodF", new DrawingGUI("foodF.svg"));
        icons.put("fragileShovelT", new DrawingGUI("fragileShovelT.svg"));
        icons.put("fragileShovelF", new DrawingGUI("fragileShovelF.svg"));
        icons.put("signalflarePart1T", new DrawingGUI("signalflarePart1T.svg"));
        icons.put("signalflarePart1F", new DrawingGUI("signalflarePart1F.svg"));
        icons.put("signalflarePart2T", new DrawingGUI("signalflarePart2T.svg"));
        icons.put("signalflarePart2F", new DrawingGUI("signalflarePart2F.svg"));
        icons.put("signalflarePart3T", new DrawingGUI("signalflarePart0T.svg"));
        icons.put("signalflarePart3F", new DrawingGUI("signalflarePart0F.svg"));
        icons.put("ropeT", new DrawingGUI("ropeT.svg"));
        icons.put("ropeF", new DrawingGUI("ropeF.svg"));
        icons.put("shovelT", new DrawingGUI("shovelT.svg"));
        icons.put("shovelF", new DrawingGUI("shovelF.svg"));

        //other actions
        icons.put("capacity", new DrawingGUI("capacity.svg"));
        icons.put("clearSnow", new DrawingGUI("clearSnow.svg"));
        icons.put("digUp", new DrawingGUI("digUp.svg"));
        icons.put("igloo", new DrawingGUI("igloo.svg"));
        icons.put("passRound", new DrawingGUI("passRound.svg"));
        icons.put("pickUp", new DrawingGUI("pickUp.svg"));
        icons.put("tent", new DrawingGUI("tent.svg"));

        //loading tiles
        icons.put("tileNoSnow", new DrawingGUI("tileNoSnow.svg"));
        icons.put("tileSnow", new DrawingGUI("tileSnow.svg"));
        icons.put("tileWater", new DrawingGUI("tileWater.svg"));
        //other icons
        icons.put("snow", new DrawingGUI("snow.svg"));
        icons.put("inWater", new DrawingGUI("inWater.svg"));
        icons.put("heart", new DrawingGUI("heart.svg"));
        icons.put("warning", new DrawingGUI("warning.svg"));
        icons.put("workingPoints", new DrawingGUI("workingPoints.svg"));
    }

    @Override
    public void start(Stage primaryStage) {
        /* //ez valami basic demo
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }
    public static void main(String[] args) {
        launch(args);
    }
}