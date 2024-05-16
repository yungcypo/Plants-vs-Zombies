package main;

import hra.Hra;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    public static void main(String[] args) {
        JFrame okno = new JFrame("Plants vs. Zombies - výber levelu");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setLayout(new BorderLayout());

        JLabel nadpis = new JLabel("Vitajte v hre Plants vs. Zombies! Vyberte level, ktorý chcete spustiť");
        JPanel tlacitka = new JPanel();

        for (int i = 1; i <= 10; i++) {
            JButton tlacitko = new JButton(String.valueOf(i));
            tlacitko.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    spusti(tlacitko.getText());
                    okno.dispose();
                }
            });
            tlacitka.add(tlacitko);
        }

        okno.add(nadpis, BorderLayout.NORTH);
        okno.add(tlacitka, BorderLayout.CENTER);

        okno.pack();
        okno.setVisible(true);
    }

    public static void spusti(String nazovSuboru) {
        Hra hra = Hra.getHra(nazovSuboru);
    }
}
