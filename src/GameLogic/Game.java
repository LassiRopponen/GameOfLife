package GameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;

public class Game {
    private HashMap<String, Cell> alive_cells;

    public Game() {
        this.alive_cells = new HashMap<>();
    }

    public void add_cell(int x, int y) {
        String key = x + "," + y;
        Cell cell = new Cell(x, y);
        alive_cells.put(key, cell);
        check_neighbors(cell);
        alert_neighbors(cell, true);
    }

    public void remove_cell(int x, int y) {
        String key = x + "," + y;
        Cell cell = alive_cells.get(key);
        if (cell != null) {
            alive_cells.remove(key);
            alert_neighbors(cell, false);
        }
    }

    public boolean cell_exists(int x, int y) {
        String key = x + "," + y;
        return alive_cells.containsKey(key);
    }

    public Collection<Cell> get_cells() {
        return alive_cells.values();
    }

    public void game_loop() {
        HashMap<String, Cell> to_alive = new HashMap<>();
        ArrayList<Cell> to_dead = new ArrayList<>();

        for (Cell current : alive_cells.values()) {
            current.alive_neighbors = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1) {
                        continue;
                    }
                    if (current.neighbors[i + j*3]) {
                        current.alive_neighbors++;
                    }
                    else {
                        String key = (current.x - 1 + i) + "," + (current.y - 1 + j);
                        Cell neighbor_cell = to_alive.get(key);
                        if (neighbor_cell == null) {
                            Cell new_cell = new Cell(current.x - 1 + i, current.y - 1 + j);
                            new_cell.alive_neighbors++;
                            new_cell.neighbors[(2-i) + (2-j)*3] = true;
                            to_alive.put(key, new_cell);
                        }
                        else {
                            neighbor_cell.alive_neighbors++;
                            neighbor_cell.neighbors[(2-i) + (2-j)*3] = true;
                        }
                    }
                }
            }
            if (current.alive_neighbors < 2 || current.alive_neighbors > 3) {
                to_dead.add(current);
            }
        }

        for (Cell cell : to_alive.values()) {
            if (cell.alive_neighbors == 3) {
                String key = cell.x + "," + cell.y;
                alive_cells.put(key, cell);
                alert_neighbors(cell, true);
            }
        }

        for (Cell cell : to_dead) {
            String key = cell.x + "," + cell.y;
            alive_cells.remove(key);
            alert_neighbors(cell, false);
        }
    }

    void alert_neighbors(Cell cell, boolean status) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                String key = (cell.x - 1 + i) + "," + (cell.y - 1 + j);
                Cell neighbor_cell = alive_cells.get(key);
                if (neighbor_cell != null) {
                    neighbor_cell.neighbors[(2-i) + (2-j)*3] = status;
                }
            }
        }
    }

    void check_neighbors(Cell cell) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }
                String key = (cell.x - 1 + i) + "," + (cell.y - 1 + j);
                Cell neighbor_cell = alive_cells.get(key);
                if (neighbor_cell != null) {
                    cell.neighbors[i + j*3] = true;
                }
            }
        }
    }
}