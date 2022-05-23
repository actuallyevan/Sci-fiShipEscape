import java.awt.*;

public class Enemy extends Entity{
    private int direction;
    private int behavior;
    private int counter = 0;
    private int squareSize = 240;

    public Enemy(int xPos, int yPos, int height, int width, int speed, int health, int direction, int behavior) {
        super(xPos, yPos, height, width, speed, health);
        this.direction = direction;
        this.behavior = behavior;
        if (this.behavior == 2) {
            this.speed /= 2;
        }
    }

    public void enemyMove() {
        switch(behavior) {
            case 0:
                break;
            case 1:
                switch (direction) {
                    case 0 -> yPos -= speed;
                    case 1 -> yPos += speed;
                    case 2 -> xPos -= speed;
                    case 3 -> xPos += speed;
                }
                break;
            case 2:
                switch (direction) {
                    case 0 -> {
                        xPos += speed;
                        yPos -= speed;
                    }
                    case 1 -> {
                        xPos -= speed;
                        yPos -= speed;
                    }
                    case 2 -> {
                        xPos -= speed;
                        yPos += speed;
                    }
                    case 3 -> {
                        xPos += speed;
                        yPos += speed;
                    }
                }
                break;
            case 3:
                if (counter > squareSize) {
                    counter = 0;
                }
                if (counter < (squareSize*0.25)) {
                    xPos += speed;
                } else if (counter >= (squareSize*0.25) && counter < (squareSize*0.5)) {
                    yPos += speed;
                } else if (counter >= (squareSize*0.5) && counter < (squareSize*0.75)) {
                    xPos -= speed;
                } else if ( counter >= (squareSize*0.75) && counter < squareSize) {
                    yPos -= speed;
                }
                counter++;
                break;
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getBehavior() {
        return behavior;
    }

    public void setBehavior(int behavior) {
        this.behavior = behavior;
    }

    public void draw (Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(xPos, yPos, width, height);
    }


}
