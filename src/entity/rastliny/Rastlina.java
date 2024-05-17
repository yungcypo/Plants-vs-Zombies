package entity.rastliny;

import entity.Entita;

/**
 * Reprezentuje rastlinu
 */
public abstract class Rastlina extends Entita {
    private int hp;
    private boolean jeJedena = false; // ci zombie akutalne zerie rastlinu

    /**
     * Konstruktor pre potomkov triedy
     *
     * @param polickoX             suradnica x
     * @param polickoY             suradnica y
     * @param nazovAnimacieObrazku cesta ku priecinku s obrazkami prisluchajucimi rastline
     * @param pocetObrazokov       pocet obrazkov v animacii
     * @param hp                   pocet zivotov rastliny
     */
    public Rastlina(int polickoX, int polickoY, String nazovAnimacieObrazku, int pocetObrazokov, int hp) {
        super(
                polickoX * 100 + 50,
                polickoY * 100 + 50,
                nazovAnimacieObrazku,
                pocetObrazokov
        );
        this.hp = hp;
    }

    /**
     * Znizi hp rastliny o 1
     */
    public void zjedz() {
        this.hp--;
    }

    /**
     * Metoda sa vola kazdu sekundu a zistuje, ci sa ma rastline uberat hp (ci je jedena). Ak ano, tak ho uberie
     */
    public void tikSekunda() {
        if (this.jeJedena) {
            this.zjedz();
        }
    }

    /**
     * Nastavi, ci ma byt rastlina jedena
     *
     * @param jeJedena true, ak ma byt jedena, inak false
     */
    public void setJeJedena(boolean jeJedena) {
        this.jeJedena = jeJedena;
    }

    /**
     * Vrati pocet zivotov, ktore ostavaju rastline
     *
     * @return pocet zivotor
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * Vrati x-ovu suradnicu praveho okraja rastliny
     *
     * @return x-ova suradnica praveho okraja rastliny
     */
    public int getX2() {
        return this.getX() + 100;
    }

    /**
     * Vrati y-ovu suradnicu dolneho okraja rastliny
     *
     * @return y-ova suradnica dolneho okraja rastliny
     */
    public int getY2() {
        return this.getY() + 100;
    }
}
