
import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {

    final int screenWidth = 1920;
    final int screenHeight = 1080;

    int FPS = 60;
    Thread gameThread;

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        long timer = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        double fps;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            //update();

            //repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
                timer += (currentTime - lastTime);

                fps = 1000000000.0 / (System.nanoTime() - lastTime);
                lastTime = System.nanoTime();

                if(timer >= 1000000000) {
                    System.out.println(fps);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();

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
