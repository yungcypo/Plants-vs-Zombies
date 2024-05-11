package entity.strely;

import entity.rastliny.utociaceRastliny.strielajuceRastliny.StrielajucaRastlina;

import java.util.ArrayList;

public class Bubliny extends Strela {
    public Bubliny(int x, int y, ArrayList<Strela> strely, StrielajucaRastlina parent) {
        super(x, y, "strely/bubliny", 4, strely, parent);
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
