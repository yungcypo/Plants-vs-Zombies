package hra;

import entity.Entita;
import entity.Kosacka;
import entity.rastliny.Rastlina;
import entity.rastliny.Zemiak;
import entity.rastliny.strielajuceRastliny.StrielajucaRastlina;
import entity.strely.HrachLadovy;
import entity.strely.Strela;
import entity.zombies.Zombie;
import hra.plocha.HernaPlocha;

import java.util.ArrayList;
import java.util.List;

/**
 * Trieda, ktora sa stara o kolizie medzi entitami
 */
public class Kolizie {
    private List<Zombie> zombies;
    private List<Strela> strely;
    private List<Rastlina> rastliny;
    private List<Kosacka> kosacky;
    private HernaPlocha hernaPlocha;

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
     * Metoda, ktora sa stara o kolizie medzi entitami.
     * Metoda sa vola pri kazdom pohybe entit.
     */
    public void tikPohybu() {
        // ak sa ma nejaka entita vymazat, prida sa do tohto zoznamu a neskor bude vymazana
        ArrayList<Entita> naVymazanie = new ArrayList<>();

        // zisti kolko zombie je v jednotlivych riadkoch pre potreby strielajucich rastlin
        int[] pocetZombieVRiadkoch = new int[5];
        for (Zombie z : this.zombies) {
            pocetZombieVRiadkoch[z.getCisloRiadku()]++;
        }

        for (Zombie z : this.zombies) {
            // jedenie rastlin
            for (Rastlina r : this.rastliny) {
                // ak su na tom istom riadku
                if (z.getCisloRiadku() == r.getCisloRiadku()) {
                    // strielajuce rastliny strielaju iba vtedy, ked su na tom istom riadku ako nejaky zombie
                    if (r instanceof StrielajucaRastlina) {
                        if (pocetZombieVRiadkoch[r.getCisloRiadku()] > 0) {
                            ((StrielajucaRastlina)r).setMaStrielat(true);
                        } else {
                            ((StrielajucaRastlina)r).setMaStrielat(false);
                        }
                    }

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

                        // ak zombie jedol zemiak, vymaz ho
                        if (r instanceof Zemiak) {
                            naVymazanie.add(z);
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

        // vymaz vsetko zo zoznamu na vymazanie
        for (Entita e : naVymazanie) {
            Hra.getHra().odstranEntitu(e);
        }
    }
}
