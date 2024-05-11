package hra;

import entity.Kosacka;
import entity.rastliny.Rastlina;
import entity.strely.Strela;
import entity.zombies.Zombie;

import java.util.ArrayList;

public class Kolizie {
    private ArrayList<Zombie> zombies;
    private ArrayList<Strela> strely;
    private ArrayList<Rastlina> rastliny;
    private ArrayList<Kosacka> kosacky;
    private Hra hra;

    public Kolizie(ArrayList<Zombie> zombies, ArrayList<Strela> strely, ArrayList<Rastlina> rastliny, ArrayList<Kosacka> kosacky, Hra hra) {
        this.zombies = zombies;
        this.strely = strely;
        this.rastliny = rastliny;
        this.kosacky = kosacky;
        this.hra = hra;
    }

    // detekuje kolizie pri kazdom pohybe
    public void tikPohybu() {
        this.kolizieStrelyZombies();
    }

    private void kolizieStrelyZombies() {
        for (int i = 0; i <= 4; i++) {
            Strela s = this.getStrelaNajviacVpravo(i);
            Zombie z = this.getZombieNajviacVlavo(i);

            if (s != null && z != null) {
                if (s.getX2() >= z.getX() && s.getX() <= z.getX2()) {
                    if (z.zasiahni()) {
                        z.skry();
                        this.zombies.remove(z);
                        this.hra.prestanSpravovatZombie(z);
                    }
                    s.skry();
                    this.strely.remove(s);
                    this.hra.prestanSpravovatStrelu(s);
                }
            }
        }


    }

    // vrati zombie, ktory je najviac vpravo pre konkretny riadok
    private Zombie getZombieNajviacVlavo(int cisloRiadku) {
        if (this.zombies.isEmpty()) {
            return null;
        }

        ArrayList<Zombie> zombiesVRiadku = new ArrayList<>();  // zombies, ktory sa nachadzaju v danom riadku
        for (Zombie z : this.zombies) {
            // TODO upravit ked bude lepsie vymysleny padding na hraciu plochu
            int zY = (z.getY()) / 100;  // riadok, v ktorom sa nachadza zombie
            if (zY == cisloRiadku) {
                zombiesVRiadku.add(z);
            }
        }

        if (zombiesVRiadku.isEmpty()) {
            return null;
        } else if (zombiesVRiadku.size() == 1) {
            return zombiesVRiadku.getFirst();
        } else {
            Zombie vyslednyZombie = zombiesVRiadku.getFirst();
            for (Zombie z : zombiesVRiadku) {
                if (z.getX() < vyslednyZombie.getX()) {
                    vyslednyZombie = z;
                }
            }

            return vyslednyZombie;
        }
    }

    // vrati strelu, ktora je najviac vpravo pre konkretny riadok
    private Strela getStrelaNajviacVpravo(int cisloRiadku) {
        if (this.strely.isEmpty()) {
            return null;
        }

        ArrayList<Strela> strelyVRiadku = new ArrayList<>();  // strely, ktore sa nachadzaju v danom riadku
        for (Strela s : this.strely) {
            // TODO upravit ked bude lepsie vymysleny padding na hraciu plochu
            int sY = (s.getY() - 50) / 100;  // riadok, ktorom sa nachadza strela
            if (sY == cisloRiadku) {
                strelyVRiadku.add(s);
            }
        }

        if (strelyVRiadku.isEmpty()) {
            return null;
        } else if (strelyVRiadku.size() == 1) {
            return strelyVRiadku.getFirst();
        } else {
            Strela vyslednaStrela = strelyVRiadku.getFirst();
            for (Strela s : strelyVRiadku) {
                if (s.getX() > vyslednaStrela.getX()) {
                    vyslednaStrela = s;
                }
            }

            return vyslednaStrela;
        }
    }


}

