import java.util.Arrays;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;

import GameLogic.*;
import GUI.GameWindow;

class Main {
    public static void main(String[] args) {
        int generations = Integer.parseInt(args[0]);
        Game game = new Game(Arrays.copyOfRange(args, 1, args.length));
        System.out.println(game.iterate(generations));
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game of Life");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new GameWindow(game.iterate(generations)));
            frame.pack();
            frame.setVisible(true);
        });
    }    
}
