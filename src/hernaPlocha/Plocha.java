package hernaPlocha;

public class Plocha {
    private Riadok[] riadky;

    public Plocha() {
        this.riadky = new Riadok[5];

        for (int i = 0; i < 5; i++) {
            this.riadky[i] = new Riadok(i * 100 + 50);
        }
    }
}
