package GameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;

public class Game {
    private HashMap<String, Cell> alive_cells;

    public Game(String[] initial_cells) {
        this.alive_cells = new HashMap<>();
        for (String cell_str : initial_cells) {
            String[] parts = cell_str.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            Cell cell = new Cell(x, y);
            String key = x + "," + y;
            this.alive_cells.put(key, cell);
        }

        for (Cell cell : this.alive_cells.values()) {
            alert_neighbors(cell, this.alive_cells, true);
        }
    }

    public Collection<Cell> iterate(int generations) {
        for (int generation = 0; generation < generations; generation++) {
            game_loop(this.alive_cells);
        }
        return this.alive_cells.values();
    }

    void game_loop(HashMap<String, Cell> alive_cells) {
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
                alert_neighbors(cell, alive_cells, true);
            }
        }

        for (Cell cell : to_dead) {
            String key = cell.x + "," + cell.y;
            alive_cells.remove(key);
            alert_neighbors(cell, alive_cells, false);
        }
    }

    void alert_neighbors(Cell cell, HashMap<String, Cell> alive_cells, boolean status) {
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
}