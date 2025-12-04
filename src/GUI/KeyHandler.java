package GUI;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Instant;
import java.time.Duration;

import GameLogic.Game;

public class KeyHandler extends KeyAdapter {
    private final static long PRESS_DELAY_MS = 200;
    private Game game;
    private GameWindow window;
    private Instant lastPress;

    public KeyHandler(Game game, GameWindow window) {
        this.game = game;
        this.window = window;
        this.lastPress = null;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (lastPress != null && 
                Duration.between(lastPress, Instant.now()).toMillis() < PRESS_DELAY_MS)
            {
                return;
            }
            game.game_loop();
            window.update_cells(game.get_cells());
            lastPress = Instant.now();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            lastPress = null;
        }
    }
}