import java.awt.*;

public class Projectile extends Entity {

    private int direction;

    public Projectile (int xpos, int ypos, int height, int width, int speed, int direction) {
        super(xpos, ypos, height, width, speed);
        this.direction = direction;
    }

    public void projectileMove() {
        switch (direction) {
            case 0:
                ypos -= speed;
                break;
            case 1:
                ypos += speed;
                break;
            case 2:
                xpos -= speed;
                break;
            case 3:
                xpos += speed;
                break;
        }
    }


    public void draw (Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(xpos, ypos, width, height);
    }


}
