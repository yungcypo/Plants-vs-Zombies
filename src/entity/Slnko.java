package entity;

import hra.Hra;

/**
 * Reprezentuje Slnko, ktore vytvara Slnecnica
 */
public class Slnko extends Entita {
    private int uhol = 0;
    private int casOdSpawnu = 0;

    /**
     * Vytvori slnko
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public Slnko(int x, int y) {
        super(x, y, "slnko", 1);
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
        Hra.getHra().odstranObjekt(this);
    }
}
