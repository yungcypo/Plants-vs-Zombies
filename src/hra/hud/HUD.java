package hra.hud;

import fri.shapesge.Obdlznik;
import hra.Klikatelne;

import java.util.ArrayList;

/**
 * Head Up Display
 * Reprezentuje dolny riadok so slnieckami a kartami
 */
public class HUD implements Klikatelne {
    private int x;
    private int y;
    private Obdlznik pozadie;
    private ArrayList<Karta> karty;
    private int sirkaZvyraznenia = 10;
    private int padding = this.sirkaZvyraznenia * 2;
    private Karta zvyraznenaKarta = null;

    public HUD(ArrayList<TypKarty> karty) {
        this.karty = new ArrayList<>();
        this.x = 50;
        this.y = 550;

        this.pozadie = new Obdlznik(this.x, this.y);
        this.pozadie.zmenStrany(
                karty.size() * (100 + this.padding) + this.padding,
                150 + this.padding * 2
        );
        this.pozadie.zmenFarbu("#987554");
        this.pozadie.zobraz();

        for (int i = 0; i < karty.size(); i++) {
            TypKarty k = karty.get(i);
            this.karty.add(new Karta(
                    this.x + this.sirkaZvyraznenia + i * (100 + this.padding),
                    this.y + this.sirkaZvyraznenia,
                    k.getCesta(),
                    k.getCena(),
                    this.sirkaZvyraznenia
            ));
        }
    }

    public void klikloSaNaHUD(int x, int y) {
        // prechadza vsetkymi kartami a zistuje, ci na ne bolo kliknute
        for (Karta k : this.karty) {
            if (k.boloNaMnaKliknute(x, y)) {
                // zrusi zvyraznenie vsetkych kariet
                for (Karta k1 : this.karty) {
                    k1.setZvyraznena(false);
                }

                if (this.zvyraznenaKarta == null) {
                    // ak nie je ziadna karta zvyraznena
                    this.zvyraznenaKarta = k;
                    this.zvyraznenaKarta.setZvyraznena(true);
                } else {
                    // ak uz je najaka karta zvyraznena
                    if (this.zvyraznenaKarta == k) {
                        // ak sa kliklo na uz zvyraznenu kartu, zrusi zvyraznenie
                        this.zvyraznenaKarta.setZvyraznena(false);
                        this.zvyraznenaKarta = null;
                    } else {
                        // ak sa kliklo na inu kartu, zrusi zvyraznenie starej a zvyrazni novu
                        this.zvyraznenaKarta = k;
                        this.zvyraznenaKarta.setZvyraznena(true);
                    }
                }

                break;
            }
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    // vrati x suradnicu praveho okraja pozadia
    public int getX2() {
        return this.x + this.karty.size() * (100 + this.padding) + this.padding;
    }

    // vrati y suradnicu dolneho okraja pozadia
    public int getY2() {
        return this.y + this.padding * 2 + 150;
    }

    public int getSirkaZvyraznenia() {
        return this.sirkaZvyraznenia;
    }

    public int getPadding() {
        return this.padding;
    }
}
