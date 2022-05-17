import java.awt.*;

public class Entity {

    int xpos;
    int ypos;
    int playerHeight;
    int playerWidth;
    int speed;

    public Entity(int xpos, int ypos, int playerHeight, int playerWidth, int speed) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.playerHeight = playerHeight;
        this.playerWidth = playerWidth;
        this.speed = speed;
    }

    public void moveUp () {
        if (ypos-speed >= 25+playerHeight) {
            ypos-=speed;
        } else {
            ypos = 25+playerHeight;
        }
    }

    public void moveDown () {
        if (ypos+speed <= 800-playerHeight) {
            ypos+=speed;
        } else {
            ypos = 800-playerHeight;
        }
    }

    public void moveLeft () {
        if (xpos-speed >= 25+playerHeight) {
            xpos-=speed;
        } else {
            xpos = 25+playerHeight;
        }
    }

    public void moveRight() {
        if (xpos+speed <= 1500-playerHeight) {
            xpos+=speed;
        } else {
            xpos = 1500-playerHeight;
        }
    }


    public void draw (Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(xpos, ypos, playerWidth, playerHeight);
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

}
