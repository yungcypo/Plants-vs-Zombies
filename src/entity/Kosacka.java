package entity;

import hra.Hra;

/**
 * Reprezentuje kosacku
 */
public class Kosacka extends Entita implements IHybajucaSaEntita {
    private boolean zapnuta = false;

    /**
     * Vytvori kosacku
     * //TODO toto by sa dalo zmenit ze Y bude furt rovnaky, x bude (policko + offset hore)
     * @param x suradnica x
     * @param y suradnica y
     */
    public Kosacka(int x, int y) {
        super(x, y, "kosackaVypnuta", 120);
    }

    /**
     * Stara sa o pohyb kosacky, ak je zapnuta
     * Ak kosacka prejde za okraj obrazovky, skryje sa
     */
    @Override
    public void tikPohybu() {
        if (this.zapnuta) {
            if (this.getX() < 1100) {
                this.posunO(16, 0);
            } else {
                Hra.getHra().odstranObjekt(this);
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

    public boolean getZapnuta() {
        return this.zapnuta;
    }

    public int getX2() {
        return this.getX() + 75;
    }

    public int getY2() {
        return this.getY() + 75;
    }
}
