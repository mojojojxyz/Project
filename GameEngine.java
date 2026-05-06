import java.util.Scanner;

/**
 * Main game loop and user interaction controller for The Career Path Oracle.
 * Manages the question-and-answer flow, undo functionality, dynamic learning,
 * round tracking, path history, and end-of-session summary.
 *
 * Uses:
 *   - DecisionTree (Binary Decision Tree) for career prediction
 *   - UndoStack (Custom Stack) for undo during gameplay
 *   - LearningLog (Custom Linked List) for tracking new careers learned
 */
public class GameEngine {
    private DecisionTree tree;
    private Scanner scanner;

    /** Custom stack for undo feature */
    private UndoStack undoStack;

    /** Custom linked list for tracking newly learned careers */
    private LearningLog learningLog;

    /** Session statistics */
    private int correctGuesses;
    private int wrongGuesses;
    private int roundNumber;

    /**
     * Custom array-like structure to store path history for each round.
     * Each element is a string describing one round's traversal path.
     * Uses a plain array (not java.util.ArrayList) to stay within constraints.
     */
    private String[] pathHistory;
    private int pathCount;

    /**
     * Constructs a GameEngine with the given decision tree.
     *
     * @param tree the DecisionTree to use for career prediction
     */
    public GameEngine(DecisionTree tree) {
        this.tree = tree;
        this.scanner = new Scanner(System.in);
        this.undoStack = new UndoStack();
        this.learningLog = new LearningLog();
        this.correctGuesses = 0;
        this.wrongGuesses = 0;
        this.roundNumber = 0;
        this.pathHistory = new String[100]; // supports up to 100 rounds
        this.pathCount = 0;
    }

    /**
     * Starts the game by displaying the welcome screen and entering
     * the main game loop. After the user finishes playing, prints
     * the session summary and saves the tree to file.
     */
    public void start() {
        printWelcome();

        boolean playAgain = true;
        while (playAgain) {
            roundNumber++;
            System.out.println("\n--- Round " + roundNumber + " ---");
            playRound();

            System.out.print("\nDo you want to play again? (yes/no): ");
            String answer = scanner.nextLine().trim().toLowerCase();
            playAgain = answer.equals("yes") || answer.equals("y");
        }

        printSummary();
        saveAndExit();
    }

    /**
     * Prints the welcome banner for The Career Path Oracle.
     */
    private void printWelcome() {
        System.out.println("==================================================");
        System.out.println("  Welcome to The Career Path Oracle \uD83D\uDD2E");
        System.out.println("==================================================");
        System.out.println("Think of a tech career. I will try to guess it.");
        System.out.println("Answer with yes (y) / no (n) / undo (u)");
    }

