import java.util.Arrays;
import GameLogic.*;

class Main {
    public static void main(String[] args) {
        int generations = Integer.parseInt(args[0]);
        Game game = new Game(Arrays.copyOfRange(args, 1, args.length));
        System.out.println(game.iterate(generations));
    }    
}
