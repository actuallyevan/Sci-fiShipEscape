
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener {

    final int screenWidth = 1920;
    final int screenHeight = 1080;

    final int NANO = 1000000000;

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

    Boolean[] isPressed = {false, false, false, false};

    public void update() {

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

//    public void paint(Graphics g) {
//
//        //super.paintComponent(g);
//
//        g.setColor(Color.black);
//        g.fillRect(0,0, screenWidth, screenHeight);
//    }


    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            isPressed[0] = true;
        }
        if (e.getKeyChar() == 's') {
            isPressed[1] = true;
        }
        if (e.getKeyChar() == 'a') {
            isPressed[2] = true;
        }
        if (e.getKeyChar() == 'd') {
            isPressed[3] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            isPressed[0] = false;
        }
        if (e.getKeyChar() == 's') {
            isPressed[1] = false;
        }
        if (e.getKeyChar() == 'a') {
            isPressed[2] = false;
        }
        if (e.getKeyChar() == 'd') {
            isPressed[3] = false;
        }
    }
}

