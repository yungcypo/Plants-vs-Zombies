package hra.plocha;

import hra.IKlikatelne;

/**
 * Reprezentuje hernu plochu
 */
public class HernaPlocha implements IKlikatelne {
    private Policko[][] hernaPlocha;
    private int pocetRiadkov = 5;
    private int pocetStlpcov = 10;
    private int rozmerPolicka = 100;

    /**
     * Vytvori hernu plochu z 5 riadkov
     */
    public HernaPlocha() {
        this.hernaPlocha = new Policko[this.pocetRiadkov][this.pocetStlpcov];

        for (int x = 0; x < this.pocetStlpcov; x++) {
            for (int y = 0; y < this.pocetRiadkov; y++) {
                FarbaPolicka farba;

                if (x % 2 == 0) {
                    farba = y % 2 == 0 ? FarbaPolicka.ZELENA1 : FarbaPolicka.ZELENA2;
                } else {
                    farba = y % 2 == 0 ? FarbaPolicka.ZELENA2 : FarbaPolicka.ZELENA3;
                }

                this.hernaPlocha[y][x] = new Policko(
                        x * this.rozmerPolicka + 50,
                        y * this.rozmerPolicka + 50,
                        farba
                );
            }
        }
    }

    /**
     * Vrati suradnicu x laveho okraja hernej plochy
     *
     * @return suradnica x laveho okraja hernej plochy
     */
    @Override
    public int getX() {
        return 50;
    }

    /**
     * Vrati suradnicu y horneho okraja hernej plochy
     *
     * @return suradnica y horneho okraja hernej plochy
     */
    @Override
    public int getY() {
        return 50;
    }

    /**
     * Vrati suradnicu x praveho okraja hernej plochy
     *
     * @return suradnica x praveho okraja hernej plochy
     */
    @Override
    public int getX2() {
        return 50 + this.pocetStlpcov * this.rozmerPolicka;
    }

    /**
     * Vrati suradnicu y dolneho okraja hernej plochy
     *
     * @return suradnica y dolneho okraja hernej plochy
     */
    @Override
    public int getY2() {
        return 50 + this.pocetRiadkov * this.rozmerPolicka;
    }

    @Override
    public boolean boloNaMnaKliknute(int x, int y) {
        return x > this.getX() && x < this.getX2() && y > this.getY() && y < this.getY2();
    }
}
