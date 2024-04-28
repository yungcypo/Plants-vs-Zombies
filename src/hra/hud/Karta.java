package hra.hud;

import fri.shapesge.Obdlznik;
import fri.shapesge.Obrazok;
import hra.Klikatelne;

public class Karta implements Klikatelne {
    private int x;
    private int y;
    private String cesta;
    private int cena;
    private Obrazok obrazok;
    private Obdlznik zvyraznenieObdlznik;
    private boolean zvyraznena = false;

    //TODO dlzka nacitavania

    public Karta(int x, int y, String cesta, int cena, int sirkaZvyraznenia) {
        this.x = x;
        this.y = y;
        this.cesta = cesta;
        this.cena = cena;

        this.zvyraznenieObdlznik = new Obdlznik(this.x, this.y);
        this.zvyraznenieObdlznik.zmenStrany(100 + sirkaZvyraznenia * 2, 150 + sirkaZvyraznenia * 2);
        this.zvyraznenieObdlznik.zmenFarbu("zvyraznenie");

        this.obrazok = new Obrazok(this.cesta, this.x + sirkaZvyraznenia, this.y + sirkaZvyraznenia);
        this.obrazok.zobraz();
    }

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

    public boolean getZvyraznena() {
        return this.zvyraznena;
    }

    public boolean boloNaMnaKliknute(int x, int y) {
        return x >= this.x && x <= this.getX2() && y >= this.y && y <= this.getY2();
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getX2() {
        return this.x + 100;
    }

    @Override
    public int getY2() {
        return this.y + 100;
    }
}
