package entity.rastliny;

import hra.Hra;

/**
 * Reprezentuje zemiak
 */
public class Zemiak extends Rastlina {
    /**
     * Vytvori zemiak
     *
     * @param polickoX             suradnica x
     * @param polickoY             suradnica y
     */
    public Zemiak(int polickoX, int polickoY) {
        super(polickoX, polickoY, "zemiak", 120, 5);
    }

    @Override
    public void setJeJedena(boolean jeJedena) {
        super.setJeJedena(jeJedena);

        // vybuchne
        this.zmenAnimaciu("bum", 180);
    }

    @Override
    public void tikAnimacie() {
        super.tikAnimacie();
        if (this.getAktualnyObrazok() >= 179) {
            Hra.getHra().odstranEntitu(this);
        }
    }
}
