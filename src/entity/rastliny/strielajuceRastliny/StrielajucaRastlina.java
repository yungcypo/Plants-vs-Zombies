package entity.rastliny.strielajuceRastliny;

import entity.strely.Strela;
import hra.Hra;

/**
 * Reprezentuje strielajucu rastlinu
 */
public abstract class StrielajucaRastlina extends entity.rastliny.Rastlina {
    private boolean maStrielat = false;  // ci ma rastlina strielat (true ak je zombie v riadku)

    /**
     * Konstruktor pre potomkov triedy
     *
     * @param x                    suranica x
     * @param y                    suradnica y
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi rastline
     * @param pocetObrazkov        pocet obrazkov v animacii
     */
    public StrielajucaRastlina(int x, int y, String nazovAnimacieObrazku, int pocetObrazkov, int hp) {
        super(x, y, nazovAnimacieObrazku, pocetObrazkov, hp);
    }

    /**
     * Vytvori strelu
     *
     * @param cisloObrazku cislo obrazku, pri ktorom sa ma vytvorit nova strela
     */
    public void vystrel(int cisloObrazku) {
        if (this.maStrielat) {
            if (this.jeObrazokCislo(cisloObrazku)) {
                //Hra.getHra().pridajStrelu(new Hrach(this.getX() + 100, this.getY() + 20, this));  // strela sa automaticky aj spravuje
                Hra.getHra().pridajStrelu(this.getNovaStrela());
            }
        }
    }

    public abstract Strela getNovaStrela();

    /**
     * Nastavi, ci ma rastlina strielat
     *
     * @param maStrielat ak ma strielat true, inak false
     */
    public void setMaStrielat(boolean maStrielat) {
        this.maStrielat = maStrielat;
    }

    /**
     * Stara sa o animaciu a strielanie.
     * Ked sa parameter zhoduje s cislom aktualneho obrazku, vystreli.
     * Pouziva sa pri rastlinach, ktore vytvaraju jednu strelu v jednom animacnom cykle (napr. Hrach).
     * Metoda je spravovana Manazerom
     *
     * @param cisloObrazku cislo obrazku, pri ktorom sa ma vytvorit nova strela
     */
    public void tikAnimacie(int cisloObrazku) {
        super.tikAnimacie();
        this.vystrel(cisloObrazku);
    }

    /**
     * Stara sa o animaciu.
     * Ked sa niektory s parametrov zhoduje s cislom aktualneho obrazku, vystreli.
     * Pouziva sa pri rastlinach, ktore vytvaraju viacero striel v jednom animacnom cykle (napr. HrachDvojity).
     * Metoda je spravovana Manazerom
     *
     * @param cisloObrazku cisla obrazkov, pri ktorych sa ma vytvorit nova strela
     */
    public void tikAnimacie(int[] cisloObrazku) {
        super.tikAnimacie();
        for (int c : cisloObrazku) {
            this.vystrel(c);
        }
    }
}
