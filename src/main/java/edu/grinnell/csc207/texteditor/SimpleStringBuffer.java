package edu.grinnell.csc207.texteditor;

import java.lang.String;

/**
 * A naive implementation of a text buffer using a <code>String</code>.
 */
public class SimpleStringBuffer {
    
    private int size;

    private String cursor;
    
    private int cursorPos;

    private String buffer;


    public SimpleStringBuffer() {
        size = 0;
        cursor = Character.toString('\u25AE');
        cursorPos = 0;
        buffer = "" + cursor;
    }

    /**
     * @param ch the character to be inserted within the String buffer.
     */
    public void insert(char ch) {
        String front;
        String back;
        String cha = Character.toString(ch);
        front = buffer.substring(0, cursorPos);
        back = buffer.substring(cursorPos + 1);
        buffer = front.concat(cha).concat(cursor).concat(back);
        cursorPos++;
        size++;
    }

    /**
     *  removes the character to the left of the cursor from the buffer.
     */
    public void delete() {
        if (this.size > 0) {
            String front;
            String back;
            front = buffer.substring(0, cursorPos - 1);
            back = buffer.substring(cursorPos + 1);
            buffer = front.concat(cursor).concat(back);
            cursorPos--;
            size--;
        }
    }

    /**
     * 
     * @return the cursors position as an index within the String buffer. 
     */
    public int getCursorPosition() {return cursorPos;}

    /**
     * moves cursor to the left of current position in buffer.
     */
    public void moveLeft() {
        if (this.cursorPos > 0) {
            String front;
            String movingCh;
            String back;
            front = buffer.substring(0, cursorPos - 1); // Grabs the characters before the one swapping pos with cursor.
            movingCh =  buffer.substring(cursorPos - 1, cursorPos); // Grabs the character swapping pos with cursor
            back =  buffer.substring(cursorPos + 1); // Grabs the remaining chararcters after the cursor.
            buffer = front.concat(cursor).concat(movingCh).concat(back);
            cursorPos--;
        }
    }

    /**
     * moves cursor to the right of current position in buffer.
     */
    public void moveRight() {  
        if (this.cursorPos < this.size) {
            String front;
            String movingCh;
            String back;
            front = buffer.substring(0, cursorPos); // Grabs the characters before the cursor.
            movingCh =  buffer.substring(cursorPos + 1, cursorPos + 2); // Grabs the character swapping pos with cursor
            back =  buffer.substring(cursorPos + 2); // Grabs the remaining chararcters after the cursor.
            buffer = front.concat(movingCh).concat(cursor).concat(back);
            cursorPos++;
        }
    }

    /**
     * 
     * @return the number of elements inside the string buffer.
     */
    public int getSize() {return size;}

    /**
     * @param i the index of the character of interest.
     * @return the character at index i within the string buffer. 
     */
    public char getChar(int i) {
        if (i < 0 || i > size) {
            throw new IllegalArgumentException("index is outside the bounds of String");
        }
        return buffer.charAt(i);
    }

    /**
     * prints the contents of the String buffer/
     */
    @Override
    public String toString() {return buffer;}
}
