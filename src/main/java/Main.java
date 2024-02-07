import core.FallingSandGame;

import java.awt.*;

public class Main {
    public static void main(String[] Args) {
        FallingSandGame window = new FallingSandGame(600, 530);

        window.init();

        window.update();
    }
}
