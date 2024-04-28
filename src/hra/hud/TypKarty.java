package hra.hud;

public enum TypKarty {
    SLNECNICA("slnecnica", 50),
    HRACH("hrach", 100),
    HRACH_DVOJITY("hrachDvojity", 200);

    private String cesta;
    private int cena;

    TypKarty(String cesta, int cena) {
        this.cesta = cesta;
        this.cena = cena;
    }

    public String getCesta() {
        return "obrazky/karty/" + this.cesta + ".png";
    }

    public int getCena() {
        return this.cena;
    }
}
