package hra;

import entity.Entita;
import entity.Kosacka;
import entity.rastliny.Rastlina;
import entity.rastliny.Zemiak;
import entity.rastliny.strielajuceRastliny.StrielajucaRastlina;
import entity.strely.Strela;
import entity.zombies.Zombie;

import java.util.ArrayList;

/**
 * Trieda, ktora sa stara o kolizie medzi entitami
 */
public class Kolizie {
    private ArrayList<Zombie> zombies;
    private ArrayList<Strela> strely;
    private ArrayList<Rastlina> rastliny;
    private ArrayList<Kosacka> kosacky;

    // TODO nemodifikovatelne arraylisty

    /**
     * Konstruktor pre kolizie.
     *
     * @param zombies  zoznam zombies
     * @param strely   zoznam striel
     * @param rastliny zoznam rastlin
     * @param kosacky  zoznam kosaciek
     */
    public Kolizie(ArrayList<Zombie> zombies, ArrayList<Strela> strely, ArrayList<Rastlina> rastliny, ArrayList<Kosacka> kosacky) {
        this.zombies = zombies;
        this.strely = strely;
        this.rastliny = rastliny;
        this.kosacky = kosacky;
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

                        if (r.getHp() <= 0) {
                            naVymazanie.add(r);
                            z.animaciaJedenia(false);
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

        // TODO toto bude tiez ine pri nemodifikovatelnych arraylistoch
        // vymaz vsetko zo zoznamu na vymazanie
        for (Entita e : naVymazanie) {
            Hra.getHra().odstranEntitu(e);
        }
    }
}
