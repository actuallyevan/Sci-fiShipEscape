
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener {

    final int screenWidth = 1920;
    final int screenHeight = 1080;

    int fps = 60;
    final int NANO = 1000000000;

    public Game() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        addKeyListener(this);
    }

    public void gameLoop() {

        long currentTime;
        int counter = 0;

        while(true) {

            currentTime = System.nanoTime();

            //update();
            //repaint();

            while (System.nanoTime()-currentTime < NANO/60) {

            }

            if (counter++ > 60) {
                System.out.println(NANO / (System.nanoTime() - currentTime));
                counter = 0;
            }
        }
    }

    public void paintComponent(Graphics g) {

        //super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0,0, screenWidth, screenHeight);
        g.setColor(Color.WHITE);
        g.fillRect(200, 200, 50, 75);

    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'a') {

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
