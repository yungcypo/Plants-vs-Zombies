package entity.rastliny;

import hra.Hra;

/**
 * Reprezentuje zemiak
 */
public class Zemiak extends Rastlina {
    private boolean jeVybuchnuty = false;
    /**
     * Vytvori zemiak
     *
     * @param polickoX             suradnica x
     * @param polickoY             suradnica y
     */
    public Zemiak(int polickoX, int polickoY) {
        super(polickoX, polickoY, "zemiak", 120, 1);
    }

    /**
     * Zmeni animaciu na vybuch, ked zombie zacne jest zemiak
     */
    @Override
    public void setJeJedena(boolean jeJedena) {
        super.setJeJedena(jeJedena);

        // vybuchne
        this.zmenAnimaciu("bum", 240);
        this.jeVybuchnuty = true;
    }

    /**
     * Stara sa o animaciu zemiaku. Ak zemiak vybuchne, tak sa odstrani.
     * Metoda je spravovana Manazerom
     */
    @Override
    public void tikAnimacie() {
        super.tikAnimacie();
        if (this.getAktualnyObrazok() >= 179) {
            Hra.getHra().odstranEntitu(this);
        }
    }
}
