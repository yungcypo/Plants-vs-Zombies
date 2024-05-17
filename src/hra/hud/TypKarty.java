package hra.hud;

/**
 * Enum pre typy kariet
 */
public enum TypKarty {
    SLNECNICA("slnecnica", 50, 5),
    HRACH("hrach", 100, 5),
    HRACH_DVOJITY("hrachDvojity", 200, 5),
    ORECH("orech", 50, 25);
    //HRACH_LADOVY("hrachLadovy", 300, 5),
    //VELKYORECH("velkyorech", 125, 35),
    //POZIERAC_HROBOV("pozieracHrobov", 75, 20),

    private String cesta;
    private int cena;
    private int dlzkaNacitavania;

    /**
     * Konstruktor pre typ karty
     *
     * @param cesta cesta k obrazku karty
     * @param cena  cena karty
     */
    TypKarty(String cesta, int cena, int dlzkaNacitavania) {
        this.cesta = cesta;
        this.cena = cena;
        this.dlzkaNacitavania = dlzkaNacitavania;
    }

    /**
     * Vrati cestu k obrazku karty
     *
     * @return cesta k obrazku karty
     */
    public String getCesta() {
        return "resources/obrazky/karty/" + this.cesta + ".png";
    }

    /**
     * Vrati cenu karty
     *
     * @return cena karty
     */
    public int getCena() {
        return this.cena;
    }

    /**
     * Vrati dlzku nacitavania karty
     *
     * @return dlzka nacitavania karty
     */
    public int getDlzkaNacitavania() {
        return this.dlzkaNacitavania;
    }
}
