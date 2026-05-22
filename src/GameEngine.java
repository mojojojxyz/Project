import java.util.Scanner;

/**
 * เครื่องมือแนะนำอาชีพสำหรับ The Career Path Oracle
 * นำทางผู้ใช้ผ่านคำถาม Yes/No รองรับการย้อนกลับ (Undo)
 * และแสดงรายละเอียดอาชีพที่แนะนำเมื่อจบ
 * เล่นได้รอบเดียว ไม่มีฟีเจอร์เรียนรู้/สอน
 */
public class GameEngine {
    private DecisionTree tree;
    private Scanner scanner;
    private UndoStack undoStack;

    /** เก็บคำตอบเพื่อสรุปผล */
    private String[] questions;
    private String[] answers;
    private int stepCount;

    /**
     * สร้าง GameEngine ด้วย Decision Tree ที่กำหนด
     * @param tree DecisionTree ที่จะใช้งาน
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
     * เริ่มกระบวนการแนะนำอาชีพ
     */
    public void start() {
        printWelcome();
        Node result = navigate();
        printResult(result);
        printSummary(result);
        saveAndExit();
    }

    /**
     * แสดงข้อความต้อนรับ
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
     * นำทางใน Decision Tree โดยถามคำถามผู้ใช้
     * รองรับการย้อนกลับผ่าน UndoStack
     *
     * @return โหนดใบ (อาชีพ) ที่ไปถึง
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
     * แสดงผลอาชีพที่แนะนำพร้อมรายละเอียดทั้งหมด
     * @param career โหนดใบของอาชีพ
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

        // ตัดคำอธิบายให้พอดีบรรทัดที่ ~60 ตัวอักษร
        String desc = career.getDescription();
        if (desc != null && !desc.isEmpty()) {
            System.out.println("   About this career:");
            printWrapped(desc, 55, "     ");
        }

        System.out.println();
        System.out.println("  ==================================================");
    }

    /**
     * แสดงข้อความโดยตัดบรรทัดตามความกว้างที่กำหนด พร้อมเติมคำนำหน้าในแต่ละบรรทัด
     * @param text   ข้อความที่จะตัดบรรทัด
     * @param width  จำนวนตัวอักษรสูงสุดต่อบรรทัด
     * @param prefix คำนำหน้าในแต่ละบรรทัด
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
     * แสดงสรุปคำตอบทั้งหมดที่ตอบไปและผลลัพธ์สุดท้าย
     * @param career อาชีพที่แนะนำ
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
     * ย่อคำถามสำหรับแสดงในส่วนสรุป
     * ตัดคำนำหน้า "Do you" ออก และตัดให้สั้นลงถ้ายาวเกินไป
     * @param question ข้อความคำถามเต็ม
     * @return คำถามที่ถูกย่อแล้ว
     */
    private String shortenQuestion(String question) {
        String q = question;
        // ตัดคำนำหน้าที่พบบ่อยออก
        if (q.startsWith("Do you ")) q = q.substring(7);
        else if (q.startsWith("Are you ")) q = q.substring(8);
        else if (q.startsWith("Does your ")) q = q.substring(10);
        // แปลงตัวอักษรแรกเป็นตัวพิมพ์ใหญ่
        if (q.length() > 0) {
            q = q.substring(0, 1).toUpperCase() + q.substring(1);
        }
        // ตัดให้สั้นลงถ้ายาวเกินไป
        if (q.length() > 70) {
            q = q.substring(0, 67) + "...";
        }
        return q;
    }

    /**
     * แสดงข้อความอำลา
     */
    private void saveAndExit() {
        System.out.println();
        System.out.println("  Thanks for using The Career Path Oracle! Goodbye.");
    }
}
