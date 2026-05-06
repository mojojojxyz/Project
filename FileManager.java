import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles saving and loading the Decision Tree to and from a text file.
 * Uses pre-order traversal for serialization with "Q:" prefix for question
 * nodes and "A:" prefix for career (answer/leaf) nodes.
 *
 * File format example:
 *   Q: Do you prefer front-end?
 *   A: Frontend Developer
 *   A: Back-End Developer
 */
public class FileManager {

    /** The filename used for persistent storage of the career tree. */
    private static final String FILE_NAME = "careers.txt";

    /**
     * Checks whether a saved data file exists in the working directory.
     *
     * @return true if careers.txt exists and is readable, false otherwise
     */
    public static boolean hasSavedData() {
        File file = new File(FILE_NAME);
        return file.exists() && file.length() > 0;
    }

    /**
     * Saves the entire decision tree to careers.txt using pre-order traversal.
     * Question (internal) nodes are prefixed with "Q: " and career (leaf)
     * nodes are prefixed with "A: ".
     *
     * @param root the root node of the tree to save
     */
    public static void saveTree(Node root) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            writePreOrder(root, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tree: " + e.getMessage());
        }
    }

    /**
     * Recursively writes a node and its children in pre-order traversal.
     * Internal nodes are written as "Q: <question>", leaf nodes as "A: <career>".
     *
     * @param node   the current node to write
     * @param writer the FileWriter to write to
     * @throws IOException if an I/O error occurs during writing
     */
    private static void writePreOrder(Node node, FileWriter writer) throws IOException {
        if (node == null) {
            return;
        }

        if (node.isLeaf()) {
            writer.write("A: " + node.getData() + "\n");
        } else {
            writer.write("Q: " + node.getData() + "\n");
            writePreOrder(node.getYesNode(), writer);
            writePreOrder(node.getNoNode(), writer);
        }
    }

    /**
     * Loads a decision tree from careers.txt by reconstructing it from
     * the pre-order traversal format.
     *
     * @return a DecisionTree reconstructed from the file, or null if loading fails
     */
    public static DecisionTree loadTree() {
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME));
            Node root = readPreOrder(scanner);
            scanner.close();
            return new DecisionTree(root);
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tree: " + e.getMessage());
            return null;
        }
    }

    /**
     * Recursively reads nodes from the scanner in pre-order format.
     * Lines starting with "Q: " become internal (question) nodes,
     * lines starting with "A: " become leaf (career) nodes.
     *
     * @param scanner the Scanner reading from the data file
     * @return the reconstructed Node subtree
     */
    private static Node readPreOrder(Scanner scanner) {
        if (!scanner.hasNextLine()) {
            return null;
        }

        String line = scanner.nextLine();

        if (line.startsWith("A: ")) {
            // Leaf node — career answer
            return new Node(line.substring(3));
        } else if (line.startsWith("Q: ")) {
            // Internal node — question
            Node node = new Node(line.substring(3));
            node.setYesNode(readPreOrder(scanner));
            node.setNoNode(readPreOrder(scanner));
            return node;
        }

        return null;
    }
}
