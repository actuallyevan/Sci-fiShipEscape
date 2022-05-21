import java.awt.*;

public class Entity {

    int xPos;
    int yPos;
    int height;
    int width;
    int speed;

    public Entity() {

    }

    public Entity(int xPos, int yPos, int height, int width, int speed) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.width = width;
        this.speed = speed;
    }

    public void moveUp () {
            yPos-=speed;
    }

    public void moveDown () {
            yPos+=speed;
    }

    public void moveLeft () {
            xPos-=speed;
    }

    public void moveRight() {
            xPos+=speed;
    }


    public void draw (Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(xPos, yPos, width, height);
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int x) {
        xPos = x;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int y) {
        yPos = y;
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
