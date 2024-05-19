package entity.strely;

import entity.rastliny.strielajuceRastliny.StrielajucaRastlina;

public class HrachLadovy extends Strela {


    /**
     * Konstruktor pre potomkov triedy
     *
     * @param x      suradnica x
     * @param y      suradnica y
     * @param parent referencia na rastlinu, ktora strelu vytvorila
     */
    public HrachLadovy(int x, int y, StrielajucaRastlina parent) {
        super(x, y, "strelaHrachLadovy", 1, parent);
    }

    /**
     * Vrati x-ovu suradnicu praveho okraja strely
     *
     * @return x-ova suradnica praveho okraja strely
     */
    @Override
    public int getX2() {
        return this.getX() + 20;
    }

    /**
     * Vrati y-ovu suradnicu spodneho okraja strely
     *
     * @return y-ova suradnica spodneho okraja strely
     */
    @Override
    public int getY2() {
        return this.getY() + 20;
    }
}
