package view;

import controler.ButtonListener;
import java.awt.*;
import javax.swing.*;

/**
 * Classe MenuPanel représentant l'interface de menu
 * @author Jaouen MARIE
 * @version 1.0
 */
public class MenuPanel extends JPanel{
    
    private ImagePanel titre;
    private JButton jouer;
    private JButton charger;
    private JButton regles;
    private JButton quitter;
    private JPanel panelB;
    private JPanel panelI;

    public MenuPanel(ButtonListener listener) {
        this.setLayout(new GridLayout(1,2));
        this.setOpaque(false);

        titre = new ImagePanel("./data/images/titre.png");
        titre.setFont(new Font("Sherif", Font.BOLD, 25));

        jouer = new JButton("Jouer");
        jouer.addActionListener(listener);

        charger = new JButton("Charger");
        charger.addActionListener(listener);

        regles = new JButton("Règles du jeu");
        regles.addActionListener(listener);

        quitter = new JButton("Quitter");
        quitter.addActionListener(listener);

        panelB = new JPanel();
        panelB.setLayout(new GridLayout(9,1));
        panelB.setOpaque(false);

        panelB.add(new JLabel(""));
        panelB.add(jouer);
        panelB.add(new JLabel(""));
        panelB.add(charger);
        panelB.add(new JLabel(""));
        panelB.add(regles);
        panelB.add(new JLabel(""));
        panelB.add(quitter);
        panelB.add(new JLabel(""));

        panelI = new JPanel();
        panelI.setLayout(new GridLayout(3,1));
        panelI.setOpaque(false);

        panelI.add(new JLabel(""));
        panelI.add(titre);
        panelI.add(new JLabel(""));

        add(panelI, BorderLayout.WEST);
        add(panelB, BorderLayout.EAST);
    }
}
