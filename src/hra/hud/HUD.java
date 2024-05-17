package hra.hud;

import fri.shapesge.BlokTextu;
import fri.shapesge.Obdlznik;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;
import fri.shapesge.Manazer;
import hra.Hra;
import hra.IKlikatelne;

import java.util.ArrayList;
import java.util.List;

/**
 * Head-Up Display
 * Reprezentuje dolny riadok s kartami a poctom slniecok
 */
public class HUD implements IKlikatelne {
    private int x;
    private int y;
    private Obdlznik pozadie;
    private ArrayList<Karta> karty;
    private ArrayList<TypKarty> typyKariet;
    private int sirkaZvyraznenia = 10;
    private int padding = this.sirkaZvyraznenia * 2;
    private Karta zvyraznenaKarta = null;
    private Obrazok obrazokSlnka;
    private int pocetSlniek = 500;
    private BlokTextu text;

    /**
     * Vytvori HUD
     */
    public HUD() {
        this.karty = new ArrayList<>();
        this.typyKariet = new ArrayList<>();
        this.typyKariet.addAll(List.of((TypKarty.values())));

        this.x = 50;
        this.y = 550;

        this.pozadie = new Obdlznik(this.x, this.y);
        this.pozadie.zmenStrany(
                this.typyKariet.size() * (100 + this.padding) + this.padding + 150,
                150 + this.padding * 2
        );
        this.pozadie.zmenFarbu("hud");
        this.pozadie.zobraz();

        for (int i = 0; i < this.typyKariet.size(); i++) {
            this.karty.add(new Karta(
                    this.x + this.sirkaZvyraznenia + i * (100 + this.padding) + 150,
                    this.y + this.sirkaZvyraznenia,
                    this.typyKariet.get(i),
                    this.sirkaZvyraznenia
            ));
        }

        this.obrazokSlnka = new Obrazok("resources/obrazky/slnko.png", this.x + 30, this.y + 25);
        this.obrazokSlnka.zobraz();
        this.text = new BlokTextu(String.valueOf(this.pocetSlniek), this.x + 40, this.y + 165);
        this.text.zmenFont("Arial", StylFontu.BOLD, 45);
        this.text.zmenFarbu("#ffffff");
        this.text.zobraz();
    }

    public void spravujKarty(Manazer manazer) {
        for (Karta k : this.karty) {
            manazer.spravujObjekt(k);
        }
    }

    public void moznoSaBudeDatKliknut(int hracoveSlniecka) {
        for (Karta k : this.karty) {
            k.moznoSaBudeDatKliknut(hracoveSlniecka);
        }
        this.zmenPocetSlniek(hracoveSlniecka);
    }

    public void moznoSaBudeDatKliknut() {
        this.moznoSaBudeDatKliknut(Hra.getHra().getHracoveSlniecka());
    }

    /**
     * Metoda, ktoru zavola trieda Hra, ked sa kliklo na HUD
     *
     * @param x suradnica x, na ktoru sa kliklo
     * @param y suradnica y, na ktoru sa kliklo
     */
    public void klikloSaNaHUD(int x, int y) {
        for (Karta k : this.karty) {
            // ak je karta nacitana a hrac ma dostatok slniecok
            if (k.getMozeBytKliknuta()) {
                // prechadza vsetkymi kartami a zistuje, ci na ne bolo kliknute
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
    }

    /**
     * Vrati zvyraznenu kartu
     *
     * @return zvyraznena karta
     */
    public Karta getZvyraznenaKarta() {
        return this.zvyraznenaKarta;
    }

    /**
     * Zrusi zvyraznenie vsetkych kariet a nastavi zvyraznenu kartu na null
     */
    public void odzvyrazniKarty() {
        for (Karta k : this.karty) {
            if (k.getZvyraznena()) {
                k.setZvyraznena(false);
            }
        }
        this.zvyraznenaKarta = null;
    }

    /**
     * Vrati suradnicu x laveho okraja pozadia
     *
     * @return x suradnica laveho okraja pozadia
     */
    public int getX() {
        return this.x;
    }

    /**
     * Vrati suradnicu y horneho okraja pozadia
     *
     * @return y suradnica horneho okraja pozadia
     */
    public int getY() {
        return this.y;
    }

    /**
     * Vrati suradnicu x praveho okraja pozadia
     *
     * @return x suradnica praveho okraja pozadia
     */
    public int getX2() {
        return this.x + this.karty.size() * (100 + this.padding) + this.padding;
    }

    /**
     * Vrati suradnicu y dolneho okraja pozadia
     *
     * @return y suradnica dolneho okraja pozadia
     */
    public int getY2() {
        return this.y + this.padding * 2 + 150;
    }

    @Override
    public boolean boloNaMnaKliknute(int x, int y) {
        return x > this.x && x < this.getX2() && y > this.y && y < this.getY2();
    }

    public void zmenPocetSlniek(int pocet) {
        this.pocetSlniek = pocet;
        this.text.zmenText(String.valueOf(this.pocetSlniek));
    }
}
