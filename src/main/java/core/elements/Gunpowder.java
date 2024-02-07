package core.elements;

import java.awt.*;

public class Gunpowder extends Powder {
    private static final Color GUNPOWDER_COLOR = new Color(10, 10, 10);

    public Gunpowder(int x, int y) {
        super(x, y, GUNPOWDER_COLOR, ElementProperties.GUNPOWDER_PROPERTIES);
    }
}
