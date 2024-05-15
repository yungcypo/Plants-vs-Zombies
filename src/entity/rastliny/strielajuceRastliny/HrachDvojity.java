package entity.rastliny.strielajuceRastliny;

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
    public HrachDvojity(int x, int y) {
        super(x, y, "hrachDvojity", 120, 10);
    }

    /**
     * Stara sa o animaciu triedy HrachDvojity
     */
    public void tikAnimacie() {
        super.tikAnimacie(new int[]{40, 65});
    }
}
