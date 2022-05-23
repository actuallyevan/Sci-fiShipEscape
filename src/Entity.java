import java.awt.*;

public class Entity {

    int xPos;
    int yPos;
    int height;
    int width;
    int speed;
    int health;

    public Entity() {

    }

    public Entity(int xPos, int yPos, int height, int width, int speed, int health) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.health = health;
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
        if (health == 0) {
            g.setColor(Color.blue);
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int hp) {
        health = hp;
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