    /**
     * Plays a single round of the guessing game.
     * Traverses the decision tree by asking Yes/No questions,
     * supports undo via the UndoStack, and handles correct/wrong guesses.
     * Records the traversal path for the session summary.
     */
    private void playRound() {
        Node current = tree.getRoot();
        undoStack.clear();

        // Build path for this round using a simple string builder approach
        String pathStr = "";
        int stepCount = 0;

        // Custom arrays to track path steps (for undo support)
        String[] pathSteps = new String[50];
        int pathStepCount = 0;

        while (!current.isLeaf()) {
            System.out.print(current.getData() + " (yes/no/undo): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("undo") || input.equals("u")) {
                // Undo: pop from stack to go back one step
                if (undoStack.isEmpty()) {
                    System.out.println("\u21A9 Nothing to undo. This is the first question.");
                } else {
                    current = undoStack.pop();
                    // Remove the last path step
                    if (pathStepCount > 0) {
                        pathStepCount--;
                    }
                    System.out.println("\u21A9 Undone! Back to previous question.");
                }
            } else if (input.equals("yes") || input.equals("y")) {
                undoStack.push(current);
                // Extract a short keyword from the question for path display
                String keyword = extractKeyword(current.getData());
                pathSteps[pathStepCount] = keyword + "\u2192yes";
                pathStepCount++;
                current = current.getYesNode();
            } else if (input.equals("no") || input.equals("n")) {
                undoStack.push(current);
                String keyword = extractKeyword(current.getData());
                pathSteps[pathStepCount] = keyword + "\u2192no";
                pathStepCount++;
                current = current.getNoNode();
            } else {
                System.out.println("Please answer with yes (y), no (n), or undo (u).");
            }
        }

        // Reached a leaf — make a guess
        System.out.print("\nIs the career you're thinking of [" + current.getData() + "]? (yes/no): ");
        String guessAnswer = scanner.nextLine().trim().toLowerCase();

        // Build the path string for summary
        pathStr = "";
        for (int i = 0; i < pathStepCount; i++) {
            if (i > 0) pathStr += ", ";
            pathStr += pathSteps[i];
        }

        if (guessAnswer.equals("yes") || guessAnswer.equals("y")) {
            System.out.println("\n\u2713 Correct! I guessed your career!");
            correctGuesses++;
            pathStr += "  \u2713 " + current.getData();
        } else {
            System.out.println("\n\u2717 I was wrong!");
            wrongGuesses++;

            // Learn the new career
            System.out.print("What career were you thinking of? ");
            String newCareer = scanner.nextLine().trim();

            System.out.print("What is a yes/no question that distinguishes [" + newCareer
                    + "] from [" + current.getData() + "]? ");
            String newQuestion = scanner.nextLine().trim();

            System.out.print("For [" + newCareer + "], is the answer to that question yes or no? ");
            String yesOrNo = scanner.nextLine().trim().toLowerCase();
            boolean isYesForNew = yesOrNo.equals("yes") || yesOrNo.equals("y");

            // Insert new knowledge into the tree
            tree.insertNewKnowledge(current, newCareer, newQuestion, isYesForNew);

            // Log to LearningLog
            learningLog.addEntry(newCareer, current.getData(), newQuestion, isYesForNew);

            System.out.println("\u2713 Thanks! I've learned about [" + newCareer + "].");
            pathStr += "  \u2717 " + current.getData() + " (learned: " + newCareer + ")";
        }

        // Store the path for this round
        pathHistory[pathCount] = "  Round " + roundNumber + ": " + pathStr;
        pathCount++;
    }

    /**
     * Extracts a short keyword from a question for the path summary display.
     * Takes the first meaningful word after common question starters.
     *
     * @param question the full question text
     * @return a short keyword representing the question
     */
    private String extractKeyword(String question) {
        // Remove common prefixes to get a shorter keyword
        String q = question.toLowerCase();

        // Try to extract a meaningful word from the question
        String[] skipWords = {"do", "you", "does", "it", "is", "are", "the", "a", "an",
                              "prefer", "enjoy", "have", "work", "with", "more", "than",
                              "writing", "working", "primarily", "focus", "on", "care",
                              "about", "of", "for", "that", "and", "or", "in"};

        String[] words = question.replaceAll("[^a-zA-Z/ ]", "").split("\\s+");

        for (String word : words) {
            String lower = word.toLowerCase();
            boolean isSkip = false;
            for (String skip : skipWords) {
                if (lower.equals(skip)) {
                    isSkip = true;
                    break;
                }
            }
            if (!isSkip && word.length() > 2) {
                // Capitalize first letter
                return word.substring(0, 1).toLowerCase() + word.substring(1);
            }
        }

        // Fallback: first 10 chars
        if (question.length() > 10) {
            return question.substring(0, 10);
        }
        return question;
    }

    /**
     * Prints the end-of-session summary including:
     * - Total rounds played
     * - Correct and wrong guess counts
     * - Path taken each round
     * - New careers learned (from LearningLog)
     */
    private void printSummary() {
        System.out.println("\n==================== SESSION SUMMARY ====================");
        System.out.println("Total rounds played : " + roundNumber);
        System.out.println("Correct guesses     : " + correctGuesses + " \u2713");
        System.out.println("Wrong guesses       : " + wrongGuesses + " \u2717");

        System.out.println("\nPath taken each round:");
        for (int i = 0; i < pathCount; i++) {
            System.out.println(pathHistory[i]);
        }

        System.out.println("\nNew knowledge learned today:");
        learningLog.printLog();

        System.out.println("=========================================================");
    }

    /**
     * Saves the current decision tree to careers.txt and prints
     * a farewell message.
     */
    private void saveAndExit() {
        FileManager.saveTree(tree.getRoot());
        System.out.println("Knowledge base saved to careers.txt \u2713");
        System.out.println("Thanks for playing! Goodbye.");
    }
}
