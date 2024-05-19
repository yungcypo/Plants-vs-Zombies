package hra;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Trieda Main.
 * Vytvára okno s výberom levelu, ktorý chce hráč spustiť.
 * Po kliknutí na tlačítko sa spustí hra s daným levelom.
 */
public class Main {
    /**
     * Vytvara okno s vyberom levelu
     */
    public static void main(String[] args) {
        // vytvori okno
        JFrame okno = new JFrame("Plants vs. Zombies - výber levelu");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setLayout(new BorderLayout());

        // vytvori nadpis a panel pre tlacitka
        JLabel nadpis = new JLabel("Vitajte v hre Plants vs. Zombies! Vyberte level, ktorý chcete spustiť");
        JPanel tlacitka = new JPanel();

        // vytvori tlacitka pre jednotlive levely
        for (int i = 1; i <= 10; i++) {
            JButton tlacitko = new JButton(String.valueOf(i));
            // po kliknuti na tlacitko sa spusti hra s danym levelom a zavrie sa okno s vyberom
            tlacitko.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    spusti(tlacitko.getText());
                    okno.dispose();
                }
            });
            tlacitka.add(tlacitko);
        }

        // vytvori okno
        okno.add(nadpis, BorderLayout.NORTH);
        okno.add(tlacitka, BorderLayout.CENTER);
        okno.pack();
        okno.setVisible(true);
    }

    /**
     * Spustí hru s daným levelom
     *
     * @param nazovSuboru názov súboru s levelom
     */
    public static void spusti(String nazovSuboru) {
        Hra hra = Hra.getHra(nazovSuboru);
    }
}
