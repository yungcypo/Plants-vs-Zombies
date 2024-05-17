package entity.strely;

import entity.rastliny.strielajuceRastliny.StrielajucaRastlina;

import java.util.ArrayList;

/**
 * Reprezentuje strelu Hrach.
 * Je vytvarana strielajucimi rastlinami, ako napr. Hrach alebo HrachDvojity
 */
public class Hrach extends Strela {
    private ArrayList<Strela> strely;

    /**
     * Vytvori strelu Hrach
     *
     * @param x      suradnica x
     * @param y      suradnica y
     * @param parent rastlina, ktora vytvorila strelu
     */
    public Hrach(int x, int y, StrielajucaRastlina parent) {
        super(x, y, "strelaHrach", 1, parent);
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
