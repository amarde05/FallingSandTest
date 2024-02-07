package core.elements;

import java.awt.*;

public class Ash  extends Powder {
    private static final Color ASH_COLOR = new Color(54, 54, 54);

    public Ash(int x, int y) {
        super(x, y, ASH_COLOR, ElementProperties.DEFAULT_PROPERTIES);
    }
}