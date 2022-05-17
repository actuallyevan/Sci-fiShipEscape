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
//        System.out.println(backGround);
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
        playerMove();
        executeNextTile(detectNextTile(player), player);
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
            y += ent.getSpeed();
        }

        if (isPressed[2]) {
            x -= ent.getSpeed();
        }

        if (isPressed[3]) {
            x += ent.getSpeed();
        }
        Tile currentTile = backGround.getTiles()[y/100][x/100];
        return currentTile;
    }

    public void executeNextTile(Tile tile, Entity ent) {
        if(tile.getClass() == Wall.class) {
            if (isPressed[0]) {
                player.moveDown();
            }

            if (isPressed[1]) {
                ent.setYpos(tile.getYpos() - 100);

            }

            if (isPressed[2]) {
                player.moveRight();
            }

            if (isPressed[3]) {
                player.moveLeft();
            }
        }
    }

}



