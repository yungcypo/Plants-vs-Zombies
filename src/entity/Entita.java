package entity;

import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;

import java.util.ArrayList;

/**
 * Reprezentuje vsetky entity v hre, ako napr. rastliny, zombie alebo strely
 */
public abstract class Entita {
    private int x;
    private int y;
    private Obrazok obrazok;
    private int pocetObrazkov;
    private int aktualnyObrazok = 0;
    private String nazovAnimacieObrazku;
    private ArrayList<DataObrazku> dataObrazku;
    private boolean maBytZobrazena = true;

    /**
     * Konstruktor pre potomkov triedy
     *
     * @param x                    suradnica x
     * @param y                    sradnica y
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi entite
     * @param pocetObrazkov        pocet obrazkov v animacii
     */
    public Entita(int x, int y, String nazovAnimacieObrazku, int pocetObrazkov) {
        this.x = x;
        this.y = y;
        this.nazovAnimacieObrazku = nazovAnimacieObrazku;
        this.pocetObrazkov = pocetObrazkov;
        this.dataObrazku = new ArrayList<>();
        this.nacitajDataObrazku();

        this.obrazok = new Obrazok(this.dataObrazku.get(this.aktualnyObrazok), this.x, this.y);
        this.obrazok.zmenPolohu(this.x, this.y);
        this.zobraz();
    }

    /**
     * Posunie entitu o zadane suradnice
     *
     * @param x suradnica x, o ktoru sa ma entita posunut
     * @param y suradnica y, o ktoru sa ma entita posunut
     */
    public void posunO(int x, int y) {
        this.x += x;
        this.y += y;
        this.obrazok.zmenPolohu(this.x, this.y);
        this.zobraz();
    }

    /**
     * Otočí entitu o zadaný uhol
     *
     * @param uhol uhol, o ktory sa ma entita otocit
     */
    public void otocO(int uhol) {
        this.obrazok.zmenUhol(uhol);

    }

    /**
     * Zobrazi obrazok entity
     */
    public void zobraz() {
        if (this.maBytZobrazena) {
            this.obrazok.zobraz();
        }
    }

    /**
     * Skryje obrazok entity
     */
    public void skry() {
        this.obrazok.skry();
        this.maBytZobrazena = false;
    }

    public void setMaBytZobrazena(boolean maBytZobrazena) {
        this.maBytZobrazena = maBytZobrazena;
    }

    /**
     * Vrati suradnicu x entity
     */
    public int getX() {
        return this.x;
    }

    /**
     * Vrati suradnicu y entity
     */
    public int getY() {
        return this.y;
    }

    /**
     * Urcuje, ci sa aktualny obrazok animacie zhoduje s parametrom
     * Vyuziva sa napr. v triede StrielajucaRastlina, kde sa urcuje, pri ktorom obrazku sa ma vytvorit strela
     *
     * @param cisloObrazku cislo obrazku, s ktorym sa ma porovnat aktualny obrazok
     * @return true, ak sa aktualny obrazok zhoduje s parametrom, inak false
     */
    public boolean jeObrazokCislo(int cisloObrazku) {
        return this.aktualnyObrazok == cisloObrazku;
    }

    /**
     * Stara sa o animaciu entity
     */
    public void tikAnimacie() {
        // ak ma entita iba jeden obrazok (napr. strela Hrach), ignoruje sa
        if (this.pocetObrazkov == 1) {
            return;
        }

        this.aktualnyObrazok += 1;
        if (this.aktualnyObrazok >= this.pocetObrazkov) {
            this.aktualnyObrazok = 0;
        }
        this.obrazok.zmenObrazok(this.dataObrazku.get(this.aktualnyObrazok));
    }

    /**
     * Nastavi entite novu cestu ku priecinku s obrazkami pre animaciu
     * Vyuziva sa napr. v triede Kosacka, kde sa po zapnuti kosacky meni animacia
     *
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami animacie
     */
    private void setNazovAnimacieObrazku(String nazovAnimacieObrazku) {
        this.nazovAnimacieObrazku = nazovAnimacieObrazku;
    }

    /**
     * Nastavi entite novy pocet obrazkov v animacii
     * Vyuziva sa napr. v triede Kosacka, kde sa po zapnuti kosacky meni animacia
     * Taktiez sa resetuje aktualny obrazok na zaciatok animacie (ak je to potrebne) pre pripadne problemy
     *
     * @param pocetObrazkov pocet obrazkov v animacii
     */
    private void setPocetObrazkov(int pocetObrazkov) {
        if (this.aktualnyObrazok >= pocetObrazkov) {
            this.aktualnyObrazok = 0;
        }
        this.pocetObrazkov = pocetObrazkov;
    }

    public void zmenAnimaciu(String nazovAnimacieObrazku, int pocetObrazkov) {
        this.setNazovAnimacieObrazku(nazovAnimacieObrazku);
        this.setPocetObrazkov(pocetObrazkov);
        this.nacitajDataObrazku();
    }

    private void nacitajDataObrazku() {
        this.dataObrazku.clear();
        for (int i = 0; i < this.pocetObrazkov; i++) {
            this.dataObrazku.add(new DataObrazku("resources/obrazky/animacie/" + this.nazovAnimacieObrazku + "/" + i + ".png"));
        }
    }

    public int getCisloRiadku() {
        return this.y / 100;
    }

    public int getAktualnyObrazok() {
        return this.aktualnyObrazok;
    }
}
