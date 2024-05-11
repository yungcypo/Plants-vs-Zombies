package hra;

import entity.Entita;
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
        this.kolizieZombieRastliny();
        this.kolizieZombieKosacky();
    }

    private void kolizieStrelyZombies() {
        for (int i = 0; i <= 4; i++) {
            Strela s = this.getStrelaNajviacVpravo(i);
            Zombie z = this.getZombieNajviacVlavo(i);

            if (s != null && z != null) {
                if (s.getX2() >= z.getX() && s.getX() <= z.getX2()) {
                    if (z.zasiahni()) {
                        this.vymaz(z);
                    }
                    this.vymaz(s);
                }
            }
        }


    }

    private void kolizieZombieRastliny() {
        for (int i = 0; i < 5; i++) {
            Zombie z = this.getZombieNajviacVlavo(i);
            Rastlina r = this.getRastlinaNajviacVpravo(i);

            if (z != null && r != null) {
                // zombie trochu posunuty blizsie ku rastline, nech to lepsie vyzera
                if (z.getX() + 20 <= r.getX2() && z.getX2() + 20 >= r.getX()) {
                    r.setJeJedena(true);
                    z.setJeRastlinu(true);

                    if (r.getHp() <= 0) {
                        this.vymaz(r);
                        z.setJeRastlinu(false);
                    }
                }
            }
        }
    }

    private void kolizieZombieKosacky() {
        for (int i = 0; i < 5; i++) {
            Zombie z = this.getZombieNajviacVlavo(i);
            Kosacka k = this.kosacky.get(i);

            if (z != null) {
                if (z.getX() <= k.getX2() && z.getX2() >= k.getX()) {
                    if (!k.getZapnuta()) {
                        k.zapni();
                    }
                    z.zraz();
                    this.vymaz(z);
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

    // vrati rasltinu, ktora je najviac vpravo pre kontkretny riadok
    private Rastlina getRastlinaNajviacVpravo(int cisloRiadku) {
        if (this.rastliny.isEmpty()) {
            return null;
        }

        ArrayList<Rastlina> rastlinyVRiadku = new ArrayList<>();  // rastliny, ktore sa nachadzaju v danom riadku
        for (Rastlina r : this.rastliny) {
            // TODO upravit ked bude lepsie vymysleny padding na hraciu plochu
            int rY = (r.getY() - 50) / 100;  // riadok, ktorom sa nachadza rastlina
            if (rY == cisloRiadku) {
                rastlinyVRiadku.add(r);
            }
        }

        if (rastlinyVRiadku.isEmpty()) {
            return null;
        } else if (rastlinyVRiadku.size() == 1) {
            return rastlinyVRiadku.getFirst();
        } else {
            Rastlina vyslednaRastlina = rastlinyVRiadku.getFirst();
            for (Rastlina r : rastlinyVRiadku) {
                if (r.getX() > vyslednaRastlina.getX()) {
                    vyslednaRastlina = r;
                }
            }

            return vyslednaRastlina;
        }
    }

    // vymaze celu entitu ako ju pozname z povrchu zemskeho a celkovej existencie
    public void vymaz(Entita e) {
        e.skry();

        switch (e) {
            case Zombie zombie -> this.zombies.remove(e);
            case Strela strela -> this.strely.remove(e);
            case Rastlina rastlina -> this.rastliny.remove(e);
            case Kosacka kosacka -> this.kosacky.remove(e);
            default -> {
            }
        }

        this.hra.prestanSpravovat(e);
    }
}
