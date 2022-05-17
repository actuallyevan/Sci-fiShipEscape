import javax.swing.*;

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
        Board test = new Board(intializeBoard());
        game.gameLoop();
    }

    public static int[][] intializeBoard() {

        int[][] ret = new int[9][16];

        for(int i = 0; i < ret.length; i++) {
            for(int k = 0; i < ret[0].length; k++) {
                if((i == 0) || (i == 8) || (k == 0) || (k == 15)) {
                    ret[i][k] = 1;
                } else {
                    ret[i][k] = 0;
                }
            }
        }
        return ret;
    }

}
