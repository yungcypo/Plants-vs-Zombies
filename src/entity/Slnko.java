package entity;

import fri.shapesge.Manazer;

import java.util.ArrayList;

/**
 * Reprezentuje Slnko, ktore vytvara Slnecnica
 */
public class Slnko extends Entita {
    private int uhol = 0;
    private ArrayList<Slnko> slnka;
    private Manazer manazer;
    private int casOdSpawnu = 0;

    /**
     * Vytvori slnko
     *
     * @param x suradnica x
     * @param y suradnica y
     * @param slnka zoznam instancii triedy Slnko, ktore vytvorila jedna Slnecnica
     * @param manazer instancia Manazera pre potreby animacie
     */
    public Slnko(int x, int y, ArrayList<Slnko> slnka, Manazer manazer) {
        super(x, y, "slnko", 1);
        this.slnka = slnka;
        this.manazer = manazer;
        this.manazer.spravujObjekt(this);
    }

    /**
     * Stara sa o animaciu slnka
     */
    @Override
    public void tikAnimacie() {
        this.uhol += 1;
        this.otocO(this.uhol);
    }

    /**
     * Zavola sa metoda despawn() po 15 sekundach od vytvorenia Slnka
     */
    public void tikSekunda() {
        this.casOdSpawnu += 1;
        if (this.casOdSpawnu % 15 == 0) {
            this.despawn();
        }
    }

    /**
     * Zoberie slnko a zaroven ho vymaze
     */
    public void zober() {
        // TODO pridaj hracovi slniecko
        this.despawn();
    }

    /**
     * Vymaze slnko
     */
    public void despawn() {
        this.skry();
        this.slnka.remove(this);
        this.manazer.prestanSpravovatObjekt(this);
    }
}
