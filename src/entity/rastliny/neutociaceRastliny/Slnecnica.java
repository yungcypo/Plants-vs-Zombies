package entity.rastliny.neutociaceRastliny;

import entity.Slnko;
import hra.Hra;

import java.util.ArrayList;

/**
 * Reprezentuje neutociacu rastlinu Slnecnica
 */
public class Slnecnica extends NeutociacaRastlina {
    private ArrayList<Slnko> slnka;
    private int casOdSpawnu = 0;

    /**
     * Vytvori Slnecnicu
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public Slnecnica(int x, int y, Hra hra) {
        super(x, y, "slnecnica", 240, hra);
        this.slnka = new ArrayList<>();
    }

    /**
     * Stara sa o vytvaranie instancii triedy Slnko
     * Vytvara Slnko kazdych 24 sekund
     */
    public void tikSekunda() {
        this.casOdSpawnu += 1;
        if (this.casOdSpawnu % 24 == 0) {
            this.slnka.add(new Slnko(this.getX() + 25, this.getY() - 10, this.slnka));
            this.getHra().spravujSlnko(this.slnka.getLast());
        }
    }
}
