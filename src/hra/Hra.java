package hra;

import entity.rastliny.Rastlina;
import entity.rastliny.nestrielajuceRastliny.Slnecnica;
import entity.zombies.Zombie;
import fri.shapesge.Manazer;

import java.util.ArrayList;
import java.util.Random;

public class Hra {
    private HernaPlocha hernaPlocha;
    private ArrayList<Zombie> zombies;
    private ArrayList<Rastlina> rastliny;

    public Hra() {
        this.hernaPlocha = new HernaPlocha();
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);

        this.zombies = new ArrayList<Zombie>();
        this.zombies.add(new Zombie(800, 200));

        this.rastliny = new ArrayList<Rastlina>();
        this.rastliny.add(new Slnecnica(0, 0));

    }

    public void tikPohybu() {
        for (Zombie z : this.zombies) {
            z.posun(-1, 0);
        }
    }

    public void tikAnimacie() {
        for (Zombie z : this.zombies) {
            z.animacia();
        }
        for (Rastlina r : this.rastliny) {
            r.animacia();
        }
    }
}
