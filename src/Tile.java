import java.awt.*;

public class Tile {

    int tileWidth = 100;
    int tileHeight = 100;
    int xPos;
    int yPos;
    Color tileColor = Color.gray;

    public Tile(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void draw (Graphics g) {
        g.setColor(tileColor);
        g.fillRect(xPos, yPos, tileWidth, tileHeight);
    }

    public String toString() {
        String str = "";
        str += "Tile X position is " + xPos/100 + ", Y position is " + yPos/100;
        return str;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
