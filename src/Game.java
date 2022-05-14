
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

    public void update() {

    }

    public void paintComponent(Graphics g) {

        //super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0,0, screenWidth, screenHeight);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        e.consume();
    }

    boolean isPressed = false;

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyChar() == 'w') {
            player.moveUp();
        }
        if (e.getKeyChar() == 's') {
            player.moveDown();
        }
        if (e.getKeyChar() == 'a') {
            player.moveLeft();
        }
        if (e.getKeyChar() == 'd') {
            player.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        e.consume();
    }
}
