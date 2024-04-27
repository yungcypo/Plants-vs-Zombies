package entity.rastliny.neutociaceRastliny;

import entity.Slnko;
import fri.shapesge.Manazer;

import java.util.ArrayList;

public class Slnecnica extends entity.rastliny.utociaceRastliny.nestrielajuceRastliny.NestrielajucaRastlina {
    private ArrayList<Slnko> slnka;
    private int casOdSpawnu = 0;
    private Manazer manazer;

    public Slnecnica(int x, int y, Manazer manazer) {
        super(x, y, "slnecnica", 240);
        this.slnka = new ArrayList<>();
        this.manazer = manazer;
    }

    public void tikSekunda() {
        this.casOdSpawnu += 1;
        System.out.println(this.casOdSpawnu);
        // TODO zmenit z 8 na 24, toto je len na testovanie
        if (this.casOdSpawnu % 8 == 0) {
            this.slnka.add(new Slnko(this.getX() + 25, this.getY() - 10, this.slnka, this.manazer));
        }
    }
}
