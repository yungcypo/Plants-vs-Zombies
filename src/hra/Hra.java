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
    private Manazer manazer;
    private ArrayList<Zombie> zombies;
    private ArrayList<Rastlina> rastliny;
    private ArrayList<Kosacka> kosacky;
    private HUD hud;
    private ArrayList<TypKarty> odomknuteKarty;

    public Hra() {
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);

        this.hernaPlocha = new HernaPlocha();

        // docasne vytvorenie Zombies, neskor sa budu pridavat cez subor
        this.zombies = new ArrayList<Zombie>();
        this.zombies.add(new Zombie(950, 200));
        this.zombies.add(new Zombie(950, 300));

        // docasne vytvorenie Rastlin, neskor sa budu pridavat klikanim na karty v HUD
        this.rastliny = new ArrayList<Rastlina>();

        this.kosacky = new ArrayList<Kosacka>();
        for (int i = 0; i < 5; i++) {
            this.kosacky.add(new Kosacka(-25, i * 100 + 65));
        }

        // vytvorenie HUD
        this.odomknuteKarty = new ArrayList<>();
        this.odomknuteKarty.add(TypKarty.SLNECNICA);
        this.odomknuteKarty.add(TypKarty.HRACH);
        this.odomknuteKarty.add(TypKarty.HRACH_DVOJITY);

        this.hud = new HUD(this.odomknuteKarty);

        this.spravujVsetkyZoznamy();
    }

    public void vyberSuradnice(int x, int y) {
        // ak sa kliklo na HUD
        if (x > this.hud.getX() && x < this.hud.getX2() && y > this.hud.getY() && y < this.hud.getY2()) {
            this.hud.klikloSaNaHUD(x, y);
            return;
        }

        // ak sa kliklo na hernu plochu
        // TODO NIEKDE JE CHYBA, PRI PRIDAVANI VIAC RASTLIN SA ZRYCHLUJE ANIMACIA STARYCH RASTLIN!!
        if (x > this.hernaPlocha.getX() && x < this.hernaPlocha.getX2() && y > this.hernaPlocha.getY() && y < this.hernaPlocha.getY2()) {
            // ak je nejaka zvyraznena karta, spawni ju
            if (this.hud.getZvyraznenaKarta() != null) {
                int noveX = (x - 50) / 100;
                int noveY = (y - 50) / 100;

                switch (this.hud.getZvyraznenaKarta().getTyp()) {
                    case SLNECNICA -> this.rastliny.add(new Slnecnica(noveX, noveY, this.manazer));
                    case HRACH -> this.rastliny.add(new Hrach(noveX, noveY, this.manazer));
                    case HRACH_DVOJITY -> this.rastliny.add(new HrachDvojity(noveX, noveY, this.manazer));

                    default -> System.out.println("nemame taku kartu");
                }

                this.hud.odzvyrazniKarty();
                this.spravujZoznamRastlin();
            }
        }
    }
    
    public void spravujVsetkyZoznamy() {
        this.spravujZoznamZombie();
        this.spravujZoznamRastlin();
        
        // zoznam kosaciek som zatial nepotreboval mat osamostatneny
        for (Kosacka k : this.kosacky) {
            this.manazer.spravujObjekt(k);
        }
    }
    
    public void spravujZoznamZombie() {
        for (Zombie z : this.zombies) {
            this.manazer.spravujObjekt(z);
        }
    }
    
    public void spravujZoznamRastlin() {
        for (Rastlina r : this.rastliny) {
            this.manazer.spravujObjekt(r);
        }
    }
}
