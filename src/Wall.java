import java.awt.*;

public class Wall extends Tile {

    int wallWidth = 100;
    int wallHeight = 100;
    int xpos;
    int ypos;
    Color tileColor = Color.black;

    public Wall (int xpos, int ypos) {
        super();
        this.xpos = xpos;
        this.ypos = ypos;
    }
    @Override
    public void draw (Graphics g) {
        g.setColor(tileColor);
        g.fillRect(xpos, ypos, wallWidth, wallHeight);
    }


}
