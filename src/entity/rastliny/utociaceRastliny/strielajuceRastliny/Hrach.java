package entity.rastliny.utociaceRastliny.strielajuceRastliny;

import fri.shapesge.Manazer;

/**
 * Reprezentuje strielaciu utociacu rastlinu Hrach
 */
public class Hrach extends StrielajucaRastlina {

    /**
     * Vytvori Hrach
     *
     * @param x suradnica x
     * @param y suradnica y
     * @param manazer instancia Manazera pre potreby animacie strely
     */
    public Hrach(int x, int y, Manazer manazer) {
        super(x, y, "hrach", 120, manazer);
    }

    /**
     * Stara sa o animaciu triedy HrachDvojity
     */
    public void tikAnimacie() {
        super.tikAnimacie(40);
    }
}
