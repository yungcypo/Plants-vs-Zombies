package entity.rastliny.utociaceRastliny.strielajuceRastliny;

import fri.shapesge.Manazer;

/**
 * Reprezentuje strielaciu utociacu rastlinu HrachDvojity
 */
public class HrachDvojity extends StrielajucaRastlina {

    /**
     * Vytvori HrachDvojity
     *
     * @param x suradnica x
     * @param y suradnica y
     * @param manazer instancia Manazera pre potreby animacie strely
     */
    public HrachDvojity(int x, int y, Manazer manazer) {
        super(x, y, "hrachDvojity", 120, manazer);
    }

    /**
     * Stara sa o animaciu triedy HrachDvojity
     */
    public void tikAnimacie() {
        super.tikAnimacie(new int[]{40, 65});
    }
}
