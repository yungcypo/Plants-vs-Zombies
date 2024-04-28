package entity.rastliny;

import entity.Entita;

/**
 * Reprezentuje rastlinu
 */
public abstract class Rastlina extends Entita {
    /**
     * Konstruktor pre potomkov triedy
     * //TODO zmenit na suradnice (nie policko) (vyber suradnic bude lahsi)
     * @param polickoX suradnica x
     * @param polickoY suradnica y
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi rastline
     * @param pocetObrazokov pocet obrazkov v animacii
     */
    public Rastlina(int polickoX, int polickoY, String nazovAnimacieObrazku, int pocetObrazokov) {
        // TODO: zmenit na (podla rozmerov obrazka)
        super(
            polickoX * 100 + 50,
            polickoY * 100 + 50,
            nazovAnimacieObrazku,
            pocetObrazokov
        );
    }
}
