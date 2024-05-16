package hra.hud;

import fri.shapesge.Obdlznik;
import fri.shapesge.Obrazok;
import hra.Hra;
import hra.IKlikatelne;

/**
 * Predstavuje jednotlive karty na HUD, ktorymi sa pridavaju rastliny
 */
public class Karta implements IKlikatelne {
    private int x;
    private int y;
    private int sirkaZvyraznenia;
    private TypKarty typ;
    private String cesta;
    private int cena;
    private Obrazok obrazok;
    private Obdlznik zvyraznenieObdlznik;
    private boolean zvyraznena = false;
    private int dlzkaNacitavania;
    private int percentoNacitania = 100;
    private Obdlznik nacitavatko;
    private Obrazok tmavyObrazok;
    private boolean mozeBytKliknuta = false;

    /**
     * Konstruktor pre vytvorenie karty
     *
     * @param x                suradnica x
     * @param y                suradnica y
     * @param typKarty         aku rastlinu predstavuje karta (pre potreby obrazku a ceny (a neskor aj dlzky nacitavania))
     * @param sirkaZvyraznenia aky siroky ma byt obdlznik okolo karty, ked je zvyraznena
     */
    public Karta(int x, int y, TypKarty typKarty, int sirkaZvyraznenia) {
        this.x = x;
        this.y = y;
        this.typ = typKarty;
        this.cesta = typKarty.getCesta();
        this.cena = typKarty.getCena();
        this.dlzkaNacitavania = typKarty.getDlzkaNacitavania();
        this.sirkaZvyraznenia = sirkaZvyraznenia;

        this.zvyraznenieObdlznik = new Obdlznik(this.x, this.y);
        this.zvyraznenieObdlznik.zmenStrany(100 + this.sirkaZvyraznenia * 2, 150 + this.sirkaZvyraznenia * 2);
        this.zvyraznenieObdlznik.zmenFarbu("zvyraznenie");

        this.obrazok = new Obrazok(this.cesta, this.x + this.sirkaZvyraznenia, this.y + this.sirkaZvyraznenia);
        this.obrazok.zobraz();

        this.tmavyObrazok = new Obrazok("obrazky/karty/tmavyObrazok.png", this.x + this.sirkaZvyraznenia, this.y + this.sirkaZvyraznenia);
        this.tmavyObrazok.zobraz();

        this.nacitavatko = new Obdlznik(this.x, this.y);
        this.nacitavatko.zmenFarbu("hud");
        this.nacitavatko.zmenStrany(100 + this.sirkaZvyraznenia * 2, 150 + this.sirkaZvyraznenia * 2);
        this.nacitavatko.zobraz();
    }

    /**
     * Zapne alebo vypne zvyraznenie karty
     *
     * @param novaHodnota urcuje, ci ma byt karta zvyraznena alebo nie
     */
    public void setZvyraznena(boolean novaHodnota) {
        this.zvyraznena = novaHodnota;
        if (this.zvyraznena) {
            this.zvyraznenieObdlznik.zobraz();
            this.obrazok.skry();
            this.obrazok.zobraz();
        } else {
            this.zvyraznenieObdlznik.skry();
            this.obrazok.skry();
            this.obrazok.zobraz();
        }
    }

    /**
     * Vrati boolean, ci je karta zvyraznena alebo nie
     *
     * @return true, ak je karta zvyraznena, inak false
     */
    public boolean getZvyraznena() {
        return this.zvyraznena;
    }

    /**
     * Vrati boolean, ci bolo na kartu kliknute podla suradnic v parametri
     *
     * @param x suradnica x
     * @param y suradnica y
     * @return true, ak bolo na kartu kliknute, inak false
     */
    public boolean boloNaMnaKliknute(int x, int y) {
        return x >= this.x && x <= this.getX2() && y >= this.y && y <= this.getY2();
    }

    public void tikPolsekunda() {
        if (this.percentoNacitania < 100) {
            this.percentoNacitania += 100 / (this.dlzkaNacitavania * 2);

            int novaVyska = 150 - ((int)(this.percentoNacitania * 1.5));
            this.nacitavatko.zmenStrany(100 + this.sirkaZvyraznenia * 2, novaVyska);
            this.nacitavatko.zobraz();
        } else {
            this.percentoNacitania = 100;
            this.nacitavatko.zmenStrany(100 + this.sirkaZvyraznenia * 2, 150 + this.sirkaZvyraznenia * 2);
            this.nacitavatko.skry();
        }
    }

    /**
     * Vrati typ karty
     *
     * @return typ karty
     */
    public TypKarty getTyp() {
        return this.typ;
    }

    /**
     * Vrati suradnicu x laveho okraja karty
     *
     * @return suradnica x laveho okraja karty
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * Vrati suradnicu y horneho okraja karty
     *
     * @return suradnica y horneho okraja karty
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * Vrati suradnicu x praveho okraja karty
     *
     * @return suradnica x praveho okraja karty
     */
    @Override
    public int getX2() {
        return this.x + 100;
    }

    /**
     * Vrati suradnicu y dolneho okraja karty
     *
     * @return suradnica y dolneho okraja karty
     */
    @Override
    public int getY2() {
        return this.y + 100;
    }

    public void moznoSaBudeDatKliknut() {
        this.moznoSaBudeDatKliknut(Hra.getHra().getHracoveSlniecka());
    }

    public void moznoSaBudeDatKliknut(int hracoveSlniecka) {
        if (hracoveSlniecka >= this.cena) {
            if (this.percentoNacitania >= 100) {
                this.tmavyObrazok.skry();
                this.mozeBytKliknuta = true;
            }
        } else {
            this.tmavyObrazok.zobraz();
            this.mozeBytKliknuta = false;
        }
    }

    public boolean getMozeBytKliknuta() {
        return this.mozeBytKliknuta;
    }

    public int getCena() {
        return this.cena;
    }

    public void resetNacitavania() {
        this.percentoNacitania = 0;
        this.nacitavatko.zmenStrany(100 + this.sirkaZvyraznenia * 2, 150 + this.sirkaZvyraznenia * 2);
    }
}
