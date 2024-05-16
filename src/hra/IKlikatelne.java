package hra;

/**
 * Interface pre objekty, na ktore sa da kliknut
 */
public interface IKlikatelne {
    int getX();
    int getY();
    int getX2();
    int getY2();
    boolean boloNaMnaKliknute(int x, int y);
}
