/**
 * Represents a single node in the Binary Decision Tree.
 * Each node holds either a Yes/No question (internal node) or
 * a career name (leaf node).
 *
 * Internal nodes have both yesNode and noNode children.
 * Leaf nodes have no children and represent a final career prediction.
 */
public class Node {
    private String data;
    private Node yesNode;
    private Node noNode;

    /**
     * Constructs a new Node with the given data.
     * Initially, the node has no children and is treated as a leaf.
     *
     * @param data the question text (for internal nodes) or career name (for leaf nodes)
     */
    public Node(String data) {
        this.data = data;
        this.yesNode = null;
        this.noNode = null;
    }

    /**
     * Returns the data stored in this node.
     *
     * @return the question or career name
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the data of this node.
     *
     * @param data the new question or career name
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Returns the child node reached by answering "Yes".
     *
     * @return the yes-branch child node, or null if this is a leaf
     */
    public Node getYesNode() {
        return yesNode;
    }

    /**
     * Sets the child node for the "Yes" branch.
     *
     * @param yesNode the node to set as the yes-child
     */
    public void setYesNode(Node yesNode) {
        this.yesNode = yesNode;
    }

    /**
     * Returns the child node reached by answering "No".
     *
     * @return the no-branch child node, or null if this is a leaf
     */
    public Node getNoNode() {
        return noNode;
    }

    /**
     * Sets the child node for the "No" branch.
     *
     * @param noNode the node to set as the no-child
     */
    public void setNoNode(Node noNode) {
        this.noNode = noNode;
    }

    /**
     * Determines whether this node is a leaf (career answer) node.
     * A leaf node has no children.
     *
     * @return true if this node has no children, false otherwise
     */
    public boolean isLeaf() {
        return yesNode == null && noNode == null;
    }
}
