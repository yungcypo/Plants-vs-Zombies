package entity;

import fri.shapesge.Manazer;

import java.util.ArrayList;

public class Slnko extends Entita {
    private int uhol = 0;
    private ArrayList<Slnko> slnka;
    private Manazer manazer;
    private int casOdSpawnu = 0;


    public Slnko(int x, int y, ArrayList<Slnko> slnka, Manazer manazer) {
        super(x, y, "slnko", 1);
        this.slnka = slnka;
        this.manazer = manazer;
        this.manazer.spravujObjekt(this);
    }

    @Override
    public void tikAnimacie() {
        this.uhol += 1;
        this.otocO(this.uhol);
    }

    public void tikSekunda() {
        this.casOdSpawnu += 1;
        if (this.casOdSpawnu % 15 == 0) {
            this.despawn();
        }
    }

    public void zober() {
        // TODO pridaj hracovi slniecko?
        this.despawn();
    }

    public void despawn() {
        this.skry();
        this.slnka.remove(this);
        this.manazer.prestanSpravovatObjekt(this);
    }
}
