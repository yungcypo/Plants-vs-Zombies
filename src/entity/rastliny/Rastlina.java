package entity.rastliny;

import entity.Entita;

public abstract class Rastlina extends Entita {
    public Rastlina(int polickoX, int polickoY, String nazovAnimacieObrazku, int pocetObrazokov) {
        super(
            // TODO: zmenit na podla rozmerov obrazka
            polickoX * 100 + 50,
            polickoY * 100 + 50,
            nazovAnimacieObrazku,
            pocetObrazokov
        );
    }
}
