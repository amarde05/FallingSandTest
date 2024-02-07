package core.elements;

import java.awt.*;

public class Stone extends Solid {
    private static final Color STONE_COLOR = new Color(136, 136, 136);

    public Stone(int x, int y) {
        super(x, y, STONE_COLOR, ElementProperties.DEFAULT_PROPERTIES);

        shadeColor(rand.nextFloat() / 5.0f);
    }
}
