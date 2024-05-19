package entity.rastliny.strielajuceRastliny;

import entity.strely.Strela;

public class HrachLadovy extends StrielajucaRastlina {
    /**
     * Konstruktor pre potomkov triedy
     *
     * @param x                    suranica x
     * @param y                    suradnica y
     */
    public HrachLadovy(int x, int y) {
        super(x, y, "hrachLadovy", 120, 8);
    }

    @Override
    public void tikAnimacie() {
        super.tikAnimacie(40);
    }

    @Override
    public Strela getNovaStrela() {
        return new entity.strely.HrachLadovy(this.getX() + 100, this.getY() + 20, this);
    }
}
