package entity.rastliny.strielajuceRastliny;

import entity.strely.Strela;

/**
 * Reprezentuje strielaciu rastlinu HrachDvojity
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

    /**
     * Vrati novu strelu triedy Hrach
     *
     * @return nova strela
     */
    @Override
    public Strela getNovaStrela() {
        return new entity.strely.Hrach(this.getX() + 100, this.getY() + 20, this);
    }
}
