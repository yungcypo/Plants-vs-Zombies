package entity;

public class Kosacka extends Entita implements HybajucaSaEntita {
    private boolean zapnuta = false;

    public Kosacka(int x, int y) {
        super(x, y, "kosackaVypnuta", 120);
    }

    @Override
    public void tikPohybu() {
        if (this.zapnuta) {
            if (this.getX() < 1100) {
                this.posunO(5, 0);
            } else {
                this.skry();
            }
        }
    }

    public void zapni() {
        this.zapnuta = true;
        this.setNazovAnimacieObrazku("kosackaZapnuta");
        this.setPocetObrazkov(30);
    }
}
