package entity.strely;

import entity.rastliny.utociaceRastliny.strielajuceRastliny.StrielajucaRastlina;
import fri.shapesge.Manazer;

public class HrachDvojity extends StrielajucaRastlina {
    public HrachDvojity(int x, int y, Manazer manazer) {
        super(x, y, "hrachDvojity", 120, manazer);
    }

    public void tikAnimacie() {
        super.tikAnimacie(new int[]{40, 65});
    }
}
