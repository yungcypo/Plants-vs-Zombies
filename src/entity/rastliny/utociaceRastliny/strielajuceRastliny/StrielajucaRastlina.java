package entity.rastliny.utociaceRastliny.strielajuceRastliny;

import entity.strely.Hrach;
import entity.strely.Strela;
import fri.shapesge.Manazer;

import java.util.ArrayList;

/**
 * Reprezentuje strielaciu utociacu rastlinu
 */
public abstract class StrielajucaRastlina extends entity.rastliny.Rastlina {
    private ArrayList<Strela> strely;
    private Manazer manazer;

    /**
     * Konstruktor pre potomkov triedy
     *
     * @param x suranica x
     * @param y suradnica y
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi rastline
     * @param pocetObrazokov pocet obrazkov v animacii
     * @param manazer instancia Manazera pre potreby animacie
     */
    public StrielajucaRastlina(int x, int y, String nazovAnimacieObrazku, int pocetObrazokov, Manazer manazer) {
        super(x, y, nazovAnimacieObrazku, pocetObrazokov);
        this.strely = new ArrayList<Strela>();
        this.manazer = manazer;
    }

    /**
     * Vytvori strelu
     *
     * @param cisloObrazku cislo obrazku, pri ktorom sa ma vytvorit nova strela
     */
    public void vystrel(int cisloObrazku) {
        if (this.jeObrazokCislo(cisloObrazku)) {
            this.strely.add(new Hrach(this.getX() + 100, this.getY() + 20, this.strely, this.manazer));
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
}
