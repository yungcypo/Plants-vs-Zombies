package hra.plocha;

import hra.IKlikatelne;

/**
 * Reprezentuje hernu plochu
 */
public class HernaPlocha implements IKlikatelne {
    private final Policko[][] hernaPlocha;
    private final int pocetRiadkov = 5;
    private final int pocetStlpcov = 10;
    private final int rozmerPolicka = 100;

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

    /**
     * Urcuje, ci bolo kliknute na hraciu plochu
     *
     * @param x suradnica x kliknutia
     * @param y suradnica y kliknutia
     * @return true, ak bolo kliknute na hraciu plochu, inak false
     */
    @Override
    public boolean boloNaMnaKliknute(int x, int y) {
        return x > this.getX() && x < this.getX2() && y > this.getY() && y < this.getY2();
    }

    /**
     * Urcuje, ci bolo kliknute na policko s indexom x, y
     *
     * @param x index x policka, ktore sa ma overovat
     * @param y index y policka, ktore sa ma overovat
     * @return true, ak bolo kliknute na dane policko, inak false
     */
    public boolean jePolickoObsadene(int x, int y) {
        return this.hernaPlocha[y][x].getObsadene();
    }

    /**
     * Nastavi stav policka zadaneho indexom x, y na true alebo false
     *
     * @param x    index x policka
     * @param y    index y policka
     * @param stav true, ak ma byt policko obsadene, inak false
     */
    public void nastavObsadeniePolicka(int x, int y, boolean stav) {
        this.hernaPlocha[y][x].setObsadene(stav);
    }
}
