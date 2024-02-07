package core;

import core.elements.*;

import java.util.Random;

import static core.elements.Element.ElementType;

public class FallingSandSim {
    private int width;
    private int height;


    private Element[] matrix;
    private boolean[] calculatedCells;

    private Random rand;

    public FallingSandSim(int width, int height) {
        this.width = width;
        this.height = height;

        matrix = new Element[width * height];
        calculatedCells = new boolean[width * height];

        rand = new Random();

        clear();
    }

    public boolean isValidPos(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    private int getIndex(int x, int y) {
        if (isValidPos(x, y)) {
            return x + y * width;
        }
        else {
            return -1;
        }
    }

    public void setElementAt(Element e) {
        if (isValidPos(e.getX(), e.getY()))
            matrix[e.getX() + e.getY() * width] = e;
    }

    public Element getElementAt(int x, int y) {
        if (isValidPos(x, y))
            return matrix[x + y * width];
        else
            return null;
    }

    public void swap(int x1, int y1, int x2, int y2) {
        if (isValidPos(x1, y1) && isValidPos(x2, y2)) {
            getElementAt(x1, y1).setPosition(x2, y2);
            getElementAt(x2, y2).setPosition(x1, y1);

            Element temp = getElementAt(x1, y1);

            setElementAt(getElementAt(x2, y2));
            setElementAt(temp);
        }
    }

    public void forceMove(Element e, int toX, int toY) {
        if (isValidPos(toX, toY)) {
            int prevX = e.getX();
            int prevY = e.getY();

            e.setPosition(toX, toY);

            setElementAt(e);

            // If element was in a valid position, fill the empty space with air
            if (isValidPos(prevX, prevY)) {
                setElementAt(new Air(prevX, prevY));
            }
        }
    }

    private void clear() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Element e = getElementAt(x, y);

                if (e == null || !e.isEmpty())
                    setElementAt(new Air(x, y));
            }
        }
    }

    boolean down = true;

    public void step(int simStep) {
        for (int i = 0; i < height * width; i++) {
            calculatedCells[i] = false;
        }

        int sx = 0;
        int ex = width;
        int dx = 1;

        int sy = height - 1;
        int ey = -1;
        int dy = -1;

        if (!down) {
            sy = 0;
            ey = height;
            dy = 1;
        }

        // Snakes through the x's and switches y direction every step for cool effect
        for (int y = sy; y != ey; y += dy) {
            for (int x = sx; x != ex; x += dx) {
                if (calculatedCells[getIndex(x, y)])
                    continue;

                Element e = getElementAt(x, y);

                if (!e.isEmpty())
                    e.doElementStep(this);
            }

            if (sx == 0) {
                sx = width - 1;
                ex = -1;
                dx = -1;
            }
            else {
                sx = 0;
                ex = width;
                dx = 1;
            }
        }

        down = !down;
    }

    public void setPositionCalculated(int x, int y, boolean value) {
        if (isValidPos(x, y))
            calculatedCells[getIndex(x, y)] = value;
    }

    public Element getNewElementOfType(int x, int y, ElementType type) {
        Element newElement = null;

        switch (type) {
            case AIR -> newElement = new Air(x, y);
            case SAND -> newElement = new Sand(x, y);
            case WATER -> newElement = new Water(x, y);
            case STONE -> newElement = new Stone(x, y);
            case FORK_BOMB -> newElement = new ForkBomb(x, y);
            case MOLD -> newElement = new Mold(x, y, Mold.MAX_ENERGY);
            case SMOKE -> newElement = new Smoke(x, y);
            case FIRE -> newElement = new Fire(x, y, false);
            case WOOD -> newElement = new Wood(x, y);
            case ASH -> newElement = new Ash(x, y);
            case GUNPOWDER -> newElement = new Gunpowder(x, y);
            case HYDROGEN -> newElement = new Hydrogen(x, y);
        }

        return newElement;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
