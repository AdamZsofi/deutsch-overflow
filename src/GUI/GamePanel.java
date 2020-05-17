package GUI;

import GlobalControllers.PositionLUT;
import PlayerClasses.PlayerContainer;
import TileClasses.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JPanel {

    int tileCorner = 50;
    int tileSize = 100;

    private Map<String, DrawingGUI> icons = new HashMap<String, DrawingGUI>();
    /**
     * collection of components from positionLUT
     */
    ArrayList<DrawingGUI> components = new ArrayList<>();

    GamePanel(){
        setLayout(new GridBagLayout());
    }

    private void initIcons(int playerNum) {
        //loading players
        //in runtime revealed if a player is eskimo or researcher
        for (int i = 0; i < playerNum; i++) {
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
        icons.put("tileNoSnow", new DrawingGUI("ice.png"));
        icons.put("tileSnow", new DrawingGUI("ice.png"));
        icons.put("tileWater", new DrawingGUI("ice.png"));
        //other icons
        icons.put("snow", new DrawingGUI("snow.svg"));
        icons.put("inWater", new DrawingGUI("inWater.svg"));
        icons.put("heart", new DrawingGUI("heart.svg"));
        icons.put("warning", new DrawingGUI("warning.svg"));
        icons.put("workingPoints", new DrawingGUI("workingPoints.svg"));
    }


    /**
     * clears former list elements
     * fills up components from positionLUT
     *
     */
    void refreshComponents(){
        components.clear();

        //loading tiles from positionLUT
        for(int x = 0; x < 6; x++){
            for(int y = 0; y < 6; y++) {
                DrawingGUI dgui = new DrawingGUI((PositionLUT.getTile(x, y).toString()));
                try {
                    dgui.texture = ImageIO.read(new File("src\\GUI\\Pack\\ice.png"));
                } catch (IOException e) {
                }
                dgui.x = tileCorner + x* (tileSize+1);
                dgui.y = tileCorner + y* (tileSize+1);
                dgui.width = tileSize;
                dgui.height = tileSize;
                components.add(dgui);
            }
        }
    }


    public void paintComponent (Graphics g)
    {
        refreshComponents();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage image = null;//new ImageIcon("GUI/Pack/start.jpg");
        try {
            image = ImageIO.read(new File("src\\GUI\\Pack\\start.jpg"));
        } catch (IOException e) {
        }
        for (DrawingGUI dg:components) {
            g2d.drawImage(dg.texture, dg.x, dg.y, dg.width, dg.height, null);
        }
        setVisible(true);

    }

}
