
import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {

    final int screenWidth = 1920;
    final int screenHeight = 1080;

    int fps = 60;
    Thread gameThread;

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        long lastTime = System.nanoTime();
        long currentTime;
        double interval = 1000000000/fps;
        double timePassed = 0;
        int frameCounter = 0;
        long secondTimer = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();
            timePassed += (currentTime - lastTime) / interval;
            secondTimer += (currentTime - lastTime);
            lastTime = currentTime;

            if (timePassed >= 1) {
                //update();
                //repaint();
                timePassed--;
                frameCounter++;
            }

            if (secondTimer >= 1000000000) {
                System.out.println(frameCounter);
                secondTimer = 0;
                frameCounter = 0;
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
