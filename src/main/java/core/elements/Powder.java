package core.elements;

import core.FallingSandSim;

import java.awt.*;

public abstract class Powder extends Element {
    public Powder(int x, int y, Color color, ElementProperties properties) {
        super(x, y, color, ElementState.POWDER, properties);
    }

    @Override
    public void step(FallingSandSim sim) {
        float chance = rand.nextFloat();

        Element down = sim.getElementAt(x, y + 1);

        int x1 = x - 1;
        int x2 = x + 1;

        if (rand.nextDouble() >= 0.5) {
            x1 = x + 1;
            x2 = x - 1;
        }

        Element diag1 = sim.getElementAt(x1, y + 1);
        Element diag2 = sim.getElementAt(x2, y + 1);

        if (down != null && (down.isEmpty() || down.getState() == ElementState.LIQUID || down.getState() == ElementState.GAS)) {
            sim.swap(x, y, down.getX(), down.getY());
        }
        else if (diag1 != null && (diag1.isEmpty() || diag1.getState() == ElementState.LIQUID || diag1.getState() == ElementState.GAS)) {
            sim.swap(x, y, diag1.getX(), diag1.getY());
        }
        else if (diag2 != null && (diag2.isEmpty() || diag2.getState() == ElementState.LIQUID || diag2.getState() == ElementState.GAS)) {
            sim.swap(x, y, diag2.getX(), diag2.getY());
        }
    }
}
