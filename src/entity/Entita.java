package entity;

import fri.shapesge.Obrazok;

public abstract class Entita {
    private int x;
    private int y;
    private Obrazok obrazok;
    private int pocetObrazkov;
    private int aktualnyObrazok = 0;
    private String nazovAnimacieObrazku;

    public Entita(int x, int y, String nazovAnimacieObrazku, int pocetObrazokov) {
        this.x = x;
        this.y = y;
        this.nazovAnimacieObrazku = nazovAnimacieObrazku;
        this.pocetObrazkov = pocetObrazokov;
        this.obrazok = new Obrazok("images/animacie/" + this.nazovAnimacieObrazku + "/0.png", this.x, this.y);
        this.obrazok.zmenPolohu(this.x, this.y);
        this.obrazok.zobraz();
    }

    public void animacia() {
        this.aktualnyObrazok += 1;
        if (this.aktualnyObrazok >= this.pocetObrazkov) {
            this.aktualnyObrazok = 0;
        }
        this.obrazok.zmenObrazok("images/animacie/" + this.nazovAnimacieObrazku + "/" + this.aktualnyObrazok + ".png");
        this.obrazok.zobraz();
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

}
