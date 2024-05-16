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
     * Stara sa o pohyb Zombie
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

    public int getX2() {
        return this.getX() + 75;
    }

    public int getY2() {
        return this.getY() + 150;
    }

    // vrati true ak zombie zomrel
    public boolean zasiahni() {
        this.hp--;
        if (this.hp == 0) {
            this.skry();
            return true;
        }

        return false;
    }

    public void setJeRastlinu(boolean jeRastlinu) {
        this.jeRastlinu = jeRastlinu;
    }

    public void zraz() {
        // TODO mozno nejaka animacia na zrazenie?
        this.hp = 0;
        this.skry();
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
