import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

public class Game extends JPanel implements KeyListener {

    final int screenWidth = 1600;
    final int screenHeight = 900;
    final int NANO = 1000000000;
    Boolean[] isPressed = {false, false, false, false};
    Map<Character, Integer> keyMap = Map.of('w', 0, 's', 1, 'a', 2, 'd', 3);

    Entity player = new Entity(800,450,75,75, 10);
    Level backGround = new Level();

    public Game() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        backGround.level1();
    }

    public void gameLoop() {

        int counter = 0;

        long currentTime;

        while(true) {

            currentTime = System.nanoTime();

            update();

            repaint();

            while (System.nanoTime()-currentTime <= NANO/60) {

            }

            if(counter++ >= 60) {
                System.out.println(detectCurrentTile(player));
                System.out.println("next " + detectNextTile(player));
                counter = 0;
            }

        }
    }

    public void update() {
        executeNextTile(player);
        playerMove();
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        backGround.draw(g);
        player.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if (keyMap.containsKey(c)) {
            isPressed[keyMap.get(c)] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
        if (keyMap.containsKey(c)) {
            isPressed[keyMap.get(c)] = false;
        }
    }

    public void playerMove () {
        if (isPressed[0]) {
            player.moveUp();
        }

        if (isPressed[1]) {
            player.moveDown();
        }

        if (isPressed[2]) {
            player.moveLeft();
        }

        if (isPressed[3]) {
            player.moveRight();
        }
    }

    public Tile detectCurrentTile(Entity ent) {
        Tile currentTile = backGround.getTiles()[ent.getYpos()/100][ent.getXpos()/100];
        return currentTile;
    }

    public Tile detectNextTile(Entity ent) {
        int x = ent.getXpos();
        int y = ent.getYpos();
        if (isPressed[0]) {
            y -= ent.getSpeed();
        }

        if (isPressed[1]) {
            y += ent.getSpeed() + 75;
        }

        if (isPressed[2]) {
            x -= ent.getSpeed();
        }

        if (isPressed[3]) {
            x += ent.getSpeed() + 75;
        }
        Tile currentTile = backGround.getTiles()[y/100][x/100];
        return currentTile;
    }

    public void executeNextTile(Entity ent) {
        Tile currentTile = detectCurrentTile(ent);
        int x = ent.getXpos();
        int y = ent.getYpos();
        int dx = 0;
        int dy = 0;
        if(isPressed[2]) dx -= 10;
        if(isPressed[3]) dx += 85;
        if(isPressed[0]) dy -= 10;
        if(isPressed[1]) dy += 85;

        if(isPressed[2] || isPressed[3]) {
            Tile horizontalTile = backGround.getTiles()[y/100][(x+dx)/100];
            Tile horizontalTileTwo = backGround.getTiles()[(y+74)/100][(x+dx)/100];
            if((horizontalTile.getClass() == Wall.class || horizontalTileTwo.getClass() == Wall.class) && isPressed[2]) {
                ent.setXpos(horizontalTile.getXpos()+110);
            }
            if((horizontalTile.getClass() == Wall.class || horizontalTileTwo.getClass() == Wall.class) && isPressed[3]) {
                ent.setXpos(horizontalTile.getXpos()-85);
            }

        }

        if(isPressed[0] || isPressed[1]) {
            Tile verticalTile = backGround.getTiles()[(y + dy)/100][x/100];
            Tile verticalTileTwo = backGround.getTiles()[(y+dy)/100][(x+74)/100];
            if((verticalTile.getClass() == Wall.class || verticalTileTwo.getClass() == Wall.class) && isPressed[0]) {
                ent.setYpos(verticalTile.getYpos()+110);
            }
            if((verticalTile.getClass() == Wall.class || verticalTileTwo.getClass() == Wall.class) && isPressed[1]) {
                ent.setYpos(verticalTile.getYpos()-85);
            }
        }

    }
}





