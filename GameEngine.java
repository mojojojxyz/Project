import java.util.Scanner;

/**
 * Career recommendation engine for The Career Path Oracle.
 * Guides the user through Yes/No questions, supports undo,
 * and displays a detailed career recommendation at the end.
 * Single round only, no learning/teaching feature.
 */
public class GameEngine {
    private DecisionTree tree;
    private Scanner scanner;
    private UndoStack undoStack;

    /** Track answers for summary */
    private String[] questions;
    private String[] answers;
    private int stepCount;

    /**
     * Constructs a GameEngine with the given decision tree.
     * @param tree the DecisionTree to use
     */
    public GameEngine(DecisionTree tree) {
        this.tree = tree;
        this.scanner = new Scanner(System.in);
        this.undoStack = new UndoStack();
        this.questions = new String[50];
        this.answers = new String[50];
        this.stepCount = 0;
    }

    /**
     * Starts the career recommendation flow.
     */
    public void start() {
        printWelcome();
        Node result = navigate();
        printResult(result);
        printSummary(result);
        saveAndExit();
    }

    /**
     * Prints the welcome banner.
     */
    private void printWelcome() {
        System.out.println("==================================================");
        System.out.println("  Welcome to The Career Path Oracle");
        System.out.println("==================================================");
        System.out.println("I will recommend a tech career path for you!");
        System.out.println("Answer a series of yes/no questions about your");
        System.out.println("interests and preferences.");
        System.out.println();
        System.out.println("  Controls:");
        System.out.println("    yes / y  = Yes");
        System.out.println("    no  / n  = No");
        System.out.println("    undo / u = Go back to previous question");
        System.out.println();
        System.out.println("  Each career is tagged as:");
        System.out.println("    [Uke]  = the gentle, supportive type <3");
        System.out.println("    [Seme] = the strong, leader type <3");
        System.out.println("==================================================");
        System.out.println();
    }

    /**
     * Navigates the decision tree by asking questions.
     * Supports undo via UndoStack.
     *
     * @return the leaf Node (career) reached
     */
    private Node navigate() {
        Node current = tree.getRoot();
        int questionNum = 1;

        while (!current.isLeaf()) {
            System.out.print("  Q" + questionNum + ". " + current.getData());
            System.out.print("\n  >>> ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("undo") || input.equals("u")) {
                if (undoStack.isEmpty()) {
                    System.out.println("  << Nothing to undo. This is the first question.\n");
                } else {
                    current = undoStack.pop();
                    if (stepCount > 0) stepCount--;
                    questionNum--;
                    System.out.println("  << Back to previous question.\n");
                }
            } else if (input.equals("yes") || input.equals("y")) {
                undoStack.push(current);
                questions[stepCount] = current.getData();
                answers[stepCount] = "yes";
                stepCount++;
                questionNum++;
                current = current.getYesNode();
                System.out.println();
            } else if (input.equals("no") || input.equals("n")) {
                undoStack.push(current);
                questions[stepCount] = current.getData();
                answers[stepCount] = "no";
                stepCount++;
                questionNum++;
                current = current.getNoNode();
                System.out.println();
            } else {
                System.out.println("  >> Please answer: yes (y) / no (n) / undo (u)\n");
            }
        }

        return current;
    }

    /**
     * Displays the career recommendation with full details.
     * @param career the career leaf node
     */
    private void printResult(Node career) {
        String type = career.getType();
        String typeLabel = "";
        if (type != null && !type.isEmpty()) {
            typeLabel = " [" + type + "]";
        }

        System.out.println();
        System.out.println("  ==================================================");
        System.out.println("   YOUR RECOMMENDED CAREER PATH");
        System.out.println("  ==================================================");
        System.out.println();
        System.out.println("   >> " + career.getData() + typeLabel);
        System.out.println();

        // Word-wrap the description at ~60 chars
        String desc = career.getDescription();
        if (desc != null && !desc.isEmpty()) {
            System.out.println("   About this career:");
            printWrapped(desc, 55, "     ");
        }

        System.out.println();
        System.out.println("  ==================================================");
    }

    /**
     * Prints text wrapped at the given width with a prefix on each line.
     * @param text   the text to wrap
     * @param width  max characters per line
     * @param prefix prefix for each line
     */
    private void printWrapped(String text, int width, String prefix) {
        String[] words = text.split(" ");
        String line = prefix;
        for (String word : words) {
            if (line.length() + word.length() + 1 > prefix.length() + width && line.length() > prefix.length()) {
                System.out.println(line);
                line = prefix;
            }
            if (line.length() > prefix.length()) {
                line += " ";
            }
            line += word;
        }
        if (line.length() > prefix.length()) {
            System.out.println(line);
        }
    }

    /**
     * Prints a summary of all answers given and the final result.
     * @param career the recommended career
     */
    private void printSummary(Node career) {
        System.out.println();
        System.out.println("  =================== SUMMARY ====================");
        System.out.println("  Questions answered: " + stepCount);
        System.out.println();
        System.out.println("  Your answers:");
        for (int i = 0; i < stepCount; i++) {
            String shortQ = shortenQuestion(questions[i]);
            String mark = answers[i].equals("yes") ? "[Y]" : "[N]";
            System.out.println("    " + (i + 1) + ". " + mark + " " + shortQ);
        }
        System.out.println();
        System.out.println("  Result: " + career.getData());
        if (career.getType() != null && !career.getType().isEmpty()) {
            System.out.println("  Type  : " + career.getType());
        }
        System.out.println("  ==================================================");
    }

    /**
     * Shortens a question for the summary display.
     * Removes "Do you" prefix and truncates if too long.
     * @param question the full question text
     * @return shortened version
     */
    private String shortenQuestion(String question) {
        String q = question;
        // Remove common prefixes
        if (q.startsWith("Do you ")) q = q.substring(7);
        else if (q.startsWith("Are you ")) q = q.substring(8);
        else if (q.startsWith("Does your ")) q = q.substring(10);
        // Capitalize first letter
        if (q.length() > 0) {
            q = q.substring(0, 1).toUpperCase() + q.substring(1);
        }
        // Truncate if too long
        if (q.length() > 70) {
            q = q.substring(0, 67) + "...";
        }
        return q;
    }

    /**
     * Prints farewell message.
     */
    private void saveAndExit() {
        System.out.println();
        System.out.println("  Thanks for using The Career Path Oracle! Goodbye.");
    }
}
