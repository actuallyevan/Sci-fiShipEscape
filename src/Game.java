
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


}
