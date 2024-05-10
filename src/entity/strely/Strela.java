package entity.strely;


import java.util.ArrayList;

/**
 * Reprezentuje strelu
 */
public abstract class Strela extends entity.Entita {
    private ArrayList<Strela> strely;

    /**
     * Konstruktor pre potomkov triedy
     *
     * @param x                    suradnica x
     * @param y                    suradnica y
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi rastline
     * @param pocetObrazkov        pocet obrazkov v animacii
     * @param strely               zoznam vsetkych striel ktore vytvorila jedna strielajuca rastlina
     */
    public Strela(int x, int y, String nazovAnimacieObrazku, int pocetObrazkov, ArrayList<Strela> strely) {
        super(x, y, nazovAnimacieObrazku, pocetObrazkov);
        this.strely = strely;
    }

    /**
     * Stara sa o pohyb strely
     * Ak strela prejde za hranicu obrazovky vymazu sa vsetky referencie na nu, cim sa uvolni pamat
     */
    public void tikPohybu() {
        if (this.getX() >= 1100) {
            this.skry();
            this.strely.remove(this);
        } else {
            this.posunO(10, 0);
        }
    }

    public abstract int getX2();

    public abstract int getY2();
}
