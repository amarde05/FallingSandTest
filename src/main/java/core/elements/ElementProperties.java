package core.elements;

public class ElementProperties {
    public static final ElementProperties DEFAULT_PROPERTIES = new ElementProperties();
    public static final ElementProperties WATER_PROPERTIES = new ElementProperties(0, false, 1);
    public static final ElementProperties SMOKE_PROPERTIES = new ElementProperties(0, false, 5);
    public static final ElementProperties WOOD_PROPERTIES = new ElementProperties(1, true, 0);
    public static final ElementProperties GUNPOWDER_PROPERTIES = new ElementProperties(8, false, 0);
    public static final ElementProperties HYDROGEN_PROPERTIES = new ElementProperties(10, false, 1);
    public static final ElementProperties MOLD_PROPERTIES = new ElementProperties(2, true, 0);

    private boolean producesSmoke;

    private float flammability;

    private int density;

    private ElementProperties() {
        flammability = 0;
        producesSmoke = false;
        density = 0;
    }

    private ElementProperties(float flammability, boolean producesSmoke, int density) {
        this.flammability = flammability;
        this.producesSmoke = producesSmoke;
        this.density = density;
    }

    public float getFlammability() { return flammability; }
    public ElementProperties setFlammability(float value) {
        flammability = value;
        return this;
    }

    public boolean getProducesSmoke() { return producesSmoke; }
    public ElementProperties setProducesSmoke(boolean value) {
        producesSmoke = value;
        return this;
    }

    public int getDensity() { return density; }
    public ElementProperties setDensity(int value) {
        density = value;
        return this;
    }

    public static ElementProperties create() {
        return new ElementProperties();
    }
}
