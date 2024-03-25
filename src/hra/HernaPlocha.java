package hra;

public class HernaPlocha {
    private Riadok[] riadky;

    public HernaPlocha() {
        this.riadky = new Riadok[5];

        for (int i = 0; i < 5; i++) {
            this.riadky[i] = new Riadok(i * 100 + 50);
        }
    }
}
