package hra;

import entity.Kosacka;
import entity.rastliny.Rastlina;
import entity.rastliny.neutociaceRastliny.Slnecnica;
import entity.rastliny.utociaceRastliny.strielajuceRastliny.Hrach;
import entity.rastliny.utociaceRastliny.strielajuceRastliny.HrachDvojity;
import entity.zombies.Zombie;
import fri.shapesge.Manazer;
import hra.hud.HUD;
import hra.hud.TypKarty;

import java.util.ArrayList;

public class Hra {
    private HernaPlocha hernaPlocha;
    private ArrayList<Zombie> zombies;
    private ArrayList<Rastlina> rastliny;
    private ArrayList<Kosacka> kosacky;
    private HUD hud;
    private ArrayList<TypKarty> odomknuteKarty;

    public Hra() {
        Manazer manazer = new Manazer();
        manazer.spravujObjekt(this);

        this.hernaPlocha = new HernaPlocha();

        // docasne vytvorenie Zombies, neskor sa budu pridavat cez subor
        this.zombies = new ArrayList<Zombie>();
        this.zombies.add(new Zombie(800, 200));

        // docasne vytvorenie Rastlin, neskor sa budu pridavat klikanim na karty v HUD
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

        // vytvorenie HUD
        this.odomknuteKarty = new ArrayList<>();
        this.odomknuteKarty.add(TypKarty.SLNECNICA);
        this.odomknuteKarty.add(TypKarty.HRACH);
        this.odomknuteKarty.add(TypKarty.HRACH_DVOJITY);

        this.hud = new HUD(this.odomknuteKarty);
    }

    public void vyberSuradnice(int x, int y) {
        // ak sa kliklo na HUD
        if (x > this.hud.getX() && x < this.hud.getX2() && y > this.hud.getY() && y < this.hud.getY2()) {
            this.hud.klikloSaNaHUD(x, y);
            return;
        }

        // ak sa kliklo na hernu plochu
        if (x > this.hernaPlocha.getX() && x < this.hernaPlocha.getX2() && y > this.hernaPlocha.getY() && y < this.hernaPlocha.getY2()) {
            // ak je nejaka zvyraznena karta, spawni ju
            if (this.hud.getZvyraznenaKarta() != null) {

            }
        }
    }
}
