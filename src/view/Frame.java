package view;

import game.Game;
import controler.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

/**
 * Classe Frame représentant la frame principal du jeu. Elle regroupe toutes les différentes interfaces 
 * qu'elle va modifier en fonction des appels de méthodes
 * @author Jaouen MARIE
 * @version 1.0
 */
public class Frame extends JFrame {

    /** partie de jeu */
    Game g;
    /** image de fond */
    ImagePanel background;
    /** menu du jeu */
    MenuPanel menu;
    /** règles du jeu */
    RulesView rules;
    /** interface du plateau */
    GameView gamev;
    /** interface du paramétrage de la partie */
    OptionsPanel options;
    /** listener des boutons */
    ButtonListener listener;
    /** listener des cartes */
    CardListener cardListener;


    /**
     * Construteur de Frame permettant d'initialiser les différents paramaètres
     * @param g partie de jeu
     */
    public Frame(Game g) {
        if(g != null) this.g = g;
        else throw new IllegalArgumentException("Error - Frame - passed parameter Game can't be null ");
        this.initComponents();
        this.setTitle("Les Batisseurs - Moyen-Âge");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Mtéthode permettant d'initialiser les différents attributs
     */
    private void initComponents() {
        this.setSize(new Dimension(1600, 900));
        this.setResizable(false);
        this.background = new ImagePanel("./data/images/background.jpg");
        this.listener = new ButtonListener(this);
        this.cardListener = new CardListener(this);
        this.menu = new MenuPanel(listener);
        this.rules = new RulesView(listener);
        this.options = new OptionsPanel(listener, g);
        this.gamev = new GameView(listener, cardListener, g);
        menuView();
    }

    /**
     * Méthode permettant de basculer sur la vue du menu
     */
    public void menuView() {
        removeAllComponents();
        setContentPane(background);
        this.setLayout(new BorderLayout());
        add(menu);
        this.setMusique("./data/musiques/batisseurs.wav");
        repaint();
        revalidate();
    }

    /**
     * Méthode permettant de basculer sur la vue du du parametrage de la partie
     */
    public void optionView() {
        removeAllComponents();
        setContentPane(background);
        this.setLayout(new BorderLayout());
        add(options);
        repaint();
        revalidate();
    }

    /**
     * Méthode permettant de basculer sur la vue du plateau de jeu
     */
    public void gameView() {
        removeAllComponents();
        setContentPane(background);
        this.setLayout(new BorderLayout());
        this.gamev.setPlayerName(this.g.getCurrentPlayer().getName());
        this.gamev.setPlayerEcus("Nombre d'écus : " + this.g.getCurrentPlayer().getNbEcus());
        this.gamev.setPlayerTour("Nombre de tours gratuits restants : " + this.g.getCurrentPlayer().getRemainingTurn());
        add(gamev);
        repaint();
        revalidate();
    }

    /**
     * Méthode permettant de basculer sur le jeu en chargeant une partie sauvegardé
     */
    public void chargerView() {
        g.charger();
        this.gameView();
    }

    /**
     * Méthode permettant de basculer sur la vue des règles
     */
    public void reglesView() {
        removeAllComponents();
        setContentPane(background);
        this.setLayout(new BorderLayout());
        add(rules);
        repaint();
        revalidate();
    }

    /**
     * Méthode permettant d'enlever tous les composants de la frame
     */
    private void removeAllComponents() {
        this.remove(menu);
        this.remove(rules);
        this.remove(gamev);
        this.remove(options);
    }

    /**
     * Methode getter permettant de récupérer l'instance de la vue des règles
     * @return vue des règles
     */
    public RulesView getRulesView() {
        return rules;
    }

    /**
     * Methode getter permettant de récupérer l'instance de la vue du parametrage
     * @return vue du parametrage
     */
    public OptionsPanel getOptionsPanel() {
        return options;
    }

    /**
     * Methode getter permettant de récupérer l'instance de la vue du jeu
     * @return vue du jeu
     */
    public GameView getGameView() {
        return this.gamev;
    }

    /**
     * Methode getter permettant de récupérer l'instance de la partie
     * @return partie de jeu
     */
    public Game getGame() {
        return g;
    } 

    /**
     * Methode permettant de changer l'ambiance sonore du jeu
     * @param path lien du chemin de la musique
     */
    public void setMusique(String path) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(path)));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
