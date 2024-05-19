package entity.zombies;

import entity.Entita;
import entity.IHybajucaSaEntita;
import fri.shapesge.Obrazok;
import hra.Hra;

/**
 * Reprezentuje zakladneho Zombie
 */
public class Zombie extends Entita implements IHybajucaSaEntita {
    private int hp = 10;  // zivoty
    private boolean jeRastlinu = false; // ci zombie akutalne zerie rastlinu
    private boolean jeZamrazeny = false;
    private int zostavajuciCasZamrazenia = 0;
    private boolean toggleZamrazenia = false;
    private Obrazok ladovec;

    /**
     * Vytvori Zombie
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public Zombie(int x, int y) {
        super(x, y, "zombie", 240);
        this.ladovec = new Obrazok("resources/obrazky/ladovec.png");
    }

    /**
     * Stara sa o pohyb Zombie.
     * Ak zombie prejde za lavy okraj obrazovky, hra sa skonci.
     * Ak je zombie zamrazeny, hybe sa len kazdy druhy tik.
     * Metoda je spravovana Manazerom
     */
    @Override
    public void tikPohybu() {
        // ak je rastlinu, nic sa nestane
        if (!this.jeRastlinu) {
            if (this.jeZamrazeny) {
                this.toggleZamrazenia = !this.toggleZamrazenia;
                if (this.toggleZamrazenia) {
                    return;
                }
            }
            this.posunO(-1, 0);
            this.ladovec.zmenPolohu(this.getX() - 10, this.getY() + 100);
        }

        // ak je zombie uplne vlavo, skonci hru
        if (this.getX() <= 0) {
            Hra.getHra().koniecHry(false);
        }
    }

    /**
     * Stara sa o animacie Zombie.
     * Ak je zombie zamrazeny, hybe sa len kazdy druhy tik.
     * Metoda je spravovana Manazerom
     */
    @Override
    public void tikAnimacie() {
        if (this.jeZamrazeny && this.toggleZamrazenia) {
            return;
        }
        super.tikAnimacie();
    }

    /**
     * Ubera cas zamrazenia o 1 sekundu.
     * Ak uz neostava ziadny cas, zrusi zamrazenie.
     * Metoda je spravovana Manazerom
     */
    public void tikSekunda() {
        if (this.jeZamrazeny) {
            this.zostavajuciCasZamrazenia--;
            if (this.zostavajuciCasZamrazenia == 0) {
                this.jeZamrazeny = false;
                this.ladovec.skry();
            }
        }
    }

    /**
     * Stara sa o uberanie hp zombie. Ak zombie nema hp (zomrie), skryje sa a vrati true
     *
     * @return true ak zombie zomrel, inak false
     */
    public boolean zasiahni() {
        this.hp--;
        if (this.hp == 0) {
            this.skry();
            return true;
        }

        return false;
    }

    /**
     * Vrati hodnotu, ci zombie zerie rastlinu alebo nie
     *
     * @return true ak zombie zerie rastlinu, inak false
     */
    public boolean getJeRastlinu() {
        return this.jeRastlinu;
    }

    /**
     * Urcuje, ci sa ma zmenit animacia na jedenie, resp. z jedenia na normalnu animaciu
     *
     * @param jeRastlinu true ak zombie zerie rastlinu, inak false
     */
    public void animaciaJedenia(boolean jeRastlinu) {
        // ak sa zmenil stav jedenia
        if (this.jeRastlinu != jeRastlinu) {
            if (jeRastlinu) {
                this.jeRastlinu = true;
                this.zmenAnimaciu("zombieJedenie", 120);
            } else {
                this.jeRastlinu = false;
                this.zmenAnimaciu("zombie", 240);
            }
        }
    }

    /**
     * Nastavi, ci zombie aktualne zerie rastlinu alebo nie.
     *
     * @param jeRastlinu true ak zombie zerie rastlinu, inak false
     */
    public void setJeRastlinu(boolean jeRastlinu) {
        this.jeRastlinu = jeRastlinu;
    }

    /**
     * Zombie-mu sa nastavi hp na 0 a skryje sa.
     * Metoda sa zavola, ked kosacka zrazi zombie
     */
    public void zraz() {
        this.hp = 0;
        this.skry();
    }

    /**
     * Vrati hp zombie
     *
     * @return hp zombie
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * Nastavi hp zombie
     *
     * @param hp hp zombie
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Vrati x-ovu suradnicu praveho okraja zombie
     *
     * @return x-ova suradnica praveho okraja zombie
     */
    public int getX2() {
        return this.getX() + 75;
    }

    /**
     * Vrati y-ovu suradnicu dolneho okraja zombie
     *
     * @return y-ova suradnica dolneho okraja zombie
     */
    public int getY2() {
        return this.getY() + 150;
    }

    /**
     * Nastavi cas, ako dlho ma byt zombie este zamrazeny
     *
     * @param cas kolko sekund ma byt zombie zamrazeny
     */
    public void setZostavajuciCasZamrazenia(int cas) {
        this.zostavajuciCasZamrazenia = cas;
        this.jeZamrazeny = true;
        this.ladovec.zobraz();
    }

    /**
     * Skryje zombie a jeho ladovec
     */
    public void skry() {
        super.skry();
        this.ladovec.skry();
    }
}
