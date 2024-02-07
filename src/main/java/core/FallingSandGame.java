package core;

import core.elements.*;
import input.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.Random;

import static core.elements.Element.ElementType;

public class FallingSandGame extends JFrame {
    private static final int GRID_SIZE = 2;
    private static final int STEP_TIME = 32; // Step time in milliseconds
    private static final int WINDOW_HEIGHT_PADDING = 39;
    private static final int WINDOW_WIDTH_PADDING = 8;

    private static final int BRUSH_SPEED = 1;


    private FallingSandSim fallingSandSim;
    private ElementType selectedElementType = ElementType.SAND;
    private int brushSize = 7;

    private Random rand;

    private Input input;

    private int texWidth;
    private int texHeight;

    private BufferedImage simImage;
    private JLabel simImageLabel;

    private BufferedImage overlayImage;
    private JLabel overlayImageLabel;

    public FallingSandGame(int width, int height) {
        super();

        this.rand = new Random();

        this.fallingSandSim = new FallingSandSim(width / GRID_SIZE, height / GRID_SIZE);


        this.texWidth = width;
        this.texHeight = height;

        // Simulation Image
        simImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        simImageLabel = new JLabel();
        simImageLabel.setBounds(0, 0, width, height);
        this.add(simImageLabel);

        // Overlay Image (for outlines, etc.)
        overlayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        overlayImageLabel = new JLabel();
        overlayImageLabel.setBounds(0, 0, width, height);
        this.add(overlayImageLabel);


        input = new Input(this);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width + WINDOW_WIDTH_PADDING * 2, height + WINDOW_HEIGHT_PADDING); // Windows is weird
        this.setLayout(null);
        this.setVisible(true);
    }

    public void init() {

    }

    public void update() {
        int simStep = 0;

        try {
            while (true) {
                fallingSandSim.step(simStep);

                handleInput(simStep);
                input.setInputHandled();

                draw();

                input.endFrame();

                simStep++;

                Thread.sleep(STEP_TIME);
            }
        }
        catch (InterruptedException e) {
            System.err.println(e.getStackTrace());
        }
    }

    public void drawCircle(int centerX, int centerY, int diameter, double chance, Element.ElementType type) {
        int r = diameter / 2;
        int rr = r * r;

        for (int dx = -r; dx <= r; dx++) {
            int x = centerX + dx;
            int dy = (int)Math.round(Math.sqrt(rr - dx * dx));
            for (int y = centerY - dy; y <= centerY + dy; y++) {
                if (rand.nextDouble() <= chance)
                    fallingSandSim.setElementAt(fallingSandSim.getNewElementOfType(x, y, type));
            }
        }
    }

    public int getGridX(int pixelX) {
        return (pixelX - WINDOW_WIDTH_PADDING) / GRID_SIZE;
    }

    public int getGridY(int pixelY) {
        return (pixelY - 31) / GRID_SIZE;
    }

    private void handleInput(int simStep) {
        if (input.getMouseButton(0) && simStep % BRUSH_SPEED == 0) {
            Point mousePos = input.getMousePosition();

            if (mousePos != null) {

                double chance = 0.66;

                if (selectedElementType == ElementType.AIR || selectedElementType == ElementType.STONE || selectedElementType == ElementType.WOOD) {
                    chance = 1.0;
                }

                drawCircle(getGridX(mousePos.x), getGridY(mousePos.y), brushSize, chance, selectedElementType);
            }
        }

        if (input.getKeyDown(KeyEvent.VK_1)) {
            selectedElementType = ElementType.SAND;
        }
        else if (input.getKeyDown(KeyEvent.VK_2)) {
            selectedElementType = ElementType.WATER;
        }
        else if (input.getKeyDown(KeyEvent.VK_3)) {
            selectedElementType = ElementType.STONE;
        }
        else if (input.getKeyDown(KeyEvent.VK_4)) {
            selectedElementType = ElementType.AIR;
        }
        else if (input.getKeyDown(KeyEvent.VK_5)) {
            selectedElementType = ElementType.FIRE;
        }
        else if (input.getKeyDown(KeyEvent.VK_6)) {
            selectedElementType = ElementType.WOOD;
        }
        else if (input.getKeyDown(KeyEvent.VK_7)) {
            selectedElementType = ElementType.GUNPOWDER;
        }
        else if (input.getKeyDown(KeyEvent.VK_8)) {
            selectedElementType = ElementType.HYDROGEN;
        }
        else if (input.getKeyDown(KeyEvent.VK_9)) {
            selectedElementType = ElementType.SMOKE;
        }
        else if (input.getKeyDown(KeyEvent.VK_0)) {
            selectedElementType = ElementType.MOLD;
        }
        else if (input.getKeyDown(KeyEvent.VK_EQUALS)) {
            brushSize++;
        }
        else if (input.getKeyDown(KeyEvent.VK_MINUS)) {
            brushSize--;

            if (brushSize < 1)
                brushSize = 1;
        }
    }

    private void draw() {
        Graphics2D g = simImage.createGraphics();

        for (int y = 0; y < fallingSandSim.getHeight(); y++) {
            for (int x = 0; x < fallingSandSim.getWidth(); x++) {
                g.setColor(fallingSandSim.getElementAt(x, y).getColor());
                g.fillRect(x * GRID_SIZE, y * GRID_SIZE, GRID_SIZE, GRID_SIZE);
            }
        }

        g.dispose();

        simImageLabel.setIcon(new ImageIcon(simImage));
        simImageLabel.repaint();
    }
}
