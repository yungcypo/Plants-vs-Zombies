package entity;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;

public abstract class Entita {
    private int x;
    private int y;
    private Obrazok obrazok;
    private int pocetObrazkov;
    private int aktualnyObrazok = 0;
    private String nazovAnimacieObrazku;
    private Manazer manazer;

    public Entita(int x, int y, String nazovAnimacieObrazku, int pocetObrazkov) {
        this.x = x;
        this.y = y;
        this.nazovAnimacieObrazku = nazovAnimacieObrazku;
        this.pocetObrazkov = pocetObrazkov;
        this.obrazok = new Obrazok("obrazky/animacie/" + this.nazovAnimacieObrazku + "/0.png", this.x, this.y);
        this.obrazok.zmenPolohu(this.x, this.y);
        this.obrazok.zobraz();

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }

    public void posunO(int x, int y) {
        this.x += x;
        this.y += y;
        this.obrazok.zmenPolohu(this.x, this.y);
        this.obrazok.zobraz();
    }

    public void posunNa(int x, int y) {
        this.x = x;
        this.y = y;
        this.obrazok.zmenPolohu(this.x, this.y);
        this.obrazok.zobraz();
    }

    public void zobraz() {
        this.obrazok.zobraz();
    }

    public void skry() {
        this.obrazok.skry();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    // vrati true ak sa cislo aktualneho obrazku zhoduje s parametrom
    // vyuzivane pri strielani
    public boolean jeObrazokCislo(int cisloObrazku) {
        return this.aktualnyObrazok == cisloObrazku;
    }

    public void tikAnimacie() {
        this.aktualnyObrazok += 1;
        if (this.aktualnyObrazok >= this.pocetObrazkov) {
            this.aktualnyObrazok = 0;
        }
        this.obrazok.zmenObrazok("obrazky/animacie/" + this.nazovAnimacieObrazku + "/" + this.aktualnyObrazok + ".png");
    }

    public void setNazovAnimacieObrazku(String nazovAnimacieObrazku) {
        this.nazovAnimacieObrazku = nazovAnimacieObrazku;
    }

    public void setPocetObrazkov(int pocetObrazkov) {
        this.aktualnyObrazok = 0;
        this.pocetObrazkov = pocetObrazkov;
    }
}
