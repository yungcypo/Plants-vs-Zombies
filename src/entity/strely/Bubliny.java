package entity.strely;

import entity.rastliny.strielajuceRastliny.StrielajucaRastlina;

public class Bubliny extends Strela {
    public Bubliny(int x, int y, StrielajucaRastlina parent) {
        super(x, y, "strely/bubliny", 4, parent);
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
