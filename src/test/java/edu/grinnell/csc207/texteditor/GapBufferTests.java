package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

public class GapBufferTests {
    
    @Test
    public void addingAndDeleting() {
        SimpleStringBuffer b = new SimpleStringBuffer();
        b.insert('h');
        b.insert('e');
        assertEquals("" + 'h' + 'e' + '\u25AE', b.toString());
        b.moveLeft();
        b.insert('l');
        assertEquals("" + 'h' + 'l' + '\u25AE' + 'e', b.toString());
        b.delete();
        assertEquals("" + 'h' + '\u25AE' + 'e', b.toString());        
    } 

    @Test
    public void movingCursor() {
        SimpleStringBuffer b = new SimpleStringBuffer();
        b.insert('h');
        b.insert('e');
        b.insert('l');
        b.insert('l');
        b.insert('o');
        b.moveLeft();
        assertEquals("" + 'h' + 'e' + 'l' + 'l' + '\u25AE' + 'o', b.toString());
        b.moveRight();
        assertEquals("" + 'h' + 'e' + 'l' + 'l' + 'o' + '\u25AE', b.toString());
        assertEquals(5, b.getCursorPosition());
    }

    @Test
    public void getSizeAndChar() {
        SimpleStringBuffer b = new SimpleStringBuffer();
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
        SimpleStringBuffer b = new SimpleStringBuffer();
        b.delete();
        assertEquals("" + '\u25AE', b.toString());
        b.insert('h');
        b.insert('e');
        b.delete();
        b.delete();
        b.delete();
        assertEquals("" + '\u25AE', b.toString());   
    } 

    @Test
    public void movingCursorEdgeCase() {
        SimpleStringBuffer b = new SimpleStringBuffer();
        assertEquals(0, b.getCursorPosition());
        b.moveLeft();
        assertEquals("" + '\u25AE', b.toString());
        b.insert('h');
        b.insert('e');
        b.insert('l');
        b.insert('l');
        b.insert('o');
        b.moveRight();
        assertEquals("" + 'h' + 'e' + 'l' + 'l' + 'o' + '\u25AE', b.toString());
    }

    @Test
    public void expandingEdgeCase() {
        SimpleStringBuffer b = new SimpleStringBuffer();
        for (int i = 0; i < 15; i++) {
            b.insert((char) i);
        }
        assertEquals("" + (char) 0 + (char) 1 + (char) 2 + (char) 3 + (char) 4 + (char) 5 + (char) 6 + 
                    (char) 7 + (char) 8 + (char) 9 + (char) 10 + (char) 11 + (char) 12 + (char) 13 + 
                    (char) 14 + '\u25AE', b.toString());
    }

    @Property
    public boolean insertingManyChar(@ForAll @IntRange(min = 0, max = 20) int sz, @ForAll char ch) {
        SimpleStringBuffer b = new SimpleStringBuffer();
        for (int i = 0; i < sz; i++) {
            b.insert(ch);
        }
        return (sz == b.getSize());
    }
}
