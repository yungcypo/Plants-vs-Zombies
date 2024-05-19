package entity;

import hra.Hra;
import hra.IKlikatelne;

/**
 * Reprezentuje Slnko, ktore vytvara Slnecnica.
 * Slnko predstavuje penaznu menu v hre, za ktoru sa kupuju rastliny
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
     * Stara sa o odstranenie slnka po 15 sekundach od spawnutia.
     * Metoda je spravovana Manazerom
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

    /**
     * Vrati x-ovu suradnicu praveho okraja slnka
     *
     * @return x-ova suradnica praveho okraja slnka
     */
    @Override
    public int getX2() {
        return this.getX() + 50;
    }

    /**
     * Vrati x-ovu suradnicu dolneho okraja slnka
     *
     * @return x-ova suradnica dolneho okraja slnka
     */
    @Override
    public int getY2() {
        return this.getY() + 50;
    }

    /**
     * Vrati hodnotu, ci sa kliklo na slnko.
     * Porovnava suradnice mysky s okrajmi slnka
     *
     * @param x x-ova suradnica mysky pri kliknuti
     * @param y y-ova suradnica mysky pri kliknuti
     * @return true, ak bolo kliknute na slnko, inak false
     */
    @Override
    public boolean boloNaMnaKliknute(int x, int y) {
        return x >= this.getX() && x <= this.getX2() && y >= this.getY() && y <= this.getY2();
    }
}
