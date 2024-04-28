package hra;

/**
 * Reprezentuje hernu plochu
 */

public class HernaPlocha {
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
}
