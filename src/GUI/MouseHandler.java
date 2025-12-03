package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import GameLogic.Game;

public class MouseHandler extends MouseAdapter {
        private Game game;
        private GameWindow window;

        public MouseHandler(Game game, GameWindow window) {
            this.game = game;
            this.window = window;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = (e.getX() / 25) - ((window.getWidth() / 25) / 2);
            int y = ((window.getHeight() / 25) / 2) - (e.getY() / 25);
            if (game.cell_exists(x, y)) {
                game.remove_cell(x, y);
            }
            else {
                game.add_cell(x, y);
            }
            window.update_cells(game.get_cells());
        }
    }