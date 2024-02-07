package core.elements;

import core.FallingSandSim;

import java.awt.*;
import java.util.Random;

public abstract class Element {
    protected static Random rand = new Random();

    public enum ElementType {
        AIR,
        SAND,
        WATER,
        STONE,
        FORK_BOMB,
        MOLD,
        SMOKE,
        FIRE,
        WOOD,
        GUNPOWDER,
        HYDROGEN,
        ASH
    }

    public enum ElementState {
        SOLID,
        POWDER,
        LIQUID,
        GAS,
        OTHER
    }

    protected int x;
    protected int y;

    protected Color color;

    protected final ElementState state;

    protected final ElementProperties properties;

    public Element(int x, int y, Color color, ElementState state, ElementProperties properties) {
        this.x = x;
        this.y = y;

        this.color = color;

        this.state = state;

        this.properties = properties;
    }

    public void doElementStep(FallingSandSim sim) {
        step(sim);

        sim.setPositionCalculated(x, y, true);
    }

    protected abstract void step(FallingSandSim sim);

    public void tintColor(float tintFactor) {
        int r = (int)(color.getRed() + (255 - color.getRed()) * tintFactor);
        int g = (int)(color.getGreen() + (255 - color.getGreen()) * tintFactor);
        int b = (int)(color.getBlue() + (255 - color.getBlue()) * tintFactor);

        color = new Color(r, g, b, color.getAlpha());
    }

    public void shadeColor(float shadeFactor) {
        int r = (int)(color.getRed() * (1 - shadeFactor));
        int g = (int)(color.getGreen() * (1 - shadeFactor));
        int b = (int)(color.getBlue() * (1 - shadeFactor));

        color = new Color(r, g, b, color.getAlpha());
    }

    public void blendColor(Color c) {
        int r = c.getRed() * c.getAlpha() + color.getRed() * (1 - c.getAlpha());
        int g = c.getGreen() * c.getAlpha() + color.getGreen() * (1 - c.getAlpha());
        int b = c.getBlue() * c.getAlpha() + color.getBlue() * (1 - c.getAlpha());

        color = new Color(r, g, b, color.getAlpha());
    }

    // Returns positive if this element is denser than the other
    public int compareDensity(Element other) {
        return properties.getDensity() - other.getProperties().getDensity();
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public ElementState getState() { return state; }

    public ElementProperties getProperties() { return properties; }

    public boolean isEmpty() { return false; }
}
