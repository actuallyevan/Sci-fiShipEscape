import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame f = new JFrame();
        f.setVisible(true);
        f.setSize(1920, 1080);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Game game = new Game();
        f.add(game);

    }
}
