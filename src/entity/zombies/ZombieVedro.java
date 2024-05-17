package entity.zombies;

public class ZombieVedro extends ZombieSCiapkou {
    /**
     * Vytvori Zombie
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public ZombieVedro(int x, int y) {
        super(x, y, "vedro");
        this.setHp(30);
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
