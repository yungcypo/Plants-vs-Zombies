package hra.plocha;

/**
 * Reprezentuje farby policok
 */
public enum FarbaPolicka {
    ZELENA1("#6ebe45"),
    ZELENA2("#60bc45"),
    ZELENA3("#53ba47");

    private final String farba;

    FarbaPolicka(String farba) {
        this.farba = farba;
    }

    public String getFarba() {
        return this.farba;
    }
}
