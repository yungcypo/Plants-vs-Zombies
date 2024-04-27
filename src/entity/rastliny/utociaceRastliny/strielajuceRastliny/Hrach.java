package entity.rastliny.utociaceRastliny.strielajuceRastliny;

import fri.shapesge.Manazer;

public class Hrach extends StrielajucaRastlina {
    public Hrach(int x, int y, Manazer manazer) {
        super(x, y, "hrach", 120, manazer);
    }

    public void tikAnimacie() {
        super.tikAnimacie(40);
    }
}
