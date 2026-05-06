/**
 * Entry point for The Career Path Oracle application.
 * Loads the decision tree from file (if available) or from the
 * hardcoded DataLoader, then starts the game engine.
 *
 * Compile and run:
 *   javac *.java
 *   java Main
 */
public class Main {

    /**
     * Main method — initializes the decision tree and starts the game.
     * Attempts to load from careers.txt first; falls back to DataLoader
     * if no saved data exists.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        DecisionTree careerTree;

        // Try to load from saved file first
        if (FileManager.hasSavedData()) {
            careerTree = FileManager.loadTree();
            System.out.println("(Loaded knowledge base from careers.txt)");
        } else {
            careerTree = DataLoader.loadInitialTree();
            System.out.println("(Loaded default knowledge base with 30 careers)");
        }

        // Start the game
        GameEngine game = new GameEngine(careerTree);
        game.start();
    }
}
