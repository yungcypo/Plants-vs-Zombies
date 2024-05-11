package entity.zombies;

import entity.IHybajucaSaEntita;

/**
 * Reprezentuje zakladneho Zombie
 */
public class Zombie extends entity.Entita implements IHybajucaSaEntita {
    private int hp = 10;  // zivoty
    private boolean jeRastlinu = false; // ci zombie akutalne zerie rastlinu

    /**
     * Vytvori Zombia
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
}
