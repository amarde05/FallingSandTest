package core.elements;

import core.FallingSandSim;

import java.awt.*;

public abstract class Solid extends Element {
    public Solid(int x, int y, Color color, ElementProperties properties) {
        super(x, y, color, ElementState.SOLID, properties);
    }

    @Override
    public void step(FallingSandSim sim) {
        // Does nothing
    }
}
