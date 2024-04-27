package entity.strely;

import entity.rastliny.utociaceRastliny.strielajuceRastliny.StrielajucaRastlina;

public class HrachDvojity extends StrielajucaRastlina {
    public HrachDvojity(int x, int y) {
        super(x, y, "hrachDvojity", 120);
    }

    public void tikAnimacie() {
        super.tikAnimacie(new int[]{40, 60});
    }
}
