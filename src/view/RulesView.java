package view;

import controler.ButtonListener;
import java.awt.*;
import javax.swing.*;

/**
 * Classe RulesView représentant l'interface des règles de jeu
 * @author Jaouen MARIE
 * @version 1.0
 */
public class RulesView extends JPanel{
    private JButton suivant;
    private JButton precedent;
    private JButton retour;
    private int page;
    private ImageIcon img;
    private JLabel imgLabel;
    private JPanel panel;

    public RulesView(ButtonListener listener) {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);

        suivant = new JButton("Suivant");
        suivant.addActionListener(listener);

        precedent = new JButton("Precedent");
        precedent.addActionListener(listener);

        retour = new JButton("Retour");
        retour.addActionListener(listener);

        page = 1;
        precedent.setEnabled(false);
        img = new ImageIcon("./data/images/regles/" + page + ".png");

        imgLabel = new JLabel();
        imgLabel.setIcon(img);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setOpaque(false);

        panel.add(precedent, BorderLayout.WEST);
        panel.add(imgLabel,BorderLayout.CENTER);
        panel.add(suivant, BorderLayout.EAST);

        add(panel, BorderLayout.CENTER);
        add(retour, BorderLayout.SOUTH);
    }

    public void suivante() {
        if(page >= 7) {
            suivant.setEnabled(false);
        }else{
            suivant.setEnabled(true);
            precedent.setEnabled(true);
        }
        page++;
        img = new ImageIcon("./data/images/regles/" + page + ".png");
        imgLabel.setIcon(img);
        repaint();
        revalidate();   
    }

    public void precedente() {
        if(page <= 2) {
            precedent.setEnabled(false);
        }else{
            precedent.setEnabled(true);
            suivant.setEnabled(true);
        }
        page--;
        img = new ImageIcon("./data/images/regles/" + page + ".png");
        imgLabel.setIcon(img);
        repaint();
        revalidate();
    }
}
