package entity.zombies;

/**
 * Reprezentuje vedlajsieho Zombie tanecnika
 */
public class ZombieVedlajsiTanecnik extends Zombie {
    /**
     * Vytvori ZombieVedlajsiTanecnik
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public ZombieVedlajsiTanecnik(int x, int y) {
        super(x, y);
        this.zmenAnimaciu("zombieVedlajsiTanecnik", 240);
    }


    /**
     * Nastavi, ci zombie aktualne zerie rastlinu alebo nie.
     * Taktiez sa zmeni animacia
     *
     * @param jeRastlinu true ak zombie zerie rastlinu, inak false
     */
    @Override
    public void animaciaJedenia(boolean jeRastlinu) {
        // ak sa zmenil stav jedenia
        if (this.getJeRastlinu() != jeRastlinu) {
            if (jeRastlinu) {
                this.zmenAnimaciu("zombieVedlajsiTanecnikJedenie", 120);
                this.setJeRastlinu(true);
            } else {
                this.zmenAnimaciu("zombieVedlajsiTanecnik", 240);
                this.setJeRastlinu(false);
            }
        }
    }
}
