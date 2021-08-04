package view;

import controler.*;
import game.*;
import java.awt.*;
import javax.swing.*;

/**
 * Classe GameView représentant l'interface de jeu. Elle permet d'afficher les cartes du jeu, les différentes options de gameplay etc
 * @author Jaouen MARIE
 * @version 1.0
 */
public class GameView extends JPanel{
    private JPanel panelB;
    private JPanel panelO;
    private JPanel actions;
    private JButton getEcus;
    private JButton getBuilding;
    private JButton getBuilder;
    private JButton putBuilder;
    private JButton finish;
    private JButton options;
    private JLabel joueurName;
    private JLabel joueurEcus;
    private JLabel joueurTour;
    private JPanel infos;
    private Game game;
    private CardListener cardListener;
    private boolean finished;

    /**
     * Constructeur de GameView permettant d'initaliser la vue
     * @param listener listener des boutons
     * @param cardListener listener des cartes
     * @param game partie de jeu
     */
    public GameView(ButtonListener listener, CardListener cardListener, Game game) {
        this.cardListener = cardListener;
        this.game = game;
        this.finished = false;

        this.refreshCard();

        joueurName = new JLabel();
        joueurName.setFont(new Font("Sherif", Font.BOLD, 25));
        joueurName.setBackground(Color.BLUE);

        joueurEcus = new JLabel();
        joueurEcus.setFont(new Font("Sherif", Font.BOLD, 25));
        joueurEcus.setBackground(Color.BLUE);

        joueurTour = new JLabel();
        joueurTour.setFont(new Font("Sherif", Font.BOLD, 25));
        joueurTour.setBackground(Color.BLUE);

        infos = new JPanel();
        infos.setLayout(new GridLayout(3,1));
        infos.add(joueurName);
        infos.add(joueurEcus);
        infos.add(joueurTour);


        actions = new JPanel();
        actions.setLayout(new GridLayout(3,2));
        actions.setOpaque(false);

        getEcus = new JButton("Prendre des écus");
        getEcus.addActionListener(listener);

        getBuilding = new JButton("Prendre un batiment");
        getBuilding.addActionListener(listener);

        getBuilder = new JButton("Prendre un ouvrier");
        getBuilder.addActionListener(listener);

        putBuilder = new JButton("Mettre un ouvrier sur un chantier");
        putBuilder.addActionListener(listener);

        finish = new JButton("Finir votre tour");
        finish.addActionListener(listener);

        options = new JButton("Options");
        options.addActionListener(listener);

        actions.add(getEcus);
        actions.add(getBuilding);
        actions.add(getBuilder);
        actions.add(putBuilder);
        actions.add(finish);
        actions.add(options);

        setLayout(new GridLayout(4,1));
        setOpaque(false);

        add(infos);
        add(panelB);
        add(panelO);
        add(actions);
    }

    public void setPlayerName(String t) {
        this.joueurName.setText(t);
    }

    public void setPlayerEcus(String t) {
        this.joueurEcus.setText(t);
    }

    public void setPlayerTour(String t) {
        this.joueurTour.setText(t);
    }

    /**
     * Méthode permettant de rafraichir l'aspect des cartes
     */
    public void refreshCard() {
        panelB = new JPanel();
        panelB.setLayout(new GridLayout(1,6));
        panelB.setOpaque(false);

        panelB.add(new JLabel("Les Batiments"));
        for(int i = 0; i < 5; i++) {
            String path = ((IBuilding) game.getDeck().getBuildingCard(i)).getPathChantier();
            if(((IBuilding) game.getDeck().getBuildingCard(i)).getFinished()) path = ((IBuilding) game.getDeck().getBuildingCard(i)).getPathFini();
            JLabel img = new JLabel(new ImageIcon(path));
            img.addMouseListener(cardListener);
            panelB.add(img);
        }

        panelO = new JPanel();
        panelO.setLayout(new GridLayout(1,6));
        panelO.setOpaque(false);

        panelO.add(new JLabel("Les Ouvriers"));
        for(int i = 0; i < 5; i++) {
            String path = game.getDeck().getBuilderCard(i).getPath();
            JLabel img = new JLabel(new ImageIcon(path));
            img.addMouseListener(cardListener);
            panelO.add(img);
        }
    }

    /**
     * Méthode permettant de récupérer des écus
     */
    public void getEcus() {
        int input = Integer.parseInt(JOptionPane.showInputDialog(this, "Nombre d'action à dépenser (1 = 1 écu, 2 = 3 écus, 3 = 6 écus) :"));
        if(input == 1) this.game.getCurrentPlayer().modifyNbEcus(1);
		else if(input == 2) this.game.getCurrentPlayer().modifyNbEcus(3);
		else if(input == 3) this.game.getCurrentPlayer().modifyNbEcus(6);
        this.game.getCurrentPlayer().removeRemainingTurn(input);
    }

