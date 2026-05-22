/**
 * แทนโหนดหนึ่งตัวใน Binary Decision Tree
 * โหนดภายใน (Internal Node) เก็บคำถาม Yes/No
 * โหนดใบ (Leaf Node) เก็บชื่ออาชีพ, คำอธิบาย, และประเภท (เคะ/เมะ)
 */
public class Node {
    private String data;
    private Node yesNode;
    private Node noNode;
    private String description;
    private String type; // "เคะ" หรือ "เมะ" สำหรับโหนดใบ

    /**
     * สร้างโหนดคำถาม (โหนดภายใน) โดยระบุแค่ข้อมูลคำถาม
     *
     * @param data ข้อความคำถาม
     */
    public Node(String data) {
        this.data = data;
        this.yesNode = null;
        this.noNode = null;
        this.description = "";
        this.type = "";
    }

    /**
     * สร้างโหนดอาชีพ (โหนดใบ) พร้อมข้อมูล, คำอธิบาย, และประเภท
     *
     * @param data        ชื่ออาชีพ
     * @param description คำอธิบายสั้นๆ ของอาชีพ
     * @param type        "เคะ" หรือ "เมะ"
     */
    public Node(String data, String description, String type) {
        this.data = data;
        this.yesNode = null;
        this.noNode = null;
        this.description = description;
        this.type = type;
    }

    /** @return คำถามหรือชื่ออาชีพ */
    public String getData() { return data; }

    /** @param data คำถามหรือชื่ออาชีพใหม่ */
    public void setData(String data) { this.data = data; }

    /** @return โหนดลูกฝั่ง Yes */
    public Node getYesNode() { return yesNode; }

    /** @param yesNode โหนดที่จะตั้งเป็นลูกฝั่ง Yes */
    public void setYesNode(Node yesNode) { this.yesNode = yesNode; }

    /** @return โหนดลูกฝั่ง No */
    public Node getNoNode() { return noNode; }

    /** @param noNode โหนดที่จะตั้งเป็นลูกฝั่ง No */
    public void setNoNode(Node noNode) { this.noNode = noNode; }

    /** @return คำอธิบายอาชีพ (เฉพาะโหนดใบ) */
    public String getDescription() { return description; }

    /** @param description คำอธิบายอาชีพ */
    public void setDescription(String description) { this.description = description; }

    /** @return "เคะ" หรือ "เมะ" (เฉพาะโหนดใบ) */
    public String getType() { return type; }

    /** @param type "เคะ" หรือ "เมะ" */
    public void setType(String type) { this.type = type; }

    /**
     * โหนดใบคือโหนดที่ไม่มีลูก และเป็นคำตอบ (อาชีพ)
     *
     * @return true ถ้าโหนดนี้ไม่มีลูก
     */
    public boolean isLeaf() {
        return yesNode == null && noNode == null;
    }
}
