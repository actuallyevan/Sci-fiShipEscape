import java.awt.*;

public class Level {


    private Tile[][] tiles = new Tile[9][16];

    public Level() {
        createBackground();
    }

    public void level1() {
        createBackground();
        tiles[3][7] = new Wall(700, 300);
        tiles[4][6] = new Wall(600, 400);
        tiles[5][7] = new Wall(700, 500);
        tiles[0][7] = new Door(700, 0);
    }
    public void level2(){
        createBackground();
        tiles[5][4] = new Wall(400, 500);
        tiles[6][4] = new Wall(400, 600);
        tiles[3][12] = new Wall(1200, 300);
        tiles[5][3] = new Wall(300, 500);
        tiles[3][15] = new Door(1500, 300);
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
