package core.elements;

import core.FallingSandSim;

import java.awt.*;

public class Air extends Element {
    private static final Color AIR_COLOR = new Color(100, 100, 100);

    public Air(int x, int y) {
        super(x, y, AIR_COLOR, ElementState.OTHER, ElementProperties.DEFAULT_PROPERTIES);
    }

    @Override
    public void step(FallingSandSim sim) {

    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
