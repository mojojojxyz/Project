/**
 * จัดการ Binary Decision Tree สำหรับทำนายอาชีพ
 */
public class DecisionTree {
    private Node root;

    /**
     * สร้าง DecisionTree โดยระบุโหนดรากที่กำหนดให้
     * @param root โหนดราก
     */
    public DecisionTree(Node root) {
        this.root = root;
    }

    /** @return โหนดราก */
    public Node getRoot() { return root; }

    /** @param root โหนดรากใหม่ */
    public void setRoot(Node root) { this.root = root; }
}
