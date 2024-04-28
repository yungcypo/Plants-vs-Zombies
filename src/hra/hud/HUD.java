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
        for (Karta k : this.karty) {
            if (k.boloNaMnaKliknute(x, y)) {
                for (Karta k1 : this.karty) {
                    k1.setZvyraznena(false);
                }
                k.setZvyraznena(true);
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
