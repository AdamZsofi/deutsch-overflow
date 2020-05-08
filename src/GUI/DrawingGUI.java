package GUI;
import java.awt.image.BufferedImage;

import static sun.tools.jconsole.inspector.IconManager.getImage;

public class DrawingGUI {
    private BufferedImage texture;

    DrawingGUI(String textureName) {
        texture = getImage(textureName);
    }
    public void draw(int x, int y, int size) {

    }
}
