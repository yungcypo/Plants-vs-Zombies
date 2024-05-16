package hra;

/**
 * Reprezentuje hernu plochu
 */

public class HernaPlocha implements IKlikatelne {
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
        return 50 + 100 * 10;
    }

    /**
     * Vrati suradnicu y dolneho okraja hernej plochy
     *
     * @return suradnica y dolneho okraja hernej plochy
     */
    @Override
    public int getY2() {
        return 50 + 100 * 5;
    }

    @Override
    public boolean boloNaMnaKliknute(int x, int y) {
        return x > this.getX() && x < this.getX2() && y > this.getY() && y < this.getY2();
    }
}
