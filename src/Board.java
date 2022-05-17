import java.awt.*;

public class Board {

    private int[][] grid;
    private Color[] tiles;

    public Board(int [][] grid) {
        this.grid = grid;
        this.tiles = new Color[] {Color.darkGray, Color.black, Color.red};
        System.out.println(tiles[1]);
    }

}
