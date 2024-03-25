package hra;

import fri.shapesge.Stvorec;

public class Policko {
    private Stvorec stvorec;
    private int x;
    private int y;
    private String farba;

    public Policko(int x, int y, FarbyPolicka farba) {
        this.x = x;
        this.y = y;
        this.stvorec = new Stvorec(this.x, this.y);

        this.stvorec.zmenStranu(100);

        switch (farba) {
            case FarbyPolicka.ZELENA1:
                this.farba = "#6ebe45";
                break;
            case FarbyPolicka.ZELENA2:
                this.farba = "#60BC45";
                break;
            case FarbyPolicka.ZELENA3:
                this.farba = "#53ba47";
                break;

        }
        this.stvorec.zmenFarbu(this.farba);

        this.stvorec.zobraz();
    }
}
