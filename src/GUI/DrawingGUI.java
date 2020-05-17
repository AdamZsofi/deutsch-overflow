package GUI;

import javax.imageio.ImageIO;
import javax.imageio.ImageTranscoder;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;

public class DrawingGUI {
    BufferedImage texture;
    int x, y, width, height;

    DrawingGUI(String textureName) {

        String name = null;
        try {
            name = "./src/GUI/Pack/" + textureName + ".png" ;
            texture = ImageIO.read(new File(name));
        } catch (Exception e) {
            System.out.println("File cannot opened");
        }
    }
}

