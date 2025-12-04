import javax.swing.SwingUtilities;
import javax.swing.JFrame;

import GameLogic.*;
import GUI.*;

class Main {
    static Game game;
    static GameWindow window;

    public static void main(String[] args) {
        game = new Game();
        window = new GameWindow();
        window.addMouseListener(new MouseHandler(game, window));
        window.addKeyListener(new KeyHandler(game, window));
        window.setFocusable(true);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game of Life");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(window);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
