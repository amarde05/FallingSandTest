package core.elements;

import core.FallingSandSim;

import java.awt.*;

public abstract class Liquid extends Element {
    private boolean left;

    private int bumps;

    private static final int MAX_BUMPS = 4;

    public Liquid(int x, int y, Color color, ElementProperties properties) {
        super(x, y, color, ElementState.LIQUID, properties);

        left = rand.nextBoolean();
    }

    @Override
    protected void step(FallingSandSim sim) {
        int[] possibleXs = {
                x + 1,
                x,
                x - 1
        };

        int chosenX = possibleXs[rand.nextInt(3)];

        Element down = sim.getElementAt(chosenX, y + 1);

        int x1 = left ? x - 1 : x + 1;

        Element horiz = sim.getElementAt(x1, y);

        if (down != null && (down.isEmpty() || (down.getState() == ElementState.LIQUID && compareDensity(down) > 0))) {
            sim.swap(x, y, down.getX(), down.getY());
        }
        else if (horiz != null && (horiz.isEmpty()
                || (horiz.getState() == ElementState.LIQUID && compareDensity(horiz) > 0))) {
            sim.swap(x, y, horiz.getX(), horiz.getY());
        }
        else if (horiz != null && horiz.getState() == ElementState.LIQUID) {
            bumps += 1;

            ((Liquid)horiz).setLeft(left);

            if (bumps == MAX_BUMPS) {
                bumps = 0;

                switchDirection();
            }
        }
        else {
            switchDirection();
        }
    }

    private void switchDirection() {
        left = !left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}
