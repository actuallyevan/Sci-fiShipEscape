import javax.swing.*;
import java.awt.*;

public class Entity extends JComponent {

    int xpos;
    int ypos;
    int playerHeight;
    int playerWidth;

    public Entity(int xpos, int ypos, int playerHeight, int playerWidth) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.playerHeight = playerHeight;
        this.playerWidth = playerWidth;
    }

    public void moveUp () {
        ypos-=10;
    }

    public void moveDown () {
        ypos+=10;
    }

    public void moveLeft () {
        xpos-=10;
    }

    public void moveRight() {
        xpos+=10;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(xpos, ypos, playerWidth, playerHeight);
    }
}
