package core.elements;

import core.FallingSandSim;

import java.awt.*;

public class Mold extends Solid {
    private static final Color MOLD_COLOR = new Color(49, 126, 27);

    private static final double SPREAD_CHANCE = 0.33;

    public static final int MAX_ENERGY = 3;
    public static final int MAX_RESIDUAL_ENERGY = 1;

    private int energy;

    private int residualEnergy;

    public Mold(int x, int y, int energy) {
        super(x, y, MOLD_COLOR, ElementProperties.MOLD_PROPERTIES);

        this.energy = energy;

        this.residualEnergy = MAX_RESIDUAL_ENERGY;

        tintColor(0.25f * (1 - (float)energy / MAX_ENERGY));
    }

    @Override
    public void step(FallingSandSim sim) {
        if (energy > 0 && rand.nextDouble() <= SPREAD_CHANCE) {
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

            Element chosenDir = directions[rand.nextInt(directions.length)];

            if (chosenDir != null && !(chosenDir instanceof Mold)) {
                if (chosenDir.getState() == ElementState.SOLID) {
                    sim.setElementAt(new Mold(chosenDir.getX(), chosenDir.getY(), MAX_ENERGY));
                } else if (chosenDir.isEmpty() && residualEnergy > 0) {
                    sim.setElementAt(new Mold(chosenDir.getX(), chosenDir.getY(), energy - 1));
                    residualEnergy--;
                }
            }
        }
    }
}
