/**
 * โครงสร้างข้อมูล Stack ที่สร้างเองโดยใช้ Linked List เพื่อรองรับฟีเจอร์ "ย้อนกลับ" (Undo)
 * ช่วยให้ผู้เล่นสามารถย้อนกลับไปคำถามก่อนหน้าได้ระหว่างเล่นเกม
 *
 * คลาสนี้สร้างขึ้นเองตั้งแต่ต้นโดยไม่ใช้ java.util.Stack
 * เพื่อให้ตรงตามข้อกำหนดของโปรเจกต์ที่ต้องสร้างโครงสร้างข้อมูลเอง
 *
 * การทำงานที่รองรับ: push(), pop(), peek(), isEmpty(), clear()
 */
public class UndoStack {
    private StackNode top;
    private int size;

    /**
     * คลาสโหนดภายในสำหรับโครงสร้าง Linked List ของ Stack
     * แต่ละโหนดเก็บ reference ไปยัง Node ของต้นไม้ (คำถามที่ผู้เล่นอยู่)
     */
    private class StackNode {
        Node treeNode;
        StackNode next;

        /**
         * สร้าง StackNode ที่ห่อหุ้มโหนดต้นไม้ที่กำหนด
         *
         * @param treeNode โหนดของ Decision Tree ที่จะเก็บ
         */
        public StackNode(Node treeNode) {
            this.treeNode = treeNode;
            this.next = null;
        }
    }

    /**
     * สร้าง UndoStack เปล่า
     */
    public UndoStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * เพิ่มโหนดต้นไม้เข้าไปที่ด้านบนสุดของ Stack
     * ถูกเรียกก่อนจะเดินไปยังโหนดลูก เพื่อให้ผู้เล่นสามารถย้อนกลับได้
     *
     * @param node โหนดต้นไม้ที่จะบันทึกลงใน Stack
     */
    public void push(Node node) {
        StackNode newNode = new StackNode(node);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /**
     * นำโหนดต้นไม้ออกจากด้านบนสุดของ Stack แล้วคืนค่ากลับมา
     * ถูกใช้เมื่อผู้เล่นพิมพ์ "undo" เพื่อกลับไปยังคำถามก่อนหน้า
     *
     * @return โหนดต้นไม้ที่ถูก push เข้ามาล่าสุด หรือ null ถ้า Stack ว่าง
     */
    public Node pop() {
        if (isEmpty()) {
            return null;
        }
        Node data = top.treeNode;
        top = top.next;
        size--;
        return data;
    }

    /**
     * คืนค่าโหนดต้นไม้ที่อยู่บนสุดของ Stack โดยไม่ลบออก
     *
     * @return โหนดต้นไม้ที่อยู่บนสุด หรือ null ถ้า Stack ว่าง
     */
    public Node peek() {
        if (isEmpty()) {
            return null;
        }
        return top.treeNode;
    }

    /**
     * ตรวจสอบว่า Stack ว่างหรือไม่
     *
     * @return true ถ้า Stack ไม่มีสมาชิก
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * คืนค่าจำนวนสมาชิกที่อยู่ใน Stack ในปัจจุบัน
     *
     * @return ขนาดของ Stack
     */
    public int getSize() {
        return size;
    }

    /**
     * ลบสมาชิกทั้งหมดออกจาก Stack ทำให้กลับเป็นสถานะว่าง
     * ถูกเรียกเมื่อเริ่มรอบใหม่
     */
    public void clear() {
        top = null;
        size = 0;
    }
}
