import java.awt.*;

public class Board {

    private int[][] grid;
    private Color[] tiles;

    public Board(int [][] grid) {
        this.grid = grid;
        this.tiles = new Color[2];
        tiles[0] = Color.BLACK;
        tiles[1] = Color.DARK_GRAY;
    }

    public void draw(Graphics g) {

        for(int i = 0; i < grid.length; i++) {
            for(int k = 0; k < grid[0].length; k++) {
                Tile temp = new Tile(k * 100, i * 100);
                temp.draw(g, tiles[grid[i][k]]);

            }
        }

    }

    public String toString() {
        String str = "";
        for(int i = 0; i < grid.length; i++) {
            for(int k = 0; k < grid[0].length; k++) {
                str += grid[i][k] + " ";
            }
            str += "\n";
        }
        return str;
    }

}
