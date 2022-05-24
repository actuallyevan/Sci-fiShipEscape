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
        createRow(4,2,6);
        createRow(4,9,13);
        createColumn(6,0,3);
        createColumn(9,5,7);
    }

    public void level3() {

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

    public void createColumn(int row, int colStart, int colEnd) {
        for (int i = 0; i < tiles.length; i++) {
            for (int k = 0; k < tiles[0].length; k++) {
                if ((k == row) && ((i >= colStart) && (i <= colEnd))) {
                    tiles[i][k] = new Wall(k * 100, i * 100);
                }
            }
        }
    }

    public void createRow(int col, int rowStart, int rowEnd) {
        for (int i = 0; i < tiles.length; i++) {
            for (int k = 0; k < tiles[0].length; k++) {
                if ((i == col) && ((k >= rowStart) && (k <= rowEnd))) {
                    tiles[i][k] = new Wall(k * 100, i * 100);
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
