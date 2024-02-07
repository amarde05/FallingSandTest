package core.elements;

import core.FallingSandSim;

import java.awt.*;

public class Fire extends Element {
    private static final Color FIRE_COLOR = new Color(248, 54, 5);

    private static final Color[] FIRE_COLORS = {
            new Color(248, 54, 5),
            new Color(248, 114, 5),
            new Color(248, 199, 5)
    };

    private static final int MAX_ENERGY = 35;

    private static final double SMOKE_CHANCE = 0.33;
    private static final double ASH_CHANCE = 0.1;

    private static final double SPREAD_CHANCE = 0.32;

    private int energy;

    private boolean produceSmoke;

    public Fire(int x, int y, boolean produceSmoke) {
        super(x, y, FIRE_COLORS[rand.nextInt(FIRE_COLORS.length)], ElementState.OTHER, ElementProperties.DEFAULT_PROPERTIES);

        this.energy = MAX_ENERGY;
        this.produceSmoke = produceSmoke;
    }

    @Override
    protected void step(FallingSandSim sim) {
        if (energy > 0) {
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

            if (chosenDir != null && rand.nextDouble() <= SPREAD_CHANCE * chosenDir.getProperties().getFlammability()
                && chosenDir.getProperties().getFlammability() > 0) {
                    sim.setElementAt(new Fire(chosenDir.getX(), chosenDir.getY(), chosenDir.getProperties().getProducesSmoke()));
            }

            decreaseEnergy();
            color = FIRE_COLORS[rand.nextInt(FIRE_COLORS.length)];

            if (produceSmoke && rand.nextDouble() <= SMOKE_CHANCE) {
                int[] possibleXs = {
                        x + 1,
                        x,
                        x - 1
                };

                int chosenX = possibleXs[rand.nextInt(3)];

                Element up = sim.getElementAt(chosenX, y-1);

                if (up != null && up.isEmpty())
                    sim.setElementAt(new Smoke(chosenX, y - 1));
            }
        }
        else {
            if (produceSmoke && rand.nextDouble() <= ASH_CHANCE)
                sim.setElementAt(new Ash(x, y));
            else
                sim.setElementAt(new Air(x, y));
        }
    }

    private void decreaseEnergy() {
        //shadeColor(.05f);
        energy--;
    }
}
