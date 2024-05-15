package entity.zombies;

public class ZombieVedro extends Zombie {
    /**
     * Vytvori Zombie
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public ZombieVedro(int x, int y) {
        super(x, y);
        this.zmenAnimaciu("zombieVedro", 240);
        this.setHp(30);
    }

    @Override
    public boolean zasiahni() {
        boolean vysledok = super.zasiahni();

        if (this.getHp() == 12) {
            this.zmenAnimaciu("zombie", 240);
        }

        return vysledok;
    }
}
