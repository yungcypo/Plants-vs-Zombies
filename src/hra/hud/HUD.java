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
            this.karty.add(new Karta(
                    this.x + this.sirkaZvyraznenia + i * (100 + this.padding),
                    this.y + this.sirkaZvyraznenia,
                    karty.get(i),
                    this.sirkaZvyraznenia
            ));
        }
    }

    public void klikloSaNaHUD(int x, int y) {
        // prechadza vsetkymi kartami a zistuje, ci na ne bolo kliknute
        for (Karta k : this.karty) {
            if (k.boloNaMnaKliknute(x, y)) {
                this.odzvyrazniKarty();

                if (this.zvyraznenaKarta == null) {
                    // ak nie je ziadna karta zvyraznena
                    this.zvyraznenaKarta = k;
                    this.zvyraznenaKarta.setZvyraznena(true);
                } else {
                    // ak uz je najaka karta zvyraznena
                    if (this.zvyraznenaKarta == k) {
                        // ak sa znova kliklo na kartu, ktora uz je zvyraznena, zrusi sa zvyraznenie
                        this.zvyraznenaKarta.setZvyraznena(false);
                        this.zvyraznenaKarta = null;
                    } else {
                        // ak sa kliklo na inu kartu, zvyrazni novu
                        this.zvyraznenaKarta = k;
                        this.zvyraznenaKarta.setZvyraznena(true);
                    }
                }
                break;
            }
        }
    }

    public Karta getZvyraznenaKarta() {
        return this.zvyraznenaKarta;
    }

    public void odzvyrazniKarty() {
        for (Karta k : this.karty) {
            k.setZvyraznena(false);
        }
        this.zvyraznenaKarta = null;
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
