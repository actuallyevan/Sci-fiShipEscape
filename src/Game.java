
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class Game extends JFrame implements KeyListener {

    final int screenWidth = 1920;
    final int screenHeight = 1080;
    final int NANO = 1000000000;
    Boolean[] isPressed = {false, false, false, false};
    Map<Character, Integer> keyMap = Map.of('w', 0, 's', 1, 'a', 2, 'd', 3);

    Entity player = new Entity(500,500,50,50);

    public Game() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        addKeyListener(this);
        add(player);
    }

    public void gameLoop() {

        long currentTime;
        int counter = 0;

        while(true) {

            currentTime = System.nanoTime();

            update();

            repaint();

            while (System.nanoTime()-currentTime < NANO/60) {

            }

            if (counter++ > 60) {
                System.out.println(NANO / (System.nanoTime() - currentTime));
                counter = 0;
            }
        }
    }

    public void update() {
        playerMove();
    }

    public void paint (Graphics g) {

        //super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0,0, screenWidth, screenHeight);
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
}

