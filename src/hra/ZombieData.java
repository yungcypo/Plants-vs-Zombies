package hra;

/**
 * Reprezentuje data potrebne na spawn zombie
 */
public class ZombieData {
    private int x;
    private int y;
    private int delay;
    private int id;

    /**
     * Konstruktor pre ZombieData
     *
     * @param x     x-ova suradnica, na ktorej sa ma zombie spawnut
     * @param y     y-ova suradnica, na ktorej sa ma zombie spawnut
     * @param delay cas, ktory sa ma pockat pred spawnutim zombie
     * @param id    id zombie, ktory sa ma spawnut
     */
    public ZombieData(int x, int y, int delay, int id) {
        this.x = x;
        this.y = y;
        this.delay = delay;
        this.id = id;
    }

    /**
     * Vrati x-ovu suradnicu zombie
     *
     * @return x-ova suradnica zombie
     */
    public int getX() {
        return this.x;
    }

    /**
     * Vrati y-ovu suradnicu zombie
     *
     * @return y-ova suradnica zombie
     */
    public int getY() {
        return this.y;
    }

    /**
     * Vrati cas, ktory sa ma pockat pred spawnutim zombie
     *
     * @return cas, ktory sa ma pockat pred spawnutim zombie
     */
    public int getDelay() {
        return this.delay;
    }

    /**
     * Vrati id zombie, ktory sa ma spawnut
     *
     * @return id zombie, ktory sa ma spawnut
     */
    public int getId() {
        return this.id;
    }
}
