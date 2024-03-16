package entity.zombies;

import fri.shapesge.Obdlznik;

public class Zombie {
    private int x;
    private int y;
    private Obdlznik stvorec;

    public Zombie(int x, int y) {
        this.x = x;
        this.y = y;
        this.stvorec = new Obdlznik(this.x, this.y);

        this.stvorec.zmenStrany(60, 110);
        this.stvorec.zmenFarbu("#000000");
        this.stvorec.zobraz();
    }

    public void posun() {
        this.stvorec.posunVodorovne(-1);
    }
}
