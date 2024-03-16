package hernaPlocha;

import entity.zombies.Zombie;
import fri.shapesge.Manazer;

import java.util.ArrayList;
import java.util.Random;

public class Hra {
    private Plocha plocha;
    private ArrayList<Zombie> zombies;

    public Hra() {
        this.plocha = new Plocha();
        this.zombies = new ArrayList<Zombie>();

        Random random = new Random();
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);

        this.zombies.add(new Zombie(1100, 30));
        this.zombies.add(new Zombie(1100, 30 + 200));
        this.zombies.add(new Zombie(1100, 30 + 400));
    }

    public void tikPohybu() {
        for (Zombie z : this.zombies) {
            z.posun();
        }
    }

    public void tikAnimacie() {

    }

}
