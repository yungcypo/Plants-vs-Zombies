package entity.rastliny.strielajuceRastliny;

/**
 * Reprezentuje strielaciu rastlinu Hrach
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
     * Stara sa o animaciu triedy Hrach
     */
    public void tikAnimacie() {
        super.tikAnimacie(40);
    }
}
