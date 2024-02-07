package core.elements;

import java.awt.*;

public class Sand extends Powder {
    private static final Color SAND_COLOR = new Color(217, 196, 104);

    public Sand(int x, int y) {
        super(x, y, SAND_COLOR, ElementProperties.DEFAULT_PROPERTIES);
    }
}
