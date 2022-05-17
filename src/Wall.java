import java.awt.*;

public class Wall extends Tile {

    Color tileColor = Color.black;

    public Wall () {
        super(xpos, ypos);
    }
    @Override
    public void draw (Graphics g) {
        g.setColor(tileColor);
        g.fillRect(xpos, ypos, , wallHeight);
    }


}
