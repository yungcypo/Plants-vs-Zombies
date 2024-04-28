package hra;

/**
 * Reprezentuje riadok na hracej ploche
 * Sklada sa z instancii triedy Policko
 */
public class Riadok {
    private int pocetPolicok;
    private Policko[] policka;
    private int y;

    /**
     * Konstruktor triedy Riadok
     * Zadava sa iba suradnica y riadku, pretoze na x-ovej osi zabera (takmer) celu sirku okna
     *
     * @param y suradnica y riadku
     */
    public Riadok(int y) {
        this.pocetPolicok = 10;
        this.policka = new Policko[this.pocetPolicok];
        this.y = y;

        // Vytvorenie policok + priradenie farby
        for (int i = 0; i < this.pocetPolicok; i++) {
            FarbyPolicka farba = null;
            boolean jeParny = this.y / 100 % 2 == 0;

            if (i % 2 == 0) {
                farba = jeParny ? FarbyPolicka.ZELENA1 : FarbyPolicka.ZELENA2;
            } else {
                farba = jeParny ? FarbyPolicka.ZELENA2 : FarbyPolicka.ZELENA3;
            }

            this.policka[i] = new Policko(i * 100 + 50, this.y, farba);
        }
    }
}
