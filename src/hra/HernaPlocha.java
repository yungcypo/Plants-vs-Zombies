package hra;

/**
 * Reprezentuje hernu plochu
 */

public class HernaPlocha implements Klikatelne {
    private Riadok[] riadky;

    /**
     * Vytvori hernu plochu z 5 riadkov
     */

    public HernaPlocha() {
        this.riadky = new Riadok[5];

        for (int i = 0; i < 5; i++) {
            this.riadky[i] = new Riadok(i * 100 + 50);
        }
    }

    // TODO toto nejak lepsie vymysliet, pomocou poctu policok a riadkov

    @Override
    public int getX() {
        return 50;
    }

    @Override
    public int getY() {
        return 50;
    }

    @Override
    public int getX2() {
        return 50 + 100 * 10;
    }

    @Override
    public int getY2() {
        return 50 + 100 * 5;
    }
}
