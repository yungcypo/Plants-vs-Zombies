package entity.zombies;

public class ZombieKuzelka extends ZombieSCiapkou {
    /**
     * Vytvori Zombie
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public ZombieKuzelka(int x, int y) {
        super(x, y, "kuzelka", 240);
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

    @Override
    public void setJeRastlinu(boolean jeRastlinu) {
        // ak sa zmenil stav jedenia rastliny
        if (this.getJeRastlinu() != jeRastlinu) {
            if (jeRastlinu) {
                super.setJeRastlinu(true);
                this.zmenAnimaciuCiapky("zombieJedenie", "kuzelkaJedenie", 120);
            } else {
                super.setJeRastlinu(false);
                this.zmenAnimaciuCiapky("zombie", "kuzelka", 240);
            }
        }
    }
}
