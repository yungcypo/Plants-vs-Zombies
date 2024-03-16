package hernaPlocha;

public class Riadok {
    private int pocetPolicok;
    private Policko[] policka;
    private int y;

    public Riadok(int y) {
        this.pocetPolicok = 10;
        this.policka = new Policko[this.pocetPolicok];
        this.y = y;

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
