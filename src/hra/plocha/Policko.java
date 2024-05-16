package hra.plocha;

import fri.shapesge.Stvorec;

/**
 * Reprezentuje jednotlive policka na hracej ploche
 */
public class Policko {
    private Stvorec stvorec;
    private int x;
    private int y;
    private String farba;

    /**
     * Konstruktor triedy Policko
     *
     * @param x suradnica x
     * @param y suradnica y
     * @param farbaPolicka farba policka z enum-u FarbyPolicka
     */
    public Policko(int x, int y, FarbaPolicka farbaPolicka) {
        this.x = x;
        this.y = y;
        this.stvorec = new Stvorec(this.x, this.y);

        this.stvorec.zmenStranu(100);

        this.farba = farbaPolicka.getFarba();
        this.stvorec.zmenFarbu(this.farba);

        this.stvorec.zobraz();
    }
}
