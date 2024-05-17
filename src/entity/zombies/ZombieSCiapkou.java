package entity.zombies;

import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;

import java.util.ArrayList;

public abstract class ZombieSCiapkou extends Zombie {
    private ArrayList<DataObrazku> dataObrazkuCiapky = new ArrayList<>();
    private Obrazok ciapka;
    private String nazovAnimacieCiapky;
    private int pocetObrazkovCiapky;
    private boolean maCiapku = true;

    /**
     * Vytvori Zombie
     *
     * @param x                    suradnica x
     * @param y                    suradnica y
     * @param nazovAnimacieCiapky nazov animacie obrazku
     */
    public ZombieSCiapkou(int x, int y, String nazovAnimacieCiapky, int pocetObrazkovCiapky) {
        super(x, y);

        this.zmenAnimaciuCiapky("zombie", nazovAnimacieCiapky, pocetObrazkovCiapky);

        this.ciapka = new Obrazok(this.dataObrazkuCiapky.get(this.getAktualnyObrazok()), this.getX(), this.getY());
        this.ciapka.zobraz();
    }

    public void tikAnimacie() {
        super.tikAnimacie();
        if (this.maCiapku) {
            this.ciapka.zmenObrazok(this.dataObrazkuCiapky.get(this.getAktualnyObrazok()));
            this.ciapka.zobraz();
        } else {
            this.ciapka.skry();
        }
    }

    public void tikPohybu() {
        super.tikPohybu();
        if (this.maCiapku) {
            this.ciapka.zmenPolohu(this.getX(), this.getY());
        } else {
            this.ciapka.skry();
        }
    }

    public boolean getMaCiapku() {
        return this.maCiapku;
    }

    public void setMaCiapku(boolean maCiapku) {
        this.maCiapku = maCiapku;
    }

    public void zmenAnimaciuCiapky(String nazovAnimacie, String nazovAnimacieCiapky, int pocetObrazkov) {
        super.zmenAnimaciu(nazovAnimacie, pocetObrazkov);

        this.nazovAnimacieCiapky = nazovAnimacieCiapky;
        this.pocetObrazkovCiapky = pocetObrazkov;

        this.dataObrazkuCiapky.clear();
        for (int i = 0; i < this.pocetObrazkovCiapky; i++) {
            this.dataObrazkuCiapky.add(new DataObrazku("resources/obrazky/animacie/" + this.nazovAnimacieCiapky + "/" + i + ".png"));
        }
    }
}
