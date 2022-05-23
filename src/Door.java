import java.awt.*;

public class Door extends Tile
{
    Color doorColor = Color.BLUE;

    public Door (int xPos, int yPos) {
        super(xPos, yPos);
    }
    @Override
    public void draw (Graphics g) {
        g.setColor(doorColor);
        g.fillRect(xPos, yPos, tileWidth, tileHeight);
    }
}
