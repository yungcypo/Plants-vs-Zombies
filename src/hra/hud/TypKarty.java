package hra.hud;

/**
 * Enum pre typy kariet
 */
public enum TypKarty {
    SLNECNICA("slnecnica", 50),
    HRACH("hrach", 100),
    HRACH_DVOJITY("hrachDvojity", 200),
    ORECH("orech", 50);

    private String cesta;
    private int cena;

    /**
     * Konstruktor pre typ karty
     *
     * @param cesta cesta k obrazku karty
     * @param cena cena karty
     */
    TypKarty(String cesta, int cena) {
        this.cesta = cesta;
        this.cena = cena;
    }

    /**
     * Vrati cestu k obrazku karty
     *
     * @return cesta k obrazku karty
     */
    public String getCesta() {
        return "obrazky/karty/" + this.cesta + ".png";
    }

    /**
     * Vrati cenu karty
     *
     * @return cena karty
     */
    public int getCena() {
        return this.cena;
    }
}
