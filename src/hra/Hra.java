package hra;

import entity.Entita;
import entity.Kosacka;
import entity.Slnko;
import entity.rastliny.Orech;
import entity.rastliny.Rastlina;
import entity.rastliny.Slnecnica;
import entity.rastliny.strielajuceRastliny.Hrach;
import entity.rastliny.strielajuceRastliny.HrachDvojity;
import entity.strely.Strela;
import entity.zombies.Zombie;
import entity.zombies.ZombieKuzelka;
import fri.shapesge.Manazer;
import hra.hud.HUD;
import hra.hud.TypKarty;

import java.util.ArrayList;
import java.util.Random;

// singleton hra
public class Hra {
    private static final Hra HRA = new Hra();
    private HernaPlocha hernaPlocha;
    private Manazer manazer;
    private ArrayList<Zombie> zombies;
    private ArrayList<Rastlina> rastliny;
    private ArrayList<Kosacka> kosacky;
    private ArrayList<Strela> strely;
    private ArrayList<Slnko> slnka;
    private HUD hud;
    private ArrayList<TypKarty> odomknuteKarty;
    private Kolizie kolizie;

    public static Hra getHra() {
        return HRA;
    }

    private Hra() {
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);

        this.hernaPlocha = new HernaPlocha();

        this.zombies = new ArrayList<Zombie>();
        this.rastliny = new ArrayList<Rastlina>();
        this.strely = new ArrayList<Strela>();
        this.slnka = new ArrayList<Slnko>();

        // TODO docasne vytvorenie Zombies, neskor sa budu pridavat cez subor
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            this.zombies.add(new Zombie(
                    100 + random.nextInt(200, 400),
                    100 * i
            ));
            this.zombies.add(new ZombieKuzelka(
                    100 + random.nextInt(400, 800),
                    100 * i
            ));
        }


        this.kosacky = new ArrayList<Kosacka>();
        for (int i = 0; i < 5; i++) {
            this.kosacky.add(new Kosacka(-25, i * 100 + 65));
        }
        this.spravujZoznam(this.kosacky);

        // vytvorenie HUD
        this.odomknuteKarty = new ArrayList<>();
        this.odomknuteKarty.add(TypKarty.SLNECNICA);
        this.odomknuteKarty.add(TypKarty.HRACH);
        this.odomknuteKarty.add(TypKarty.HRACH_DVOJITY);
        this.odomknuteKarty.add(TypKarty.ORECH);
        this.hud = new HUD(this.odomknuteKarty);
        this.hud.spravujKarty(this.manazer);


        // TODO ked sa budu normalne vytvarat zombie, toto tu nebude treba
        this.spravujZoznam(this.zombies);

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
                    case SLNECNICA:
                        this.rastliny.add(new Slnecnica(noveX, noveY));
                        break;
                    case HRACH:
                        this.rastliny.add(new Hrach(noveX, noveY));
                        break;
                    case HRACH_DVOJITY:
                        this.rastliny.add(new HrachDvojity(noveX, noveY));
                        break;
                    case ORECH:
                        this.rastliny.add(new Orech(noveX, noveY));
                        break;
                    default:
                        break;
                }
                this.spravujZoznam(this.rastliny);
            }
        }

        this.hud.odzvyrazniKarty();
    }

    public void spravujEntitu(Entita e) {
        this.manazer.prestanSpravovatObjekt(e);
        this.manazer.spravujObjekt(e);
    }

    public void spravujZoznam(ArrayList<? extends Entita> entity) {
        for (Entita e : entity) {
            this.spravujEntitu(e);
        }
    }

    public void pridajStrelu(Strela s) {
        this.strely.add(s);
        this.manazer.spravujObjekt(s);
    }

    public void pridajSlnko(Slnko s) {
        this.slnka.add(s);
        this.manazer.spravujObjekt(s);
    }

    public void odstranObjekt(Entita e) {
        this.manazer.prestanSpravovatObjekt(e);

        if (e instanceof Zombie) {
            this.zombies.remove(e);
        } else if (e instanceof Strela) {
            this.strely.remove(e);
        } else if (e instanceof Rastlina) {
            this.rastliny.remove(e);
        } else if (e instanceof Kosacka) {
            this.kosacky.remove(e);
        }

        e.skry();
    }

    public Manazer getManazer() {
        return this.manazer;
    }
}
