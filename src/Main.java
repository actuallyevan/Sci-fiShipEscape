import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        Game game = new Game();
        window.setVisible(true);
        window.setResizable(false);
        window.setSize(1600, 900);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.add(game);
        window.addKeyListener(game);
        window.pack();
        game.gameLoop();
    }
}
