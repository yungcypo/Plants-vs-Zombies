package entity.zombies;

public class Zombie extends entity.Entita implements entity.HybajucaSaEntita {
    public Zombie(int x, int y) {
        super(x, y, "zombie", 240);
    }

    @Override
    public void tikPohybu() {
        this.posunO(-1, 0);
    }
}
