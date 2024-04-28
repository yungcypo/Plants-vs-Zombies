package entity.zombies;

import entity.IHybajucaSaEntita;

/**
 * Reprezentuje zakladneho Zombie
 */
public class Zombie extends entity.Entita implements IHybajucaSaEntita {
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
        this.posunO(-1, 0);
    }
}
