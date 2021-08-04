import game.Game;

/**
 * Classe GameLauncher qui est le point d'entrée du jeu
 * @author Jaouen MARIE
 * @version 1.0
 */
public class GameLauncher {

    private static Game game;

    public static void main(String[] args) {
        game = new Game();
    }
}