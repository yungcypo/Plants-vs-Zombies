package entity.strely;

import fri.shapesge.Manazer;

import java.util.ArrayList;

/**
 * Reprezentuje strelu Hrach
 * Je vytvarana strielajucimi rastlinami, ako napr. Hrach alebo HrachDvojity
 */
public class Hrach extends Strela {
    private ArrayList<Strela> strely;

    /**
     * Vytvori strelu Hrach
     *
     * @param x suradnica x
     * @param y suradnica y
     * @param strely zoznam vsetkych striel ktore vytvorila jedna strielajuca rastlina
     * @param manazer instancia Manazera pre potreby animacie
     */
    public Hrach(int x, int y, ArrayList<Strela> strely, Manazer manazer) {
        super(x, y, "strelaHrach", 1, strely, manazer);
    }
}
