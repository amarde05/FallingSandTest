package core.elements;

import core.FallingSandSim;

import java.awt.*;

public class ForkBomb extends Element {
    private static final Color FORK_BOMB_COLOR = new Color(218, 20, 20);

    public ForkBomb(int x, int y) {
        super(x, y, FORK_BOMB_COLOR, ElementState.OTHER, ElementProperties.DEFAULT_PROPERTIES);
    }

    @Override
    public void step(FallingSandSim sim) {
        Element[] directions = {
                sim.getElementAt(x - 1, y - 1),
                sim.getElementAt(x, y - 1),
                sim.getElementAt(x + 1, y - 1),
                sim.getElementAt(x - 1, y),
                sim.getElementAt(x + 1, y),
                sim.getElementAt(x - 1, y + 1),
                sim.getElementAt(x, y + 1),
                sim.getElementAt(x + 1, y + 1),
        };

        int chosenDir = rand.nextInt(directions.length);

        if (directions[chosenDir] != null && directions[chosenDir].isEmpty()) {
            sim.setElementAt(new ForkBomb(directions[chosenDir].getX(), directions[chosenDir].getY()));
        }
    }
}
