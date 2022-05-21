import java.awt.*;

public class Wall extends Tile {

    Color tileColor = Color.black;

    public Wall (int xPos, int yPos) {
        super(xPos, yPos);
    }
    @Override
    public void draw (Graphics g) {
        g.setColor(tileColor);
        g.fillRect(xPos, yPos, tileWidth, tileHeight);
    }


}
