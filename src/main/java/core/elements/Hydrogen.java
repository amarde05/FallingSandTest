package core.elements;

import java.awt.*;

public class Hydrogen extends Gas {
    private static final Color HYDROGEN_COLOR = new Color(243, 185, 6);

    public Hydrogen(int x, int y) {
        super(x, y, HYDROGEN_COLOR, ElementProperties.HYDROGEN_PROPERTIES);
    }
}