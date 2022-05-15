import javax.swing.*;
import java.awt.*;

public class Tile extends JComponent {

    int tileWidth = 120;
    int tileHeight = 120;
    int xpos;
    int ypos;

    public Tile(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public void draw (Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(xpos, ypos, tileWidth, tileHeight);
    }
}
