package entity.rastliny.neutociaceRastliny;

import entity.rastliny.Rastlina;

/**
 * Reprezentuje neutociacu rastlinu
 */
public abstract class NeutociacaRastlina extends Rastlina {
    /**
     * Konstruktor pre potomkov triedy
     * //TODO zmenit na suradnice (nie policko) (vyber suradnic bude lahsi)
     * @param polickoX suradnica x
     * @param polickoY suradnica y
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi rastline
     * @param pocetObrazokov pocet obrazkov v animacii
     */
    public NeutociacaRastlina(int polickoX, int polickoY, String nazovAnimacieObrazku, int pocetObrazokov, int hp) {
        super(polickoX, polickoY, nazovAnimacieObrazku, pocetObrazokov, hp);
    }
}
