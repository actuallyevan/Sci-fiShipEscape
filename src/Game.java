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
    Board temp = new Board(intArr());

    public Game() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        System.out.println(temp);
    }

    public void gameLoop() {

        long currentTime;
        int counter = 0;

        while(true) {

            currentTime = System.nanoTime();

            update();

            repaint();

            while (System.nanoTime()-currentTime <= NANO/60) {

            }

//            if (counter++ >= 60) {
//                System.out.println(NANO / (System.nanoTime() - currentTime));
//                counter = 0;
//            }
        }
    }

    public void update() {
        playerMove();
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        temp.draw(g);
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

    public static int[][] intArr() {

        int[][] ret = new int[9][16];

        for(int i = 0; i < ret.length; i++) {
            for(int k = 0; k < ret[0].length; k++) {
                if((i == 0) || (i == 8) || (k == 0) || (k == 15)) {
                    ret[i][k] = 0;
                } else {
                    ret[i][k] = 1;
                }
            }
        }
        return ret;
    }

}

