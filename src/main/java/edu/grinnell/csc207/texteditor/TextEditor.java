package edu.grinnell.csc207.texteditor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    /**
     * The main entry point for the TextEditor application.
     * @param args command-line arguments.
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        //String[] arg =  new String[1];
        //arg[0] = "randfile.txt";
        //args = arg;
        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            System.exit(1);
        }
        
        String path = args[0];
        System.out.format("Loading %s...\n", path);

        Path paths = Paths.get(path);
        if (!(Files.exists(paths))) {
            System.err.println("Please enter a filename <exampleName.txt>");
            System.exit(1);
        }
        if (!Files.isRegularFile(paths)) {
            System.err.println("Please enter a filename and not a directory");
            System.exit(1);
        }

        Screen screen = new DefaultTerminalFactory().createScreen();
        GapBuffer buffer = new GapBuffer();

        long size = Files.size(paths);
        String fileContent = "";

        screen.startScreen();

        if (size > 0) {
            fileContent = Files.readString(paths);
            for (int i = 0; i < size; i++) {
                buffer.insert(fileContent.charAt(i));
                screen.setCharacter(buffer.getCursorPosition(), 
                                    0,
                                    TextCharacter.fromCharacter(fileContent.charAt(i))[0]);
                drawBuffer(buffer, screen);
            }
        } else {
            boolean write = true;
        }



        boolean isRunning = true;
        while (isRunning) {
            KeyStroke key = screen.readInput();
            KeyType keyStroke = key.getKeyType();
            if (KeyType.Character.equals(keyStroke)) {
                buffer.insert(key.getCharacter());
                screen.setCharacter(buffer.getCursorPosition(), 
                                    0,
                                    TextCharacter.fromCharacter(key.getCharacter())[0]);
            } else if (KeyType.ArrowLeft.equals(keyStroke)) {
                buffer.moveLeft();
                
            } else if (KeyType.ArrowRight.equals(keyStroke)) {
                buffer.moveRight();
            } else if (KeyType.Backspace.equals(keyStroke)) {
                buffer.delete();
                screen.setCharacter(buffer.getCursorPosition() + 1, 
                                    0,
                                    TextCharacter.fromCharacter('\u0020')[0]); 
            } else if (KeyType.Escape.equals(keyStroke)) {
                Files.writeString(paths, buffer.toString());
                screen.stopScreen();
                System.exit(1);
            }

            drawBuffer(buffer, screen);
        }
    }


    static void drawBuffer(GapBuffer buf, Screen screen) throws IOException {
        screen.setCursorPosition(new TerminalPosition(buf.getCursorPosition() + 1, 0));
        screen.refresh();
    }
}
