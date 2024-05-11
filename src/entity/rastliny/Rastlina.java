package entity.rastliny;

import entity.Entita;
import hra.Hra;

/**
 * Reprezentuje rastlinu
 */
public abstract class Rastlina extends Entita {
    private int hp;
    private boolean jeJedena = false; // ci zombie akutalne zerie rastlinu

    // TODO arraylist zombies, ktory jedia rastlinu? mozno by bolo dobre ked zombie je rastlinu a zomrie

    /**
     * Konstruktor pre potomkov triedy
     * //TODO zmenit na suradnice (nie policko) (vyber suradnic bude lahsi)
     *
     * @param polickoX             suradnica x
     * @param polickoY             suradnica y
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi rastline
     * @param pocetObrazokov       pocet obrazkov v animacii
     */
    public Rastlina(int polickoX, int polickoY, String nazovAnimacieObrazku, int pocetObrazokov, int hp) {
        // TODO: zmenit na (podla rozmerov obrazka)
        super(
                polickoX * 100 + 50,
                polickoY * 100 + 50,
                nazovAnimacieObrazku,
                pocetObrazokov
        );
        this.hp = hp;
    }

    public void zjedz() {
        this.hp--;
    }

    public void tikSekunda() {
        if (this.jeJedena) {
            this.zjedz();
        }
    }

    public boolean getJeJedena() {
        return this.jeJedena;
    }

    public void setJeJedena(boolean jeJedena) {
        this.jeJedena = jeJedena;
    }

    public int getHp() {
        return this.hp;
    }

    public int getX2() {
        return this.getX() + 100;
    }

    public int getY2() {
        return this.getY() + 100;
    }
}
