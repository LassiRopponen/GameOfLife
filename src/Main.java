import java.util.Arrays;
import java.util.Collection;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

import GameLogic.*;
import GUI.GameWindow;

class Main {
    public static void main(String[] args) {
        int generations = Integer.parseInt(args[0]);
        Game game = new Game(Arrays.copyOfRange(args, 1, args.length));
        Collection<Cell> result = game.iterate(generations);
        System.out.println(result);
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game of Life");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new GameWindow(result));
            frame.pack();
            frame.setVisible(true);
        });
    }    
}
