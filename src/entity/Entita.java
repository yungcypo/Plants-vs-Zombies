package entity;

import fri.shapesge.Obrazok;

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

    /**
     * Konstruktor pre potomkov triedy
     *
     * @param x suradnica x
     * @param y sradnica y
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi entite
     * @param pocetObrazkov pocet obrazkov v animacii
     */
    public Entita(int x, int y, String nazovAnimacieObrazku, int pocetObrazkov) {
        this.x = x;
        this.y = y;
        this.nazovAnimacieObrazku = nazovAnimacieObrazku;
        this.pocetObrazkov = pocetObrazkov;
        this.obrazok = new Obrazok("obrazky/animacie/" + this.nazovAnimacieObrazku + "/0.png", this.x, this.y);
        this.obrazok.zmenPolohu(this.x, this.y);
        this.obrazok.zobraz();
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
        this.obrazok.zobraz();
    }

    /**
     * Posunie entitu na zadane suradnice
     *
     * @param x suradnica x, na ktoru sa ma entita posunut
     * @param y suradnica y, na ktoru sa ma entita posunut
     */
    public void posunNa(int x, int y) {
        this.x = x;
        this.y = y;
        this.obrazok.zmenPolohu(this.x, this.y);
        this.obrazok.zobraz();
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
        this.obrazok.zobraz();
    }

    /**
     * Skryje obrazok entity
     */
    public void skry() {
        this.obrazok.skry();
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
        // ak ma entita iba jeden obrazok (napr. Hrach), ignoruje sa
        if (this.pocetObrazkov == 1) {
            return;
        }

        this.aktualnyObrazok += 1;
        if (this.aktualnyObrazok >= this.pocetObrazkov) {
            this.aktualnyObrazok = 0;
        }
        this.obrazok.zmenObrazok("obrazky/animacie/" + this.nazovAnimacieObrazku + "/" + this.aktualnyObrazok + ".png");
    }

    /**
     * Nastavi entite novu cestu ku priecinku s obrazkami pre animaciu
     * Vyuziva sa napr. v triede Kosacka, kde sa po zapnuti kosacky meni animacia
     *
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami animacie
     */
    public void setNazovAnimacieObrazku(String nazovAnimacieObrazku) {
        this.nazovAnimacieObrazku = nazovAnimacieObrazku;
    }

    /**
     * Nastavi entite novy pocet obrazkov v animacii
     * Vyuziva sa napr. v triede Kosacka, kde sa po zapnuti kosacky meni animacia
     * Taktiez sa resetuje aktualny obrazok na zaciatok animacie pre pripadne problemy
     *
     * @param pocetObrazkov pocet obrazkov v animacii
     */
    public void setPocetObrazkov(int pocetObrazkov) {
        this.aktualnyObrazok = 0;
        this.pocetObrazkov = pocetObrazkov;
    }
}
