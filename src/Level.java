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
        tiles[4][15] = new Door(1500, 400);
    }

    public void level3() {
        createBackground();
        createRow(6,2,4);
        tiles[7][4] = new Wall(400, 700);
        createRow(2,11,13);
        tiles[1][11] = new Wall(1100, 100);
        createRow(3,2,3);
        tiles[2][3] = new Wall(300, 200);
        createRow(5,12,13);
        tiles[6][12] = new Wall(1200, 600);
        tiles[0][7] = new Door(700, 0);
    }

    public void level4() {
        createBackground();
        createRow(6,4,9);
        createRow(2,4,12);
        createRow(4,4,11);
        createColumn(2,2,6);
        createColumn(13,2,6);
        tiles[3][11] = new Wall(1100, 300);
        tiles[4][3] = new Wall(300, 400);
        tiles[1][10] = new Wall(1000, 100);
        tiles[7][11] = new Wall(1100, 700);
        tiles[6][11] = new Wall(1100, 600);
        tiles[5][6] = new Wall(600, 500);
        tiles[3][10] = new Door(1000, 300);
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
