/**
 * Custom stack implementation using a linked list to support the "undo" feature.
 * Allows the player to go back to the previous question during gameplay.
 *
 * This class is implemented from scratch without using java.util.Stack
 * to satisfy the project requirement of custom data structures.
 *
 * Operations: push(), pop(), peek(), isEmpty(), clear()
 */
public class UndoStack {
    private StackNode top;
    private int size;

    /**
     * Internal node class for the stack's linked list structure.
     * Each node holds a reference to a tree Node (the question the player was at).
     */
    private class StackNode {
        Node treeNode;
        StackNode next;

        /**
         * Constructs a StackNode wrapping the given tree node.
         *
         * @param treeNode the decision tree node to store
         */
        public StackNode(Node treeNode) {
            this.treeNode = treeNode;
            this.next = null;
        }
    }

    /**
     * Constructs an empty UndoStack.
     */
    public UndoStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * Pushes a tree node onto the top of the stack.
     * Called before traversing to a child node so the player can undo.
     *
     * @param node the tree node to save on the stack
     */
    public void push(Node node) {
        StackNode newNode = new StackNode(node);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /**
     * Removes and returns the tree node from the top of the stack.
     * Used when the player types "undo" to return to the previous question.
     *
     * @return the most recently pushed tree node, or null if the stack is empty
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
     * Returns the tree node at the top of the stack without removing it.
     *
     * @return the top tree node, or null if the stack is empty
     */
    public Node peek() {
        if (isEmpty()) {
            return null;
        }
        return top.treeNode;
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return true if the stack has no elements
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Returns the number of elements currently in the stack.
     *
     * @return the stack size
     */
    public int getSize() {
        return size;
    }

    /**
     * Removes all elements from the stack, resetting it to empty.
     * Called at the start of each new round.
     */
    public void clear() {
        top = null;
        size = 0;
    }
}
