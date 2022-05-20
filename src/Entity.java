import java.awt.*;

public class Entity {

    int xpos;
    int ypos;
    int height;
    int width;
    int speed;

    public Entity(int xpos, int ypos, int height, int width, int speed) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.height = height;
        this.width = width;
        this.speed = speed;
    }

    public void moveUp () {
//        if (ypos-speed >= 25+height) {
            ypos-=speed;
//        } else {
//            ypos = 25+height;
//        }
    }

    public void moveDown () {
//        if (ypos+speed <= 800-height) {
            ypos+=speed;
//        } else {
//            ypos = 800-height;
//        }
    }

    public void moveLeft () {
//        if (xpos-speed >= 25+height) {
            xpos-=speed;
//        } else {
//            xpos = 25+height;
//        }
    }

    public void moveRight() {
//        if (xpos+speed <= 1500-height) {
            xpos+=speed;
//        } else {
//            xpos = 1500-height;
//        }
    }


    public void draw (Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(xpos, ypos, width, height);
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int x) {
        xpos = x;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int y) {
        ypos = y;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
