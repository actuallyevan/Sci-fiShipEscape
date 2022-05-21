import java.awt.*;

public class Projectile extends Entity {

    private int direction;
    private int bloom;
    private double projX;
    private double projY;

    public Projectile (double projX, double projY, int height, int width, int speed, int direction, int bloom) {
        this.projX = projX;
        this.projY = projY;
        this.height = height;
        this.width = width;
        this.speed = speed;
        this.direction = direction;
        this.bloom = bloom;
    }

    public void projectileMove() {
        switch (direction) {
            case 0:
                projY -= speed;
                switch (bloom) {
                    case 0:
                        break;
                    case 1:
                        projX -= 0.5;
                        break;
                    case 2:
                        projX += 0.5;
                        break;
                }
                break;
            case 1:
                projY += speed;
                switch (bloom) {
                    case 0:
                        break;
                    case 1:
                        projX -= 0.5;
                        break;
                    case 2:
                        projX += 0.5;
                        break;
                }
                break;
            case 2:
                projX -= speed;
                switch (bloom) {
                    case 0:
                        break;
                    case 1:
                        projY -= 0.5;
                        break;
                    case 2:
                        projY += 0.5;
                        break;
                }
                break;
            case 3:
                projX += speed;
                switch (bloom) {
                    case 0:
                        break;
                    case 1:
                        projY -= 0.5;
                        break;
                    case 2:
                        projY += 0.5;
                        break;
                }
                break;
        }
    }


    public void draw (Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect((int) projX, (int) projY, width, height);
    }


}
