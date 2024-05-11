package entity.rastliny.utociaceRastliny.strielajuceRastliny;

import hra.Hra;

/**
 * Reprezentuje strielaciu utociacu rastlinu HrachDvojity
 */
public class HrachDvojity extends StrielajucaRastlina {

    /**
     * Vytvori HrachDvojity
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public HrachDvojity(int x, int y, Hra hra) {
        super(x, y, "hrachDvojity", 120, 10, hra);
    }

    /**
     * Stara sa o animaciu triedy HrachDvojity
     */
    public void tikAnimacie() {
        super.tikAnimacie(new int[]{40, 65});
    }
}
