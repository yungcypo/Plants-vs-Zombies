package entity.zombies;

import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;

import java.util.ArrayList;

public abstract class ZombieSCiapkou extends Zombie {
    private ArrayList<DataObrazku> dataObrazkuCiapka = new ArrayList<>();
    private Obrazok ciapka;
    private boolean maCiapku = true;

    /**
     * Vytvori Zombie
     *
     * @param x                    suradnica x
     * @param y                    suradnica y
     * @param nazovAnimacieCiapky nazov animacie obrazku
     */
    public ZombieSCiapkou(int x, int y, String nazovAnimacieCiapky) {
        super(x, y);

        this.dataObrazkuCiapka = new ArrayList<>();
        for (int i = 0; i < 240; i++) {
            this.dataObrazkuCiapka.add(new DataObrazku("resources/obrazky/animacie/" + nazovAnimacieCiapky + "/" + i + ".png"));
        }

        this.ciapka = new Obrazok(this.dataObrazkuCiapka.get(this.getAktualnyObrazok()), this.getX(), this.getY());
        this.ciapka.zobraz();
    }

    public void tikAnimacie() {
        super.tikAnimacie();
        if (this.maCiapku) {
            this.ciapka.zmenObrazok(this.dataObrazkuCiapka.get(this.getAktualnyObrazok()));
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

    public void setMaCiapku(boolean maCiapku) {
        this.maCiapku = maCiapku;
    }
}
