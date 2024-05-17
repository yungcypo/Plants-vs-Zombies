package entity.zombies;

public class ZombieVedro extends ZombieSCiapkou {
    /**
     * Vytvori Zombie
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public ZombieVedro(int x, int y) {
        super(x, y, "vedro", 240);
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

    @Override
    public void setJeRastlinu(boolean jeRastlinu) {
        // ak sa zmenil stav jedenia rastliny
        if (this.getJeRastlinu() != jeRastlinu) {
            if (jeRastlinu) {
                super.setJeRastlinu(true);
                this.zmenAnimaciuCiapky("zombieJedenie", "vedroJedenie", 120);
            } else {
                super.setJeRastlinu(false);
                this.zmenAnimaciuCiapky("zombie", "vedro", 240);
            }
        }
    }
}
