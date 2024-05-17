package entity.strely;

import entity.Entita;
import entity.rastliny.strielajuceRastliny.StrielajucaRastlina;
import hra.Hra;

/**
 * Reprezentuje strelu
 */
public abstract class Strela extends Entita {
    private StrielajucaRastlina parent;

    /**
     * Konstruktor pre potomkov triedy
     *
     * @param x                    suradnica x
     * @param y                    suradnica y
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi strele
     * @param pocetObrazkov        pocet obrazkov v animacii
     * @param parent               referencia na rastlinu, ktora strelu vytvorila
     */
    public Strela(int x, int y, String nazovAnimacieObrazku, int pocetObrazkov, StrielajucaRastlina parent) {
        super(x, y, nazovAnimacieObrazku, pocetObrazkov);
        this.parent = parent;
    }

    /**
     * Stara sa o pohyb strely.
     * Ak strela prejde za hranicu obrazovky, odstrani sa
     */
    public void tikPohybu() {
        if (this.getX() <= 1100) {
            this.posunO(10, 0);
        } else {
            Hra.getHra().odstranEntitu(this);
        }
    }

    /**
     * Povie rastline, ktora strelu vytvorila, aby prestala strielat
     */
    public void nechParentPrestaneStrielat() {
        this.parent.setMaStrielat(false);
    }

    /**
     * Vrati x-ovu suradnicu praveho okraja strely
     *
     * @return x-ova suradnica praveho okraja strely
     */
    public abstract int getX2();

    /**
     * Vrati y-ovu suradnicu dolneho okraja strely
     *
     * @return y-ova suradnica dolneho okraja strely
     */
    public abstract int getY2();
}
