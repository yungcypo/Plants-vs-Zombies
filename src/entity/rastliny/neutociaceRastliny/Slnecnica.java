package entity.rastliny.neutociaceRastliny;

import entity.Slnko;
import fri.shapesge.Manazer;

import java.util.ArrayList;

/**
 * Reprezentuje neutociacu rastlinu Slnecnica
 */
public class Slnecnica extends NeutociacaRastlina {
    private ArrayList<Slnko> slnka;
    private int casOdSpawnu = 0;
    private Manazer manazer;

    /**
     * Vytvori Slnecnicu
     *
     * @param x suradnica x
     * @param y suradnica y
     * @param manazer instancia Manazera pre potreby animacie
     */
    public Slnecnica(int x, int y, Manazer manazer) {
        super(x, y, "slnecnica", 240);
        this.slnka = new ArrayList<>();
        this.manazer = manazer;
    }

    /**
     * Stara sa o vytvaranie instancii triedy Slnko
     * Vytvara Slnko kazdych 24 sekund
     */
    public void tikSekunda() {
        this.casOdSpawnu += 1;
        if (this.casOdSpawnu % 24 == 0) {
            this.slnka.add(new Slnko(this.getX() + 25, this.getY() - 10, this.slnka, this.manazer));
        }
    }
}
