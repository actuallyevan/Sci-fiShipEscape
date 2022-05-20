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
            ypos-=speed;
    }

    public void moveDown () {
            ypos+=speed;
    }

    public void moveLeft () {
            xpos-=speed;
    }

    public void moveRight() {
            xpos+=speed;
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
