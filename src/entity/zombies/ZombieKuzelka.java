package entity.zombies;

public class ZombieKuzelka extends ZombieSCiapkou {
    /**
     * Vytvori Zombie
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public ZombieKuzelka(int x, int y) {
        super(x, y, "kuzelka");
        this.setHp(20);
    }

    @Override
    public boolean zasiahni() {
        boolean vysledok = super.zasiahni();

        if (this.getHp() == 10) {
            this.setMaCiapku(false);
        }

        return vysledok;
    }
}
