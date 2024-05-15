package hra;

import entity.Entita;
import entity.Kosacka;
import entity.rastliny.Rastlina;
import entity.rastliny.utociaceRastliny.strielajuceRastliny.StrielajucaRastlina;
import entity.strely.Strela;
import entity.zombies.Zombie;

import java.util.ArrayList;

public class Kolizie {
    private ArrayList<Zombie> zombies;
    private ArrayList<Strela> strely;
    private ArrayList<Rastlina> rastliny;
    private ArrayList<Kosacka> kosacky;

    public Kolizie(ArrayList<Zombie> zombies, ArrayList<Strela> strely, ArrayList<Rastlina> rastliny, ArrayList<Kosacka> kosacky) {
        this.zombies = zombies;
        this.strely = strely;
        this.rastliny = rastliny;
        this.kosacky = kosacky;
    }

    // detekuje kolizie pri kazdom pohybe
    public void tikPohybu() {
        // ak sa ma nejaka entita vymazat, prida sa do tohto zoznamu a neskor bude vymazana
        ArrayList<Entita> naVymazanie = new ArrayList<>();

        // zisti kolko zombie je v jednotlivych riadkoch pre potreby strielajucich rastlin
        int[] pocetZombieVRiadkoch = new int[5];
        for (Zombie z : this.zombies) {
            pocetZombieVRiadkoch[z.getCisloRiadku()]++;
        }

        for (Zombie z : this.zombies) {
            for (Rastlina r : this.rastliny) {
                // ak su na tom istom riadku
                if (z.getCisloRiadku() == r.getCisloRiadku()) {
                    // osetrenie strielania strielajucich rastlin
                    if (r instanceof StrielajucaRastlina) {
                        if (pocetZombieVRiadkoch[r.getCisloRiadku()] > 0) {
                            ((StrielajucaRastlina)r).setMaStrielat(true);
                        } else {
                            ((StrielajucaRastlina)r).setMaStrielat(false);
                        }
                    }

                    // zombie trochu posunuty blizsie ku rastline, nech to lepsie vyzera
                    if (z.getX() + 20 <= r.getX2() && z.getX2() + 20 >= r.getX()) {
                        r.setJeJedena(true);
                        z.setJeRastlinu(true);

                        if (r.getHp() <= 0) {
                            naVymazanie.add(r);
                            z.setJeRastlinu(false);
                        }
                    }
                }
            }
            for (Strela s : this.strely) {
                // ak su na rovnakom riadku
                if (z.getCisloRiadku() == s.getCisloRiadku()) {
                    // ak sa prekryvaju
                    if (s.getX2() >= z.getX() && s.getX() <= z.getX2()) {
                        // ak zombie zomrel, vymaz ho
                        if (z.zasiahni()) {
                            naVymazanie.add(z);
                            s.nechParentPrestaneStrielat();
                        }

                        // TODO toto nejak nefunguje alebo co :/
                        naVymazanie.add(s);
                    }
                }
            }
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
            this.vymaz(e);
        }
    }

    // vymaze celu entitu ako ju pozname z povrchu zemskeho a celkovej existencie na veky vekov
    public void vymaz(Entita e) {
        e.skry();

        if (e instanceof Zombie) {
            this.zombies.remove(e);
        } else if (e instanceof Strela) {
            this.strely.remove(e);
        } else if (e instanceof Rastlina) {
            this.rastliny.remove(e);
        } else if (e instanceof Kosacka) {
            this.kosacky.remove(e);
        }

        Hra.getHra().prestanSpravovat(e);
    }
}
