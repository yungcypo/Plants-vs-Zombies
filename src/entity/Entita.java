package entity;

import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;

import java.util.ArrayList;

/**
 * Reprezentuje vsetky entity v hre, ako napr. zombie alebo strely
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
        this.dataObrazku = new ArrayList<>();

        this.zmenAnimaciu(nazovAnimacieObrazku, pocetObrazkov);

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
     * Zobrazi obrazok entity, ak ma byt zobrazeny
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
     * Urcuje, ci sa aktualny obrazok animacie zhoduje s parametrom.
     * Vyuziva sa napr. v triede StrielajucaRastlina, kde sa urcuje, pri ktorom obrazku sa ma vytvorit strela
     *
     * @param cisloObrazku cislo obrazku, s ktorym sa ma porovnat aktualne cislo obrazku
     * @return true, ak sa aktualny obrazok zhoduje s parametrom, inak false
     */
    public boolean jeObrazokCislo(int cisloObrazku) {
        return this.aktualnyObrazok == cisloObrazku;
    }

    /**
     * Stara sa o animaciu entity.
     * Metoda je spravovana Manazerom
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
     * Zmeni nazov animacie obrazku
     *
     * @param nazovAnimacieObrazku novy nazov animacie obrazku
     */
    private void setNazovAnimacieObrazku(String nazovAnimacieObrazku) {
        this.nazovAnimacieObrazku = nazovAnimacieObrazku;
    }

    private void setPocetObrazkov(int pocetObrazkov) {
        if (this.aktualnyObrazok >= pocetObrazkov) {
            this.aktualnyObrazok = 0;
        }
        this.pocetObrazkov = pocetObrazkov;
    }

    /**
     * Zmeni animaciu entity na novu na zaklade parametrov
     * Vyuziva sa pri entitach, kde sa v 'zivotnom cykle' meni animacia (napr. Zombie pri jedeni alebo Kosacka pri zapnuti)
     *
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi entite
     * @param pocetObrazkov        pocet obrazkov v animacii
     */
    public void zmenAnimaciu(String nazovAnimacieObrazku, int pocetObrazkov) {
        this.setNazovAnimacieObrazku(nazovAnimacieObrazku);
        this.setPocetObrazkov(pocetObrazkov);

        this.dataObrazku.clear();
        for (int i = 0; i < this.pocetObrazkov; i++) {
            this.dataObrazku.add(new DataObrazku("resources/obrazky/animacie/" + this.nazovAnimacieObrazku + "/" + i + ".png"));
        }
    }

    /**
     * Vrati cislo riadku, v ktorom sa entita nachadza na zaklade suradnice y
     *
     * @return cislo riadku, v ktorom sa entita nachadza
     */
    public int getCisloRiadku() {
        return this.y / 100;
    }

    /**
     * Vrati cislo aktualneho obrazku animacie
     *
     * @return cislo aktualneho obrazku animacie
     */
    public int getAktualnyObrazok() {
        return this.aktualnyObrazok;
    }
}
