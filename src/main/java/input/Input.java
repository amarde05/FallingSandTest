package input;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class Input implements MouseListener, KeyListener {

    // Holds data for state of mouse buttons
    // 0 = left click, 1 = right click, 2 = middle click
    // 0000 = Nothing
    // 0001 = Pressed this frame
    // 0010 = Held
    // 0100 = Up
    // Last flag denotes that key was pressed after input was handled (so value will remain through the next frame)
    private char[] mouseButtons = {
        0b0000, 0b0000, 0b0000
    };

    // Holds data for state of keyboard keys
    private char[] keys = new char[600];

    private boolean inputHandled;

    private JFrame frame;

    public Input(JFrame frame) {
        this.frame = frame;

        frame.addMouseListener(this);
        frame.addKeyListener(this);

        inputHandled = false;
    }


    // MouseListener methods
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Set the appropriate value to 0011 (Held and pressed this frame)
        char val = 0b0011;

        if (inputHandled)
            val = 0b1011;

        switch (e.getButton()) {
            case MouseEvent.BUTTON1 -> mouseButtons[0] = val; // Left click
            case MouseEvent.BUTTON2 -> mouseButtons[2] = val; // Middle click
            case MouseEvent.BUTTON3 -> mouseButtons[1] = val; // Right click
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Set the appropriate value to 0100 (Released this frame)
        char val = 0b0100;

        if (inputHandled)
            val = 0b1100;

        switch (e.getButton()) {
            case MouseEvent.BUTTON1 -> mouseButtons[0] = val; // Left click
            case MouseEvent.BUTTON2 -> mouseButtons[2] = val; // Middle click
            case MouseEvent.BUTTON3 -> mouseButtons[1] = val; // Right click
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    // KeyListener methods
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Set the appropriate value to 0011 (Held and pressed this frame)
        char val = 0b0011;

        if ((keys[e.getKeyCode()] & 0b0010) > 0)
            val = 0b0010;
        else if (inputHandled)
            val = 0b1011;

        keys[e.getKeyCode()] = val;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Set the appropriate value to 0100 (Released this frame)
        char val = 0b0100;

        if (inputHandled)
            val = 0b1100;

        keys[e.getKeyCode()] = val;
    }


    public void endFrame() {
        // Reset the flags for pressed and released (held flag remains the same)
        for (int i = 0; i < 3; i++) {
            if ((mouseButtons[i] & 0b1000) == 0)
                mouseButtons[i] &= 0b0010;

            mouseButtons[i] &= 0b0111;
        }

        // Same for keys
        for (int i = 0; i < 0x7B; i++) {
            if ((keys[i] & 0b1000) == 0)
                keys[i] &= 0b0010;

            keys[i] &= 0b0111;
        }

        inputHandled = false;
    }

    public void setInputHandled() {
        inputHandled = true;
    }

    public boolean getMouseButtonDown(int buttonIndex) {
        return (mouseButtons[buttonIndex] & 0b0001) > 0;
    }

    public boolean getMouseButton(int buttonIndex) {
        return (mouseButtons[buttonIndex] & 0b0010) > 0;
    }

    public boolean getMouseButtonUp(int buttonIndex) {
        return (mouseButtons[buttonIndex] & 0b0100) > 0;
    }

    public Point getMousePosition() {
        return frame.getMousePosition();
    }

    public boolean getKeyDown(int keycode) {
        return (keys[keycode] & 0b0001) > 0;
    }

    public boolean getKey(int keycode) {
        return (keys[keycode] & 0b0010) > 0;
    }

    public boolean getKeyUp(int keycode) {
        return (keys[keycode] & 0b0100) > 0;
    }
}
