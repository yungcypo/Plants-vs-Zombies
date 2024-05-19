package entity.zombies;

import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;

import java.util.ArrayList;

/**
 * Reprezentuje Zombie s Ciapkou - ZombieKuzelka alebo ZombieVedro
 */
public abstract class ZombieSCiapkou extends Zombie {
    private final ArrayList<DataObrazku> dataObrazkuCiapky = new ArrayList<>();
    private final Obrazok ciapka;
    private String nazovAnimacieCiapky;
    private int pocetObrazkovCiapky;
    private boolean maCiapku = true;

    /**
     * Konstruktor pre potomkov triedy
     *
     * @param x                   suradnica x
     * @param y                   suradnica y
     * @param nazovAnimacieCiapky nazov animacie obrazku ciapky
     * @param pocetObrazkovCiapky pocet obrazkov animacie ciapky
     */
    public ZombieSCiapkou(int x, int y, String nazovAnimacieCiapky, int pocetObrazkovCiapky) {
        super(x, y);

        this.zmenAnimaciuCiapky("zombie", nazovAnimacieCiapky, pocetObrazkovCiapky);

        this.ciapka = new Obrazok(this.dataObrazkuCiapky.get(this.getAktualnyObrazok()), this.getX(), this.getY());
        this.ciapka.zobraz();
    }

    /**
     * Stara sa o animaciu ZombieSCiapkou.
     * Metoda je spravovana Manazerom
     */
    public void tikAnimacie() {
        super.tikAnimacie();
        if (this.maCiapku) {
            this.ciapka.zmenObrazok(this.dataObrazkuCiapky.get(this.getAktualnyObrazok()));
            this.ciapka.zobraz();
        } else {
            this.ciapka.skry();
        }
    }

    /**
     * Stara sa o pohyb ZombieSCiapkou.
     * Metoda je spravovana Manazerom
     */
    public void tikPohybu() {
        super.tikPohybu();
        if (this.maCiapku) {
            this.ciapka.zmenPolohu(this.getX(), this.getY());
        } else {
            this.ciapka.skry();
        }
    }

    /**
     * Nastavi, ci ma mat zombie ciapku
     */
    public void setMaCiapku(boolean maCiapku) {
        this.maCiapku = maCiapku;
    }

    /**
     * Zmeni animaciu ZombieSCiapkou.
     * Vymaze sa zoznam s datami obrazku a nahradi sa novymi datami obrazku.
     *
     * @param nazovAnimacie       cesta ku priecinku s obrazkami prisluchajucimi zombie
     * @param nazovAnimacieCiapky cesta ku priecinku s obrazkami prisluchajucimi ciapke
     * @param pocetObrazkov       pocet obrazkov animacie
     */
    public void zmenAnimaciuCiapky(String nazovAnimacie, String nazovAnimacieCiapky, int pocetObrazkov) {
        super.zmenAnimaciu(nazovAnimacie, pocetObrazkov);

        this.nazovAnimacieCiapky = nazovAnimacieCiapky;
        this.pocetObrazkovCiapky = pocetObrazkov;

        this.dataObrazkuCiapky.clear();
        for (int i = 0; i < this.pocetObrazkovCiapky; i++) {
            this.dataObrazkuCiapky.add(new DataObrazku("resources/obrazky/animacie/" + this.nazovAnimacieCiapky + "/" + i + ".png"));
        }
    }

    /**
     * Skryje ciapku
     */
    public void skryCiapku() {
        this.ciapka.skry();
    }
}
