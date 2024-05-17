package entity.zombies;

import entity.Entita;
import entity.IHybajucaSaEntita;
import hra.Hra;

/**
 * Reprezentuje zakladneho Zombie
 */
public class Zombie extends Entita implements IHybajucaSaEntita {
    private int hp = 10;  // zivoty
    private boolean jeRastlinu = false; // ci zombie akutalne zerie rastlinu

    /**
     * Vytvori Zombie
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public Zombie(int x, int y) {
        super(x, y, "zombie", 240);
    }

    /**
     * Stara sa o pohyb Zombie. Ak zombie prejde za lavy okraj obrazovky, hra sa skonci
     */
    @Override
    public void tikPohybu() {
        if (!this.jeRastlinu) {
            this.posunO(-1, 0);
        }
        if (this.getX() <= 0) {
            Hra.getHra().koniecHry(false);
        }
    }

    /**
     * Stara sa o uberanie hp zombie. Ak zombie nema hp (t. j. zomrie), skryje sa a vrati true
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
     * Nastavi, ci zombie aktualne zerie rastlinu alebo nie.
     * Taktiez sa zmeni animacia
     *
     * @param jeRastlinu true ak zombie zerie rastlinu, inak false
     */
    public void setJeRastlinu(boolean jeRastlinu) {
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
     * Metoda sa zavola, ked kosacka zrazi zombie. Zombie-mu sa nastavi hp na 0 a skryje sa
     */
    public void zraz() {
        // TODO mozno nejaka animacia na zrazenie?
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
}
