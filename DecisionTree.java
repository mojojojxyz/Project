/**
 * Manages the Binary Decision Tree used for career prediction.
 * Contains the root of the tree and provides methods for
 * dynamic learning (inserting new knowledge when a guess is wrong).
 */
public class DecisionTree {
    private Node root;

    /**
     * Constructs a DecisionTree with the given root node.
     *
     * @param root the root node of the decision tree
     */
    public DecisionTree(Node root) {
        this.root = root;
    }

    /**
     * Returns the root node of the tree.
     *
     * @return the root node
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Sets the root node of the tree.
     *
     * @param root the new root node
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * Mutates a leaf node into a question node, adding two child career leaves.
     * This is the core "learning" mechanism: when the Oracle guesses wrong,
     * the user provides the correct career and a distinguishing question.
     * The original leaf becomes an internal question node, with the new career
     * and the old career as its two children.
     *
     * Example: If the leaf was "Web Developer" and the user says the answer
     * is "Game Developer" with the question "Do you make interactive 3D experiences?"
     * and answers "yes" for the new career, then:
     *   - The leaf's data becomes the new question
     *   - yesNode = "Game Developer"
     *   - noNode  = "Web Developer"
     *
     * @param currentLeaf       the leaf node that was guessed incorrectly
     * @param newCareer          the correct career provided by the user
     * @param newQuestion        a distinguishing question between the old and new career
     * @param isYesAnswerForNew  true if answering "yes" to the question leads to the new career
     */
    public void insertNewKnowledge(Node currentLeaf, String newCareer, String newQuestion, boolean isYesAnswerForNew) {
        String oldCareer = currentLeaf.getData();
        currentLeaf.setData(newQuestion);

        if (isYesAnswerForNew) {
            currentLeaf.setYesNode(new Node(newCareer));
            currentLeaf.setNoNode(new Node(oldCareer));
        } else {
            currentLeaf.setYesNode(new Node(oldCareer));
            currentLeaf.setNoNode(new Node(newCareer));
        }
    }
}
