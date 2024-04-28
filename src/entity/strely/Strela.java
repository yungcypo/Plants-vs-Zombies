package entity.strely;

import fri.shapesge.Manazer;

import java.util.ArrayList;

/**
 * Reprezentuje strelu
 */
public abstract class Strela extends entity.Entita {
    private ArrayList<Strela> strely;
    private Manazer manazer;

    /**
     * Konstruktor pre potomkov triedy
     *
     * @param x suradnica x
     * @param y suradnica y
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi rastline
     * @param pocetObrazkov pocet obrazkov v animacii
     * @param strely zoznam vsetkych striel ktore vytvorila jedna strielajuca rastlina
     * @param manazer instancia Manazera pre potreby animacie
     */
    public Strela(int x, int y, String nazovAnimacieObrazku, int pocetObrazkov, ArrayList<Strela> strely, Manazer manazer) {
        super(x, y, nazovAnimacieObrazku, pocetObrazkov);
        this.strely = strely;
        this.manazer = manazer;
        this.manazer.spravujObjekt(this);
    }

    /**
     * Stara sa o pohyb strely
     * Ak strela prejde za hranicu obrazovky vymazu sa vsetky referencie na nu, cim sa uvolni pamat
     */
    public void tikPohybu() {
        if (this.getX() >= 1100) {
            this.skry();
            this.manazer.prestanSpravovatObjekt(this);
            this.strely.remove(this);
        } else {
            this.posunO(10, 0);
        }
    }
}
