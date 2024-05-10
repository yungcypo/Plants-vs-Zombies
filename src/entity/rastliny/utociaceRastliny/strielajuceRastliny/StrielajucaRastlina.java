package entity.rastliny.utociaceRastliny.strielajuceRastliny;

import entity.strely.Hrach;
import entity.strely.Strela;

import java.util.ArrayList;

/**
 * Reprezentuje strielaciu utociacu rastlinu
 */
public abstract class StrielajucaRastlina extends entity.rastliny.Rastlina {
    private ArrayList<Strela> strely;

    /**
     * Konstruktor pre potomkov triedy
     *
     * @param x suranica x
     * @param y suradnica y
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi rastline
     * @param pocetObrazokov pocet obrazkov v animacii
     */
    public StrielajucaRastlina(int x, int y, String nazovAnimacieObrazku, int pocetObrazokov) {
        super(x, y, nazovAnimacieObrazku, pocetObrazokov);
        this.strely = new ArrayList<Strela>();
    }

    /**
     * Vytvori strelu
     *
     * @param cisloObrazku cislo obrazku, pri ktorom sa ma vytvorit nova strela
     */
    public void vystrel(int cisloObrazku) {
        if (this.jeObrazokCislo(cisloObrazku)) {
            this.strely.add(new Hrach(this.getX() + 100, this.getY() + 20, this.strely));
        }
    }

    /**
     * Stara sa o animaciu
     * Pouziva sa pri rastlinach, ktore vytvaraju jednut strelu v jednom animacnom cykle (napr. HrachDvojity)
     *
     * @param cisloObrazku cislo obrazku, pri ktorom sa ma vytvorit nova strela
     */
    public void tikAnimacie(int cisloObrazku) {
        super.tikAnimacie();
        this.vystrel(cisloObrazku);
    }

    /**
     * Stara sa o animaciu
     * Pouziva sa pri rastlinach, ktore vytvaraju viacero striel v jednom animacnom cykle (napr. HrachDvojity)
     *
     * @param cisloObrazku cisla obrazkov, pri ktorych sa ma vytvorit nova strela
     */
    public void tikAnimacie(int[] cisloObrazku) {
        super.tikAnimacie();
        for (int c : cisloObrazku) {
            this.vystrel(c);
        }
    }

    /**
     * Vrati zoznam vsetkych striel
     * Potrebne pre hybanie striel a kolizie
     *
     * @return zoznam striel
     */
    public ArrayList<Strela> getStrely() {
        return this.strely;
    }
}
