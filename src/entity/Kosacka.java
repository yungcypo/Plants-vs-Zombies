package entity;

import hra.Hra;

/**
 * Reprezentuje kosacku
 */
public class Kosacka extends Entita implements IHybajucaSaEntita {
    private boolean zapnuta = false;

    /**
     * Vytvori kosacku
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public Kosacka(int x, int y) {
        super(x, y, "kosackaVypnuta", 120);
    }

    /**
     * Stara sa o pohyb kosacky, ak je zapnuta
     * Ak kosacka prejde za okraj obrazovky, odstrani sa
     */
    @Override
    public void tikPohybu() {
        if (this.zapnuta) {
            if (this.getX() < 1100) {
                this.posunO(16, 0);
            } else {
                Hra.getHra().odstranEntitu(this);
            }
        }
    }

    /**
     * Zapne kosacku a zmeni jej animaciu
     */
    public void zapni() {
        this.zapnuta = true;
        this.zmenAnimaciu("kosackaZapnuta", 30);
    }

    /**
     * Vrati hodnotu, ci je kosacka zapnuta
     *
     * @return true, ak je zapnuta, inak false
     */
    public boolean getZapnuta() {
        return this.zapnuta;
    }

    /**
     * Vrati x-ovu suradnicu praveho okraja kosacky
     *
     * @return x-ova suradnica praveho okraja kosacky
     */
    public int getX2() {
        return this.getX() + 75;
    }

    /**
     * Vrati y-ovu suradnicu dolneho okraja strely
     *
     * @return y-ova suradnica dolneho okraja strely
     */
    public int getY2() {
        return this.getY() + 75;
    }
}
