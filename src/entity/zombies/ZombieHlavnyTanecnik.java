package entity.zombies;

import hra.Hra;

/**
 * Reprezentuje hlavneho Zombie tanecnika
 */
public class ZombieHlavnyTanecnik extends Zombie {
    /**
     * Vytvori ZombieHlavnyTanecnik
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public ZombieHlavnyTanecnik(int x, int y) {
        super(x, y);
        this.zmenAnimaciu("zombieHlavnyTanecnik", 240);
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
                this.zmenAnimaciu("zombieHlavnyTanecnikJedenie", 120);
                this.setJeRastlinu(true);
            } else {
                this.zmenAnimaciu("zombieHlavnyTanecnik", 240);
                this.setJeRastlinu(false);
            }
        }
    }

    @Override
    public void tikPohybu() {
        super.tikPohybu();
        if (this.getX() == 800 || this.getX() == 500) {
            this.zjavVedlajsichTanecnikov();
        }
    }

    private void zjavVedlajsichTanecnikov() {
        int pridaniZombies = 2;

        Hra.getHra().pridajZombie(new ZombieVedlajsiTanecnik(this.getX() - 100, this.getY()));
        Hra.getHra().pridajZombie(new ZombieVedlajsiTanecnik(this.getX() + 100, this.getY()));

        if (this.getCisloRiadku() != 0) {
            Hra.getHra().pridajZombie(new ZombieVedlajsiTanecnik(this.getX(), this.getY() - 100));
            pridaniZombies++;
        }

        if (this.getCisloRiadku() != 4) {
            Hra.getHra().pridajZombie(new ZombieVedlajsiTanecnik(this.getX(), this.getY() + 100));
            pridaniZombies++;
        }
        Hra.getHra().pridajZombiesCelkovo(pridaniZombies);
    }
}
