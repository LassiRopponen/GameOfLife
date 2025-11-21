package GUI;

import java.util.Collection;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import GameLogic.Cell;

public class GameWindow extends JPanel {
    private Collection<Cell> cells;

    public GameWindow(Collection<Cell> cells) {
        this.setBackground(Color.WHITE);
        this.cells = cells;
    }

    public Dimension getPreferredSize() {
        return new Dimension(450,300);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        int width = (int) g.getClipBounds().getWidth();
        int height = (int) g.getClipBounds().getHeight();
        for (int i = 0; i < width; i += 10) {
            g.drawLine(i, 0, i, height);
        }
        for (int j = 0; j < height; j += 10) {
            g.drawLine(0, j, width, j);
        }
    }
}