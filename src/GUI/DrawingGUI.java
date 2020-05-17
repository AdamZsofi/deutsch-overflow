package GUI;

import javax.imageio.ImageIO;
import javax.imageio.ImageTranscoder;
import java.awt.image.BufferedImage;
import java.io.*;

public class DrawingGUI {
    private BufferedImage texture;

    DrawingGUI(String textureName) {
        String name = null;
        try {
            name = "./src/GUI/Pack/" + textureName;
            texture = ImageIO.read(new File(name));
        } catch (Exception e) {
            System.out.println("File cannot opened");
        }
        System.out.println(name);
    }
    public void draw(int x, int y, int size) {
    }

}

