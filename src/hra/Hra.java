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
    private Manazer manazer;
    private HernaPlocha hernaPlocha;
    private HUD hud;
    private Kolizie kolizie;
    private ArrayList<Zombie> zombies;
    private ArrayList<Rastlina> rastliny;
    private ArrayList<Kosacka> kosacky;
    private ArrayList<Strela> strely;
    private ArrayList<Slnko> slnka;
    private ArrayList<TypKarty> odomknuteKarty;
    private int hracoveSlniecka = 50;

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
        for (int i = 0; i < 4; i++) {
            this.zombies.add(new Zombie(
                    100 + random.nextInt(400, 600),
                    100 * i
            ));
            this.zombies.add(new ZombieKuzelka(
                    100 + random.nextInt(600, 800),
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
        this.hud.moznoSaBudeDatKliknut(this.hracoveSlniecka);

        // TODO ked sa budu normalne vytvarat zombie, toto tu nebude treba
        this.spravujZoznam(this.zombies);

        this.kolizie = new Kolizie(this.zombies, this.strely, this.rastliny, this.kosacky);
        this.manazer.spravujObjekt(this.kolizie);
    }

    public void vyberSuradnice(int x, int y) {
        // ak sa kliklo na HUD
        if (this.hud.boloNaMnaKliknute(x, y)) {
            this.hud.klikloSaNaHUD(x, y);
            return;
        }

        // ak sa kliklo na hernu plochu
        if (this.hernaPlocha.boloNaMnaKliknute(x, y)) {
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
                // odcita hracovi prislusny pocet slniecok
                this.hracoveSlniecka -= this.hud.getZvyraznenaKarta().getCena();

                this.hud.getZvyraznenaKarta().resetNacitavania();
                this.hud.odzvyrazniKarty();
                this.hud.moznoSaBudeDatKliknut();

                this.spravujZoznam(this.rastliny);
                return;
            }

            if (!this.slnka.isEmpty()) {
                for (Slnko s : this.slnka) {
                    if (s.boloNaMnaKliknute(x, y)) {
                        this.hracoveSlniecka += 25;
                        this.hud.moznoSaBudeDatKliknut();
                        this.odstranObjekt(s);
                    }
                }
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

    public int getHracoveSlniecka() {
        return this.hracoveSlniecka;
    }
}
