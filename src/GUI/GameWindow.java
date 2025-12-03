package GUI;

import java.util.Collection;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import GameLogic.Cell;

public class GameWindow extends JPanel {
    private Collection<Cell> cells;

    public GameWindow() {
        this.setBackground(Color.WHITE);
    }

    public void update_cells(Collection<Cell> cells) {
        this.cells = cells;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600,450);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        int width = (int) g.getClipBounds().getWidth();
        int height = (int) g.getClipBounds().getHeight();
        for (int i = 0; i < width; i += 25) {
            g.drawLine(i, 0, i, height);
        }
        for (int j = 0; j < height; j += 25) {
            g.drawLine(0, j, width, j);
        }
        if (cells != null) {
            g.setColor(Color.YELLOW);
            for (Cell cell : cells) {
                int x = ((width/25)/2 + cell.getX()) * 25;
                int y = ((height/25)/2 - cell.getY()) * 25;
                g.fillRect(x, y, 25, 25);
            }
        }
    }
}