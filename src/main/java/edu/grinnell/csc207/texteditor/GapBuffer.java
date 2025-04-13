package edu.grinnell.csc207.texteditor;

/**
 * A gap buffer-based implementation of a text buffer.
 */
public class GapBuffer {

    private char cursor;
    
    private int cursorStart;

    private int cursorEnd;

    private char[] buffer;

    /**
     * 
     */
    public GapBuffer() {
        cursor = '\u0020';
        cursorStart = 0;
        cursorEnd = 9;
        buffer = new char[10];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = ' ';
        }
        //buffer[0] = '\0'; //cursor;
    }

    /**
     * @param ch the character to be inserted within the String buffer.
     */
    public void insert(char ch) {
        if (cursorStart == cursorEnd) {
            char[] newBuffer = new char[buffer.length * 2];
            for (int i = 0; i < cursorStart; i++) {
                newBuffer[i] = buffer[i];
            }
            for (int i = cursorEnd; i < buffer.length; i++) {
                newBuffer[i + buffer.length] = buffer[i];
            }
            cursorEnd += buffer.length;
            buffer = newBuffer;
        }
        
        buffer[cursorStart++] = ch;
        buffer[cursorStart] = '\u0020';
    }

    /**
     *  removes the character to the left of the cursor from the buffer.
     */
    public void delete() {
        if (cursorStart > 0) {
            cursorStart--;
            buffer[cursorStart] = ' '; //cursor;
        }
    }

    /**
     * @return the cursors position as an index within the String buffer. 
     */
    public int getCursorPosition() {
        return cursorStart;
    }

    /**
     * moves cursor to the left of current position in buffer.
     */
    public void moveLeft() {
        if (cursorStart > 0) {
            cursorEnd--;
            cursorStart--;
            buffer[cursorEnd] = buffer[cursorStart];
            buffer[cursorStart] = ' ';
            //buffer[cursorStart] = cursor;
        }
    }

    /**
     * moves cursor to the right of current position in buffer.
     */
    public void moveRight() {
        if (cursorEnd < buffer.length - 1) {
            buffer[cursorStart] = buffer[cursorEnd];
            cursorStart++;
            buffer[cursorEnd] = ' ';
            cursorEnd++;
        }
    }

    /**
     * @return the number of elements inside the string buffer.
     */
    public int getSize() { // assuming size is meant to return the number of elements in the array
        return buffer.length - (cursorEnd - cursorStart) - 1;
    }

    /**
     * @param i the index of the character of interest.
     * @return the character at index i within the string buffer. 
     */
    public char getChar(int i) {
        if (i < 0 || i > buffer.length) {
            throw new IllegalArgumentException("index is outside the bounds of String");
        }
        return buffer[i];
    }

    /**
     * @return the contents of the String buffer
     */
    public String toString() {
        String ret = "";
        for (int i = 0; i < buffer.length - 1; i++) {
            if (i <= cursorStart || i >= cursorEnd) {
                ret += buffer[i];
            }
        }
        return ret;
    }
}
