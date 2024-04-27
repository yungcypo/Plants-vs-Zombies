package entity.strely;

import fri.shapesge.Manazer;

import java.util.ArrayList;

public abstract class Strela extends entity.Entita {
    private ArrayList<Strela> strely;
    private Manazer manazer;

    public Strela(int x, int y, String nazovAnimacieObrazku, int pocetObrazkov, ArrayList<Strela> strely, Manazer manazer) {
        super(x, y, nazovAnimacieObrazku, pocetObrazkov);
        this.strely = strely;
        this.manazer = manazer;
        this.manazer.spravujObjekt(this);
    }

    public void tikPohybu() {
        if (this.getX() >= 1100) {
            this.skry();
            this.manazer.prestanSpravovatObjekt(this);
            this.strely.remove(this);
        } else {
            this.posunO(10, 0);
        }
    }
}
