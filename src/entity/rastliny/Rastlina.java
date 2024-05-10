package entity.rastliny;

import entity.Entita;
import hra.Hra;

/**
 * Reprezentuje rastlinu
 */
public abstract class Rastlina extends Entita {
    private Hra hra;

    /**
     * Konstruktor pre potomkov triedy
     * //TODO zmenit na suradnice (nie policko) (vyber suradnic bude lahsi)
     * @param polickoX suradnica x
     * @param polickoY suradnica y
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi rastline
     * @param pocetObrazokov pocet obrazkov v animacii
     * @param hra instancia triedy Hra potrebna pre manazovanie objektov vytvaranych rastlinamy (strely, slniecka)
     */
    public Rastlina(int polickoX, int polickoY, String nazovAnimacieObrazku, int pocetObrazokov, Hra hra) {
        // TODO: zmenit na (podla rozmerov obrazka)
        super(
            polickoX * 100 + 50,
            polickoY * 100 + 50,
            nazovAnimacieObrazku,
            pocetObrazokov
        );
        this.hra = hra;
    }

    /**
     * Vrati instanciu triedy Hra
     */
    public Hra getHra() {
        return this.hra;
    }
}
