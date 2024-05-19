package hra;

import entity.Entita;
import entity.Kosacka;
import entity.rastliny.Rastlina;
import entity.rastliny.Zemiak;
import entity.rastliny.strielajuceRastliny.StrielajucaRastlina;
import entity.strely.HrachLadovy;
import entity.strely.Strela;
import entity.zombies.Zombie;
import entity.zombies.ZombieSCiapkou;
import hra.plocha.HernaPlocha;

import java.util.ArrayList;
import java.util.List;

/**
 * Trieda, ktora sa stara o kolizie medzi entitami
 */
public class Kolizie {
    private final List<Zombie> zombies;
    private final List<Strela> strely;
    private final List<Rastlina> rastliny;
    private final List<Kosacka> kosacky;
    private final HernaPlocha hernaPlocha;

    /**
     * Konstruktor pre kolizie.
     *
     * @param zombies  zoznam zombies
     * @param strely   zoznam striel
     * @param rastliny zoznam rastlin
     * @param kosacky  zoznam kosaciek
     */
    public Kolizie(List<Zombie> zombies, List<Strela> strely, List<Rastlina> rastliny, List<Kosacka> kosacky, HernaPlocha hernaPlocha) {
        this.zombies = zombies;
        this.strely = strely;
        this.rastliny = rastliny;
        this.kosacky = kosacky;
        this.hernaPlocha = hernaPlocha;
    }

    /**
     * Stara sa o kolizie medzi entitami.
     * Metoda je spravovana Manazerom
     */
    public void tikPohybu() {
        // ak nie su ziadni zombies, nic sa nevykona
        if (this.zombies.isEmpty()) {
            return;
        }

        // ak sa ma nejaka entita vymazat, prida sa do tohto zoznamu a neskor bude vymazana
        ArrayList<Entita> naVymazanie = new ArrayList<>();

        for (Zombie z : this.zombies) {
            // jedenie rastlin
            for (Rastlina r : this.rastliny) {
                // ak su na tom istom riadku a zombie uz je viditelny
                if (z.getCisloRiadku() == r.getCisloRiadku()) {
                    // ak sa prekryvaju, zombie zacne jest rastlinu
                    if (z.getX() + 20 <= r.getX2() && z.getX2() + 20 >= r.getX() + 50) {
                        z.animaciaJedenia(true);
                        r.setJeJedena(true);

                        // ak je raslina zjedena, vymaz ju a uvolni policko
                        if (r.getHp() <= 0) {
                            z.animaciaJedenia(false);
                            naVymazanie.add(r);

                            int x = r.getX() / 100;
                            int y = r.getY() / 100;

                            this.hernaPlocha.nastavObsadeniePolicka(x, y, false);
                        }

                        // ak zombie jedol zemiak a zemiak nie je vybuchnuty
                        if (r instanceof Zemiak) {
                            // vymaz zombie, uvolni policko kde bol zemiak
                            naVymazanie.add(z);
                            z.animaciaJedenia(false);
                            this.hernaPlocha.nastavObsadeniePolicka(r.getX() / 100, r.getY() / 100, false);
                        }
                    }
                }
            }

            // zasah strely do zombie
            for (Strela s : this.strely) {
                // ak su na rovnakom riadku
                if (z.getCisloRiadku() == s.getCisloRiadku()) {
                    // ak sa prekryvaju
                    if (s.getX2() >= z.getX() + 10 && s.getX() <= z.getX2() + 10) {
                        naVymazanie.add(s);
                        // ak zombie zomrel, vymaz ho; rastlina prestane strielat
                        if (z.zasiahni()) {
                            naVymazanie.add(z);
                            s.nechParentPrestaneStrielat();
                        }
                        if (s instanceof HrachLadovy) {
                            z.setZostavajuciCasZamrazenia(5);
                        }
                    }
                }
            }

            // ak zombie prisiel az ku kosacke
            for (Kosacka k : this.kosacky) {
                // ak su na tom istom riadku
                if (z.getCisloRiadku() == k.getCisloRiadku()) {
                    // ak sa prekryvaju
                    if (z.getX() <= k.getX2() && z.getX2() >= k.getX()) {
                        if (!k.getZapnuta()) {
                            k.zapni();
                        }
                        z.zraz();
                        naVymazanie.add(z);
                    }
                }
            }
        }


        // zisti kolko zombie je v jednotlivych riadkoch
        int[] pocetZombieVRiadkoch = new int[5];
        for (Zombie z : this.zombies) {
            pocetZombieVRiadkoch[z.getCisloRiadku()]++;
        }

        // ak je v riadku strielajuca rastlina bez zombie, prestane strielat
        for (Rastlina r : this.rastliny) {
            if (r instanceof StrielajucaRastlina) {
                ((StrielajucaRastlina)r).setMaStrielat(pocetZombieVRiadkoch[r.getCisloRiadku()] > 0);
            }
        }

        // vymaz vsetko zo zoznamu na vymazanie
        for (Entita e : naVymazanie) {
            Hra.getHra().odstranEntitu(e);

            // ak to bol zombie s ciapkou, skry mu ciapku
            if (e instanceof ZombieSCiapkou) {
                ((ZombieSCiapkou)e).skryCiapku();
            }
        }
    }
}
