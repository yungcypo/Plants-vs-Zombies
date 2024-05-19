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
import entity.rastliny.strielajuceRastliny.HrachLadovy;
import entity.strely.Strela;
import entity.zombies.Zombie;
import entity.zombies.ZombieHlavnyTanecnik;
import entity.zombies.ZombieKuzelka;
import entity.zombies.ZombieVedro;
import fri.shapesge.BlokTextu;
import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;
import hra.hud.HUD;
import hra.plocha.HernaPlocha;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;

/**
 * Trieda Hra.
 * Hlavna trieda, kde sa vykonava vacsina hry.
 * Trieda je singleton, t. j. moze byt vytvorena iba jedna instancia
 */
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

    /**
     * Privatny konstruktor triedy hra
     *
     * @param nazovSuboru nazov suboru s levelom
     */
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
                Collections.unmodifiableList(this.kosacky),
                this.hernaPlocha
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

    /**
     * Vrati instanciu triedy Hra s levelom podla nazvu suboru.
     * Touto metodou sa vytvara prva instancia triedy
     *
     * @param nazovSuboru nazov suboru s levelom
     * @return instancia triedy Hra
     */
    public static Hra getHra(String nazovSuboru) {
        if (hra == null) {
            hra = new Hra(nazovSuboru);
        }
        return hra;
    }

    /**
     * Vrati instanciu triedy Hra
     *
     * @return instancia triedy Hra
     */
    public static Hra getHra() {
        return hra;
    }

    /**
     * Stara sa o klikanie myskou v hre.
     * Metoda je spravovana Manazerom
     *
     * @param x suradnica x, na ktoru s kliklo
     * @param y suradnica y, na ktoru s kliklo
     */
    public void vyberSuradnice(int x, int y) {
        // ak sa kliklo na HUD
        if (this.hud.boloNaMnaKliknute(x, y)) {
            this.hud.klikloSaNaHUD(x, y);
            return;
        }

        // ak sa kliklo na hernu plochu
        if (this.hernaPlocha.boloNaMnaKliknute(x, y)) {
            // premeni suradnice na cislo policka
            int noveX = (x - 50) / 100;
            int noveY = (y - 50) / 100;

            // ak policko, na ktore sa kliklo nie je obsadene
            if (!this.hernaPlocha.jePolickoObsadene(noveX, noveY)) {
                // ak je nejaka zvyraznena karta, spawni ju
                if (this.hud.getZvyraznenaKarta() != null) {

                    boolean uspesneVytvorenie = true;
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
                        case HRACH_LADOVY:
                            this.rastliny.add(new HrachLadovy(noveX, noveY));
                            break;
                        case ORECH:
                            this.rastliny.add(new Orech(noveX, noveY));
                            break;
                        case ZEMIAK:
                            this.rastliny.add(new Zemiak(noveX, noveY));
                            break;
                        default:
                            uspesneVytvorenie = false;
                            break;
                    }
                    if (uspesneVytvorenie) {
                        this.hernaPlocha.nastavObsadeniePolicka(noveX, noveY, true);
                    }

                    // odcita hracovi prislusny pocet slniecok
                    this.hracoveSlniecka -= this.hud.getZvyraznenaKarta().getCena();

                    this.hud.getZvyraznenaKarta().resetNacitavania();
                    this.hud.odzvyrazniKarty();
                    this.hud.moznoSaBudeDatKliknut();

                    this.spravujZoznam(this.rastliny);
                    return;
                }
            }

            // ak existuju nejake slniecka
            if (!this.slnka.isEmpty()) {
                Slnko kliknuteSlnko = null;
                // ak bolo kliknute na nejake slnko
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

    /**
     * Prikaze Manazerovi spravovat entitu
     *
     * @param e entita, ktora sa ma spravovat
     */
    public void spravujEntitu(Entita e) {
        this.manazer.prestanSpravovatObjekt(e);
        this.manazer.spravujObjekt(e);
    }

    /**
     * Prikaze Manazerovi spravovat zoznam entit
     *
     * @param entity entity, ktore sa maju spravovat
     */
    public void spravujZoznam(ArrayList<? extends Entita> entity) {
        for (Entita e : entity) {
            this.spravujEntitu(e);
        }
    }

    /**
     * Odstrani entitu z hry (prestane sa spravovat, ostrani sa z prislusneho zoznamu a skryje sa)
     *
     * @param e entita, ktora sa ma odstranit
     */
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

    /**
     * Prida strelu do hry
     *
     * @param s strela, ktora sa ma pridat
     */
    public void pridajStrelu(Strela s) {
        this.strely.add(s);
        this.manazer.spravujObjekt(s);
    }

    /**
     * Prida slnko do hry
     *
     * @param s slnko, ktore sa ma pridat
     */
    public void pridajSlnko(Slnko s) {
        this.slnka.add(s);
        this.manazer.spravujObjekt(s);
    }

    /**
     * Prida zombie do hry
     *
     * @param z zombie, ktory sa ma pridat
     */
    public void pridajZombie(Zombie z) {
        this.zombies.add(z);
        this.manazer.spravujObjekt(z);
    }

    /**
     * Vrati pocet slniecok, ktore ma hrac
     *
     * @return pocet slniecok, ktore ma hrac
     */
    public int getHracoveSlniecka() {
        return this.hracoveSlniecka;
    }

    /**
     * Nacita zombies zo suboru a ulozi ich do zoznamu na pridanie
     */
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

    /**
     * Pridava zombies do hry zo zoznamu na pridanie.
     * Metoda je spravovana Manazerom
     */
    public void tikSekunda() {
        // navysi sa cas a zmeni sa text
        this.cas++;
        this.zmenaTextu();

        // ak existuju nejaki zombies na pridanie
        if (!this.zombiesNaPridanie.isEmpty()) {
            // pomocny zoznam na odstranenie z ArrayList-u this.zombiesNaPridanie
            ArrayList<ZombieData> naOdstranenie = new ArrayList<>();

            // ak sa momentalny cas zhoduje s casom, kedy sa ma zombie pridat, prida sa
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

            // odstranuje sa z ArrayList-u
            for (ZombieData z : naOdstranenie) {
                this.zombiesNaPridanie.remove(z);
            }
        } else {
            // ak nie su ziadni zombies na pridanie a tiez nie su ziadni zombies v hre, hra konci, hrac vyhral
            if (this.zombies.isEmpty()) {
                this.koniecHry(true);
            }
        }
    }

    /**
     * Koniec hry
     *
     * @param vyhra true, ak hrac vyhral, false, ak prehral
     */
    public void koniecHry(boolean vyhra) {
        this.manazer.prestanSpravovatObjekt(this);

        // po skonceni hry sa prestanu spawnovat slnka, ako v original hre
        for (Rastlina r : this.rastliny) {
            if (r instanceof Slnecnica) {
                ((Slnecnica)r).prestanSpawnovatSlnka();
            }
        }

        // zobrazi sa obrazok vyhra, resp. prehra
        Obrazok ukoncenie = new Obrazok("resources/obrazky/vyhra.png", 0, 0);

        if (!vyhra) {
            ukoncenie.zmenObrazok("resources/obrazky/prehra.png");
        }

        ukoncenie.zobraz();
    }

    /**
     * Zmeni cas a pocet zombies v hornom riadku hry
     */
    private void zmenaTextu() {
        this.text.zmenText("Level: " + this.nazovSuboru + "     Zničení zombies: " + this.zombiesZniceni + "/" + this.zombiesCelkovo);
    }

    /**
     * Prida pocet zombies, ktorych treba znicit
     *
     * @param pocet pocet zombies, ktorych treba znicit
     */
    public void pridajZombiesCelkovo(int pocet) {
        this.zombiesCelkovo += pocet;
        this.zmenaTextu();
    }
}
