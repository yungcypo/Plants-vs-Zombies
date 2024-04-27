package entity.strely;

import fri.shapesge.Manazer;

import java.util.ArrayList;

public class Hrach extends Strela {
    private ArrayList<Strela> strely;

    public Hrach(int x, int y, ArrayList<Strela> strely, Manazer manazer) {
        super(x, y, "strelaHrach", 1, strely, manazer);
    }
}
