import java.awt.*;

public class Tile {

    int tileWidth = 100;
    int tileHeight = 100;
    int xpos;
    int ypos;
    Color tileColor = Color.gray;

    public Tile() {
        int xpos = 0;
        int ypos = 0;
        tileWidth = 100;
        tileHeight = 100;
    }

    public Tile(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public void draw (Graphics g) {
        g.setColor(tileColor);
        g.fillRect(xpos, ypos, tileWidth, tileHeight);
    }

    public String toString() {
        String str = "";
        str += "Tile X position is " + xpos/100 + ", Y position is " + ypos/100;
        return str;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }
}
