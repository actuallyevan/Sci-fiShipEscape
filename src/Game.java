
import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {

    final int screenWidth = 1920;
    final int screenHeight = 1080;

    int fps = 60;
    final int NANO = 1000000000;
    Thread gameThread;

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        long currentTime;
        int counter = 0;

        while(gameThread != null) {

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

    public Game() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect(200, 200, 50, 75);

    }


}
