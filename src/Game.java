
import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {

    final int screenWidth = 1920;
    final int screenHeight = 1080;

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