    /**
     * Méthode permetttant de récupérer un batiment
     */
    public void getBuilding() {
        int input = Integer.parseInt(JOptionPane.showInputDialog(this, "Batiment que vous souhaiter prendre (batiment 0, 1, 2, 3 ou 4) :"));
        if(input == 0) {
            this.game.getCurrentPlayer().getPlayerDeck().addBuilding((IBuilding) this.game.getDeck().getBuildingCard(0));
            this.game.getCurrentPlayer().getPlayerDeck().removeBuilding((IBuilding) this.game.getDeck().getBuildingCard(0));
        }
        else if(input == 1){
            this.game.getCurrentPlayer().getPlayerDeck().addBuilding((IBuilding) this.game.getDeck().getBuildingCard(1));
            this.game.getCurrentPlayer().getPlayerDeck().removeBuilding((IBuilding) this.game.getDeck().getBuildingCard(1));
        }
        else if(input == 2) {
            this.game.getCurrentPlayer().getPlayerDeck().addBuilding((IBuilding) this.game.getDeck().getBuildingCard(2));
            this.game.getCurrentPlayer().getPlayerDeck().removeBuilding((IBuilding) this.game.getDeck().getBuildingCard(2));
        }
        else if(input == 3) {
            this.game.getCurrentPlayer().getPlayerDeck().addBuilding((IBuilding) this.game.getDeck().getBuildingCard(3));
            this.game.getCurrentPlayer().getPlayerDeck().removeBuilding((IBuilding) this.game.getDeck().getBuildingCard(3));
        }
        else if(input == 4) {
            this.game.getCurrentPlayer().getPlayerDeck().addBuilding((IBuilding) this.game.getDeck().getBuildingCard(4));
            this.game.getCurrentPlayer().getPlayerDeck().removeBuilding((IBuilding) this.game.getDeck().getBuildingCard(4));
        }
        this.game.getCurrentPlayer().removeRemainingTurn(1);
    }

    /**
     * Méthode permettant de récupérer un constructeur
     */
    public void getBuilder() {
        int input = Integer.parseInt(JOptionPane.showInputDialog(this, "Batiment que vous souhaiter prendre (batiment 0, 1, 2, 3 ou 4) :"));
        if(input == 0) {
            this.game.getCurrentPlayer().getPlayerDeck().addBuilder(this.game.getDeck().getBuilderCard(0));
            this.game.getDeck().removeBuilder(this.game.getDeck().getBuilderCard(0));
        }
        else if(input == 1) {
            this.game.getCurrentPlayer().getPlayerDeck().addBuilder(this.game.getDeck().getBuilderCard(1));
            this.game.getDeck().removeBuilder(this.game.getDeck().getBuilderCard(1));
        }
        else if(input == 2) {
            this.game.getCurrentPlayer().getPlayerDeck().addBuilder(this.game.getDeck().getBuilderCard(2));
            this.game.getDeck().removeBuilder(this.game.getDeck().getBuilderCard(2));
        }
        else if(input == 3) {
            this.game.getCurrentPlayer().getPlayerDeck().addBuilder(this.game.getDeck().getBuilderCard(3));
            this.game.getDeck().removeBuilder(this.game.getDeck().getBuilderCard(3));
        }
        else if(input == 4) {
            this.game.getCurrentPlayer().getPlayerDeck().addBuilder(this.game.getDeck().getBuilderCard(4));
            this.game.getDeck().removeBuilder(this.game.getDeck().getBuilderCard(4));
        }
        this.game.getCurrentPlayer().removeRemainingTurn(1);
    }

    /**
     * Méthode permettant de poser un constructeur sur un chantier
     */
    public void putBuilder() {
        int input1 = Integer.parseInt(JOptionPane.showInputDialog(this, "Ouvrier à mettre sur un chantier : "));
        int input2 = Integer.parseInt(JOptionPane.showInputDialog(this, "Chantier sur lequel mettre l'ouvrier : "));
        if(!((IBuilding) this.game.getCurrentPlayer().getPlayerDeck().getBuildingCard(input2)).getFinished()) {
            this.game.getCurrentPlayer().getPlayerDeck().putBuilderOnBuildingSite(input2, input1);
            ((IBuilding)this.game.getCurrentPlayer().getPlayerDeck().getBuildingCard(input2)).checkIsFinished(this.game.getCurrentPlayer());
            this.game.getCurrentPlayer().removeRemainingTurn(1);
            this.game.getCurrentPlayer().modifyNbEcus(this.game.getCurrentPlayer().getPlayerDeck().getBuilderCard(input1).getCost());
        }else {
            System.out.println("The construction of this building is finished");
        }
    }

    /**
     * Méthode permettant de savoir si le joueur à fini son tour
     * @return vrai si a fini, faux sinon
     */
    public boolean getFinished() {
        return finished;
    }

    /**
     * Méthode permettant de modifier la valeur de finished
     * @param b valeur boolean
     */
    public void setFinished(boolean b) {
        this.finished = b;
    }

    /**
     * Méthode permettant d'afficher les options
     */
    public void options() {

    }
}
