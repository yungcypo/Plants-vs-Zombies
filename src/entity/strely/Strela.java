package entity.strely;

import fri.shapesge.Manazer;

public abstract class Strela extends entity.Entita {
    public Strela(int x, int y, String nazovAnimacieObrazku, int pocetObrazkov) {
        super(x, y, nazovAnimacieObrazku, pocetObrazkov);
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);
    }

    public void tikPohybu() {
        if (this.getX() >= 1100) {
            this.skry();
        } else {
            this.posunO(10, 0);
        }
    }
}
