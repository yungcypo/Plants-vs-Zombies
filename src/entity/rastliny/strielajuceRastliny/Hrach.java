package entity.rastliny.strielajuceRastliny;

import entity.strely.Strela;

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


    @Override
    public Strela getNovaStrela() {
        return new entity.strely.Hrach(this.getX() + 100, this.getY() + 20, this);
    }
}
