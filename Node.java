/**
 * Represents a single node in the Binary Decision Tree.
 * Internal nodes hold Yes/No questions; leaf nodes hold a career name,
 * description, and type (เคะ/เมะ).
 */
public class Node {
    private String data;
    private Node yesNode;
    private Node noNode;
    private String description;
    private String type; // "เคะ" or "เมะ" for leaf nodes

    /**
     * Constructs a question (internal) node with only data.
     *
     * @param data the question text
     */
    public Node(String data) {
        this.data = data;
        this.yesNode = null;
        this.noNode = null;
        this.description = "";
        this.type = "";
    }

    /**
     * Constructs a career (leaf) node with data, description, and type.
     *
     * @param data        the career name
     * @param description short description of the career
     * @param type        "เคะ" or "เมะ"
     */
    public Node(String data, String description, String type) {
        this.data = data;
        this.yesNode = null;
        this.noNode = null;
        this.description = description;
        this.type = type;
    }

    /** @return the question or career name */
    public String getData() { return data; }

    /** @param data the new question or career name */
    public void setData(String data) { this.data = data; }

    /** @return the yes-branch child node */
    public Node getYesNode() { return yesNode; }

    /** @param yesNode the node to set as the yes-child */
    public void setYesNode(Node yesNode) { this.yesNode = yesNode; }

    /** @return the no-branch child node */
    public Node getNoNode() { return noNode; }

    /** @param noNode the node to set as the no-child */
    public void setNoNode(Node noNode) { this.noNode = noNode; }

    /** @return career description (leaf nodes only) */
    public String getDescription() { return description; }

    /** @param description the career description */
    public void setDescription(String description) { this.description = description; }

    /** @return "เคะ" or "เมะ" (leaf nodes only) */
    public String getType() { return type; }

    /** @param type "เคะ" or "เมะ" */
    public void setType(String type) { this.type = type; }

    /**
     * A leaf node has no children and represents a career answer.
     *
     * @return true if this node has no children
     */
    public boolean isLeaf() {
        return yesNode == null && noNode == null;
    }
}
