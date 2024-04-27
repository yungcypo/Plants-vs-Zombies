package hra;

import entity.Kosacka;
import entity.rastliny.Rastlina;
import entity.rastliny.neutociaceRastliny.Slnecnica;
import entity.rastliny.utociaceRastliny.strielajuceRastliny.Hrach;
import entity.strely.HrachDvojity;
import entity.zombies.Zombie;

import java.util.ArrayList;

public class Hra {
    private HernaPlocha hernaPlocha;
    private ArrayList<Zombie> zombies;
    private ArrayList<Rastlina> rastliny;
    private ArrayList<Kosacka> kosacky;

    public Hra() {
        this.hernaPlocha = new HernaPlocha();

        this.zombies = new ArrayList<Zombie>();
        this.zombies.add(new Zombie(800, 200));

        this.rastliny = new ArrayList<Rastlina>();
        this.rastliny.add(new Slnecnica(0, 0));
        this.rastliny.add(new Hrach(0, 2));
        this.rastliny.add(new HrachDvojity(0, 4));

        this.kosacky = new ArrayList<Kosacka>();
        for (int i = 0; i < 5; i++) {
            this.kosacky.add(new Kosacka(-25, i * 100 + 65));
        }
        this.kosacky.get(3).zapni();
    }
}
