package entity.rastliny.strielajuceRastliny;

/**
 * Reprezentuje strielaciu utociacu rastlinu Hrach
 */
public class Hrach extends StrielajucaRastlina {

    /**
     * Vytvori Hrach
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public Hrach(int x, int y) {
        super(x, y, "hrach", 120, 8);
    }

    /**
     * Stara sa o animaciu triedy HrachDvojity
     */
    public void tikAnimacie() {
        super.tikAnimacie(40);
    }
}
