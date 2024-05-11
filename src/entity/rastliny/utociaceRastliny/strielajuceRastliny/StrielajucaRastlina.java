package entity.rastliny.utociaceRastliny.strielajuceRastliny;

import entity.strely.Hrach;
import entity.strely.Strela;
import hra.Hra;

import java.util.ArrayList;

/**
 * Reprezentuje strielaciu utociacu rastlinu
 */
public abstract class StrielajucaRastlina extends entity.rastliny.Rastlina {
    // TODO potrebuje rastlina strely? mozno ich staci iba v hre
    private ArrayList<Strela> strely;
    private boolean striela = false;  // ci ma rastlina strielat (true ak je zombie v riadku)

    /**
     * Konstruktor pre potomkov triedy
     *
     * @param x suranica x
     * @param y suradnica y
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi rastline
     * @param pocetObrazokov pocet obrazkov v animacii
     */
    public StrielajucaRastlina(int x, int y, String nazovAnimacieObrazku, int pocetObrazokov, int hp, Hra hra) {
        super(x, y, nazovAnimacieObrazku, pocetObrazokov, hp, hra);
        this.strely = new ArrayList<>();
    }

    /**
     * Vytvori strelu
     *
     * @param cisloObrazku cislo obrazku, pri ktorom sa ma vytvorit nova strela
     */
    public void vystrel(int cisloObrazku) {
        if (this.jeObrazokCislo(cisloObrazku)) {
            this.strely.add(new Hrach(this.getX() + 100, this.getY() + 20, this.strely));
            this.getHra().pridajStrelu(this.strely.getLast());  // strela sa automaticky aj spravuje
            // TODO strely sa furt pridavaju a pridavaju, ale neodstranuju sa!!
        }
    }

    /**
     * Stara sa o animaciu
     * Pouziva sa pri rastlinach, ktore vytvaraju jednut strelu v jednom animacnom cykle (napr. Hrach)
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
