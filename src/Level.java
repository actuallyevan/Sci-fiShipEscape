import java.awt.*;

public class Level {

    private Tile[][] tiles = new Tile[9][16];

    public Level() {
        createBackground();
    }

    public void level1() {

    }

    public void createBackground() {
        for (int i = 0; i < tiles.length; i++) {
            for (int k = 0; k < tiles[0].length; k++) {
                if ((i == 0) || (i == 8) || (k == 0) || (k == 15)) {
                    tiles[i][k] = new Wall(k * 100, i * 100);
                } else {
                    tiles[i][k] = new Tile(k * 100, i * 100);
                }
            }
        }
    }

    public void draw(Graphics g) {

        for (Tile[] i : tiles) {
            for (Tile k : i) {
                k.draw(g);
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles;
    }

}
