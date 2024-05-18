package hra;

import entity.Entita;
import entity.Kosacka;
import entity.Slnko;
import entity.rastliny.Orech;
import entity.rastliny.Rastlina;
import entity.rastliny.Slnecnica;
import entity.rastliny.Zemiak;
import entity.rastliny.strielajuceRastliny.Hrach;
import entity.rastliny.strielajuceRastliny.HrachDvojity;
import entity.strely.Strela;
import entity.zombies.Zombie;
import entity.zombies.ZombieHlavnyTanecnik;
import entity.zombies.ZombieKuzelka;
import entity.zombies.ZombieVedro;
import fri.shapesge.BlokTextu;
import fri.shapesge.Manazer;
import fri.shapesge.StylFontu;
import hra.hud.HUD;
import hra.plocha.HernaPlocha;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

// singleton hra
public class Hra {
    private static Hra hra;
    private Manazer manazer;
    private HernaPlocha hernaPlocha;
    private HUD hud;
    private Kolizie kolizie;
    private ArrayList<Zombie> zombies;
    private ArrayList<Rastlina> rastliny;
    private ArrayList<Kosacka> kosacky;
    private ArrayList<Strela> strely;
    private ArrayList<Slnko> slnka;
    private int hracoveSlniecka = 50;
    private int cas = 0;
    private String nazovSuboru;
    private ArrayList<ZombieData> zombiesNaPridanie;
    private BlokTextu text;
    private int zombiesCelkovo;
    private int zombiesZniceni = 0;

    public static Hra getHra() {
        if (hra == null) {
            hra = new Hra(null);
        }
        return hra;
    }

    public static Hra getHra(String nazovSuboru) {
        if (hra == null) {
            hra = new Hra(nazovSuboru);
        }
        return hra;
    }

    private Hra(String nazovSuboru) {
        this.manazer = new Manazer();

        this.hernaPlocha = new HernaPlocha();

        this.zombies = new ArrayList<>();
        this.zombiesNaPridanie = new ArrayList<>();
        this.rastliny = new ArrayList<>();
        this.strely = new ArrayList<>();
        this.slnka = new ArrayList<>();

        this.kosacky = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            this.kosacky.add(new Kosacka(-25, i * 100 + 65));
        }

        this.spravujZoznam(this.kosacky);

        // vytvorenie HUD
        this.hud = new HUD();
        this.hud.spravujKarty(this.manazer);
        this.hud.moznoSaBudeDatKliknut(this.hracoveSlniecka);

        // TODO unmodifiable list
        this.kolizie = new Kolizie(
                Collections.unmodifiableList(this.zombies),
                Collections.unmodifiableList(this.strely),
                Collections.unmodifiableList(this.rastliny),
                Collections.unmodifiableList(this.kosacky)
        );
        this.manazer.spravujObjekt(this.kolizie);
        this.manazer.spravujObjekt(this);

        this.nazovSuboru = nazovSuboru;
        this.nacitajZombies();
        this.zombiesCelkovo = this.zombiesNaPridanie.size();
        this.text = new BlokTextu("", 50, 40);
        this.text.zmenFont("Arial", StylFontu.BOLD, 20);
        this.text.zobraz();
        this.zmenaTextu();
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
                    case ZEMIAK:
                        this.rastliny.add(new Zemiak(noveX, noveY));
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
                Slnko kliknuteSlnko = null;
                for (Slnko s : this.slnka) {
                    if (s.boloNaMnaKliknute(x, y)) {
                        this.hracoveSlniecka += 25;
                        this.hud.moznoSaBudeDatKliknut();
                        kliknuteSlnko = s;
                    }
                }
                if (kliknuteSlnko != null) {
                    kliknuteSlnko.skry();
                    this.slnka.remove(kliknuteSlnko);
                    this.manazer.prestanSpravovatObjekt(kliknuteSlnko);
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

    public void odstranEntitu(Entita e) {
        this.manazer.prestanSpravovatObjekt(e);

        if (e instanceof Zombie) {
            this.zombies.remove(e);
            this.zombiesZniceni++;
            this.zmenaTextu();
        } else if (e instanceof Strela) {
            this.strely.remove(e);
        } else if (e instanceof Rastlina) {
            this.rastliny.remove(e);
        } else if (e instanceof Kosacka) {
            this.kosacky.remove(e);
        } else if (e instanceof Slnko) {
            this.slnka.remove(e);
        }

        e.skry();
    }

    public void pridajStrelu(Strela s) {
        this.strely.add(s);
        this.manazer.spravujObjekt(s);
    }

    public void pridajSlnko(Slnko s) {
        this.slnka.add(s);
        this.manazer.spravujObjekt(s);
    }

    public void pridajZombie(Zombie z) {
        this.zombies.add(z);
        this.manazer.spravujObjekt(z);
    }

    public int getHracoveSlniecka() {
        return this.hracoveSlniecka;
    }

    private void nacitajZombies() {
        File subor = new File("resources/levely/" + this.nazovSuboru + ".zombiedata");
        Random random = new Random();
        int poslednyCas = 0;
        int x = 1100 + random.nextInt(50);

        try {
            Scanner scanner = new Scanner(subor);
            while (scanner.hasNextLine()) {
                String riadok = scanner.nextLine();
                int delay = Integer.parseInt(riadok.split(", ")[0]);
                int zombieID = Integer.parseInt(riadok.split(", ")[1]);

                int y = random.nextInt(0, 5) * 100;
                poslednyCas += delay;

                this.zombiesNaPridanie.add(new ZombieData(x, y, poslednyCas, zombieID));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void tikSekunda() {
        this.cas++;
        this.zmenaTextu();

        if (!this.zombiesNaPridanie.isEmpty()) {
            ArrayList<ZombieData> naOdstranenie = new ArrayList<>();

            for (ZombieData z : this.zombiesNaPridanie) {
                if (z.getDelay() == this.cas) {
                    switch (z.getId()) {
                        case 0:
                            this.pridajZombie(new Zombie(z.getX(), z.getY()));
                            break;
                        case 1:
                            this.pridajZombie(new ZombieKuzelka(z.getX(), z.getY()));
                            break;
                        case 2:
                            this.pridajZombie(new ZombieVedro(z.getX(), z.getY()));
                            break;
                        case 3:
                            this.pridajZombie(new ZombieHlavnyTanecnik(z.getX(), z.getY()));
                            break;
                        default:
                            break;
                    }
                    naOdstranenie.add(z);
                }
            }

            for (ZombieData z : naOdstranenie) {
                this.zombiesNaPridanie.remove(z);
            }
        } else {
            if (this.zombies.isEmpty()) {
                this.koniecHry(true);
            }
        }
    }

    public void koniecHry(boolean vyhra) {
        this.manazer.prestanSpravovatObjekt(this);

        // po skonceni hry sa prestanu spawnovat slnka, ako v original hre
        for (Rastlina r : this.rastliny) {
            if (r instanceof Slnecnica) {
                ((Slnecnica)r).prestanSpawnovatSlnka();
            }
        }

        // TODO dorobit
        if (vyhra) {
            System.out.println("vyhra!");
        } else {
            System.out.println("prehra!");
        }

    }

    private void zmenaTextu() {
        this.text.zmenText("Level: " + this.nazovSuboru + "     Zničení zombies: " + this.zombiesZniceni + "/" + this.zombiesCelkovo);
    }

    public void pridajZombiesCelkovo(int pocet) {
        this.zombiesCelkovo += pocet;
        this.zmenaTextu();
    }
}
