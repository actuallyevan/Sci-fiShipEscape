import javax.swing.*;
import java.awt.*;

public class Tile extends JComponent {

    int tileWidth = 100;
    int tileHeight = 100;
    int xpos;
    int ypos;
    Color tileColor = Color.gray;

    public Tile() {
        int xpos = 0;
        int ypos = 0;
    }

    public Tile(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public void draw (Graphics g) {
        g.setColor(tileColor);
        g.fillRect(xpos, ypos, tileWidth, tileHeight);
    }
}
