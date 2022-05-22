import java.awt.*;

public class Enemy extends Entity{
    private int direction;
    //0 up, 1 down, 2 left, 3 right
    //0 up right, 1 up left, 2 down left, 3 down right
    private int behavior;
    //0 means stationary, 1 means single axis movement, 2 means double axis movement.
    //3 means square movement, doesn't require collision enemy follows set path, just hardcoded it in, might be cool if enemy goes in square around a wall or something
    private int counter = 0;
    private int squareSize = 240;

    public Enemy(int xPos, int yPos, int height, int width, int speed, int direction, int behavior) {
        super(xPos, yPos, height, width, speed);
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
