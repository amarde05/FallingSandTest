package core.elements;

import java.awt.*;

public class Wood extends Solid {
    private static final Color WOOD_COLOR = new Color(128, 96, 43);

    public Wood(int x, int y) {
        super(x, y, WOOD_COLOR, ElementProperties.WOOD_PROPERTIES);

        shadeColor(rand.nextFloat() / 5.0f);
    }
}
