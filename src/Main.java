import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Game game = new Game();
        game.setVisible(true);
        game.setSize(1920, 1080);
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game.gameLoop();

    }
}
