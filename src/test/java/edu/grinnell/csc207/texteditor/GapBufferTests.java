package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

public class GapBufferTests {
    
    @Test
    public void addingAndDeleting() {
        GapBuffer b = new GapBuffer();
        b.insert('h');
        b.insert('e');
        assertEquals("" + 'h' + 'e' + '\u0020', b.toString());
        b.moveLeft();
        b.insert('l');
        assertEquals("" + 'h' + 'l' + '\u0020' + 'e', b.toString());
        b.delete();
        assertEquals("" + 'h' + '\u0020' + 'e', b.toString());        
    } 

    @Test
    public void movingCursor() {
        GapBuffer b = new GapBuffer();
        b.insert('h');
        b.insert('e');
        b.insert('l');
        b.insert('l');
        b.insert('o');
        b.moveLeft();
        assertEquals("" + 'h' + 'e' + 'l' + 'l' + '\u0020' + 'o', b.toString());
        b.moveRight();
        assertEquals("" + 'h' + 'e' + 'l' + 'l' + 'o' + '\u0020', b.toString());
        assertEquals(5, b.getCursorPosition());
    }

    @Test
    public void getSizeAndChar() {
        GapBuffer b = new GapBuffer();
        assertEquals(0, b.getSize());
        b.insert('h');
        b.insert('e');
        b.insert('l');
        b.insert('l');
        b.insert('o');
        assertEquals(5, b.getSize());
        assertEquals('e', b.getChar(1));
    }

    @Test
    public void deletingEdgeCase() {
        GapBuffer b = new GapBuffer();
        b.delete();
        assertEquals("" + '\u0020', b.toString());
        b.insert('h');
        b.insert('e');
        b.delete();
        b.delete();
        b.delete();
        assertEquals("" + '\u0020', b.toString());   
    } 

    @Test
    public void movingCursorEdgeCase() {
        GapBuffer b = new GapBuffer();
        assertEquals(0, b.getCursorPosition());
        b.moveLeft();
        assertEquals("" + '\u0020', b.toString());
        b.insert('h');
        b.insert('e');
        b.insert('l');
        b.insert('l');
        b.insert('o');
        b.moveRight();
        assertEquals("" + 'h' + 'e' + 'l' + 'l' + 'o' + '\u0020', b.toString());
    }

    @Test
    public void insertEdgeCase() {
        GapBuffer b = new GapBuffer();
        b.insert('h');
        b.insert('e');
        b.insert('l');
        b.insert('l');
        b.insert('o');
        b.moveLeft();
        b.insert('l');
        assertEquals("" + 'h' + 'e' + 'l' + 'l' + 'l' + '\u0020' + 'o', b.toString());
    }

    @Test
    public void expandingEdgeCase() {
        GapBuffer b = new GapBuffer();
        for (int i = 0; i < 15; i++) {
            b.insert((char) i);
        }
        assertEquals("" + (char) 0 + (char) 1 + (char) 2 + (char) 3 + (char) 4 + (char) 5 + (char) 6 + 
                    (char) 7 + (char) 8 + (char) 9 + (char) 10 + (char) 11 + (char) 12 + (char) 13 + 
                    (char) 14 + '\u0020', b.toString());
    }

    @Property
    public boolean insertingManyChar(@ForAll @IntRange(min = 0, max = 20) int sz, @ForAll char ch) {
        GapBuffer b = new GapBuffer();
        for (int i = 0; i < sz; i++) {
            b.insert(ch);
        }
        return (sz == b.getSize());
    }
}
