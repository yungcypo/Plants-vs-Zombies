package entity.zombies;

/**
 * Reprezentuje Zombie s Vedrom na hlave
 */
public class ZombieVedro extends ZombieSCiapkou {
    /**
     * Vytvori Zombie s Vedrom na hlave
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public ZombieVedro(int x, int y) {
        super(x, y, "vedro", 240);
        this.setHp(30);
    }

    /**
     * Stara sa o uberanie hp zombie.
     * Ak zombie zomrel, vrati true.
     * Ak ma zombie 10 hp, zoberie sa mu vedro.
     *
     * @return true, ak zombie zomrel, inak false
     */
    @Override
    public boolean zasiahni() {
        boolean vysledok = super.zasiahni();

        if (this.getHp() == 10) {
            this.setMaCiapku(false);
        }

        return vysledok;
    }

    /**
     * Nastavi, ci zombie aktualne zerie rastlinu alebo nie.
     * Taktiez sa zmeni animacia zombie pomocou metody predka, a animacia Kuzelky
     *
     * @param jeRastlinu true ak zombie zerie rastlinu, inak false
     */
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
