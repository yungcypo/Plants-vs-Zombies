package hra;

public class ZombieData {
    private int x;
    private int y;
    private int delay;
    private int id;

    public ZombieData(int x, int y, int delay, int id) {
        this.x = x;
        this.y = y;
        this.delay = delay;
        this.id = id;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getDelay() {
        return this.delay;
    }

    public int getId() {
        return this.id;
    }
}
