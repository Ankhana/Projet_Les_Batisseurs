package view;

import controler.ButtonListener;
import game.*;
import java.awt.*;
import javax.swing.*;

/**
 * Classe OptionsPanel représentant l'interface des options de parametrage de la partie
 * @author Jaouen MARIE
 * @version 1.0
 */
public class OptionsPanel extends JPanel{
    
    private JTextField name1;
    private JTextField name2;
    private JTextField name3;
    private JTextField name4;
    private JPanel name;
    private JComboBox<Mode> mode;
    private JButton retour;
    private JButton lancer;
    private JPanel panelB;
    private JPanel panel;
    private Game g;

    public OptionsPanel(ButtonListener listener, Game g) {
        this.g=g;

        name = new JPanel();
        name.setLayout(new GridLayout(2,2));
        name.setOpaque(false);

        name1 = new JTextField("Nom joueur 1");
        name2 = new JTextField("Nom joueur 2");
        name3 = new JTextField("Nom joueur 3");
        name4 = new JTextField("Nom joueur 4");

        name.add(name1);
        name.add(name2);
        name.add(name3);
        name.add(name4);

        mode = new JComboBox<Mode>(Mode.values());

        retour = new JButton("Retour");
        retour.addActionListener(listener);
        lancer = new JButton("Lancer");
        lancer.addActionListener(listener);

        panelB = new JPanel();
        panelB.setLayout(new GridLayout(1,2));
        panelB.setOpaque(false);

        panelB.add(retour);
        panelB.add(lancer);

        panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(5,1));
        panel.add(mode);
        panel.add(new JLabel(""));
        panel.add(name);
        panel.add(new JLabel(""));
        panel.add(panelB);

        setLayout(new GridLayout(3,3));
        setOpaque(false);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(panel);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
    }

    public void initializeParam() {
        this.g.initializeGame((Mode) mode.getSelectedItem(), name1.getText(), name2.getText(), name3.getText(), name4.getText());
    
    }

    public Mode getMode() {
        return (Mode) mode.getSelectedItem();
    }

    public String getName1() {
        return name1.getText();
    }

    public String getName2() {
        return name2.getText();
    }

    public String getName3() {
        return name3.getText();
    }

    public String getName4() {
        return name4.getText();
    }
}
