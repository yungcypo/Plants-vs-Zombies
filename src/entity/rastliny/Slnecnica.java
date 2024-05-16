package entity.rastliny;

import entity.Slnko;
import hra.Hra;

/**
 * Reprezentuje neutociacu rastlinu Slnecnica
 */
public class Slnecnica extends Rastlina {
    private int casOdSpawnu = 0;

    /**
     * Vytvori Slnecnicu
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public Slnecnica(int x, int y) {
        super(x, y, "slnecnica", 240, 6);
    }

    /**
     * Stara sa o vytvaranie instancii triedy Slnko
     * Vytvara Slnko kazdych 20 sekund
     */
    public void tikSekunda() {
        super.tikSekunda();
        this.casOdSpawnu += 1;
        if (this.casOdSpawnu % 20 == 0) {
            Hra.getHra().pridajSlnko(new Slnko(this.getX() + 25, this.getY() - 10));
        }
    }
}
