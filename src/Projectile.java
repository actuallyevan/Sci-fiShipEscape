import java.awt.*;

public class Projectile extends Entity {

    private int direction;
    private int bloom;
    private double projX;
    private double projY;
    private double bloomAmount = 0.5;

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
            case 0 -> {
                projY -= speed;
                switch (bloom) {
                    case 0:
                        break;
                    case 1:
                        projX -= bloomAmount;
                        break;
                    case 2:
                        projX += bloomAmount;
                        break;
                }
            }
            case 1 -> {
                projY += speed;
                switch (bloom) {
                    case 0:
                        break;
                    case 1:
                        projX -= bloomAmount;
                        break;
                    case 2:
                        projX += bloomAmount;
                        break;
                }
            }
            case 2 -> {
                projX -= speed;
                switch (bloom) {
                    case 0:
                        break;
                    case 1:
                        projY -= bloomAmount;
                        break;
                    case 2:
                        projY += bloomAmount;
                        break;
                }
            }
            case 3 -> {
                projX += speed;
                switch (bloom) {
                    case 0:
                        break;
                    case 1:
                        projY -= bloomAmount;
                        break;
                    case 2:
                        projY += bloomAmount;
                        break;
                }
            }
        }
    }

    public int getDirection() {
        return direction;
    }

    public double getProjY () {
        return projY;
    }

    public double getProjX () {
        return projX;
    }

    public void draw (Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect((int) projX, (int) projY, width, height);
    }


}
