package entity.zombies;

/**
 * Reprezentuje Zombie s Kuzelkou na hlave
 */
public class ZombieKuzelka extends ZombieSCiapkou {
    /**
     * Vytvori Zombie s Kuzelkou na hlave
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public ZombieKuzelka(int x, int y) {
        super(x, y, "kuzelka", 240);
        this.setHp(20);
    }

    /**
     * Stara sa o uberanie hp zombie.
     * Vola sa metoda predka, kde sa urcute, ci zombie zomrel.
     * Ak zombie zomrel, vrati true.
     * Ak ma zombie 10 hp, zoberie sa mu kuzelka.
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
     * Taktiez sa zmeni animacia zombie a animacia Kuzelky
     *
     * @param jeRastlinu true ak zombie zerie rastlinu, inak false
     */
    @Override
    public void setJeRastlinu(boolean jeRastlinu) {
        // ak sa zmenil stav jedenia
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
