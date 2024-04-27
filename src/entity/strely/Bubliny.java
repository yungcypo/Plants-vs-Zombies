package entity.strely;

import fri.shapesge.Manazer;

import java.util.ArrayList;

public class Bubliny extends Strela {
    public Bubliny(int x, int y, ArrayList<Strela> strely, Manazer manazer) {
        super(x, y, "strely/bubliny", 4, strely, manazer);
    }
}
