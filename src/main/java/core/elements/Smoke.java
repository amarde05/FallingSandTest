package core.elements;

import java.awt.*;

public class Smoke extends Gas {
    private static final Color SMOKE_COLOR = new Color(65, 62, 62);

    public Smoke(int x, int y) {
        super(x, y, SMOKE_COLOR, ElementProperties.SMOKE_PROPERTIES);
    }
}
