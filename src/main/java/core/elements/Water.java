package core.elements;

import java.awt.*;

public class Water extends Liquid {
    private static final Color WATER_COLOR = new Color(41, 150, 227);

    public Water(int x, int y) {
        super(x, y, WATER_COLOR, ElementProperties.WATER_PROPERTIES);
    }
}
