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
     */
    public Strela(int x, int y, String nazovAnimacieObrazku, int pocetObrazkov, StrielajucaRastlina parent) {
        super(x, y, nazovAnimacieObrazku, pocetObrazkov);
        this.parent = parent;
    }

    /**
     * Stara sa o pohyb strely
     * Ak strela prejde za hranicu obrazovky vymazu sa vsetky referencie na nu, cim sa uvolni pamat
     */
    public void tikPohybu() {
        if (this.getX() <= 1100) {
            this.posunO(10, 0);
        } else {
            Hra.getHra().odstranEntitu(this);
        }
    }

    public abstract int getX2();

    public abstract int getY2();

    public void nechParentPrestaneStrielat() {
        this.parent.setMaStrielat(false);
    }
}
