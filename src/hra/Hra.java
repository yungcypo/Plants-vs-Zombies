package hra;

import entity.Entita;
import entity.Kosacka;
import entity.Slnko;
import entity.rastliny.Rastlina;
import entity.rastliny.neutociaceRastliny.Slnecnica;
import entity.rastliny.utociaceRastliny.strielajuceRastliny.Hrach;
import entity.rastliny.utociaceRastliny.strielajuceRastliny.HrachDvojity;
import entity.strely.Strela;
import entity.zombies.Zombie;
import fri.shapesge.Manazer;
import hra.hud.HUD;
import hra.hud.TypKarty;

import java.util.ArrayList;
import java.util.Random;

// singleton hra
public class Hra {
    private static final Hra hra = new Hra();
    private HernaPlocha hernaPlocha;
    private Manazer manazer;
    private ArrayList<Zombie> zombies;
    private ArrayList<Rastlina> rastliny;
    private ArrayList<Kosacka> kosacky;
    private ArrayList<Strela> strely;
    private HUD hud;
    private ArrayList<TypKarty> odomknuteKarty;
    private Kolizie kolizie;

    public static Hra getHra() {
        return hra;
    }

    private Hra() {
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);

        this.hernaPlocha = new HernaPlocha();

        Random random = new Random();

        // docasne vytvorenie Zombies, neskor sa budu pridavat cez subor
        this.zombies = new ArrayList<Zombie>();
        for (int i = 0; i <= 2; i++) {
            this.zombies.add(new Zombie(
                    100 + random.nextInt(200, 800),
                    100 * i
            ));
        }

        this.rastliny = new ArrayList<Rastlina>();
        this.strely = new ArrayList<Strela>();

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

        this.kolizie = new Kolizie(this.zombies, this.strely, this.rastliny, this.kosacky);
        this.manazer.spravujObjekt(this.kolizie);
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
                int noveX = (x - 50) / 100;
                int noveY = (y - 50) / 100;

                switch (this.hud.getZvyraznenaKarta().getTyp()) {
                    case SLNECNICA -> this.rastliny.add(new Slnecnica(noveX, noveY));
                    case HRACH -> this.rastliny.add(new Hrach(noveX, noveY));
                    case HRACH_DVOJITY -> this.rastliny.add(new HrachDvojity(noveX, noveY));

                    default -> System.out.println("nemame taku kartu");
                }

                this.hud.odzvyrazniKarty();
                this.spravujZoznamRastlin();
            }
        } else {
            // TODO toto asi nejak nefunguje
            this.hud.odzvyrazniKarty();
        }
    }

    public void spravujVsetkyZoznamy() {
        this.spravujZoznamZombie();
        this.spravujZoznamRastlin();

        // zoznam kosaciek som zatial nepotreboval mat osamostatneny
        for (Kosacka k : this.kosacky) {
            this.manazer.prestanSpravovatObjekt(k);
            this.manazer.spravujObjekt(k);
        }
    }

    public void spravujZoznamZombie() {
        for (Zombie z : this.zombies) {
            this.manazer.prestanSpravovatObjekt(z);
            this.manazer.spravujObjekt(z);
        }
    }

    public void spravujZoznamRastlin() {
        for (Rastlina r : this.rastliny) {
            this.manazer.prestanSpravovatObjekt(r);
            this.manazer.spravujObjekt(r);

            // spravuj zoznam rastlin ?
        }
    }

    public void spravujStrelu(Strela s) {
        this.manazer.spravujObjekt(s);
    }

    public void spravujSlnko(Slnko s) {
        this.manazer.spravujObjekt(s);
    }


    public void prestanSpravovat(Entita e) {
        this.manazer.prestanSpravovatObjekt(e);
    }

    public void pridajStrelu(Strela s) {
        this.strely.add(s);
        this.manazer.spravujObjekt(s);
    }
}
