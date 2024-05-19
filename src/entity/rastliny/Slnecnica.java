package entity.rastliny;

import entity.Slnko;
import hra.Hra;

import java.util.Random;

/**
 * Reprezentuje neutociacu rastlinu Slnecnica
 */
public class Slnecnica extends Rastlina {
    private int casOdSpawnu = 0;
    private boolean maSpawnowatSlnka = true;
    private int casPotrebnyNaSpawnSlnka;
    private Random random = new Random();

    /**
     * Vytvori Slnecnicu
     *
     * @param x suradnica x
     * @param y suradnica y
     */
    public Slnecnica(int x, int y) {
        super(x, y, "slnecnica", 240, 6);
        this.zmenCasPotrebnyNaSpawnSlnka();
    }

    /**
     * Stara sa o vytvaranie instancii triedy Slnko.
     * Vytvara Slnko kazdych 12 - 20 sekund.
     * Metoda je spravovana Manazerom
     */
    public void tikSekunda() {
        super.tikSekunda();
        this.casOdSpawnu += 1;
        if (this.casOdSpawnu == this.casPotrebnyNaSpawnSlnka && this.maSpawnowatSlnka) {
            Hra.getHra().pridajSlnko(new Slnko(this.getX() + 25, this.getY() - 10));
            this.zmenCasPotrebnyNaSpawnSlnka();
        }
    }

    /**
     * Zmeni cas potrebny na spawn slnka na nahodnu hodnotu od 12 do 20
     */
    private void zmenCasPotrebnyNaSpawnSlnka() {
        this.casPotrebnyNaSpawnSlnka += this.random.nextInt(12, 20);
    }

    /**
     * Prestane spawnovat slnka.
     * Pouziva sa vtedy, ked skonci hra
     */
    public void prestanSpawnovatSlnka() {
        this.maSpawnowatSlnka = false;
    }
}
