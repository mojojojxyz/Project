/**
 * Manages the Binary Decision Tree for career prediction.
 */
public class DecisionTree {
    private Node root;

    /**
     * Constructs a DecisionTree with the given root node.
     * @param root the root node
     */
    public DecisionTree(Node root) {
        this.root = root;
    }

    /** @return the root node */
    public Node getRoot() { return root; }

    /** @param root the new root node */
    public void setRoot(Node root) { this.root = root; }
}
