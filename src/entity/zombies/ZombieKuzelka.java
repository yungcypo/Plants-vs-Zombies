package entity.zombies;

public class ZombieKuzelka extends Zombie {
    /**
     * Vytvori Zombie
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public ZombieKuzelka(int x, int y) {
        super(x, y);
        this.zmenAnimaciu("zombieKuzelka", 240);
        this.setHp(20);
    }

    @Override
    public boolean zasiahni() {
        boolean vysledok = super.zasiahni();

        if (this.getHp() == 10) {
            this.zmenAnimaciu("zombie", 240);
        }

        return vysledok;
    }
}
