import javax.swing.*;
import java.awt.*;

public class Tile extends JComponent {

    int tileWidth = 100;
    int tileHeight = 100;
    int xpos;
    int ypos;

    public Tile(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public void draw (Graphics g, Color tile) {
        g.setColor(tile);
        g.fillRect(xpos, ypos, tileWidth, tileHeight);
    }
}
