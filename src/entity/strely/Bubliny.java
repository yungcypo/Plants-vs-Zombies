package entity.strely;

import java.util.ArrayList;

public class Bubliny extends Strela {
    public Bubliny(int x, int y, ArrayList<Strela> strely) {
        super(x, y, "strely/bubliny", 4, strely);
    }

    @Override
    public int getX2() {
        // TODO zmenit toto ked bude hotovy obrazok
        return this.getX() + 50;
    }

    @Override
    public int getY2() {
        // TODO zmenit toto ked bude hotovy obrazok
        return this.getY() + 50;
    }
}
