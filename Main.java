/**
 * Entry point for The Career Path Oracle application.
 * Loads the decision tree from the hardcoded DataLoader,
 * then starts the game engine.
 *
 * Compile and run:
 *   javac *.java
 *   java Main
 */
public class Main {

    /**
     * Main method — initializes the decision tree and starts the game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        DecisionTree careerTree = DataLoader.loadInitialTree();
        System.out.println("(Loaded default knowledge base with 20 careers)");

        // Start the game
        GameEngine game = new GameEngine(careerTree);
        game.start();
    }
}
