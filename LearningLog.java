/**
 * Custom singly Linked List to track new knowledge learned during a game session.
 * Each entry records a newly taught career, the distinguishing question,
 * and which answer (yes/no) leads to the new career.
 *
 * This class is implemented from scratch without using java.util.LinkedList
 * to satisfy the project requirement of custom data structures.
 */
public class LearningLog {
    private LogNode head;
    private LogNode tail;
    private int size;

    /**
     * Internal node class for the singly linked list.
     * Each node stores details about one learning event.
     */
    private class LogNode {
        String newCareer;
        String oldCareer;
        String question;
        boolean answeredYes;
        LogNode next;

        /**
         * Constructs a LogNode with full learning event details.
         *
         * @param newCareer   the career the user taught the Oracle
         * @param oldCareer   the career the Oracle incorrectly guessed
         * @param question    the distinguishing question between old and new
         * @param answeredYes true if "yes" leads to the new career
         */
        public LogNode(String newCareer, String oldCareer, String question, boolean answeredYes) {
            this.newCareer = newCareer;
            this.oldCareer = oldCareer;
            this.question = question;
            this.answeredYes = answeredYes;
            this.next = null;
        }
    }

    /**
     * Constructs an empty LearningLog.
     */
    public LearningLog() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Appends a new learning entry to the end of the linked list.
     *
     * @param newCareer   the career the user taught the Oracle
     * @param oldCareer   the career the Oracle incorrectly guessed
     * @param question    the distinguishing question between old and new
     * @param answeredYes true if "yes" leads to the new career
     */
    public void addEntry(String newCareer, String oldCareer, String question, boolean answeredYes) {
        LogNode newNode = new LogNode(newCareer, oldCareer, question, answeredYes);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Returns the number of entries in the learning log.
     *
     * @return the size of the log
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks if the learning log is empty.
     *
     * @return true if no entries have been recorded
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Prints all learning log entries in a formatted list.
     * Each entry shows the new career learned, the distinguishing question,
     * and which answer maps to which career.
     *
     * Format:
     *   [1] Game Developer  |  "Do you make interactive 3D experiences?"
     *                       |  yes \u2192 Game Developer,  no \u2192 AR/VR Developer
     */
    public void printLog() {
        if (isEmpty()) {
            System.out.println("  No new knowledge learned today.");
            return;
        }

        LogNode current = head;
        int index = 1;
        while (current != null) {
            String yesCareer = current.answeredYes ? current.newCareer : current.oldCareer;
            String noCareer  = current.answeredYes ? current.oldCareer : current.newCareer;

            System.out.println("  [" + index + "] " + current.newCareer
                    + "  |  \"" + current.question + "\"");
            System.out.println("      "
                    + "  |  yes \u2192 " + yesCareer
                    + ",  no \u2192 " + noCareer);

            current = current.next;
            index++;
        }
    }
}
