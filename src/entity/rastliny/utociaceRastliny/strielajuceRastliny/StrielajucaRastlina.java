package entity.rastliny.utociaceRastliny.strielajuceRastliny;

import entity.strely.Hrach;
import entity.strely.Strela;
import fri.shapesge.Manazer;

import java.util.ArrayList;

public abstract class StrielajucaRastlina extends entity.rastliny.Rastlina {
    private ArrayList<Strela> strely;
    private Manazer manazer;

    public StrielajucaRastlina(int x, int y, String nazovAnimacieObrazku, int pocetObrazokov, Manazer manazer) {
        super(x, y, nazovAnimacieObrazku, pocetObrazokov);
        this.strely = new ArrayList<Strela>();
        this.manazer = manazer;
    }

    public void vystrel(int cisloObrazku) {
        if (this.jeObrazokCislo(cisloObrazku)) {
            this.strely.add(new Hrach(this.getX() + 100, this.getY() + 20, this.strely, this.manazer));
        }
    }


    public void tikAnimacie(int cisloObrazku) {
        super.tikAnimacie();
        this.vystrel(cisloObrazku);
    }

    public void tikAnimacie(int[] cisloObrazku) {
        super.tikAnimacie();
        for (int c : cisloObrazku) {
            this.vystrel(c);
        }
    }
}
