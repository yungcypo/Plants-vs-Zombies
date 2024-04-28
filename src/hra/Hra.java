package hra;

import entity.Kosacka;
import entity.rastliny.Rastlina;
import entity.rastliny.neutociaceRastliny.Slnecnica;
import entity.rastliny.utociaceRastliny.strielajuceRastliny.Hrach;
import entity.rastliny.utociaceRastliny.strielajuceRastliny.HrachDvojity;
import entity.zombies.Zombie;
import fri.shapesge.Manazer;

import java.util.ArrayList;

public class Hra {
    private HernaPlocha hernaPlocha;
    private ArrayList<Zombie> zombies;
    private ArrayList<Rastlina> rastliny;
    private ArrayList<Kosacka> kosacky;

    public Hra() {
        Manazer manazer = new Manazer();

        this.hernaPlocha = new HernaPlocha();

        this.zombies = new ArrayList<Zombie>();
        this.zombies.add(new Zombie(800, 200));

        this.rastliny = new ArrayList<Rastlina>();
        this.rastliny.add(new Slnecnica(0, 0, manazer));
        this.rastliny.add(new Hrach(0, 2, manazer));
        this.rastliny.add(new HrachDvojity(0, 4, manazer));

        this.kosacky = new ArrayList<Kosacka>();
        for (int i = 0; i < 5; i++) {
            this.kosacky.add(new Kosacka(-25, i * 100 + 65));
        }
        this.kosacky.get(3).zapni();

        for (Zombie z : this.zombies) {
            manazer.spravujObjekt(z);
        }
        for (Rastlina r : this.rastliny) {
            manazer.spravujObjekt(r);
        }
        for (Kosacka k : this.kosacky) {
            manazer.spravujObjekt(k);
        }
    }
}
