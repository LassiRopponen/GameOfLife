package GameLogic;

import java.util.Arrays;

public class Cell {
    public int x;
    public int y;
    boolean[] neighbors;
    int alive_neighbors;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.neighbors = new boolean[9];
        Arrays.fill(this.neighbors, false);
        this.alive_neighbors = 0;
    }

    public String toString() {
        return this.x + "," + this.y;
    }
}