/**
 * จุดเริ่มต้นของแอปพลิเคชัน The Career Path Oracle
 * โหลด Decision Tree จาก DataLoader ที่กำหนดไว้
 * จากนั้นเริ่มทำงานผ่าน GameEngine
 *
 * วิธีคอมไพล์และรัน:
 *   javac *.java
 *   java Main
 */
public class Main {

    /**
     * เมธอดหลัก — สร้าง Decision Tree และเริ่มเกม
     *
     * @param args อาร์กิวเมนต์จาก command-line (ไม่ได้ใช้งาน)
     */
    public static void main(String[] args) {
        DecisionTree careerTree = DataLoader.loadInitialTree();
        System.out.println("(Loaded default knowledge base with 20 careers)");

        // เริ่มเกม
        GameEngine game = new GameEngine(careerTree);
        game.start();
    }
}
