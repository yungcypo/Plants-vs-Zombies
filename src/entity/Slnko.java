package entity;

import hra.Hra;
import hra.IKlikatelne;

/**
 * Reprezentuje Slnko, ktore vytvara Slnecnica
 */
public class Slnko extends Entita implements IKlikatelne {
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
            this.vymaz();
        }
    }

    /**
     * Vymaze slnko
     */
    public void vymaz() {
        Hra.getHra().odstranEntitu(this);
    }

    @Override
    public int getX2() {
        return this.getX() + 50;
    }

    @Override
    public int getY2() {
        return this.getY() + 50;
    }

    @Override
    public boolean boloNaMnaKliknute(int x, int y) {
        return x >= this.getX() && x <= this.getX2() && y >= this.getY() && y <= this.getY2();
    }
}
